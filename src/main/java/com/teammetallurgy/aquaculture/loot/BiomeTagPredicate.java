package com.teammetallurgy.aquaculture.loot;

import com.google.common.collect.Lists;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import com.teammetallurgy.aquaculture.Aquaculture;
import com.teammetallurgy.aquaculture.misc.BiomeDictionaryHelper;
import net.minecraft.advancements.criterion.MinMaxBounds;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.BiomeDictionary;

import javax.annotation.Nullable;
import java.util.*;

public class BiomeTagPredicate {
    private static final BiomeTagPredicate ANY = new BiomeTagPredicate(MinMaxBounds.FloatBound.UNBOUNDED, MinMaxBounds.FloatBound.UNBOUNDED, MinMaxBounds.FloatBound.UNBOUNDED, Lists.newArrayList(), Lists.newArrayList(), false);
    private static final HashMap<CheckType, List<ResourceLocation>> CACHE = new HashMap<>();
    private static final List<BiomeDictionary.Type> INVALID_TYPES = Arrays.asList(BiomeDictionary.Type.NETHER, BiomeDictionary.Type.END);
    private final MinMaxBounds.FloatBound x;
    private final MinMaxBounds.FloatBound y;
    private final MinMaxBounds.FloatBound z;
    private final List<BiomeDictionary.Type> include;
    private final List<BiomeDictionary.Type> exclude;
    private final boolean and;

    public BiomeTagPredicate(MinMaxBounds.FloatBound x, MinMaxBounds.FloatBound y, MinMaxBounds.FloatBound z, List<BiomeDictionary.Type> include, List<BiomeDictionary.Type> exclude, boolean and) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.include = include;
        this.exclude = exclude;
        this.and = and;
    }

    public boolean test(ServerWorld world, float x, float y, float z) {
        if (!this.x.test(x)) {
            return false;
        } else if (!this.y.test(y)) {
            return false;
        } else if (!this.z.test(z)) {
            return false;
        } else {
            BlockPos pos = new BlockPos(x, y, z);
            Biome biome = world.getBiome(pos);
            ResourceLocation biomeFromRegistry = world.func_241828_r().getRegistry(Registry.BIOME_KEY).getKey(biome);

            CheckType checkType = CheckType.getOrCreate(this.include, this.exclude, this.and);

            List<ResourceLocation> validBiomes = CACHE.get(checkType);
            if (validBiomes == null) {
                validBiomes = getValidBiomes(checkType);
                CACHE.put(checkType, validBiomes);
            }
            return validBiomes.contains(biomeFromRegistry);
        }
    }

    public static List<ResourceLocation> getValidBiomes(CheckType checkType) {
        return getValidBiomes(checkType.getInclude(), checkType.getExclude(), checkType.isAnd());
    }

    public static List<ResourceLocation> getValidBiomes(List<BiomeDictionary.Type> includeList, List<BiomeDictionary.Type> excludeList, boolean and) { //Can't add biome as a parameter, since this is called elsewhere where world is not available
        List<ResourceLocation> biomes = Lists.newArrayList();

        if (includeList.isEmpty() && !excludeList.isEmpty()) { //Add all BiomeDictionary tags, when only excluding biomes
            Set<BiomeDictionary.Type> validTypes = new HashSet<>(BiomeDictionary.Type.getAll());
            includeList.addAll(validTypes);
            excludeList.addAll(INVALID_TYPES);
        }

        if (!includeList.isEmpty()) {
            List<RegistryKey<Biome>> addBiomes = Lists.newArrayList();
            for (BiomeDictionary.Type type : includeList) {
                addBiomes.addAll(BiomeDictionary.getBiomes(type));
            }

            if (and) {
                for (BiomeDictionary.Type type : includeList) {
                    addBiomes.removeIf(biome -> !BiomeDictionary.getBiomes(type).contains(biome));
                }
            }

            if (includeList.stream().noneMatch(INVALID_TYPES::contains)) { //Exclude invalid tags, as long as they're not specified in include
                excludeList.addAll(INVALID_TYPES);
            }
            for (RegistryKey<Biome> addBiome : addBiomes) {
                if (!biomes.contains(addBiome.getLocation())) {
                    biomes.add(addBiome.getLocation());
                }
            }
        }
        if (!excludeList.isEmpty()) {
            for (BiomeDictionary.Type type : excludeList) {
                for (RegistryKey<Biome> biome : BiomeDictionary.getBiomes(type)) {
                    biomes.remove(biome.getLocation());
                }
            }
        }

        return biomes;
    }

    public JsonElement serialize() {
        if (this == ANY) {
            return JsonNull.INSTANCE;
        } else {
            JsonObject object = new JsonObject();
            if (!this.x.isUnbounded() || !this.y.isUnbounded() || !this.z.isUnbounded()) {
                JsonObject posObj = new JsonObject();
                posObj.add("x", this.x.serialize());
                posObj.add("y", this.y.serialize());
                posObj.add("z", this.z.serialize());
                object.add("position", posObj);
            }
            if (this.include != null) {
                for (BiomeDictionary.Type type : this.include) {
                    object.add("include", object.getAsJsonArray(type.getName()));
                }
            }
            if (this.exclude != null) {
                for (BiomeDictionary.Type type : this.exclude) {
                    object.add("exclude", object.getAsJsonArray(type.getName()));
                }
            }
            object.addProperty("add", object.getAsBoolean());
            return object;
        }
    }

    public static BiomeTagPredicate deserialize(@Nullable JsonElement element) {
        if (element != null && !element.isJsonNull()) {
            JsonObject location = JSONUtils.getJsonObject(element, "location");
            JsonObject position = JSONUtils.getJsonObject(location, "position", new JsonObject());
            MinMaxBounds.FloatBound x = MinMaxBounds.FloatBound.fromJson(position.get("x"));
            MinMaxBounds.FloatBound y = MinMaxBounds.FloatBound.fromJson(position.get("y"));
            MinMaxBounds.FloatBound z = MinMaxBounds.FloatBound.fromJson(position.get("z"));
            List<BiomeDictionary.Type> include = Lists.newArrayList();
            if (location.has("include")) {
                JsonArray includeArray = JSONUtils.getJsonArray(location, "include");
                for (int entry = 0; entry < includeArray.size(); entry++) {
                    String name = includeArray.get(entry).getAsString().toLowerCase(Locale.ROOT);
                    BiomeDictionary.Type type = BiomeDictionaryHelper.getType(name);
                    if (type == null) {
                        Aquaculture.LOG.error("Failed to include BiomeDictionary Type: " + name + ". Please check your loot tables");
                    } else {
                        include.add(type);
                    }
                }
            }

            List<BiomeDictionary.Type> exclude = Lists.newArrayList();
            if (location.has("exclude")) {
                JsonArray excludeArray = JSONUtils.getJsonArray(location, "exclude");
                for (int entry = 0; entry < excludeArray.size(); entry++) {
                    String name = excludeArray.get(entry).getAsString().toLowerCase(Locale.ROOT);
                    BiomeDictionary.Type type = BiomeDictionaryHelper.getType(name);
                    if (type == null) {
                        Aquaculture.LOG.error("Failed to exclude BiomeDictionary Type: " + name + ". Please check your loot tables");
                    } else {
                        exclude.add(type);
                    }
                }
            }
            boolean and = false;
            if (location.has("and")) {
                and = JSONUtils.getBoolean(location, "and");
            }
            return new BiomeTagPredicate(x, y, z, include, exclude, and);
        } else {
            return ANY;
        }
    }

    public static class CheckType {
        private static final Map<Integer, CheckType> BY_NAME = new TreeMap<>();
        private final List<BiomeDictionary.Type> include;
        private final List<BiomeDictionary.Type> exclude;
        private final boolean and;

        private CheckType(List<BiomeDictionary.Type> include, List<BiomeDictionary.Type> exclude, boolean and) {
            this.include = include;
            this.exclude = exclude;
            this.and = and;

            BY_NAME.put(this.hashCode(), this);
        }

        public List<BiomeDictionary.Type> getInclude() {
            return this.include;
        }

        public List<BiomeDictionary.Type> getExclude() {
            return this.exclude;
        }

        public boolean isAnd() {
            return this.and;
        }

        public static CheckType getOrCreate(List<BiomeDictionary.Type> include, List<BiomeDictionary.Type> exclude, boolean and) {
            CheckType checkType = BY_NAME.get(Objects.hash(include, exclude, and));
            if (checkType == null) {
                checkType = new CheckType(include, exclude, and);
            }
            return checkType;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            CheckType checkType = (CheckType) o;
            return this.and == checkType.and && Objects.equals(this.include, checkType.include) && Objects.equals(this.exclude, checkType.exclude);
        }

        @Override
        public int hashCode() {
            return Objects.hash(this.include, this.exclude, this.and);
        }
    }
}
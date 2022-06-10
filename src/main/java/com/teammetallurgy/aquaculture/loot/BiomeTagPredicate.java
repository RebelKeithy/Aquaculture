package com.teammetallurgy.aquaculture.loot;

import com.google.common.collect.Lists;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import net.minecraft.advancements.critereon.MinMaxBounds;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BiomeTags;
import net.minecraft.tags.TagKey;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.level.biome.Biome;

import javax.annotation.Nullable;
import java.util.*;
import java.util.stream.Collectors;

public class BiomeTagPredicate {
    private static final BiomeTagPredicate ANY = new BiomeTagPredicate(MinMaxBounds.Doubles.ANY, MinMaxBounds.Doubles.ANY, MinMaxBounds.Doubles.ANY, Lists.newArrayList(), Lists.newArrayList(), false);
    private static final HashMap<CheckType, List<ResourceLocation>> CACHE = new HashMap<>();
    public static final List<TagKey<Biome>> INVALID_TYPES = Arrays.asList(BiomeTags.IS_NETHER, BiomeTags.IS_END);
    private final MinMaxBounds.Doubles x;
    private final MinMaxBounds.Doubles y;
    private final MinMaxBounds.Doubles z;
    private final List<TagKey<Biome>> include;
    private final List<TagKey<Biome>> exclude;
    private final boolean and;

    public BiomeTagPredicate(MinMaxBounds.Doubles x, MinMaxBounds.Doubles y, MinMaxBounds.Doubles z, List<TagKey<Biome>> include, List<TagKey<Biome>> exclude, boolean and) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.include = include;
        this.exclude = exclude;
        this.and = and;
    }

    public boolean test(ServerLevel serverLevel, float x, float y, float z) {
        if (!this.x.matches(x)) {
            return false;
        } else if (!this.y.matches(y)) {
            return false;
        } else if (!this.z.matches(z)) {
            return false;
        } else {
            BlockPos pos = new BlockPos(x, y, z);
            Biome biome = serverLevel.getBiome(pos).value();
            ResourceLocation biomeFromRegistry = serverLevel.registryAccess().registryOrThrow(Registry.BIOME_REGISTRY).getKey(biome);

            CheckType checkType = CheckType.getOrCreate(this.include, this.exclude, this.and);

            List<ResourceLocation> validBiomes = CACHE.get(checkType);
            if (validBiomes == null) {
                validBiomes = getValidBiomes(serverLevel, checkType);
                CACHE.put(checkType, validBiomes);
            }
            return validBiomes.contains(biomeFromRegistry);
        }
    }

    public static List<ResourceLocation> getValidBiomes(ServerLevel serverLevel, CheckType checkType) {
        return getValidBiomes(serverLevel, checkType.getInclude(), checkType.getExclude(), checkType.isAnd());
    }

    //TODO
    public static List<ResourceLocation> getValidBiomes(ServerLevel serverLevel, List<TagKey<Biome>> includeList, List<TagKey<Biome>> excludeList, boolean and) {
        List<ResourceLocation> biomes = Lists.newArrayList();

        if (includeList.isEmpty() && !excludeList.isEmpty()) { //Add all tags, when only excluding biomes
            Optional<? extends Registry<Biome>> biomeRegistry = serverLevel.registryAccess().registry(Registry.BIOME_REGISTRY);
            if (biomeRegistry.isPresent()) {
                Set<TagKey<Biome>> validTypes = biomeRegistry.get().getTagNames().collect(Collectors.toSet());
                includeList.addAll(validTypes);
            }
            excludeList.addAll(INVALID_TYPES);
        }

        /*if (!includeList.isEmpty()) {
            List<ResourceKey<Biome>> addBiomes = Lists.newArrayList();
            for (TagKey<Biome> type : includeList) {
                addBiomes.addAll(BiomeDictionary.getBiomes(type));
            }

            if (and) {
                for (TagKey<Biome> type : includeList) {
                    addBiomes.removeIf(biome -> !BiomeDictionary.getBiomes(type).contains(biome));
                }
            }

            if (includeList.stream().noneMatch(INVALID_TYPES::contains)) { //Exclude invalid tags, as long as they're not specified in include
                excludeList.addAll(INVALID_TYPES);
            }
            for (ResourceKey<Biome> addBiome : addBiomes) {
                if (!biomes.contains(addBiome.location())) {
                    biomes.add(addBiome.location());
                }
            }
        }
        if (!excludeList.isEmpty()) {
            for (TagKey<Biome> type : excludeList) {
                for (ResourceKey<Biome> biome : BiomeDictionary.getBiomes(type)) {
                    biomes.remove(biome.location());
                }
            }
        }*/

        return biomes;
    }

    public JsonElement serialize() {
        if (this == ANY) {
            return JsonNull.INSTANCE;
        } else {
            JsonObject object = new JsonObject();
            if (!this.x.isAny() || !this.y.isAny() || !this.z.isAny()) {
                JsonObject posObj = new JsonObject();
                posObj.add("x", this.x.serializeToJson());
                posObj.add("y", this.y.serializeToJson());
                posObj.add("z", this.z.serializeToJson());
                object.add("position", posObj);
            }
            /*if (this.include != null) { //TODO
                for (TagKey<Biome> type : this.include) {
                    object.add("include", object.getAsJsonArray(type.getName()));
                }
            }
            if (this.exclude != null) {
                for (TagKey<Biome> type : this.exclude) {
                    object.add("exclude", object.getAsJsonArray(type.getName()));
                }
            }*/
            object.addProperty("add", object.getAsBoolean());
            return object;
        }
    }

    public static BiomeTagPredicate deserialize(@Nullable JsonElement element) {
        if (element != null && !element.isJsonNull()) {
            JsonObject location = GsonHelper.convertToJsonObject(element, "location");
            JsonObject position = GsonHelper.getAsJsonObject(location, "position", new JsonObject());
            MinMaxBounds.Doubles x = MinMaxBounds.Doubles.fromJson(position.get("x"));
            MinMaxBounds.Doubles y = MinMaxBounds.Doubles.fromJson(position.get("y"));
            MinMaxBounds.Doubles z = MinMaxBounds.Doubles.fromJson(position.get("z"));
            List<TagKey<Biome>> include = Lists.newArrayList();
            if (location.has("include")) {
                JsonArray includeArray = GsonHelper.getAsJsonArray(location, "include");
                for (int entry = 0; entry < includeArray.size(); entry++) {
                    String name = includeArray.get(entry).getAsString().toLowerCase(Locale.ROOT);
                    /*TagKey<Biome> type = BiomeDictionaryHelper.getType(name); //TODO
                    if (type == null) {
                        Aquaculture.LOG.error("Failed to include BiomeDictionary Type: " + name + ". Please check your loot tables");
                    } else {
                        include.add(type);
                    }*/
                }
            }

            List<TagKey<Biome>> exclude = Lists.newArrayList();
            if (location.has("exclude")) {
                JsonArray excludeArray = GsonHelper.getAsJsonArray(location, "exclude");
                for (int entry = 0; entry < excludeArray.size(); entry++) {
                    String name = excludeArray.get(entry).getAsString().toLowerCase(Locale.ROOT);
                    /*TagKey<Biome> type = BiomeDictionaryHelper.getType(name); //TODO
                    if (type == null) {
                        Aquaculture.LOG.error("Failed to exclude BiomeDictionary Type: " + name + ". Please check your loot tables");
                    } else {
                        exclude.add(type);
                    }*/
                }
            }
            boolean and = false;
            if (location.has("and")) {
                and = GsonHelper.getAsBoolean(location, "and");
            }
            return new BiomeTagPredicate(x, y, z, include, exclude, and);
        } else {
            return ANY;
        }
    }

    public static class CheckType {
        private static final Map<Integer, CheckType> BY_NAME = new TreeMap<>();
        private final List<TagKey<Biome>> include;
        private final List<TagKey<Biome>> exclude;
        private final boolean and;

        private CheckType(List<TagKey<Biome>> include, List<TagKey<Biome>> exclude, boolean and) {
            this.include = include;
            this.exclude = exclude;
            this.and = and;

            BY_NAME.put(this.hashCode(), this);
        }

        public List<TagKey<Biome>> getInclude() {
            return this.include;
        }

        public List<TagKey<Biome>> getExclude() {
            return this.exclude;
        }

        public boolean isAnd() {
            return this.and;
        }

        public static CheckType getOrCreate(List<TagKey<Biome>> include, List<TagKey<Biome>> exclude, boolean and) {
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
package com.teammetallurgy.aquaculture.loot;

import com.google.common.collect.Lists;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import com.teammetallurgy.aquaculture.Aquaculture;
import com.teammetallurgy.aquaculture.misc.AquaConfig;
import net.minecraft.advancements.critereon.MinMaxBounds;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BiomeTags;
import net.minecraft.tags.TagKey;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.level.biome.Biome;
import net.minecraftforge.common.Tags;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nullable;
import java.util.*;
import java.util.stream.Collectors;

public class BiomeTagPredicate {
    private static final BiomeTagPredicate ANY = new BiomeTagPredicate(MinMaxBounds.Doubles.ANY, MinMaxBounds.Doubles.ANY, MinMaxBounds.Doubles.ANY, Lists.newArrayList(), Lists.newArrayList(), false);
    private static final HashMap<CheckType, List<Holder<Biome>>> CACHE = new HashMap<>();
    public static final List<TagKey<Biome>> INVALID_TYPES = Arrays.asList(BiomeTags.IS_NETHER, BiomeTags.IS_END, Tags.Biomes.IS_VOID);
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
            if (serverLevel.isLoaded(pos)) {
                Biome biome = serverLevel.getBiome(pos).value();
                Registry<Biome> biomeRegistry = serverLevel.registryAccess().registryOrThrow(Registry.BIOME_REGISTRY);
                Optional<ResourceKey<Biome>> resourceKey = biomeRegistry.getResourceKey(biome);

                //System.out.println(biomeRegistry.getKey(biome));
                if (resourceKey.isPresent()) {
                    Optional<Holder<Biome>> biomeHolder = biomeRegistry.getHolder(resourceKey.get());
                    if (biomeHolder.isPresent()) {
                        CheckType checkType = CheckType.getOrCreate(this.include, this.exclude, this.and);

                        CACHE.clear();
                        List<Holder<Biome>> validBiomes = CACHE.get(checkType);
                        if (validBiomes == null) {
                            validBiomes = getValidBiomes(serverLevel, checkType);
                            CACHE.put(checkType, validBiomes);
                        }
                        if (AquaConfig.BASIC_OPTIONS.debugMode.get()) {
                            if (validBiomes.contains(biomeHolder.get())) {
                                validBiomes.forEach(v -> Aquaculture.LOG.info("Valid Biome: " + biomeRegistry.getKey(v.get())));
                            }
                        }
                        return validBiomes.contains(biomeHolder.get());
                    }
                }
            }
            return false;
        }
    }

    public static List<Holder<Biome>> getValidBiomes(ServerLevel serverLevel, CheckType checkType) {
        return getValidBiomes(serverLevel, checkType.getInclude(), checkType.getExclude(), checkType.isAnd());
    }

    public static List<Holder<Biome>> getValidBiomes(ServerLevel serverLevel, List<TagKey<Biome>> includeList, List<TagKey<Biome>> excludeList, boolean and) {
        for (TagKey<Biome> test : includeList) {
            System.out.println("TEST: " + test);
        }


        List<Holder<Biome>> biomes = Lists.newArrayList();
        Optional<? extends Registry<Biome>> optionalBiomeRegistry = serverLevel.registryAccess().registry(Registry.BIOME_REGISTRY);
        if (optionalBiomeRegistry.isPresent()) {
            Registry<Biome> biomeRegistry = optionalBiomeRegistry.get();

            if (includeList.isEmpty() && !excludeList.isEmpty()) { //Add all tags, when only excluding biomes
                System.out.println("EXCLUDE ONLY");
                includeList.addAll(biomeRegistry.getTagNames().collect(Collectors.toSet()));
                excludeList.addAll(INVALID_TYPES);
            }

            if (!includeList.isEmpty()) {
                List<Holder<Biome>> addBiomes = Lists.newArrayList();
                for (TagKey<Biome> tagKey : includeList) {
                    getBiomeFromTag(biomeRegistry, tagKey).forEach(addBiomes::add);
                }

                if (and) {
                    for (TagKey<Biome> tagKey : includeList) {
                        List<Holder<Biome>> tagKeyList = Lists.newArrayList();
                        getBiomeFromTag(biomeRegistry, tagKey).forEach(tagKeyList::add);

                        //tagKeyList.forEach(b -> System.out.println("tag key list: " + biomeRegistry.getKey(b.value())));
                        addBiomes.removeIf(biomeHolder -> !tagKeyList.contains(biomeHolder));
                    }
                    //addBiomes.forEach(b -> System.out.println("Add biomes: " + biomeRegistry.getKey(b.value())));
                }

                if (includeList.stream().noneMatch(INVALID_TYPES::contains)) { //Exclude invalid tags, as long as they're not specified in include
                    System.out.println("INVALID TYPES");
                    excludeList.addAll(INVALID_TYPES);
                }
                for (Holder<Biome> addBiome : addBiomes) {
                    if (!biomes.contains(addBiome)) {
                        biomes.add(addBiome);
                    }
                }
            }
            if (!excludeList.isEmpty()) {
                for (TagKey<Biome> tagKey : excludeList) {
                    getBiomeFromTag(biomeRegistry, tagKey).forEach(b -> {
                        System.out.println("Exclude: " + biomeRegistry.getKey(b.value()));
                        biomes.remove(b);
                    });
                }
            }
            getBiomeFromTag(biomeRegistry, BiomeTags.IS_OCEAN).forEach(biomeHolder -> {
                System.out.println("TEST SHIT: " + biomeRegistry.getKey(biomeHolder.get()));
            });
        }
        return biomes;
    }

    public static Iterable<Holder<Biome>> getBiomeFromTag(Registry<Biome> biomeRegistry, TagKey<Biome> tagKey) {
        return biomeRegistry.getTagOrEmpty(tagKey);
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
            if (this.include != null) {
                for (TagKey<Biome> tagKey : this.include) {
                    object.add("include", object.getAsJsonArray(tagKey.location().toString()));
                }
            }
            if (this.exclude != null) {
                for (TagKey<Biome> tagKey : this.exclude) {
                    object.add("exclude", object.getAsJsonArray(tagKey.location().toString()));
                }
            }
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
                    TagKey<Biome> type = TagKey.create(Registry.BIOME_REGISTRY, new ResourceLocation(name));
                    include.add(type);
                }
            }

            List<TagKey<Biome>> exclude = Lists.newArrayList();
            if (location.has("exclude")) {
                JsonArray excludeArray = GsonHelper.getAsJsonArray(location, "exclude");
                for (int entry = 0; entry < excludeArray.size(); entry++) {
                    String name = excludeArray.get(entry).getAsString().toLowerCase(Locale.ROOT);
                    TagKey<Biome> type = TagKey.create(Registry.BIOME_REGISTRY, new ResourceLocation(name));
                    exclude.add(type);
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
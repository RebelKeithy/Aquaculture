//package com.teammetallurgy.aquaculture.loot;
//
//import com.google.common.collect.Lists;
//import com.google.gson.*;
//import com.teammetallurgy.aquaculture.Aquaculture;
//import com.teammetallurgy.aquaculture.init.AquaLootTables;
//import com.teammetallurgy.aquaculture.init.FishRegistry;
//import com.teammetallurgy.aquaculture.misc.AquaConfig;
//import net.minecraft.resources.ResourceLocation;
//import net.minecraft.tags.TagKey;
//import net.minecraft.util.Mth;
//import net.minecraft.world.entity.EntityType;
//import net.minecraft.world.entity.MobCategory;
//import net.minecraft.world.level.biome.Biome;
//import net.minecraft.world.level.biome.MobSpawnSettings;
//import net.minecraftforge.registries.ForgeRegistries;
//import net.minecraftforge.registries.RegistryObject;
//
//import java.io.BufferedReader;
//import java.io.InputStreamReader;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Locale;
//
//public class FishReadFromJson { //TODO
//    public static HashMap<EntityType<?>, List<ResourceLocation>> FISH_BIOME_MAP = new HashMap<>();
//    public static HashMap<EntityType<?>, Integer> FISH_WEIGHT_MAP = new HashMap<>();
//    private static final Gson GSON_INSTANCE = new GsonBuilder().setPrettyPrinting().create();
//    public static boolean hasRunFirstTime;
//
//    public static void read() {
//        try {
//            String filePath = Aquaculture.MOD_ID + "/loot_tables/" + AquaLootTables.FISH.getPath() + ".json";
//            InputStreamReader fileReader = new InputStreamReader(Aquaculture.instance.getClass().getResourceAsStream("/data/" + filePath));
//
//            BufferedReader reader = new BufferedReader(fileReader);
//            JsonElement json = GSON_INSTANCE.fromJson(reader, JsonElement.class);
//            if (json != null && !json.isJsonNull()) {
//                JsonObject jsonObject = json.getAsJsonObject();
//                JsonArray pools = jsonObject.getAsJsonArray("pools");
//                JsonObject poolsObject = pools.get(0).getAsJsonObject();
//                JsonArray entries = poolsObject.getAsJsonArray("entries");
//                for (JsonElement entry : entries) {
//                    JsonArray conditions = entry.getAsJsonObject().getAsJsonArray("conditions");
//                    EntityType<?> fish = getEntityFromString(entry.getAsJsonObject().get("name").toString());
//                    for (JsonElement conditionElement : conditions) {
//                        JsonObject condition = conditionElement.getAsJsonObject();
//                        if (condition.get("condition").getAsString().equals("aquaculture:biome_tag_check")) {
//                            FISH_BIOME_MAP.put(fish, getSpawnableBiomes(condition.get("predicate")));
//                        } else if (condition.get("condition").getAsString().equals("minecraft:alternative")) {
//                            for (JsonElement term : condition.getAsJsonObject().getAsJsonArray("terms")) {
//                                List<ResourceLocation> spawnableBiomes = getSpawnableBiomes(term.getAsJsonObject().get("predicate"));
//                                if (!FISH_BIOME_MAP.containsKey(fish)) {
//                                    FISH_BIOME_MAP.put(fish, spawnableBiomes);
//                                } else {
//                                    spawnableBiomes.forEach(biome -> FISH_BIOME_MAP.get(fish).add(biome));
//                                }
//                            }
//                        }
//                    }
//                    FISH_WEIGHT_MAP.put(fish, entry.getAsJsonObject().get("weight").getAsInt());
//                }
//                //Remove loot entries that does not have an entity
//                FISH_BIOME_MAP.keySet().retainAll(FishRegistry.fishEntities.stream().map(RegistryObject::get).toList());
//                FISH_WEIGHT_MAP.keySet().retainAll(FishRegistry.fishEntities.stream().map(RegistryObject::get).toList());
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    private static EntityType<?> getEntityFromString(String name) {
//        name = name.replace("\"", "");
//        return ForgeRegistries.ENTITIES.getValue(new ResourceLocation(name));
//    }
//
//    private static List<ResourceLocation> getSpawnableBiomes(JsonElement predicate) {
//        List<ResourceLocation> biomes = Lists.newArrayList();
//        List<TagKey<Biome>> includeList = Lists.newArrayList();
//        List<TagKey<Biome>> excludeList = Lists.newArrayList();
//        boolean and = false;
//
//        if (predicate.getAsJsonObject().has("include")) {
//            JsonArray include = predicate.getAsJsonObject().get("include").getAsJsonArray();
//            for (int entry = 0; entry < include.size(); entry++) {
//                includeList.add(BiomeDictionaryHelper.getType(include.get(entry).getAsString().toLowerCase(Locale.ROOT)));
//            }
//        }
//        if (predicate.getAsJsonObject().has("exclude")) {
//            JsonArray exclude = predicate.getAsJsonObject().get("exclude").getAsJsonArray();
//            for (int entry = 0; entry < exclude.size(); entry++) {
//                excludeList.add(BiomeDictionaryHelper.getType(exclude.get(entry).getAsString().toLowerCase(Locale.ROOT)));
//            }
//        }
//        if (predicate.getAsJsonObject().has("and")) {
//            and = predicate.getAsJsonObject().get("and").getAsBoolean();
//        }
//
//        biomes.addAll(BiomeTagPredicate.getValidBiomes(includeList, excludeList, and));
//
//        return biomes;
//    }
//
//    public static void addFishSpawns(BiomeLoadingEvent event) {
//        if (AquaConfig.BASIC_OPTIONS.enableFishSpawning.get()) {
//            //Biome debug
//            if (!FISH_BIOME_MAP.isEmpty()) {
//                for (EntityType<?> fish : FISH_BIOME_MAP.keySet()) {
//                    if (fish != null) {
//                        if (AquaConfig.BASIC_OPTIONS.debugMode.get() && !hasRunFirstTime) {
//                            List<String> strings = new ArrayList<>();
//                            for (ResourceLocation biome : FISH_BIOME_MAP.get(fish)) {
//                                if (biome != null) {
//                                    strings.add(biome.getPath());
//                                }
//                            }
//                            Aquaculture.LOG.info(fish.getRegistryName() + " Biomes: " + strings);
//                        }
//
//
//                        if (FISH_WEIGHT_MAP.get(fish) != null) {
//                            int weight = FISH_WEIGHT_MAP.get(fish) / 3;
//                            int maxGroupSize = Mth.clamp((FISH_WEIGHT_MAP.get(fish) / 10), 1, 8);
//                            if (weight < 1) weight = 1;
//                            if (AquaConfig.BASIC_OPTIONS.debugMode.get() && !hasRunFirstTime) {
//                                Aquaculture.LOG.info(fish.getRegistryName() + " spawn debug = loottable weight: " + FISH_WEIGHT_MAP.get(fish) + " | weight : " + weight + " | maxGroupSize: " + maxGroupSize);
//                            }
//                            ResourceLocation name = event.getName();
//                            if (name != null) {
//                                for (ResourceLocation biome : FISH_BIOME_MAP.get(fish)) {
//                                    if (name.equals(biome)) {
//                                        event.getSpawns().getSpawner(MobCategory.WATER_AMBIENT).add(new MobSpawnSettings.SpawnerData(fish, weight, 1, maxGroupSize));
//                                    }
//                                }
//                            }
//                        }
//                    }
//                    hasRunFirstTime = true;
//                }
//            }
//        }
//    }
//}
package com.teammetallurgy.aquaculture.loot;

import com.google.common.collect.Lists;
import com.google.gson.*;
import com.teammetallurgy.aquaculture.Aquaculture;
import com.teammetallurgy.aquaculture.init.AquaLootTables;
import com.teammetallurgy.aquaculture.init.FishRegistry;
import com.teammetallurgy.aquaculture.misc.BiomeDictionaryHelper;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.registries.ForgeRegistries;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.List;

public class FishReadFromJson {
    public static HashMap<EntityType<?>, List<Biome>> FISH_BIOME_MAP = new HashMap<>();
    private static final Gson GSON_INSTANCE = new GsonBuilder().setPrettyPrinting().create();

    public static void read() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(ModList.get().getModFileById(Aquaculture.MOD_ID).getFile().findResource("data\\aquaculture\\loot_tables\\" + AquaLootTables.FISH.getPath() + ".json").toFile()));
            JsonElement json = GSON_INSTANCE.fromJson(reader, JsonElement.class);
            if (json != null && !json.isJsonNull()) {
                JsonObject jsonObject = json.getAsJsonObject();
                JsonArray pools = jsonObject.getAsJsonArray("pools");
                JsonObject poolsObject = pools.get(0).getAsJsonObject();
                JsonArray entries = poolsObject.getAsJsonArray("entries");
                for (JsonElement entry : entries) {
                    JsonArray conditions = entry.getAsJsonObject().getAsJsonArray("conditions");
                    for (JsonElement conditionElement : conditions) {
                        JsonObject condition = conditionElement.getAsJsonObject();
                        if (condition.get("condition").getAsString().equals("aquaculture:biome_tag_check")) {
                            FISH_BIOME_MAP.put(ForgeRegistries.ENTITIES.getValue(new ResourceLocation(entry.getAsJsonObject().get("name").toString().replace("\"", ""))), getSpawnableBiomes(condition.get("predicate")));
                        } else if (condition.get("condition").getAsString().equals("minecraft:alternative")) {
                            for (JsonElement term : condition.getAsJsonObject().getAsJsonArray("terms")) {
                                FISH_BIOME_MAP.put(ForgeRegistries.ENTITIES.getValue(new ResourceLocation(entry.getAsJsonObject().get("name").toString().replace("\"", ""))), getSpawnableBiomes(term.getAsJsonObject().get("predicate")));
                            }
                        }
                    }
                }
                FISH_BIOME_MAP.keySet().retainAll(FishRegistry.fishEntities); //Remove loot entries that does not have an entity
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static List<Biome> getSpawnableBiomes(JsonElement predicate) {
        List<Biome> biomes = Lists.newArrayList();
        List<BiomeDictionary.Type> includeList = Lists.newArrayList();
        List<BiomeDictionary.Type> excludeList = Lists.newArrayList();
        boolean and = false;

        if (predicate.getAsJsonObject().has("and")) {
            and = predicate.getAsJsonObject().get("and").getAsBoolean();
        }

        if (predicate.getAsJsonObject().has("include")) {
            JsonArray include = predicate.getAsJsonObject().get("include").getAsJsonArray();
            for (int entry = 0; entry < include.size(); entry++) {
                includeList.add(BiomeDictionaryHelper.getType(include.get(entry).getAsString()));
            }
        }
        if (predicate.getAsJsonObject().has("exclude")) {
            JsonArray exclude = predicate.getAsJsonObject().get("exclude").getAsJsonArray();
            for (int entry = 0; entry < exclude.size(); entry++) {
                excludeList.add(BiomeDictionaryHelper.getType(exclude.get(entry).getAsString()));
            }
        }
        biomes.addAll(BiomeTagPredicate.getValidBiomes(includeList, excludeList, and));
        return biomes;
    }

    public static void addFishSpawns() {
        for (EntityType fish : FISH_BIOME_MAP.keySet()) {
            for (Biome biome : FISH_BIOME_MAP.get(fish)) {
                biome.addSpawn(EntityClassification.WATER_CREATURE, new Biome.SpawnListEntry(fish, 60, 1, 1)); //TODO Weight and group szie
            }
        }
    }
}
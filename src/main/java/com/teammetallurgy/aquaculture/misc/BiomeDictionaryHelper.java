package com.teammetallurgy.aquaculture.misc;

import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.util.RegistryKey;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.MobSpawnInfo;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public class BiomeDictionaryHelper {
    public static final BiomeDictionary.Type TWILIGHT = BiomeDictionary.Type.getType("TWILIGHT"); //Add Twilight tag, for Twilight Forest support

    /**
     * Retrieves a #BiomeDictionary.Type
     * Based on {@link BiomeDictionary.Type#getType(String, BiomeDictionary.Type...)}, but doesn't create a new {@link BiomeDictionary.Type} if the input is not already a {@link BiomeDictionary.Type}
     *
     * @param name The name of this #BiomeDictionary.Type
     * @return An instance of this #BiomeDictionary.Type
     */
    public static BiomeDictionary.Type getType(String name) {
        Map<String, BiomeDictionary.Type> byName = BiomeDictionary.Type.getAll().stream().collect(Collectors.toMap(BiomeDictionary.Type::getName, Function.identity()));
        name = name.toUpperCase();
        return byName.get(name);
    }

    /**
     * Converts a List <? extends String> to a {@link BiomeDictionary.Type} array
     *
     * @param strings string array containing valid #BiomeDictionary.Types
     * @return {@link BiomeDictionary.Type} based on the string input
     */
    public static BiomeDictionary.Type[] toBiomeTypeArray(List<? extends String> strings) {
        BiomeDictionary.Type[] types = new BiomeDictionary.Type[strings.size()];
        for (int i = 0; i < strings.size(); i++) {
            String string = strings.get(i);
            types[i] = getType(string);
        }
        return types;
    }

    /**
     * Adds spawns for an entity, based on BiomeDictionary Type
     */
    public static void addSpawn(EntityType<?> entityType, int min, int max, int weight, List<? extends String> include, List<? extends String> exclude, BiomeLoadingEvent event) {
        if (weight > 0) {
            if (event.getName() != null) {
                Biome biome = ForgeRegistries.BIOMES.getValue(event.getName());
                if (biome != null) {
                    RegistryKey<Biome> biomeKey = RegistryKey.getOrCreateKey(ForgeRegistries.Keys.BIOMES, event.getName());
                    List<BiomeDictionary.Type> includeList = Arrays.asList(BiomeDictionaryHelper.toBiomeTypeArray(include));
                    List<BiomeDictionary.Type> excludeList = Arrays.asList(BiomeDictionaryHelper.toBiomeTypeArray(exclude));
                    if (!includeList.isEmpty()) {
                        Set<BiomeDictionary.Type> biomeTypes = BiomeDictionary.getTypes(biomeKey);
                        if (biomeTypes.stream().noneMatch(excludeList::contains) && biomeTypes.stream().anyMatch(includeList::contains)) {
                            event.getSpawns().getSpawner(EntityClassification.CREATURE).add(new MobSpawnInfo.Spawners(entityType, weight, min, max));
                        }
                    } else {
                        throw new IllegalArgumentException("Do not leave the BiomeDictionary type inclusion list empty. If you wish to disable spawning of an entity, set the weight to 0 instead.");
                    }
                }
            }
        }
    }
}
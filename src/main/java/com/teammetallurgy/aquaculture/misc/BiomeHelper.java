package com.teammetallurgy.aquaculture.misc;

import com.teammetallurgy.aquaculture.loot.BiomePropertiesPredicate;
import net.minecraft.entity.EntityType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.MobSpawnInfo;
import net.minecraftforge.event.world.BiomeLoadingEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class BiomeHelper {

    /**
     * Converts a List <? extends String> to a {@link Biome.Category} list
     *
     * @param stringList list of strings containing valid {@link Biome.Category}s
     * @return {@link Biome.Category} based on the string input
     */
    public static List<Biome.Category> toBiomeCategoryList(List<? extends String> stringList) {
        List<Biome.Category> newList = new ArrayList<>();
        for (String string : stringList) {
            Biome.Category category = Biome.Category.byName(string.toLowerCase(Locale.ROOT));
            if (category != null) {
                newList.add(category);
            }
        }
        return newList;
    }

    /**
     * Converts a List <? extends String> to a {@link BiomePropertiesPredicate.TemperatureType} list
     *
     * @param stringList list of strings containing valid {@link BiomePropertiesPredicate.TemperatureType}s
     * @return {@link BiomePropertiesPredicate.TemperatureType} based on the string input
     */
    public static List<BiomePropertiesPredicate.TemperatureType> toBiomeTemperatureTypeList(List<? extends String> stringList) {
        List<BiomePropertiesPredicate.TemperatureType> newList = new ArrayList<>();
        for (String string : stringList) {
            BiomePropertiesPredicate.TemperatureType temperatureType = BiomePropertiesPredicate.TemperatureType.getTemperatureType(string.toLowerCase(Locale.ROOT));
            if (temperatureType != null) {
                newList.add(temperatureType);
            }
        }
        return newList;
    }

    /**
     * Converts a List <? extends String> to a {@link Biome.RainType} list
     *
     * @param stringList list of strings containing valid {@link Biome.RainType}s
     * @return {@link Biome.RainType} based on the string input
     */
    public static List<Biome.RainType> toBiomeRainTypeList(List<? extends String> stringList) {
        List<Biome.RainType> newList = new ArrayList<>();
        for (String string : stringList) {
            Biome.RainType rainType = Biome.RainType.getRainType(string.toLowerCase(Locale.ROOT));
            if (rainType != null) {
                newList.add(rainType);
            }
        }
        return newList;
    }

    /**
     * Adds spawns for an entity, based on Biome Categories
     */
    public static void addSpawn(EntityType<?> entityType, int min, int max, int weight, List<? extends String> include, List<? extends String> exclude, List<? extends String> temperatures, List<? extends String> rain, BiomeLoadingEvent event) {
        if (weight > 0) {
            List<Biome.Category> includeList = toBiomeCategoryList(include);
            List<Biome.Category> excludeList = toBiomeCategoryList(exclude);
            List<BiomePropertiesPredicate.TemperatureType> temperatureTypes = toBiomeTemperatureTypeList(temperatures);
            List<Biome.RainType> rainTypes = toBiomeRainTypeList(rain);

            List<Biome> spawnableBiomes = BiomePropertiesPredicate.getValidBiomes(includeList, excludeList, temperatureTypes, rainTypes);

            for (Biome biome : spawnableBiomes) {
                if (event.getName().equals(biome.getRegistryName())) {
                    event.getSpawns().getSpawner(entityType.getClassification()).add(new MobSpawnInfo.Spawners(entityType, weight, min, max));
                }
            }
        }
    }
}
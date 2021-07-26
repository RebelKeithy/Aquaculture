package com.teammetallurgy.aquaculture.init;

import net.minecraft.world.food.FoodProperties;

public class AquaFoods {
    public static final FoodProperties ALGAE = new FoodProperties.Builder().nutrition(1).saturationMod(0.0F).fast().build();
    public static final FoodProperties FISH_RAW = new FoodProperties.Builder().nutrition(1).saturationMod(0.2F).build();
    public static final FoodProperties FISH_FILLET = new FoodProperties.Builder().nutrition(3).saturationMod(0.6F).build();
    public static final FoodProperties FROG_LEGS = new FoodProperties.Builder().nutrition(3).saturationMod(0.1F).meat().fast().build();
    public static final FoodProperties SUSHI = new FoodProperties.Builder().nutrition(4).saturationMod(0.8F).build();
}
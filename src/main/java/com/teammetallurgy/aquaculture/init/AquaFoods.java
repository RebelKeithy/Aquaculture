package com.teammetallurgy.aquaculture.init;

import net.minecraft.item.Food;

public class AquaFoods {
    public static final Food ALGAE = new Food.Builder().hunger(2).saturation(0.0F).build();
    public static final Food FISH_RAW = new Food.Builder().hunger(2).saturation(0.3F).build();
    public static final Food WHALE_STEAK = new Food.Builder().hunger(10).saturation(0.8F).build();
    public static final Food WHALE_BURGER = new Food.Builder().hunger(20).saturation(0.8F).build();
    public static final Food FISH_FILLET = new Food.Builder().hunger(5).saturation(0.6F).build();
    public static final Food FROG_LEGS = new Food.Builder().hunger(3).saturation(0.6F).meat().build();
    public static final Food SUSHI = new Food.Builder().hunger(4).saturation(0.6F).build();
}
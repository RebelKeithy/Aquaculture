package com.teammetallurgy.aquaculture.init;

import net.minecraft.item.Food;

public class AquaFoods {
    public static final Food ALGAE = new Food.Builder().hunger(1).saturation(0.0F).fastToEat().build();
    public static final Food FISH_RAW = new Food.Builder().hunger(1).saturation(0.2F).build();
    public static final Food FISH_FILLET = new Food.Builder().hunger(3).saturation(0.6F).build();
    public static final Food FROG_LEGS = new Food.Builder().hunger(3).saturation(0.1F).meat().fastToEat().build();
    public static final Food SUSHI = new Food.Builder().hunger(4).saturation(0.8F).build();
}
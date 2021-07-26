package com.teammetallurgy.aquaculture.item;

import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;

public class FishItem extends SimpleItem {
    public static final FoodProperties RAW_FISH = (new FoodProperties.Builder()).nutrition(1).saturationMod(0.1F).build();

    public FishItem() {
        super(new Item.Properties().food(RAW_FISH));
    }
}
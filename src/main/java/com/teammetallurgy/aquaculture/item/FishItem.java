package com.teammetallurgy.aquaculture.item;

import net.minecraft.item.Food;
import net.minecraft.item.Item;

public class FishItem extends SimpleItem {
    public static final Food RAW_FISH = (new Food.Builder()).hunger(1).saturation(0.1F).build();

    public FishItem() {
        super(new Item.Properties().food(RAW_FISH));
    }
}
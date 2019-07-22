package com.teammetallurgy.aquaculture.item;

import com.teammetallurgy.aquaculture.Aquaculture;
import net.minecraft.item.HoeItem;
import net.minecraft.item.IItemTier;
import net.minecraft.item.Item;

public class NeptuniumHoe extends HoeItem {

    public NeptuniumHoe(IItemTier tier, float speed) {
        super(tier, speed, new Item.Properties().group(Aquaculture.TAB));
    }
}
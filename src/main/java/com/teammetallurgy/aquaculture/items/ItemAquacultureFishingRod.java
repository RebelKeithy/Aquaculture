package com.teammetallurgy.aquaculture.items;

import net.minecraft.item.ItemFishingRod;
import net.minecraft.item.ItemTier;

public class ItemAquacultureFishingRod extends ItemFishingRod {
    public int enchantability;

    public ItemAquacultureFishingRod(ItemTier material, Properties properties) {
        super(properties);
        this.enchantability = material == ItemTier.WOOD ? 10 : material.getEnchantability();
    }

    @Override
    public int getItemEnchantability() {
        return enchantability;
    }
}
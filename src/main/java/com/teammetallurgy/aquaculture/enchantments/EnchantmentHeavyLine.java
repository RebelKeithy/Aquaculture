package com.teammetallurgy.aquaculture.enchantments;

import com.teammetallurgy.aquaculture.Aquaculture;

import net.minecraft.util.ResourceLocation;

public class EnchantmentHeavyLine extends EnchantmentFishingPole {

    public EnchantmentHeavyLine(int id, int weight) {
        super(id, new ResourceLocation(Aquaculture.MOD_ID + ":heavy_line"), weight);
        setName("aquacultureHeavyLine");
    }

    @Override
    public int getMaxLevel() {
        return 5;
    }

    @Override
    public int getMinEnchantability(int par1) {
        return 5 + 20 * (par1 - 1);
    }

    @Override
    public int getMaxEnchantability(int par1) {
        return super.getMinEnchantability(par1) + 50;
    }
}

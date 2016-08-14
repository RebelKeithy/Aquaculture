package com.teammetallurgy.aquaculture.enchantments;

import net.minecraft.enchantment.Enchantment;

public class EnchantmentHeavyLine extends EnchantmentFishingPole {

    public EnchantmentHeavyLine(Enchantment.Rarity rarity) {
        super(rarity);
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

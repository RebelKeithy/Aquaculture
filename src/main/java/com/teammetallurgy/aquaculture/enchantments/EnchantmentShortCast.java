package com.teammetallurgy.aquaculture.enchantments;

import net.minecraft.enchantment.Enchantment;

/**
 * @author Freyja
 */
public class EnchantmentShortCast extends EnchantmentFishingPole {

    public EnchantmentShortCast(Enchantment.Rarity rarity) {
        super(rarity);
        setName("aquacultureShortCast");
    }

    @Override
    public int getMaxLevel() {
        return 5;
    }

    @Override
    public boolean canApplyTogether(Enchantment enchantment) {
        return !((enchantment instanceof EnchantmentShortCast) || (enchantment instanceof EnchantmentLongCast) || (enchantment instanceof EnchantmentFastcast));
    }

    @Override
    public int getMinEnchantability(int par1) {
        return 1 + 10 * (par1 - 1);
    }

    @Override
    public int getMaxEnchantability(int par1) {
        return super.getMinEnchantability(par1) + 50;
    }

}

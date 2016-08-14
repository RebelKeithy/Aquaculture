package com.teammetallurgy.aquaculture.enchantments;

import net.minecraft.enchantment.Enchantment;

/**
 * @author Freyja
 */
public class EnchantmentMagnetic extends EnchantmentFishingPole {

    public EnchantmentMagnetic(Enchantment.Rarity rarity) {
        super(rarity);
        setName("aquacultureMagnetic");
    }

    @Override
    public int getMaxLevel() {
        return 5;
    }

    @Override
    public boolean canApplyTogether(Enchantment enchantment) {
        return !((enchantment instanceof EnchantmentMagnetic) || (enchantment instanceof EnchantmentAppealing));
    }

    @Override
    public int getMinEnchantability(int par1) {
        return 12 + (par1 - 1) * 20;
    }

    @Override
    public int getMaxEnchantability(int par1) {
        return this.getMinEnchantability(par1) + 25;
    }

    /*@Override
    public String getTranslatedName(int par1) {
    	return LocalizationHelper.localize("enchantment.magnetic") + RomanNumeral.convertToRoman(par1);
    }*/
}

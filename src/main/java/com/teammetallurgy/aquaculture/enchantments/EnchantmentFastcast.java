package com.teammetallurgy.aquaculture.enchantments;

import net.minecraft.enchantment.Enchantment;

/**
 * @author Freyja
 */
public class EnchantmentFastcast extends EnchantmentFishingPole {

    public EnchantmentFastcast(Enchantment.Rarity rarity) {
        super(rarity);
        setName("aquacultureFastCast");
    }

    @Override
    public int getMaxLevel() {
        return 5;
    }

    @Override
    public boolean canApplyTogether(Enchantment enchantment) {
        return !((enchantment instanceof EnchantmentShortCast) || (enchantment instanceof EnchantmentLongCast) || (enchantment instanceof EnchantmentFastcast));
    }
}

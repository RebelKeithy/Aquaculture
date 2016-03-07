package com.teammetallurgy.aquaculture.enchantments;

import com.teammetallurgy.aquaculture.Aquaculture;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.util.ResourceLocation;

/**
 * @author Freyja
 */
public class EnchantmentLongCast extends EnchantmentFishingPole {

    public EnchantmentLongCast(int id, int weight) {
        super(id, new ResourceLocation(Aquaculture.MOD_ID + ":long_cast"), weight);
        setName("aquacultureLongCast");
    }

    @Override
    public int getMaxLevel() {
        return 5;
    }

    @Override
    public boolean canApplyTogether(Enchantment enchantment) {
        return !((enchantment instanceof EnchantmentShortCast) || (enchantment instanceof EnchantmentLongCast));
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

package com.teammetallurgy.aquaculture.enchantments;

import com.teammetallurgy.aquaculture.Aquaculture;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.util.ResourceLocation;

/**
 * @author Freyja
 */
public class EnchantmentDoubleHook extends EnchantmentFishingPole {

    public EnchantmentDoubleHook(int id, int weight) {
        super(id, new ResourceLocation(Aquaculture.MOD_ID + ":double_hook"), weight);
        setName("aquacultureDoubleHook");
    }

    @Override
    public int getMaxLevel() {
        return 5;
    }

    @Override
    public boolean canApplyTogether(Enchantment enchantment) {
        return !((enchantment instanceof EnchantmentDoubleHook) || (enchantment instanceof EnchantmentBardedHook));
    }

    @Override
    public int getMinEnchantability(int par1) {
        return 15;
    }

    @Override
    public int getMaxEnchantability(int par1) {
        return super.getMinEnchantability(par1) + 50;
    }
}

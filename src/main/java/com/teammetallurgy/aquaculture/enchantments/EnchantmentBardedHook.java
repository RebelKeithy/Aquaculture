package com.teammetallurgy.aquaculture.enchantments;

import com.teammetallurgy.aquaculture.Aquaculture;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentLootBonus;
import net.minecraft.util.ResourceLocation;

/**
 * @author Freyja
 */
public class EnchantmentBardedHook extends EnchantmentFishingPole {

    public EnchantmentBardedHook(int id, int weight) {
        super(id, new ResourceLocation(Aquaculture.MOD_ID + ":barbed_hook"), weight);
        setName("aquacultureBarbedHook");
    }

    @Override
    public int getMaxLevel() {
        return 5;
    }

    @Override
    public boolean canApplyTogether(Enchantment enchantment) {
        return !((enchantment instanceof EnchantmentDoubleHook) || (enchantment instanceof EnchantmentBardedHook) || (enchantment instanceof EnchantmentLootBonus));
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

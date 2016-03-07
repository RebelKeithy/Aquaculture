package com.teammetallurgy.aquaculture.enchantments;

import com.teammetallurgy.aquaculture.Aquaculture;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.util.ResourceLocation;

/**
 * @author Freyja
 */
public class EnchantmentAppealing extends EnchantmentFishingPole {

    public EnchantmentAppealing(int id, int weight) {
        super(id, new ResourceLocation(Aquaculture.MOD_ID + ":appealing"), weight);
        setName("aquacultureAppealing");
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
}

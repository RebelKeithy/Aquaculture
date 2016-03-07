package com.teammetallurgy.aquaculture.enchantments;

import com.teammetallurgy.aquaculture.Aquaculture;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.util.ResourceLocation;

/**
 * @author Freyja
 */
public class EnchantmentFastcast extends EnchantmentFishingPole {

    public EnchantmentFastcast(int id, int weight) {
        super(id, new ResourceLocation(Aquaculture.MOD_ID + ":fast_cast"), weight);
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

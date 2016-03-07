package com.teammetallurgy.aquaculture.enchantments;

import com.teammetallurgy.aquaculture.Aquaculture;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.util.EnumHelper;

public class EnchantmentSlotReserve extends Enchantment {

    private static EnumEnchantmentType enumSlotReserve = EnumHelper.addEnchantmentType("slotReserve");

    public EnchantmentSlotReserve(int par1) {
        super(par1, new ResourceLocation(Aquaculture.MOD_ID + ":slot_reserve"), 0, enumSlotReserve);
    }
}

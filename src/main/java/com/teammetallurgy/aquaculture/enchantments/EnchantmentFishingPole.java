package com.teammetallurgy.aquaculture.enchantments;

import com.teammetallurgy.aquaculture.items.ItemAdminFishingRod;
import com.teammetallurgy.aquaculture.items.ItemAquacultureFishingRod;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;

/**
 * @author Freyja
 */
public abstract class EnchantmentFishingPole extends Enchantment {

    public EnchantmentFishingPole(Rarity rarity) {
        super(rarity, AquacultureEnchants.enumFishingPole, new EntityEquipmentSlot[] { EntityEquipmentSlot.MAINHAND });
    }

    @Override
    public boolean canApply(ItemStack itemStack) {
        return ((itemStack.getItem() instanceof ItemAquacultureFishingRod) || (itemStack.getItem() instanceof ItemAdminFishingRod));
    }

    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack) {
        return canApply(stack);
    }
}

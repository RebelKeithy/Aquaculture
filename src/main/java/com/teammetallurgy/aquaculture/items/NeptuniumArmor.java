package com.teammetallurgy.aquaculture.items;

import net.minecraft.entity.Entity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;

public class NeptuniumArmor extends ArmorItem {
    private String texture;

    public NeptuniumArmor(IArmorMaterial armorMaterial, EquipmentSlotType equipmentSlot, Item.Properties builder) {
        super(armorMaterial, equipmentSlot, builder);
    }

    public Item setArmorTexture(String string) {
        texture = string;
        return this;
    }

    @Override
    public String getArmorTexture(@Nonnull ItemStack stack, Entity entity, EquipmentSlotType slot, String layer) {
        return "aquaculture:textures/armor/" + texture + ".png";
    }
}
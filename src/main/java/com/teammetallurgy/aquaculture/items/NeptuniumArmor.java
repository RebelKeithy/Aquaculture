package com.teammetallurgy.aquaculture.items;

import net.minecraft.entity.Entity;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;

public class NeptuniumArmor extends ItemArmor {
    private String texture;

    public NeptuniumArmor(IArmorMaterial armorMaterial, EntityEquipmentSlot equipmentSlot, Item.Properties builder) {
        super(armorMaterial, equipmentSlot, builder);
    }

    public Item setArmorTexture(String string) {
        texture = string;
        return this;
    }

    @Override
    public String getArmorTexture(@Nonnull ItemStack stack, Entity entity, EntityEquipmentSlot slot, String layer) {
        return "aquaculture:textures/armor/" + texture + ".png";
    }
}
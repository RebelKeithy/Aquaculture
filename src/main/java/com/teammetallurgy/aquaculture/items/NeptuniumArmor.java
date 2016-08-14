package com.teammetallurgy.aquaculture.items;

import net.minecraft.entity.Entity;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;

public class NeptuniumArmor extends ItemArmor {
    private String texture;

    public NeptuniumArmor(ArmorMaterial armorMaterial, int renderIndex, EntityEquipmentSlot equipmentSlot) {
        super(armorMaterial, renderIndex, equipmentSlot);
    }

    public Item setArmorTexture(String string) {
        texture = string;
        return this;
    }

    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, EntityEquipmentSlot slot, String layer) {
        return "aquaculture:textures/armor/" + texture + ".png";
    }

    @Override
    public Item setUnlocalizedName(String par1Str) {
        super.setUnlocalizedName(par1Str);
        return this;
    }

}

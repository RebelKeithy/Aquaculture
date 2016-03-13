package com.teammetallurgy.aquaculture.items;

import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;

public class NeptuniumArmor extends ItemArmor {
    private String texture;

    public NeptuniumArmor(ArmorMaterial par2EnumArmorMaterial, int par3, int par4) {
        super(par2EnumArmorMaterial, par3, par4);
    }

    public Item setArmorTexture(String string) {
        texture = string;
        return this;
    }

    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, int slot, String layer) {
        return "aquaculture:textures/armor/" + texture + ".png";
    }

    @Override
    public Item setUnlocalizedName(String par1Str) {
        super.setUnlocalizedName(par1Str);
        return this;
    }

}

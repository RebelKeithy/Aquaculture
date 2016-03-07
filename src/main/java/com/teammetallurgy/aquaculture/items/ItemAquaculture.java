package com.teammetallurgy.aquaculture.items;

import net.minecraft.item.Item;

public class ItemAquaculture extends Item {

    public ItemAquaculture() {
        super();
    }

    /**
     * Sets the unlocalized name of this item to the string passed as the parameter, prefixed by "item."
     */
    @Override
    public Item setUnlocalizedName(String par1Str) {
        super.setUnlocalizedName(par1Str);
        this.setTextureName("aquaculture:" + par1Str.replaceAll("\\s", ""));
        return this;
    }

    @Override
    public Item setTextureName(String par1Str) {
        super.setTextureName(par1Str);
        return this;
    }
}

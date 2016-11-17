package com.teammetallurgy.aquaculture.handlers;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class AquacultureTab extends CreativeTabs {
    ItemStack tabItem;

    public AquacultureTab(String par2Str) {
        super(par2Str);
    }

    public void setItemStack(ItemStack itemStack) {
        tabItem = itemStack;
    }

    @Override
    public ItemStack getTabIconItem() {
        return tabItem;
    }
}

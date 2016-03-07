package com.teammetallurgy.aquaculture.handlers;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class AquacultureTab extends CreativeTabs {
    Item tabItem;

    public AquacultureTab(String par2Str) {
        super(par2Str);
    }

    public void setItem(Item item) {
        tabItem = item;
    }

    @Override
    public Item getTabIconItem() {
        return tabItem;
    }
}

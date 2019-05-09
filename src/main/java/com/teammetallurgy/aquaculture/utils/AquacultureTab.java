package com.teammetallurgy.aquaculture.utils;

import net.minecraft.init.Items;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;

public class AquacultureTab extends ItemGroup {

    public AquacultureTab(String label) {
        super(label);
    }

    @Override
    @Nonnull
    public ItemStack createIcon() {
        return new ItemStack(Items.ARROW); //TODO change back to AquaItems.IRON_FISHING_ROD
    }
}
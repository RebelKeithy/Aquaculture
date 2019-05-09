package com.teammetallurgy.aquaculture.utils;

import com.teammetallurgy.aquaculture.init.AquaItems;
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
        return new ItemStack(AquaItems.IRON_FISHING_ROD);
    }
}
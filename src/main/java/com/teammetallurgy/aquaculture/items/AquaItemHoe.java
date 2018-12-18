package com.teammetallurgy.aquaculture.items;

import net.minecraft.item.Item;
import net.minecraft.item.ItemHoe;

import javax.annotation.Nonnull;

public class AquaItemHoe extends ItemHoe {

    public AquaItemHoe(ToolMaterial toolMaterial) {
        super(toolMaterial);
    }

    @Override
    @Nonnull
    public Item setTranslationKey(@Nonnull String name) {
        super.setTranslationKey(name);
        return this;
    }
}
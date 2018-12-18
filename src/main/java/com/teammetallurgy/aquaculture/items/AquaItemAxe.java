package com.teammetallurgy.aquaculture.items;

import net.minecraft.item.Item;
import net.minecraft.item.ItemAxe;

import javax.annotation.Nonnull;

public class AquaItemAxe extends ItemAxe {

    public AquaItemAxe(ToolMaterial toolMaterial) {
        super(toolMaterial, 8.0F, -3.0F);
    }

    @Override
    @Nonnull
    public Item setTranslationKey(@Nonnull String name) {
        super.setTranslationKey(name);
        return this;
    }
}
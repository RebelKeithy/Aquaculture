package com.teammetallurgy.aquaculture.items;

import net.minecraft.item.Item;
import net.minecraft.item.ItemSpade;

import javax.annotation.Nonnull;

public class AquaItemSpade extends ItemSpade {

    public AquaItemSpade(ToolMaterial toolMaterial) {
        super(toolMaterial);
    }

    @Override
    @Nonnull
    public Item setTranslationKey(@Nonnull String name) {
        super.setTranslationKey(name);
        return this;
    }
}
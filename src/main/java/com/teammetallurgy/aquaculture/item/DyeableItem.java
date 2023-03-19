package com.teammetallurgy.aquaculture.item;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.DyeableLeatherItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import javax.annotation.Nonnull;

public class DyeableItem extends Item implements DyeableLeatherItem {
    private final int defaultColor;

    public DyeableItem(int defaultColor) {
        super(new Item.Properties());
        this.defaultColor = defaultColor;
    }

    @Override
    public int getColor(@Nonnull ItemStack stack) {
        CompoundTag nbt = stack.getTagElement("display");
        return nbt != null && nbt.contains("color", 99) ? nbt.getInt("color") : this.defaultColor;
    }
}
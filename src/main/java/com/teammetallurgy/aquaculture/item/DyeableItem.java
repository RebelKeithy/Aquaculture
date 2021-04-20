package com.teammetallurgy.aquaculture.item;

import com.teammetallurgy.aquaculture.Aquaculture;
import net.minecraft.item.IDyeableArmorItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;

import javax.annotation.Nonnull;

public class DyeableItem extends Item implements IDyeableArmorItem {
    private final int defaultColor;

    public DyeableItem(int defaultColor) {
        super(new Item.Properties().group(Aquaculture.GROUP));
        this.defaultColor = defaultColor;
    }

    @Override
    public int getColor(@Nonnull ItemStack stack) {
        CompoundNBT nbt = stack.getChildTag("display");
        return nbt != null && nbt.contains("color", 99) ? nbt.getInt("color") : this.defaultColor;
    }
}
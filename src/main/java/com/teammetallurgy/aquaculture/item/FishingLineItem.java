package com.teammetallurgy.aquaculture.item;

import com.teammetallurgy.aquaculture.Aquaculture;
import net.minecraft.item.IDyeableArmorItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;

import javax.annotation.Nonnull;

public class FishingLineItem extends Item implements IDyeableArmorItem {

    public FishingLineItem() {
        super(new Item.Properties().group(Aquaculture.GROUP));
    }

    @Override
    public int getColor(@Nonnull ItemStack stack) {
        CompoundNBT nbt = stack.getChildTag("display");
        return nbt != null && nbt.contains("color", 99) ? nbt.getInt("color") : 0;
    }
}
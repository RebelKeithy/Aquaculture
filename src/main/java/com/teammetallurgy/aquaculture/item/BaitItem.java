package com.teammetallurgy.aquaculture.item;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;

public class BaitItem extends Item {
    private int lureSpeedModifier;
    private int durability;

    public BaitItem(int durability, int lureSpeedModifier, ItemGroup group) {
        super(new Item.Properties().group(group).setNoRepair());
        this.lureSpeedModifier = lureSpeedModifier;
        this.durability = durability;
    }

    public int getLureSpeedModifier() {
        return this.lureSpeedModifier;
    }

    @Override
    public int getMaxDamage(ItemStack stack) {
        return this.durability;
    }

    @Override
    public int getRGBDurabilityForDisplay(@Nonnull ItemStack stack) {
        return 16761035;
    }
}
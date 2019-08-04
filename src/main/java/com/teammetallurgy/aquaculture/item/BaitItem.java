package com.teammetallurgy.aquaculture.item;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;

public class BaitItem extends Item {
    private int lureSpeedModifier;

    public BaitItem(int durability, int lureSpeedModifier, ItemGroup group) {
        super(new Item.Properties().maxStackSize(1).defaultMaxDamage(durability).group(group).setNoRepair());
        this.lureSpeedModifier = lureSpeedModifier;
    }

    public int getLureSpeedModifier() {
        return this.lureSpeedModifier;
    }

    @Override
    public int getRGBDurabilityForDisplay(@Nonnull ItemStack stack) {
        return 16761035;
    }
}
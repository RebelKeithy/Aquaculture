package com.teammetallurgy.aquaculture.item;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import javax.annotation.Nonnull;

public class BaitItem extends Item {
    private int lureSpeedModifier;
    private int durability;

    public BaitItem(int durability, int lureSpeedModifier, CreativeModeTab group) {
        super(new Item.Properties().tab(group).setNoRepair());
        this.lureSpeedModifier = lureSpeedModifier;
        this.durability = durability;
    }

    public int getLureSpeedModifier() {
        return this.lureSpeedModifier;
    }

    @Override
    public boolean canBeDepleted() {
        return this.durability > 0;
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
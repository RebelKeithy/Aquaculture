package com.teammetallurgy.aquaculture.inventory.container.slot;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.FishingRodItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;

public class FishingRodSlot extends Slot {

    public FishingRodSlot(IInventory inventory, int index, int x, int y) {
        super(inventory, index, x, y);
    }

    @Override
    public boolean isItemValid(@Nonnull ItemStack stack) {
        Item item = stack.getItem();
        return item instanceof FishingRodItem;
    }
}
package com.teammetallurgy.aquaculture.inventory.container.slot;

import com.teammetallurgy.aquaculture.api.AquacultureAPI;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;

public class BaitSlot extends Slot {

    public BaitSlot(IInventory inventory, int index, int x, int y) {
        super(inventory, index, x, y);
    }

    @Override
    public boolean isItemValid(@Nonnull ItemStack stack) {
        Item item = stack.getItem();
        return item.isIn(AquacultureAPI.Tags.BAIT);
    }
}
package com.teammetallurgy.aquaculture.inventory.container.slot;

import com.teammetallurgy.aquaculture.api.AquacultureAPI;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

import javax.annotation.Nonnull;

public class TackleBoxSlot extends SlotItemHandler {

    public TackleBoxSlot(IItemHandler handler, int index, int x, int y) {
        super(handler, index, x, y);
    }

    @Override
    public boolean isItemValid(@Nonnull ItemStack stack) { //TODO Use Forge tag for string, when added
        Item item = stack.getItem();
        return item == Items.STRING || item.isIn(AquacultureAPI.Tags.HOOKS) || item.isIn(AquacultureAPI.Tags.BAIT);
    }
}
package com.teammetallurgy.aquaculture.inventory.container.slot;

import com.teammetallurgy.aquaculture.item.AquaFishingRodItem;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.SlotItemHandler;

import javax.annotation.Nonnull;

public class SlotFishingRod extends SlotItemHandler {
    public ItemStackHandler rodHandler;

    public SlotFishingRod(IItemHandler itemHandler, int index, int xPosition, int yPosition) {
        super(itemHandler, index, xPosition, yPosition);
        this.onSlotChanged(); //Initialise
    }

    @Override
    public boolean isItemValid(@Nonnull ItemStack stack) {
        return stack.getItem() instanceof AquaFishingRodItem;
    }

    @Override
    public void onSlotChanged() { //Save changes to the rod
        ItemStack stack = getStack();
        this.rodHandler = (ItemStackHandler) stack.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).orElse(AquaFishingRodItem.FishingRodEquipmentHandler.EMPTY.getItems());
        if (!stack.isEmpty() && stack.hasTag() && stack.getTag() != null && stack.getTag().contains("Inventory")) {
            this.rodHandler.deserializeNBT(stack.getTag().getCompound("Inventory")); //Reload
        }
    }
}
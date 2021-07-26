package com.teammetallurgy.aquaculture.inventory.container.slot;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.SlotItemHandler;

import javax.annotation.Nonnull;

public class SlotHidable extends SlotItemHandler {
    private final SlotFishingRod fishingRod;

    public SlotHidable(SlotFishingRod fishingRod, int index, int xPosition, int yPosition) {
        super(fishingRod.rodHandler, index, xPosition, yPosition);
        this.fishingRod = fishingRod;
    }

    @Override
    public IItemHandler getItemHandler() {
        return this.fishingRod.rodHandler;
    }

    @Override
    public boolean mayPlace(@Nonnull ItemStack stack) {
        return this.fishingRod.hasItem();
    }

    @Override
    public boolean mayPickup(Player player) {
        return this.fishingRod.hasItem() && super.mayPickup(player);
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public boolean isActive() {
        return this.fishingRod.hasItem();
    }

    @Override
    public void setChanged() { //Save changes to the rod
        ItemStack stack = this.fishingRod.getItem();
        if (!stack.isEmpty()) {
            CompoundTag tag = stack.getOrCreateTag();
            tag.put("Inventory", ((ItemStackHandler) getItemHandler()).serializeNBT());
            stack.setTag(tag);
        }
    }
}
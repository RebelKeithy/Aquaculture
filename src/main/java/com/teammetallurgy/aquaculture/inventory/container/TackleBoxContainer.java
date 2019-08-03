package com.teammetallurgy.aquaculture.inventory.container;

import com.teammetallurgy.aquaculture.api.AquacultureAPI;
import com.teammetallurgy.aquaculture.init.AquaBlocks;
import com.teammetallurgy.aquaculture.init.AquaGuis;
import com.teammetallurgy.aquaculture.item.AquaFishingRodItem;
import com.teammetallurgy.aquaculture.item.HookItem;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IWorldPosCallable;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.SlotItemHandler;

import javax.annotation.Nonnull;
import java.util.Objects;

public class TackleBoxContainer extends Container {
    private TileEntity tileEntity;
    public int rows = 4;
    public int collumns = 4;

    public TackleBoxContainer(int windowID, BlockPos pos, PlayerInventory playerInventory) {
        super(AquaGuis.TACKLE_BOX, windowID);
        this.tileEntity = playerInventory.player.world.getTileEntity(pos);

        if (this.tileEntity != null) {
            this.tileEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).ifPresent(handler -> {
                //Fishing Pole
                this.addSlot(new SlotItemHandler(handler, 0, 35, 16) {
                    @Override
                    public boolean isItemValid(@Nonnull ItemStack stack) {
                        Item item = stack.getItem();
                        return item instanceof AquaFishingRodItem;
                    }

                    @Override
                    public void onSlotChanged() {
                        super.onSlotChanged();
                    }
                });

                //Fishing Rod slots
                ItemStack fishingRod = handler.getStackInSlot(0);
                if (!fishingRod.isEmpty()) {
                    fishingRod.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).ifPresent(rodHandler -> {
                        //Hook
                        this.addSlot(new SlotItemHandler(rodHandler, 0, 16, 35) {
                            @Override
                            public boolean isItemValid(@Nonnull ItemStack stack) {
                                return stack.getItem() instanceof HookItem;
                            }

                            @Override
                            public boolean isEnabled() {
                                return !handler.getStackInSlot(0).isEmpty();
                            }
                        });

                        //Bait
                        this.addSlot(new SlotItemHandler(rodHandler, 1, 48, 35) {
                            @Override
                            public boolean isItemValid(@Nonnull ItemStack stack) {
                                return !(stack.getItem() instanceof HookItem); //TODO Make it bait when added
                            }

                            @Override
                            public boolean isEnabled() {
                                return !handler.getStackInSlot(0).isEmpty();
                            }
                        });
                    });
                }

                //Tackle Box
                for (int column = 0; column < collumns; ++column) {
                    for (int row = 0; row < rows; ++row) {
                        this.addSlot(new SlotItemHandler(handler, 1 + row + column * collumns, 89 + row * 18, 8 + column * 18) {
                            @Override
                            public boolean isItemValid(@Nonnull ItemStack stack) {
                                Item item = stack.getItem();
                                return item.isIn(AquacultureAPI.Tags.TACKLE_BOX) || item instanceof HookItem /*|| item.isIn(AquacultureAPI.Tags.BAIT)*/;
                            }
                        });
                    }
                }
            });
        }

        //Player Inventory
        for (int column = 0; column < 3; ++column) {
            for (int row = 0; row < 9; ++row) {
                this.addSlot(new Slot(playerInventory, row + column * 9 + 9, 8 + row * 18, 84 + column * 18));
            }
        }

        //Hotbar
        for (int row = 0; row < 9; ++row) {
            this.addSlot(new Slot(playerInventory, row, 8 + row * 18, 142));
        }
    }

    @Override
    public boolean canInteractWith(@Nonnull PlayerEntity player) {
        return isWithinUsableDistance(IWorldPosCallable.of(Objects.requireNonNull(tileEntity.getWorld()), tileEntity.getPos()), player, AquaBlocks.TACKLE_BOX);
    }

    @Override
    @Nonnull
    public ItemStack transferStackInSlot(PlayerEntity player, int index) {
        ItemStack transferStack = ItemStack.EMPTY;
        Slot slot = this.inventorySlots.get(index);
        if (slot != null && slot.getHasStack()) {
            ItemStack slotStack = slot.getStack();
            transferStack = slotStack.copy();
            if (index < this.rows * this.collumns) {
                if (!this.mergeItemStack(slotStack, this.rows * this.collumns, this.inventorySlots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.mergeItemStack(slotStack, 0, this.rows * this.collumns, false)) {
                return ItemStack.EMPTY;
            }
            if (slotStack.isEmpty()) {
                slot.putStack(ItemStack.EMPTY);
            } else {
                slot.onSlotChanged();
            }
        }
        return transferStack;
    }
}
package com.teammetallurgy.aquaculture.block.tileentity;

import com.teammetallurgy.aquaculture.init.AquaBlocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class FishTrapTileEntity extends IItemHandlerTEBase implements INamedContainerProvider {

    public FishTrapTileEntity() {
        super(AquaBlocks.AquaTileEntities.FISH_TRAP);
    }

    @Override
    @Nonnull
    public ITextComponent getDisplayName() {
        return this.getBlockState().getBlock().getNameTextComponent();
    }

    @Override
    @Nullable
    public Container createMenu(int windowID, @Nonnull PlayerInventory playerInventory, @Nonnull PlayerEntity player) {
        return null; //TODO
    }

    @Override
    @Nonnull
    protected IItemHandler createItemHandler() {
        return new ItemStackHandler(9);
    }
}
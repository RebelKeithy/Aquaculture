package com.teammetallurgy.aquaculture.block.tileentity;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public abstract class IItemHandlerTEBase extends TileEntity {
    private LazyOptional<IItemHandler> handler = LazyOptional.of(this::createItemHandler);

    public IItemHandlerTEBase(TileEntityType<?> tileEntityType) {
        super(tileEntityType);
    }

    @Nonnull
    protected abstract IItemHandler createItemHandler();

    @Override
    public void read(CompoundNBT tag) {
        CompoundNBT invTag = tag.getCompound("inv");
        handler.ifPresent(stack -> ((INBTSerializable<CompoundNBT>) stack).deserializeNBT(invTag));
        super.read(tag);
    }

    @Override
    @Nonnull
    public CompoundNBT write(CompoundNBT tag) {
        handler.ifPresent(stack -> {
            CompoundNBT compound = ((INBTSerializable<CompoundNBT>) stack).serializeNBT();
            tag.put("inv", compound);
        });
        return super.write(tag);
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return handler.cast();
        }
        return super.getCapability(cap, side);
    }
}
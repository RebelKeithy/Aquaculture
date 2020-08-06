package com.teammetallurgy.aquaculture.block.tileentity;

import net.minecraft.block.BlockState;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraft.util.INameable;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public abstract class IItemHandlerTEBase extends TileEntity implements INameable {
    private ITextComponent customName;
    private final LazyOptional<IItemHandler> handler = LazyOptional.of(this::createItemHandler);

    public IItemHandlerTEBase(TileEntityType<?> tileEntityType) {
        super(tileEntityType);
    }

    @Nonnull
    protected abstract IItemHandler createItemHandler();

    @Override
    public void func_230337_a_(@Nonnull BlockState state, CompoundNBT tag) {
        CompoundNBT invTag = tag.getCompound("inv");
        this.handler.ifPresent(stack -> ((INBTSerializable<CompoundNBT>) stack).deserializeNBT(invTag));
        if (tag.contains("CustomName", 8)) {
            this.customName = ITextComponent.Serializer.func_240643_a_(tag.getString("CustomName"));
        }
        super.func_230337_a_(state, tag);
    }

    @Override
    @Nonnull
    public CompoundNBT write(CompoundNBT tag) {
        this.handler.ifPresent(stack -> {
            CompoundNBT compound = ((INBTSerializable<CompoundNBT>) stack).serializeNBT();
            tag.put("inv", compound);
        });
        if (this.customName != null) {
            tag.putString("CustomName", ITextComponent.Serializer.toJson(this.customName));
        }
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

    @Override
    @Nonnull
    public ITextComponent getName() {
        return this.customName != null ? this.customName : new TranslationTextComponent(this.getBlockState().getBlock().getTranslationKey());
    }

    @Override
    @Nonnull
    public ITextComponent getDisplayName() {
        return getName();
    }

    public void setCustomName(ITextComponent name) {
        this.customName = name;
    }
}
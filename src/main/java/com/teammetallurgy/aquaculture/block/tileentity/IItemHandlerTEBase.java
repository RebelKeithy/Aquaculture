package com.teammetallurgy.aquaculture.block.tileentity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.Nameable;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public abstract class IItemHandlerTEBase extends BlockEntity implements Nameable {
    private Component customName;
    private final LazyOptional<IItemHandler> handler = LazyOptional.of(this::createItemHandler);

    public IItemHandlerTEBase(BlockEntityType<?> tileEntityType, BlockPos pos, BlockState state) {
        super(tileEntityType, pos, state);
    }

    @Nonnull
    protected abstract IItemHandler createItemHandler();

    @Override
    public void load(CompoundTag tag) {
        CompoundTag invTag = tag.getCompound("inv");
        this.handler.ifPresent(stack -> ((INBTSerializable<CompoundTag>) stack).deserializeNBT(invTag));
        if (tag.contains("CustomName", 8)) {
            this.customName = Component.Serializer.fromJson(tag.getString("CustomName"));
        }
        super.load(tag);
    }

    @Override
    @Nonnull
    public CompoundTag save(@Nonnull CompoundTag tag) {
        this.handler.ifPresent(stack -> {
            CompoundTag compound = ((INBTSerializable<CompoundTag>) stack).serializeNBT();
            tag.put("inv", compound);
        });
        if (this.customName != null) {
            tag.putString("CustomName", Component.Serializer.toJson(this.customName));
        }
        return super.save(tag);
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
    public Component getName() {
        return this.customName != null ? this.customName : new TranslatableComponent(this.getBlockState().getBlock().getDescriptionId());
    }

    @Override
    @Nonnull
    public Component getDisplayName() {
        return getName();
    }

    public void setCustomName(Component name) {
        this.customName = name;
    }
}
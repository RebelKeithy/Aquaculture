package com.teammetallurgy.aquaculture.block.blockentity;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.Nameable;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.common.util.INBTSerializable;
import net.neoforged.neoforge.items.IItemHandler;

import javax.annotation.Nonnull;

public abstract class IItemHandlerBEBase extends BlockEntity implements Nameable {
    private Component customName;
    public final IItemHandler handler = createItemHandler();

    public IItemHandlerBEBase(BlockEntityType<?> tileEntityType, BlockPos pos, BlockState state) {
        super(tileEntityType, pos, state);
    }

    @Nonnull
    protected abstract IItemHandler createItemHandler();

    @Override
    public void load(@Nonnull CompoundTag tag) {
        CompoundTag invTag = tag.getCompound("inv");
        ((INBTSerializable<CompoundTag>) handler).deserializeNBT(invTag);
        if (tag.contains("CustomName", 8)) {
            this.customName = Component.Serializer.fromJson(tag.getString("CustomName"));
        }
        super.load(tag);
    }

    @Override
    public void saveAdditional(@Nonnull CompoundTag tag) {
        CompoundTag compound = ((INBTSerializable<CompoundTag>) handler).serializeNBT();
        if (compound != null) {
            tag.put("inv", compound);
        }
        if (this.customName != null) {
            tag.putString("CustomName", Component.Serializer.toJson(this.customName));
        }
        super.saveAdditional(tag);
    }

    @Override
    @Nonnull
    public Component getName() {
        return this.customName != null ? this.customName : Component.translatable(this.getBlockState().getBlock().getDescriptionId());
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
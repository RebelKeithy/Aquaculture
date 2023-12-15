package com.teammetallurgy.aquaculture.block.blockentity;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.Nameable;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nonnull;

public abstract class IItemHandlerBEBase extends BlockEntity implements Nameable { //TODO Reimplement Capabilities
    private Component customName;

    public IItemHandlerBEBase(BlockEntityType<?> tileEntityType, BlockPos pos, BlockState state) {
        super(tileEntityType, pos, state);
    }

    @Override
    public void load(@Nonnull CompoundTag tag) {
        if (tag.contains("CustomName", 8)) {
            this.customName = Component.Serializer.fromJson(tag.getString("CustomName"));
        }
        super.load(tag);
    }

    @Override
    public void saveAdditional(@Nonnull CompoundTag tag) {
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
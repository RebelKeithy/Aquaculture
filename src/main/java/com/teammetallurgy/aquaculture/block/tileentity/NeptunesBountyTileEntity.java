package com.teammetallurgy.aquaculture.block.tileentity;

import com.teammetallurgy.aquaculture.init.AquaBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nonnull;

public class NeptunesBountyTileEntity extends ChestBlockEntity {

    public NeptunesBountyTileEntity(BlockPos pos, BlockState state) {
        super(AquaBlocks.AquaTileEntities.NEPTUNES_BOUNTY, pos, state);
    }

    @Override
    @Nonnull
    public Component getDefaultName() {
        return new TranslatableComponent(this.getBlockState().getBlock().getDescriptionId());
    }
}
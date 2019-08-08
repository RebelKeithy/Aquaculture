package com.teammetallurgy.aquaculture.block;

import com.teammetallurgy.aquaculture.block.tileentity.FishTrapTileEntity;
import net.minecraft.block.Block;
import net.minecraft.block.ContainerBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class FishTrapBlock extends ContainerBlock {

    public FishTrapBlock() {
        super(Block.Properties.create(Material.WOOD).hardnessAndResistance(1.2F).sound(SoundType.CLOTH));
    }

    @Override
    @Nullable
    public TileEntity createNewTileEntity(@Nonnull IBlockReader world) {
        return new FishTrapTileEntity();
    }
}
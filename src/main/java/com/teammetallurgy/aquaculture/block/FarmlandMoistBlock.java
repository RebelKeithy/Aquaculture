package com.teammetallurgy.aquaculture.block;

import net.minecraft.block.Block;
import net.minecraft.block.FarmlandBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class FarmlandMoistBlock extends FarmlandBlock {

    public FarmlandMoistBlock() {
        super(Block.Properties.create(Material.EARTH).hardnessAndResistance(0.6F).sound(SoundType.GROUND));
        this.setDefaultState(this.stateContainer.getBaseState().with(MOISTURE, 7));
    }
}
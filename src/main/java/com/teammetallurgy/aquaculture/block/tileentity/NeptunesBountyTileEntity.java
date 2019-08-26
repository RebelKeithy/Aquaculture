package com.teammetallurgy.aquaculture.block.tileentity;

import com.teammetallurgy.aquaculture.init.AquaBlocks;
import net.minecraft.tileentity.ChestTileEntity;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;

import javax.annotation.Nonnull;

public class NeptunesBountyTileEntity extends ChestTileEntity {

    public NeptunesBountyTileEntity() {
        super(AquaBlocks.AquaTileEntities.NEPTUNES_BOUNTY);
    }

    @Override
    @Nonnull
    public ITextComponent getDefaultName() {
        return new TranslationTextComponent(this.getBlockState().getBlock().getTranslationKey());
    }
}
package com.teammetallurgy.aquaculture.client.renderer.tileentity;

import com.teammetallurgy.aquaculture.Aquaculture;
import com.teammetallurgy.aquaculture.block.blockentity.NeptunesBountyBlockEntity;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.blockentity.ChestRenderer;
import net.minecraft.client.resources.model.Material;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.state.properties.ChestType;

import javax.annotation.Nonnull;

public class NeptunesBountyRenderer extends ChestRenderer<NeptunesBountyBlockEntity> {
    public static NeptunesBountyRenderer instance;

    public NeptunesBountyRenderer(BlockEntityRendererProvider.Context context) {
        super(context);
        instance = this;
    }

    @Override
    @Nonnull
    protected Material getMaterial(@Nonnull NeptunesBountyBlockEntity tileEntity, @Nonnull ChestType chestType) {
        return new Material(Sheets.CHEST_SHEET, new ResourceLocation(Aquaculture.MOD_ID, "entity/tileentity/neptunes_bounty"));
    }
}
package com.teammetallurgy.aquaculture.client.renderer.tileentity;

import com.teammetallurgy.aquaculture.Aquaculture;
import net.minecraft.client.renderer.tileentity.ChestTileEntityRenderer;
import net.minecraft.client.renderer.tileentity.model.ChestModel;
import net.minecraft.client.renderer.tileentity.model.LargeChestModel;
import net.minecraft.tileentity.IChestLid;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nonnull;

public class NeptunesBountyRenderer<T extends TileEntity & IChestLid> extends ChestTileEntityRenderer<T> {
    private static final ResourceLocation NEPPTUNES_BOUNTY = new ResourceLocation(Aquaculture.MOD_ID, "textures/entity/tileentity/neptunes_bounty.png");
    private final ChestModel simpleChest = new ChestModel();
    private final ChestModel largeChest = new LargeChestModel();

    @Override
    @Nonnull
    public ChestModel getChestModel(T chest, int destroyStages, boolean isDouble) {
        ResourceLocation location;
        if (destroyStages >= 0) {
            location = DESTROY_STAGES[destroyStages];
        } else {
            location = NEPPTUNES_BOUNTY;
        }
        this.bindTexture(location);
        return isDouble ? this.largeChest : this.simpleChest;
    }
}
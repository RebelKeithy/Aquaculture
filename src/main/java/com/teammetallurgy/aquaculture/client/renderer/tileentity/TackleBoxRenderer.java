package com.teammetallurgy.aquaculture.client.renderer.tileentity;

import com.mojang.blaze3d.platform.GlStateManager;
import com.teammetallurgy.aquaculture.Aquaculture;
import com.teammetallurgy.aquaculture.block.TackleBoxBlock;
import com.teammetallurgy.aquaculture.block.tileentity.TackleBoxTileEntity;
import com.teammetallurgy.aquaculture.client.renderer.tileentity.model.TackleBoxModel;
import com.teammetallurgy.aquaculture.init.AquaBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.tileentity.IChestLid;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;

public class TackleBoxRenderer <T extends TackleBoxTileEntity & IChestLid> extends TileEntityRenderer<T> {
    private static final ResourceLocation TACKLE_BOX_TEXTURE = new ResourceLocation(Aquaculture.MOD_ID, "textures/entity/tileentity/tackle_box.png");
    private final TackleBoxModel tackleBoxModel = new TackleBoxModel();

    public TackleBoxRenderer() {

    }

    @Override
    public void render(T tackleBox, double x, double y, double z, float partialTicks, int destroyStage) {
        GlStateManager.enableDepthTest();
        GlStateManager.depthFunc(515);
        GlStateManager.depthMask(true);
        BlockState tackleBoxSouth = tackleBox.hasWorld() ? tackleBox.getBlockState() : AquaBlocks.TACKLE_BOX.getDefaultState().with(TackleBoxBlock.FACING, Direction.SOUTH);
        if (destroyStage >= 0) { //TODO
            GlStateManager.matrixMode(5890);
            GlStateManager.pushMatrix();
            GlStateManager.scalef(4.0F, 4.0F, 1.0F);
            GlStateManager.translatef(0.0625F, 0.0625F, 0.0625F);
            GlStateManager.matrixMode(5888);
        } else {
            GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        }

        GlStateManager.pushMatrix();
        GlStateManager.enableRescaleNormal();
        this.bindTexture(TACKLE_BOX_TEXTURE);
        GlStateManager.translatef((float) x, (float) y + 1.15F, (float) z);
        GlStateManager.scalef(1.0F, -1.0F, -1.0F); //Flip
        GlStateManager.scalef(0.065F, 0.065F, 0.065F); //Resize
        Direction direction = tackleBoxSouth.get(TackleBoxBlock.FACING);
        if ((double) Math.abs(direction.getHorizontalAngle()) > 1.0E-5D) {
            GlStateManager.rotatef(direction.getHorizontalAngle(), 0.0F, 1.0F, 0.0F);
        }

        if (direction == Direction.NORTH) {
            GlStateManager.translatef(-14.65F, 0F, 7.75F);
        } else if (direction == Direction.SOUTH) {
            GlStateManager.translatef(0.65F, 0F, -7.75F);
        } else if (direction == Direction.WEST) {
            GlStateManager.translatef(0.65F, 0F, 7.75F);
        } else if (direction == Direction.EAST) {
            GlStateManager.translatef(-14.65F, 0F, -7.75F);
        }

        this.applyLidRotation(tackleBox, partialTicks, this.tackleBoxModel);
        this.tackleBoxModel.render(1.0F);
        GlStateManager.disableRescaleNormal();
        GlStateManager.popMatrix();
        GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        if (destroyStage >= 0) {
            GlStateManager.matrixMode(5890);
            GlStateManager.popMatrix();
            GlStateManager.matrixMode(5888);
        }
    }

    private void applyLidRotation(T tackleBox, float partialTicks, TackleBoxModel tackleBoxModel) {
        float lidAngle = tackleBox.getLidAngle(partialTicks);
        lidAngle = 1.0F - lidAngle;
        lidAngle = 1.0F - lidAngle * lidAngle * lidAngle;
        tackleBoxModel.getLid().rotateAngleX = -(lidAngle * 1.5707964F);
    }
}
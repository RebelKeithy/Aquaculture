/*package com.teammetallurgy.aquaculture.client.renderer.tileentity;

import com.mojang.blaze3d.systems.RenderSystem;
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
import org.lwjgl.opengl.GL11;

public class TackleBoxRenderer <T extends TackleBoxTileEntity & IChestLid> extends TileEntityRenderer<T> { //TODO
    private static final ResourceLocation TACKLE_BOX_TEXTURE = new ResourceLocation(Aquaculture.MOD_ID, "textures/entity/tileentity/tackle_box.png");
    private final TackleBoxModel tackleBoxModel = new TackleBoxModel();

    @Override
    public void render(T tackleBox, double x, double y, double z, float partialTicks, int destroyStage) {
        RenderSystem.enableDepthTest();
        RenderSystem.depthFunc(515);
        RenderSystem.depthMask(true);
        BlockState tackleBoxSouth = tackleBox.hasWorld() ? tackleBox.getBlockState() : AquaBlocks.TACKLE_BOX.getDefaultState().with(TackleBoxBlock.FACING, Direction.SOUTH);
        if (destroyStage >= 0) {
            this.bindTexture(DESTROY_STAGES[destroyStage]);
            RenderSystem.matrixMode(GL11.GL_TEXTURE);
            RenderSystem.pushMatrix();
            RenderSystem.scalef(4.5F, 4.5F, 4.0F);
            RenderSystem.translatef(0.0325F, 0.0325F, 0.0325F);
            RenderSystem.matrixMode(GL11.GL_MODELVIEW);
        } else {
            this.bindTexture(TACKLE_BOX_TEXTURE);
            RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        }

        RenderSystem.pushMatrix();
        RenderSystem.enableRescaleNormal();
        RenderSystem.translatef((float) x, (float) y + 1.15F, (float) z);
        RenderSystem.scalef(1.0F, -1.0F, -1.0F); //Flip
        RenderSystem.scalef(0.065F, 0.065F, 0.065F); //Resize
        Direction direction = tackleBoxSouth.get(TackleBoxBlock.FACING);
        if ((double) Math.abs(direction.getHorizontalAngle()) > 1.0E-5D) {
            RenderSystem.rotatef(direction.getHorizontalAngle(), 0.0F, 1.0F, 0.0F);
        }

        if (direction == Direction.NORTH) {
            RenderSystem.translatef(-14.65F, 0F, 7.75F);
        } else if (direction == Direction.SOUTH) {
            RenderSystem.translatef(0.65F, 0F, -7.75F);
        } else if (direction == Direction.WEST) {
            RenderSystem.translatef(0.65F, 0F, 7.75F);
        } else if (direction == Direction.EAST) {
            RenderSystem.translatef(-14.65F, 0F, -7.75F);
        }

        this.applyLidRotation(tackleBox, partialTicks, this.tackleBoxModel);
        this.tackleBoxModel.render(1.0F);
        RenderSystem.disableRescaleNormal();
        RenderSystem.popMatrix();
        RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        if (destroyStage >= 0) {
            RenderSystem.matrixMode(5890);
            RenderSystem.popMatrix();
            RenderSystem.matrixMode(5888);
        }
    }

    private void applyLidRotation(T tackleBox, float partialTicks, TackleBoxModel tackleBoxModel) {
        float lidAngle = tackleBox.getLidAngle(partialTicks);
        lidAngle = 1.0F - lidAngle;
        lidAngle = 1.0F - lidAngle * lidAngle * lidAngle;
        tackleBoxModel.getLid().rotateAngleX = -(lidAngle * 1.5707964F);
    }
}*/
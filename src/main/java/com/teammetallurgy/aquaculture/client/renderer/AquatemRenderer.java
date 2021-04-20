package com.teammetallurgy.aquaculture.client.renderer;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.teammetallurgy.aquaculture.block.NeptunesBountyBlock;
import com.teammetallurgy.aquaculture.block.TackleBoxBlock;
import com.teammetallurgy.aquaculture.block.tileentity.NeptunesBountyTileEntity;
import com.teammetallurgy.aquaculture.block.tileentity.TackleBoxTileEntity;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.tileentity.ItemStackTileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nonnull;

@OnlyIn(Dist.CLIENT)
public class AquatemRenderer extends ItemStackTileEntityRenderer {

    @Override
    public void func_239207_a_(@Nonnull ItemStack stack, @Nonnull ItemCameraTransforms.TransformType transformType, @Nonnull MatrixStack matrixStack, @Nonnull IRenderTypeBuffer buffer, int i, int i1) {
        Item item = stack.getItem();
        if (item instanceof BlockItem) {
            Block block = ((BlockItem) item).getBlock();
            if (block instanceof TackleBoxBlock) {
                TileEntityRendererDispatcher.instance.renderItem(new TackleBoxTileEntity(), matrixStack, buffer, i, i1);
            } else if (block instanceof NeptunesBountyBlock) {
                TileEntityRendererDispatcher.instance.renderItem(new NeptunesBountyTileEntity(), matrixStack, buffer, i, i1);
            }
        }
    }
}
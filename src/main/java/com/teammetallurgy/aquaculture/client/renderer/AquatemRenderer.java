package com.teammetallurgy.aquaculture.client.renderer;

import com.teammetallurgy.aquaculture.block.NeptunesBountyBlock;
import com.teammetallurgy.aquaculture.block.TackleBoxBlock;
import com.teammetallurgy.aquaculture.block.tileentity.NeptunesBountyTileEntity;
import com.teammetallurgy.aquaculture.block.tileentity.TackleBoxTileEntity;
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
    private static final NeptunesBountyTileEntity NEPTUNES_BOUNTY = new NeptunesBountyTileEntity();
    private static final TackleBoxTileEntity TACKLE_BOX = new TackleBoxTileEntity();

    @Override
    public void renderByItem(@Nonnull ItemStack stack) {
        Item item = stack.getItem();
        boolean isBlockItem = item instanceof BlockItem;
        if (isBlockItem && ((BlockItem) item).getBlock() instanceof TackleBoxBlock) {
            TileEntityRendererDispatcher.instance.renderAsItem(TACKLE_BOX);
        } else if (isBlockItem && ((BlockItem) item).getBlock() instanceof NeptunesBountyBlock) {
            TileEntityRendererDispatcher.instance.renderAsItem(NEPTUNES_BOUNTY);
        }
    }
}
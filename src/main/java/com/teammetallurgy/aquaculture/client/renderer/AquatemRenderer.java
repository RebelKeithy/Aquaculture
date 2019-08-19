package com.teammetallurgy.aquaculture.client.renderer;

import com.teammetallurgy.aquaculture.block.TackleBoxBlock;
import com.teammetallurgy.aquaculture.block.tileentity.TackleBoxTileEntity;
import net.minecraft.client.renderer.tileentity.ItemStackTileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;

public class AquatemRenderer extends ItemStackTileEntityRenderer {
    private final TackleBoxTileEntity tackleBox = new TackleBoxTileEntity();

    @Override
    public void renderByItem(@Nonnull ItemStack stack) {
        Item item = stack.getItem();
        boolean isBlockItem = item instanceof BlockItem;
        if (isBlockItem && ((BlockItem) item).getBlock() instanceof TackleBoxBlock) {
            TileEntityRendererDispatcher.instance.renderAsItem(this.tackleBox);
        }
    }
}
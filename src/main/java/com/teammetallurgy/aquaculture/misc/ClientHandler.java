package com.teammetallurgy.aquaculture.misc;

import com.teammetallurgy.aquaculture.block.tileentity.NeptunesBountyTileEntity;
import com.teammetallurgy.aquaculture.block.tileentity.TackleBoxTileEntity;
import com.teammetallurgy.aquaculture.client.gui.screen.TackleBoxScreen;
import com.teammetallurgy.aquaculture.client.renderer.entity.*;
import com.teammetallurgy.aquaculture.client.renderer.tileentity.NeptunesBountyRenderer;
import com.teammetallurgy.aquaculture.client.renderer.tileentity.TackleBoxRenderer;
import com.teammetallurgy.aquaculture.entity.*;
import com.teammetallurgy.aquaculture.init.AquaGuis;
import com.teammetallurgy.aquaculture.init.AquaItems;
import com.teammetallurgy.aquaculture.item.FishingLineItem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.client.renderer.color.ItemColors;
import net.minecraft.client.renderer.entity.TippedArrowRenderer;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

public class ClientHandler {

    public static void setupClient() {
        ScreenManager.registerFactory(AquaGuis.TACKLE_BOX, TackleBoxScreen::new);
        ClientRegistry.bindTileEntitySpecialRenderer(NeptunesBountyTileEntity.class, new NeptunesBountyRenderer<>());
        ClientRegistry.bindTileEntitySpecialRenderer(TackleBoxTileEntity.class, new TackleBoxRenderer<>());
        RenderingRegistry.registerEntityRenderingHandler(AquaFishingBobberEntity.class, AquaBobberRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(AquaFishEntity.class, AquaFishRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(NeptuniumTridentEntity.class, NeptuniumTridentRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(WaterArrowEntity.class, TippedArrowRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(TurtleLandEntity.class, TurtleLandRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(JellyfishEntity.class, JellyfishRenderer::new);
        //Item Colors
        ItemColors itemColor = Minecraft.getInstance().getItemColors();
        itemColor.register((stack, tintIndex) -> tintIndex > 0 ? -1 : ((FishingLineItem) stack.getItem()).getColor(stack), AquaItems.FISHING_LINE);
    }
}
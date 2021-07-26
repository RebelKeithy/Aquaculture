package com.teammetallurgy.aquaculture.client.renderer.tileentity;

import com.teammetallurgy.aquaculture.Aquaculture;
import com.teammetallurgy.aquaculture.block.tileentity.NeptunesBountyTileEntity;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.blockentity.ChestRenderer;
import net.minecraft.client.resources.model.Material;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.state.properties.ChestType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import javax.annotation.Nonnull;

@Mod.EventBusSubscriber(modid = Aquaculture.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class NeptunesBountyRenderer extends ChestRenderer<NeptunesBountyTileEntity> {
    private static final ResourceLocation NEPTUNES_BOUNTY = new ResourceLocation(Aquaculture.MOD_ID, "entity/tileentity/neptunes_bounty");

    public NeptunesBountyRenderer(BlockEntityRendererProvider.Context contex) {
        super(contex);
    }

    @Override
    @Nonnull
    protected Material getMaterial(@Nonnull NeptunesBountyTileEntity tileEntity, @Nonnull ChestType chestType) {
        return new Material(Sheets.CHEST_SHEET, NEPTUNES_BOUNTY);
    }

    @SubscribeEvent
    public static void onTextureStitch(TextureStitchEvent.Pre event) {
        if (event.getMap().location().equals(Sheets.CHEST_SHEET)) {
            event.addSprite(NEPTUNES_BOUNTY);
        }
    }
}
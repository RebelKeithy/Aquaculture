package com.teammetallurgy.aquaculture.client;

import com.teammetallurgy.aquaculture.Aquaculture;
import com.teammetallurgy.aquaculture.client.gui.screen.TackleBoxScreen;
import com.teammetallurgy.aquaculture.client.renderer.entity.AquaBobberRenderer;
import com.teammetallurgy.aquaculture.client.renderer.entity.AquaFishRenderer;
import com.teammetallurgy.aquaculture.client.renderer.entity.FishMountRenderer;
import com.teammetallurgy.aquaculture.client.renderer.entity.TurtleLandRenderer;
import com.teammetallurgy.aquaculture.client.renderer.tileentity.NeptunesBountyRenderer;
import com.teammetallurgy.aquaculture.client.renderer.tileentity.TackleBoxRenderer;
import com.teammetallurgy.aquaculture.entity.AquaFishEntity;
import com.teammetallurgy.aquaculture.entity.FishMountEntity;
import com.teammetallurgy.aquaculture.init.*;
import com.teammetallurgy.aquaculture.item.DyeableItem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.client.renderer.color.ItemColors;
import net.minecraft.client.renderer.entity.TippedArrowRenderer;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.FishingRodItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemModelsProperties;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Aquaculture.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientHandler {

    public static void setupClient() {
        ScreenManager.registerFactory(AquaGuis.TACKLE_BOX, TackleBoxScreen::new);
        ClientRegistry.bindTileEntityRenderer(AquaBlocks.AquaTileEntities.NEPTUNES_BOUNTY, NeptunesBountyRenderer::new);
        ClientRegistry.bindTileEntityRenderer(AquaBlocks.AquaTileEntities.TACKLE_BOX, TackleBoxRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(AquaEntities.BOBBER, AquaBobberRenderer::new);
        for (EntityType<AquaFishEntity> fish : FishRegistry.fishEntities) {
            RenderingRegistry.registerEntityRenderingHandler(fish, (manager) -> new AquaFishRenderer(manager, fish.getRegistryName() != null && fish.getRegistryName().equals(new ResourceLocation(Aquaculture.MOD_ID, "jellyfish"))));
        }
        RenderingRegistry.registerEntityRenderingHandler(AquaEntities.WATER_ARROW, TippedArrowRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(AquaEntities.BOX_TURTLE, TurtleLandRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(AquaEntities.ARRAU_TURTLE, TurtleLandRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(AquaEntities.STARSHELL_TURTLE, TurtleLandRenderer::new);
        for (EntityType<FishMountEntity> fishMount : FishRegistry.fishMounts) {
            RenderingRegistry.registerEntityRenderingHandler(fishMount, FishMountRenderer::new);
        }
        //Item Colors
        ItemColors itemColor = Minecraft.getInstance().getItemColors();
        itemColor.register((stack, tintIndex) -> tintIndex > 0 ? -1 : ((DyeableItem) stack.getItem()).getColor(stack), AquaItems.FISHING_LINE, AquaItems.BOBBER);
        registerFishingRodModelProperties(AquaItems.IRON_FISHING_ROD);
        registerFishingRodModelProperties(AquaItems.GOLD_FISHING_ROD);
        registerFishingRodModelProperties(AquaItems.DIAMOND_FISHING_ROD);
        registerFishingRodModelProperties(AquaItems.NEPTUNIUM_FISHING_ROD);
        registerBowModelProperties(AquaItems.NEPTUNIUM_BOW);
    }

    @SubscribeEvent
    public static void registerModels(ModelRegistryEvent event) {
        ModelLoader.addSpecialModel(FishMountRenderer.OAK);
        ModelLoader.addSpecialModel(FishMountRenderer.SPRUCE);
        ModelLoader.addSpecialModel(FishMountRenderer.BIRCH);
        ModelLoader.addSpecialModel(FishMountRenderer.JUNGLE);
        ModelLoader.addSpecialModel(FishMountRenderer.ACACIA);
        ModelLoader.addSpecialModel(FishMountRenderer.DARK_OAK);
    }

    public static void registerFishingRodModelProperties(Item fishingRod) {
        ItemModelsProperties.registerProperty(fishingRod, new ResourceLocation("cast"), (heldStack, world, livingEntity) -> {
            if (livingEntity == null) {
                return 0.0F;
            } else {
                boolean isMainhand = livingEntity.getHeldItemMainhand() == heldStack;
                boolean isOffHand = livingEntity.getHeldItemOffhand() == heldStack;
                if (livingEntity.getHeldItemMainhand().getItem() instanceof FishingRodItem) {
                    isOffHand = false;
                }
                return (isMainhand || isOffHand) && livingEntity instanceof PlayerEntity && ((PlayerEntity) livingEntity).fishingBobber != null ? 1.0F : 0.0F;
            }
        });
    }

    public static void registerBowModelProperties(Item bow) {
        ItemModelsProperties.registerProperty(bow, new ResourceLocation("pull"), (stack, world, entity) -> {
            if (entity == null) {
                return 0.0F;
            } else {
                return entity.getActiveItemStack() != stack ? 0.0F : (float) (stack.getUseDuration() - entity.getItemInUseCount()) / 20.0F;
            }
        });
        ItemModelsProperties.registerProperty(bow, new ResourceLocation("pulling"), (stack, world, entity) -> entity != null && entity.isHandActive() && entity.getActiveItemStack() == stack ? 1.0F : 0.0F);
    }
}
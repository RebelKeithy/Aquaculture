package com.teammetallurgy.aquaculture.client;

import com.teammetallurgy.aquaculture.Aquaculture;
import com.teammetallurgy.aquaculture.client.gui.screen.TackleBoxScreen;
import com.teammetallurgy.aquaculture.client.renderer.entity.AquaBobberRenderer;
import com.teammetallurgy.aquaculture.client.renderer.entity.AquaFishRenderer;
import com.teammetallurgy.aquaculture.client.renderer.entity.FishMountRenderer;
import com.teammetallurgy.aquaculture.client.renderer.entity.TurtleLandRenderer;
import com.teammetallurgy.aquaculture.client.renderer.entity.model.*;
import com.teammetallurgy.aquaculture.client.renderer.tileentity.NeptunesBountyRenderer;
import com.teammetallurgy.aquaculture.client.renderer.tileentity.TackleBoxRenderer;
import com.teammetallurgy.aquaculture.entity.AquaFishEntity;
import com.teammetallurgy.aquaculture.entity.FishMountEntity;
import com.teammetallurgy.aquaculture.init.*;
import com.teammetallurgy.aquaculture.item.DyeableItem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.color.item.ItemColors;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.client.renderer.entity.SpectralArrowRenderer;
import net.minecraft.client.renderer.entity.TippableArrowRenderer;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.FishingRodItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Aquaculture.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientHandler {
    public static final ModelLayerLocation TACKLE_BOX = new ModelLayerLocation(new ResourceLocation(Aquaculture.MOD_ID, "tackle_box"), "tackle_box");
    public static final ModelLayerLocation TURTLE_LAND_LAYER = new ModelLayerLocation(new ResourceLocation(Aquaculture.MOD_ID, "turtle_land"), "turtle_land");
    public static final ModelLayerLocation SMALL_MODEL = new ModelLayerLocation(new ResourceLocation(Aquaculture.MOD_ID, "small_model"), "small_model");
    public static final ModelLayerLocation MEDIUM_MODEL = new ModelLayerLocation(new ResourceLocation(Aquaculture.MOD_ID, "medium_model"), "medium_model");
    public static final ModelLayerLocation LARGE_MODEL = new ModelLayerLocation(new ResourceLocation(Aquaculture.MOD_ID, "large_model"), "large_model");
    public static final ModelLayerLocation LONGNOSE_MODEL = new ModelLayerLocation(new ResourceLocation(Aquaculture.MOD_ID, "longnose_model"), "longnose_model");
    public static final ModelLayerLocation CATFISH_MODEL = new ModelLayerLocation(new ResourceLocation(Aquaculture.MOD_ID, "catfish_model"), "catfish_model");
    public static final ModelLayerLocation JELLYFISH_MODEL = new ModelLayerLocation(new ResourceLocation(Aquaculture.MOD_ID, "jellyfish_model"), "jellyfish_model");

    public static void setupClient() {
        MenuScreens.register(AquaGuis.TACKLE_BOX, TackleBoxScreen::new);
        BlockEntityRenderers.register(AquaBlocks.AquaTileEntities.NEPTUNES_BOUNTY, NeptunesBountyRenderer::new);
        BlockEntityRenderers.register(AquaBlocks.AquaTileEntities.TACKLE_BOX, TackleBoxRenderer::new);

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
    public static void registerEntityRenders(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(AquaEntities.BOBBER, AquaBobberRenderer::new);
        for (EntityType<AquaFishEntity> fish : FishRegistry.fishEntities) {
            event.registerEntityRenderer(fish, (context) -> new AquaFishRenderer(context, fish.getRegistryName() != null && fish.getRegistryName().equals(new ResourceLocation(Aquaculture.MOD_ID, "jellyfish"))));
        }
        event.registerEntityRenderer(AquaEntities.WATER_ARROW, TippableArrowRenderer::new);
        event.registerEntityRenderer(AquaEntities.SPECTRAL_WATER_ARROW, SpectralArrowRenderer::new);
        event.registerEntityRenderer(AquaEntities.BOX_TURTLE, TurtleLandRenderer::new);
        event.registerEntityRenderer(AquaEntities.ARRAU_TURTLE, TurtleLandRenderer::new);
        event.registerEntityRenderer(AquaEntities.STARSHELL_TURTLE, TurtleLandRenderer::new);
        for (EntityType<FishMountEntity> fishMount : FishRegistry.fishMounts) {
            event.registerEntityRenderer(fishMount, FishMountRenderer::new);
        }
    }

    @SubscribeEvent
    public static void registerLayerDefinition(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(TACKLE_BOX, TackleBoxRenderer::createLayer);
        event.registerLayerDefinition(TURTLE_LAND_LAYER, TurtleLandModel::createBodyLayer);
        event.registerLayerDefinition(SMALL_MODEL, FishSmallModel::createBodyLayer);
        event.registerLayerDefinition(MEDIUM_MODEL, FishMediumModel::createBodyLayer);
        event.registerLayerDefinition(LARGE_MODEL, FishLargeModel::createBodyLayer);
        event.registerLayerDefinition(LONGNOSE_MODEL, FishLongnoseModel::createBodyLayer);
        event.registerLayerDefinition(CATFISH_MODEL, FishCathfishModel::createBodyLayer);
        event.registerLayerDefinition(JELLYFISH_MODEL, JellyfishModel::createBodyLayer);
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
        ItemProperties.register(fishingRod, new ResourceLocation("cast"), (stack, level, entity, i) -> {
            if (entity == null) {
                return 0.0F;
            } else {
                boolean isMainhand = entity.getMainHandItem() == stack;
                boolean isOffHand = entity.getOffhandItem() == stack;
                if (entity.getMainHandItem().getItem() instanceof FishingRodItem) {
                    isOffHand = false;
                }
                return (isMainhand || isOffHand) && entity instanceof Player && ((Player) entity).fishing != null ? 1.0F : 0.0F;
            }
        });
    }

    public static void registerBowModelProperties(Item bow) {
        ItemProperties.register(bow, new ResourceLocation("pull"), (stack, level, entity, i) -> {
            if (entity == null) {
                return 0.0F;
            } else {
                return entity.getUseItem() != stack ? 0.0F : (float) (stack.getUseDuration() - entity.getUseItemRemainingTicks()) / 20.0F;
            }
        });
        ItemProperties.register(bow, new ResourceLocation("pulling"), (stack, level, entity, i) -> entity != null && entity.isUsingItem() && entity.getUseItem() == stack ? 1.0F : 0.0F);
    }
}
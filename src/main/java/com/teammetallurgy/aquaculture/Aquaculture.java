package com.teammetallurgy.aquaculture;

import com.teammetallurgy.aquaculture.block.tileentity.TackleBoxTileEntity;
import com.teammetallurgy.aquaculture.client.gui.screen.TackleBoxScreen;
import com.teammetallurgy.aquaculture.client.renderer.entity.AquaBobberRenderer;
import com.teammetallurgy.aquaculture.client.renderer.entity.AquaFishRenderer;
import com.teammetallurgy.aquaculture.client.renderer.entity.NeptuniumTridentRenderer;
import com.teammetallurgy.aquaculture.client.renderer.tileentity.TackleBoxRenderer;
import com.teammetallurgy.aquaculture.entity.AquaFishEntity;
import com.teammetallurgy.aquaculture.entity.AquaFishingBobberEntity;
import com.teammetallurgy.aquaculture.entity.NeptuniumTridentEntity;
import com.teammetallurgy.aquaculture.entity.WaterArrowEntity;
import com.teammetallurgy.aquaculture.init.AquaGuis;
import com.teammetallurgy.aquaculture.init.AquaItems;
import com.teammetallurgy.aquaculture.item.crafting.ConditionFactory;
import com.teammetallurgy.aquaculture.loot.BiomeTagCheck;
import com.teammetallurgy.aquaculture.loot.FishReadFromJson;
import com.teammetallurgy.aquaculture.loot.FishWeightHandler;
import com.teammetallurgy.aquaculture.misc.AquaConfig;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.client.renderer.entity.TippedArrowRenderer;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.world.storage.loot.conditions.LootConditionManager;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.Nonnull;

@Mod(value = Aquaculture.MOD_ID)
public class Aquaculture {
    public final static String MOD_ID = "aquaculture";
    public static final Logger LOG = LogManager.getLogger(MOD_ID);
    public static final ItemGroup GROUP = new ItemGroup(Aquaculture.MOD_ID) {
        @Override
        @Nonnull
        public ItemStack createIcon() {
            return new ItemStack(AquaItems.IRON_FISHING_ROD);
        }
    };

    public Aquaculture() {
        final IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();
        modBus.addListener(this::setupCommon);
        modBus.addListener(this::setupClient);
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, AquaConfig.spec);
    }

    private void setupCommon(FMLCommonSetupEvent event) {
        LootConditionManager.registerCondition(new BiomeTagCheck.Serializer());
        ConditionFactory.registerConditions();
        FishWeightHandler.registerFishData();
        BiomeDictionary.Type.getType("TWILIGHT"); //Add Twilight tag, for Twilight Forest support
        FishReadFromJson.addFishSpawns();
    }

    private void setupClient(FMLClientSetupEvent event) {
        ScreenManager.registerFactory(AquaGuis.TACKLE_BOX, TackleBoxScreen::new);
        ClientRegistry.bindTileEntitySpecialRenderer(TackleBoxTileEntity.class, new TackleBoxRenderer<>());
        RenderingRegistry.registerEntityRenderingHandler(AquaFishingBobberEntity.class, AquaBobberRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(AquaFishEntity.class, AquaFishRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(NeptuniumTridentEntity.class, NeptuniumTridentRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(WaterArrowEntity.class, TippedArrowRenderer::new);
    }
}
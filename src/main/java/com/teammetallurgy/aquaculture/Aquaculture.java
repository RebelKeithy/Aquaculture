package com.teammetallurgy.aquaculture;

import com.teammetallurgy.aquaculture.api.AquacultureAPI;
import com.teammetallurgy.aquaculture.block.WormFarmBlock;
import com.teammetallurgy.aquaculture.client.ClientHandler;
import com.teammetallurgy.aquaculture.init.AquaEntities;
import com.teammetallurgy.aquaculture.init.AquaItems;
import com.teammetallurgy.aquaculture.init.FishRegistry;
import com.teammetallurgy.aquaculture.loot.BiomePropertiesCheck;
import com.teammetallurgy.aquaculture.loot.FishWeightHandler;
import com.teammetallurgy.aquaculture.misc.AquaConfig;
import cpw.mods.modlauncher.Environment;
import cpw.mods.modlauncher.Launcher;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.LootConditionType;
import net.minecraft.loot.conditions.LootConditionManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DeferredWorkQueue;
import net.minecraftforge.fml.ModLoadingContext;
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
    public static Aquaculture instance;
    public static final boolean IS_DEV = Launcher.INSTANCE.environment().getProperty(Environment.Keys.VERSION.get()).filter(v -> v.equals("MOD_DEV")).isPresent();
    public final static String MOD_ID = "aquaculture";
    public static final Logger LOG = LogManager.getLogger(MOD_ID);
    public static final ItemGroup GROUP = new ItemGroup(Aquaculture.MOD_ID) {
        @Override
        @Nonnull
        public ItemStack createIcon() {
            return new ItemStack(AquaItems.IRON_FISHING_ROD);
        }
    };
    public static final LootConditionType BIOME_TAG_CHECK = LootConditionManager.register(new ResourceLocation(MOD_ID, "biome_properties_check").toString(), new BiomePropertiesCheck.Serializer());

    public Aquaculture() {
        instance = this;
        final IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();
        modBus.addListener(this::setupCommon);
        modBus.addListener(this::setupClient);
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, AquaConfig.spec);
        AquacultureAPI.Tags.init();
    }

    private void setupCommon(FMLCommonSetupEvent event) {
        DeferredWorkQueue.runLater(() -> {
            FishWeightHandler.registerFishData();
            AquaEntities.setSpawnPlacement();
            WormFarmBlock.addCompostables();
            if (AquaConfig.BASIC_OPTIONS.aqFishToBreedCats.get()) {
                FishRegistry.addCatBreeding();
            }
        });
    }

    private void setupClient(FMLClientSetupEvent event) {
        ClientHandler.setupClient();
    }
}
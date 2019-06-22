package com.teammetallurgy.aquaculture;

import com.teammetallurgy.aquaculture.client.RenderAquaFishHook;
import com.teammetallurgy.aquaculture.entity.AquaFishingBobberEntity;
import com.teammetallurgy.aquaculture.init.AquaItems;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import javax.annotation.Nonnull;

@Mod(value = Aquaculture.MOD_ID)
public class Aquaculture {
    public final static String MOD_ID = "aquaculture";
    public static final ItemGroup TAB = new ItemGroup(Aquaculture.MOD_ID) {
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

    }

    private void setupClient(FMLClientSetupEvent event) {
        RenderingRegistry.registerEntityRenderingHandler(AquaFishingBobberEntity.class, RenderAquaFishHook::new);
    }
}
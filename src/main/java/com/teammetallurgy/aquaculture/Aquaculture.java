package com.teammetallurgy.aquaculture;

import com.teammetallurgy.aquaculture.client.RenderAquaFishHook;
import com.teammetallurgy.aquaculture.entity.EntityAquaFishHook;
import com.teammetallurgy.aquaculture.utils.AquacultureTab;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(value = Aquaculture.MOD_ID)
public class Aquaculture {
    public final static String MOD_ID = "aquaculture";
    public static final AquacultureTab TAB = new AquacultureTab(MOD_ID);

    public Aquaculture() {
        final IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();
        modBus.addListener(this::setupCommon);
        modBus.addListener(this::setupClient);
    }

    private void setupCommon(FMLCommonSetupEvent event) {

    }

    private void setupClient(FMLClientSetupEvent event) {
        RenderingRegistry.registerEntityRenderingHandler(EntityAquaFishHook.class, RenderAquaFishHook::new);
    }
}
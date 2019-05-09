package com.teammetallurgy.aquaculture;

import com.teammetallurgy.aquaculture.utils.AquacultureTab;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(value = Aquaculture.MOD_ID)
public class Aquaculture {
    public final static String MOD_ID = "aquaculture";
    public final static String MOD_NAME = "Aquaculture";

    public static final AquacultureTab TAB = new AquacultureTab(MOD_ID);

    public Aquaculture() {
        final IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();
        modBus.addListener(this::setupCommon);
        modBus.addListener(this::setupClient);
    }

    private void setupCommon(FMLCommonSetupEvent event) {
        //EntityRegistry.registerModEntity(new ResourceLocation("aquaculture:custom_fish_hook"), EntityCustomFishHook.class, "CustomFishHook", 0, Aquaculture.instance, 64, 5, true); //TODO, also don't register here
    }

    private void setupClient(FMLClientSetupEvent event) {

    }
}
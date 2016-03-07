package com.teammetallurgy.aquaculture;

import com.teammetallurgy.aquaculture.enchantments.AquacultureEnchants;
import com.teammetallurgy.aquaculture.handlers.AquacultureRecipes;
import com.teammetallurgy.aquaculture.handlers.AquacultureTab;
import com.teammetallurgy.aquaculture.handlers.Config;
import com.teammetallurgy.aquaculture.handlers.EntityCustomFishHook;
import com.teammetallurgy.aquaculture.items.AquacultureItems;
import com.teammetallurgy.aquaculture.proxy.CommonProxy;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.EntityRegistry;

@Mod(modid = Aquaculture.MOD_ID, name = Aquaculture.MOD_NAME, version = Aquaculture.MOD_VERSION)
public class Aquaculture {
    public final static String MOD_ID = "aquaculture";
    public final static String MOD_NAME = "Aquaculture";
    public final static String MOD_VERSION = "1.2.6";

    @Instance(MOD_ID)
    public static Aquaculture instance;

    @SidedProxy(clientSide = "com.teammetallurgy.aquaculture.proxy.ClientProxy", serverSide = "com.teammetallurgy.aquaculture.proxy.CommonProxy")
    public static CommonProxy proxy;
    public static AquacultureTab tab;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        new Config().init(event.getSuggestedConfigurationFile());
        tab = new AquacultureTab("Aquaculture");

        new AquacultureItems().register();
    }

    @EventHandler
    public void load(FMLInitializationEvent event) {

        new AquacultureRecipes().addRecipes();

        EntityRegistry.registerGlobalEntityID(EntityCustomFishHook.class, "CustomFishHook", EntityRegistry.findGlobalUniqueEntityId());
        AquacultureEnchants.init();

        tab.setItem(AquacultureItems.ironFishingRod);

        proxy.registerModelRenderers();
    }
}

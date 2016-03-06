package rebelkeithy.mods.aquaculture;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.EntityRegistry;
import rebelkeithy.mods.aquaculture.enchantments.AquacultureEnchants;
import rebelkeithy.mods.aquaculture.handlers.AquacultureRecipes;
import rebelkeithy.mods.aquaculture.handlers.AquacultureTab;
import rebelkeithy.mods.aquaculture.handlers.Config;
import rebelkeithy.mods.aquaculture.handlers.EntityCustomFishHook;
import rebelkeithy.mods.aquaculture.items.AquacultureItems;
import rebelkeithy.mods.aquaculture.proxy.CommonProxy;

@Mod(modid = Aquaculture.MOD_ID, name = Aquaculture.MOD_NAME, version = Aquaculture.MOD_VERSION)
public class Aquaculture {
    public final static String MOD_ID = "Aquaculture";
    public final static String MOD_NAME = "Aquaculture";
    public final static String MOD_VERSION = "1.2.6";

    @Instance(MOD_ID)
    public static Aquaculture instance;

    @SidedProxy(clientSide = "rebelkeithy.mods.aquaculture.proxy.ClientProxy", serverSide = "rebelkeithy.mods.aquaculture.proxy.CommonProxy")
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

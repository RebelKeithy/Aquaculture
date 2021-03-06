package rebelkeithy.mods.aquaculture;

import rebelkeithy.mods.aquaculture.enchantments.AquacultureEnchants;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;


@Mod(modid = Aquaculture.MOD_ID, name = Aquaculture.MOD_NAME, version = Aquaculture.MOD_VERSION, dependencies = "required-after:KeithyUtils@[1.1,]")
@NetworkMod(clientSideRequired = true, serverSideRequired = true)
public class Aquaculture 
{
	public final static String MOD_ID = "Aquaculture";
	public final static String MOD_NAME = "Aquaculture";
	public final static String MOD_VERSION = "1.2.3";

	@Instance(MOD_ID)
	public static Aquaculture instance;

	@SidedProxy(clientSide = "rebelkeithy.mods.aquaculture.ClientProxy", serverSide = "rebelkeithy.mods.aquaculture.CommonProxy")
	public static CommonProxy proxy;
	public static AquacultureTab tab;
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		//FMLClientHandler.instance().getClient().renderEngine
		Config.init(event.getSuggestedConfigurationFile());
		tab = new AquacultureTab("Aquaculture");
		LanguageRegistry.instance().addStringLocalization("itemGroup.Aquaculture", "Aquaculture");
		
		AquacultureItems.init();
		AquacultureItems.addNames();

		LanguageRegistry.instance().addStringLocalization("enchantment.longCast", "Longcast");
		LanguageRegistry.instance().addStringLocalization("enchantment.shortCast", "Shortcast");
		LanguageRegistry.instance().addStringLocalization("enchantment.doubleHook", "Double Hook");
		LanguageRegistry.instance().addStringLocalization("enchantment.barbedHook", "Barbed Hook");
		LanguageRegistry.instance().addStringLocalization("enchantment.appealing", "Appealing");
		LanguageRegistry.instance().addStringLocalization("enchantment.magnetic", "Magnetic");
		LanguageRegistry.instance().addStringLocalization("enchantment.heavyLine", "Heavy Line");
		//LanguageRegistry.reloadLanguageTable();
	}

	@EventHandler
	public void load(FMLInitializationEvent event)
	{
		
		AquacultureRecipes.addRecipes();

		EntityRegistry.registerGlobalEntityID(EntityCustomFishHook.class, "CustomFishHook", 174);
		//EntityRegistry.registerModEntity(EntityCustomFishHook.class, "CustomFishHook", 0, this, 128, 1, true);
        AquacultureEnchants.init();
		
		tab.setItemID(AquacultureItems.ironFishingRod.itemID);
		
		proxy.registerModelRenderers();
	}
}

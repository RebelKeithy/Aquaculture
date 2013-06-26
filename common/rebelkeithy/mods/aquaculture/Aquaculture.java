package rebelkeithy.mods.aquaculture;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.EntityRegistry;
import rebelkeithy.mods.aquaculture.enchantments.AquacultureEnchants;


@Mod(modid = Aquaculture.MOD_ID, name = Aquaculture.MOD_NAME, version = Aquaculture.MOD_VERSION, dependencies = "required-after:Forge@[7.7.2.682,)")
@NetworkMod(clientSideRequired = true, serverSideRequired = true)
public class Aquaculture 
{
	public final static String MOD_ID = "Aquaculture";
	public final static String MOD_NAME = "Aquaculture";
	public final static String MOD_VERSION = "1.2.0";

	@Instance(MOD_ID)
	public static Aquaculture instance;

	@SidedProxy(clientSide = "rebelkeithy.mods.aquaculture.ClientProxy", serverSide = "rebelkeithy.mods.aquaculture.CommonProxy")
	public static CommonProxy proxy;
	public static AquacultureTab tab;

	@PreInit
	public void preInit(FMLPreInitializationEvent event)
	{
		Config.init(event.getSuggestedConfigurationFile());
		tab = new AquacultureTab("Aquaculture");
	}

	@Init
	public void load(FMLInitializationEvent event)
	{
		AquacultureItems.init();
		AquacultureItems.addNames();
		
		AquacultureRecipes.addRecipes();

        AquacultureEnchants.init();

        EntityRegistry.registerModEntity(EntityCustomFishHook.class, "AquacultureFishHook", 0, this, 64, 1, false);

        tab.setItemID(AquacultureItems.IronFishingRod.itemID);

        proxy.registerModelRenderers();
	}
}

package rebelkeithy.mods.aquaculture;

import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.src.ModLoader;
import net.minecraftforge.common.EnumHelper;
import net.minecraftforge.oredict.OreDictionary;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class AquacultureItems 
{
	public static Item IronFishingRod;
	public static Item AdminFishingRod;
	
	public static Item WhaleSteak;
	public static Item CookedWhaleSteak;
	public static Item FishFillet;
	public static Item CookedFillet;
	public static Item WhaleBurger;

	public static Item Algae;
	public static Item Seaweed;
	public static Item Driftwood;
	public static Item TinCan;
	public static Item NeptuniumBar;

	public static Item MessageInABottle;
	public static Item Box;
	public static Item Lockbox;
	public static Item TreasureChest;
	
	public static Item NeptuniumHelmet;
	public static Item NeptuniumPlate;
	public static Item NeptuniumLegs;
	public static Item NeptuniumBoots;
	public static Item NeptuniumPickaxe;
	public static Item NeptuniumShovel;
	public static Item NeptuniumSword;
	public static Item NeptuniumAxe;
	public static Item NeptuniumHoe;
	
	public static ItemFish fish;
	
	public static void init()
	{
		IronFishingRod = new ItemAquacultureFishingRod(Config.rodID, 75).setUnlocalizedName("Aquaculture:IronFishingRod").setCreativeTab(Aquaculture.tab);
		AdminFishingRod = new ItemAdminFishingRod(Config.adminRodID, 75).setUnlocalizedName("Aquaculture:AdminFishingRod").setCreativeTab(Aquaculture.tab);
		
		Seaweed = new ItemFood(Config.seaweedID, 1, 0, false).setUnlocalizedName("Aquaculture:Seaweed").setCreativeTab(Aquaculture.tab);
		Algae = new ItemFood(Config.algaeID, 1, 0, false).setUnlocalizedName("Aquaculture:Algae").setCreativeTab(Aquaculture.tab);

		//Food
		WhaleSteak = new Item(Config.whaleSteakID).setUnlocalizedName("Aquaculture:RawWhaleSteak").setCreativeTab(Aquaculture.tab);
		FishFillet = new Item(Config.fishFilletID).setUnlocalizedName("Aquaculture:RawFishFillet").setCreativeTab(Aquaculture.tab);
		CookedFillet = new ItemFood(Config.cookedFilletID, 5, 0.6F, false).setUnlocalizedName("Aquaculture:CookedFishFillet").setCreativeTab(Aquaculture.tab);
		CookedWhaleSteak = new ItemFood(Config.cookedWhaleSteakID, 10, 0.8F, false).setUnlocalizedName("Aquaculture:CookedWhaleSteak").setCreativeTab(Aquaculture.tab);
		WhaleBurger = new ItemFood(Config.whaleBurgerID, 20, 0.8F, false).setUnlocalizedName("Aquaculture:Whaleburger").setCreativeTab(Aquaculture.tab);

		Driftwood = new Item(Config.driftwoodID).setUnlocalizedName("Aquaculture:Driftwood").setCreativeTab(Aquaculture.tab);
		NeptuniumBar = new Item(Config.neptuniumIngotID).setUnlocalizedName("Aquaculture:NeptuniumIngot").setCreativeTab(Aquaculture.tab);
		TinCan = new Item(Config.tinCanID).setUnlocalizedName("Aquaculture:TinCan").setCreativeTab(Aquaculture.tab);
		MessageInABottle = new ItemMessageInABottle(Config.bottleID).setUnlocalizedName("Aquaculture:MessageInABottle").setCreativeTab(Aquaculture.tab);
		Box = new ItemBox(Config.boxID).setUnlocalizedName("Aquaculture:Box").setCreativeTab(Aquaculture.tab);
		Lockbox = new ItemLockbox(Config.lockBoxID).setUnlocalizedName("Aquaculture:Lockbox").setCreativeTab(Aquaculture.tab);
		TreasureChest = new ItemTreasureChest(Config.treasureChestID).setUnlocalizedName("Aquaculture:TreasureChest").setCreativeTab(Aquaculture.tab);
		
		EnumToolMaterial neptuniumEnum = EnumHelper.addToolMaterial("Neptunium", 3, 2500, 9F, 4, 15);
		NeptuniumPickaxe = new ItemPickaxe(Config.neptuniumPickaxeID, neptuniumEnum).setUnlocalizedName("Aquaculture:NeptuniumPickaxe").setCreativeTab(Aquaculture.tab);
		NeptuniumShovel = new ItemSpade(Config.neptuniumShovelID, neptuniumEnum).setUnlocalizedName("Aquaculture:NeptuniumShovel").setCreativeTab(Aquaculture.tab);
		NeptuniumAxe = new ItemAxe(Config.neptuniumAxeID, neptuniumEnum).setUnlocalizedName("Aquaculture:NeptuniumAxe").setCreativeTab(Aquaculture.tab);
		NeptuniumHoe = new ItemHoe(Config.neptuniumHoeID, neptuniumEnum).setUnlocalizedName("Aquaculture:NeptuniumHoe").setCreativeTab(Aquaculture.tab);
		NeptuniumSword = new ItemSword(Config.neptuniumSwordID, neptuniumEnum).setUnlocalizedName("Aquaculture:NeptuniumSword").setCreativeTab(Aquaculture.tab);

		EnumArmorMaterial neptuniumArmorEnum = EnumHelper.addArmorMaterial("Neptunium", 75, new int[] {3, 8, 6, 3}, 15);
		NeptuniumHelmet = new NeptuniumArmor(Config.neptuniumHelmetID, neptuniumArmorEnum, 0, 0).setArmorTexture("Neptunium_1").setUnlocalizedName("Aquaculture:NeptuniumHelmet").setCreativeTab(Aquaculture.tab);
		NeptuniumPlate = new NeptuniumArmor(Config.neptuniumPlateID, neptuniumArmorEnum, 1, 1).setArmorTexture("Neptunium_1").setUnlocalizedName("Aquaculture:NeptuniumPlate").setCreativeTab(Aquaculture.tab);
		NeptuniumLegs = new NeptuniumArmor(Config.neptuniumLegsID, neptuniumArmorEnum, 2, 2).setArmorTexture("Neptunium_1").setUnlocalizedName("Aquaculture:NeptuniumLegs").setCreativeTab(Aquaculture.tab);
		NeptuniumBoots = new NeptuniumArmor(Config.neptuniumBootsID, neptuniumArmorEnum, 3, 3).setArmorTexture("Neptunium_1").setUnlocalizedName("Aquaculture:NeptuniumBoots").setCreativeTab(Aquaculture.tab);
		
		fish = (ItemFish) new ItemFish(Config.fishID).setCreativeTab(Aquaculture.tab);
		
		// Freshwater
		fish.addFish("Bluegill", 1);
		fish.addFish("Bass", 1);
		fish.addFish("Perch", 2);
		fish.addFish("Brown Trout", 2);
		fish.addFish("Catfish", 3);
		fish.addFish("Carp", 4);
		fish.addFish("Muskellunge", 4);
		
		// Saltwater
		fish.addFish("Salmon", 2);
		fish.addFish("Tuna", 3);
		fish.addFish("Red Grouper", 4);
		fish.addFish("Swordfish", 5);
		fish.addFish("Shark", 6);
		fish.addFish("Whale", 0);
		fish.addFish("Squid", 0);
		
		// Tundra
		fish.addFish("Cod", 1);
		fish.addFish("Pollock", 1);
		fish.addFish("Herring", 2);
		fish.addFish("Rainbow Trout", 4);
		
		// Desert
		fish.addFish("Capitaine", 2);
		fish.addFish("Boulti", 1);
		fish.addFish("Bagrid", 3);
		fish.addFish("Syndontis", 4);
		
		// Mushroom Island
		fish.addFish("Red Shrooma", 0);
		fish.addFish("Brown Shrooma", 0);
		
		fish.addFish("Goldfish", 0);
		
		OreDictionary.registerOre("listAllfishraw", new ItemStack(FishFillet));
		OreDictionary.registerOre("listAllfishcooked", new ItemStack(CookedFillet));
	}
	
	public static void addNames()
	{
		LanguageRegistry.addName(IronFishingRod, "Iron Fishing Rod");
		LanguageRegistry.addName(AdminFishingRod, "Admin Fishing Rod");
		
		LanguageRegistry.addName(Seaweed, "Seaweed");
		LanguageRegistry.addName(Driftwood, "Driftwood");
		LanguageRegistry.addName(Algae, "Algae");
		LanguageRegistry.addName(TinCan, "Tin Can");
		LanguageRegistry.addName(MessageInABottle, "Message In A Bottle");
		LanguageRegistry.addName(Box, "Box");
		LanguageRegistry.addName(Lockbox, "Lockbox");
		LanguageRegistry.addName(TreasureChest, "Treasure Chest");
		LanguageRegistry.addName(NeptuniumBar, "Neptunium Bar");

		LanguageRegistry.addName(FishFillet, "Raw Fish Fillet");
		LanguageRegistry.addName(CookedFillet, "Cooked Fish Fillet");
		LanguageRegistry.addName(WhaleSteak, "Raw Whale Steak");
		LanguageRegistry.addName(CookedWhaleSteak, "Cooked Whale Steak");
		LanguageRegistry.addName(WhaleBurger, "Whaleburger");

		LanguageRegistry.addName(NeptuniumHelmet, "Neptunium Helmet");
		LanguageRegistry.addName(NeptuniumPlate, "Neptunium Plate");
		LanguageRegistry.addName(NeptuniumLegs, "Neptunium Legs");
		LanguageRegistry.addName(NeptuniumBoots, "Neptunium Boots");
		LanguageRegistry.addName(NeptuniumSword, "Neptunium Sword");
		LanguageRegistry.addName(NeptuniumPickaxe, "Neptunium Pickaxe");
		LanguageRegistry.addName(NeptuniumShovel, "Neptunium Shovel");
		LanguageRegistry.addName(NeptuniumHoe, "Neptunium Hoe");
		LanguageRegistry.addName(NeptuniumAxe, "Neptunium Axe");
	}
}

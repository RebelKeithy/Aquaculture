package rebelkeithy.mods.aquaculture;

import java.lang.reflect.Field;
import java.util.Map;

import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StringTranslate;
import net.minecraftforge.common.EnumHelper;
import net.minecraftforge.oredict.OreDictionary;
import rebelkeithy.mods.aquaculture.items.AquaItemAxe;
import rebelkeithy.mods.aquaculture.items.AquaItemHoe;
import rebelkeithy.mods.aquaculture.items.AquaItemPickaxe;
import rebelkeithy.mods.aquaculture.items.AquaItemSpade;
import rebelkeithy.mods.aquaculture.items.AquaItemSword;
import rebelkeithy.mods.aquaculture.items.ItemAdminFishingRod;
import rebelkeithy.mods.aquaculture.items.ItemAquacultureFishingRod;
import rebelkeithy.mods.aquaculture.items.ItemBox;
import rebelkeithy.mods.aquaculture.items.ItemFish;
import rebelkeithy.mods.aquaculture.items.ItemLockbox;
import rebelkeithy.mods.aquaculture.items.ItemMessageInABottle;
import rebelkeithy.mods.aquaculture.items.ItemNeptunesBounty;
import rebelkeithy.mods.aquaculture.items.ItemTreasureChest;
import rebelkeithy.mods.aquaculture.items.NeptuniumArmor;
import rebelkeithy.mods.keithyutils.metaitem.SubItem;
import rebelkeithy.mods.keithyutils.metaitem.SubItemFood;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.relauncher.ReflectionHelper;

public class AquacultureItems 
{
	public static Item ironFishingRod;
	public static Item goldFishingRod;
	public static Item diamondFishingRod;
	public static Item adminFishingRod;
	
	public static SubItem whaleSteak;
	public static SubItem cookedWhaleSteak;
	public static SubItem fishFillet;
	public static SubItem cookedFillet;
	public static SubItem whaleBurger;
	public static SubItem frogLegs;
	public static SubItem cookedFrogLegs;
	public static SubItem turtleSoup;
	public static SubItem sushi;

	public static SubItem algae;
	public static SubItem seaweed;
	public static SubItem driftwood;
	public static SubItem tinCan;
	public static SubItem neptuniumBar;

	public static SubItem nessageInABottle;
	public static SubItem box;
	public static SubItem lockbox;
	public static SubItem treasureChest;
	public static SubItem neptunesBounty;
	
	public static Item neptuniumHelmet;
	public static Item neptuniumPlate;
	public static Item neptuniumLegs;
	public static Item neptuniumBoots;
	public static Item neptuniumPickaxe;
	public static Item neptuniumShovel;
	public static Item neptuniumSword;
	public static Item neptuniumAxe;
	public static Item neptuniumHoe;
	
	public static ItemFish fish;
	
	public static void init()
	{
		Item item = new Item(0) {{func_111206_d("texture name");}};
		System.out.println();
		ironFishingRod = new ItemAquacultureFishingRod(Config.ironRodID, 75, 14, "Iron").setUnlocalizedName("IronFishingRod").setCreativeTab(Aquaculture.tab);
		goldFishingRod = new ItemAquacultureFishingRod(Config.goldRodID, 50, 22, "Gold").setUnlocalizedName("GoldFishingRod").setCreativeTab(Aquaculture.tab);
		diamondFishingRod = new ItemAquacultureFishingRod(Config.diamondRodID, 250, 10, "Diamond").setUnlocalizedName("DiamondFishingRod").setCreativeTab(Aquaculture.tab);
		adminFishingRod = new ItemAdminFishingRod(Config.adminRodID, 75).setTextureName("AdminFishingRod").setUnlocalizedName("AdminFishingRod").setCreativeTab(Aquaculture.tab);
		
		seaweed = new SubItemFood(Config.lootID, 2, 0, false).setUnlocalizedName("Seaweed").setCreativeTab(Aquaculture.tab);
		algae = new SubItemFood(Config.lootID, 2, 0, false).setUnlocalizedName("Algae").setCreativeTab(Aquaculture.tab);

		//Food
		whaleSteak = new SubItem(Config.lootID).setUnlocalizedName("RawWhaleSteak").setCreativeTab(Aquaculture.tab);
		fishFillet = new SubItem(Config.lootID).setUnlocalizedName("RawFishFillet").setCreativeTab(Aquaculture.tab);
		cookedFillet = new SubItemFood(Config.lootID, 5, 0.6F, false).setUnlocalizedName("CookedFishFillet").setCreativeTab(Aquaculture.tab);
		cookedWhaleSteak = new SubItemFood(Config.lootID, 10, 0.8F, false).setUnlocalizedName("CookedWhaleSteak").setCreativeTab(Aquaculture.tab);
		whaleBurger = new SubItemFood(Config.lootID, 20, 0.8F, false).setUnlocalizedName("Whaleburger").setCreativeTab(Aquaculture.tab);
		frogLegs = new SubItem(Config.lootID).setUnlocalizedName("FrogLegs").setCreativeTab(Aquaculture.tab);
		cookedFrogLegs = new SubItemFood(Config.lootID, 3, 0.6F, false).setEatTime(2).setUnlocalizedName("CookedFrogLegs").setCreativeTab(Aquaculture.tab);
		turtleSoup = new SubItemFood(Config.lootID, 6, 0.6F, false).setUnlocalizedName("TurtleSoup").setCreativeTab(Aquaculture.tab);
		sushi = new SubItemFood(Config.lootID, 4, 0.6F, false).setUnlocalizedName("Sushi").setCreativeTab(Aquaculture.tab);
		
		driftwood = new SubItem(Config.lootID).setUnlocalizedName("Driftwood").setCreativeTab(Aquaculture.tab);
		neptuniumBar = new SubItem(Config.lootID).setUnlocalizedName("NeptuniumIngot").setCreativeTab(Aquaculture.tab);
		tinCan = new SubItem(Config.lootID).setUnlocalizedName("TinCan").setCreativeTab(Aquaculture.tab);
		nessageInABottle = new ItemMessageInABottle(Config.lootID).setUnlocalizedName("MessageInABottle").setCreativeTab(Aquaculture.tab);
		box = new ItemBox(Config.lootID).setUnlocalizedName("Box").setCreativeTab(Aquaculture.tab);
		lockbox = new ItemLockbox(Config.lootID).setUnlocalizedName("Lockbox").setCreativeTab(Aquaculture.tab);
		treasureChest = new ItemTreasureChest(Config.lootID).setUnlocalizedName("TreasureChest").setCreativeTab(Aquaculture.tab);
		neptunesBounty = new ItemNeptunesBounty(Config.lootID).setUnlocalizedName("NeptunesBounty").setCreativeTab(Aquaculture.tab);
		
		EnumToolMaterial neptuniumEnum = EnumHelper.addToolMaterial("Neptunium", 3, 2500, 9F, 6F, 15);
		//EnumToolMaterial neptuniumEnum = EnumToolMaterial.EMERALD;
		neptuniumPickaxe = new AquaItemPickaxe(Config.neptuniumPickaxeID, neptuniumEnum).setUnlocalizedName("NeptuniumPickaxe").setCreativeTab(Aquaculture.tab);
		neptuniumShovel = new AquaItemSpade(Config.neptuniumShovelID, neptuniumEnum).setUnlocalizedName("NeptuniumShovel").setCreativeTab(Aquaculture.tab);
		neptuniumAxe = new AquaItemAxe(Config.neptuniumAxeID, neptuniumEnum).setUnlocalizedName("NeptuniumAxe").setCreativeTab(Aquaculture.tab);
		neptuniumHoe = new AquaItemHoe(Config.neptuniumHoeID, neptuniumEnum).setUnlocalizedName("NeptuniumHoe").setCreativeTab(Aquaculture.tab);
		neptuniumSword = new AquaItemSword(Config.neptuniumSwordID, neptuniumEnum).setUnlocalizedName("NeptuniumSword").setCreativeTab(Aquaculture.tab);

		EnumArmorMaterial neptuniumArmorEnum = EnumHelper.addArmorMaterial("Neptunium", 75, new int[] {3, 8, 6, 3}, 15);
		neptuniumHelmet = new NeptuniumArmor(Config.neptuniumHelmetID, neptuniumArmorEnum, 0, 0).setArmorTexture("Neptunium_1").setUnlocalizedName("NeptuniumHelmet").setCreativeTab(Aquaculture.tab);
		neptuniumPlate = new NeptuniumArmor(Config.neptuniumPlateID, neptuniumArmorEnum, 1, 1).setArmorTexture("Neptunium_1").setUnlocalizedName("NeptuniumPlate").setCreativeTab(Aquaculture.tab);
		neptuniumLegs = new NeptuniumArmor(Config.neptuniumLegsID, neptuniumArmorEnum, 2, 2).setArmorTexture("Neptunium_1").setUnlocalizedName("NeptuniumLegs").setCreativeTab(Aquaculture.tab);
		neptuniumBoots = new NeptuniumArmor(Config.neptuniumBootsID, neptuniumArmorEnum, 3, 3).setArmorTexture("Neptunium_1").setUnlocalizedName("NeptuniumBoots").setCreativeTab(Aquaculture.tab);
		
		fish = (ItemFish) new ItemFish(Config.fishID).setCreativeTab(Aquaculture.tab);
		
		// Freshwater
		fish.addFish("Bluegill", 1, 1, 5, BiomeType.freshwater, 1);
		fish.addFish("Perch", 1, 1, 5, BiomeType.freshwater, 1);
		fish.addFish("Gar", 2, 1, 10, BiomeType.freshwater, 1);
		fish.addFish("Bass", 3, 1, 25, BiomeType.freshwater, 1);
		fish.addFish("Muskellunge", 3, 1, 35, BiomeType.freshwater, 1);
		fish.addFish("Brown Trout", 3, 1, 40, BiomeType.freshwater, 1);
		fish.addFish("Catfish", 4, 1, 50, BiomeType.freshwater, 1);
		fish.addFish("Carp", 5, 1, 100, BiomeType.freshwater, 1);
		
		// Saltwater
		fish.addFish("Blowfish", 3, 1, 25, BiomeType.saltwater, 1);
		fish.addFish("Red Grouper", 4, 1, 50, BiomeType.saltwater, 1);
		fish.addFish("Salmon", 5, 1, 100, BiomeType.saltwater, 1);
		fish.addFish("Tuna", 5, 1, 135, BiomeType.saltwater, 1);
		fish.addFish("Swordfish", 7, 1, 1400, BiomeType.saltwater, 1);
		fish.addFish("Shark", 8, 1, 5000, BiomeType.saltwater, 1);
		fish.addFish("Whale", 0, 1, 190000, BiomeType.saltwater, 1);
		fish.addFish("Squid", 0, 1, 1000, BiomeType.saltwater, 1);
		
		// Brackish
		fish.addFish("Frog", 0, 1, 1, BiomeType.brackish, 1);
		fish.addFish("Turtle", 0, 1, 1, BiomeType.brackish, 1);
		fish.addFish("Leech", 0, 1, 1, BiomeType.brackish, 1);
		
		// Jungle
		fish.addFish("Pirahna", 1, 1, 8, BiomeType.tropical, 1);
		fish.addFish("Electric Eel", 3, 1, 45, BiomeType.tropical, 1);
		fish.addFish("Tambaqui", 4, 1, 75, BiomeType.tropical, 1);
		fish.addFish("Arapaima", 6, 1, 220, BiomeType.tropical, 1);
		
		// Tundra
		fish.addFish("Cod", 6, 1, 210, BiomeType.arctic, 1);
		fish.addFish("Pollock", 3, 1, 45, BiomeType.arctic, 1);
		fish.addFish("Herring", 1, 1, 3, BiomeType.arctic, 1);
		fish.addFish("Halibut", 7, 1, 700, BiomeType.arctic, 1);
		fish.addFish("Pink Salmon", 5, 1, 100, BiomeType.arctic, 1);
		fish.addFish("Rainbow Trout", 4, 1, 50, BiomeType.arctic, 1);
		fish.addFish("Blackfish", 2, 1, 10, BiomeType.arctic, 1);
		
		// Desert
		fish.addFish("Capitaine", 4, 1, 450, BiomeType.arid, 1);
		fish.addFish("Boulti", 2, 1, 10, BiomeType.arid, 1);
		fish.addFish("Bagrid", 3, 1, 25, BiomeType.arid, 1);
		fish.addFish("Syndontis", 1, 1, 3, BiomeType.arid, 1);
		
		// Mushroom Island
		fish.addFish("Red Shrooma", 0, 1, 5, BiomeType.mushroom, 1);
		fish.addFish("Brown Shrooma", 0, 1, 5, BiomeType.mushroom, 1);
		
		fish.addFish("Goldfish", 0, 1, 4, new BiomeType[] {}, 1);
		
		FishLoot.instance().addJunkLoot(algae.getItemStack(), 25);
		FishLoot.instance().addJunkLoot(driftwood.getItemStack(), 25);
		FishLoot.instance().addJunkLoot(tinCan.getItemStack(), 30);
		FishLoot.instance().addJunkLoot(box.getItemStack(), 25);
		FishLoot.instance().addJunkLoot(lockbox.getItemStack(), 23);
		FishLoot.instance().addJunkLoot(treasureChest.getItemStack(), 10);
		FishLoot.instance().addJunkLoot(new ItemStack(Item.stick), 20);
		FishLoot.instance().addJunkLoot(new ItemStack(Item.bone), 20);
		FishLoot.instance().addJunkLoot(new ItemStack(Item.bootsLeather), 20);
		FishLoot.instance().addJunkLoot(new ItemStack(Item.appleRed), 20);
		FishLoot.instance().addJunkLoot(fish.getItemStackFish("Goldfish"), 10);
		FishLoot.instance().addJunkLoot(nessageInABottle.getItemStack(), 23);
		FishLoot.instance().addJunkLoot(neptunesBounty.getItemStack(), 1);
		
		OreDictionary.registerOre("listAllfishraw", fishFillet.getItemStack());
		OreDictionary.registerOre("listAllfishcooked", cookedFillet.getItemStack());
	}
	
	public static void addNames()
	{
		LanguageRegistry.addName(ironFishingRod, "Iron Fishing Rod");
		LanguageRegistry.addName(goldFishingRod, "Gold Fishing Rod");
		LanguageRegistry.addName(diamondFishingRod, "Diamond Fishing Rod");
		LanguageRegistry.addName(adminFishingRod, "Admin Fishing Rod");
		
		LanguageRegistry.addName(seaweed.getItemStack(), "Seaweed");
		LanguageRegistry.addName(driftwood.getItemStack(), "Driftwood");
		LanguageRegistry.addName(algae.getItemStack(), "Algae");
		LanguageRegistry.addName(tinCan.getItemStack(), "Tin Can");
		LanguageRegistry.addName(nessageInABottle.getItemStack(), "Message In A Bottle");
		LanguageRegistry.addName(box.getItemStack(), "Box");
		LanguageRegistry.addName(lockbox.getItemStack(), "Lockbox");
		LanguageRegistry.addName(treasureChest.getItemStack(), "Treasure Chest");
		LanguageRegistry.addName(neptunesBounty.getItemStack(), "Neptune's Bounty");
		LanguageRegistry.addName(neptuniumBar.getItemStack(), "Neptunium Bar");

		LanguageRegistry.addName(fishFillet.getItemStack(), "Raw Fish Fillet");
		LanguageRegistry.addName(cookedFillet.getItemStack(), "Cooked Fish Fillet");
		LanguageRegistry.addName(whaleSteak.getItemStack(), "Raw Whale Steak");
		LanguageRegistry.addName(cookedWhaleSteak.getItemStack(), "Cooked Whale Steak");
		LanguageRegistry.addName(whaleBurger.getItemStack(), "Whaleburger");

		LanguageRegistry.addName(neptuniumHelmet, "Neptunium Helmet");
		LanguageRegistry.addName(neptuniumPlate, "Neptunium Plate");
		LanguageRegistry.addName(neptuniumLegs, "Neptunium Legs");
		LanguageRegistry.addName(neptuniumBoots, "Neptunium Boots");
		LanguageRegistry.addName(neptuniumSword, "Neptunium Sword");
		LanguageRegistry.addName(neptuniumPickaxe, "Neptunium Pickaxe");
		LanguageRegistry.addName(neptuniumShovel, "Neptunium Shovel");
		LanguageRegistry.addName(neptuniumHoe, "Neptunium Hoe");
		LanguageRegistry.addName(neptuniumAxe, "Neptunium Axe");
	}
	
	public static void addName(Item item, String name)
	{
		addName(new ItemStack(item), name);
	}
	
	public static void addName(ItemStack stack, String name)
	{
		String unloc = stack.getItem().getUnlocalizedName(stack) + ".name";
		
		Field ifield;
		try {
			Field[] fields = StringTranslate.class.getDeclaredFields();
			
			ifield = null;
			for(Field f : fields)
			{
				System.out.println(f.getName());
				
				if(f.getName().equals("instance"))
				{
					f.setAccessible(true);
					ifield = f;
				}
			}
			
			//ifield = StringTranslate.class.getField("instance");
			StringTranslate translate = (StringTranslate) ifield.get(null);
			
			Map field = ReflectionHelper.getPrivateValue(StringTranslate.class, translate, "languageList");
			
			System.out.println("nameing " + unloc + " => " + name);
			field.put(unloc, name);
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

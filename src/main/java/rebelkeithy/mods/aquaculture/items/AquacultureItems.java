package rebelkeithy.mods.aquaculture.items;

import net.minecraft.init.Items;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.oredict.OreDictionary;
import rebelkeithy.mods.aquaculture.Aquaculture;
import rebelkeithy.mods.aquaculture.BiomeType;
import rebelkeithy.mods.aquaculture.Config;
import rebelkeithy.mods.aquaculture.FishLoot;
import rebelkeithy.mods.aquaculture.items.meta.MetaItem;
import rebelkeithy.mods.aquaculture.items.meta.SubItem;
import rebelkeithy.mods.aquaculture.items.meta.SubItemFood;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

public enum AquacultureItems {
	INSTANCE;

	public static final Item woodenFishingRod = new ItemAquacultureWoodenFishingRod( 50, 10, "Wood").setUnlocalizedName("fishingRod");
	public static final Item ironFishingRod = new ItemAquacultureFishingRod(75, 14, "Iron").setTextureName("aquaculture:IronFishingRod").setUnlocalizedName("IronFishingRod").setCreativeTab(Aquaculture.tab);
	public static final Item goldFishingRod = new ItemAquacultureFishingRod(50, 22, "Gold").setTextureName("aquaculture:GoldFishingRod").setUnlocalizedName("GoldFishingRod").setCreativeTab(Aquaculture.tab);
	public static final Item diamondFishingRod = new ItemAquacultureFishingRod(250, 10, "Diamond").setTextureName("aquaculture:DiamondFishingRod").setUnlocalizedName("DiamondFishingRod").setCreativeTab(Aquaculture.tab);
	public static final Item adminFishingRod = new ItemAdminFishingRod(75).setTextureName("AdminFishingRod").setUnlocalizedName("AdminFishingRod").setCreativeTab(Aquaculture.tab);

	public static final MetaItem metaLootItem = new MetaItem();
	
	public static final SubItem seaweed = new SubItemFood(metaLootItem, 2, 0, false).setUnlocalizedName("Seaweed").setTextureName("aquaculture:Seaweed").setCreativeTab(Aquaculture.tab);
	public static final SubItem algae = new SubItemFood(metaLootItem, 2, 0, false).setUnlocalizedName("Algae").setTextureName("aquaculture:Algae").setCreativeTab(Aquaculture.tab);

	// Food
	public static final SubItem whaleSteak = new SubItem(metaLootItem).setUnlocalizedName("RawWhaleSteak").setTextureName("aquaculture:RawWhaleSteak").setCreativeTab(Aquaculture.tab);
	public static final SubItem fishFillet = new SubItemFood(metaLootItem, 2, 0.3F, false).setUnlocalizedName("RawFishFillet").setTextureName("aquaculture:RawFishFillet").setCreativeTab(Aquaculture.tab);
	public static final SubItem cookedFillet = new SubItemFood(metaLootItem, 5, 0.6F, false).setUnlocalizedName("CookedFishFillet").setTextureName("aquaculture:CookedFishFillet").setCreativeTab(Aquaculture.tab);
	public static final SubItem cookedWhaleSteak = new SubItemFood(metaLootItem, 10, 0.8F, false).setUnlocalizedName("CookedWhaleSteak").setTextureName("aquaculture:CookedWhaleSteak").setCreativeTab(Aquaculture.tab);
	public static final SubItem whaleBurger = new SubItemFood(metaLootItem, 20, 0.8F, false).setUnlocalizedName("Whaleburger").setTextureName("aquaculture:Whaleburger").setCreativeTab(Aquaculture.tab);
	public static final SubItem frogLegs = new SubItem(metaLootItem).setUnlocalizedName("FrogLegs").setTextureName("aquaculture:FrogLegs").setCreativeTab(Aquaculture.tab);
	public static final SubItem cookedFrogLegs = new SubItemFood(metaLootItem, 3, 0.6F, false).setUnlocalizedName("CookedFrogLegs").setTextureName("aquaculture:CookedFrogLegs").setCreativeTab(Aquaculture.tab);
	public static final SubItem turtleSoup = new SubItemFood(metaLootItem, 6, 0.6F, false).setUnlocalizedName("TurtleSoup").setTextureName("aquaculture:TurtleSoup").setCreativeTab(Aquaculture.tab);
	public static final SubItem sushi = new SubItemFood(metaLootItem, 4, 0.6F, false).setUnlocalizedName("Sushi").setTextureName("aquaculture:Sushi").setCreativeTab(Aquaculture.tab);

	public static final SubItem driftwood = new SubItem(metaLootItem).setUnlocalizedName("Driftwood").setTextureName("aquaculture:Driftwood").setCreativeTab(Aquaculture.tab);
	public static final SubItem neptuniumBar = new SubItem(metaLootItem).setUnlocalizedName("NeptuniumIngot").setTextureName("aquaculture:NeptuniumIngot").setCreativeTab(Aquaculture.tab);
	public static final SubItem tinCan = new SubItem(metaLootItem).setUnlocalizedName("TinCan").setTextureName("aquaculture:TinCan").setCreativeTab(Aquaculture.tab);
	public static final SubItem nessageInABottle = new ItemMessageInABottle(metaLootItem).setUnlocalizedName("MessageInABottle").setTextureName("aquaculture:MessageInABottle").setCreativeTab(Aquaculture.tab);
	public static final SubItem box = new ItemBox(metaLootItem).setUnlocalizedName("Box").setTextureName("aquaculture:Box").setCreativeTab(Aquaculture.tab);
	public static final SubItem lockbox = new ItemLockbox(metaLootItem).setUnlocalizedName("Lockbox").setTextureName("aquaculture:Lockbox").setCreativeTab(Aquaculture.tab);
	public static final SubItem treasureChest = new ItemTreasureChest(metaLootItem).setUnlocalizedName("TreasureChest").setTextureName("aquaculture:TreasureChest").setCreativeTab(Aquaculture.tab);
	public static final SubItem neptunesBounty = new ItemNeptunesBounty(metaLootItem).setUnlocalizedName("NeptunesBounty").setTextureName("aquaculture:NeptunesBounty").setCreativeTab(Aquaculture.tab);

	private static ToolMaterial neptuniumEnum = EnumHelper.addToolMaterial("Neptunium", 3, 2500, 9F, 6F, 15);

	public static final Item neptuniumPickaxe = new AquaItemPickaxe(neptuniumEnum).setUnlocalizedName("NeptuniumPickaxe").setCreativeTab(Aquaculture.tab);
	public static final Item neptuniumShovel = new AquaItemSpade(neptuniumEnum).setUnlocalizedName("NeptuniumShovel").setCreativeTab(Aquaculture.tab);
	public static final Item neptuniumAxe = new AquaItemAxe(neptuniumEnum).setUnlocalizedName("NeptuniumAxe").setCreativeTab(Aquaculture.tab);
	public static final Item neptuniumHoe = new AquaItemHoe(neptuniumEnum).setUnlocalizedName("NeptuniumHoe").setCreativeTab(Aquaculture.tab);
	public static final Item neptuniumSword = new AquaItemSword(neptuniumEnum).setUnlocalizedName("NeptuniumSword").setCreativeTab(Aquaculture.tab);

	private static ArmorMaterial neptuniumArmorEnum = EnumHelper.addArmorMaterial("Neptunium", 75, new int[]{3, 8, 6, 3}, 15);

	public static final Item neptuniumHelmet = new NeptuniumArmor(neptuniumArmorEnum, 0, 0).setArmorTexture("Neptunium_1").setUnlocalizedName("NeptuniumHelmet").setCreativeTab(Aquaculture.tab);
	public static final Item neptuniumPlate = new NeptuniumArmor(neptuniumArmorEnum, 1, 1).setArmorTexture("Neptunium_1").setUnlocalizedName("NeptuniumPlate").setCreativeTab(Aquaculture.tab);
	public static final Item neptuniumLegs = new NeptuniumArmor(neptuniumArmorEnum, 2, 2).setArmorTexture("Neptunium_2").setUnlocalizedName("NeptuniumLegs").setCreativeTab(Aquaculture.tab);
	public static final Item neptuniumBoots = new NeptuniumArmor(neptuniumArmorEnum, 3, 3).setArmorTexture("Neptunium_1").setUnlocalizedName("NeptuniumBoots").setCreativeTab(Aquaculture.tab);

	public static final ItemFish fish = (ItemFish) new ItemFish().setUnlocalizedName("Fish").setCreativeTab(Aquaculture.tab);

	public void register() {
		register(woodenFishingRod);
		register(ironFishingRod);
		register(goldFishingRod);
		register(diamondFishingRod);
		register(adminFishingRod);

		register(seaweed);
		//TODO: fix registration
		/*register(algae);

		// Food
		register(whaleSteak);
		register(fishFillet);
		register(cookedFillet);
		register(cookedWhaleSteak);
		register(whaleBurger);
		register(frogLegs);
		register(cookedFrogLegs);
		register(turtleSoup);
		register(sushi);

		register(driftwood);
		register(neptuniumBar);
		register(tinCan);
		register(nessageInABottle);
		register(box);
		register(lockbox);
		register(treasureChest);
		register(neptunesBounty); */

		register(neptuniumPickaxe);
		register(neptuniumShovel);
		register(neptuniumAxe);
		register(neptuniumHoe);
		register(neptuniumSword);

		register(neptuniumHelmet);
		register(neptuniumPlate);
		register(neptuniumLegs);
		register(neptuniumBoots);

		register(fish);

		// Adds fish to

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
		fish.addFish("Jellyfish", 0, 1, 500, BiomeType.saltwater, 1);

		// Brackish
		fish.addFish("Frog", 0, 1, 1, BiomeType.brackish, 1);
		fish.addFish("Turtle", 0, 1, 5, BiomeType.brackish, 1);
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

		fish.addFish("Goldfish", 0, 1, 4, new BiomeType[]{}, 1);
		fish.addFish("Fish Bones", 0, 1, 1, new BiomeType[]{}, 1);

		FishLoot.instance().addJunkLoot(seaweed.getItemStack(), BiomeType.saltwater, 25);
		FishLoot.instance().addJunkLoot(algae.getItemStack(), new BiomeType[]{BiomeType.arctic, BiomeType.arid, BiomeType.brackish, BiomeType.freshwater, BiomeType.mushroom, BiomeType.tropical}, 25);
		FishLoot.instance().addJunkLoot(driftwood.getItemStack(), 25);
		FishLoot.instance().addJunkLoot(tinCan.getItemStack(), 30);
		FishLoot.instance().addJunkLoot(box.getItemStack(), 25);
		FishLoot.instance().addJunkLoot(lockbox.getItemStack(), 23);
		FishLoot.instance().addJunkLoot(treasureChest.getItemStack(), 10);
		FishLoot.instance().addJunkLoot(new ItemStack(Items.stick), 20);
		FishLoot.instance().addJunkLoot(new ItemStack(Items.bone), 20);
		FishLoot.instance().addJunkLoot(new ItemStack(Items.leather_boots), 20);
		FishLoot.instance().addJunkLoot(new ItemStack(Items.apple), 20);
		FishLoot.instance().addJunkLoot(fish.getItemStackFish("Goldfish"), 10);
		FishLoot.instance().addJunkLoot(nessageInABottle.getItemStack(), 23);
		FishLoot.instance().addJunkLoot(neptunesBounty.getItemStack(), 1);

		OreDictionary.registerOre("listAllfishraw", fishFillet.getItemStack());
		OreDictionary.registerOre("listAllfishcooked", cookedFillet.getItemStack());
		OreDictionary.registerOre("cropSeaweed", seaweed.getItemStack());
	}

	public void register(Item item) {
		GameRegistry.registerItem(item, item.getUnlocalizedName());
		this.name(item, item.getUnlocalizedName());
	}

	public void register(SubItem item) {
		GameRegistry.registerItem(item.item, item.unlocalizedName);
		this.name(item.item, item.unlocalizedName);
	}

	private void name(Item item, String tag) {
		if(!tag.contains("item.")) {
			tag = "item." + tag;
		}
		//LanguageRegistry.addName(item, LocalizationHelper.localize(tag + ".name"));
	}
}

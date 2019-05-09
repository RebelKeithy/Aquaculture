package com.teammetallurgy.aquaculture.init;

import com.teammetallurgy.aquaculture.Aquaculture;
import com.teammetallurgy.aquaculture.items.*;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.*;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Locale;

@Mod.EventBusSubscriber(modid = Aquaculture.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class AquaItems {
    public static final Item WOODEN_FISHING_ROD = new ItemAquacultureFishingRod(ItemTier.WOOD, new Item.Properties().defaultMaxDamage(60).group(Aquaculture.TAB));
    public static final Item IRON_FISHING_ROD = new ItemAquacultureFishingRod(ItemTier.IRON, new Item.Properties().defaultMaxDamage(75).group(Aquaculture.TAB));
    public static final Item GOLD_FISHING_ROD = new ItemAquacultureFishingRod(ItemTier.GOLD, new Item.Properties().defaultMaxDamage(50).group(Aquaculture.TAB));
    public static final Item DIAMOND_FISHING_ROD = new ItemAquacultureFishingRod(ItemTier.DIAMOND, new Item.Properties().defaultMaxDamage(250).group(Aquaculture.TAB));
    public static final Item ADMIN_FISHING_ROD = new ItemFishingRod(new Item.Properties().defaultMaxDamage(75).maxStackSize(1).group(Aquaculture.TAB));

    /*public static final Item SEAWEED = new ItemFood(2, 0, false, new Item.Properties().group(Aquaculture.TAB));
    public static final Item ALGAE = new ItemFood(2, 0, false, new Item.Properties().group(Aquaculture.TAB));

    // Food
    public static final Item WHALE_STEAK = new ItemFood(2, 0.3F, false, new Item.Properties().group(Aquaculture.TAB));
    public static final Item FISH_FILLET = new ItemFood(2, 0.3F, false, new Item.Properties().group(Aquaculture.TAB));
    public static final Item COOKED_FILLET = new ItemFood(5, 0.6F, true, new Item.Properties().group(Aquaculture.TAB));
    public static final Item COOKED_WHALE_STEAK = new ItemFood(10, 0.8F, true, new Item.Properties().group(Aquaculture.TAB));
    public static final Item WHALE_BURGER = new ItemFood(20, 0.8F, true, new Item.Properties().group(Aquaculture.TAB));
    public static final Item FROG_LEGS = new ItemFood(2, 0.3F, false, new Item.Properties().group(Aquaculture.TAB));
    public static final Item COOKED_FROG_LEGS = new ItemFood(3, 0.6F, true, new Item.Properties().group(Aquaculture.TAB));
    public static final Item TURTLE_SOUP = new ItemFood(6, 0.6F, false, new Item.Properties().group(Aquaculture.TAB));
    public static final Item SUSHI = new ItemFood(4, 0.6F, false, new Item.Properties().group(Aquaculture.TAB));

    public static final Item DRIFTWOOD = new Item(new Item.Properties().group(Aquaculture.TAB));
    public static final Item NEPTUNIUM_BAR = new Item(new Item.Properties().group(Aquaculture.TAB));
    public static final Item TIN_CAN = new Item(new Item.Properties().group(Aquaculture.TAB));
    public static final Item NESSAGE_IN_ABOTTLE = new ItemMessageInABottle(new Item.Properties().group(Aquaculture.TAB));
    public static final Item BOX = new ItemBox(new Item.Properties().group(Aquaculture.TAB));
    public static final Item LOCKBOX = new ItemLockbox(new Item.Properties().group(Aquaculture.TAB));
    public static final Item TREASURE_CHEST = new ItemTreasureChest(new Item.Properties().group(Aquaculture.TAB));
    public static final Item NEPTUNES_BOUNTY = new ItemNeptunesBounty(new Item.Properties().group(Aquaculture.TAB));*/
    public static final Item FISH = new ItemFish(new Item.Properties().defaultMaxDamage(0).group(Aquaculture.TAB));
    private static ItemTier NEPTUNIUM_ENUM = null /*EnumHelper.addToolMaterial("Neptunium", 3, 2500, 9F, 6F, 15)*/;
    public static final Item NEPTUNIUM_PICKAXE = new AquaItemPickaxe(NEPTUNIUM_ENUM, 1, -2.8F, new Item.Properties().group(Aquaculture.TAB));
    public static final Item NEPTUNIUM_SHOVEL = new ItemSpade(NEPTUNIUM_ENUM, 1.5F, -3.0F, new Item.Properties().group(Aquaculture.TAB));
    public static final Item NEPTUNIUM_AXE = new AquaItemAxe(NEPTUNIUM_ENUM, 8.0F, -3.0F, new Item.Properties().group(Aquaculture.TAB));
    public static final Item NEPTUNIUM_HOE = new ItemHoe(NEPTUNIUM_ENUM, 0.0F, new Item.Properties().group(Aquaculture.TAB));
    public static final Item NEPTUNIUM_SWORD = new ItemSword(NEPTUNIUM_ENUM, 3, -2.4F, new Item.Properties().group(Aquaculture.TAB));
    private static ArmorMaterial NEPTUNIUM_ARMOR_ENUM = null /*EnumHelper.addArmorMaterial("Neptunium", "", 75, new int[]{3, 6, 8, 3}, 15, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.0F)*/;
    public static final Item NEPTUNIUM_HELMET = new NeptuniumArmor(NEPTUNIUM_ARMOR_ENUM, EntityEquipmentSlot.HEAD, new Item.Properties().group(Aquaculture.TAB)).setArmorTexture("neptunium_layer_1");
    public static final Item NEPTUNIUM_PLATE = new NeptuniumArmor(NEPTUNIUM_ARMOR_ENUM, EntityEquipmentSlot.CHEST, new Item.Properties().group(Aquaculture.TAB)).setArmorTexture("neptunium_layer_1");
    public static final Item NEPTUNIUM_LEGS = new NeptuniumArmor(NEPTUNIUM_ARMOR_ENUM, EntityEquipmentSlot.LEGS, new Item.Properties().group(Aquaculture.TAB)).setArmorTexture("neptunium_layer_2");
    public static final Item NEPTUNIUM_BOOTS = new NeptuniumArmor(NEPTUNIUM_ARMOR_ENUM, EntityEquipmentSlot.FEET, new Item.Properties().group(Aquaculture.TAB)).setArmorTexture("neptunium_layer_1");

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        register(WOODEN_FISHING_ROD);
        register(IRON_FISHING_ROD);
        register(GOLD_FISHING_ROD);
        register(DIAMOND_FISHING_ROD);
        register(ADMIN_FISHING_ROD);

        register(NEPTUNIUM_PICKAXE);
        register(NEPTUNIUM_SHOVEL);
        register(NEPTUNIUM_AXE);
        register(NEPTUNIUM_HOE);
        register(NEPTUNIUM_SWORD);
        register(NEPTUNIUM_HELMET);
        register(NEPTUNIUM_PLATE);
        register(NEPTUNIUM_LEGS);
        register(NEPTUNIUM_BOOTS);

        register(FISH);
    }

    public void register() {
        // Adding fish to main fish item

        // Freshwater
        /*FISH.addFish("Bluegill", 1, 1, 5, BiomeType.freshwater, Config.fishRarity.get("Bluegill"));
        FISH.addFish("Perch", 1, 1, 5, BiomeType.freshwater, Config.fishRarity.get("Perch"));
        FISH.addFish("Gar", 2, 1, 10, BiomeType.freshwater, Config.fishRarity.get("Gar"));
        FISH.addFish("Bass", 3, 1, 25, BiomeType.freshwater, Config.fishRarity.get("Bass"));
        FISH.addFish("Muskellunge", 3, 1, 35, BiomeType.freshwater, Config.fishRarity.get("Muskellunge"));
        FISH.addFish("Brown Trout", 3, 1, 40, BiomeType.freshwater, Config.fishRarity.get("Brown Trout"));
        FISH.addFish("Catfish", 4, 1, 50, BiomeType.freshwater, Config.fishRarity.get("Catfish"));
        FISH.addFish("Carp", 5, 1, 100, BiomeType.freshwater, Config.fishRarity.get("Carp"));

        // Saltwater
        FISH.addFish("Blowfish", 3, 1, 25, BiomeType.saltwater, Config.fishRarity.get("Blowfish"));
        FISH.addFish("Red Grouper", 4, 1, 50, BiomeType.saltwater, Config.fishRarity.get("Red Grouper"));
        FISH.addFish("Salmon", 5, 1, 100, BiomeType.saltwater, Config.fishRarity.get("Salmon"));
        FISH.addFish("Tuna", 5, 1, 135, BiomeType.saltwater, Config.fishRarity.get("Tuna"));
        FISH.addFish("Swordfish", 7, 1, 1400, BiomeType.saltwater, Config.fishRarity.get("Swordfish"));
        FISH.addFish("Shark", 8, 1, 5000, BiomeType.saltwater, Config.fishRarity.get("Shark"));
        FISH.addFish("Whale", 0, 1, 190000, BiomeType.saltwater, Config.fishRarity.get("Whale"));
        FISH.addFish("Squid", 0, 1, 1000, BiomeType.saltwater, Config.fishRarity.get("Squid"));
        FISH.addFish("Jellyfish", 0, 1, 500, BiomeType.saltwater, Config.fishRarity.get("Jellyfish"));

        // Brackish
        FISH.addFish("Frog", 0, 1, 1, BiomeType.brackish, Config.fishRarity.get("Frog"));
        FISH.addFish("Turtle", 0, 1, 5, BiomeType.brackish, Config.fishRarity.get("Turtle"));
        FISH.addFish("Leech", 0, 1, 1, BiomeType.brackish, Config.fishRarity.get("Leech"));

        // Topical
        FISH.addFish("Pirahna", 1, 1, 8, BiomeType.tropical, Config.fishRarity.get("Pirahna"));
        FISH.addFish("Electric Eel", 3, 1, 45, BiomeType.tropical, Config.fishRarity.get("Electric Eel"));
        FISH.addFish("Tambaqui", 4, 1, 75, BiomeType.tropical, Config.fishRarity.get("Tambaqui"));
        FISH.addFish("Arapaima", 6, 1, 220, BiomeType.tropical, Config.fishRarity.get("Arapaima"));

        // Arctic
        FISH.addFish("Cod", 6, 1, 210, BiomeType.arctic, Config.fishRarity.get("Cod"));
        FISH.addFish("Pollock", 3, 1, 45, BiomeType.arctic, Config.fishRarity.get("Pollock"));
        FISH.addFish("Herring", 1, 1, 3, BiomeType.arctic, Config.fishRarity.get("Herring"));
        FISH.addFish("Halibut", 7, 1, 700, BiomeType.arctic, Config.fishRarity.get("Halibut"));
        FISH.addFish("Pink Salmon", 5, 1, 100, BiomeType.arctic, Config.fishRarity.get("Pink Salmon"));
        FISH.addFish("Rainbow Trout", 4, 1, 50, BiomeType.arctic, Config.fishRarity.get("Rainbow Trout"));
        FISH.addFish("Blackfish", 2, 1, 10, BiomeType.arctic, Config.fishRarity.get("Blackfish"));

        // Arid
        FISH.addFish("Capitaine", 4, 1, 450, BiomeType.arid, Config.fishRarity.get("Capitaine"));
        FISH.addFish("Boulti", 2, 1, 10, BiomeType.arid, Config.fishRarity.get("Boulti"));
        FISH.addFish("Bagrid", 3, 1, 25, BiomeType.arid, Config.fishRarity.get("Bagrid"));
        FISH.addFish("Syndontis", 1, 1, 3, BiomeType.arid, Config.fishRarity.get("Syndontis"));

        // Mushroom
        FISH.addFish("Red Shrooma", 0, 1, 5, BiomeType.mushroom, Config.fishRarity.get("Red Shrooma"));
        FISH.addFish("Brown Shrooma", 0, 1, 5, BiomeType.mushroom, Config.fishRarity.get("Brown Shrooma"));

        FISH.addFish("Goldfish", 0, 1, 4, new BiomeType[]{}, 1);
        FISH.addFish("Fish Bones", 0, 1, 1, new BiomeType[]{}, 1);*/

        /*FishLoot.instance().addJunkLoot(SEAWEED.getItemStack(), BiomeType.saltwater, Config.junkRarity.get("Seaweed"));
        FishLoot.instance().addJunkLoot(ALGAE.getItemStack(), new BiomeType[]{BiomeType.arctic, BiomeType.arid, BiomeType.brackish, BiomeType.freshwater, BiomeType.mushroom, BiomeType.tropical}, Config.junkRarity.get("Algae"));
        FishLoot.instance().addJunkLoot(DRIFTWOOD.getItemStack(), Config.junkRarity.get("Driftwood"));
        FishLoot.instance().addJunkLoot(TIN_CAN.getItemStack(), Config.junkRarity.get("Tin Can"));
        FishLoot.instance().addJunkLoot(BOX.getItemStack(), Config.junkRarity.get("Box"));
        FishLoot.instance().addJunkLoot(LOCKBOX.getItemStack(), Config.junkRarity.get("Lockbox"));
        FishLoot.instance().addJunkLoot(TREASURE_CHEST.getItemStack(), Config.junkRarity.get("Treasure Chest"));
        FishLoot.instance().addJunkLoot(new ItemStack(Items.STICK), Config.junkRarity.get("Stick"));
        FishLoot.instance().addJunkLoot(new ItemStack(Items.BONE), Config.junkRarity.get("Bone"));
        FishLoot.instance().addJunkLoot(new ItemStack(Items.LEATHER_BOOTS), Config.junkRarity.get("Leather Boots"));
        FishLoot.instance().addJunkLoot(new ItemStack(Items.APPLE), Config.junkRarity.get("Apple"));
        FishLoot.instance().addJunkLoot(FISH.getItemStackFish("Goldfish"), Config.junkRarity.get("Goldfish"));
        FishLoot.instance().addJunkLoot(NESSAGE_IN_ABOTTLE.getItemStack(), Config.junkRarity.get("Message In A Bottle"));
        if (Config.enableNeptuniumLoot) {
            FishLoot.instance().addJunkLoot(NEPTUNES_BOUNTY.getItemStack(), Config.junkRarity.get("Neptunes Bounty"));
        }*/ //TODO Move to loot table?

        //OreDictionary.registerOre("listAllfishraw", fishFillet.getItemStack());
        //OreDictionary.registerOre("listAllfishcooked", cookedFillet.getItemStack());
        //OreDictionary.registerOre("cropSeaweed", seaweed.getItemStack());
    }

    public static void register(Item item) {
        String name = item.getTranslationKey();
        name = name.replaceAll("item.", "");
        name = name.replaceAll("([A-Za-z][a-z\\d]+)(?=([A-Z][a-z\\d]+))", "$1_").toLowerCase(Locale.US);
        item.setRegistryName(name);
        ForgeRegistries.ITEMS.register(item);
    }
}
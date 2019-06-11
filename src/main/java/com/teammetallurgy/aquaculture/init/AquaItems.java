package com.teammetallurgy.aquaculture.init;

import com.google.common.collect.Lists;
import com.teammetallurgy.aquaculture.Aquaculture;
import com.teammetallurgy.aquaculture.items.*;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.*;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ObjectHolder;

import javax.annotation.Nonnull;
import java.util.List;

@Mod.EventBusSubscriber(modid = Aquaculture.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
@ObjectHolder(Aquaculture.MOD_ID)
public class AquaItems {
    public static List<Item> ITEMS = Lists.newArrayList();
    public static final Item IRON_FISHING_ROD = register(new AquaFishingRodItem(ItemTier.IRON, new Item.Properties().defaultMaxDamage(75).group(Aquaculture.TAB)), "iron_fishing_rod");
    public static final Item GOLD_FISHING_ROD = register(new AquaFishingRodItem(ItemTier.GOLD, new Item.Properties().defaultMaxDamage(50).group(Aquaculture.TAB)), "gold_fishing_rod");
    public static final Item DIAMOND_FISHING_ROD = register(new AquaFishingRodItem(ItemTier.DIAMOND, new Item.Properties().defaultMaxDamage(250).group(Aquaculture.TAB)), "diamond_fishing_rod");
    public static final Item ADMIN_FISHING_ROD = register(new AquaFishingRodItem(null, new Item.Properties().defaultMaxDamage(75).group(Aquaculture.TAB)), "admin_fishing_rod");

    // Neptunium
    public static final Item NEPTUNIUM_INGOT = register(new Item(new Item.Properties().group(Aquaculture.TAB)), "neptunium_ingot");

    public static final Item NEPTUNIUM_PICKAXE = register(new AquaItemPickaxe(AquaEnums.NEPTUNIUM_TOOL, 1, -2.8F, new Item.Properties().group(Aquaculture.TAB)), "neptunium_pickaxe");
    public static final Item NEPTUNIUM_SHOVEL = register(new ShovelItem(AquaEnums.NEPTUNIUM_TOOL, 1.5F, -3.0F, new Item.Properties().group(Aquaculture.TAB)), "neptunium_shovel");
    public static final Item NEPTUNIUM_AXE = register(new AquaAxeItem(AquaEnums.NEPTUNIUM_TOOL, 8.0F, -3.0F, new Item.Properties().group(Aquaculture.TAB)), "neptunium_axe");
    public static final Item NEPTUNIUM_HOE = register(new HoeItem(AquaEnums.NEPTUNIUM_TOOL, 0.0F, new Item.Properties().group(Aquaculture.TAB)), "neptunium_hoe");
    public static final Item NEPTUNIUM_SWORD = register(new SwordItem(AquaEnums.NEPTUNIUM_TOOL, 3, -2.4F, new Item.Properties().group(Aquaculture.TAB)), "neptunium_sword");

    public static final Item NEPTUNIUM_HELMET = register(new NeptuniumArmor(AquaEnums.NEPTINIUM_ARMOR, EquipmentSlotType.HEAD, new Item.Properties().group(Aquaculture.TAB)).setArmorTexture("neptunium_layer_1"), "neptunium_helmet");
    public static final Item NEPTUNIUM_PLATE = register(new NeptuniumArmor(AquaEnums.NEPTINIUM_ARMOR, EquipmentSlotType.CHEST, new Item.Properties().group(Aquaculture.TAB)).setArmorTexture("neptunium_layer_1"), "neptunium_chestplate");
    public static final Item NEPTUNIUM_LEGS = register(new NeptuniumArmor(AquaEnums.NEPTINIUM_ARMOR, EquipmentSlotType.LEGS, new Item.Properties().group(Aquaculture.TAB)).setArmorTexture("neptunium_layer_2"), "neptunium_leggings");
    public static final Item NEPTUNIUM_BOOTS = register(new NeptuniumArmor(AquaEnums.NEPTINIUM_ARMOR, EquipmentSlotType.FEET, new Item.Properties().group(Aquaculture.TAB)).setArmorTexture("neptunium_layer_1"), "neptunium_boots");

    // Misc
    public static final Item DRIFTWOOD = register(new Item(new Item.Properties().group(Aquaculture.TAB)), "driftwood");
    public static final Item TIN_CAN = register(new Item(new Item.Properties().group(Aquaculture.TAB)), "tin_can");
    public static final Item NESSAGE_IN_A_BOTTLE = register(new ItemMessageInABottle(new Item.Properties().group(Aquaculture.TAB)), "message_in_a_bottle");
    public static final Item BOX = register(new LootBoxItem(new Item.Properties().group(Aquaculture.TAB), AquaLootTables.BOX), "box");
    public static final Item LOCKBOX = register(new LootBoxItem(new Item.Properties().group(Aquaculture.TAB), AquaLootTables.LOCKBOX), "lockbox");
    public static final Item TREASURE_CHEST = register(new LootBoxItem(new Item.Properties().group(Aquaculture.TAB), AquaLootTables.TREASURE_CHEST), "treasure_chest");
    public static final Item NEPTUNES_BOUNTY = register(new LootBoxItem(new Item.Properties().group(Aquaculture.TAB), AquaLootTables.NEPTUNES_BOUNTY), "neptunes_bounty");
    public static final Item SEAWEED = register(new Item(new Item.Properties().group(Aquaculture.TAB).food(AquaFoods.ALGAE)), "seaweed");
    public static final Item ALGAE = register(new Item(new Item.Properties().group(Aquaculture.TAB).food(AquaFoods.ALGAE)), "algae");

    // Food
    public static final Item WHALE_STEAK = register(new Item(new Item.Properties().group(Aquaculture.TAB).food(AquaFoods.FISH_RAW)), "whale_steak_raw");
    public static final Item COOKED_WHALE_STEAK = register(new Item(new Item.Properties().group(Aquaculture.TAB).food(AquaFoods.WHALE_STEAK)), "whale_steak_cooked");
    public static final Item WHALE_BURGER = register(new Item(new Item.Properties().group(Aquaculture.TAB).food(AquaFoods.WHALE_BURGER)), "whale_burger");
    public static final Item FISH_FILLET = register(new Item(new Item.Properties().group(Aquaculture.TAB).food(AquaFoods.FISH_RAW)), "fish_fillet_raw");
    public static final Item COOKED_FILLET = register(new Item(new Item.Properties().group(Aquaculture.TAB).food(AquaFoods.FISH_FILLET)), "fish_fillet_cooked");
    public static final Item FROG_LEGS = register(new Item(new Item.Properties().group(Aquaculture.TAB).food(AquaFoods.FISH_RAW)), "frog_legs_raw");
    public static final Item COOKED_FROG_LEGS = register(new Item(new Item.Properties().group(Aquaculture.TAB).food(AquaFoods.FROG_LEGS)), "frog_legs_cooked");
    public static final Item TURTLE_SOUP = register(new SoupItem(new Item.Properties().group(Aquaculture.TAB).food(Foods.MUSHROOM_STEW)), "turtle_soup");
    public static final Item SUSHI = register(new SoupItem(new Item.Properties().group(Aquaculture.TAB).food(AquaFoods.SUSHI)), "sushi");

    // Fish
    public static final Item BLUEGILL = register(new Item(new Item.Properties().group(Aquaculture.TAB)), "bluegill");
    public static final Item PERCH = register(new Item(new Item.Properties().group(Aquaculture.TAB)), "perch");
    public static final Item GAR = register(new Item(new Item.Properties().group(Aquaculture.TAB)), "gar");
    public static final Item BASS = register(new Item(new Item.Properties().group(Aquaculture.TAB)), "bass");
    public static final Item MUSKELLUNGE = register(new Item(new Item.Properties().group(Aquaculture.TAB)), "muskellunge");
    public static final Item BROWN_TROUT = register(new Item(new Item.Properties().group(Aquaculture.TAB)), "brown_trout");
    public static final Item CATFISH = register(new Item(new Item.Properties().group(Aquaculture.TAB)), "catfish");
    public static final Item CARP = register(new Item(new Item.Properties().group(Aquaculture.TAB)), "carp");
    public static final Item RED_GROUPER = register(new Item(new Item.Properties().group(Aquaculture.TAB)), "red_grouper");
    public static final Item TUNA = register(new Item(new Item.Properties().group(Aquaculture.TAB)), "tuna");
    public static final Item SWORDFISH = register(new Item(new Item.Properties().group(Aquaculture.TAB)), "swordfish");
    public static final Item SHARK = register(new Item(new Item.Properties().group(Aquaculture.TAB)), "shark");
    public static final Item WHALE = register(new Item(new Item.Properties().group(Aquaculture.TAB)), "whale");
    public static final Item SQUID = register(new Item(new Item.Properties().group(Aquaculture.TAB)), "squid");
    public static final Item JELLYFISH = register(new Item(new Item.Properties().group(Aquaculture.TAB)), "jellyfish");
    public static final Item FROG = register(new Item(new Item.Properties().group(Aquaculture.TAB)), "frog");
    public static final Item TURTLE = register(new Item(new Item.Properties().group(Aquaculture.TAB)), "turtle");
    public static final Item LEECH = register(new Item(new Item.Properties().group(Aquaculture.TAB)), "leech");
    public static final Item PIRAHNA = register(new Item(new Item.Properties().group(Aquaculture.TAB)), "pirahna");
    public static final Item ELECTRIC_EEL = register(new Item(new Item.Properties().group(Aquaculture.TAB)), "electric_eel");
    public static final Item TAMBAQUI = register(new Item(new Item.Properties().group(Aquaculture.TAB)), "tambaqui");
    public static final Item ARAPAIMA = register(new Item(new Item.Properties().group(Aquaculture.TAB)), "arapaima");
    public static final Item POLLOCK = register(new Item(new Item.Properties().group(Aquaculture.TAB)), "pollock");
    public static final Item HERRING = register(new Item(new Item.Properties().group(Aquaculture.TAB)), "herring");
    public static final Item HALIBUT = register(new Item(new Item.Properties().group(Aquaculture.TAB)), "halibut");
    public static final Item PINK_SALMON = register(new Item(new Item.Properties().group(Aquaculture.TAB)), "pink_salmon");
    public static final Item RAINBOW_TROUT = register(new Item(new Item.Properties().group(Aquaculture.TAB)), "rainbow_trout");
    public static final Item BLACKFISH = register(new Item(new Item.Properties().group(Aquaculture.TAB)), "blackfish");
    public static final Item CAPITAINE = register(new Item(new Item.Properties().group(Aquaculture.TAB)), "capitaine");
    public static final Item BOULTI = register(new Item(new Item.Properties().group(Aquaculture.TAB)), "boulti");
    public static final Item BAGRID = register(new Item(new Item.Properties().group(Aquaculture.TAB)), "bagrid");
    public static final Item SYNDONTIS = register(new Item(new Item.Properties().group(Aquaculture.TAB)), "syndontis");
    public static final Item RED_SHROOMA = register(new Item(new Item.Properties().group(Aquaculture.TAB)), "red_shrooma");
    public static final Item BROWN_SHROOMA = register(new Item(new Item.Properties().group(Aquaculture.TAB)), "brown_shrooma");
    public static final Item GOLDFISH = register(new Item(new Item.Properties().group(Aquaculture.TAB)), "goldfish");
    public static final Item FISH_BONES = register(new Item(new Item.Properties().group(Aquaculture.TAB)), "fish_bones");

    public static void register() {
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
        FISH.addFish("Red Grouper", 4, 1, 50, BiomeType.saltwater, Config.fishRarity.get("Red Grouper"));
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

    /**
     * Registers an item
     *
     * @param item The item to be registered
     * @param name The name to register the item with
     * @return The Item that was registered
     */
    public static Item register(@Nonnull Item item, @Nonnull String name) {
        item.setRegistryName(new ResourceLocation(Aquaculture.MOD_ID, name));
        ITEMS.add(item);
        return item;
    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        for (Item item : ITEMS) {
            event.getRegistry().register(item);
        }
    }
}
package com.teammetallurgy.aquaculture.init;

import com.google.common.collect.Lists;
import com.teammetallurgy.aquaculture.Aquaculture;
import com.teammetallurgy.aquaculture.api.AquaEnums;
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
    public static final Item BLUEGILL = register(new FishItem(1, 5), "bluegill");
    public static final Item PERCH = register(new FishItem(1, 5), "perch");
    public static final Item GAR = register(new FishItem(1, 10), "gar");
    public static final Item BASS = register(new FishItem(1, 25), "bass");
    public static final Item MUSKELLUNGE = register(new FishItem(1, 35), "muskellunge");
    public static final Item BROWN_TROUT = register(new FishItem(1, 40), "brown_trout");
    public static final Item CATFISH = register(new FishItem(1, 50), "catfish");
    public static final Item CARP = register(new FishItem(1, 100), "carp");
    public static final Item RED_GROUPER = register(new FishItem(1, 50), "red_grouper");
    public static final Item TUNA = register(new FishItem(1, 135), "tuna");
    public static final Item SWORDFISH = register(new FishItem(1, 1400), "swordfish");
    public static final Item SHARK = register(new FishItem(1, 5000), "shark"); //Not sure if should be in FISHES tag
    public static final Item WHALE = register(new FishItem(1, 190000), "whale"); //Not sure if should be in FISHES tag
    public static final Item SQUID = register(new FishItem(1, 1000), "squid"); //Not sure if should be in FISHES tag
    public static final Item JELLYFISH = register(new FishItem(1, 500), "jellyfish"); //Not sure if should be in FISHES
    public static final Item FROG = register(new Item(new Item.Properties().group(Aquaculture.TAB)), "frog");
    public static final Item TURTLE = register(new Item(new Item.Properties().group(Aquaculture.TAB)), "turtle");
    public static final Item LEECH = register(new Item(new Item.Properties().group(Aquaculture.TAB)), "leech");
    public static final Item PIRAHNA = register(new FishItem(1, 8), "pirahna");
    public static final Item ELECTRIC_EEL = register(new FishItem(1, 45), "electric_eel");
    public static final Item TAMBAQUI = register(new FishItem(1, 75), "tambaqui");
    public static final Item ARAPAIMA = register(new FishItem(1, 220), "arapaima");
    public static final Item POLLOCK = register(new FishItem(1, 45), "pollock");
    public static final Item HERRING = register(new FishItem(1, 3), "herring");
    public static final Item HALIBUT = register(new FishItem(1, 700), "halibut");
    public static final Item PINK_SALMON = register(new FishItem(1, 100), "pink_salmon");
    public static final Item RAINBOW_TROUT = register(new FishItem(1, 50), "rainbow_trout");
    public static final Item BLACKFISH = register(new FishItem(1, 10), "blackfish");
    public static final Item CAPITAINE = register(new FishItem(1, 450), "capitaine");
    public static final Item BOULTI = register(new FishItem(1, 10), "boulti");
    public static final Item BAGRID = register(new FishItem(1, 25), "bagrid");
    public static final Item SYNDONTIS = register(new FishItem(1, 3), "syndontis");
    public static final Item RED_SHROOMA = register(new FishItem(1, 5), "red_shrooma");
    public static final Item BROWN_SHROOMA = register(new FishItem(1, 5), "brown_shrooma");
    public static final Item GOLDFISH = register(new FishItem(1, 4), "goldfish");
    public static final Item FISH_BONES = register(new Item(new Item.Properties().group(Aquaculture.TAB)), "fish_bones");

    public static void register() {
        //TODO Add Seaweed and Kelp as junk in OCEAN tags
        //FishLoot.instance().addJunkLoot(ALGAE.getItemStack(), new BiomeType[]{BiomeType.arctic, BiomeType.arid, BiomeType.brackish, BiomeType.freshwater, BiomeType.mushroom, BiomeType.tropical}, Config.junkRarity.get("Algae"));
        //TODO ^ Add to junk when BiomeDictionary tag condition have been made
        /*FishLoot.instance().addJunkLoot(DRIFTWOOD.getItemStack(), Config.junkRarity.get("Driftwood"));
        FishLoot.instance().addJunkLoot(TIN_CAN.getItemStack(), Config.junkRarity.get("Tin Can"));
        FishLoot.instance().addJunkLoot(BOX.getItemStack(), Config.junkRarity.get("Box"));
        FishLoot.instance().addJunkLoot(LOCKBOX.getItemStack(), Config.junkRarity.get("Lockbox"));
        FishLoot.instance().addJunkLoot(TREASURE_CHEST.getItemStack(), Config.junkRarity.get("Treasure Chest"));
        */
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
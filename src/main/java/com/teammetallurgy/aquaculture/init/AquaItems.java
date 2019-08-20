package com.teammetallurgy.aquaculture.init;

import com.google.common.collect.Lists;
import com.teammetallurgy.aquaculture.Aquaculture;
import com.teammetallurgy.aquaculture.api.AquacultureAPI;
import com.teammetallurgy.aquaculture.entity.AquaFishEntity;
import com.teammetallurgy.aquaculture.item.*;
import com.teammetallurgy.aquaculture.item.neptunium.*;
import net.minecraft.entity.EntityType;
import net.minecraft.fluid.Fluids;
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

    //Fishing
    public static final Item IRON_FISHING_ROD = register(new AquaFishingRodItem(ItemTier.IRON, new Item.Properties().defaultMaxDamage(125).group(Aquaculture.GROUP)), "iron_fishing_rod");
    public static final Item GOLD_FISHING_ROD = register(new AquaFishingRodItem(ItemTier.GOLD, new Item.Properties().defaultMaxDamage(55).group(Aquaculture.GROUP)), "gold_fishing_rod");
    public static final Item DIAMOND_FISHING_ROD = register(new AquaFishingRodItem(ItemTier.DIAMOND, new Item.Properties().defaultMaxDamage(450).group(Aquaculture.GROUP)), "diamond_fishing_rod");
    public static final Item NEPTUNIUM_FISHING_ROD = register(new AquaFishingRodItem(AquacultureAPI.MATS.NEPTUNIUM, new Item.Properties().defaultMaxDamage(1000).group(Aquaculture.GROUP)), "neptunium_fishing_rod");
    public static final Item WORM = register(AquacultureAPI.createBait(20, 1, Aquaculture.GROUP), "worm");
    public static final Item FISHING_LINE = register(new FishingLineItem(), "fishing_line");

    // Neptunium
    public static final Item NEPTUNIUM_INGOT = register(new SimpleItem(), "neptunium_ingot");
    public static final Item NEPTUNIUM_PICKAXE = register(new NeptuniumPickaxe(AquacultureAPI.MATS.NEPTUNIUM, 1, -2.8F), "neptunium_pickaxe");
    public static final Item NEPTUNIUM_SHOVEL = register(new NeptuniumShovel(AquacultureAPI.MATS.NEPTUNIUM, 1.5F, -3.0F), "neptunium_shovel");
    public static final Item NEPTUNIUM_AXE = register(new AxeItem(AquacultureAPI.MATS.NEPTUNIUM, 8.0F, -3.0F, new Item.Properties().group(Aquaculture.GROUP)), "neptunium_axe");
    public static final Item NEPTUNIUM_HOE = register(new NeptuniumHoe(AquacultureAPI.MATS.NEPTUNIUM, 0.4F), "neptunium_hoe");
    public static final Item NEPTUNIUM_SWORD = register(new SwordItem(AquacultureAPI.MATS.NEPTUNIUM, 3, -2.4F, new Item.Properties().group(Aquaculture.GROUP)), "neptunium_sword");
    public static final Item NEPTUNIUM_TRIDENT = register(new NeptuniumTrident(), "neptunium_trident");
    public static final Item NEPTUNIUM_BOW = register(new NeptuniumBow(), "neptunium_bow");
    public static final Item NEPTUNIUM_HELMET = register(new NeptuniumArmor(AquacultureAPI.MATS.NEPTINIUM_ARMOR, EquipmentSlotType.HEAD).setArmorTexture("neptunium_layer_1"), "neptunium_helmet");
    public static final Item NEPTUNIUM_PLATE = register(new NeptuniumArmor(AquacultureAPI.MATS.NEPTINIUM_ARMOR, EquipmentSlotType.CHEST).setArmorTexture("neptunium_layer_1"), "neptunium_chestplate");
    public static final Item NEPTUNIUM_LEGS = register(new NeptuniumArmor(AquacultureAPI.MATS.NEPTINIUM_ARMOR, EquipmentSlotType.LEGS).setArmorTexture("neptunium_layer_2"), "neptunium_leggings");
    public static final Item NEPTUNIUM_BOOTS = register(new NeptuniumArmor(AquacultureAPI.MATS.NEPTINIUM_ARMOR, EquipmentSlotType.FEET).setArmorTexture("neptunium_layer_1"), "neptunium_boots");

    // Fillet Knifes
    public static final Item WOOD_FILLET_KNIFE = register(new ItemFilletKnife(ItemTier.WOOD), "wood_fillet_knife");
    public static final Item STONE_FILLET_KNIFE = register(new ItemFilletKnife(ItemTier.STONE), "stone_fillet_knife");
    public static final Item IRON_FILLET_KNIFE = register(new ItemFilletKnife(ItemTier.IRON), "iron_fillet_knife");
    public static final Item GOLD_FILLET_KNIFE = register(new ItemFilletKnife(ItemTier.GOLD), "gold_fillet_knife");
    public static final Item DIAMOND_FILLET_KNIFE = register(new ItemFilletKnife(ItemTier.DIAMOND), "diamond_fillet_knife");
    public static final Item NEPTINIUM_FILLET_KNIFE = register(new ItemFilletKnife(AquacultureAPI.MATS.NEPTUNIUM), "neptunium_fillet_knife");

    // Misc
    public static final Item DRIFTWOOD = register(new SimpleItem(), "driftwood");
    public static final Item TIN_CAN = register(new SimpleItem(), "tin_can");
    public static final Item NESSAGE_IN_A_BOTTLE = register(new ItemMessageInABottle(), "message_in_a_bottle");
    public static final Item BOX = register(new LootBoxItem(AquaLootTables.BOX), "box");
    public static final Item LOCKBOX = register(new LootBoxItem(AquaLootTables.LOCKBOX), "lockbox");
    public static final Item TREASURE_CHEST = register(new LootBoxItem(AquaLootTables.TREASURE_CHEST), "treasure_chest");
    public static final Item ALGAE = register(new Item(new Item.Properties().group(Aquaculture.GROUP).food(AquaFoods.ALGAE)), "algae");
    public static final Item FISH_BONES = register(new SimpleItem(), "fish_bones");

    // Food
    public static final Item FISH_FILLET = register(new Item(new Item.Properties().group(Aquaculture.GROUP).food(AquaFoods.FISH_RAW)), "fish_fillet_raw");
    public static final Item COOKED_FILLET = register(new Item(new Item.Properties().group(Aquaculture.GROUP).food(AquaFoods.FISH_FILLET)), "fish_fillet_cooked");
    public static final Item FROG_LEGS = register(new Item(new Item.Properties().group(Aquaculture.GROUP).food(AquaFoods.FISH_RAW)), "frog_legs_raw");
    public static final Item COOKED_FROG_LEGS = register(new Item(new Item.Properties().group(Aquaculture.GROUP).food(AquaFoods.FROG_LEGS)), "frog_legs_cooked");
    public static final Item TURTLE_SOUP = register(new SoupItem(new Item.Properties().group(Aquaculture.GROUP).food(Foods.MUSHROOM_STEW)), "turtle_soup");
    public static final Item SUSHI = register(new SoupItem(new Item.Properties().group(Aquaculture.GROUP).food(AquaFoods.SUSHI)), "sushi");

    // Fish
    public static final Item ATLANTIC_COD = FishRegistry.register(new SimpleItem(), "atlantic_cod");
    public static final Item BLACKFISH = FishRegistry.register(new SimpleItem(), "blackfish");
    public static final Item PACIFIC_HALIBUT = FishRegistry.register(new SimpleItem(), "pacific_halibut");
    public static final Item ATLANTIC_HALIBUT = FishRegistry.register(new SimpleItem(), "atlantic_halibut");
    public static final Item ATLANTIC_HERRING = FishRegistry.register(new SimpleItem(), "atlantic_herring");
    public static final Item PINK_SALMON = FishRegistry.register(new SimpleItem(), "pink_salmon");
    public static final Item POLLOCK = FishRegistry.register(new SimpleItem(), "pollock");
    public static final Item RAINBOW_TROUT = FishRegistry.register(new SimpleItem(), "rainbow_trout");
    public static final Item BAYAD = FishRegistry.register(new SimpleItem(), "bayad");
    public static final Item BOULTI = FishRegistry.register(new SimpleItem(), "boulti");
    public static final Item CAPITAINE = FishRegistry.register(new SimpleItem(), "capitaine");
    public static final Item SYNODONTIS = FishRegistry.register(new SimpleItem(), "synodontis");
    public static final Item SMALLMOUTH_BASS = FishRegistry.register(new SimpleItem(), "smallmouth_bass");
    public static final Item LARGEMOUTH_BASS = FishRegistry.register(new SimpleItem(), "largemouth_bass");
    public static final Item BLUEGILL = FishRegistry.register(new SimpleItem(), "bluegill");
    public static final Item BROWN_TROUT = FishRegistry.register(new SimpleItem(), "brown_trout");
    public static final Item CARP = FishRegistry.register(new SimpleItem(), "carp");
    public static final Item CATFISH = FishRegistry.register(new SimpleItem(), "catfish");
    public static final Item GAR = FishRegistry.register(new SimpleItem(), "gar");
    public static final Item MINNOW = FishRegistry.register(new SimpleItem(), "minnow");
    public static final Item MUSKELLUNGE = FishRegistry.register(new SimpleItem(), "muskellunge");
    public static final Item PERCH = FishRegistry.register(new SimpleItem(), "perch");
    public static final Item PIKE = FishRegistry.register(new SimpleItem(), "pike");
    public static final Item STURGEON = FishRegistry.register(new SimpleItem(), "sturgeon");
    public static final Item WALLEYE = FishRegistry.register(new SimpleItem(), "walleye");
    public static final Item ARAPAIMA = FishRegistry.register(new SimpleItem(), "arapaima");
    public static final Item ELECTRIC_EEL = register(new SimpleItem(), "electric_eel");
    public static final Item PIRANHA = FishRegistry.register(new SimpleItem(), "piranha");
    public static final Item TAMBAQUI = FishRegistry.register(new SimpleItem(), "tambaqui");
    public static final Item BROWN_SHROOMA = FishRegistry.register(new SimpleItem(), "brown_shrooma");
    public static final Item RED_SHROOMA = FishRegistry.register(new SimpleItem(), "red_shrooma");
    public static final Item ANCHOVY = FishRegistry.register(new SimpleItem(), "anchovy");
    public static final Item ANGLERFISH = FishRegistry.register(new SimpleItem(), "anglerfish");
    public static final Item BONITO = FishRegistry.register(new SimpleItem(), "bonito");
    public static final Item COELACANTH = FishRegistry.register(new SimpleItem(), "coelacanth");
    public static final Item EEL = register(new SimpleItem(), "eel");
    public static final Item EUROPEAN_FLOUNDER = FishRegistry.register(new SimpleItem(), "european_flounder");
    public static final Item BLACK_GROUPER = FishRegistry.register(new SimpleItem(), "black_grouper");
    public static final Item JELLYFISH = register(new SimpleItem(), "jellyfish");
    public static final Item LUNG_FISH = FishRegistry.register(new SimpleItem(), "lung_fish");
    public static final Item MACKEREL = FishRegistry.register(new SimpleItem(), "mackerel");
    public static final Item RABBITFISH = FishRegistry.register(new SimpleItem(), "rabbitfish");
    public static final Item RED_GROUPER = FishRegistry.register(new SimpleItem(), "red_grouper");
    public static final Item YELLOWEYE_ROCKFISH = FishRegistry.register(new SimpleItem(), "yelloweye_rockfish");
    public static final Item SARDINE = FishRegistry.register(new SimpleItem(), "sardine");
    public static final Item SQUID = register(new SimpleItem(), "squid");
    public static final Item SWORDFISH = register(new SimpleItem(), "swordfish");
    public static final Item TARPON = FishRegistry.register(new SimpleItem(), "tarpon");
    public static final Item TUNA = FishRegistry.register(new SimpleItem(), "tuna");
    public static final Item FROG = register(new SimpleItem(), "frog");
    public static final Item LEECH = register(new SimpleItem(), "leech");
    public static final Item TURTLE = register(new SimpleItem(), "turtle");
    public static final Item GOLDFISH = register(new SimpleItem(), "goldfish");

    /**
     * Registers an item
     *
     * @param item The item to be registered
     * @param name The name to register the item with
     * @return The Item that was registered
     */
    public static Item register(@Nonnull Item item, @Nonnull String name) {
        return register(item, new ResourceLocation(Aquaculture.MOD_ID, name));
    }

    public static Item register(@Nonnull Item item, @Nonnull ResourceLocation registryName) {
        item.setRegistryName(registryName);
        ITEMS.add(item);
        return item;
    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        for (Item item : ITEMS) {
            event.getRegistry().register(item);
        }
        for (EntityType fishType : FishRegistry.fishEntities) { //Registers fish buckets
            if (fishType.getRegistryName() != null) {
                Item bucket = new AquaFishBucket(fishType, Fluids.WATER, (new Item.Properties()).maxStackSize(1).group(Aquaculture.GROUP));
                bucket.setRegistryName(fishType.getRegistryName().getPath() + "_bucket");
                event.getRegistry().register(bucket);
                AquaFishEntity.BUCKETS.put(fishType, bucket);
            }
        }
    }
}
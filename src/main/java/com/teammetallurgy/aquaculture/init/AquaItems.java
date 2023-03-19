package com.teammetallurgy.aquaculture.init;

import com.teammetallurgy.aquaculture.Aquaculture;
import com.teammetallurgy.aquaculture.api.AquaArmorMaterials;
import com.teammetallurgy.aquaculture.api.AquacultureAPI;
import com.teammetallurgy.aquaculture.entity.FishType;
import com.teammetallurgy.aquaculture.item.*;
import com.teammetallurgy.aquaculture.item.neptunium.*;
import net.minecraft.world.food.Foods;
import net.minecraft.world.item.*;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Supplier;

public class AquaItems {
    public static final DeferredRegister<Item> ITEM_DEFERRED = DeferredRegister.create(ForgeRegistries.ITEMS, Aquaculture.MOD_ID);
    public static final Collection<RegistryObject<Item>> ITEMS_FOR_TAB_LIST = new ArrayList<>();
    public static final Collection<RegistryObject<Item>> SPAWN_EGGS = new ArrayList<>();

    //Fishing
    public static final RegistryObject<Item> IRON_FISHING_ROD = registerWithTab(() -> new AquaFishingRodItem(Tiers.IRON, new Item.Properties().defaultDurability(125)), "iron_fishing_rod");
    public static final RegistryObject<Item> GOLD_FISHING_ROD = registerWithTab(() -> new AquaFishingRodItem(Tiers.GOLD, new Item.Properties().defaultDurability(55)), "gold_fishing_rod");
    public static final RegistryObject<Item> DIAMOND_FISHING_ROD = registerWithTab(() -> new AquaFishingRodItem(Tiers.DIAMOND, new Item.Properties().defaultDurability(450)), "diamond_fishing_rod");
    public static final RegistryObject<Item> NEPTUNIUM_FISHING_ROD = registerWithTab(() -> new AquaFishingRodItem(AquacultureAPI.MATS.NEPTUNIUM, new Item.Properties().defaultDurability(1000)), "neptunium_fishing_rod");
    public static final RegistryObject<Item> WORM = registerWithTab(() -> AquacultureAPI.createBait(20, 1, Aquaculture.GROUP), "worm");
    public static final RegistryObject<Item> FISHING_LINE = registerWithTab(() -> new DyeableItem(0), "fishing_line");
    public static final RegistryObject<Item> BOBBER = registerWithTab(() -> new DyeableItem(13838890), "bobber");

    // Neptunium
    public static final RegistryObject<Item> NEPTUNIUM_NUGGET = registerWithTab(SimpleItem::new, "neptunium_nugget");
    public static final RegistryObject<Item> NEPTUNIUM_INGOT = registerWithTab(SimpleItem::new, "neptunium_ingot");
    public static final RegistryObject<Item> NEPTUNIUM_PICKAXE = registerWithTab(() -> new NeptuniumPickaxe(AquacultureAPI.MATS.NEPTUNIUM, 1, -2.8F), "neptunium_pickaxe");
    public static final RegistryObject<Item> NEPTUNIUM_SHOVEL = registerWithTab(() -> new NeptuniumShovel(AquacultureAPI.MATS.NEPTUNIUM, 1.5F, -3.0F), "neptunium_shovel");
    public static final RegistryObject<Item> NEPTUNIUM_AXE = registerWithTab(() -> new AxeItem(AquacultureAPI.MATS.NEPTUNIUM, 5.0F, -3.0F, new Item.Properties()), "neptunium_axe");
    public static final RegistryObject<Item> NEPTUNIUM_HOE = registerWithTab(() -> new NeptuniumHoe(AquacultureAPI.MATS.NEPTUNIUM, -3, 0.2F), "neptunium_hoe");
    public static final RegistryObject<Item> NEPTUNIUM_SWORD = registerWithTab(() -> new SwordItem(AquacultureAPI.MATS.NEPTUNIUM, 3, -2.4F, new Item.Properties()), "neptunium_sword");
    public static final RegistryObject<Item> NEPTUNIUM_BOW = registerWithTab(NeptuniumBow::new, "neptunium_bow");
    public static final RegistryObject<Item> NEPTUNIUM_HELMET = registerWithTab(() -> new NeptuniumArmor(AquaArmorMaterials.NEPTUNIUM, ArmorItem.Type.HELMET).setArmorTexture("neptunium_layer_1"), "neptunium_helmet");
    public static final RegistryObject<Item> NEPTUNIUM_PLATE = registerWithTab(() -> new NeptuniumArmor(AquaArmorMaterials.NEPTUNIUM, ArmorItem.Type.CHESTPLATE).setArmorTexture("neptunium_layer_1"), "neptunium_chestplate");
    public static final RegistryObject<Item> NEPTUNIUM_LEGS = registerWithTab(() -> new NeptuniumArmor(AquaArmorMaterials.NEPTUNIUM, ArmorItem.Type.LEGGINGS).setArmorTexture("neptunium_layer_2"), "neptunium_leggings");
    public static final RegistryObject<Item> NEPTUNIUM_BOOTS = registerWithTab(() -> new NeptuniumArmor(AquaArmorMaterials.NEPTUNIUM, ArmorItem.Type.BOOTS).setArmorTexture("neptunium_layer_1"), "neptunium_boots");

    // Fillet Knifes
    public static final RegistryObject<Item> WOODEN_FILLET_KNIFE = registerWithTab(() -> new ItemFilletKnife(Tiers.WOOD), "wooden_fillet_knife");
    public static final RegistryObject<Item> STONE_FILLET_KNIFE = registerWithTab(() -> new ItemFilletKnife(Tiers.STONE), "stone_fillet_knife");
    public static final RegistryObject<Item> IRON_FILLET_KNIFE = registerWithTab(() -> new ItemFilletKnife(Tiers.IRON), "iron_fillet_knife");
    public static final RegistryObject<Item> GOLD_FILLET_KNIFE = registerWithTab(() -> new ItemFilletKnife(Tiers.GOLD), "gold_fillet_knife");
    public static final RegistryObject<Item> DIAMOND_FILLET_KNIFE = registerWithTab(() -> new ItemFilletKnife(Tiers.DIAMOND), "diamond_fillet_knife");
    public static final RegistryObject<Item> NEPTINIUM_FILLET_KNIFE = registerWithTab(() -> new ItemFilletKnife(AquacultureAPI.MATS.NEPTUNIUM), "neptunium_fillet_knife");

    // Misc
    public static final RegistryObject<Item> DRIFTWOOD = registerWithTab(SimpleItem::new, "driftwood");
    public static final RegistryObject<Item> TIN_CAN = registerWithTab(SimpleItem::new, "tin_can");
    public static final RegistryObject<Item> NESSAGE_IN_A_BOTTLE = registerWithTab(ItemMessageInABottle::new, "message_in_a_bottle");
    public static final RegistryObject<Item> BOX = registerWithTab(() -> new LootBoxItem(AquaLootTables.BOX), "box");
    public static final RegistryObject<Item> LOCKBOX = registerWithTab(() -> new LootBoxItem(AquaLootTables.LOCKBOX), "lockbox");
    public static final RegistryObject<Item> TREASURE_CHEST = registerWithTab(() -> new LootBoxItem(AquaLootTables.TREASURE_CHEST), "treasure_chest");
    public static final RegistryObject<Item> ALGAE = registerWithTab(() -> new Item(new Item.Properties().food(AquaFoods.ALGAE)), "algae");
    public static final RegistryObject<Item> FISH_BONES = registerWithTab(SimpleItem::new, "fish_bones");

    // Food
    public static final RegistryObject<Item> FISH_FILLET = registerWithTab(() -> new Item(new Item.Properties().food(AquaFoods.FISH_RAW)), "fish_fillet_raw");
    public static final RegistryObject<Item> COOKED_FILLET = registerWithTab(() -> new Item(new Item.Properties().food(AquaFoods.FISH_FILLET)), "fish_fillet_cooked");
    public static final RegistryObject<Item> TURTLE_SOUP = registerWithTab(() -> new BowlFoodItem(new Item.Properties().stacksTo(1).food(Foods.MUSHROOM_STEW)), "turtle_soup");
    public static final RegistryObject<Item> SUSHI = registerWithTab(() -> new Item(new Item.Properties().food(AquaFoods.SUSHI)), "sushi");

    // Fish
    public static final RegistryObject<Item> ATLANTIC_COD = FishRegistry.register(FishItem::new, "atlantic_cod");
    public static final RegistryObject<Item> BLACKFISH = FishRegistry.register(FishItem::new, "blackfish");
    public static final RegistryObject<Item> PACIFIC_HALIBUT = FishRegistry.register(FishItem::new, "pacific_halibut", FishType.HALIBUT);
    public static final RegistryObject<Item> ATLANTIC_HALIBUT = FishRegistry.register(FishItem::new, "atlantic_halibut", FishType.HALIBUT);
    public static final RegistryObject<Item> ATLANTIC_HERRING = FishRegistry.register(FishItem::new, "atlantic_herring", FishType.SMALL);
    public static final RegistryObject<Item> PINK_SALMON = FishRegistry.register(FishItem::new, "pink_salmon");
    public static final RegistryObject<Item> POLLOCK = FishRegistry.register(FishItem::new, "pollock");
    public static final RegistryObject<Item> RAINBOW_TROUT = FishRegistry.register(FishItem::new, "rainbow_trout");
    public static final RegistryObject<Item> BAYAD = FishRegistry.register(FishItem::new, "bayad", FishType.CATFISH);
    public static final RegistryObject<Item> BOULTI = FishRegistry.register(FishItem::new, "boulti", FishType.SMALL);
    public static final RegistryObject<Item> CAPITAINE = FishRegistry.register(FishItem::new, "capitaine");
    public static final RegistryObject<Item> SYNODONTIS = FishRegistry.register(FishItem::new, "synodontis", FishType.SMALL);
    public static final RegistryObject<Item> SMALLMOUTH_BASS = FishRegistry.register(FishItem::new, "smallmouth_bass");
    public static final RegistryObject<Item> BLUEGILL = FishRegistry.register(FishItem::new, "bluegill", FishType.SMALL);
    public static final RegistryObject<Item> BROWN_TROUT = FishRegistry.register(FishItem::new, "brown_trout");
    public static final RegistryObject<Item> CARP = FishRegistry.register(FishItem::new, "carp", FishType.LARGE);
    public static final RegistryObject<Item> CATFISH = FishRegistry.register(FishItem::new, "catfish", FishType.CATFISH);
    public static final RegistryObject<Item> GAR = FishRegistry.register(FishItem::new, "gar", FishType.LONGNOSE);
    public static final RegistryObject<Item> MINNOW = FishRegistry.register(() -> AquacultureAPI.createBait(50, 1, Aquaculture.GROUP), "minnow", FishType.SMALL);
    public static final RegistryObject<Item> MUSKELLUNGE = FishRegistry.register(FishItem::new, "muskellunge", FishType.LONGNOSE);
    public static final RegistryObject<Item> PERCH = FishRegistry.register(FishItem::new, "perch", FishType.SMALL);
    public static final RegistryObject<Item> ARAPAIMA = FishRegistry.register(FishItem::new, "arapaima", FishType.LONGNOSE);
    public static final RegistryObject<Item> PIRANHA = FishRegistry.register(FishItem::new, "piranha", FishType.SMALL);
    public static final RegistryObject<Item> TAMBAQUI = FishRegistry.register(FishItem::new, "tambaqui", FishType.LARGE);
    public static final RegistryObject<Item> BROWN_SHROOMA = FishRegistry.register(FishItem::new, "brown_shrooma", FishType.SMALL);
    public static final RegistryObject<Item> RED_SHROOMA = FishRegistry.register(FishItem::new, "red_shrooma", FishType.SMALL);
    public static final RegistryObject<Item> JELLYFISH = FishRegistry.register(SimpleItem::new, "jellyfish", FishType.JELLYFISH);
    public static final RegistryObject<Item> RED_GROUPER = FishRegistry.register(FishItem::new, "red_grouper");
    public static final RegistryObject<Item> TUNA = FishRegistry.register(FishItem::new, "tuna", FishType.LARGE);
    public static final RegistryObject<Item> LEECH = registerWithTab(() -> AquacultureAPI.createBait(35, 1, Aquaculture.GROUP), "leech");
    public static final RegistryObject<Item> GOLDFISH = registerWithTab(SimpleItem::new, "goldfish");
    public static final RegistryObject<Item> BOX_TURTLE = registerWithTab(SimpleItem::new, "box_turtle");
    public static final RegistryObject<Item> ARRAU_TURTLE = registerWithTab(SimpleItem::new, "arrau_turtle");
    public static final RegistryObject<Item> STARSHELL_TURTLE = registerWithTab(SimpleItem::new, "starshell_turtle");

    //Fish Mounting
    public static final RegistryObject<Item> OAK_FISH_MOUNT = AquacultureAPI.registerFishMount("oak_fish_mount");
    public static final RegistryObject<Item> SPRUCE_FISH_MOUNT = AquacultureAPI.registerFishMount("spruce_fish_mount");
    public static final RegistryObject<Item> BIRCH_FISH_MOUNT = AquacultureAPI.registerFishMount("birch_fish_mount");
    public static final RegistryObject<Item> JUNGLE_FISH_MOUNT = AquacultureAPI.registerFishMount("jungle_fish_mount");
    public static final RegistryObject<Item> ACACIA_FISH_MOUNT = AquacultureAPI.registerFishMount("acacia_fish_mount");
    public static final RegistryObject<Item> DARK_OAK_FISH_MOUNT = AquacultureAPI.registerFishMount("dark_oak_fish_mount");

    /**
     * Registers an item
     *
     * @param initializer The item initializer
     * @param name        The name to register the item with
     * @return The Item that was registered
     */
    public static RegistryObject<Item> register(@Nonnull Supplier<Item> initializer, @Nonnull String name) {
        return ITEM_DEFERRED.register(name, initializer);
    }

    /**
     * Registers an item & add the item to the Aquaculture creative tab
     *
     * @param initializer The item initializer
     * @param name        The name to register the item with
     * @return The Item that was registered
     */
    public static RegistryObject<Item> registerWithTab(@Nonnull Supplier<Item> initializer, @Nonnull String name) {
        RegistryObject<Item> registryObject = register(initializer, name);
        ITEMS_FOR_TAB_LIST.add(registryObject);
        return registryObject;
    }
}
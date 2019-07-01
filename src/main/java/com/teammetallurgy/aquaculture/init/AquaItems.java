package com.teammetallurgy.aquaculture.init;

import com.google.common.collect.Lists;
import com.teammetallurgy.aquaculture.Aquaculture;
import com.teammetallurgy.aquaculture.api.AquacultureAPI;
import com.teammetallurgy.aquaculture.entity.AquaFishEntity;
import com.teammetallurgy.aquaculture.item.*;
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
    public static final Item IRON_FISHING_ROD = register(new AquaFishingRodItem(ItemTier.IRON, new Item.Properties().defaultMaxDamage(75).group(Aquaculture.TAB)), "iron_fishing_rod");
    public static final Item GOLD_FISHING_ROD = register(new AquaFishingRodItem(ItemTier.GOLD, new Item.Properties().defaultMaxDamage(50).group(Aquaculture.TAB)), "gold_fishing_rod");
    public static final Item DIAMOND_FISHING_ROD = register(new AquaFishingRodItem(ItemTier.DIAMOND, new Item.Properties().defaultMaxDamage(250).group(Aquaculture.TAB)), "diamond_fishing_rod");
    public static final Item ADMIN_FISHING_ROD = register(new AquaFishingRodItem(null, new Item.Properties().defaultMaxDamage(75).group(Aquaculture.TAB)), "admin_fishing_rod");

    // Neptunium
    public static final Item NEPTUNIUM_INGOT = register(new Item(new Item.Properties().group(Aquaculture.TAB)), "neptunium_ingot");

    public static final Item NEPTUNIUM_PICKAXE = register(new AquaItemPickaxe(AquacultureAPI.MATS.NEPTUNIUM, 1, -2.8F, new Item.Properties().group(Aquaculture.TAB)), "neptunium_pickaxe");
    public static final Item NEPTUNIUM_SHOVEL = register(new ShovelItem(AquacultureAPI.MATS.NEPTUNIUM, 1.5F, -3.0F, new Item.Properties().group(Aquaculture.TAB)), "neptunium_shovel");
    public static final Item NEPTUNIUM_AXE = register(new AquaAxeItem(AquacultureAPI.MATS.NEPTUNIUM, 8.0F, -3.0F, new Item.Properties().group(Aquaculture.TAB)), "neptunium_axe");
    public static final Item NEPTUNIUM_HOE = register(new HoeItem(AquacultureAPI.MATS.NEPTUNIUM, 0.0F, new Item.Properties().group(Aquaculture.TAB)), "neptunium_hoe");
    public static final Item NEPTUNIUM_SWORD = register(new SwordItem(AquacultureAPI.MATS.NEPTUNIUM, 3, -2.4F, new Item.Properties().group(Aquaculture.TAB)), "neptunium_sword");

    public static final Item NEPTUNIUM_HELMET = register(new NeptuniumArmor(AquacultureAPI.MATS.NEPTINIUM_ARMOR, EquipmentSlotType.HEAD, new Item.Properties().group(Aquaculture.TAB)).setArmorTexture("neptunium_layer_1"), "neptunium_helmet");
    public static final Item NEPTUNIUM_PLATE = register(new NeptuniumArmor(AquacultureAPI.MATS.NEPTINIUM_ARMOR, EquipmentSlotType.CHEST, new Item.Properties().group(Aquaculture.TAB)).setArmorTexture("neptunium_layer_1"), "neptunium_chestplate");
    public static final Item NEPTUNIUM_LEGS = register(new NeptuniumArmor(AquacultureAPI.MATS.NEPTINIUM_ARMOR, EquipmentSlotType.LEGS, new Item.Properties().group(Aquaculture.TAB)).setArmorTexture("neptunium_layer_2"), "neptunium_leggings");
    public static final Item NEPTUNIUM_BOOTS = register(new NeptuniumArmor(AquacultureAPI.MATS.NEPTINIUM_ARMOR, EquipmentSlotType.FEET, new Item.Properties().group(Aquaculture.TAB)).setArmorTexture("neptunium_layer_1"), "neptunium_boots");

    // Misc
    public static final Item FILLET_KNIFE = register(new Item(new Item.Properties().group(Aquaculture.TAB).defaultMaxDamage(150)), "fillet_knife");
    public static final Item DRIFTWOOD = register(new Item(new Item.Properties().group(Aquaculture.TAB)), "driftwood");
    public static final Item TIN_CAN = register(new Item(new Item.Properties().group(Aquaculture.TAB)), "tin_can");
    public static final Item NESSAGE_IN_A_BOTTLE = register(new ItemMessageInABottle(new Item.Properties().group(Aquaculture.TAB)), "message_in_a_bottle");
    public static final Item BOX = register(new LootBoxItem(new Item.Properties().group(Aquaculture.TAB), AquaLootTables.BOX), "box");
    public static final Item LOCKBOX = register(new LootBoxItem(new Item.Properties().group(Aquaculture.TAB), AquaLootTables.LOCKBOX), "lockbox");
    public static final Item TREASURE_CHEST = register(new LootBoxItem(new Item.Properties().group(Aquaculture.TAB), AquaLootTables.TREASURE_CHEST), "treasure_chest");
    public static final Item NEPTUNES_BOUNTY = register(new LootBoxItem(new Item.Properties().group(Aquaculture.TAB), AquaLootTables.NEPTUNES_BOUNTY), "neptunes_bounty");
    public static final Item ALGAE = register(new Item(new Item.Properties().group(Aquaculture.TAB).food(AquaFoods.ALGAE)), "algae");
    public static final Item FISH_BONES = register(new Item(new Item.Properties().group(Aquaculture.TAB)), "fish_bones");

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
    public static final Item ATLANTIC_COD = FishRegistry.register(new FishItem(), "atlantic_cod");
    public static final Item BLACKFISH = FishRegistry.register(new FishItem(), "blackfish");
    public static final Item PACIFIC_HALIBUT = FishRegistry.register(new FishItem(), "pacific_halibut");
    public static final Item ATLANTIC_HALIBUT = FishRegistry.register(new FishItem(), "atlantic_halibut");
    public static final Item ATLANTIC_HERRING = FishRegistry.register(new FishItem(), "atlantic_herring");
    public static final Item PINK_SALMON = FishRegistry.register(new FishItem(), "pink_salmon");
    public static final Item POLLOCK = FishRegistry.register(new FishItem(), "pollock");
    public static final Item RAINBOW_TROUT = FishRegistry.register(new FishItem(), "rainbow_trout");
    public static final Item BAGRID = FishRegistry.register(new FishItem(), "bagrid");
    public static final Item BOULTI = FishRegistry.register(new FishItem(), "boulti");
    public static final Item CAPITAINE = FishRegistry.register(new FishItem(), "capitaine");
    public static final Item SYNODONTIS = FishRegistry.register(new FishItem(), "synodontis");
    public static final Item BASS = FishRegistry.register(new FishItem(), "bass");
    public static final Item BLUEGILL = FishRegistry.register(new FishItem(), "bluegill");
    public static final Item BROWN_TROUT = FishRegistry.register(new FishItem(), "brown_trout");
    public static final Item CARP = FishRegistry.register(new FishItem(), "carp");
    public static final Item CATFISH = FishRegistry.register(new FishItem(), "catfish");
    public static final Item GAR = FishRegistry.register(new FishItem(), "gar");
    public static final Item MINNOW = FishRegistry.register(new FishItem(), "minnow");
    public static final Item MUSKELLUNGE = FishRegistry.register(new FishItem(), "muskellunge");
    public static final Item PERCH = FishRegistry.register(new FishItem(), "perch");
    public static final Item PIKE = FishRegistry.register(new FishItem(), "pike");
    public static final Item STURGEON = FishRegistry.register(new FishItem(), "sturgeon");
    public static final Item WALLEYE = FishRegistry.register(new FishItem(), "walleye");
    public static final Item ARAPAIMA = FishRegistry.register(new FishItem(), "arapaima");
    public static final Item ELECTRIC_EEL = FishRegistry.register(new FishItem(), "electric_eel");
    public static final Item PIRANHA = FishRegistry.register(new FishItem(), "piranha");
    public static final Item TAMBAQUI = FishRegistry.register(new FishItem(), "tambaqui");
    public static final Item BROWN_SHROOMA = FishRegistry.register(new FishItem(), "brown_shrooma");
    public static final Item RED_SHROOMA = FishRegistry.register(new FishItem(), "red_shrooma");
    public static final Item ANCHOVY = FishRegistry.register(new FishItem(), "anchovy");
    public static final Item ANGLERFISH = FishRegistry.register(new FishItem(), "anglerfish");
    public static final Item BONITO = FishRegistry.register(new FishItem(), "bonito");
    public static final Item COELACANTH = FishRegistry.register(new FishItem(), "coelacanth");
    public static final Item EEL = FishRegistry.register(new FishItem(), "eel");
    public static final Item FLOUNDER = FishRegistry.register(new FishItem(), "flounder");
    public static final Item GROUPER = FishRegistry.register(new FishItem(), "grouper");
    public static final Item JELLYFISH = FishRegistry.register(new FishItem(), "jellyfish");
    public static final Item LUNG_FISH = FishRegistry.register(new FishItem(), "lung_fish");
    public static final Item MACKEREL = FishRegistry.register(new FishItem(), "mackerel");
    public static final Item RABBITFISH = FishRegistry.register(new FishItem(), "rabbitfish");
    public static final Item RED_GROUPER = FishRegistry.register(new FishItem(), "red_grouper");
    public static final Item RED_SNAPPER = FishRegistry.register(new FishItem(), "red_snapper");
    public static final Item SARDINE = FishRegistry.register(new FishItem(), "sardine");
    public static final Item SHARK = register(new FishItem(), "shark");
    public static final Item SQUID = register(new FishItem(), "squid");
    public static final Item SWORDFISH = register(new FishItem(), "swordfish");
    public static final Item TARPON = FishRegistry.register(new FishItem(), "tarpon");
    public static final Item TUNA = FishRegistry.register(new FishItem(), "tuna");
    public static final Item WHALE = register(new FishItem(), "whale");
    public static final Item FROG = register(new Item(new Item.Properties().group(Aquaculture.TAB)), "frog");
    public static final Item LEECH = register(new Item(new Item.Properties().group(Aquaculture.TAB)), "leech");
    public static final Item TURTLE = register(new Item(new Item.Properties().group(Aquaculture.TAB)), "turtle");
    public static final Item GOLDFISH = register(new FishItem(), "goldfish");

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
        for (EntityType fishType : FishRegistry.fishEntities) { //Register Fish buckets
            if (fishType.getRegistryName() != null) {
                Item bucket = new FishBucketItem(fishType, Fluids.WATER, (new Item.Properties()).maxStackSize(1).group(Aquaculture.TAB));
                bucket.setRegistryName(fishType.getRegistryName().getPath() + "_bucket");
                event.getRegistry().register(bucket);
                AquaFishEntity.BUCKETS.put(fishType, bucket);
            }
        }
    }
}
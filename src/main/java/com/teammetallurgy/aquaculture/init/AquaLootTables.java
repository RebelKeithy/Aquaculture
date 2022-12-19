package com.teammetallurgy.aquaculture.init;

import com.teammetallurgy.aquaculture.Aquaculture;
import com.teammetallurgy.aquaculture.misc.AquaConfig;
import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.advancements.critereon.FishingHookPredicate;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.entries.LootPoolEntryContainer;
import net.minecraft.world.level.storage.loot.entries.LootTableReference;
import net.minecraft.world.level.storage.loot.predicates.LootItemEntityPropertyCondition;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.util.ObfuscationReflectionHelper;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

@Mod.EventBusSubscriber(modid = Aquaculture.MOD_ID)
public class AquaLootTables {
    //Boxes
    public static final ResourceLocation BOX = register("box/box");
    public static final ResourceLocation LOCKBOX = register("box/lockbox");
    public static final ResourceLocation TREASURE_CHEST = register("box/treasure_chest");
    public static final ResourceLocation NEPTUNES_BOUNTY = register("box/neptunes_bounty");
    //Fishing
    public static final ResourceLocation FISH = register("gameplay/fishing/fish");
    public static final ResourceLocation JUNK = register("gameplay/fishing/junk");
    public static final ResourceLocation NEPTUNIUM = register("gameplay/fishing/neptunium");
    public static final ResourceLocation LAVA_FISHING = register("gameplay/fishing/lava/fishing");
    public static final ResourceLocation LAVA_FISH = register("gameplay/fishing/lava/fish");
    public static final ResourceLocation LAVA_JUNK = register("gameplay/fishing/lava/junk");
    public static final ResourceLocation LAVA_TREASURE = register("gameplay/fishing/lava/treasure");
    public static final ResourceLocation NETHER_FISHING = register("gameplay/fishing/nether/fishing");
    public static final ResourceLocation NETHER_FISH = register("gameplay/fishing/nether/fish");
    public static final ResourceLocation NETHER_JUNK = register("gameplay/fishing/nether/junk");
    public static final ResourceLocation NETHER_TREASURE = register("gameplay/fishing/nether/treasure");

    private static ResourceLocation register(String path) {
        return BuiltInLootTables.register(new ResourceLocation(Aquaculture.MOD_ID, path));
    }

    @SubscribeEvent
    public static void onLootTableLoad(LootTableLoadEvent event) {
        ResourceLocation name = event.getName();
        if (name.equals(BuiltInLootTables.FISHING)) {
            LootPool pool = event.getTable().getPool("main");
            if (pool != null) {
                addEntry(pool, getInjectEntry(FISH, 85, -1));
                addEntry(pool, getInjectEntry(JUNK, 10, -2));
                if (AquaConfig.NEPTUNIUM_OPTIONS.addNeptunesBountyToLoot.get()) {
                    LootPoolEntryContainer neptuniumEntry = LootTableReference.lootTableReference(NEPTUNIUM).setWeight(1).setQuality(2).when(LootItemEntityPropertyCondition.hasProperties(LootContext.EntityTarget.THIS, EntityPredicate.Builder.entity().subPredicate(FishingHookPredicate.inOpenWater(true)))).build();
                    addEntry(pool, neptuniumEntry);
                }
            }
        }
    }

    private static LootPoolEntryContainer getInjectEntry(ResourceLocation location, int weight, int quality) {
        return LootTableReference.lootTableReference(location).setWeight(weight).setQuality(quality).build();
    }

    private static void addEntry(LootPool pool, LootPoolEntryContainer entry) {
        try {
            Field entries = ObfuscationReflectionHelper.findField(LootPool.class, "f_79023_");
            entries.setAccessible(true);

            LootPoolEntryContainer[] lootPoolEntriesArray = (LootPoolEntryContainer[]) entries.get(pool);
            ArrayList<LootPoolEntryContainer> newLootEntries = new ArrayList<>(List.of(lootPoolEntriesArray));

            if (newLootEntries.stream().anyMatch(e -> e == entry)) {
                throw new RuntimeException("Attempted to add a duplicate entry to pool: " + entry);
            }

            newLootEntries.add(entry);

            LootPoolEntryContainer[] newLootEntriesArray = new LootPoolEntryContainer[newLootEntries.size()];
            newLootEntries.toArray(newLootEntriesArray);
            entries.set(pool, newLootEntriesArray);
        } catch (IllegalAccessException e) {
            Aquaculture.LOG.error("Error occurred when attempting to add a new entry, to the fishing loot table");
            e.printStackTrace();
        }
    }
}
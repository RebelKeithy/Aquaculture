package com.teammetallurgy.aquaculture.init;

import com.teammetallurgy.aquaculture.Aquaculture;
import com.teammetallurgy.aquaculture.misc.AquaConfig;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootEntry;
import net.minecraft.world.storage.loot.LootPool;
import net.minecraft.world.storage.loot.LootTables;
import net.minecraft.world.storage.loot.TableLootEntry;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

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
        return LootTables.register(new ResourceLocation(Aquaculture.MOD_ID, path));
    }

    @SubscribeEvent
    public static void onLootTableLoad(LootTableLoadEvent event) {
        ResourceLocation name = event.getName();
        if (name.equals(LootTables.GAMEPLAY_FISHING)) {
            LootPool pool = event.getTable().getPool("main");
            if (pool != null) {
                addEntry(pool, getInjectEntry(FISH, 85, -1));
                addEntry(pool, getInjectEntry(JUNK, 10, -2));
                if (AquaConfig.NEPTUNIUM_OPTIONS.addNeptunesBountyToLoot.get()) {
                    addEntry(pool, getInjectEntry(NEPTUNIUM, 1, 2));
                }
            }
        }
    }

    private static LootEntry getInjectEntry(ResourceLocation location, int weight, int quality) {
        return TableLootEntry.builder(location).weight(weight).quality(quality).build();
    }

    private static void addEntry(LootPool pool, LootEntry entry) {
        if (pool.lootEntries.stream().anyMatch(e -> e == entry)) {
            throw new RuntimeException("Attempted to add a duplicate entry to pool: " + entry);
        }
        pool.lootEntries.add(entry);
    }
}
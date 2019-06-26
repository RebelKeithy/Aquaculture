package com.teammetallurgy.aquaculture.init;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.teammetallurgy.aquaculture.Aquaculture;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.*;
import net.minecraft.world.storage.loot.conditions.ILootCondition;
import net.minecraft.world.storage.loot.conditions.LootConditionManager;
import net.minecraft.world.storage.loot.functions.ILootFunction;
import net.minecraft.world.storage.loot.functions.LootFunctionManager;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.loading.FMLPaths;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

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
    public static final ResourceLocation TREASURE = register("gameplay/fishing/treasure");

    private static ResourceLocation register(String path) {
        return LootTables.register(new ResourceLocation(Aquaculture.MOD_ID, path));
    }

    @SubscribeEvent
    public static void onLootTableLoad(LootTableLoadEvent event) {
        ResourceLocation name = event.getName();
        if (name.equals(LootTables.GAMEPLAY_FISHING_FISH)) {
            LootPool pool = event.getTable().getPool("main");
            if (pool != null) {
                pool
            }
            event.getTable().getPool("main").builder().addEntry(TableLootEntry.func_216171_a(FISH));
        } else if (name.equals(LootTables.GAMEPLAY_FISHING_TREASURE)) {
            event.getTable().getPool("main").builder().addEntry(TableLootEntry.func_216171_a(TREASURE));
        } else if (name.equals(LootTables.GAMEPLAY_FISHING_JUNK)) {
            event.getTable().getPool("main").builder().addEntry(TableLootEntry.func_216171_a(JUNK));
        }
    }

    private static LootPool getInjectPool(ResourceLocation location) {
        return LootPool.builder().addEntry(TableLootEntry.func_216171_a(location)).build();
    }
}
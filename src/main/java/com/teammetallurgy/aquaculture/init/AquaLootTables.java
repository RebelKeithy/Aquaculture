package com.teammetallurgy.aquaculture.init;

import com.teammetallurgy.aquaculture.Aquaculture;
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
    public static final ResourceLocation TREASURE = register("gameplay/fishing/treasure");

    private static ResourceLocation register(String path) {
        return LootTables.register(new ResourceLocation(Aquaculture.MOD_ID, path));
    }

    @SubscribeEvent
    public static void onLootTableLoad(LootTableLoadEvent event) { //TODO. Not fired for vanilla loot tables atm. Forge issue opened about it #5671
        ResourceLocation name = event.getName();
        System.out.println("LOOT: " + name);

        if (name.equals(LootTables.GAMEPLAY_FISHING_FISH)) {
            System.out.println("FISH");
            event.getTable().addPool(getInjectPool(FISH));
        } else if (name.equals(LootTables.GAMEPLAY_FISHING_TREASURE)) {
            System.out.println("TREASURE");
            event.getTable().addPool(getInjectPool(TREASURE));
        } else if (name.equals(LootTables.GAMEPLAY_FISHING_JUNK)) {
            System.out.println("JUNK");
            event.getTable().addPool(getInjectPool(JUNK));
        }
    }

    private static LootPool getInjectPool(ResourceLocation location) {
        return LootPool.builder().addEntry(getInjectEntry(location.toString(), 1)).build();
    }

    private static LootEntry.Builder getInjectEntry(String name, int weight) {
        ResourceLocation location = new ResourceLocation(Aquaculture.MOD_ID, "inject/" + name);
        return TableLootEntry.func_216171_a(location).weight(weight);
    }
}
package com.teammetallurgy.aquaculture.init;

import com.teammetallurgy.aquaculture.Aquaculture;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootTables;

public class AquaLootTables {
    //Boxes
    public static final ResourceLocation BOX = register("box/box");
    public static final ResourceLocation LOCKBOX = register("box/lockbox");
    public static final ResourceLocation TREASURE_CHEST = register("box/treasure_chest");
    public static final ResourceLocation NEPTUNES_BOUNTY = register("box/neptunes_bounty");

    private static ResourceLocation register(String path) {
        return LootTables.register(new ResourceLocation(Aquaculture.MOD_ID, path));
    }
}
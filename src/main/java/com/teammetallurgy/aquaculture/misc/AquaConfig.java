package com.teammetallurgy.aquaculture.misc;

import net.minecraftforge.common.ForgeConfigSpec;

public class AquaConfig {
    private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final BasicOptions BASIC_OPTIONS = new BasicOptions(BUILDER);

    public static class BasicOptions {
        public ForgeConfigSpec.BooleanValue enableFishSpawning;
        public ForgeConfigSpec.BooleanValue enableNeptuniumItems;
        public ForgeConfigSpec.BooleanValue enableNeptuniumArmor;
        public ForgeConfigSpec.BooleanValue addNeptunesBountyToLoot;
        public ForgeConfigSpec.BooleanValue randomWeight;
        public ForgeConfigSpec.BooleanValue debugMode;

        public BasicOptions(ForgeConfigSpec.Builder builder) {
            builder.push("basic options");
            enableFishSpawning = builder.comment("Enable fish mob spawning? Weight & biomes can be modified in the Aquaculture fish loot table").define("Enable fish spawning?", true);
            enableNeptuniumItems = builder.comment("Enable recipes for Neptunium items?").define("Enable Neptunium items?", true);
            enableNeptuniumArmor = builder.comment("Enable recipes for Neptunium armor?").define("Enable Neptunium armor?", true);
            addNeptunesBountyToLoot = builder.comment("Should Neptune's bounty be added to the treasure loot table?").define("Add Neptune's Bounty as loot?", true);
            randomWeight = builder.comment("Enable weight for fish? Useful for fishing competitions").define("Add weight?", false);
            debugMode = builder.comment("Enable Aquaculture's debug mode").define("Enable debug mode?", false);
            builder.pop();
        }
    }

    public static final ForgeConfigSpec spec = BUILDER.build();
}
package com.teammetallurgy.aquaculture;

import net.minecraftforge.common.ForgeConfigSpec;

public class AquaConfig {
    private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final BasicOptions BASIC_OPTIONS = new BasicOptions(BUILDER);

    public static class BasicOptions {
        public ForgeConfigSpec.BooleanValue enableNeptuniumItems;
        public ForgeConfigSpec.BooleanValue enableNeptuniumArmor;
        public ForgeConfigSpec.BooleanValue addNeptunesBountyToLoot;
        public ForgeConfigSpec.BooleanValue randomWeight;

        public BasicOptions(ForgeConfigSpec.Builder builder) {
            builder.push("basic options");
            enableNeptuniumItems = builder.comment("Enable recipes for Neptunium items?").define("Enable Neptunium items?", true);
            enableNeptuniumArmor = builder.comment("Enable recipes for Neptunium armor?").define("Enable Neptunium armor?", true);
            addNeptunesBountyToLoot = builder.comment("Should Neptune's bounty be added to the treasure loot table?").define("Add Neptune's Bounty as loot?", true);
            randomWeight = builder.comment("Enable weight for fish? Useful for fishing competitions").define("Add weight?", false);
            builder.pop();
        }
    }

    public static final ForgeConfigSpec spec = BUILDER.build();
}
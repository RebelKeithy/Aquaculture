package com.teammetallurgy.aquaculture;

import net.minecraftforge.common.ForgeConfigSpec;

public class AquaConfig {
    private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final BasicOptions BASIC_OPTIONS = new BasicOptions(BUILDER);

    public static class BasicOptions {
        public ForgeConfigSpec.BooleanValue randomWeight;

        public BasicOptions(ForgeConfigSpec.Builder builder) {
            builder.push("basic options");
            randomWeight = builder.comment("Enable weight for fish? Useful for fishing competitions").define("Add weight?", false);
            builder.pop();
        }
    }

    public static final ForgeConfigSpec spec = BUILDER.build();
}
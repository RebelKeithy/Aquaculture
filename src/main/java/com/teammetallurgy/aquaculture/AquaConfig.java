package com.teammetallurgy.aquaculture;

import net.minecraftforge.common.ForgeConfigSpec;

public class AquaConfig {
    private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final BasicOptions BASIC_OPTIONS = new BasicOptions(BUILDER);

    public static class BasicOptions {
        public ForgeConfigSpec.BooleanValue randomWeightAqua;
        public ForgeConfigSpec.BooleanValue randomWeightAll;

        public BasicOptions(ForgeConfigSpec.Builder builder) {
            builder.push("basic options");
            randomWeightAqua = builder.comment("Enable fish weight for all Aquaculture fish?").define("Enable Aquaculture fish weight", false);
            randomWeightAll= builder.comment("Enable fish weight for all fish, that is not from Aquaculture?").define("Enable non-Aquaculture fish weight", false);
            builder.pop();
        }
    }

    public static final ForgeConfigSpec spec = BUILDER.build();
}
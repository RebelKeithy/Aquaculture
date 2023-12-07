package com.teammetallurgy.aquaculture.item.crafting;

import com.mojang.serialization.Codec;
import com.teammetallurgy.aquaculture.Aquaculture;
import com.teammetallurgy.aquaculture.misc.AquaConfig;
import net.neoforged.neoforge.common.conditions.ICondition;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

public class ConditionFactory {
    public static final DeferredRegister<Codec<? extends ICondition>> CONDITION_CODECS = DeferredRegister.create(NeoForgeRegistries.Keys.CONDITION_CODECS, Aquaculture.MOD_ID);
    public static final DeferredHolder<Codec<? extends ICondition>, Codec<NeptuniumItems>> NEPTUNIUM_ITEMS_CHECK = CONDITION_CODECS.register("neptunium_items_enabled", () -> NeptuniumItems.CODEC);
    public static final DeferredHolder<Codec<? extends ICondition>, Codec<NeptuniumArmor>> NEPTUNIUM_ARMOR_CHECK = CONDITION_CODECS.register("neptunium_armor_enabled", () -> NeptuniumArmor.CODEC);


    public static class NeptuniumItems implements ICondition {
        public static NeptuniumItems INSTANCE = new NeptuniumItems();
        public static Codec<NeptuniumItems> CODEC = Codec.unit(INSTANCE).stable();


        @Override
        public boolean test(IContext context) {
            return AquaConfig.NEPTUNIUM_OPTIONS.enableNeptuniumItems.get();
        }

        @Override
        public Codec<? extends ICondition> codec() {
            return CODEC;
        }
    }

    public static class NeptuniumArmor implements ICondition {
        private static final NeptuniumArmor INSTANCE = new NeptuniumArmor();
        public static Codec<NeptuniumArmor> CODEC = Codec.unit(INSTANCE).stable();

        @Override
        public boolean test(IContext context) {
            return AquaConfig.NEPTUNIUM_OPTIONS.enableNeptuniumArmor.get();
        }

        @Override
        public Codec<? extends ICondition> codec() {
            return CODEC;
        }
    }
}
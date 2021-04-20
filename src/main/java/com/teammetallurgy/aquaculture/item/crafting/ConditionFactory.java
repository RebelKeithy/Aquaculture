package com.teammetallurgy.aquaculture.item.crafting;

import com.google.gson.JsonObject;
import com.teammetallurgy.aquaculture.Aquaculture;
import com.teammetallurgy.aquaculture.misc.AquaConfig;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.common.crafting.conditions.ICondition;
import net.minecraftforge.common.crafting.conditions.IConditionSerializer;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Aquaculture.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ConditionFactory {

    @SubscribeEvent
    public static void registerConditions(RegistryEvent.Register<IRecipeSerializer<?>> event) {
        CraftingHelper.register(NeptuniumItems.Serializer.INSTANCE);
        CraftingHelper.register(NeptuniumArmor.Serializer.INSTANCE);
    }

    public static class NeptuniumItems implements ICondition {
        private static final NeptuniumItems INSTANCE = new NeptuniumItems();
        private static final ResourceLocation NAME = new ResourceLocation(Aquaculture.MOD_ID, "neptunium_items_enabled");

        @Override
        public ResourceLocation getID() {
            return NAME;
        }

        @Override
        public boolean test() {
            return AquaConfig.NEPTUNIUM_OPTIONS.enableNeptuniumItems.get();
        }

        public static class Serializer implements IConditionSerializer<NeptuniumItems> {
            private static final Serializer INSTANCE = new Serializer();

            @Override
            public void write(JsonObject json, NeptuniumItems value) {
                json.addProperty("value", value.test());
            }

            @Override
            public NeptuniumItems read(JsonObject json) {
                return NeptuniumItems.INSTANCE;
            }

            @Override
            public ResourceLocation getID() {
                return NeptuniumItems.NAME;
            }
        }
    }

    public static class NeptuniumArmor implements ICondition {
        private static final NeptuniumArmor INSTANCE = new NeptuniumArmor();
        private static final ResourceLocation NAME = new ResourceLocation(Aquaculture.MOD_ID, "neptunium_armor_enabled");

        @Override
        public ResourceLocation getID() {
            return NAME;
        }

        @Override
        public boolean test() {
            return AquaConfig.NEPTUNIUM_OPTIONS.enableNeptuniumArmor.get();
        }

        public static class Serializer implements IConditionSerializer<NeptuniumArmor> {
            private static final Serializer INSTANCE = new Serializer();

            @Override
            public void write(JsonObject json, NeptuniumArmor value) {
                json.addProperty("value", value.test());
            }

            @Override
            public NeptuniumArmor read(JsonObject json) {
                return NeptuniumArmor.INSTANCE;
            }

            @Override
            public ResourceLocation getID() {
                return NeptuniumArmor.NAME;
            }
        }
    }
}
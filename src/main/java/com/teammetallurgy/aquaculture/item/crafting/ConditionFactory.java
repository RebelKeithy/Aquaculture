package com.teammetallurgy.aquaculture.item.crafting;

import com.google.gson.JsonObject;
import com.teammetallurgy.aquaculture.Aquaculture;
import com.teammetallurgy.aquaculture.misc.AquaConfig;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.common.crafting.IConditionSerializer;

import javax.annotation.Nonnull;
import java.util.function.BooleanSupplier;

public class ConditionFactory {

    public static void registerConditions() {
        CraftingHelper.register(new ResourceLocation(Aquaculture.MOD_ID, "neptunium_items_enabled"), new ConditionFactory.NeptuniumItems());
        CraftingHelper.register(new ResourceLocation(Aquaculture.MOD_ID, "neptunium_armor_enabled"), new ConditionFactory.NeptuniumArmor());
    }

    public static class NeptuniumItems implements IConditionSerializer {
        @Override
        @Nonnull
        public BooleanSupplier parse(@Nonnull JsonObject json) {
            boolean value = JSONUtils.getBoolean(json, "value", true);
            return () -> AquaConfig.BASIC_OPTIONS.enableNeptuniumItems.get() == value;
        }
    }

    public static class NeptuniumArmor implements IConditionSerializer {
        @Override
        @Nonnull
        public BooleanSupplier parse(@Nonnull JsonObject json) {
            boolean value = JSONUtils.getBoolean(json, "value", true);
            return () -> AquaConfig.BASIC_OPTIONS.enableNeptuniumArmor.get() == value;
        }
    }
}
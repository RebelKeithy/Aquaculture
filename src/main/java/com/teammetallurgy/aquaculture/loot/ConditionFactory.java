package com.teammetallurgy.aquaculture.loot;

import com.google.gson.JsonObject;
import net.minecraftforge.common.crafting.IConditionSerializer;

import javax.annotation.Nonnull;
import java.util.function.BooleanSupplier;

public class ConditionFactory {

    public static class NeptuniumLoot implements IConditionSerializer {

        @Override
        @Nonnull
        public BooleanSupplier parse(@Nonnull JsonObject json) {
            return null;
        }
    }
}
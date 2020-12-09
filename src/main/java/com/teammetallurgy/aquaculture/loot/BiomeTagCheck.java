package com.teammetallurgy.aquaculture.loot;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.teammetallurgy.aquaculture.Aquaculture;
import net.minecraft.loot.ILootSerializer;
import net.minecraft.loot.LootConditionType;
import net.minecraft.loot.LootContext;
import net.minecraft.loot.LootParameters;
import net.minecraft.loot.conditions.ILootCondition;
import net.minecraft.util.math.vector.Vector3d;

import javax.annotation.Nonnull;

public class BiomeTagCheck implements ILootCondition {
    private final BiomeTagPredicate predicate;

    private BiomeTagCheck(BiomeTagPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public boolean test(LootContext context) {
        Vector3d pos = context.get(LootParameters.field_237457_g_);
        return pos != null && this.predicate.test(context.getWorld(), (float) pos.getX(), (float) pos.getY(), (float) pos.getZ());
    }

    @Override
    @Nonnull
    public LootConditionType func_230419_b_() {
        return Aquaculture.BIOME_TAG_CHECK;
    }

    public static class Serializer implements ILootSerializer<BiomeTagCheck> {

        @Override
        public void serialize(JsonObject json, BiomeTagCheck tagCheck, @Nonnull JsonSerializationContext context) {
            json.add("predicate", tagCheck.predicate.serialize());
        }

        @Override
        @Nonnull
        public BiomeTagCheck deserialize(JsonObject json, @Nonnull JsonDeserializationContext context) {
            return new BiomeTagCheck(BiomeTagPredicate.deserialize(json.get("predicate")));
        }
    }
}
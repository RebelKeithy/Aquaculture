package com.teammetallurgy.aquaculture.loot;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.teammetallurgy.aquaculture.Aquaculture;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.storage.loot.LootContext;
import net.minecraft.world.storage.loot.LootParameters;
import net.minecraft.world.storage.loot.conditions.ILootCondition;

import javax.annotation.Nonnull;

public class BiomeTagCheck implements ILootCondition {
    private final BiomeTagPredicate predicate;

    private BiomeTagCheck(BiomeTagPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public boolean test(LootContext context) {
        BlockPos pos = context.get(LootParameters.POSITION);
        return pos != null && this.predicate.test(context.getWorld(), (float) pos.getX(), (float) pos.getY(), (float) pos.getZ());
    }

    public static class Serializer extends ILootCondition.AbstractSerializer<BiomeTagCheck> {
        public Serializer() {
            super(new ResourceLocation(Aquaculture.MOD_ID, "biome_tag_check"), BiomeTagCheck.class);
        }

        @Override
        public void serialize(JsonObject json, BiomeTagCheck value, @Nonnull JsonSerializationContext context) {
            json.add("predicate", value.predicate.serialize());
        }

        @Override
        @Nonnull
        public BiomeTagCheck deserialize(JsonObject json, @Nonnull JsonDeserializationContext context) {
            BiomeTagPredicate predicate = BiomeTagPredicate.deserialize(json.get("predicate"));
            return new BiomeTagCheck(predicate);
        }
    }
}
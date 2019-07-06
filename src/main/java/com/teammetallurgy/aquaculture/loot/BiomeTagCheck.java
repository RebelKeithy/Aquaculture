package com.teammetallurgy.aquaculture.loot;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.teammetallurgy.aquaculture.Aquaculture;
import com.teammetallurgy.aquaculture.misc.AquaConfig;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.storage.loot.LootContext;
import net.minecraft.world.storage.loot.LootParameters;
import net.minecraft.world.storage.loot.conditions.ILootCondition;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

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
        public void serialize(JsonObject json, BiomeTagCheck tagCheck, @Nonnull JsonSerializationContext context) {
            json.add("predicate", tagCheck.predicate.serialize());
        }

        @Override
        @Nonnull
        public BiomeTagCheck deserialize(JsonObject json, @Nonnull JsonDeserializationContext context) {
            BiomeTagPredicate predicate = BiomeTagPredicate.deserialize(json.get("predicate"));

            if (AquaConfig.BASIC_OPTIONS.debugMode.get()) {
                List<String> strings = new ArrayList<>();
                for (Biome biome : BiomeTagPredicate.getValidBiomes(predicate.include, predicate.exclude, predicate.and)) {
                    if (biome.getRegistryName() != null) {
                        strings.add(biome.getRegistryName().getPath());
                    }
                }
                Aquaculture.LOG.info("Biomes: " + strings);
                Aquaculture.LOG.info(json.get("predicate"));
            }

            return new BiomeTagCheck(predicate);
        }
    }
}
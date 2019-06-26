package com.teammetallurgy.aquaculture.loot;

import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import com.teammetallurgy.aquaculture.utils.BiomeDictionaryHelper;
import net.minecraft.advancements.criterion.MinMaxBounds;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ServerWorld;
import net.minecraftforge.common.BiomeDictionary;

import javax.annotation.Nullable;

public class BiomeTagPredicate {
    private static final BiomeTagPredicate ANY = new BiomeTagPredicate(MinMaxBounds.FloatBound.UNBOUNDED, MinMaxBounds.FloatBound.UNBOUNDED, MinMaxBounds.FloatBound.UNBOUNDED, null);
    private final MinMaxBounds.FloatBound x;
    private final MinMaxBounds.FloatBound y;
    private final MinMaxBounds.FloatBound z;
    private final BiomeDictionary.Type biomeType;

    public BiomeTagPredicate(MinMaxBounds.FloatBound x, MinMaxBounds.FloatBound y, MinMaxBounds.FloatBound z, @Nullable BiomeDictionary.Type biomeType) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.biomeType = biomeType;
    }

    public boolean test(ServerWorld world, float x, float y, float z) {
        if (!this.x.test(x)) {
            return false;
        } else if (!this.y.test(y)) {
            return false;
        } else if (!this.z.test(z)) {
            return false;
        } else {
            BlockPos pos = new BlockPos((double) x, (double) y, (double) z);
            return this.biomeType == null || BiomeDictionary.getBiomes(this.biomeType).contains(world.getBiome(pos)); //TODO Test
        }
    }

    public JsonElement serialize() {
        if (this == ANY) {
            return JsonNull.INSTANCE;
        } else {
            JsonObject object = new JsonObject();
            if (!this.x.isUnbounded() || !this.y.isUnbounded() || !this.z.isUnbounded()) {
                JsonObject posObj = new JsonObject();
                posObj.add("x", this.x.serialize());
                posObj.add("y", this.y.serialize());
                posObj.add("z", this.z.serialize());
                object.add("position", posObj);
            }

            if (this.biomeType != null) {
                object.addProperty("biome_tag", biomeType.toString().toUpperCase());
            }
            return object;
        }
    }

    public static BiomeTagPredicate deserialize(@Nullable JsonElement element) {
        if (element != null && !element.isJsonNull()) {
            JsonObject location = JSONUtils.getJsonObject(element, "location");
            JsonObject position = JSONUtils.getJsonObject(location, "position", new JsonObject());
            MinMaxBounds.FloatBound x = MinMaxBounds.FloatBound.fromJson(position.get("x"));
            MinMaxBounds.FloatBound y = MinMaxBounds.FloatBound.fromJson(position.get("y"));
            MinMaxBounds.FloatBound z = MinMaxBounds.FloatBound.fromJson(position.get("z"));
            BiomeDictionary.Type biomeTag = null;
            if (location.has("biome_tag")) {
                biomeTag = BiomeDictionaryHelper.getType(JSONUtils.getString(location, "biome_tag"));
            }
            return new BiomeTagPredicate(x, y, z, biomeTag);
        } else {
            return ANY;
        }
    }

    public static class Builder {
        private MinMaxBounds.FloatBound x;
        private MinMaxBounds.FloatBound y;
        private MinMaxBounds.FloatBound z;
        private BiomeDictionary.Type biomeType;

        public Builder() {
            this.x = MinMaxBounds.FloatBound.UNBOUNDED;
            this.y = MinMaxBounds.FloatBound.UNBOUNDED;
            this.z = MinMaxBounds.FloatBound.UNBOUNDED;
        }

        public BiomeTagPredicate.Builder biome(@Nullable BiomeDictionary.Type biomeType) {
            this.biomeType = biomeType;
            return this;
        }

        public BiomeTagPredicate build() {
            return new BiomeTagPredicate(this.x, this.y, this.z, this.biomeType);
        }
    }
}
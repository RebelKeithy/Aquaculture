package com.teammetallurgy.aquaculture.loot;

import com.google.common.collect.Lists;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import com.teammetallurgy.aquaculture.Aquaculture;
import com.teammetallurgy.aquaculture.misc.BiomeDictionaryHelper;
import net.minecraft.advancements.criterion.MinMaxBounds;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ServerWorld;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;

import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BiomeTagPredicate {
    private static final BiomeTagPredicate ANY = new BiomeTagPredicate(MinMaxBounds.FloatBound.UNBOUNDED, MinMaxBounds.FloatBound.UNBOUNDED, MinMaxBounds.FloatBound.UNBOUNDED, Lists.newArrayList(), Lists.newArrayList(), false);
    private static final List<BiomeDictionary.Type> INVALID_TAGS = Arrays.asList(BiomeDictionary.Type.NETHER, BiomeDictionary.Type.END, BiomeDictionary.Type.VOID);
    private final MinMaxBounds.FloatBound x;
    private final MinMaxBounds.FloatBound y;
    private final MinMaxBounds.FloatBound z;
    private final List<BiomeDictionary.Type> include;
    private final List<BiomeDictionary.Type> exclude;
    private final boolean and;

    public BiomeTagPredicate(MinMaxBounds.FloatBound x, MinMaxBounds.FloatBound y, MinMaxBounds.FloatBound z, List<BiomeDictionary.Type> include, List<BiomeDictionary.Type> exclude, boolean and) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.include = include;
        this.exclude = exclude;
        this.and = and;
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
            return this.getValidBiomes().contains(world.getBiome(pos));
        }
    }

    public List<Biome> getValidBiomes() {
        List<Biome> biomes = Lists.newArrayList();

        if (this.include.isEmpty() && !this.exclude.isEmpty()) { //Add all BiomeDictionary tags, when only excluding biomes
            Set<BiomeDictionary.Type> validTags = new HashSet<>(BiomeDictionary.Type.getAll());
            this.include.addAll(validTags);
            this.exclude.addAll(INVALID_TAGS);
        }

        if (!this.include.isEmpty()) {
            List<Biome> addBiomes = Lists.newArrayList();
            for (BiomeDictionary.Type type : this.include) {
                addBiomes.addAll(BiomeDictionary.getBiomes(type));
            }

            if (this.and) {
                for (BiomeDictionary.Type type : this.include) {
                    addBiomes.removeIf(biome -> !BiomeDictionary.hasType(biome, type));
                }
            }

            if (this.include.stream().noneMatch(INVALID_TAGS::contains)) { //Exclude invalid tags, as long as they're not specified in include
                this.exclude.addAll(INVALID_TAGS);
            }
            biomes.addAll(addBiomes);
        }
        if (!this.exclude.isEmpty()) {
            for (BiomeDictionary.Type type : this.exclude) {
                biomes.removeAll(BiomeDictionary.getBiomes(type));
            }
        }
        return biomes;
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
            if (this.include != null) {
                for (BiomeDictionary.Type type : this.include) {
                    object.add("include", object.getAsJsonArray(type.toString()));
                }
            }
            if (this.exclude != null) {
                for (BiomeDictionary.Type type : this.exclude) {
                    object.add("exclude", object.getAsJsonArray(type.toString()));
                }
            }
            object.addProperty("add", object.getAsBoolean());
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
            List<BiomeDictionary.Type> include = Lists.newArrayList();
            if (location.has("include")) {
                JsonArray includeArray = JSONUtils.getJsonArray(location, "include");
                for (int entry = 0; entry < includeArray.size(); entry++) {
                    BiomeDictionary.Type type = BiomeDictionaryHelper.getType(includeArray.get(entry).getAsString());
                    if (type == null) {
                        Aquaculture.LOG.error("Failed to include BiomeDictionary Tag. Please check your loot tables");
                    }
                    include.add(type);
                }
            }

            List<BiomeDictionary.Type> exclude = Lists.newArrayList();
            if (location.has("exclude")) {
                JsonArray excludeArray = JSONUtils.getJsonArray(location, "exclude");
                for (int entry = 0; entry < excludeArray.size(); entry++) {
                    BiomeDictionary.Type type = BiomeDictionaryHelper.getType(excludeArray.get(entry).getAsString());
                    if (type == null) {
                        Aquaculture.LOG.error("Failed to exclude BiomeDictionary Tag. Please check your loot tables");
                    }
                    exclude.add(type);
                }
            }

            boolean and = false;
            if (location.has("and")) {
                and = JSONUtils.getBoolean(location, "and");
            }
            return new BiomeTagPredicate(x, y, z, include, exclude, and);
        } else {
            return ANY;
        }
    }
}
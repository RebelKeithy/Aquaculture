package com.teammetallurgy.aquaculture.loot;

import com.google.common.collect.Lists;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import com.teammetallurgy.aquaculture.Aquaculture;
import net.minecraft.advancements.criterion.MinMaxBounds;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.server.ServerWorld;

import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.List;

public class BiomeTagPredicate {
    private static final BiomeTagPredicate ANY = new BiomeTagPredicate(MinMaxBounds.FloatBound.UNBOUNDED, MinMaxBounds.FloatBound.UNBOUNDED, MinMaxBounds.FloatBound.UNBOUNDED, Lists.newArrayList(), Lists.newArrayList(), false);
    private static final List<Biome.Category> INVALID_TAGS = Arrays.asList(Biome.Category.NETHER, Biome.Category.THEEND, Biome.Category.NONE);
    private final MinMaxBounds.FloatBound x;
    private final MinMaxBounds.FloatBound y;
    private final MinMaxBounds.FloatBound z;
    private final List<Biome.Category> include;
    private final List<Biome.Category> exclude;
    public final boolean and;

    public BiomeTagPredicate(MinMaxBounds.FloatBound x, MinMaxBounds.FloatBound y, MinMaxBounds.FloatBound z, List<Biome.Category> include, List<Biome.Category> exclude, boolean and) {
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
            BlockPos pos = new BlockPos(x, y, z);
            return getValidBiomes(this.include, this.exclude, this.and).contains(world.getBiome(pos));
        }
    }

    public static List<Biome> getValidBiomes(List<Biome.Category> includeList, List<Biome.Category> excludeList, boolean and) {
        List<Biome> biomes = Lists.newArrayList();

        /*if (includeList.isEmpty() && !excludeList.isEmpty()) { //Add all BiomeDictionary tags, when only excluding biomes
            Set<BiomeDictionary.Type> validTags = new HashSet<>(BiomeDictionary.Type.getAll());
            includeList.addAll(validTags);
            excludeList.addAll(INVALID_TAGS);
        }

        if (!includeList.isEmpty()) {
            List<Biome> addBiomes = Lists.newArrayList();
            for (BiomeDictionary.Type type : includeList) {
                addBiomes.addAll(BiomeDictionary.getBiomes(type));
            }

            if (and) {
                for (BiomeDictionary.Type type : includeList) {
                    addBiomes.removeIf(biome -> !BiomeDictionary.hasType(biome, type));
                }
            }

            if (includeList.stream().noneMatch(INVALID_TAGS::contains)) { //Exclude invalid tags, as long as they're not specified in include
                excludeList.addAll(INVALID_TAGS);
            }
            for (Biome addBiome : addBiomes) {
                if (!biomes.contains(addBiome)) {
                    biomes.add(addBiome);
                }
            }
        }
        if (!excludeList.isEmpty()) {
            for (BiomeDictionary.Type type : excludeList) {
                biomes.removeAll(BiomeDictionary.getBiomes(type));
            }
        }*/
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
                for (Biome.Category category : this.include) {
                    object.add("include", object.getAsJsonArray(category.getName()));
                }
            }
            if (this.exclude != null) {
                for (Biome.Category category : this.exclude) {
                    object.add("exclude", object.getAsJsonArray(category.getName()));
                }
            }
            object.addProperty("and", object.getAsBoolean());
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
            List<Biome.Category> include = Lists.newArrayList();
            if (location.has("include")) {
                JsonArray includeArray = JSONUtils.getJsonArray(location, "include");
                for (int entry = 0; entry < includeArray.size(); entry++) {
                    Biome.Category category = Biome.Category.byName(includeArray.get(entry).getAsString());
                    if (category == null) {
                        Aquaculture.LOG.error("Failed to include Biome Category. Please check your loot tables");
                    }
                    include.add(category);
                }
            }

            List<Biome.Category> exclude = Lists.newArrayList();
            if (location.has("exclude")) {
                JsonArray excludeArray = JSONUtils.getJsonArray(location, "exclude");
                for (int entry = 0; entry < excludeArray.size(); entry++) {
                    Biome.Category category = Biome.Category.byName(excludeArray.get(entry).getAsString());
                    if (category == null) {
                        Aquaculture.LOG.error("Failed to exclude Biome Category. Please check your loot tables");
                    }
                    exclude.add(category);
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
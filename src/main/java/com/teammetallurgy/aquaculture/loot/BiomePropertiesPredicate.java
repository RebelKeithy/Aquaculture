package com.teammetallurgy.aquaculture.loot;

import com.google.common.collect.Lists;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import com.teammetallurgy.aquaculture.Aquaculture;
import net.minecraft.advancements.criterion.MinMaxBounds;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

public class BiomePropertiesPredicate {
    private static final BiomePropertiesPredicate ANY = new BiomePropertiesPredicate(MinMaxBounds.FloatBound.UNBOUNDED, MinMaxBounds.FloatBound.UNBOUNDED, MinMaxBounds.FloatBound.UNBOUNDED, Lists.newArrayList(), Lists.newArrayList(), Lists.newArrayList(), Lists.newArrayList());
    private static final List<Biome.Category> INVALID_CATEGORIES = Arrays.asList(Biome.Category.NETHER, Biome.Category.THEEND, Biome.Category.NONE);
    private final MinMaxBounds.FloatBound x;
    private final MinMaxBounds.FloatBound y;
    private final MinMaxBounds.FloatBound z;
    private final List<Biome.Category> include;
    private final List<Biome.Category> exclude;
    private final List<TemperatureType> temperatureTypes;
    private final List<Biome.RainType> rainTypes;

    public BiomePropertiesPredicate(MinMaxBounds.FloatBound x, MinMaxBounds.FloatBound y, MinMaxBounds.FloatBound z, List<Biome.Category> include, List<Biome.Category> exclude, List<TemperatureType> temperatureTypes, List<Biome.RainType> rainTypes) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.include = include;
        this.exclude = exclude;
        this.temperatureTypes = temperatureTypes;
        this.rainTypes = rainTypes;
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
            Biome biome = world.getBiome(pos);
            Biome biomeFromRegistry = ForgeRegistries.BIOMES.getValue(world.func_241828_r().getRegistry(Registry.BIOME_KEY).getKey(biome));
            return getValidBiomes(this.include, this.exclude, this.temperatureTypes, this.rainTypes).contains(biomeFromRegistry);
        }
    }

    public static List<Biome> getValidBiomes(List<Biome.Category> includeList, List<Biome.Category> excludeList, List<TemperatureType> temperatureTypes, List<Biome.RainType> rainTypes) { //Can't add biome as a parameter, since this called elsewhere where world is not available
        List<Biome> registryBiomes = Lists.newArrayList(ForgeRegistries.BIOMES.getValues());
        List<Biome> biomes = Lists.newArrayList(registryBiomes);

        if (includeList.isEmpty() && excludeList.isEmpty() && temperatureTypes.isEmpty() && rainTypes.isEmpty()) {
            biomes.clear(); //Don't add biomes, when nothing is specified
        }

        if (includeList.isEmpty() && !excludeList.isEmpty()) { //Add all Biome Categories, when only excluding biomes
            includeList.addAll(Arrays.asList(Biome.Category.values()));
            excludeList.addAll(INVALID_CATEGORIES);
        }

        if (includeList.isEmpty() && excludeList.isEmpty()) { //Exclude invalid Biome Categories, when only temperature and/or rain type is specified
            excludeList.addAll(INVALID_CATEGORIES);
        }

        if (!includeList.isEmpty()) {
            if (includeList.stream().noneMatch(INVALID_CATEGORIES::contains)) { //Exclude invalid tags, as long as they're not specified in include
                excludeList.addAll(INVALID_CATEGORIES);
            }
        }

        if (!includeList.isEmpty()) {
            biomes.removeIf(biome -> !includeList.contains(biome.getCategory()));
        }
        if (!excludeList.isEmpty()) {
            biomes.removeIf(biome -> excludeList.contains(biome.getCategory()));
        }

        if (!temperatureTypes.isEmpty()) {
            biomes.removeIf(biome -> !temperatureTypes.contains(getTemperatureType(biome.getTemperature(), biome.getRegistryName() != null ? biome.getRegistryName().getPath() : "")));
        }

        if (!rainTypes.isEmpty()) {
            biomes.removeIf(biome -> !rainTypes.contains(biome.getPrecipitation()));
        }

        return biomes;
    }

    public static TemperatureType getTemperatureType(float temp, String biomeName) {
        if (temp >= 1.0F || biomeName.contains("hot") || (!biomeName.contains("lukewarm") && biomeName.contains("warm"))) {
            return TemperatureType.HOT;
        } else if (temp < 0.15F || biomeName.contains("cold") || biomeName.contains("frozen")) {
            return TemperatureType.COLD;
        } else if (temp >= 0.15F && temp < 1.0F) {
            return TemperatureType.TEMPERATE;
        } else {
            return TemperatureType.HOT;
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
            if (this.temperatureTypes != null) {
                for (TemperatureType temperatureTypes : this.temperatureTypes) {
                    object.add("temperature", object.getAsJsonArray(temperatureTypes.getName()));
                }
            }
            if (this.rainTypes != null) {
                for (Biome.RainType rainType : this.rainTypes) {
                    object.add("rain_type", object.getAsJsonArray(rainType.getName()));
                }
            }
            return object;
        }
    }

    public static BiomePropertiesPredicate deserialize(@Nullable JsonElement element) {
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
                    String name = includeArray.get(entry).getAsString().toLowerCase(Locale.ROOT);
                    Biome.Category category = Biome.Category.byName(name);
                    if (category == null) {
                        Aquaculture.LOG.error("Failed to include Biome Category: " + name + ". Please check your loot tables");
                    } else {
                        include.add(category);
                    }
                }
            }

            List<Biome.Category> exclude = Lists.newArrayList();
            if (location.has("exclude")) {
                JsonArray excludeArray = JSONUtils.getJsonArray(location, "exclude");
                for (int entry = 0; entry < excludeArray.size(); entry++) {
                    String name = excludeArray.get(entry).getAsString().toLowerCase(Locale.ROOT);
                    Biome.Category category = Biome.Category.byName(name);
                    if (category == null) {
                        Aquaculture.LOG.error("Failed to exclude Biome Category: " + name + ". Please check your loot tables");
                    } else {
                        exclude.add(category);
                    }
                }
            }

            List<TemperatureType> temperatureTypes = Lists.newArrayList();
            if (location.has("temperature")) {
                JsonArray temperatureArray = JSONUtils.getJsonArray(location, "temperature");
                for (int entry = 0; entry < temperatureArray.size(); entry++) {
                    String name = temperatureArray.get(entry).getAsString().toLowerCase(Locale.ROOT);
                    TemperatureType temperatureType = TemperatureType.getTemperatureType(name);
                    if (temperatureType == null) {
                        Aquaculture.LOG.error("Could not find Biome Temperature Type: " + name + ". Please check your loot tables");
                    } else {
                        temperatureTypes.add(temperatureType);
                    }
                }
            }

            List<Biome.RainType> rainTypes = Lists.newArrayList();
            if (location.has("rain_type")) {
                JsonArray rainTypeArray = JSONUtils.getJsonArray(location, "rain_type");
                for (int entry = 0; entry < rainTypeArray.size(); entry++) {
                    String name = rainTypeArray.get(entry).getAsString().toLowerCase(Locale.ROOT);
                    Biome.RainType rainType = Biome.RainType.getRainType(name);
                    if (rainType == null) {
                        Aquaculture.LOG.error("Could not find Biome Rain Type: " + name + ". Please check your loot tables");
                    } else {
                        rainTypes.add(rainType);
                    }
                }
            }
            /*System.out.println("include: " + include);
            System.out.println("exclude: " + exclude);
            System.out.println("temp: " + temperatureTypes);
            System.out.println("rain: " + rainTypes);*/
            return new BiomePropertiesPredicate(x, y, z, include, exclude, temperatureTypes, rainTypes);
        } else {
            return ANY;
        }
    }

    public enum TemperatureType implements IStringSerializable {
        COLD("cold"),
        TEMPERATE("temperate"),
        HOT("hot");

        private static final Map<String, TemperatureType> BY_NAME = Arrays.stream(values()).collect(Collectors.toMap(TemperatureType::getName, (precipitation) -> precipitation));
        private final String name;

        private TemperatureType(String name) {
            this.name = name;
        }

        public String getName() {
            return this.name;
        }

        public static TemperatureType getTemperatureType(String name) {
            return BY_NAME.get(name);
        }

        @Override
        @Nonnull
        public String getString() {
            return this.name;
        }
    }
}
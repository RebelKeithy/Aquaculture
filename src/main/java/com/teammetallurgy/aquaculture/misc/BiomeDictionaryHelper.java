package com.teammetallurgy.aquaculture.misc;

import net.minecraftforge.common.BiomeDictionary;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class BiomeDictionaryHelper {

    /**
     * Retrieves a #BiomeDictionary.Type
     * Based on {@link BiomeDictionary.Type#getType(String, BiomeDictionary.Type...)}, but doesn't create a new {@link BiomeDictionary.Type} if the input is not already a {@link BiomeDictionary.Type}
     *
     * @param name The name of this #BiomeDictionary.Type
     * @return An instance of this #BiomeDictionary.Type
     */
    public static BiomeDictionary.Type getType(String name) {
        Map<String, BiomeDictionary.Type> byName = BiomeDictionary.Type.getAll().stream().collect(Collectors.toMap(BiomeDictionary.Type::getName, Function.identity()));
        name = name.toUpperCase();
        return byName.get(name);
    }
}
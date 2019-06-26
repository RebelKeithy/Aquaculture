package com.teammetallurgy.aquaculture.api.weight;

import net.minecraft.item.Item;

import java.util.HashMap;

public class FishWeight {
    private HashMap<Item, Integer> WEIGHT_MIN = new HashMap<>();
    private HashMap<Item, Integer> WEIGHT_MAX = new HashMap<>();

    public void addWeight(Item fish, int min, int max) {
        WEIGHT_MIN.put(fish, min);
        WEIGHT_MAX.put(fish, max);
    }

    public int getMinWeight(Item fish) {
        return WEIGHT_MIN.get(fish);
    }

    public int getMaxWeight(Item fish) {
        return WEIGHT_MAX.get(fish);
    }

    public boolean hasWeight(Item fish) {
        return WEIGHT_MIN.containsKey(fish) || WEIGHT_MAX.containsKey(fish);
    }
}
package com.teammetallurgy.aquaculture.api.fish;

import net.minecraft.item.Item;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FishData {
    private HashMap<Item, Integer> WEIGHT_MIN = new HashMap<>();
    private HashMap<Item, Integer> WEIGHT_MAX = new HashMap<>();
    private HashMap<Item, Integer> FILLET_AMOUNT = new HashMap<>();

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

    public List<Item> getFish() {
        return new ArrayList<>(WEIGHT_MIN.keySet());
    }

    public void addAll(Item fish, int min, int max) {
        this.addAll(fish, min, max, 1);
    }

    public void addAll(Item fish, int min, int max, int filletAmount) {
        this.addWeight(fish, min, max);
        if (filletAmount > 0) {
            this.addFilletAmount(fish, filletAmount);
        }
    }

    public void addFilletAmount(Item fish, int filletAmount) {
        FILLET_AMOUNT.put(fish, filletAmount);
    }

    public boolean hasFilletAmount(Item fish) {
        return FILLET_AMOUNT.containsKey(fish);
    }

    public int getFilletAmount(Item fish) {
        return FILLET_AMOUNT.get(fish);
    }
}
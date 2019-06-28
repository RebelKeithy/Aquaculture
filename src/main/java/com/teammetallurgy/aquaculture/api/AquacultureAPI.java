package com.teammetallurgy.aquaculture.api;

import com.teammetallurgy.aquaculture.api.fish.FishData;
import net.minecraft.item.Item;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.Tag;
import net.minecraft.util.ResourceLocation;

public class AquacultureAPI {
    /**
     * Reference to Aquaculture's materials
     **/
    public static AquaMats MATS = new AquaMats();

    /**
     * Reference to setting weight for fish
     **/
    public static FishData FISH_DATA = new FishData();

    public static class Tags {
        public static final Tag<Item> FILLET_KNIFE = tag("fillet_knife");

        public static Tag<Item> tag(String name) {
            return new ItemTags.Wrapper(new ResourceLocation("forge", name));
        }
    }
}
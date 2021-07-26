package com.teammetallurgy.aquaculture.api;

import com.teammetallurgy.aquaculture.Aquaculture;
import com.teammetallurgy.aquaculture.api.fish.FishData;
import com.teammetallurgy.aquaculture.init.FishRegistry;
import com.teammetallurgy.aquaculture.item.BaitItem;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.Tag;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;

import javax.annotation.Nonnull;

public class AquacultureAPI {
    /**
     * Reference to Aquaculture's materials
     **/
    public static AquaMats MATS = new AquaMats();

    /**
     * Reference to setting weight for fish
     **/
    public static FishData FISH_DATA = new FishData();

    public static BaitItem createBait(int durability, int lureSpeedModifier, CreativeModeTab group) {
        return new BaitItem(durability, lureSpeedModifier, group);
    }

    public static Item registerFishMount(@Nonnull String name) {
        return FishRegistry.registerFishMount(name);
    }

    public static class Tags {
        public static final Tag.Named<Item> FILLET_KNIFE = tag("forge", "fillet_knife");
        public static final Tag.Named<Item> FISHING_LINE = tag(Aquaculture.MOD_ID, "fishing_line");
        public static final Tag.Named<Item> BOBBER = tag(Aquaculture.MOD_ID, "bobber");
        public static final Tag.Named<Item> TACKLE_BOX = tag(Aquaculture.MOD_ID, "tackle_box");
        public static final Tag.Named<Item> TURTLE_EDIBLE = tag(Aquaculture.MOD_ID, "turtle_edible");
        public static final Tag.Named<Item> TOOLTIP = tag(Aquaculture.MOD_ID, "tooltip");

        public static Tag.Named<Item> tag(String modID, String name) {
            return ItemTags.bind(new ResourceLocation(modID, name).toString());
        }

        public static void init() {
        }
    }
}
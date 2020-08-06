package com.teammetallurgy.aquaculture.api;

import com.teammetallurgy.aquaculture.Aquaculture;
import com.teammetallurgy.aquaculture.api.fish.FishData;
import com.teammetallurgy.aquaculture.init.FishRegistry;
import com.teammetallurgy.aquaculture.item.BaitItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.tags.ITag;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.ResourceLocation;

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

    public static BaitItem createBait(int durability, int lureSpeedModifier, ItemGroup group) {
        return new BaitItem(durability, lureSpeedModifier, group);
    }

    public static Item registerFishMount(@Nonnull String name) {
        return FishRegistry.registerFishMount(name);
    }

    public static class Tags {
        public static final ITag.INamedTag<Item> FILLET_KNIFE = tag("forge", "fillet_knife");
        public static final ITag.INamedTag<Item> FISHING_LINE = tag(Aquaculture.MOD_ID, "fishing_line");
        public static final ITag.INamedTag<Item> BOBBER = tag(Aquaculture.MOD_ID, "bobber");
        public static final ITag.INamedTag<Item> TACKLE_BOX = tag(Aquaculture.MOD_ID, "tackle_box");
        public static final ITag.INamedTag<Item> TURTLE_EDIBLE = tag(Aquaculture.MOD_ID, "turtle_edible");
        public static final ITag.INamedTag<Item> TOOLTIP = tag(Aquaculture.MOD_ID, "tooltip");

        public static ITag.INamedTag<Item> tag(String modID, String name) {
            return ItemTags.makeWrapperTag(new ResourceLocation(modID, name).toString());
        }

        public static void init() {
        }
    }
}
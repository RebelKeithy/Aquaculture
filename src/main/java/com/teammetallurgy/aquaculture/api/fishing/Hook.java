package com.teammetallurgy.aquaculture.api.fishing;

import com.teammetallurgy.aquaculture.Aquaculture;
import com.teammetallurgy.aquaculture.init.AquaItems;
import com.teammetallurgy.aquaculture.item.HookItem;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.Item;
import net.minecraft.tags.FluidTags;
import net.minecraft.tags.Tag;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;

public class Hook {
    private final String name;
    private final String modID;
    private final ResourceLocation texture;
    private final TextFormatting color;
    private final Item hookItem;
    private final Tag<Fluid> fluid;

    private Hook(String name, String modID, TextFormatting color, Tag<Fluid> fluid) {
        this.name = name;
        this.modID = modID;
        this.color = color;
        this.fluid = fluid;
        this.texture = new ResourceLocation(modID, "textures/entity/rod/hook/" + name + "_hook" + ".png");
        this.hookItem = AquaItems.register(new HookItem(this), new ResourceLocation(modID, name + "_hook"));
    }

    public String getName() {
        return name;
    }

    public String getModID() {
        return modID;
    }

    public ResourceLocation getTexture() {
        return texture;
    }

    public TextFormatting getColor() {
        return color;
    }

    public Item getItem() {
        return hookItem;
    }

    public Tag<Fluid> getFluid() {
        return fluid;
    }

    public static class HookBuilder {
        private String name;
        private String modID = Aquaculture.MOD_ID;
        private TextFormatting color = TextFormatting.WHITE;
        private Tag<Fluid> fluid = FluidTags.WATER;

        public HookBuilder(String name) {
            this.name = name;
        }

        public HookBuilder setModID(String modID) {
            this.modID = modID;
            return this;
        }

        public HookBuilder setColor(TextFormatting color) {
            this.color = color;
            return this;
        }

        public HookBuilder setFluid(Tag<Fluid> fluid) {
            this.fluid = fluid;
            return this;
        }

        public Hook build() {
            return new Hook(this.name, this.modID, this.color, this.fluid);
        }
    }
}
package com.teammetallurgy.aquaculture.api.fishing;

import com.teammetallurgy.aquaculture.init.AquaItems;
import com.teammetallurgy.aquaculture.item.HookItem;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.Item;
import net.minecraft.tags.FluidTags;
import net.minecraft.tags.Tag;
import net.minecraft.util.text.TextFormatting;

public class Hook {
    private final String name;
    private final TextFormatting color;
    private final Item hookItem;
    private final Tag<Fluid> fluid;

    private Hook(String name, TextFormatting color, Tag<Fluid> fluid) {
        this.name = name;
        this.color = color;
        this.fluid = fluid;
        this.hookItem = AquaItems.register(new HookItem(this), name + "_hook");
    }

    public String getName() {
        return name;
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
        private TextFormatting color = TextFormatting.WHITE;
        private Tag<Fluid> fluid = FluidTags.WATER;

        public HookBuilder(String name) {
            this.name = name;
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
            return new Hook(this.name, this.color, this.fluid);
        }
    }
}
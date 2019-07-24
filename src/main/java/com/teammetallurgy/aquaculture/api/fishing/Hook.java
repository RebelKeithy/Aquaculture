package com.teammetallurgy.aquaculture.api.fishing;

import net.minecraft.fluid.Fluid;
import net.minecraft.tags.FluidTags;
import net.minecraft.tags.Tag;

public class Hook {
    private final Tag<Fluid> fluid;

    private Hook(Tag<Fluid> fluid) {
        this.fluid = fluid;
    }

    public Tag<Fluid> getFluid() {
        return fluid;
    }

    public static class HookBuilder {
        private Tag<Fluid> fluid = FluidTags.WATER;

        public HookBuilder setFluid(Tag<Fluid> fluid) {
            this.fluid = fluid;
            return this;
        }

        public Hook build() {
            return new Hook(this.fluid);
        }
    }
}
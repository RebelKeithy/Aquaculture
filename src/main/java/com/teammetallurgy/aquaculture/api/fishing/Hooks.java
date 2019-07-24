package com.teammetallurgy.aquaculture.api.fishing;

import net.minecraft.tags.FluidTags;

public class Hooks {
    public static final Hook LIGHT = new Hook.HookBuilder().build();
    public static final Hook WEIGHTED = new Hook.HookBuilder().build();
    public static final Hook DOUBLE = new Hook.HookBuilder().build();
    public static final Hook NOTE = new Hook.HookBuilder().build();
    public static final Hook OBSIDIAN = new Hook.HookBuilder().setFluid(FluidTags.LAVA).build();
    public static final Hook GOLD = new Hook.HookBuilder().build();
    public static final Hook RED = new Hook.HookBuilder().build();

}
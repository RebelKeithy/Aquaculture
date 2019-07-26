package com.teammetallurgy.aquaculture.api.fishing;

import com.teammetallurgy.aquaculture.Aquaculture;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Aquaculture.MOD_ID) //To get the static registering working
public class Hooks {
    public static final Hook LIGHT = new Hook.HookBuilder("light").setColor(TextFormatting.ITALIC).build();
    public static final Hook HEAVY = new Hook.HookBuilder("heavy").setColor(TextFormatting.BOLD).build();
    public static final Hook DOUBLE = new Hook.HookBuilder("double").setColor(TextFormatting.DARK_GRAY).build();
    public static final Hook GOLD = new Hook.HookBuilder("gold").setColor(TextFormatting.GOLD).build();
    public static final Hook OBSIDIAN = new Hook.HookBuilder("obsidian").setColor(TextFormatting.DARK_PURPLE).setFluid(FluidTags.LAVA).build();
    public static final Hook REDSTONE = new Hook.HookBuilder("redstone").setColor(TextFormatting.RED).build();
    public static final Hook GLOWSTONE = new Hook.HookBuilder("glowstone").setColor(TextFormatting.YELLOW).build();
    public static final Hook NOTE = new Hook.HookBuilder("note").setColor(TextFormatting.DARK_RED).build();
}
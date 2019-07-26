package com.teammetallurgy.aquaculture.api.fishing;

import com.teammetallurgy.aquaculture.Aquaculture;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Aquaculture.MOD_ID) //To get the static registering working
public class Hooks {
    public static final Hook LIGHT = new Hook.HookBuilder("light_hook").setColor(TextFormatting.ITALIC).build();
    public static final Hook WEIGHTED = new Hook.HookBuilder("weighted_hook").setColor(TextFormatting.BOLD).build();
    public static final Hook DOUBLE = new Hook.HookBuilder("double_hook").setColor(TextFormatting.DARK_GRAY).build();
    public static final Hook NOTE = new Hook.HookBuilder("note_hook").setColor(TextFormatting.YELLOW).build();
    public static final Hook OBSIDIAN = new Hook.HookBuilder("obsidian_hook").setColor(TextFormatting.DARK_PURPLE).setFluid(FluidTags.LAVA).build();
    public static final Hook GOLD = new Hook.HookBuilder("gold_hook").setColor(TextFormatting.GOLD).build();
    public static final Hook RED = new Hook.HookBuilder("red_hook").setColor(TextFormatting.RED).build();
}
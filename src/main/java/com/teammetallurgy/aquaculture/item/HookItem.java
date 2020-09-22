package com.teammetallurgy.aquaculture.item;

import com.teammetallurgy.aquaculture.Aquaculture;
import com.teammetallurgy.aquaculture.api.fishing.Hook;
import com.teammetallurgy.aquaculture.api.fishing.Hooks;
import net.minecraft.block.Blocks;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.text.IFormattableTextComponent;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

public class HookItem extends Item {
    private final Hook hook;

    public HookItem(Hook hook) {
        super(new Item.Properties().group(Aquaculture.GROUP).maxStackSize(16));
        this.hook = hook;
    }

    public Hook getHookType() {
        return hook;
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void addInformation(@Nonnull ItemStack stack, @Nullable World world, List<ITextComponent> tooltips, ITooltipFlag tooltipFlag) {
        Hook hook = getHookType();
        if (hook != Hooks.EMPTY && hook.getFluids().contains(FluidTags.LAVA)) {
            if (hook.getFluids().contains(FluidTags.WATER)) {
                IFormattableTextComponent universal = new TranslationTextComponent("aquaculture.universal");
                tooltips.add(universal.mergeStyle(universal.getStyle().setFormatting(TextFormatting.BOLD)));
            } else {
                IFormattableTextComponent lava = new TranslationTextComponent(Blocks.LAVA.getTranslationKey());
                tooltips.add(lava.mergeStyle(lava.getStyle().setFormatting(TextFormatting.RED)));
            }
        }
        super.addInformation(stack, world, tooltips, tooltipFlag);
    }
}
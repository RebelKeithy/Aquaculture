package com.teammetallurgy.aquaculture.item;

import com.teammetallurgy.aquaculture.Aquaculture;
import com.teammetallurgy.aquaculture.api.fishing.Hook;
import net.minecraft.block.Blocks;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

public class HookItem extends Item {
    private Hook hook;

    public HookItem(Hook hook) {
        super(new Item.Properties().group(Aquaculture.GROUP).maxStackSize(16));
        this.hook = hook;
    }

    public Hook getHookType() {
        return hook;
    }

    @Override
    public void addInformation(@Nonnull ItemStack stack, @Nullable World world, List<ITextComponent> tooltips, ITooltipFlag tooltipFlag) {
        Hook hook = getHookType();
        if (hook != null && hook.getFluids().contains(FluidTags.LAVA)) {
            if (hook.getFluids().contains(FluidTags.WATER)) {
                tooltips.add(new TranslationTextComponent("aquaculture.universal").setStyle(new Style().setColor(TextFormatting.BOLD)));
            } else {
                tooltips.add(new TranslationTextComponent(Blocks.LAVA.getTranslationKey()).setStyle(new Style().setColor(TextFormatting.RED)));
            }
        }
        super.addInformation(stack, world, tooltips, tooltipFlag);
    }
}
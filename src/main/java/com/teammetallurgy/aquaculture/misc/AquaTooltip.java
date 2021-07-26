package com.teammetallurgy.aquaculture.misc;

import com.mojang.blaze3d.platform.InputConstants;
import com.teammetallurgy.aquaculture.Aquaculture;
import com.teammetallurgy.aquaculture.api.AquacultureAPI;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.lwjgl.glfw.GLFW;

@Mod.EventBusSubscriber(modid = Aquaculture.MOD_ID, value = Dist.CLIENT)
public class AquaTooltip {

    @SubscribeEvent
    public static void onTooltip(ItemTooltipEvent event) {
        ItemStack stack = event.getItemStack();
        if (!stack.isEmpty()) {
            Item item = stack.getItem();
            if (!item.getTags().isEmpty() && stack.is(AquacultureAPI.Tags.TOOLTIP)) {
                if (item.getRegistryName() != null) {
                    String itemIdentifier = item.getRegistryName().getPath() + ".tooltip";
                    if (InputConstants.isKeyDown(Minecraft.getInstance().getWindow().getWindow(), GLFW.GLFW_KEY_LEFT_SHIFT)) {
                        event.getToolTip().add(new TranslatableComponent(Aquaculture.MOD_ID + "." + itemIdentifier + ".desc").withStyle(ChatFormatting.AQUA));
                    } else {
                        event.getToolTip().add(new TranslatableComponent(Aquaculture.MOD_ID + "." + itemIdentifier + ".title").withStyle(ChatFormatting.AQUA)
                                .append(" ").append(new TranslatableComponent(Aquaculture.MOD_ID + ".shift").withStyle(ChatFormatting.DARK_GRAY)));
                    }
                }
            }
        }
    }
}
package com.teammetallurgy.aquaculture.misc;

import com.teammetallurgy.aquaculture.Aquaculture;
import com.teammetallurgy.aquaculture.api.AquacultureAPI;
import net.minecraft.item.Item;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Aquaculture.MOD_ID, value = Dist.CLIENT)
public class AquaTooltip {

    @SubscribeEvent
    public static void onTooltip(ItemTooltipEvent event) {
        if (!event.getItemStack().isEmpty()) {
            Item item = event.getItemStack().getItem();
            if (item.isIn(AquacultureAPI.Tags.TOOLTIP)) {
                if (item.getRegistryName() != null) {
                    event.getToolTip().add(new TranslationTextComponent(Aquaculture.MOD_ID + "." + item.getRegistryName().getPath() + ".tooltip"));
                }
            }
        }
    }
}
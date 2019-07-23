package com.teammetallurgy.aquaculture.misc;

import com.teammetallurgy.aquaculture.Aquaculture;
import com.teammetallurgy.aquaculture.init.AquaItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.tags.FluidTags;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Aquaculture.MOD_ID)
public class NeptunesMight {

    @SubscribeEvent
    public static void onAttack(LivingHurtEvent event) {
        Entity source = event.getSource().getTrueSource();
        if (source instanceof LivingEntity) {
            LivingEntity living = (LivingEntity) source;
            System.out.println(living.getName());
            if (living.areEyesInFluid(FluidTags.WATER)) {
                ItemStack heldStack = living.getHeldItemMainhand();
                if (heldStack.getItem() == AquaItems.NEPTUNIUM_SWORD || heldStack.getItem() == AquaItems.NEPTUNIUM_AXE) {
                    event.setAmount(event.getAmount() * 1.5F);
                }
            }
        }
    }
}
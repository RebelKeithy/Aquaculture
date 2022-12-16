package com.teammetallurgy.aquaculture.misc;

import com.teammetallurgy.aquaculture.Aquaculture;
import com.teammetallurgy.aquaculture.init.AquaItems;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Aquaculture.MOD_ID)
public class NeptunesMight {

    @SubscribeEvent
    public static void onAttack(LivingHurtEvent event) {
        Entity source = event.getSource().getEntity();
        if (source instanceof LivingEntity living) {
            if (living.isEyeInFluidType(ForgeMod.WATER_TYPE.get())) {
                ItemStack heldStack = living.getMainHandItem();
                if (heldStack.getItem() == AquaItems.NEPTUNIUM_SWORD.get() || heldStack.getItem() == AquaItems.NEPTUNIUM_AXE.get()) {
                    event.setAmount(event.getAmount() * 1.5F);
                }
            }
        }
    }
}
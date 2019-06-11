package com.teammetallurgy.aquaculture.init;

import com.teammetallurgy.aquaculture.Aquaculture;
import com.teammetallurgy.aquaculture.entity.AquaFishingBobberEntity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ObjectHolder;

@Mod.EventBusSubscriber(modid = Aquaculture.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
@ObjectHolder(Aquaculture.MOD_ID)
public class AquaEntities {
    public static final EntityType<AquaFishingBobberEntity> FISH_HOOK = EntityType.Builder.<AquaFishingBobberEntity>create(EntityClassification.MISC)
            .disableSerialization()
            .disableSummoning()
            .size(0.25F, 0.25F)
            .setTrackingRange(64)
            .setUpdateInterval(5).build(new ResourceLocation(Aquaculture.MOD_ID, "bobber").toString());

    @SubscribeEvent
    public static void registerEntities(RegistryEvent.Register<EntityType<?>> event) {
        event.getRegistry().register(FISH_HOOK);
    }
}
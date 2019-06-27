package com.teammetallurgy.aquaculture.init;

import com.teammetallurgy.aquaculture.Aquaculture;
import com.teammetallurgy.aquaculture.entity.AquaFishingBobberEntity;
import net.minecraft.entity.Entity;
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
    public static final EntityType<AquaFishingBobberEntity> FISH_HOOK = register("aqua_bobber", EntityType.Builder.<AquaFishingBobberEntity>create(EntityClassification.MISC)
            .disableSerialization()
            .disableSummoning()
            .size(0.25F, 0.25F)
            .setTrackingRange(64)
            .setUpdateInterval(5));

    @SubscribeEvent
    public static void registerEntities(RegistryEvent.Register<EntityType<?>> event) {
        System.out.println("REGISTER FISH_HOOK");
        event.getRegistry().register(FISH_HOOK);
    }

    private static <T extends Entity> EntityType<T> register(String name, EntityType.Builder<T> builder) {
        ResourceLocation location = new ResourceLocation(Aquaculture.MOD_ID, name);
        EntityType<T> entityType = builder.build(location.toString());
        entityType.setRegistryName(location);
        return entityType;
    }
}
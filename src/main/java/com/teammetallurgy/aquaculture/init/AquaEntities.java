package com.teammetallurgy.aquaculture.init;

import com.google.common.collect.Lists;
import com.teammetallurgy.aquaculture.Aquaculture;
import com.teammetallurgy.aquaculture.entity.AquaFishingBobberEntity;
import com.teammetallurgy.aquaculture.entity.NeptuniumTridentEntity;
import com.teammetallurgy.aquaculture.entity.WaterArrowEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ObjectHolder;

import java.util.List;

@Mod.EventBusSubscriber(modid = Aquaculture.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
@ObjectHolder(Aquaculture.MOD_ID)
public class AquaEntities {
    private static List<EntityType> ENTITIES = Lists.newArrayList();
    public static final EntityType<AquaFishingBobberEntity> BOBBER = register("aqua_bobber", EntityType.Builder.<AquaFishingBobberEntity>create(EntityClassification.MISC)
            .disableSerialization()
            .disableSummoning()
            .size(0.25F, 0.25F)
            .setTrackingRange(4)
            .setUpdateInterval(5)
            .setCustomClientFactory(AquaFishingBobberEntity::new));
    public static final EntityType<NeptuniumTridentEntity> NEPTUNIUM_TRIDENT = register("neptunium_trident", EntityType.Builder.<NeptuniumTridentEntity>create(NeptuniumTridentEntity::new, EntityClassification.MISC)
            .size(0.5F, 0.5F)
            .setCustomClientFactory(NeptuniumTridentEntity::new));
    public static final EntityType<WaterArrowEntity> WATER_ARROW = register("water_arrow", EntityType.Builder.<WaterArrowEntity>create(WaterArrowEntity::new, EntityClassification.MISC)
            .size(0.5F, 0.5F)
            .setCustomClientFactory(WaterArrowEntity::new));

    @SubscribeEvent
    public static void registerEntities(RegistryEvent.Register<EntityType<?>> event) {
        for (EntityType entity : ENTITIES) {
            event.getRegistry().register(entity);
        }
    }

    private static <T extends Entity> EntityType<T> register(String name, EntityType.Builder<T> builder) {
        ResourceLocation location = new ResourceLocation(Aquaculture.MOD_ID, name);
        EntityType<T> entityType = builder.build(location.toString());
        entityType.setRegistryName(location);
        ENTITIES.add(entityType);
        return entityType;
    }
}
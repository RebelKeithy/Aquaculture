package com.teammetallurgy.aquaculture.init;

import com.google.common.collect.Lists;
import com.teammetallurgy.aquaculture.Aquaculture;
import com.teammetallurgy.aquaculture.entity.AquaFishingBobberEntity;
import com.teammetallurgy.aquaculture.entity.NeptuniumTridentEntity;
import com.teammetallurgy.aquaculture.entity.TurtleLandEntity;
import com.teammetallurgy.aquaculture.entity.WaterArrowEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.SpawnEggItem;
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
    public static final EntityType<TurtleLandEntity> BOX_TURTLE = registerMob("box_turtle", 0x7F8439, 0x5D612A, EntityType.Builder.create(TurtleLandEntity::new, EntityClassification.CREATURE)
            .size(0.5F, 0.25F));
    public static final EntityType<TurtleLandEntity> ARRAU_TURTLE = registerMob("arrau_turtle", 0x71857A, 0x4F6258, EntityType.Builder.create(TurtleLandEntity::new, EntityClassification.CREATURE)
            .size(0.5F, 0.25F));
    public static final EntityType<TurtleLandEntity> STARSHELL_TURTLE = registerMob("starshell_turtle", 0xDCE2E5, 0x464645, EntityType.Builder.create(TurtleLandEntity::new, EntityClassification.CREATURE)
            .size(0.5F, 0.25F));

    @SubscribeEvent
    public static void registerEntities(RegistryEvent.Register<EntityType<?>> event) {
        for (EntityType entity : ENTITIES) {
            event.getRegistry().register(entity);
        }
    }

    private static <T extends Entity> EntityType<T> registerMob(String name, int eggPrimary, int eggSecondary, EntityType.Builder<T> builder) {
        EntityType<T> entityType = register(name, builder);
        Item spawnEgg = new SpawnEggItem(entityType, eggPrimary, eggSecondary, (new Item.Properties()).group(ItemGroup.MISC));
        AquaItems.register(spawnEgg, name + "_spawn_egg");
        return entityType;
    }

    private static <T extends Entity> EntityType<T> register(String name, EntityType.Builder<T> builder) {
        ResourceLocation location = new ResourceLocation(Aquaculture.MOD_ID, name);
        EntityType<T> entityType = builder.build(location.toString());
        entityType.setRegistryName(location);
        ENTITIES.add(entityType);
        return entityType;
    }
}
package com.teammetallurgy.aquaculture.init;

import com.google.common.collect.Lists;
import com.teammetallurgy.aquaculture.Aquaculture;
import com.teammetallurgy.aquaculture.entity.AquaFishEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntitySpawnPlacementRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.gen.Heightmap;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import javax.annotation.Nonnull;
import java.util.List;

@Mod.EventBusSubscriber(modid = Aquaculture.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class FishRegistry {
    public static List<EntityType> fishEntities = Lists.newArrayList();

    /**
     * Same as {@link #register(Item, String, float, float)}, but with default size
     */
    public static Item register(@Nonnull Item fishItem, @Nonnull String name) {
        return register(fishItem, name, 0.5F, 0.3F);
    }

    /**
     * Registers the fish item, fish entity and fish bucket
     *
     * @param fishItem The fish item to be registered
     * @param name     The fish name
     * @return The fish Item that was registered
     */
    public static Item register(@Nonnull Item fishItem, @Nonnull String name, float width, float height) {
        ResourceLocation location = new ResourceLocation(Aquaculture.MOD_ID, name);
        AquaItems.register(fishItem, name);
        EntityType<AquaFishEntity> entity = EntityType.Builder.create(AquaFishEntity::new, EntityClassification.WATER_CREATURE).size(width, height).disableSerialization().build(location.toString()); //TODO remove disableSerialization, and figure out how to make data fixers work
        registerEntity(name, entity);
        return fishItem;
    }

    public static <T extends Entity> EntityType<T> registerEntity(String name, EntityType<T> entityType) {
        ResourceLocation location = new ResourceLocation(Aquaculture.MOD_ID, name);
        entityType.setRegistryName(location);
        fishEntities.add(entityType);
        return entityType;
    }

    @SubscribeEvent
    public static void registerFish(RegistryEvent.Register<EntityType<?>> event) {
        System.out.println("Register Fish");
        for (EntityType entityType : fishEntities) {
            event.getRegistry().register(entityType);
            EntitySpawnPlacementRegistry.register(entityType, EntitySpawnPlacementRegistry.PlacementType.IN_WATER, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, AquaFishEntity::func_223363_b);
        }
        /*Item bucket = new FishBucketItem(EntityType.COD, Fluids.WATER, (new Item.Properties()).maxStackSize(1).group(Aquaculture.TAB));
        bucket.setRegistryName(new ResourceLocation(Aquaculture.MOD_ID, Objects.requireNonNull(EntityType.COD.getRegistryName()).getPath() + "_bucket"));
        ForgeRegistries.ITEMS.register(bucket); //Can't use the RegistryEvent, since items gets registered before entities*/
    }
}
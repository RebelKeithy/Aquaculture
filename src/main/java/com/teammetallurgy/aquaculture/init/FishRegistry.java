package com.teammetallurgy.aquaculture.init;

import com.google.common.collect.Lists;
import com.teammetallurgy.aquaculture.Aquaculture;
import com.teammetallurgy.aquaculture.entity.AquaFishEntity;
import com.teammetallurgy.aquaculture.entity.FishMountEntity;
import com.teammetallurgy.aquaculture.entity.FishType;
import com.teammetallurgy.aquaculture.item.FishMountItem;
import com.teammetallurgy.aquaculture.misc.StackHelper;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.animal.AbstractFish;
import net.minecraft.world.entity.animal.Cat;
import net.minecraft.world.entity.animal.Ocelot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

@Mod.EventBusSubscriber(modid = Aquaculture.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class FishRegistry {
    public static List<EntityType<AquaFishEntity>> fishEntities = Lists.newArrayList();
    public static List<EntityType<FishMountEntity>> fishMounts = Lists.newArrayList();

    public static Item registerFishMount(@Nonnull String name) {
        EntityType.Builder<FishMountEntity> fishMountBuilder = EntityType.Builder.<FishMountEntity>of(FishMountEntity::new, MobCategory.MISC)
                .sized(0.5F, 0.5F)
                .setCustomClientFactory(FishMountEntity::new);
        EntityType<FishMountEntity> fishMount = AquaEntities.register(name, fishMountBuilder);
        FishMountItem fishMountItem = new FishMountItem(fishMount);
        AquaItems.register(() -> fishMountItem, name);
        fishMounts.add(fishMount);
        return fishMountItem;
    }

    /**
     * Same as {@link #register(Supplier, String, FishType)}, but with default size
     */
    public static RegistryObject<Item> register(@Nonnull Supplier<Item> initializer, @Nonnull String name) {
        return register(initializer, name, FishType.MEDIUM);
    }

    /**
     * Registers the fish item, fish entity and fish bucket
     *
     * @param initializer The fish initializer
     * @param name     The fish name
     * @return The fish Item that was registered
     */
    public static RegistryObject<Item> register(@Nonnull Supplier<Item> initializer, @Nonnull String name, FishType fishSize) {
        EntityType<AquaFishEntity> fish = EntityType.Builder.of(AquaFishEntity::new, MobCategory.WATER_AMBIENT).sized(fishSize.getWidth(), fishSize.getHeight()).build("minecraft:cod"); //TODO Change when Forge allow for custom datafixers
        registerFishEntity(name, fish);
        AquaFishEntity.TYPES.put(fish, fishSize);
        return AquaItems.register(initializer, name);
    }

    public static EntityType<AquaFishEntity> registerFishEntity(String name, EntityType<AquaFishEntity> entityType) {
        ResourceLocation location = new ResourceLocation(Aquaculture.MOD_ID, name);
        entityType.setRegistryName(location);
        fishEntities.add(entityType);
        return entityType;
    }

    @SubscribeEvent
    public static void registerFishies(RegistryEvent.Register<EntityType<?>> event) {
        for (EntityType<AquaFishEntity> entityType : fishEntities) {
            event.getRegistry().register(entityType);
            SpawnPlacements.register(entityType, SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, AquaFishEntity::canSpawnHere);
        }
    }

    @SubscribeEvent
    public static void addFishEntity0Attributes(EntityAttributeCreationEvent event) {
        for (EntityType<AquaFishEntity> entityType : fishEntities) {
            event.put(entityType, AbstractFish.createAttributes().build());
        }
    }

    public static void addCatBreeding() {
        try {
            Ingredient catBreedingItems = Cat.TEMPT_INGREDIENT;
            Ingredient ocelotBreedingItems = Ocelot.TEMPT_INGREDIENT;
            List<ItemStack> aquaFish = new ArrayList<>();
            fishEntities.forEach(f -> aquaFish.add(new ItemStack(ForgeRegistries.ITEMS.getValue(f.getRegistryName()))));
            aquaFish.removeIf(p -> p.getItem().equals(AquaItems.JELLYFISH));

            Cat.TEMPT_INGREDIENT = StackHelper.mergeIngredient(catBreedingItems, StackHelper.ingredientFromStackList(aquaFish));
            Ocelot.TEMPT_INGREDIENT = StackHelper.mergeIngredient(ocelotBreedingItems, StackHelper.ingredientFromStackList(aquaFish));
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }
}
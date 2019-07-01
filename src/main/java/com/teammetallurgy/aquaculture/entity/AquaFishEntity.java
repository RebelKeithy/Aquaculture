package com.teammetallurgy.aquaculture.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.fish.AbstractGroupFishEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import java.util.HashMap;

public class AquaFishEntity extends AbstractGroupFishEntity { //TODO Look into grouping per type
    public static HashMap<EntityType, Item> BUCKETS = new HashMap<>();

    public AquaFishEntity(EntityType<? extends AbstractGroupFishEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    public ItemStack getPickedResult(RayTraceResult target) {
        return this.getFishBucket();
    }

    @Override
    @Nonnull
    protected ItemStack getFishBucket() {
        return new ItemStack(BUCKETS.get(this.getType()));
    }

    @Override
    @Nonnull
    protected SoundEvent getFlopSound() { //TODO
        return SoundEvents.ENTITY_COD_FLOP;
    }
}
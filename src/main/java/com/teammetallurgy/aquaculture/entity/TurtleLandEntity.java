package com.teammetallurgy.aquaculture.entity;

import net.minecraft.block.Block;
import net.minecraft.block.VineBlock;
import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class TurtleLandEntity extends AnimalEntity {

    public TurtleLandEntity(EntityType<? extends AnimalEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    protected void registerAttributes() {
        super.registerAttributes();
        this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(10.0D);
        this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.15D);
    }

    @Override
    public boolean isBreedingItem(@Nonnull ItemStack stack) {
        return stack.getItem().isIn(ItemTags.SMALL_FLOWERS) || Block.getBlockFromItem(stack.getItem()) instanceof VineBlock;
    }

    @Override
    @Nullable
    public AgeableEntity createChild(@Nonnull AgeableEntity ageableEntity) {
        return (AgeableEntity) this.getType().create(this.world);
    }
}
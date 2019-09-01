package com.teammetallurgy.aquaculture.entity;

import com.teammetallurgy.aquaculture.entity.ai.goal.FollowTypeSchoolLeaderGoal;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.goal.FollowSchoolLeaderGoal;
import net.minecraft.entity.passive.fish.AbstractFishEntity;
import net.minecraft.entity.passive.fish.AbstractGroupFishEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import java.util.HashMap;
import java.util.Random;

public class AquaFishEntity extends AbstractGroupFishEntity {
    public static HashMap<EntityType, Item> BUCKETS = new HashMap<>();

    public AquaFishEntity(EntityType<? extends AbstractGroupFishEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();

        this.goalSelector.goals.forEach(prioritizedGoal -> { //Remove vanilla schooling goal
            if (prioritizedGoal.getGoal().getClass() == FollowSchoolLeaderGoal.class)
                this.goalSelector.removeGoal(prioritizedGoal.getGoal());
        });
        this.goalSelector.addGoal(5, new FollowTypeSchoolLeaderGoal(this));
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

    public static boolean canSpawnHere(EntityType<? extends AbstractFishEntity> fish, IWorld world, SpawnReason spawnReason, BlockPos pos, Random random) {
        return world.getBlockState(pos).getBlock() == Blocks.WATER;
    }
}
package com.teammetallurgy.aquaculture.entity;

import com.teammetallurgy.aquaculture.entity.ai.goal.FollowTypeSchoolLeaderGoal;
import com.teammetallurgy.aquaculture.init.AquaItems;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Pose;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.goal.FollowSchoolLeaderGoal;
import net.minecraft.entity.passive.fish.AbstractFishEntity;
import net.minecraft.entity.passive.fish.AbstractGroupFishEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import java.util.HashMap;
import java.util.Objects;
import java.util.Random;

public class AquaFishEntity extends AbstractGroupFishEntity {
    public static HashMap<EntityType, Item> BUCKETS = new HashMap<>();
    public static HashMap<EntityType, FishType> SIZES = new HashMap<>();

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
    protected SoundEvent getFlopSound() {
        if (Objects.equals(this.getType().getRegistryName(), AquaItems.JELLYFISH.getRegistryName())) {
            return SoundEvents.BLOCK_SLIME_BLOCK_STEP;
        }
        return SoundEvents.ENTITY_COD_FLOP;
    }

    @Override
    @Nonnull
    public EntitySize getSize(Pose pose) {
        return super.getSize(pose);
    }

    @Override
    public void onCollideWithPlayer(PlayerEntity player) {
        super.onCollideWithPlayer(player);
        if (Objects.equals(this.getType().getRegistryName(), AquaItems.JELLYFISH.getRegistryName())) {
            if (this.isAlive()) {
                if (this.getDistanceSq(player) < 1.0D && player.attackEntityFrom(DamageSource.causeMobDamage(this), 0.5F)) {
                    this.playSound(SoundEvents.ENTITY_SLIME_ATTACK, 0.5F, (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F);
                    this.applyEnchantments(this, player);
                }
            }
        }
    }

    public static boolean canSpawnHere(EntityType<? extends AbstractFishEntity> fish, IWorld world, SpawnReason spawnReason, BlockPos pos, Random random) {
        return world.getBlockState(pos).getBlock() == Blocks.WATER;
    }
}
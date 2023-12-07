package com.teammetallurgy.aquaculture.entity;

import com.teammetallurgy.aquaculture.Aquaculture;
import com.teammetallurgy.aquaculture.entity.ai.goal.FollowTypeSchoolLeaderGoal;
import com.teammetallurgy.aquaculture.init.AquaSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.ai.goal.FollowFlockLeaderGoal;
import net.minecraft.world.entity.animal.AbstractFish;
import net.minecraft.world.entity.animal.AbstractSchoolingFish;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.HitResult;

import javax.annotation.Nonnull;
import java.util.Objects;

public class AquaFishEntity extends AbstractSchoolingFish {
    private final FishType fishType;

    public AquaFishEntity(EntityType<? extends AbstractSchoolingFish> entityType, Level world, FishType fishType) {
        super(entityType, world);
        this.fishType = fishType;
    }

    public FishType getFishType() {
        return this.fishType;
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();

        this.goalSelector.availableGoals.forEach(prioritizedGoal -> { //Removes vanilla schooling goal
            if (prioritizedGoal.getGoal().getClass() == FollowFlockLeaderGoal.class) {
                this.goalSelector.removeGoal(prioritizedGoal.getGoal());
            }
        });
        this.goalSelector.addGoal(5, new FollowTypeSchoolLeaderGoal(this));
    }

    @Override
    public ItemStack getPickedResult(HitResult target) {
        return this.getBucketItemStack();
    }

    @Override
    @Nonnull
    public ItemStack getBucketItemStack() {
        return new ItemStack(BuiltInRegistries.ITEM.get(new ResourceLocation(BuiltInRegistries.ENTITY_TYPE.getKey(this.getType()).toString() + "_bucket")));
    }

    @Override
    @Nonnull
    protected SoundEvent getFlopSound() {
        if (this.getFishType() == FishType.JELLYFISH) {
            return AquaSounds.JELLYFISH_FLOP.get();
        }
        return AquaSounds.FISH_FLOP.get();
    }
    
    @Override
    protected SoundEvent getAmbientSound() {
        return AquaSounds.FISH_AMBIENT.get();
    }
    
    @Override
    protected SoundEvent getDeathSound() {
        return AquaSounds.FISH_DEATH.get();
    }

    @Override
    protected SoundEvent getHurtSound(@Nonnull DamageSource damageSource) {
        return AquaSounds.FISH_HURT.get();
    }

    @Override
    @Nonnull
    public EntityDimensions getDimensions(@Nonnull Pose pose) {
        return super.getDimensions(pose);
    }

    @Override
    public void playerTouch(@Nonnull Player player) {
        super.playerTouch(player);
        if (Objects.equals(BuiltInRegistries.ENTITY_TYPE.getKey(this.getType()), new ResourceLocation(Aquaculture.MOD_ID, "jellyfish"))) {
            if (this.isAlive()) {
                if (this.distanceToSqr(player) < 1.0D && player.hurt(this.damageSources().mobAttack(this), 0.5F)) {
                    this.playSound(AquaSounds.JELLYFISH_COLLIDE.get(), 0.5F, (this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F);
                    this.doEnchantDamageEffects(this, player);
                }
            }
        }
    }

    @Override
    public void stopFollowing() {
        if (this.leader != null) {
            super.stopFollowing();
        }
    }

    public static boolean canSpawnHere(EntityType<? extends AbstractFish> fish, LevelAccessor world, MobSpawnType spawnReason, BlockPos pos, RandomSource random) {
        int seaLevel = world.getSeaLevel();
        int minY = seaLevel - 13;
        boolean isAllNeighborsSource = isSourceBlock(world, pos.north()) && isSourceBlock(world, pos.south()) && isSourceBlock(world, pos.west()) && isSourceBlock(world, pos.east());
        return isSourceBlock(world, pos) && isAllNeighborsSource && pos.getY() >= minY && pos.getY() <= seaLevel;
    }

    private static boolean isSourceBlock(LevelAccessor world, BlockPos pos) {
        BlockState state = world.getBlockState(pos);
        return state.getBlock() instanceof LiquidBlock && world.getBlockState(pos).is(Blocks.WATER) && state.getValue(LiquidBlock.LEVEL) == 0;
    }
}

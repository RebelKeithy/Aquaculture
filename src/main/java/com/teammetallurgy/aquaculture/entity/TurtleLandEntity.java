package com.teammetallurgy.aquaculture.entity;

import com.teammetallurgy.aquaculture.api.AquacultureAPI;
import net.minecraft.block.Block;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.controller.MovementController;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class TurtleLandEntity extends AnimalEntity {
    private static final Ingredient TURTLE_EDIBLE = Ingredient.fromTag(AquacultureAPI.Tags.TURTLE_EDIBLE);

    public TurtleLandEntity(EntityType<? extends AnimalEntity> entityType, World world) {
        super(entityType, world);
        this.moveController = new TurtleLandMovementController(this);
        this.setPathPriority(PathNodeType.WATER, 0.2F);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new TurtleLandSwimGoal());
        this.goalSelector.addGoal(1, new PanicGoal(this, 1.2D));
        this.goalSelector.addGoal(2, new BreedGoal(this, 1.05D));
        this.goalSelector.addGoal(3, new TemptGoal(this, 1.15D, false, TURTLE_EDIBLE));
        this.goalSelector.addGoal(4, new FollowParentGoal(this, 1.1D));
        this.goalSelector.addGoal(5, new GetOutOfWaterGoal(this));
        this.goalSelector.addGoal(6, new RandomWalkingGoal(this, 1.0D));
        this.goalSelector.addGoal(7, new LookAtGoal(this, PlayerEntity.class, 6.0F));
        this.goalSelector.addGoal(8, new LookRandomlyGoal(this));
    }

    @Override
    protected void registerAttributes() {
        super.registerAttributes();
        this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(10.0D);
        this.getAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(1.5D);
        this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.1D);
    }

    @Override
    public boolean isBreedingItem(@Nonnull ItemStack stack) {
        return TURTLE_EDIBLE.test(stack);
    }

    @Override
    @Nullable
    public AgeableEntity createChild(@Nonnull AgeableEntity ageableEntity) {
        return (AgeableEntity) this.getType().create(this.world);
    }

    @Override
    public boolean canBreatheUnderwater() {
        return true;
    }

    @Override
    protected float getWaterSlowDown() {
        return 1.0F;
    }

    @Override
    protected float getStandingEyeHeight(Pose pose, EntitySize size) {
        return this.isChild() ? size.height * 0.75F : size.height * 0.7F;
    }

    public class TurtleLandSwimGoal extends SwimGoal {

        public TurtleLandSwimGoal() {
            super(TurtleLandEntity.this);
        }

        @Override
        public boolean shouldExecute() {
            return TurtleLandEntity.this.isInWater() && TurtleLandEntity.this.getSubmergedHeight() > 0.25D * 0.55D || TurtleLandEntity.this.isInLava();
        }
    }

    static class TurtleLandMovementController extends MovementController {
        private final TurtleLandEntity turtle;

        TurtleLandMovementController(TurtleLandEntity turtle) {
            super(turtle);
            this.turtle = turtle;
        }

        @Override
        public void tick() {
            super.tick();
            this.updateSpeed();
        }

        private void updateSpeed() {
            if (this.turtle.isInWater()) {
                if (this.turtle.isChild()) {
                    this.turtle.setAIMoveSpeed(0.14F);
                } else {
                    this.turtle.setAIMoveSpeed(0.12F);
                }
            } else if (this.turtle.onGround) {
                this.turtle.setAIMoveSpeed(0.1F);
            }
        }
    }

    static class GetOutOfWaterGoal extends MoveToBlockGoal {
        private final TurtleLandEntity turtle;

        private GetOutOfWaterGoal(TurtleLandEntity turtle) {
            super(turtle, turtle.isChild() ? 1.4D : 1.2D, 24);
            this.turtle = turtle;
            this.field_203112_e = -1;
        }

        @Override
        public boolean shouldContinueExecuting() {
            return this.turtle.isInWater() && this.timeoutCounter <= 1200 && this.shouldMoveTo(this.turtle.world, this.destinationBlock);
        }

        @Override
        public boolean shouldExecute() {
            return this.turtle.isInWater() && super.shouldExecute();
        }

        @Override
        public boolean shouldMove() {
            return this.timeoutCounter % 160 == 0;
        }

        @Override
        protected boolean shouldMoveTo(IWorldReader reader, @Nonnull BlockPos pos) {
            Block block = reader.getBlockState(pos).getBlock();
            System.out.println("Hi: " + !(block instanceof FlowingFluidBlock));
            return !(block instanceof FlowingFluidBlock);
        }
    }
}
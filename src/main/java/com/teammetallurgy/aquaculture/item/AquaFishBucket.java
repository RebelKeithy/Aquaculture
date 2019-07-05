package com.teammetallurgy.aquaculture.item;

import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FlowingFluid;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.FishBucketItem;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.RayTraceContext;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public class AquaFishBucket extends FishBucketItem {
    private final EntityType<?> fishType;

    public AquaFishBucket(EntityType<?> entityType, Fluid fluid, Properties properties) {
        super(entityType, fluid, properties);
        this.fishType = entityType;
    }

    @Override
    @Nonnull
    public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, @Nonnull Hand hand) {
        ItemStack heldStack = player.getHeldItem(hand);
        if (world.isRemote) {
            return new ActionResult<>(ActionResultType.PASS, heldStack);
        } else {
            RayTraceResult raytrace = rayTrace(world, player, RayTraceContext.FluidMode.SOURCE_ONLY);
            if (raytrace.getType() != RayTraceResult.Type.BLOCK) {
                return new ActionResult<>(ActionResultType.PASS, heldStack);
            } else {
                BlockRayTraceResult blockRaytrace = (BlockRayTraceResult)raytrace;
                BlockPos pos = blockRaytrace.getPos();
                if (!(world.getBlockState(pos).getBlock() instanceof FlowingFluidBlock)) {
                    return super.onItemRightClick(world, player, hand);
                } else if (world.isBlockModifiable(player, pos) && player.canPlayerEdit(pos, blockRaytrace.getFace(), heldStack)) {
                    if (this.fishType.spawn(world, heldStack, player, pos, SpawnReason.SPAWN_EGG, false, false) == null) {
                        return new ActionResult<>(ActionResultType.PASS, heldStack);
                    } else {
                        if (!player.abilities.isCreativeMode) {
                            heldStack.shrink(1);
                        }
                        this.onLiquidPlaced(world, heldStack, pos);
                        player.addStat(Stats.ITEM_USED.get(this));
                        return new ActionResult<>(ActionResultType.SUCCESS, heldStack);
                    }
                } else {
                    return new ActionResult<>(ActionResultType.FAIL, heldStack);
                }
            }
        }
    }
}
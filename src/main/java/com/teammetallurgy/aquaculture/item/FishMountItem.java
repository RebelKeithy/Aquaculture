package com.teammetallurgy.aquaculture.item;

import com.teammetallurgy.aquaculture.entity.FishMountEntity;
import com.teammetallurgy.aquaculture.init.AquaEntities;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.HangingEntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class FishMountItem extends HangingEntityItem {
    public FishMountItem(Properties properties) {
        super(AquaEntities.FISH_MOUNT, properties);
    }

    @Override
    public ActionResultType onItemUse(ItemUseContext context) {
        BlockPos blockPos = context.getPos();
        Direction direction = context.getFace();
        BlockPos offset = blockPos.offset(direction);
        PlayerEntity player = context.getPlayer();
        ItemStack itemStack = context.getItem();
        if (player != null && !this.canPlace(player, direction, itemStack, offset)) {
            return ActionResultType.FAIL;
        } else {
            World world = context.getWorld();
            FishMountEntity fishMountEntity = new FishMountEntity(world, offset, direction);

            CompoundNBT tag = itemStack.getTag();
            if (tag != null) {
                EntityType.applyItemNBT(world, player, fishMountEntity, tag);
            }

            if (fishMountEntity.onValidSurface()) {
                if (!world.isRemote) {
                    fishMountEntity.playPlaceSound();
                    world.addEntity(fishMountEntity);
                }

                itemStack.shrink(1);
            }

            return ActionResultType.SUCCESS;
        }
    }

    protected boolean canPlace(PlayerEntity entity, Direction direction, ItemStack itemStack, BlockPos blockPos) {
        return !World.isOutsideBuildHeight(blockPos) && entity.canPlayerEdit(blockPos, direction, itemStack);
    }
}

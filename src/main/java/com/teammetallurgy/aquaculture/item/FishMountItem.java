package com.teammetallurgy.aquaculture.item;

import com.teammetallurgy.aquaculture.Aquaculture;
import com.teammetallurgy.aquaculture.entity.FishMountEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.HangingEntityItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public class FishMountItem extends HangingEntityItem {
    private final EntityType<? extends FishMountEntity> fishMount;

    public FishMountItem(EntityType<? extends FishMountEntity> entityType) {
        super(entityType, new Item.Properties().group(Aquaculture.GROUP));
        this.fishMount = entityType;
    }

    @Override
    @Nonnull
    public ActionResultType onItemUse(ItemUseContext context) {
        BlockPos pos = context.getPos();
        Direction direction = context.getFace();
        BlockPos offset = pos.offset(direction);
        PlayerEntity player = context.getPlayer();
        ItemStack useStack = context.getItem();
        if (player != null && !this.canPlace(player, direction, useStack, offset)) {
            return ActionResultType.FAIL;
        } else {
            World world = context.getWorld();
            FishMountEntity fishMountEntity = new FishMountEntity(this.fishMount, world, offset, direction);

            CompoundNBT tag = useStack.getTag();
            if (tag != null) {
                EntityType.applyItemNBT(world, player, fishMountEntity, tag);
            }

            if (fishMountEntity.onValidSurface()) {
                if (!world.isRemote) {
                    fishMountEntity.playPlaceSound();
                    world.addEntity(fishMountEntity);
                }
                useStack.shrink(1);
            }
            return ActionResultType.SUCCESS;
        }
    }

    @Override
    protected boolean canPlace(@Nonnull PlayerEntity entity, Direction direction, @Nonnull ItemStack stack, @Nonnull BlockPos pos) {
        return !World.isOutsideBuildHeight(pos) && entity.canPlayerEdit(pos, direction, stack);
    }
}
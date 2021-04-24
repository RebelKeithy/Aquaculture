package com.teammetallurgy.aquaculture.item.neptunium;

import com.teammetallurgy.aquaculture.Aquaculture;
import com.teammetallurgy.aquaculture.init.AquaBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.HoeItem;
import net.minecraft.item.IItemTier;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.ForgeEventFactory;

import javax.annotation.Nonnull;

public class NeptuniumHoe extends HoeItem {

    public NeptuniumHoe(IItemTier tier, int damage, float speed) {
        super(tier, damage, speed, new Item.Properties().group(Aquaculture.GROUP));
    }

    @Override
    @Nonnull
    public ActionResultType onItemUse(ItemUseContext useContext) {
        World world = useContext.getWorld();
        BlockPos pos = useContext.getPos();
        int hook = ForgeEventFactory.onHoeUse(useContext);
        if (hook != 0) {
            if (hook >= 0) {
                return ActionResultType.FAIL;
            }
            BlockState state = world.getBlockState(pos);
            if (state != null) {
                if (state == Blocks.FARMLAND.getDefaultState()) {
                    world.setBlockState(pos, AquaBlocks.FARMLAND.getDefaultState(), 11);
                }
            }
            return ActionResultType.SUCCESS;
        } else {
            if (useContext.getFace() != Direction.DOWN && world.isAirBlock(pos.up())) {
                BlockState state = HOE_LOOKUP.get(world.getBlockState(pos).getBlock());
                if (state != null) {
                    PlayerEntity player = useContext.getPlayer();
                    world.playSound(player, pos, SoundEvents.ITEM_HOE_TILL, SoundCategory.BLOCKS, 1.0F, 1.0F);
                    if (!world.isRemote) {
                        if (state == Blocks.FARMLAND.getDefaultState()) {
                            world.setBlockState(pos, AquaBlocks.FARMLAND.getDefaultState(), 11);
                        } else {
                            world.setBlockState(pos, state, 11);
                        }
                        if (player != null) {
                            useContext.getItem().damageItem(1, player, (livingEntity) -> livingEntity.sendBreakAnimation(useContext.getHand()));
                        }
                    }
                    return ActionResultType.SUCCESS;
                }
            }
            return ActionResultType.PASS;
        }
    }
}
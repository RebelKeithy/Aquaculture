package com.teammetallurgy.aquaculture.item.neptunium;

import com.mojang.datafixers.util.Pair;
import com.teammetallurgy.aquaculture.Aquaculture;
import com.teammetallurgy.aquaculture.init.AquaBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.ForgeEventFactory;

import javax.annotation.Nonnull;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class NeptuniumHoe extends HoeItem {

    public NeptuniumHoe(Tier tier, int damage, float speed) {
        super(tier, damage, speed, new Item.Properties().tab(Aquaculture.GROUP));
    }

    @Override
    @Nonnull
    public InteractionResult useOn(UseOnContext useContext) {
        Level level = useContext.getLevel();
        BlockPos pos = useContext.getClickedPos();
        BlockState state = level.getBlockState(pos);
        int hook = ForgeEventFactory.onHoeUse(useContext);
        if (hook != 0) {
            if (hook < 0) {
                return InteractionResult.FAIL;
            }
            if (state == Blocks.FARMLAND.defaultBlockState()) {
                level.setBlock(pos, AquaBlocks.FARMLAND.defaultBlockState(), 11);
            }
            return InteractionResult.SUCCESS;
        } else {
            if (useContext.getClickedFace() != Direction.DOWN && level.isEmptyBlock(pos.above())) {
                Pair<Predicate<UseOnContext>, Consumer<UseOnContext>> pair = TILLABLES.get(level.getBlockState(pos).getBlock());
                if (pair != null) {
                    Predicate<UseOnContext> predicate = pair.getFirst();
                    Consumer<UseOnContext> consumer = pair.getSecond();
                    if (predicate.test(useContext)) {
                        Player player = useContext.getPlayer();
                        level.playSound(player, pos, SoundEvents.HOE_TILL, SoundSource.BLOCKS, 1.0F, 1.0F);
                        if (!level.isClientSide) {
                            consumer.accept(useContext);
                            if (state == Blocks.FARMLAND.defaultBlockState()) {
                                level.setBlock(pos, AquaBlocks.FARMLAND.defaultBlockState(), 11);
                            } else {
                                level.setBlock(pos, state, 11);
                            }
                            if (player != null) {
                                useContext.getItemInHand().hurtAndBreak(1, player, (livingEntity) -> livingEntity.broadcastBreakEvent(useContext.getHand()));
                            }
                        }
                        return InteractionResult.sidedSuccess(level.isClientSide);
                    }
                }
            }
            return InteractionResult.PASS;
        }
    }
}
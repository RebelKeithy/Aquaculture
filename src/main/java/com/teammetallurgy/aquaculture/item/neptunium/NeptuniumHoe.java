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

import javax.annotation.Nonnull;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class NeptuniumHoe extends HoeItem {

    public NeptuniumHoe(Tier tier, int damage, float speed) {
        super(tier, damage, speed, new Item.Properties().tab(Aquaculture.GROUP));
    }

    @Override
    @Nonnull
    public InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        BlockPos pos = context.getClickedPos();
        Pair<Predicate<UseOnContext>, Consumer<UseOnContext>> tillables = TILLABLES.get(level.getBlockState(pos).getBlock());
        if (context.getClickedFace() != Direction.DOWN && level.isEmptyBlock(pos.above())) {
            if (tillables == null) {
                return InteractionResult.PASS;
            } else {
                Predicate<UseOnContext> predicate = tillables.getFirst();
                if (predicate.test(context)) {
                    Player player = context.getPlayer();
                    level.playSound(player, pos, SoundEvents.HOE_TILL, SoundSource.BLOCKS, 1.0F, 1.0F);
                    if (!level.isClientSide) {
                        level.setBlock(pos, AquaBlocks.FARMLAND.defaultBlockState(), 2);
                        if (player != null) {
                            context.getItemInHand().hurtAndBreak(1, player, (p_150845_) -> {
                                p_150845_.broadcastBreakEvent(context.getHand());
                            });
                        }
                    }
                    return InteractionResult.sidedSuccess(level.isClientSide);
                } else {
                    return InteractionResult.PASS;
                }
            }
        }
        return InteractionResult.PASS;
    }
}
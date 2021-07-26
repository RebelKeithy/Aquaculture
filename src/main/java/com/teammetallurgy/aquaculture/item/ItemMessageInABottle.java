package com.teammetallurgy.aquaculture.item;

import com.teammetallurgy.aquaculture.Aquaculture;
import com.teammetallurgy.aquaculture.misc.AquaConfig;
import com.teammetallurgy.aquaculture.misc.AquacultureSounds;
import net.minecraft.Util;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import javax.annotation.Nonnull;

public class ItemMessageInABottle extends Item {

    public ItemMessageInABottle() {
        super(new Item.Properties().tab(Aquaculture.GROUP));
    }

    @Override
    @Nonnull
    public InteractionResultHolder<ItemStack> use(Level world, Player player, @Nonnull InteractionHand hand) {
        ItemStack heldStack = player.getItemInHand(hand);
        world.playSound(null, player.getX(), player.getY(), player.getZ(), AquacultureSounds.BOTTLE_OPEN, SoundSource.PLAYERS, 0.5F, 0.4F / (world.random.nextFloat() * 0.4F + 0.8F));

        if (player instanceof ServerPlayer) {
            player.sendMessage(new TranslatableComponent("aquaculture.message" + world.random.nextInt(AquaConfig.BASIC_OPTIONS.messageInABottleAmount.get() + 1)), Util.NIL_UUID);
        }
        heldStack.shrink(1);

        return super.use(world, player, hand);
    }
}
package com.teammetallurgy.aquaculture.item;

import com.teammetallurgy.aquaculture.Aquaculture;
import com.teammetallurgy.aquaculture.misc.AquaConfig;
import com.teammetallurgy.aquaculture.misc.AquacultureSounds;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.Util;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public class ItemMessageInABottle extends Item {

    public ItemMessageInABottle() {
        super(new Item.Properties().group(Aquaculture.GROUP));
    }

    @Override
    @Nonnull
    public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, @Nonnull Hand hand) {
        ItemStack heldStack = player.getHeldItem(hand);
        world.playSound(null, player.getPosX(), player.getPosY(), player.getPosZ(), AquacultureSounds.BOTTLE_OPEN, SoundCategory.PLAYERS, 0.5F, 0.4F / (world.rand.nextFloat() * 0.4F + 0.8F));

        if (player instanceof ServerPlayerEntity) {
            player.sendMessage(new TranslationTextComponent("aquaculture.message" + world.rand.nextInt(AquaConfig.BASIC_OPTIONS.messageInABottleAmount.get() + 1)), Util.DUMMY_UUID);
        }
        heldStack.shrink(1);

        return super.onItemRightClick(world, player, hand);
    }
}
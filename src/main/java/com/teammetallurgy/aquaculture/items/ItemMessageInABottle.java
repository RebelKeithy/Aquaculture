package com.teammetallurgy.aquaculture.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import java.util.Random;

public class ItemMessageInABottle extends Item {
    private Random rand = new Random();

    public ItemMessageInABottle(Properties properties) {
        super(properties);
    }

    @Override
    @Nonnull
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, @Nonnull EnumHand hand) {
        ItemStack heldStack = player.getHeldItem(hand);
        world.playSound(null, player.posX, player.posY, player.posZ, SoundEvents.BLOCK_GLASS_BREAK, SoundCategory.PLAYERS, 0.5F, 0.4F / (rand.nextFloat() * 0.4F + 0.8F));

        int messageRoll = rand.nextInt(29) + 1;

        String message;
        if (messageRoll == 0) {
            message = "ERROR! Fish Escaping!";
        } else {
            message = "aquaculture.message" + messageRoll;
        }

        if (player instanceof EntityPlayerMP) {
            TextComponentTranslation chatMessage = new TextComponentTranslation(message);
            player.sendMessage(chatMessage);
        }

        heldStack.shrink(1);

        return super.onItemRightClick(world, player, hand);
    }
}
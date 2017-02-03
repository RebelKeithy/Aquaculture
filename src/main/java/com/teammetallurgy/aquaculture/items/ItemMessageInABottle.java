package com.teammetallurgy.aquaculture.items;

import com.teammetallurgy.aquaculture.items.meta.MetaItem;
import com.teammetallurgy.aquaculture.items.meta.SubItem;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;

import java.util.Random;

public class ItemMessageInABottle extends SubItem {
    Random rand = new Random();

    public ItemMessageInABottle(MetaItem par1) {
        super(par1);
    }

    @Override
    public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
        if (par2World.isRemote)
            return par1ItemStack;

        par2World.playSound(null, par3EntityPlayer.posX, par3EntityPlayer.posY, par3EntityPlayer.posZ, SoundEvents.BLOCK_GLASS_BREAK, SoundCategory.PLAYERS, 0.5F, 0.4F / (rand.nextFloat() * 0.4F + 0.8F));

        int messageRoll = rand.nextInt(29) + 1;

        String message = "";
        if (messageRoll == 0) {
            message = "ERROR! Fish Escaping!";
        } else {
            message = "aquaculture.message" + messageRoll;
        }

        if (par3EntityPlayer instanceof EntityPlayerMP) {

            TextComponentTranslation chatMessage = new TextComponentTranslation(message);
            par3EntityPlayer.sendMessage(chatMessage);
        }

        par1ItemStack.shrink(1);
        return par1ItemStack;
    }
}

package com.teammetallurgy.aquaculture.items;

import com.teammetallurgy.aquaculture.items.meta.MetaItem;
import com.teammetallurgy.aquaculture.items.meta.SubItem;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.StatCollector;
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

        par2World.playSoundAtEntity(par3EntityPlayer, "random.glass", 0.5F, 0.4F / (rand.nextFloat() * 0.4F + 0.8F));

        int messageRoll = rand.nextInt(29) + 1;

        String message = "";
        if (messageRoll == 0) {
            message = "ERROR! Fish Escaping!";
        } else {
            message = "aquaculture.message" + messageRoll;
        }

        if (par3EntityPlayer instanceof EntityPlayerMP) {

            ChatComponentText chatMessage = new ChatComponentText(StatCollector.translateToLocal(message));
            par3EntityPlayer.addChatMessage(chatMessage);
        }

        --par1ItemStack.stackSize;
        return par1ItemStack;
    }
}

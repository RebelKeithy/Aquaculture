package com.teammetallurgy.aquaculture.items;

import com.teammetallurgy.aquaculture.items.meta.MetaItem;
import com.teammetallurgy.aquaculture.items.meta.SubItem;
import com.teammetallurgy.aquaculture.loot.WeightedLootSet;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import java.util.Random;

public class ItemLockbox extends SubItem {
    public WeightedLootSet loot;
    Random rand = new Random();

    public ItemLockbox(MetaItem par1) {
        super(par1);
        loot = new WeightedLootSet();
        loot.addLoot(Items.IRON_INGOT, 8);
        loot.addLoot(Items.GOLD_INGOT, 7);
        loot.addLoot(Items.GOLDEN_APPLE, 5);
        loot.addLoot(new ItemStack(Items.DYE, 1, 4), 8);
        loot.addLoot(Items.REDSTONE, 8);
        loot.addLoot(Items.BOOK, 10);
        loot.addLoot(Items.PAPER, 10);
        loot.addLoot(Items.COMPASS, 10);
        loot.addLoot(Items.CLOCK, 10);
        loot.addLoot(Items.GLOWSTONE_DUST, 7);
        loot.addLoot(Items.GUNPOWDER, 10);
        loot.addLoot(Items.QUARTZ, 7);
    }

    @Override
    public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
        if (par2World.isRemote)
            return par1ItemStack;

        ItemStack randomLoot = loot.getRandomLoot();

        EntityItem entityitem = new EntityItem(par3EntityPlayer.worldObj, par3EntityPlayer.posX, par3EntityPlayer.posY, par3EntityPlayer.posZ, randomLoot);
        par2World.spawnEntityInWorld(entityitem);

        --par1ItemStack.stackSize;
        return par1ItemStack;
    }
}

package com.teammetallurgy.aquaculture.items;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import java.util.Random;

import com.teammetallurgy.aquaculture.items.meta.MetaItem;
import com.teammetallurgy.aquaculture.items.meta.SubItem;
import com.teammetallurgy.aquaculture.loot.WeightedLootSet;

public class ItemLockbox extends SubItem {
    public WeightedLootSet loot;
    Random rand = new Random();

    public ItemLockbox(MetaItem par1) {
        super(par1);
        loot = new WeightedLootSet();
        loot.addLoot(Items.iron_ingot, 8);
        loot.addLoot(Items.gold_ingot, 7);
        loot.addLoot(Items.golden_apple, 5);
        loot.addLoot(new ItemStack(Items.dye, 1, 4), 8);
        loot.addLoot(Items.redstone, 8);
        loot.addLoot(Items.book, 10);
        loot.addLoot(Items.paper, 10);
        loot.addLoot(Items.compass, 10);
        loot.addLoot(Items.clock, 10);
        loot.addLoot(Items.glowstone_dust, 7);
        loot.addLoot(Items.gunpowder, 10);
        loot.addLoot(Items.quartz, 7);
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

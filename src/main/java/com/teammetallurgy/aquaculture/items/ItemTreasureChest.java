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

public class ItemTreasureChest extends SubItem {
    Random rand = new Random();
    WeightedLootSet loot;

    public ItemTreasureChest(MetaItem par1) {
        super(par1);
        loot = new WeightedLootSet();
        loot.addLoot(Items.IRON_INGOT, 40);
        loot.addLoot(Items.GOLD_INGOT, 30);
        loot.addLoot(Items.DIAMOND, 15);
        loot.addLoot(Items.EMERALD, 15);
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

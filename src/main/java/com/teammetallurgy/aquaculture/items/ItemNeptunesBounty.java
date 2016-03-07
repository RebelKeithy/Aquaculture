package com.teammetallurgy.aquaculture.items;

import com.teammetallurgy.aquaculture.items.meta.MetaItem;
import com.teammetallurgy.aquaculture.items.meta.SubItem;
import com.teammetallurgy.aquaculture.loot.WeightedLootSet;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import java.util.Random;

public class ItemNeptunesBounty extends SubItem {
    Random rand = new Random();
    WeightedLootSet loot;

    public ItemNeptunesBounty(MetaItem par1) {
        super(par1);
        loot = null;
    }

    public void initLoot() {
        loot = new WeightedLootSet();

        loot.addLoot(AquacultureItems.neptuniumAxe, 1, 1, 1);
        loot.addLoot(AquacultureItems.neptuniumPickaxe, 1, 1, 1);
        loot.addLoot(AquacultureItems.neptuniumShovel, 1, 1, 1);
        loot.addLoot(AquacultureItems.neptuniumHoe, 1, 1, 1);
        loot.addLoot(AquacultureItems.neptuniumSword, 1, 1, 1);
        loot.addLoot(AquacultureItems.neptuniumHelmet, 1, 1, 1);
        loot.addLoot(AquacultureItems.neptuniumPlate, 1, 1, 1);
        loot.addLoot(AquacultureItems.neptuniumLegs, 1, 1, 1);
        loot.addLoot(AquacultureItems.neptuniumBoots, 1, 1, 1);
    }

    public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
        if (par2World.isRemote)
            return par1ItemStack;

        if (loot == null)
            initLoot();

        ItemStack item = loot.getRandomLoot();

        EntityItem entityitem = new EntityItem(par3EntityPlayer.worldObj, par3EntityPlayer.posX, par3EntityPlayer.posY, par3EntityPlayer.posZ, item);
        par2World.spawnEntityInWorld(entityitem);

        --par1ItemStack.stackSize;
        return par1ItemStack;
    }
}

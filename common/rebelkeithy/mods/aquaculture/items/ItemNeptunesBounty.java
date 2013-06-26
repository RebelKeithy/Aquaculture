package rebelkeithy.mods.aquaculture.items;

import java.util.Random;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import rebelkeithy.mods.aquaculture.AquacultureItems;
import rebelkeithy.mods.keithyutils.loot.WeightedLootSet;
import rebelkeithy.mods.keithyutils.metaitem.SubItem;

public class ItemNeptunesBounty extends SubItem
{
	Random rand = new Random();
	WeightedLootSet loot;
	
    public ItemNeptunesBounty(int par1)
    {
        super(par1);
        loot = null;
    }
    
    public void initLoot()
    {
        loot = new WeightedLootSet();
        
        loot.addLoot(AquacultureItems.NeptuniumAxe, 1, 1, 1);
        loot.addLoot(AquacultureItems.NeptuniumPickaxe, 1, 1, 1);
        loot.addLoot(AquacultureItems.NeptuniumShovel, 1, 1, 1);
        loot.addLoot(AquacultureItems.NeptuniumHoe, 1, 1, 1);
        loot.addLoot(AquacultureItems.NeptuniumSword, 1, 1, 1);
        loot.addLoot(AquacultureItems.NeptuniumHelmet, 1, 1, 1);
        loot.addLoot(AquacultureItems.NeptuniumPlate, 1, 1, 1);
        loot.addLoot(AquacultureItems.NeptuniumLegs, 1, 1, 1);
        loot.addLoot(AquacultureItems.NeptuniumBoots, 1, 1, 1);
    }
    
    public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
    {
    	if(par2World.isRemote)
    		return par1ItemStack;
    	
    	if(loot == null)
    		initLoot();
    	
        ItemStack item = loot.getRandomLoot();
        
        EntityItem entityitem = new EntityItem(par3EntityPlayer.worldObj, par3EntityPlayer.posX, par3EntityPlayer.posY, par3EntityPlayer.posZ, item);
        par2World.spawnEntityInWorld(entityitem);
        
        --par1ItemStack.stackSize;
        return par1ItemStack;
    }
}
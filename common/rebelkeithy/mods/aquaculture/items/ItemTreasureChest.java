package rebelkeithy.mods.aquaculture.items;

import java.util.Random;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import rebelkeithy.mods.keithyutils.metaitem.SubItem;

public class ItemTreasureChest extends SubItem
{
	Random rand = new Random();
	
    public ItemTreasureChest(int par1)
    {
        super(par1);
    }
    
    public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
    {
    	if(par2World.isRemote)
    		return par1ItemStack;
    	
        int lootRoll = rand.nextInt(3) + 1;
        
        Item loot = null;
        switch(lootRoll)
        {
        	case 1: loot = Item.ingotIron; break;
        	case 2: loot = Item.ingotGold; break;
        	case 3: loot = Item.diamond; break;
        }
        
        EntityItem entityitem = new EntityItem(par3EntityPlayer.worldObj, par3EntityPlayer.posX, par3EntityPlayer.posY, par3EntityPlayer.posZ, new ItemStack(loot));
        par2World.spawnEntityInWorld(entityitem);
        
        --par1ItemStack.stackSize;
        return par1ItemStack;
    }
}
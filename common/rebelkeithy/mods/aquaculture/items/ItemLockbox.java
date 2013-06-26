package rebelkeithy.mods.aquaculture.items;

import java.util.Random;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import rebelkeithy.mods.keithyutils.metaitem.SubItem;

public class ItemLockbox extends SubItem
{
	Random rand = new Random();
	
    public ItemLockbox(int par1)
    {
        super(par1);
    }
    
    @Override
    public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
    {
    	if(par2World.isRemote)
    		return par1ItemStack;
    	
        int lootRoll = rand.nextInt(15) + 1;
        
        Item loot = null;
        switch(lootRoll)
        {
	        case 1: loot = Item.bucketEmpty; break;
	        case 2: loot = Item.coal; break;
	        case 3: loot = Item.compass; break;
	        case 4: loot = Item.glassBottle; break;
	        case 5: loot = Item.helmetIron; break;
	        case 6: loot = Item.legsIron; break;
	        case 7: loot = Item.plateIron; break;
	        case 8: loot = Item.bootsIron; break;
	        case 9: loot = Item.paper; break;
	        case 10: loot = Item.saddle; break;
	        case 11: loot = Item.slimeBall; break;
	        case 12: loot = Item.ingotIron; break;
	        case 13: loot = Item.ingotGold; break;
	        case 14: loot = Item.map; break;
	        case 15: loot = Item.appleGold; break;
	        default: loot = Item.appleGold; break;
        }
        
        EntityItem entityitem = new EntityItem(par3EntityPlayer.worldObj, par3EntityPlayer.posX, par3EntityPlayer.posY, par3EntityPlayer.posZ, new ItemStack(loot));
        par2World.spawnEntityInWorld(entityitem);
        
        --par1ItemStack.stackSize;
        return par1ItemStack;
    }
}
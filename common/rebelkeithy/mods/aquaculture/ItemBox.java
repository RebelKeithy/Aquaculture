package rebelkeithy.mods.aquaculture;

import java.util.Random;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemBox extends Item
{
	Random rand = new Random();
	
    public ItemBox(int par1)
    {
        super(par1);
    }
    
    public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
    {
    	if(par2World.isRemote)
    		return par1ItemStack;
    	
        int lootRoll = rand.nextInt(21) + 1;
        
        Item loot = null;
        switch(lootRoll)
        {
	        case 1: loot = Item.appleRed; break;
	        case 2: loot = Item.book; break;
	        case 3: loot = Item.arrow; break;
	        case 4: loot = Item.bone; break;
	        case 5: loot = Item.bucketEmpty; break;
	        case 6: loot = Item.coal; break;
	        case 7: loot = Item.compass; break;
	        case 8: loot = Item.glassBottle; break;
	        case 9: loot = Item.helmetLeather; break;
	        case 10: loot = Item.legsLeather; break;
	        case 11: loot = Item.plateLeather; break;
	        case 12: loot = Item.bootsLeather; break;
	        case 13: loot = Item.paper; break;
	        case 14: loot = Item.reed; break;
	        case 15: loot = Item.saddle; break;
	        case 16: loot = Item.sign; break;
	        case 17: loot = Item.slimeBall; break;
	        case 18: loot = Item.silk; break;
	        case 19: loot = Item.stick; break;
	        case 20: loot = Item.wheat; break;
	        case 21: loot = Item.map; break;
	        default: loot = Item.appleGold; break;
        }
        
        EntityItem entityitem = new EntityItem(par3EntityPlayer.worldObj, par3EntityPlayer.posX, par3EntityPlayer.posY, par3EntityPlayer.posZ, new ItemStack(loot));
        par2World.spawnEntityInWorld(entityitem);
        
        --par1ItemStack.stackSize;
        return par1ItemStack;
    }
}
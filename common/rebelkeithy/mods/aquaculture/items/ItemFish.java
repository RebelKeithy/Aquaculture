package rebelkeithy.mods.aquaculture.items;

import java.util.ArrayList;
import java.util.List;

import rebelkeithy.mods.aquaculture.AquacultureItems;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemFish extends ItemFood
{
	public List<Fish> fish;
	
	public class Fish
	{
		public String name;
		public int filletAmount;
		public Icon icon;
		
		public Fish(String name, int amount)
		{
			this.name = name;
			filletAmount = amount;
		}
	}
	
	public ItemFish(int par1) 
	{
		super(par1, 0, false);
        this.setHasSubtypes(true);
        this.setMaxDamage(0);
		
		fish = new ArrayList<Fish>();
	}
	
	public void addFish(String name, int filletAmount)
	{
		fish.add(new Fish(name, filletAmount));
		LanguageRegistry.addName(new ItemStack(itemID, 1, fish.size()-1), name);
	}
	
	public void addFilletRecipes()
	{
		for(int i = 0; i < fish.size(); i++)
		{
			Fish f = fish.get(i);
			if(f.filletAmount != 0)
			{
				GameRegistry.addShapelessRecipe(AquacultureItems.FishFillet.getItemStack(f.filletAmount), new ItemStack(itemID, 1, i));
			}
		}
	}

	public ItemStack getFish(String name)
	{
		for(int i = 0; i < fish.size(); i++)
		{
			if(fish.get(i).name.equals(name))
			{
				return new ItemStack(itemID, 1, i);
			}
		}
		
		return null;
	}

    public ItemStack onEaten(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
    {
    	//TODO: fix this
        --par1ItemStack.stackSize;
        par3EntityPlayer.getFoodStats().addStats(this);
        par2World.playSoundAtEntity(par3EntityPlayer, "random.burp", 0.5F, par2World.rand.nextFloat() * 0.1F + 0.9F);
        this.onFoodEaten(par1ItemStack, par2World, par3EntityPlayer);
        return par1ItemStack;
    }

    @SideOnly(Side.CLIENT)
    /**
     * Gets an icon index based on an item's damage value
     */
    public Icon getIconFromDamage(int par1)
    {
        int j = MathHelper.clamp_int(par1, 0, fish.size());
        return fish.get(j).icon;
    }

    /**
     * Returns the unlocalized name of this item. This version accepts an ItemStack so different stacks can have
     * different names based on their damage or NBT.
     */
    public String getUnlocalizedName(ItemStack par1ItemStack)
    {
        int i = MathHelper.clamp_int(par1ItemStack.getItemDamage(), 0, fish.size());
        
        return super.getUnlocalizedName() + "." + fish.get(i).name;
    }

    @SideOnly(Side.CLIENT)
    /**
     * returns a list of items with the same ID, but different meta (eg: dye returns 16 items)
     */
    public void getSubItems(int par1, CreativeTabs par2CreativeTabs, List par3List)
    {
        for (int j = 0; j < fish.size(); ++j)
        {
        	System.out.println("adding ");
            par3List.add(new ItemStack(par1, 1, j));
        }
    }

    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister par1IconRegister)
    {
        for(Fish f : fish)
        {
        	f.icon = par1IconRegister.registerIcon("Aquaculture:" + f.name);
        }
    }
}

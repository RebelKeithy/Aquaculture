package rebelkeithy.mods.aquaculture.items;

import rebelkeithy.mods.aquaculture.AquacultureItem;
import rebelkeithy.mods.aquaculture.EntityCustomFishHook;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemAquacultureFishingRod extends AquacultureItem
{
	public Icon usingIcon;
	public String type;
	public int enchantability;
	
    public ItemAquacultureFishingRod(int i, int d, int enchantability, String type)
    {
        super(i);
        setMaxDamage(d);
        setMaxStackSize(1);
        this.type = type;
        this.enchantability = enchantability;
    }

    @SideOnly(Side.CLIENT)

    /**
     * Returns true if this item should be rotated by 180 degrees around the Y axis when being held in an entities
     * hands.
     */
    public boolean shouldRotateAroundWhenRendering()
    {
        return true;
    }
    
    @Override
    public boolean isItemTool(ItemStack par1ItemStack)
    {
    	return true;
    }
    
    public int getItemEnchantability()
    {
    	return enchantability;
    }


    public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer entityplayer)
    {
        if (entityplayer.fishEntity != null)
        {
            int i = entityplayer.fishEntity.catchFish();
            itemstack.damageItem(i, entityplayer);
            entityplayer.swingItem();

        	if(!itemstack.hasTagCompound())
        		itemstack.setTagCompound(new NBTTagCompound());
        	
        	NBTTagCompound tag = itemstack.getTagCompound();
        	tag.setBoolean("using", false);
        }
        else
        {
            world.playSoundAtEntity(entityplayer, "random.bow", 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));
            if (!world.isRemote)
            {
                world.spawnEntityInWorld(new EntityCustomFishHook(world, entityplayer));
            }
            entityplayer.swingItem();

        	if(!itemstack.hasTagCompound())
        		itemstack.setTagCompound(new NBTTagCompound());
        	
        	NBTTagCompound tag = itemstack.getTagCompound();
        	tag.setBoolean("using", true);
        }
        return itemstack;
    }

    public Icon getIcon(ItemStack stack, int renderPass, EntityPlayer player, ItemStack usingItem, int useRemaining)
    {
    	if(!stack.hasTagCompound())
    		stack.setTagCompound(new NBTTagCompound());
    	
    	NBTTagCompound tag = stack.getTagCompound();
    	
    	if(tag.hasKey("using"));
    	{
    		boolean using = tag.getBoolean("using");
    		
    		if(using)
    			return usingIcon;
    	}
    	
    	return itemIcon;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void registerIcons(IconRegister par1IconRegister)
    {
        super.registerIcons(par1IconRegister);
        
        usingIcon = par1IconRegister.registerIcon(type + "FishingRodUsing");
    }
}
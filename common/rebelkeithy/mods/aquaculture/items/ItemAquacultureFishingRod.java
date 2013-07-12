package rebelkeithy.mods.aquaculture.items;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumHelper;
import rebelkeithy.mods.aquaculture.EntityCustomFishHook;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemAquacultureFishingRod extends ItemTool
{
	public Icon usingIcon;
	public String type;
	public int enchantability;
	
    public ItemAquacultureFishingRod(int i, int d, int enchantability, String type)
    {
        super(i, 0, EnumHelper.addToolMaterial("Fishing" + type, 0, d, 0, 0, enchantability), new Block[] {});
        setMaxDamage(d);
        setMaxStackSize(1);
        this.type = type;
        this.enchantability = enchantability;
    }

    @SideOnly(Side.CLIENT)

    /**
     * Returns True is the item is renderer in full 3D when hold.
     */
    @Override
    public boolean isFull3D()
    {
        return true;
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
    
    public int getItemEnchantability()
    {
    	return enchantability;
    }
    
    public Multimap func_111205_h()
    {
        return HashMultimap.create();
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
    		{
    			System.out.println("test");
    			return usingIcon;
    		}
    	}
    	
    	return itemIcon;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void registerIcons(IconRegister par1IconRegister)
    {
        super.registerIcons(par1IconRegister);
        
        usingIcon = par1IconRegister.registerIcon("aquaculture:" + type + "FishingRodUsing");
    }
}
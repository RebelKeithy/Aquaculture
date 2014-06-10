package rebelkeithy.mods.aquaculture.items;

import java.util.Set;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraftforge.common.util.EnumHelper;
import rebelkeithy.mods.aquaculture.EntityCustomFishHook;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.google.common.collect.Sets;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemAquacultureFishingRod extends ItemTool {
	public IIcon usingIcon;
	public String type;
	public int enchantability;
	private static Set effectiveBlockSet = Sets.newHashSet(new Block[]{});

	public ItemAquacultureFishingRod(int d, int enchantability, String type) {
		super(0F, EnumHelper.addToolMaterial("Fishing" + type, 0, d, 0, 0, enchantability), effectiveBlockSet);
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
	public boolean isFull3D() {
		return true;
	}

	@SideOnly(Side.CLIENT)
	/**
	 * Returns true if this item should be rotated by 180 degrees around the Y axis when being held in an entities
	 * hands.
	 */
	@Override
	public boolean shouldRotateAroundWhenRendering() {
		return true;
	}

	@Override
	public int getItemEnchantability() {
		return enchantability;
	}

	@Override
	public Multimap getItemAttributeModifiers() {
		return HashMultimap.create();
	}

	@Override
	public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer entityplayer) {
		if(entityplayer.fishEntity != null) {
			int i = entityplayer.fishEntity.func_146034_e();
			itemstack.damageItem(i, entityplayer);
			entityplayer.swingItem();

			if(!itemstack.hasTagCompound())
				itemstack.setTagCompound(new NBTTagCompound());

			NBTTagCompound tag = itemstack.getTagCompound();
			tag.setBoolean("using", false);
		} else {
			world.playSoundAtEntity(entityplayer, "random.bow", 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));
			if(!world.isRemote) {
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

	@Override
	public IIcon getIcon(ItemStack stack, int renderPass, EntityPlayer player, ItemStack usingItem, int useRemaining) {
		if(!stack.hasTagCompound())
			stack.setTagCompound(new NBTTagCompound());

		NBTTagCompound tag = stack.getTagCompound();

		if(tag.hasKey("using"))
			;
		{
			boolean using = tag.getBoolean("using");

			if(using) {
				return usingIcon;
			}
		}

		return itemIcon;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerIcons(IIconRegister par1IconRegister) {
		super.registerIcons(par1IconRegister);

		usingIcon = par1IconRegister.registerIcon("aquaculture:" + type + "FishingRodUsing");
	}
}
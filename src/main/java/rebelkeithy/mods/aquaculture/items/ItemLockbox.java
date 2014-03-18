package rebelkeithy.mods.aquaculture.items;

import java.util.Random;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import rebelkeithy.mods.aquaculture.WeightedLootSet;
import rebelkeithy.mods.aquaculture.items.meta.SubItem;

public class ItemLockbox extends SubItem {
	Random rand = new Random();
	public WeightedLootSet loot;

	public ItemLockbox(int par1) {
		super(par1);
		loot = new WeightedLootSet();
		loot.addLoot(Item.ingotIron, 8);
		loot.addLoot(Item.ingotGold, 7);
		loot.addLoot(Item.appleGold, 5);
		loot.addLoot(new ItemStack(Item.dyePowder, 1, 4), 8);
		loot.addLoot(Item.redstone, 8);
		loot.addLoot(Item.book, 10);
		loot.addLoot(Item.paper, 10);
		loot.addLoot(Item.compass, 10);
		loot.addLoot(Item.pocketSundial, 10);
		loot.addLoot(Item.glowstone, 7);
		loot.addLoot(Item.gunpowder, 10);
		loot.addLoot(Item.netherQuartz, 7);
	}

	@Override
	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
		if(par2World.isRemote)
			return par1ItemStack;

		ItemStack randomLoot = loot.getRandomLoot();

		EntityItem entityitem = new EntityItem(par3EntityPlayer.worldObj, par3EntityPlayer.posX, par3EntityPlayer.posY, par3EntityPlayer.posZ, randomLoot);
		par2World.spawnEntityInWorld(entityitem);

		--par1ItemStack.stackSize;
		return par1ItemStack;
	}
}
package rebelkeithy.mods.aquaculture.items;

import java.util.Random;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import rebelkeithy.mods.aquaculture.WeightedLootSet;
import rebelkeithy.mods.aquaculture.items.meta.MetaItem;
import rebelkeithy.mods.aquaculture.items.meta.SubItem;

public class ItemTreasureChest extends SubItem {
	Random rand = new Random();
	WeightedLootSet loot;

	public ItemTreasureChest(MetaItem par1) {
		super(par1);
		loot = new WeightedLootSet();
		loot.addLoot(Items.iron_ingot, 40);
		loot.addLoot(Items.gold_ingot, 30);
		loot.addLoot(Items.diamond, 15);
		loot.addLoot(Items.emerald, 15);
	}

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
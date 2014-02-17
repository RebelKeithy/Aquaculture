package rebelkeithy.mods.aquaculture.items;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import rebelkeithy.mods.aquaculture.WeightedLootSet;
import rebelkeithy.mods.aquaculture.items.meta.SubItem;

public class ItemBox extends SubItem {
	public Random rand = new Random();
	public WeightedLootSet loot;

	public ItemBox(int par1) {
		super(par1);
		loot = new WeightedLootSet();
		loot.addLoot(Block.stone, 5, 1, 1);
		loot.addLoot(Block.dirt, 5, 1, 1);
		loot.addLoot(Block.cobblestone, 5);
		loot.addLoot(new ItemStack(Block.planks, 1, 0), 1);
		loot.addLoot(new ItemStack(Block.planks, 1, 1), 1);
		loot.addLoot(new ItemStack(Block.planks, 1, 2), 1);
		loot.addLoot(new ItemStack(Block.planks, 1, 3), 1);
		loot.addLoot(new ItemStack(Block.sapling, 1, 0), 1);
		loot.addLoot(new ItemStack(Block.sapling, 1, 1), 1);
		loot.addLoot(new ItemStack(Block.sapling, 1, 2), 1);
		loot.addLoot(new ItemStack(Block.sapling, 1, 3), 1);
		loot.addLoot(new ItemStack(Block.wood, 1, 0), 1);
		loot.addLoot(new ItemStack(Block.wood, 1, 1), 1);
		loot.addLoot(new ItemStack(Block.wood, 1, 2), 1);
		loot.addLoot(new ItemStack(Block.wood, 1, 3), 1);
		loot.addLoot(Block.gravel, 5);
		loot.addLoot(new ItemStack(Item.coal, 1, 0), 3);
		loot.addLoot(new ItemStack(Item.coal, 1, 1), 3);
		loot.addLoot(Item.seeds, 3);
		loot.addLoot(Item.stick, 5);
		loot.addLoot(Item.bowlEmpty, 3);
		loot.addLoot(Item.helmetLeather, 2);
		loot.addLoot(Item.plateLeather, 2);
		loot.addLoot(Item.legsLeather, 2);
		loot.addLoot(Item.bootsLeather, 2);
		loot.addLoot(Item.flint, 4);
		loot.addLoot(Item.clay, 4);
		loot.addLoot(Item.bucketEmpty, 1);
		loot.addLoot(Item.leather, 4);
		loot.addLoot(Item.slimeBall, 1);
		loot.addLoot(Block.reed, 1);
		loot.addLoot(Item.bone, 5);
		loot.addLoot(Item.rottenFlesh, 5);
		loot.addLoot(Item.glassBottle, 1);
		loot.addLoot(Item.carrot, 1);
		loot.addLoot(Item.potato, 1);
		loot.addLoot(Block.vine, 1);
		loot.addLoot(Block.tallGrass, 3);
		loot.addLoot(Item.silk, 3);
		loot.addLoot(Item.feather, 4);
		loot.addLoot(Item.appleRed, 1);
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
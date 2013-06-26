package rebelkeithy.mods.aquaculture;

import net.minecraft.item.ItemStack;
import rebelkeithy.mods.keithyutils.loot.WeightedLootSet;


public class FishLoot 
{
	WeightedLootSet fishLoot;
	WeightedLootSet junkLoot;
	
	public FishLoot instance;
	
	public FishLoot instance()
	{
		if(instance == null)
			instance = new FishLoot();
		
		return instance;
	}
	
	private FishLoot()
	{
		fishLoot = new WeightedLootSet();
		junkLoot = new WeightedLootSet();
	}
	
	public void addFish(ItemStack fish, int rarity)
	{
		fishLoot.addLoot(fish, rarity, 1, 1);
	}
	
	public void addJunkLoot(ItemStack fish, int rarity)
	{
		junkLoot.addLoot(fish, rarity, 1, 1);
	}
	
	public ItemStack getRandomFish()
	{
		return fishLoot.getRandomLoot();
	}
	
	public ItemStack getRandomJunk()
	{
		return junkLoot.getRandomLoot();
	}
}

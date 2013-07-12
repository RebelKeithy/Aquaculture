package rebelkeithy.mods.aquaculture;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.minecraft.item.ItemStack;
import rebelkeithy.mods.keithyutils.loot.WeightedLootSet;


public class FishLoot 
{
	
	
	Map<BiomeType, WeightedLootSet> fishBiomeMap;
	Map<BiomeType, WeightedLootSet> junkBiomeMap;
	
	public static FishLoot instance;
	
	public static FishLoot instance()
	{
		if(instance == null)
			instance = new FishLoot();
		
		return instance;
	}
	
	private FishLoot()
	{
		junkBiomeMap = new HashMap<BiomeType, WeightedLootSet>();
		
		fishBiomeMap = new HashMap<BiomeType, WeightedLootSet>();
	}
	
	public void addBiome(BiomeType biome)
	{
		junkBiomeMap.put(biome, new WeightedLootSet());
		fishBiomeMap.put(biome, new WeightedLootSet());
	}
	
	public void addFish(ItemStack fish, BiomeType biome, int rarity)
	{
		if(!fishBiomeMap.containsKey(biome))
		{
			fishBiomeMap.put(biome, new WeightedLootSet());
		}

		fishBiomeMap.get(biome).addLoot(fish, rarity, 1, 1);
	}
	
	public void addJunkLoot(ItemStack junk, int rarity)
	{
		for(WeightedLootSet lootSet : junkBiomeMap.values())
		{
			lootSet.addLoot(junk, rarity, 1, 1);
		}
	}
	
	public void addJunkLoot(ItemStack junk, BiomeType[] biomes, int rarity)
	{
		for(BiomeType biome : biomes)
		{
			addJunkLoot(junk, biome, rarity);
		}
	}
	
	public void addJunkLoot(ItemStack fish, BiomeType biome, int rarity)
	{
		if(!junkBiomeMap.containsKey(biome))
		{
			junkBiomeMap.put(biome, new WeightedLootSet());
		}

		junkBiomeMap.get(biome).addLoot(fish, rarity, 1, 1);
	}

	public ItemStack getRandomFish(int biomeID)
	{
		BiomeType biome = BiomeType.getBiomeType(biomeID);
		
		ItemStack fishStack = null;
		if(biome != null && fishBiomeMap.containsKey(biome))
			fishStack = fishBiomeMap.get(biome).getRandomLoot();
		else
			fishStack = fishBiomeMap.get(BiomeType.freshwater).getRandomLoot();
		
		return fishStack;
	}
	
	public ItemStack getRandomFish(int biomeID, int heavyLineLvl)
	{
		BiomeType biome = BiomeType.getBiomeType(biomeID);
		
		ItemStack fishStack = null;
		if(biome != null && fishBiomeMap.containsKey(biome))
			fishStack = fishBiomeMap.get(biome).getRandomLoot();
		else
			fishStack = fishBiomeMap.get(BiomeType.freshwater).getRandomLoot();
		
		AquacultureItems.fish.assignRandomWeight(fishStack, heavyLineLvl);
		
		return fishStack;
	}
	
	public ItemStack getRandomJunk(int biomeID)
	{
		BiomeType biome = BiomeType.getBiomeType(biomeID);
		
		if(biome != null && junkBiomeMap.containsKey(biome))
			return junkBiomeMap.get(biome).getRandomLoot();
		else
			return junkBiomeMap.get(BiomeType.freshwater).getRandomLoot();
	}
}

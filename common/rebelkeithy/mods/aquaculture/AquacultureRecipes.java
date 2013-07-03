package rebelkeithy.mods.aquaculture;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.src.ModLoader;
import cpw.mods.fml.common.registry.GameRegistry;

public class AquacultureRecipes 
{
	public static void addRecipes()
	{
		AquacultureItems.fish.addFilletRecipes();
		
		GameRegistry.addShapelessRecipe(AquacultureItems.whaleSteak.getItemStack(5), AquacultureItems.fish.getItemStackFish("Whale"));
		GameRegistry.addShapelessRecipe(new ItemStack(Item.dyePowder, 2, 0), AquacultureItems.fish.getItemStackFish("Squid"));
		GameRegistry.addShapelessRecipe(new ItemStack(Block.mushroomRed, 5), AquacultureItems.fish.getItemStackFish("Red Shrooma"));
		GameRegistry.addShapelessRecipe(new ItemStack(Block.mushroomBrown, 5), AquacultureItems.fish.getItemStackFish("Brown Shrooma"));
		GameRegistry.addShapelessRecipe(new ItemStack(Item.leather, 2), new ItemStack(Item.bootsLeather));
		GameRegistry.addShapelessRecipe(new ItemStack(Item.goldNugget, 1), AquacultureItems.fish.getItemStackFish("Goldfish"));
		GameRegistry.addShapelessRecipe(AquacultureItems.frogLegs.getItemStack(), AquacultureItems.fish.getItemStackFish("Frog"));
		GameRegistry.addShapelessRecipe(AquacultureItems.turtleSoup.getItemStack(), AquacultureItems.fish.getItemStackFish("Turtle"));
		GameRegistry.addShapelessRecipe(AquacultureItems.sushi.getItemStack(), AquacultureItems.fishFillet.getItemStack(), AquacultureItems.seaweed.getItemStack());
		
		GameRegistry.addShapelessRecipe(new ItemStack(Block.planks, 4), AquacultureItems.driftwood.getItemStack());

		//GameRegistry.addSmelting(AquacultureItems.FishFillet.itemID, new ItemStack(AquacultureItems.CookedFillet, 1), 0.3f);
		//GameRegistry.addSmelting(AquacultureItems.WhaleSteak.itemID, new ItemStack(AquacultureItems.CookedWhaleSteak, 1), 0.3f);
		//GameRegistry.addSmelting(AquacultureItems.TinCan.item, new ItemStack(Item.ingotIron), 0.7f);
		FurnaceRecipes.smelting().addSmelting(AquacultureItems.fishFillet.itemID, AquacultureItems.fishFillet.damage, AquacultureItems.cookedFillet.getItemStack(), 0.3f);
		FurnaceRecipes.smelting().addSmelting(AquacultureItems.whaleSteak.itemID, AquacultureItems.whaleSteak.damage, AquacultureItems.cookedWhaleSteak.getItemStack(), 0.3f);
		FurnaceRecipes.smelting().addSmelting(AquacultureItems.tinCan.itemID, AquacultureItems.tinCan.damage, new ItemStack(Item.ingotIron), 0.7f);
		FurnaceRecipes.smelting().addSmelting(AquacultureItems.frogLegs.itemID, AquacultureItems.frogLegs.damage, AquacultureItems.cookedFrogLegs.getItemStack(), 0.3F);
		
		GameRegistry.addShapedRecipe(new ItemStack(AquacultureItems.ironFishingRod, 1), "  X", " XS", "I S", 'X', Item.ingotIron, 'I', Item.stick, 'S', Item.silk);
	    GameRegistry.addRecipe(AquacultureItems.whaleBurger.getItemStack(), "B", "W", "B", 'W', AquacultureItems.cookedWhaleSteak.getItemStack(), 'B', Item.bread);

		GameRegistry.addRecipe(new ItemStack(AquacultureItems.neptuniumPickaxe, 1), "XXX", " | ", " | ", 'X', AquacultureItems.neptuniumBar.getItemStack(), '|', Item.stick);
		GameRegistry.addRecipe(new ItemStack(AquacultureItems.neptuniumShovel, 1), " X ", " | ", " | ", 'X', AquacultureItems.neptuniumBar.getItemStack(), '|', Item.stick);
		GameRegistry.addRecipe(new ItemStack(AquacultureItems.neptuniumAxe, 1), "XX ", "X| ", " | ", 'X', AquacultureItems.neptuniumBar.getItemStack(), '|', Item.stick);
		GameRegistry.addRecipe(new ItemStack(AquacultureItems.neptuniumHoe, 1), "XX ", " | ", " | ", 'X', AquacultureItems.neptuniumBar.getItemStack(), '|', Item.stick);
		GameRegistry.addRecipe(new ItemStack(AquacultureItems.neptuniumSword, 1), " X ", " X ", " | ", 'X', AquacultureItems.neptuniumBar.getItemStack(), '|', Item.stick);
	    GameRegistry.addRecipe(new ItemStack(AquacultureItems.neptuniumHelmet, 1), "XXX", "X X", 'X', AquacultureItems.neptuniumBar.getItemStack());
	    GameRegistry.addRecipe(new ItemStack(AquacultureItems.neptuniumPlate, 1), "X X", "XXX", "XXX", 'X', AquacultureItems.neptuniumBar.getItemStack());
	    GameRegistry.addRecipe(new ItemStack(AquacultureItems.neptuniumLegs, 1), "XXX", "X X", "X X", 'X', AquacultureItems.neptuniumBar.getItemStack());
	    GameRegistry.addRecipe(new ItemStack(AquacultureItems.neptuniumBoots, 1), "X X", "X X", 'X', AquacultureItems.neptuniumBar.getItemStack());
	}
}
	


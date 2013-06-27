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
		
		GameRegistry.addShapelessRecipe(AquacultureItems.WhaleSteak.getItemStack(5), AquacultureItems.fish.getItemStackFish("Whale"));
		GameRegistry.addShapelessRecipe(new ItemStack(Item.dyePowder, 2, 0), AquacultureItems.fish.getItemStackFish("Squid"));
		GameRegistry.addShapelessRecipe(new ItemStack(Block.mushroomRed, 5), AquacultureItems.fish.getItemStackFish("Red Shrooma"));
		GameRegistry.addShapelessRecipe(new ItemStack(Block.mushroomBrown, 5), AquacultureItems.fish.getItemStackFish("Brown Shrooma"));
		GameRegistry.addShapelessRecipe(new ItemStack(Item.leather, 2), new ItemStack(Item.bootsLeather));
		GameRegistry.addShapelessRecipe(new ItemStack(Item.goldNugget, 1), AquacultureItems.fish.getItemStackFish("Goldfish"));

		GameRegistry.addShapelessRecipe(new ItemStack(Block.planks, 4), AquacultureItems.Driftwood.getItemStack());

		//GameRegistry.addSmelting(AquacultureItems.FishFillet.itemID, new ItemStack(AquacultureItems.CookedFillet, 1), 0.3f);
		//GameRegistry.addSmelting(AquacultureItems.WhaleSteak.itemID, new ItemStack(AquacultureItems.CookedWhaleSteak, 1), 0.3f);
		//GameRegistry.addSmelting(AquacultureItems.TinCan.item, new ItemStack(Item.ingotIron), 0.7f);
		FurnaceRecipes.smelting().addSmelting(AquacultureItems.FishFillet.itemID, AquacultureItems.FishFillet.damage, AquacultureItems.CookedFillet.getItemStack(), 0.3f);
		FurnaceRecipes.smelting().addSmelting(AquacultureItems.WhaleSteak.itemID, AquacultureItems.WhaleSteak.damage, AquacultureItems.CookedWhaleSteak.getItemStack(), 0.3f);
		FurnaceRecipes.smelting().addSmelting(AquacultureItems.TinCan.itemID, AquacultureItems.TinCan.damage, new ItemStack(Item.ingotIron), 0.7f);
		
		
		GameRegistry.addShapedRecipe(new ItemStack(AquacultureItems.IronFishingRod, 1), "  X", " XS", "I S", 'X', Item.ingotIron, 'I', Item.stick, 'S', Item.silk);
	    GameRegistry.addRecipe(AquacultureItems.WhaleBurger.getItemStack(), "B", "W", "B", 'W', AquacultureItems.CookedWhaleSteak.getItemStack(), 'B', Item.bread);

		GameRegistry.addRecipe(new ItemStack(AquacultureItems.NeptuniumPickaxe, 1), "XXX", " | ", " | ", 'X', AquacultureItems.NeptuniumBar.getItemStack(), '|', Item.stick);
		GameRegistry.addRecipe(new ItemStack(AquacultureItems.NeptuniumShovel, 1), " X ", " | ", " | ", 'X', AquacultureItems.NeptuniumBar.getItemStack(), '|', Item.stick);
		GameRegistry.addRecipe(new ItemStack(AquacultureItems.NeptuniumAxe, 1), "XX ", "X| ", " | ", 'X', AquacultureItems.NeptuniumBar.getItemStack(), '|', Item.stick);
		GameRegistry.addRecipe(new ItemStack(AquacultureItems.NeptuniumHoe, 1), "XX ", " | ", " | ", 'X', AquacultureItems.NeptuniumBar.getItemStack(), '|', Item.stick);
		GameRegistry.addRecipe(new ItemStack(AquacultureItems.NeptuniumSword, 1), " X ", " X ", " | ", 'X', AquacultureItems.NeptuniumBar.getItemStack(), '|', Item.stick);
	    GameRegistry.addRecipe(new ItemStack(AquacultureItems.NeptuniumHelmet, 1), "XXX", "X X", 'X', AquacultureItems.NeptuniumBar.getItemStack());
	    GameRegistry.addRecipe(new ItemStack(AquacultureItems.NeptuniumPlate, 1), "X X", "XXX", "XXX", 'X', AquacultureItems.NeptuniumBar.getItemStack());
	    GameRegistry.addRecipe(new ItemStack(AquacultureItems.NeptuniumLegs, 1), "XXX", "X X", "X X", 'X', AquacultureItems.NeptuniumBar.getItemStack());
	    GameRegistry.addRecipe(new ItemStack(AquacultureItems.NeptuniumBoots, 1), "X X", "X X", 'X', AquacultureItems.NeptuniumBar.getItemStack());
	}
}
	


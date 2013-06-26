package rebelkeithy.mods.aquaculture;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.src.ModLoader;
import cpw.mods.fml.common.registry.GameRegistry;

public class AquacultureRecipes 
{
	public static void addRecipes()
	{
		AquacultureItems.fish.addFilletRecipes();
		
		GameRegistry.addShapelessRecipe(new ItemStack(AquacultureItems.WhaleSteak, 5), AquacultureItems.fish.getFish("Whale"));
		GameRegistry.addShapelessRecipe(new ItemStack(Item.dyePowder, 2, 0), AquacultureItems.fish.getFish("Squid"));
		GameRegistry.addShapelessRecipe(new ItemStack(Block.mushroomRed, 5), AquacultureItems.fish.getFish("Red Shrooma"));
		GameRegistry.addShapelessRecipe(new ItemStack(Block.mushroomBrown, 5), AquacultureItems.fish.getFish("Brown Shrooma"));
		GameRegistry.addShapelessRecipe(new ItemStack(Item.leather, 2), new ItemStack(Item.bootsLeather));
		GameRegistry.addShapelessRecipe(new ItemStack(Item.goldNugget, 1), AquacultureItems.fish.getFish("Goldfish"));

		GameRegistry.addShapelessRecipe(new ItemStack(Block.planks, 4), AquacultureItems.Driftwood);

		GameRegistry.addSmelting(AquacultureItems.FishFillet.itemID, new ItemStack(AquacultureItems.CookedFillet, 1), 0.3f);
		GameRegistry.addSmelting(AquacultureItems.WhaleSteak.itemID, new ItemStack(AquacultureItems.CookedWhaleSteak, 1), 0.3f);
		GameRegistry.addSmelting(AquacultureItems.TinCan.itemID, new ItemStack(Item.ingotIron), 0.7f);
		
		
		GameRegistry.addShapedRecipe(new ItemStack(AquacultureItems.IronFishingRod, 1), "  X", " XS", "I S", 'X', Item.ingotIron, 'I', Item.stick, 'S', Item.silk);
	    GameRegistry.addRecipe(new ItemStack(AquacultureItems.WhaleBurger, 1), "B", "W", "B", 'W', AquacultureItems.CookedWhaleSteak, 'B', Item.bread);

		GameRegistry.addRecipe(new ItemStack(AquacultureItems.NeptuniumPickaxe, 1), "XXX", " | ", " | ", 'X', AquacultureItems.NeptuniumBar, '|', Item.stick);
		GameRegistry.addRecipe(new ItemStack(AquacultureItems.NeptuniumShovel, 1), " X ", " | ", " | ", 'X', AquacultureItems.NeptuniumBar, '|', Item.stick);
		GameRegistry.addRecipe(new ItemStack(AquacultureItems.NeptuniumAxe, 1), "XX ", "X| ", " | ", 'X', AquacultureItems.NeptuniumBar, '|', Item.stick);
		GameRegistry.addRecipe(new ItemStack(AquacultureItems.NeptuniumHoe, 1), "XX ", " | ", " | ", 'X', AquacultureItems.NeptuniumBar, '|', Item.stick);
		GameRegistry.addRecipe(new ItemStack(AquacultureItems.NeptuniumSword, 1), " X ", " X ", " | ", 'X', AquacultureItems.NeptuniumBar, '|', Item.stick);
	    GameRegistry.addRecipe(new ItemStack(AquacultureItems.NeptuniumHelmet, 1), "XXX", "X X", 'X', AquacultureItems.NeptuniumBar);
	    GameRegistry.addRecipe(new ItemStack(AquacultureItems.NeptuniumPlate, 1), "X X", "XXX", "XXX", 'X', AquacultureItems.NeptuniumBar);
	    GameRegistry.addRecipe(new ItemStack(AquacultureItems.NeptuniumLegs, 1), "XXX", "X X", "X X", 'X', AquacultureItems.NeptuniumBar);
	    GameRegistry.addRecipe(new ItemStack(AquacultureItems.NeptuniumBoots, 1), "X X", "X X", 'X', AquacultureItems.NeptuniumBar);
	}
}
	


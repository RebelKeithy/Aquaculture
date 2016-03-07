package rebelkeithy.mods.aquaculture.handlers;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import rebelkeithy.mods.aquaculture.items.AquacultureItems;

public class AquacultureRecipes {

    public void addRecipes() {
        AquacultureItems.fish.addFilletRecipes();

        GameRegistry.addShapelessRecipe(AquacultureItems.whaleSteak.getItemStack(5), AquacultureItems.fish.getItemStackFish("Whale"));
        GameRegistry.addShapelessRecipe(new ItemStack(Items.dye, 2, 0), AquacultureItems.fish.getItemStackFish("Squid"));
        GameRegistry.addShapelessRecipe(new ItemStack(Blocks.red_mushroom, 5), AquacultureItems.fish.getItemStackFish("Red Shrooma"));
        GameRegistry.addShapelessRecipe(new ItemStack(Blocks.brown_mushroom, 5), AquacultureItems.fish.getItemStackFish("Brown Shrooma"));
        GameRegistry.addShapelessRecipe(new ItemStack(Items.leather, 2), new ItemStack(Items.leather_boots));
        GameRegistry.addShapelessRecipe(new ItemStack(Items.gold_nugget, 1), AquacultureItems.fish.getItemStackFish("Goldfish"));
        GameRegistry.addShapelessRecipe(AquacultureItems.frogLegs.getItemStack(1), AquacultureItems.fish.getItemStackFish("Frog"));
        GameRegistry.addShapelessRecipe(AquacultureItems.turtleSoup.getItemStack(), AquacultureItems.fish.getItemStackFish("Turtle"), Items.water_bucket);
        GameRegistry.addShapelessRecipe(AquacultureItems.sushi.getItemStack(), AquacultureItems.fishFillet.getItemStack(), AquacultureItems.seaweed.getItemStack());
        GameRegistry.addShapelessRecipe(new ItemStack(Items.dye, 3, 15), AquacultureItems.fish.getItemStackFish("Fish Bones"));

        GameRegistry.addShapelessRecipe(new ItemStack(Blocks.planks, 4), AquacultureItems.driftwood.getItemStack());

        FurnaceRecipes.smelting().func_151394_a(AquacultureItems.fishFillet.getItemStack(), AquacultureItems.cookedFillet.getItemStack(), 0.3f);
        FurnaceRecipes.smelting().func_151394_a(AquacultureItems.whaleSteak.getItemStack(), AquacultureItems.cookedWhaleSteak.getItemStack(), 0.3f);
        FurnaceRecipes.smelting().func_151394_a(AquacultureItems.tinCan.getItemStack(), new ItemStack(Items.iron_ingot), 0.7f);
        FurnaceRecipes.smelting().func_151394_a(AquacultureItems.frogLegs.getItemStack(), AquacultureItems.cookedFrogLegs.getItemStack(), 0.3F);

        GameRegistry.addShapedRecipe(new ItemStack(AquacultureItems.ironFishingRod, 1), "  X", " XS", "I S", 'X', Items.iron_ingot, 'I', Items.stick, 'S', Items.string);
        GameRegistry.addShapedRecipe(new ItemStack(AquacultureItems.goldFishingRod, 1), "  X", " XS", "I S", 'X', Items.gold_ingot, 'I', Items.stick, 'S', Items.string);
        GameRegistry.addShapedRecipe(new ItemStack(AquacultureItems.diamondFishingRod, 1), "  X", " XS", "I S", 'X', Items.diamond, 'I', Items.stick, 'S', Items.string);
        GameRegistry.addRecipe(AquacultureItems.whaleBurger.getItemStack(), " B ", " W ", " B ", 'W', AquacultureItems.cookedWhaleSteak.getItemStack(), 'B', Items.bread);

        GameRegistry.addRecipe(new ItemStack(AquacultureItems.neptuniumPickaxe, 1), "XXX", " | ", " | ", 'X', AquacultureItems.neptuniumBar.getItemStack(), '|', Items.stick);
        GameRegistry.addRecipe(new ItemStack(AquacultureItems.neptuniumShovel, 1), " X ", " | ", " | ", 'X', AquacultureItems.neptuniumBar.getItemStack(), '|', Items.stick);
        GameRegistry.addRecipe(new ItemStack(AquacultureItems.neptuniumAxe, 1), "XX ", "X| ", " | ", 'X', AquacultureItems.neptuniumBar.getItemStack(), '|', Items.stick);
        GameRegistry.addRecipe(new ItemStack(AquacultureItems.neptuniumHoe, 1), "XX ", " | ", " | ", 'X', AquacultureItems.neptuniumBar.getItemStack(), '|', Items.stick);
        GameRegistry.addRecipe(new ItemStack(AquacultureItems.neptuniumSword, 1), " X ", " X ", " | ", 'X', AquacultureItems.neptuniumBar.getItemStack(), '|', Items.stick);
        GameRegistry.addRecipe(new ItemStack(AquacultureItems.neptuniumHelmet, 1), "XXX", "X X", 'X', AquacultureItems.neptuniumBar.getItemStack());
        GameRegistry.addRecipe(new ItemStack(AquacultureItems.neptuniumPlate, 1), "X X", "XXX", "XXX", 'X', AquacultureItems.neptuniumBar.getItemStack());
        GameRegistry.addRecipe(new ItemStack(AquacultureItems.neptuniumLegs, 1), "XXX", "X X", "X X", 'X', AquacultureItems.neptuniumBar.getItemStack());
        GameRegistry.addRecipe(new ItemStack(AquacultureItems.neptuniumBoots, 1), "X X", "X X", 'X', AquacultureItems.neptuniumBar.getItemStack());
    }
}

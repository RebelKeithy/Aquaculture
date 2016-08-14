package com.teammetallurgy.aquaculture.handlers;

import com.teammetallurgy.aquaculture.items.AquacultureItems;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class AquacultureRecipes {

    public void addRecipes() {
        AquacultureItems.fish.addFilletRecipes();

        GameRegistry.addShapelessRecipe(AquacultureItems.whaleSteak.getItemStack(5), AquacultureItems.fish.getItemStackFish("Whale"));
        GameRegistry.addShapelessRecipe(new ItemStack(Items.DYE, 2, 0), AquacultureItems.fish.getItemStackFish("Squid"));
        GameRegistry.addShapelessRecipe(new ItemStack(Blocks.RED_MUSHROOM, 5), AquacultureItems.fish.getItemStackFish("Red Shrooma"));
        GameRegistry.addShapelessRecipe(new ItemStack(Blocks.BROWN_MUSHROOM, 5), AquacultureItems.fish.getItemStackFish("Brown Shrooma"));
        GameRegistry.addShapelessRecipe(new ItemStack(Items.LEATHER, 2), new ItemStack(Items.LEATHER_BOOTS));
        GameRegistry.addShapelessRecipe(new ItemStack(Items.GOLD_NUGGET, 1), AquacultureItems.fish.getItemStackFish("Goldfish"));
        GameRegistry.addShapelessRecipe(AquacultureItems.frogLegs.getItemStack(1), AquacultureItems.fish.getItemStackFish("Frog"));
        GameRegistry.addShapelessRecipe(AquacultureItems.turtleSoup.getItemStack(), AquacultureItems.fish.getItemStackFish("Turtle"), Items.WATER_BUCKET);
        GameRegistry.addShapelessRecipe(AquacultureItems.sushi.getItemStack(), AquacultureItems.fishFillet.getItemStack(), AquacultureItems.seaweed.getItemStack());
        GameRegistry.addShapelessRecipe(new ItemStack(Items.DYE, 3, 15), AquacultureItems.fish.getItemStackFish("Fish Bones"));

        GameRegistry.addShapelessRecipe(new ItemStack(Blocks.PLANKS, 4), AquacultureItems.driftwood.getItemStack());

        GameRegistry.addSmelting(AquacultureItems.fishFillet.getItemStack(), AquacultureItems.cookedFillet.getItemStack(), 0.3f);
        GameRegistry.addSmelting(AquacultureItems.whaleSteak.getItemStack(), AquacultureItems.cookedWhaleSteak.getItemStack(), 0.3f);
        GameRegistry.addSmelting(AquacultureItems.tinCan.getItemStack(), new ItemStack(Items.IRON_INGOT), 0.7f);
        GameRegistry.addSmelting(AquacultureItems.frogLegs.getItemStack(), AquacultureItems.cookedFrogLegs.getItemStack(), 0.3F);

        GameRegistry.addShapelessRecipe(new ItemStack(AquacultureItems.woodenFishingRod), new ItemStack(Items.FISHING_ROD));
        GameRegistry.addShapelessRecipe(new ItemStack(Items.FISHING_ROD), new ItemStack(AquacultureItems.woodenFishingRod));
        GameRegistry.addShapedRecipe(new ItemStack(AquacultureItems.ironFishingRod, 1), "  X", " XS", "I S", 'X', Items.IRON_INGOT, 'I', Items.STICK, 'S', Items.STRING);
        GameRegistry.addShapedRecipe(new ItemStack(AquacultureItems.ironFishingRod, 1), "  X", " XS", "I S", 'X', Items.IRON_INGOT, 'I', Items.STICK, 'S', Items.STRING);
        GameRegistry.addShapedRecipe(new ItemStack(AquacultureItems.goldFishingRod, 1), "  X", " XS", "I S", 'X', Items.GOLD_INGOT, 'I', Items.STICK, 'S', Items.STRING);
        GameRegistry.addShapedRecipe(new ItemStack(AquacultureItems.diamondFishingRod, 1), "  X", " XS", "I S", 'X', Items.DIAMOND, 'I', Items.STICK, 'S', Items.STRING);
        GameRegistry.addRecipe(AquacultureItems.whaleBurger.getItemStack(), " B ", " W ", " B ", 'W', AquacultureItems.cookedWhaleSteak.getItemStack(), 'B', Items.BREAD);

        GameRegistry.addRecipe(new ItemStack(AquacultureItems.neptuniumPickaxe, 1), "XXX", " | ", " | ", 'X', AquacultureItems.neptuniumBar.getItemStack(), '|', Items.STICK);
        GameRegistry.addRecipe(new ItemStack(AquacultureItems.neptuniumShovel, 1), " X ", " | ", " | ", 'X', AquacultureItems.neptuniumBar.getItemStack(), '|', Items.STICK);
        GameRegistry.addRecipe(new ItemStack(AquacultureItems.neptuniumAxe, 1), "XX ", "X| ", " | ", 'X', AquacultureItems.neptuniumBar.getItemStack(), '|', Items.STICK);
        GameRegistry.addRecipe(new ItemStack(AquacultureItems.neptuniumHoe, 1), "XX ", " | ", " | ", 'X', AquacultureItems.neptuniumBar.getItemStack(), '|', Items.STICK);
        GameRegistry.addRecipe(new ItemStack(AquacultureItems.neptuniumSword, 1), " X ", " X ", " | ", 'X', AquacultureItems.neptuniumBar.getItemStack(), '|', Items.STICK);
        GameRegistry.addRecipe(new ItemStack(AquacultureItems.neptuniumHelmet, 1), "XXX", "X X", 'X', AquacultureItems.neptuniumBar.getItemStack());
        GameRegistry.addRecipe(new ItemStack(AquacultureItems.neptuniumPlate, 1), "X X", "XXX", "XXX", 'X', AquacultureItems.neptuniumBar.getItemStack());
        GameRegistry.addRecipe(new ItemStack(AquacultureItems.neptuniumLegs, 1), "XXX", "X X", "X X", 'X', AquacultureItems.neptuniumBar.getItemStack());
        GameRegistry.addRecipe(new ItemStack(AquacultureItems.neptuniumBoots, 1), "X X", "X X", 'X', AquacultureItems.neptuniumBar.getItemStack());
    }
}

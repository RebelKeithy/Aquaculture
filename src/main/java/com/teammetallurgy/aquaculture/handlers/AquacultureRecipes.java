package com.teammetallurgy.aquaculture.handlers;

import com.teammetallurgy.aquaculture.items.AquacultureItems;
import net.minecraft.init.Items;
import net.minecraft.init.PotionTypes;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionType;
import net.minecraft.potion.PotionUtils;
import net.minecraft.util.NonNullList;
import net.minecraftforge.common.brewing.BrewingRecipeRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;

@Mod.EventBusSubscriber
public class AquacultureRecipes {

    public static void addSmeltingRecipes() {
        GameRegistry.addSmelting(AquacultureItems.fishFillet.getItemStack(), AquacultureItems.cookedFillet.getItemStack(), 0.3F);
        GameRegistry.addSmelting(AquacultureItems.whaleSteak.getItemStack(), AquacultureItems.cookedWhaleSteak.getItemStack(), 0.3F);
        GameRegistry.addSmelting(AquacultureItems.frogLegs.getItemStack(), AquacultureItems.cookedFrogLegs.getItemStack(), 0.3F);
        GameRegistry.addSmelting(AquacultureItems.fish.getItemStackFish("Jellyfish"), new ItemStack(Items.SLIME_BALL), 0.3F);
        GameRegistry.addSmelting(AquacultureItems.tinCan.getItemStack(), OreDictionary.doesOreNameExist("nuggetTin") ? new ItemStack(getOreDict("nuggetTin").getItem(), 7, getOreDict("nuggetTin").getItemDamage()) : new ItemStack(Items.IRON_NUGGET, 7), 0.7F);
    }

    public static void addBrewingRecipes() {
        addFishBrewingRecipe(Items.POTIONITEM, PotionTypes.WATER, "Jellyfish", Items.POTIONITEM, PotionTypes.MUNDANE);
        addFishBrewingRecipe(Items.POTIONITEM, PotionTypes.AWKWARD, "Jellyfish", Items.POTIONITEM, PotionTypes.POISON);
        addFishBrewingRecipe(Items.SPLASH_POTION, PotionTypes.AWKWARD, "Jellyfish", Items.SPLASH_POTION, PotionTypes.POISON);
        addFishBrewingRecipe(Items.SPLASH_POTION, PotionTypes.WATER, "Jellyfish", Items.SPLASH_POTION, PotionTypes.MUNDANE);
        addFishBrewingRecipe(Items.LINGERING_POTION, PotionTypes.WATER, "Jellyfish", Items.LINGERING_POTION, PotionTypes.MUNDANE);
        addFishBrewingRecipe(Items.LINGERING_POTION, PotionTypes.AWKWARD, "Jellyfish", Items.LINGERING_POTION, PotionTypes.POISON);
        addFishBrewingRecipe(Items.POTIONITEM, PotionTypes.WATER, "Leech", Items.POTIONITEM, PotionTypes.MUNDANE);
        addFishBrewingRecipe(Items.POTIONITEM, PotionTypes.AWKWARD, "Leech", Items.POTIONITEM, PotionTypes.HEALING);
        addFishBrewingRecipe(Items.SPLASH_POTION, PotionTypes.AWKWARD, "Leech", Items.SPLASH_POTION, PotionTypes.HEALING);
        addFishBrewingRecipe(Items.SPLASH_POTION, PotionTypes.WATER, "Leech", Items.SPLASH_POTION, PotionTypes.MUNDANE);
        addFishBrewingRecipe(Items.LINGERING_POTION, PotionTypes.WATER, "Leech", Items.LINGERING_POTION, PotionTypes.MUNDANE);
        addFishBrewingRecipe(Items.LINGERING_POTION, PotionTypes.AWKWARD, "Leech", Items.LINGERING_POTION, PotionTypes.HEALING);
    }

    private static void addFishBrewingRecipe(Item input, PotionType potionInput, String fishType, Item potionItem, PotionType potionType) {
        BrewingRecipeRegistry.addRecipe(PotionUtils.addPotionToItemStack(new ItemStack(input), potionInput), AquacultureItems.fish.getItemStackFish(fishType), PotionUtils.addPotionToItemStack(new ItemStack(potionItem), potionType));
    }

    private static ItemStack getOreDict(String oreName) {
        NonNullList<ItemStack> oreStacks = OreDictionary.getOres(oreName);
        if (!oreStacks.isEmpty()) {
            for (ItemStack stack : oreStacks) {
                return stack;
            }
        }
        return ItemStack.EMPTY;
    }
}
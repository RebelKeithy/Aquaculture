package com.teammetallurgy.aquaculture.handlers;

import com.teammetallurgy.aquaculture.items.AquacultureItems;
import net.minecraft.init.Items;
import net.minecraft.init.PotionTypes;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionType;
import net.minecraft.util.NonNullList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;

import static net.minecraft.potion.PotionUtils.addPotionToItemStack;
import static net.minecraftforge.common.brewing.BrewingRecipeRegistry.addRecipe;

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
        addBrewingRecipeWithSubPotions(AquacultureItems.fish.getItemStackFish("Jellyfish"), PotionTypes.POISON);
        addBrewingRecipeWithSubPotions(AquacultureItems.fish.getItemStackFish("Leech"), PotionTypes.HEALING);
    }

    private static void addBrewingRecipeWithSubPotions(ItemStack ingredient, PotionType potionType) {
        addRecipe(addPotionToItemStack(new ItemStack(Items.POTIONITEM), PotionTypes.WATER), ingredient, addPotionToItemStack(new ItemStack(Items.POTIONITEM), PotionTypes.MUNDANE));
        addRecipe(addPotionToItemStack(new ItemStack(Items.POTIONITEM), PotionTypes.AWKWARD), ingredient, addPotionToItemStack(new ItemStack(Items.POTIONITEM), potionType));
        addRecipe(addPotionToItemStack(new ItemStack(Items.SPLASH_POTION), PotionTypes.AWKWARD), ingredient, addPotionToItemStack(new ItemStack(Items.SPLASH_POTION), potionType));
        addRecipe(addPotionToItemStack(new ItemStack(Items.SPLASH_POTION), PotionTypes.WATER), ingredient, addPotionToItemStack(new ItemStack(Items.SPLASH_POTION), PotionTypes.MUNDANE));
        addRecipe(addPotionToItemStack(new ItemStack(Items.LINGERING_POTION), PotionTypes.WATER), ingredient, addPotionToItemStack(new ItemStack(Items.LINGERING_POTION), PotionTypes.MUNDANE));
        addRecipe(addPotionToItemStack(new ItemStack(Items.LINGERING_POTION), PotionTypes.AWKWARD), ingredient, addPotionToItemStack(new ItemStack(Items.LINGERING_POTION), potionType));
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
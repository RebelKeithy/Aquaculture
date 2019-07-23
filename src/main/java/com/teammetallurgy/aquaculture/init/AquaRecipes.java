package com.teammetallurgy.aquaculture.init;

import com.teammetallurgy.aquaculture.Aquaculture;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.potion.Potion;
import net.minecraft.potion.Potions;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import javax.annotation.Nonnull;

import static net.minecraft.potion.PotionUtils.addPotionToItemStack;
import static net.minecraftforge.common.brewing.BrewingRecipeRegistry.addRecipe;

@Mod.EventBusSubscriber(modid = Aquaculture.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class AquaRecipes {

    @SubscribeEvent
    public static void registerBrewingRecipes(RegistryEvent.Register<Potion> event) {
        addBrewingRecipeWithSubPotions(AquaItems.JELLYFISH, Potions.POISON);
        addBrewingRecipeWithSubPotions(AquaItems.LEECH, Potions.HEALING);
    }

    private static void addBrewingRecipeWithSubPotions(Item item, Potion potionType) {
        //addBrewingRecipeWithSubPotions(new ItemStack(item), potionType); //TODO
    }

    private static void addBrewingRecipeWithSubPotions(@Nonnull ItemStack input, Potion potionType) {
        Ingredient ingredient = toIngredient(input);
        addRecipe(toIngredient(addPotionToItemStack(new ItemStack(Items.POTION), Potions.WATER)), ingredient, addPotionToItemStack(new ItemStack(Items.POTION), Potions.MUNDANE));
        addRecipe(toIngredient(addPotionToItemStack(new ItemStack(Items.POTION), Potions.AWKWARD)), ingredient, addPotionToItemStack(new ItemStack(Items.POTION), potionType));
        addRecipe(toIngredient(addPotionToItemStack(new ItemStack(Items.SPLASH_POTION), Potions.AWKWARD)), ingredient, addPotionToItemStack(new ItemStack(Items.SPLASH_POTION), potionType));
        addRecipe(toIngredient(addPotionToItemStack(new ItemStack(Items.SPLASH_POTION), Potions.WATER)), ingredient, addPotionToItemStack(new ItemStack(Items.SPLASH_POTION), Potions.MUNDANE));
        addRecipe(toIngredient(addPotionToItemStack(new ItemStack(Items.LINGERING_POTION), Potions.WATER)), ingredient, addPotionToItemStack(new ItemStack(Items.LINGERING_POTION), Potions.MUNDANE));
        addRecipe(toIngredient(addPotionToItemStack(new ItemStack(Items.LINGERING_POTION), Potions.AWKWARD)), ingredient, addPotionToItemStack(new ItemStack(Items.LINGERING_POTION), potionType));
    }

    private static Ingredient toIngredient(@Nonnull ItemStack stack) {
        return Ingredient.fromStacks(stack);
    }
}
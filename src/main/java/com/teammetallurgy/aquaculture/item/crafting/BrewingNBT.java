package com.teammetallurgy.aquaculture.item.crafting;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.brewing.BrewingRecipe;

import javax.annotation.Nonnull;

public class BrewingNBT extends BrewingRecipe {

    public BrewingNBT(Ingredient input, Ingredient ingredient, ItemStack output) {
        super(input, ingredient, output);
    }

    @Override
    public boolean isInput(@Nonnull ItemStack stack) {
        return super.isInput(stack) && testNBT(super.getInput(), stack);
    }

    @Override
    public boolean isIngredient(@Nonnull ItemStack ingredient) {
        return super.isIngredient(ingredient) && testNBT(super.getIngredient(), ingredient);
    }

    private boolean testNBT(Ingredient ingredient, @Nonnull ItemStack stack) {
        for (ItemStack testStack : ingredient.getItems()) {
            return (testStack.getTag() == null || testStack.getTag().equals(stack.getTag()) && testStack.areCapsCompatible(stack));
        }
        return false;
    }
}
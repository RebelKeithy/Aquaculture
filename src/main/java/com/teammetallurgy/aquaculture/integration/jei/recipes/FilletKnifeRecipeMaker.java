package com.teammetallurgy.aquaculture.integration.jei.recipes;

import com.teammetallurgy.aquaculture.Aquaculture;
import com.teammetallurgy.aquaculture.api.AquacultureAPI;
import com.teammetallurgy.aquaculture.init.AquaItems;
import mezz.jei.api.constants.VanillaRecipeCategoryUid;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.ShapelessRecipe;
import net.minecraft.tags.Tag;
import net.minecraft.core.NonNullList;
import net.minecraft.resources.ResourceLocation;

import java.util.ArrayList;
import java.util.List;

public class FilletKnifeRecipeMaker {

    public static List<ShapelessRecipe> createFilletKnifeRecipes() {
        List<ShapelessRecipe> recipes = new ArrayList<>();

        for (Item fish : AquacultureAPI.FISH_DATA.getFish()) {
            Tag.Named<Item> filletKnifeTag = AquacultureAPI.Tags.FILLET_KNIFE;
            Ingredient filletKnifes = Ingredient.of(AquaItems.WOODEN_FILLET_KNIFE, AquaItems.STONE_FILLET_KNIFE, AquaItems.IRON_FILLET_KNIFE, AquaItems.GOLD_FILLET_KNIFE, AquaItems.DIAMOND_FILLET_KNIFE, AquaItems.NEPTINIUM_FILLET_KNIFE);
            NonNullList<Ingredient> input = NonNullList.of(Ingredient.EMPTY, filletKnifeTag.getValues().isEmpty() ? filletKnifes : Ingredient.of(filletKnifeTag), Ingredient.of(fish));
            if (AquacultureAPI.FISH_DATA.hasFilletAmount(fish)) {
                ItemStack output = new ItemStack(AquaItems.FISH_FILLET, AquacultureAPI.FISH_DATA.getFilletAmount(fish));
                if (fish.getRegistryName() != null) {
                    ResourceLocation id = new ResourceLocation(Aquaculture.MOD_ID, "fish_fillet." + fish.getRegistryName().getPath());
                    recipes.add(new ShapelessRecipe(id, VanillaRecipeCategoryUid.CRAFTING.getPath(), output, input));
                }
            }
        }
        return recipes;
    }
}
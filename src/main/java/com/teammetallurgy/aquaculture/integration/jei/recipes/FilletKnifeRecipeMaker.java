//package com.teammetallurgy.aquaculture.integration.jei.recipes;
//
//import com.teammetallurgy.aquaculture.Aquaculture;
//import com.teammetallurgy.aquaculture.api.AquacultureAPI;
//import com.teammetallurgy.aquaculture.init.AquaItems;
//import mezz.jei.api.constants.RecipeTypes;
//import net.minecraft.core.NonNullList;
//import net.minecraft.resources.ResourceLocation;
//import net.minecraft.tags.TagKey;
//import net.minecraft.world.item.Item;
//import net.minecraft.world.item.ItemStack;
//import net.minecraft.world.item.crafting.CraftingBookCategory;
//import net.minecraft.world.item.crafting.CraftingRecipe;
//import net.minecraft.world.item.crafting.Ingredient;
//import net.minecraft.world.item.crafting.ShapelessRecipe;
//import net.minecraftforge.registries.ForgeRegistries;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class FilletKnifeRecipeMaker {
//
//    public static List<CraftingRecipe> createFilletKnifeRecipes() {
//        List<CraftingRecipe> recipes = new ArrayList<>();
//
//        for (Item fish : AquacultureAPI.FISH_DATA.getFish()) {
//            TagKey<Item> filletKnifeTag = AquacultureAPI.Tags.FILLET_KNIFE;
//            NonNullList<Ingredient> input = NonNullList.of(Ingredient.EMPTY, Ingredient.of(filletKnifeTag), Ingredient.of(fish));
//            if (AquacultureAPI.FISH_DATA.hasFilletAmount(fish)) {
//                ItemStack output = new ItemStack(AquaItems.FISH_FILLET.get(), AquacultureAPI.FISH_DATA.getFilletAmount(fish));
//                ResourceLocation itemID = ForgeRegistries.ITEMS.getKey(fish);
//                if (itemID != null) {
//                    ResourceLocation id = new ResourceLocation(Aquaculture.MOD_ID, "fish_fillet." + itemID.getPath());
//                    recipes.add(new ShapelessRecipe(id, RecipeTypes.CRAFTING.getUid().getPath(), CraftingBookCategory.MISC, output, input));
//                }
//            }
//        }
//        return recipes;
//    }
//}
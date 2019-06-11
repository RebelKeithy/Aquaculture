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

    public static void addSmeltingRecipes() {
        /*GameRegistry.addSmelting(AquaItems.fishFillet.getItemStack(), AquaItems.cookedFillet.getItemStack(), 0.3F); //TODO Move to new Json system
        GameRegistry.addSmelting(AquaItems.whaleSteak.getItemStack(), AquaItems.cookedWhaleSteak.getItemStack(), 0.3F);
        GameRegistry.addSmelting(AquaItems.frogLegs.getItemStack(), AquaItems.cookedFrogLegs.getItemStack(), 0.3F);
        GameRegistry.addSmelting(AquaItems.fish.getItemStackFish("Jellyfish"), new ItemStack(Items.SLIME_BALL), 0.3F);
        GameRegistry.addSmelting(AquaItems.tinCan.getItemStack(), OreDictionary.doesOreNameExist("nuggetTin") ? new ItemStack(getOreDict("nuggetTin").getItem(), 7, getOreDict("nuggetTin").getItemDamage()) : new ItemStack(Items.IRON_NUGGET, 7), 0.7F);*/
    }

    @SubscribeEvent
    public static void registerRecipes(RegistryEvent.Register<Potion> event) {
        addBrewingRecipeWithSubPotions(AquaItems.JELLYFISH, Potions.POISON);
        addBrewingRecipeWithSubPotions(AquaItems.LEECH, Potions.HEALING);
    }

    private static void addBrewingRecipeWithSubPotions(Item item, Potion potionType) {
        addBrewingRecipeWithSubPotions(new ItemStack(item), potionType);
    }

    private static void addBrewingRecipeWithSubPotions(@Nonnull ItemStack stack, Potion potionType) {
        Ingredient ingredient = Ingredient.fromStacks(stack);
        addRecipe(addPotionToItemStack(new ItemStack(Items.POTION), Potions.WATER), ingredient, addPotionToItemStack(new ItemStack(Items.POTION), Potions.MUNDANE));
        addRecipe(addPotionToItemStack(new ItemStack(Items.POTION), Potions.AWKWARD), ingredient, addPotionToItemStack(new ItemStack(Items.POTION), potionType));
        addRecipe(addPotionToItemStack(new ItemStack(Items.SPLASH_POTION), Potions.AWKWARD), ingredient, addPotionToItemStack(new ItemStack(Items.SPLASH_POTION), potionType));
        addRecipe(addPotionToItemStack(new ItemStack(Items.SPLASH_POTION), Potions.WATER), ingredient, addPotionToItemStack(new ItemStack(Items.SPLASH_POTION), Potions.MUNDANE));
        addRecipe(addPotionToItemStack(new ItemStack(Items.LINGERING_POTION), Potions.WATER), ingredient, addPotionToItemStack(new ItemStack(Items.LINGERING_POTION), Potions.MUNDANE));
        addRecipe(addPotionToItemStack(new ItemStack(Items.LINGERING_POTION), Potions.AWKWARD), ingredient, addPotionToItemStack(new ItemStack(Items.LINGERING_POTION), potionType));
    }
}
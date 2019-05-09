package com.teammetallurgy.aquaculture.init;

import com.teammetallurgy.aquaculture.Aquaculture;
import net.minecraft.init.Items;
import net.minecraft.init.PotionTypes;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.potion.PotionType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

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
    public static void registerRecipes(RegistryEvent.Register<PotionType> event) {
        /*addBrewingRecipeWithSubPotions(AquaItems.FISH.getItemStackFish("Jellyfish"), PotionTypes.POISON); //TODO
        addBrewingRecipeWithSubPotions(AquaItems.FISH.getItemStackFish("Leech"), PotionTypes.HEALING);*/
    }

    private static void addBrewingRecipeWithSubPotions(Ingredient ingredient, PotionType potionType) {
        addRecipe(addPotionToItemStack(new ItemStack(Items.POTION), PotionTypes.WATER), ingredient, addPotionToItemStack(new ItemStack(Items.POTION), PotionTypes.MUNDANE));
        addRecipe(addPotionToItemStack(new ItemStack(Items.POTION), PotionTypes.AWKWARD), ingredient, addPotionToItemStack(new ItemStack(Items.POTION), potionType));
        addRecipe(addPotionToItemStack(new ItemStack(Items.SPLASH_POTION), PotionTypes.AWKWARD), ingredient, addPotionToItemStack(new ItemStack(Items.SPLASH_POTION), potionType));
        addRecipe(addPotionToItemStack(new ItemStack(Items.SPLASH_POTION), PotionTypes.WATER), ingredient, addPotionToItemStack(new ItemStack(Items.SPLASH_POTION), PotionTypes.MUNDANE));
        addRecipe(addPotionToItemStack(new ItemStack(Items.LINGERING_POTION), PotionTypes.WATER), ingredient, addPotionToItemStack(new ItemStack(Items.LINGERING_POTION), PotionTypes.MUNDANE));
        addRecipe(addPotionToItemStack(new ItemStack(Items.LINGERING_POTION), PotionTypes.AWKWARD), ingredient, addPotionToItemStack(new ItemStack(Items.LINGERING_POTION), potionType));
    }
}
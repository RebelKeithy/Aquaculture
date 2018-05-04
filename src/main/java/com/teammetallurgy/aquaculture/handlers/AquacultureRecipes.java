package com.teammetallurgy.aquaculture.handlers;

import com.teammetallurgy.aquaculture.items.AquacultureItems;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
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
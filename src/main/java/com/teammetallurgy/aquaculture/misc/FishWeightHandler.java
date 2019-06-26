package com.teammetallurgy.aquaculture.misc;

import com.teammetallurgy.aquaculture.Aquaculture;
import com.teammetallurgy.aquaculture.api.AquacultureAPI;
import com.teammetallurgy.aquaculture.api.weight.FishWeight;
import com.teammetallurgy.aquaculture.items.FishItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.event.entity.player.ItemFishedEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import javax.annotation.Nonnull;
import java.math.BigDecimal;
import java.math.MathContext;
import java.text.DecimalFormat;
import java.util.Objects;
import java.util.Random;

import static com.teammetallurgy.aquaculture.init.AquaItems.*;

@Mod.EventBusSubscriber(modid = Aquaculture.MOD_ID)
public class FishWeightHandler {

    @SubscribeEvent
    public static void onItemFished(ItemFishedEvent event) { //TODO Config. Add support for non Aquaculture fish
        ItemStack fish = event.getDrops().get(0);
        System.out.println(fish.getItem());
        if (AquacultureAPI.FISH_WEIGHT.hasWeight(fish.getItem())) {
            System.out.println("Hi");
        }
    }

    @SubscribeEvent
    public static void onTooltip(ItemTooltipEvent event) {
        ItemStack stack = event.getItemStack();
        if (!stack.isEmpty() && stack != null && stack.hasTag()) { //Keep stack null check, in case of other mods doing bad things
            if (Objects.requireNonNull(stack.getTag()).contains("weight")) {
                float weight = stack.getTag().getFloat("weight");

                DecimalFormat df = new DecimalFormat("#,###.##");
                BigDecimal bd = new BigDecimal(weight);
                bd = bd.round(new MathContext(3));
                if (bd.doubleValue() > 999) {
                    event.getToolTip().add(new TranslationTextComponent("weight: " + df.format((int) bd.doubleValue()) + "lb"));
                } else {
                    event.getToolTip().add(new TranslationTextComponent("weight: " + bd + "lb"));
                }
            }
        }
    }

    public void assignRandomWeight(Item fish) {
        ItemStack stack = new ItemStack(fish);
        if (stack.isEmpty()) {
            return;
        }
        FishWeight fishWeight = AquacultureAPI.FISH_WEIGHT;
        Random rand = new Random();
        int min = fishWeight.getMinWeight(fish);
        int max = fishWeight.getMaxWeight(fish);
        float weight = rand.nextInt((max - min)) + max;

        if (!stack.hasTag()) {
            stack.setTag(new CompoundNBT());
        }
        CompoundNBT tag = stack.getTag();

        if (tag != null) {
            tag.putFloat("weight", weight);
            if (weight <= max * 0.10F) {
                tag.putString("size", "Juvenile");
            } else if (weight > max * 0.10F && weight <= max * 0.20F) {
                tag.putString("size", "Small");
            } else if (weight >= max * 0.80F && weight < max * 0.90F) {
                tag.putString("size", "Large");
            } else if (weight >= max * 0.90F) {
                tag.putString("size", "Massive");
            }
        }
    }

    public static void registerWeight() {
        AquacultureAPI.FISH_WEIGHT.addWeight(BLUEGILL, 1, 5);
        AquacultureAPI.FISH_WEIGHT.addWeight(PERCH, 1, 5);
        AquacultureAPI.FISH_WEIGHT.addWeight(GAR, 1, 10);
        AquacultureAPI.FISH_WEIGHT.addWeight(BASS, 1, 25);
        AquacultureAPI.FISH_WEIGHT.addWeight(MUSKELLUNGE, 1, 35);
        AquacultureAPI.FISH_WEIGHT.addWeight(BROWN_TROUT, 1, 40);
        AquacultureAPI.FISH_WEIGHT.addWeight(CATFISH, 1, 50);
        AquacultureAPI.FISH_WEIGHT.addWeight(CARP, 1, 100);
        AquacultureAPI.FISH_WEIGHT.addWeight(RED_GROUPER, 1, 50);
        AquacultureAPI.FISH_WEIGHT.addWeight(TUNA, 1, 135);
        AquacultureAPI.FISH_WEIGHT.addWeight(SWORDFISH, 1, 1400);
        AquacultureAPI.FISH_WEIGHT.addWeight(SHARK, 1, 5000);
        AquacultureAPI.FISH_WEIGHT.addWeight(WHALE, 1, 190000);
        AquacultureAPI.FISH_WEIGHT.addWeight(SQUID, 1, 1000);
        AquacultureAPI.FISH_WEIGHT.addWeight(JELLYFISH, 1, 500);
        AquacultureAPI.FISH_WEIGHT.addWeight(PIRANHA, 1, 8);
        AquacultureAPI.FISH_WEIGHT.addWeight(ELECTRIC_EEL, 1, 45);
        AquacultureAPI.FISH_WEIGHT.addWeight(TAMBAQUI, 1, 75);
        AquacultureAPI.FISH_WEIGHT.addWeight(ARAPAIMA, 1, 220);
        AquacultureAPI.FISH_WEIGHT.addWeight(POLLOCK, 1, 45);
        AquacultureAPI.FISH_WEIGHT.addWeight(HERRING, 1, 3);
        AquacultureAPI.FISH_WEIGHT.addWeight(HALIBUT, 1, 700);
        AquacultureAPI.FISH_WEIGHT.addWeight(PINK_SALMON, 1, 100);
        AquacultureAPI.FISH_WEIGHT.addWeight(RAINBOW_TROUT, 1, 50);
        AquacultureAPI.FISH_WEIGHT.addWeight(BLACKFISH, 1, 10);
        AquacultureAPI.FISH_WEIGHT.addWeight(CAPITAINE, 1, 450);
        AquacultureAPI.FISH_WEIGHT.addWeight(BOULTI, 1, 10);
        AquacultureAPI.FISH_WEIGHT.addWeight(BAGRID, 1, 25);
        AquacultureAPI.FISH_WEIGHT.addWeight(SYNDONTIS, 1, 3);
        AquacultureAPI.FISH_WEIGHT.addWeight(RED_SHROOMA, 1, 5);
        AquacultureAPI.FISH_WEIGHT.addWeight(BROWN_SHROOMA, 1, 5);
        AquacultureAPI.FISH_WEIGHT.addWeight(GOLDFISH, 1, 4);
    }
}
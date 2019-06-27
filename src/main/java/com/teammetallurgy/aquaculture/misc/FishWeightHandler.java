package com.teammetallurgy.aquaculture.misc;

import com.teammetallurgy.aquaculture.AquaConfig;
import com.teammetallurgy.aquaculture.Aquaculture;
import com.teammetallurgy.aquaculture.api.AquacultureAPI;
import com.teammetallurgy.aquaculture.api.weight.FishWeight;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.event.entity.player.ItemFishedEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.loading.StringUtils;

import java.math.BigDecimal;
import java.math.MathContext;
import java.text.DecimalFormat;
import java.util.Random;

import static com.teammetallurgy.aquaculture.init.AquaItems.*;

@Mod.EventBusSubscriber(modid = Aquaculture.MOD_ID)
public class FishWeightHandler {

    @SubscribeEvent
    public static void onItemFished(ItemFishedEvent event) {
        ItemStack fish = event.getDrops().get(0);
        if (AquaConfig.BASIC_OPTIONS.randomWeight.get()) {
            if (AquacultureAPI.FISH_WEIGHT.hasWeight(fish.getItem())) {
                FishWeight fishWeight = AquacultureAPI.FISH_WEIGHT;
                assignRandomWeight(fish, fishWeight.getMinWeight(fish.getItem()), fishWeight.getMaxWeight(fish.getItem()));
            } else if (fish.getItem().isIn(ItemTags.FISHES)) { //Adds weight to any fish that does not have one specified
                assignRandomWeight(fish, 0, 100);
            }
        }
    }

    @SubscribeEvent
    public static void onTooltip(ItemTooltipEvent event) {
        ItemStack stack = event.getItemStack();
        if (!stack.isEmpty() && stack != null && stack.hasTag() && stack.getTag() != null) { //Keep stack null check, in case of other mods doing bad things
            if (stack.getTag().contains("fishSize")) {
                Style style = new Style().setItalic(true).setColor(TextFormatting.AQUA);
                event.getToolTip().add(new TranslationTextComponent("aquaculture.fishWeight." + StringUtils.toLowerCase(stack.getTag().getString("fishSize"))).setStyle(style));
            }
            if (stack.getTag().contains("fishWeight")) {
                float weight = stack.getTag().getFloat("fishWeight");

                DecimalFormat df = new DecimalFormat("#,###.##");
                BigDecimal bd = new BigDecimal(weight);
                bd = bd.round(new MathContext(3));
                Style style = new Style().setItalic(true).setColor(TextFormatting.GRAY);
                if (bd.doubleValue() > 999) {
                    event.getToolTip().add(new TranslationTextComponent("aquaculture.fishWeight.weight", df.format((int) bd.doubleValue()) + "lb").setStyle(style));
                } else {
                    event.getToolTip().add(new TranslationTextComponent("aquaculture.fishWeight.weight", bd + "lb").setStyle(style));
                }
            }
        }
    }

    private static void assignRandomWeight(ItemStack fish, int min, int max) {
        if (fish.isEmpty()) {
            return;
        }
        Random rand = new Random();
        float weight = rand.nextInt((max - min)) + min;

        if (!fish.hasTag()) {
            fish.setTag(new CompoundNBT());
        }

        CompoundNBT tag = fish.getTag();

        if (tag != null) {
            tag.putFloat("fishWeight", weight);
            if (weight <= max * 0.10F) {
                tag.putString("fishSize", "juvenile");
            } else if (weight > max * 0.10F && weight <= max * 0.20F) {
                tag.putString("fishSize", "small");
            } else if (weight >= max * 0.80F && weight < max * 0.90F) {
                tag.putString("fishSize", "large");
            } else if (weight >= max * 0.90F) {
                tag.putString("fishSize", "massive");
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
        //Vanilla
        AquacultureAPI.FISH_WEIGHT.addWeight(Items.COD, 1, 210);
        AquacultureAPI.FISH_WEIGHT.addWeight(Items.SALMON, 1, 135);
        AquacultureAPI.FISH_WEIGHT.addWeight(Items.TROPICAL_FISH, 1, 5);
        AquacultureAPI.FISH_WEIGHT.addWeight(Items.PUFFERFISH, 1, 25);
    }
}
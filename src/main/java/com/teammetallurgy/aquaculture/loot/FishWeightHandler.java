package com.teammetallurgy.aquaculture.loot;

import com.teammetallurgy.aquaculture.Aquaculture;
import com.teammetallurgy.aquaculture.api.AquacultureAPI;
import com.teammetallurgy.aquaculture.api.fish.FishData;
import com.teammetallurgy.aquaculture.misc.AquaConfig;
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

import static com.teammetallurgy.aquaculture.init.AquaItems.*;

@Mod.EventBusSubscriber(modid = Aquaculture.MOD_ID)
public class FishWeightHandler {

    @SubscribeEvent
    public static void onItemFished(ItemFishedEvent event) {
        if (!event.getDrops().isEmpty()) {
            ItemStack fish = event.getDrops().get(0);
            if (AquaConfig.BASIC_OPTIONS.randomWeight.get()) {
                if (AquacultureAPI.FISH_DATA.hasWeight(fish.getItem())) {
                    FishData fishWeight = AquacultureAPI.FISH_DATA;
                    assignRandomWeight(fish, fishWeight.getMinWeight(fish.getItem()), fishWeight.getMaxWeight(fish.getItem()));
                } else if (fish.getItem().isIn(ItemTags.FISHES)) { //Adds weight to any fish that does not have one specified
                    assignRandomWeight(fish, 0.1, 100);
                }
            }
        }
    }

    @SubscribeEvent
    public static void onTooltip(ItemTooltipEvent event) {
        ItemStack stack = event.getItemStack();
        if (!stack.isEmpty() && stack != null && stack.hasTag() && stack.getTag() != null) { //Keep stack null check, in case of other mods is doing bad things
            if (stack.getTag().contains("fishSize")) {
                Style style = new Style().setItalic(true).setColor(TextFormatting.AQUA);
                event.getToolTip().add(new TranslationTextComponent("aquaculture.fishWeight." + StringUtils.toLowerCase(stack.getTag().getString("fishSize"))).setStyle(style));
            }
            if (stack.getTag().contains("fishWeight")) {
                double weight = stack.getTag().getDouble("fishWeight");
                String lb = weight == 1.0D ? " lb" : " lbs";

                DecimalFormat df = new DecimalFormat("#,###.##");
                BigDecimal bd = new BigDecimal(weight);
                bd = bd.round(new MathContext(3));
                Style style = new Style().setItalic(true).setColor(TextFormatting.GRAY);
                if (bd.doubleValue() > 999) {
                    event.getToolTip().add(new TranslationTextComponent("aquaculture.fishWeight.weight", df.format((int) bd.doubleValue()) + lb).setStyle(style));
                } else {
                    event.getToolTip().add(new TranslationTextComponent("aquaculture.fishWeight.weight", bd + lb).setStyle(style));
                }
            }
        }
    }

    private static void assignRandomWeight(ItemStack fish, double min, double max) {
        if (fish.isEmpty()) {
            return;
        }
        double weight = min + Math.random() * (max - min);

        if (!fish.hasTag()) {
            fish.setTag(new CompoundNBT());
        }

        CompoundNBT tag = fish.getTag();

        if (tag != null) {
            tag.putDouble("fishWeight", weight);
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

    public static int getFilletAmountFromWeight(double weight) {
        if (weight >= 2 && weight < 10D) {
            return 1;
        } else if (weight >= 10 && weight < 50) {
            return 2;
        } else if (weight >= 50 && weight < 100) {
            return 3;
        } else if (weight >= 100 && weight < 150) {
            return 4;
        } else if (weight >= 150 && weight < 200) {
            return 5;
        } else if (weight >= 200 && weight < 250) {
            return 6;
        } else if (weight >= 250 && weight < 300) {
            return 7;
        } else if (weight >= 300 && weight < 350) {
            return 8;
        } else if (weight >= 350 && weight < 400) {
            return 9;
        } else if (weight >= 400 && weight < 450) {
            return 10;
        } else if (weight >= 450 && weight < 500) {
            return 11;
        } else if (weight >= 500 && weight < 600) {
            return 12;
        } else if (weight >= 600 && weight < 700) {
            return 13;
        } else if (weight >= 700 && weight < 800) {
            return 14;
        } else if (weight >= 800 && weight < 1000) {
            return 15;
        } else if (weight >= 1000) {
            return 16;
        } else {
            return 0;
        }
    }

    public static void registerFishData() {
        AquacultureAPI.FISH_DATA.add(ATLANTIC_COD, 10, 211, 6);
        AquacultureAPI.FISH_DATA.add(BLACKFISH, 1, 28, 2);
        AquacultureAPI.FISH_DATA.add(PACIFIC_HALIBUT, 25, 550, 12);
        AquacultureAPI.FISH_DATA.add(ATLANTIC_HALIBUT, 50, 710, 14);
        AquacultureAPI.FISH_DATA.add(ATLANTIC_HERRING, 0.5, 2.4);
        AquacultureAPI.FISH_DATA.add(PINK_SALMON, 1.5, 15, 2);
        AquacultureAPI.FISH_DATA.add(POLLOCK, 3, 46, 2);
        AquacultureAPI.FISH_DATA.add(RAINBOW_TROUT, 2, 27, 2);
        AquacultureAPI.FISH_DATA.add(BAYAD, 5, 145, 4);
        AquacultureAPI.FISH_DATA.add(BOULTI, 1, 9.5, 1);
        AquacultureAPI.FISH_DATA.add(CAPITAINE, 20, 440, 10);
        AquacultureAPI.FISH_DATA.add(SYNODONTIS, 0.5, 2.5);
        AquacultureAPI.FISH_DATA.add(SMALLMOUTH_BASS, 1, 12, 2);
        AquacultureAPI.FISH_DATA.add(LARGEMOUTH_BASS, 3, 25, 2);
        AquacultureAPI.FISH_DATA.add(BLUEGILL, 0.8, 4.5);
        AquacultureAPI.FISH_DATA.add(BROWN_TROUT, 1.5, 44, 2);
        AquacultureAPI.FISH_DATA.add(CARP, 2, 40, 2);
        AquacultureAPI.FISH_DATA.add(CATFISH, 10, 220, 6);
        AquacultureAPI.FISH_DATA.add(GAR, 8, 100, 4);
        AquacultureAPI.FISH_DATA.add(MINNOW, 0.2, 1.5, 0);
        AquacultureAPI.FISH_DATA.add(MUSKELLUNGE, 5, 70, 3);
        AquacultureAPI.FISH_DATA.add(PERCH, 0.5, 6);
        AquacultureAPI.FISH_DATA.add(PIKE, 3, 63, 2);
        AquacultureAPI.FISH_DATA.add(STURGEON, 10, 1800, 16);
        AquacultureAPI.FISH_DATA.add(WALLEYE, 2, 29, 2);
        AquacultureAPI.FISH_DATA.add(ARAPAIMA, 20, 440, 10);
        AquacultureAPI.FISH_DATA.add(ELECTRIC_EEL, 3, 45, 2);
        AquacultureAPI.FISH_DATA.add(PIRANHA, 0.5, 7.7);
        AquacultureAPI.FISH_DATA.add(TAMBAQUI, 7, 97, 3);
        AquacultureAPI.FISH_DATA.add(BROWN_SHROOMA, 1, 5, 0);
        AquacultureAPI.FISH_DATA.add(RED_SHROOMA, 1, 5, 0);
        AquacultureAPI.FISH_DATA.add(ANCHOVY, 0.005, 0.2, 0);
        AquacultureAPI.FISH_DATA.add(ANGLERFISH, 4, 70, 3);
        AquacultureAPI.FISH_DATA.add(BONITO, 0.8, 13, 2);
        AquacultureAPI.FISH_DATA.add(COELACANTH, 11, 200, 6);
        AquacultureAPI.FISH_DATA.add(EEL, 1.5, 55, 3);
        AquacultureAPI.FISH_DATA.add(EUROPEAN_FLOUNDER, 3, 31, 2);
        AquacultureAPI.FISH_DATA.add(BLACK_GROUPER, 15, 220, 6);
        AquacultureAPI.FISH_DATA.add(JELLYFISH, 5, 400, 0);
        AquacultureAPI.FISH_DATA.add(LUNG_FISH, 2, 45, 2);
        AquacultureAPI.FISH_DATA.add(MACKEREL, 0.6, 8);
        AquacultureAPI.FISH_DATA.add(RABBITFISH, 0.8, 5.5);
        AquacultureAPI.FISH_DATA.add(RED_GROUPER, 4, 50, 3);
        AquacultureAPI.FISH_DATA.add(YELLOWEYE_ROCKFISH, 2, 22, 2);
        AquacultureAPI.FISH_DATA.add(SARDINE, 0.003, 0.25, 0);
        AquacultureAPI.FISH_DATA.add(SQUID, 10, 1000, 0); //TODO. Figured out what species the vanilla squid is
        AquacultureAPI.FISH_DATA.add(SWORDFISH, 115, 1430, 16);
        AquacultureAPI.FISH_DATA.add(TARPON, 13, 280, 8);
        AquacultureAPI.FISH_DATA.add(TUNA, 30, 1508, 10);
        AquacultureAPI.FISH_DATA.add(GOLDFISH, 0.05, 5, 0);

        //Vanilla
        AquacultureAPI.FISH_DATA.add(Items.COD, 12, 211, 4);
        AquacultureAPI.FISH_DATA.add(Items.SALMON, 0.6, 15, 2);
        AquacultureAPI.FISH_DATA.add(Items.TROPICAL_FISH, 0.01, 1, 0);
        AquacultureAPI.FISH_DATA.add(Items.PUFFERFISH, 1, 25);
    }
}
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
                    assignRandomWeight(fish, 0, 100);
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
                String lb = weight >= 2.0D ? " lbs" : " lb";

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

    public static void registerFishData() {
        AquacultureAPI.FISH_DATA.add(BLACKFISH, 1, 10, 2);
        AquacultureAPI.FISH_DATA.add(PACIFIC_HALIBUT, 1, 700, 7);
        AquacultureAPI.FISH_DATA.add(ATLANTIC_HERRING, 1, 3);
        AquacultureAPI.FISH_DATA.add(PINK_SALMON, 1, 100, 5);
        AquacultureAPI.FISH_DATA.add(POLLOCK, 1, 45, 3);
        AquacultureAPI.FISH_DATA.add(RAINBOW_TROUT, 1, 50, 4);
        AquacultureAPI.FISH_DATA.add(BAGRID, 1, 25, 3);
        AquacultureAPI.FISH_DATA.add(BOULTI, 1, 10, 2);
        AquacultureAPI.FISH_DATA.add(CAPITAINE, 1, 450, 4);
        AquacultureAPI.FISH_DATA.add(SYNODONTIS, 1, 3);
        AquacultureAPI.FISH_DATA.add(SMALLMOUTH_BASS, 1, 25, 3);
        AquacultureAPI.FISH_DATA.add(BLUEGILL, 1, 5);
        AquacultureAPI.FISH_DATA.add(BROWN_TROUT, 1, 40, 3);
        AquacultureAPI.FISH_DATA.add(CARP, 1, 100, 5);
        AquacultureAPI.FISH_DATA.add(CATFISH, 1, 50, 4);
        AquacultureAPI.FISH_DATA.add(GAR, 1, 10, 2);
        AquacultureAPI.FISH_DATA.add(MUSKELLUNGE, 1, 35, 3);
        AquacultureAPI.FISH_DATA.add(PERCH, 1, 5);
        AquacultureAPI.FISH_DATA.add(ARAPAIMA, 1, 220, 6);
        AquacultureAPI.FISH_DATA.add(ELECTRIC_EEL, 1, 45, 3);
        AquacultureAPI.FISH_DATA.add(PIRANHA, 1, 8);
        AquacultureAPI.FISH_DATA.add(TAMBAQUI, 1, 75, 4);
        AquacultureAPI.FISH_DATA.add(BROWN_SHROOMA, 1, 5, 0);
        AquacultureAPI.FISH_DATA.add(RED_SHROOMA, 1, 5, 0);
        AquacultureAPI.FISH_DATA.add(JELLYFISH, 1, 500, 0);
        AquacultureAPI.FISH_DATA.add(RED_GROUPER, 1, 50, 4);
        AquacultureAPI.FISH_DATA.add(SHARK, 1, 5000, 8);
        AquacultureAPI.FISH_DATA.add(SQUID, 1, 1000, 0);
        AquacultureAPI.FISH_DATA.add(SWORDFISH, 1, 1400, 7);
        AquacultureAPI.FISH_DATA.add(TUNA, 1, 135, 5);
        AquacultureAPI.FISH_DATA.add(WHALE, 1, 190000, 0);
        AquacultureAPI.FISH_DATA.add(GOLDFISH, 1, 4, 0);
        //Vanilla
        AquacultureAPI.FISH_DATA.add(Items.COD, 1, 210, 4);
        AquacultureAPI.FISH_DATA.add(Items.SALMON, 1, 135, 3);
        AquacultureAPI.FISH_DATA.add(Items.TROPICAL_FISH, 1, 5, 0);
        AquacultureAPI.FISH_DATA.add(Items.PUFFERFISH, 1, 25);
    }
}
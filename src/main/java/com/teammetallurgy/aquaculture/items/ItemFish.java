package com.teammetallurgy.aquaculture.items;

import com.google.common.base.Preconditions;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.math.BigDecimal;
import java.math.MathContext;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Random;

public class ItemFish extends Item {

    public ItemFish(Properties properties) {
        super(properties);
    }

    @Override
    @Nonnull
    public ITextComponent getDisplayName(@Nonnull ItemStack stack) {
        if (stack.hasTag() && stack.getTag() != null) {
            if (stack.getTag().contains("Prefix")) {
                return new TextComponentTranslation(stack.getTag().getString("Prefix") + " " + super.getDisplayName(stack));
            }
        }
        return super.getDisplayName(stack);
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void addInformation(@Nonnull ItemStack stack, @Nullable World world, List<ITextComponent> toolTip, ITooltipFlag tooltipType) { //TODO Move to event. Config. Add support for non Aquaculture fish
        if (stack.hasTag() && stack.getTag() != null) {
            if (stack.getTag().contains("Weight")) {
                float weight = stack.getTag().getFloat("Weight");

                DecimalFormat df = new DecimalFormat("#,###.##");
                BigDecimal bd = new BigDecimal(weight);
                bd = bd.round(new MathContext(3));
                if (bd.doubleValue() > 999) {
                    toolTip.add(new TextComponentTranslation("Weight: " + df.format((int) bd.doubleValue()) + "lb"));
                } else {
                    toolTip.add(new TextComponentTranslation("Weight: " + bd + "lb"));
                }
            }
        }
    }

    public void assignRandomWeight(@Nonnull ItemStack stack, int heavyLineLvl) {
        if (stack.isEmpty()) {
            return;
        }

        Fish f = null;

        if (f.maxWeight == 1 && f.minWeight == 1)
            return;

        float min = f.minWeight;

        min += (f.maxWeight - min) * (0.1 * heavyLineLvl);

        float weight = new Random().nextFloat() * (f.maxWeight - min) + min;

        if (!stack.hasTag()) {
            stack.setTag(new NBTTagCompound());
        }

        Preconditions.checkNotNull(stack.getTag(), "tagCompound");
        stack.getTag().putFloat("Weight", weight);

        if (weight <= f.maxWeight * 0.10F) {
            stack.getTag().putString("Prefix", "Juvenile");
        } else if (weight > f.maxWeight * 0.10F && weight <= f.maxWeight * 0.20F) {
            stack.getTag().putString("Prefix", "Small");
        } else if (weight >= f.maxWeight * 0.80F && weight < f.maxWeight * 0.90F) {
            stack.getTag().putString("Prefix", "Large");
        } else if (weight >= f.maxWeight * 0.90F) {
            stack.getTag().putString("Prefix", "Massive");
        }
    }

    public class Fish {
        public String name;
        public int filletAmount;
        public int minWeight;
        public int maxWeight;

        public Fish(String name, int amount, int min, int max) {
            this.name = name;
            filletAmount = amount;
            maxWeight = max;
            minWeight = min;
        }
    }
}
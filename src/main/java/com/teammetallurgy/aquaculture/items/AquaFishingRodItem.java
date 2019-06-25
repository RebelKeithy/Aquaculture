package com.teammetallurgy.aquaculture.items;

import com.teammetallurgy.aquaculture.entity.AquaFishingBobberEntity;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.FishingRodItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTier;
import net.minecraft.stats.Stats;
import net.minecraft.util.*;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class AquaFishingRodItem extends FishingRodItem {
    private int enchantability;
    private boolean isAdminRod;

    public AquaFishingRodItem(@Nullable ItemTier material, Properties properties) {
        super(properties);
        if (material == null) {
            isAdminRod = true;
        } else {
            this.enchantability = material == ItemTier.WOOD ? 10 : material.getEnchantability();
        }
    }

    @Override
    public int getItemEnchantability() {
        return enchantability;
    }

    @Override
    @Nonnull
    public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, @Nonnull Hand hand) {
        ItemStack heldStack = player.getHeldItem(hand);
        int lureSpeed;
        if (player.fishingBobber != null) {
            if (!world.isRemote) {
                lureSpeed = player.fishingBobber.handleHookRetraction(heldStack);
                if (!isAdminRod) {
                    heldStack.damageItem(lureSpeed, player, (entity) -> entity.sendBreakAnimation(hand));
                }
            }
            player.swingArm(hand);
            world.playSound(null, player.posX, player.posY, player.posZ, SoundEvents.ENTITY_FISHING_BOBBER_RETRIEVE, SoundCategory.NEUTRAL, 1.0F, 0.4F / (random.nextFloat() * 0.4F + 0.8F));
        } else {
            world.playSound(null, player.posX, player.posY, player.posZ, SoundEvents.ENTITY_FISHING_BOBBER_THROW, SoundCategory.NEUTRAL, 0.5F, 0.4F / (random.nextFloat() * 0.4F + 0.8F));
            if (!world.isRemote) {
                lureSpeed = EnchantmentHelper.getFishingSpeedBonus(heldStack);
                int luck = EnchantmentHelper.getFishingLuckBonus(heldStack);
                world.addEntity(new AquaFishingBobberEntity(player, world, luck, lureSpeed));
            }
            player.swingArm(hand);
            player.addStat(Stats.ITEM_USED.get(this));
        }

        return new ActionResult<>(ActionResultType.SUCCESS, heldStack);
    }
}
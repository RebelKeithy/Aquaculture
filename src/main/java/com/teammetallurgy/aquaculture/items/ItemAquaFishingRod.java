package com.teammetallurgy.aquaculture.items;

import com.teammetallurgy.aquaculture.entity.EntityAquaFishHook;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemFishingRod;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTier;
import net.minecraft.stats.StatList;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class ItemAquaFishingRod extends ItemFishingRod {
    private int enchantability;
    private boolean isAdminRod;

    public ItemAquaFishingRod(@Nullable ItemTier material, Properties properties) {
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
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, @Nonnull EnumHand hand) {
        ItemStack heldStack = player.getHeldItem(hand);
        if (player.fishEntity != null) {
            int hookRetraction = player.fishEntity.handleHookRetraction(heldStack);
            if (!this.isAdminRod) heldStack.damageItem(hookRetraction, player);
            player.swingArm(hand);
            world.playSound(null, player.posX, player.posY, player.posZ, SoundEvents.ENTITY_FISHING_BOBBER_RETRIEVE, SoundCategory.NEUTRAL, 1.0F, 0.4F / (random.nextFloat() * 0.4F + 0.8F));
        } else {
            world.playSound(null, player.posX, player.posY, player.posZ, SoundEvents.ENTITY_FISHING_BOBBER_THROW, SoundCategory.NEUTRAL, 0.5F, 0.4F / (random.nextFloat() * 0.4F + 0.8F));
            if (!world.isRemote) {
                EntityAquaFishHook fishHook = new EntityAquaFishHook(world, player);
                int speedBonus = EnchantmentHelper.getFishingSpeedBonus(heldStack);
                if (speedBonus > 0) {
                    fishHook.setLureSpeed(speedBonus);
                }

                int luckBonus = EnchantmentHelper.getFishingLuckBonus(heldStack);
                if (luckBonus > 0) {
                    fishHook.setLuck(luckBonus);
                }
                world.spawnEntity(fishHook);
            }

            player.swingArm(hand);
            player.addStat(StatList.ITEM_USED.get(this));
        }

        return new ActionResult<>(EnumActionResult.SUCCESS, heldStack);
    }
}
package com.teammetallurgy.aquaculture.item;

import com.teammetallurgy.aquaculture.api.AquacultureAPI;
import com.teammetallurgy.aquaculture.api.fishing.Hook;
import com.teammetallurgy.aquaculture.api.fishing.Hooks;
import com.teammetallurgy.aquaculture.entity.AquaFishingBobberEntity;
import com.teammetallurgy.aquaculture.misc.AquaConfig;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.FishingRodItem;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTier;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.stats.Stats;
import net.minecraft.util.*;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

public class AquaFishingRodItem extends FishingRodItem {
    private IItemTier material;
    private int enchantability;

    public AquaFishingRodItem(IItemTier material, Properties properties) {
        super(properties);
        this.enchantability = material == ItemTier.WOOD ? 10 : material.getEnchantability();
        this.material = material;
    }

    @Override
    public int getItemEnchantability() {
        return enchantability;
    }

    @Override
    public boolean showDurabilityBar(@Nonnull ItemStack stack) {
        return this.getDamage(stack) < this.getMaxDamage(stack) && super.showDurabilityBar(stack);
    }

    @Override
    @Nonnull
    public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, @Nonnull Hand hand) {
        ItemStack heldStack = player.getHeldItem(hand);
        int lureSpeed;
        int damage = this.getDamage(heldStack);
        if (damage >= this.getMaxDamage(heldStack)) return new ActionResult<>(ActionResultType.FAIL, heldStack);
        Hook hook = getHookType(heldStack);
        if (player.fishingBobber != null) {
            if (!world.isRemote) {
                lureSpeed = player.fishingBobber.handleHookRetraction(heldStack);
                int currentDamage = this.getMaxDamage(heldStack) - damage;
                if (lureSpeed >= currentDamage) {
                    lureSpeed = currentDamage;
                }
                if (!(AquaConfig.BASIC_OPTIONS.debugMode.get() && this.material == AquacultureAPI.MATS.NEPTUNIUM)) {
                    if (hook != null && hook.getDurabilityChance() > 0) {
                        if (random.nextDouble() >= hook.getDurabilityChance()) {
                            heldStack.attemptDamageItem(lureSpeed, world.rand, null);
                        }
                    } else {
                        heldStack.attemptDamageItem(lureSpeed, world.rand, null);
                    }
                }
            }
            player.swingArm(hand);
            world.playSound(null, player.posX, player.posY, player.posZ, SoundEvents.ENTITY_FISHING_BOBBER_RETRIEVE, SoundCategory.NEUTRAL, 1.0F, 0.4F / (random.nextFloat() * 0.4F + 0.8F));
        } else {
            world.playSound(null, player.posX, player.posY, player.posZ, SoundEvents.ENTITY_FISHING_BOBBER_THROW, SoundCategory.NEUTRAL, 0.5F, 0.4F / (random.nextFloat() * 0.4F + 0.8F));
            if (!world.isRemote) {
                lureSpeed = EnchantmentHelper.getFishingSpeedBonus(heldStack);
                if (this.material == AquacultureAPI.MATS.NEPTUNIUM) lureSpeed += 1;
                int luck = EnchantmentHelper.getFishingLuckBonus(heldStack);
                if (hook != null && hook == Hooks.GOLD) luck += 1;
                world.addEntity(new AquaFishingBobberEntity(player, world, luck, lureSpeed, hook));
            }
            player.swingArm(hand);
            player.addStat(Stats.ITEM_USED.get(this));
        }

        return new ActionResult<>(ActionResultType.SUCCESS, heldStack);
    }

    @Nullable
    public static Hook getHookType(@Nonnull ItemStack fishingRod) {
        Hook hook = null;
        ItemStack hookStack = fishingRod.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).orElseGet(null).getStackInSlot(0);
        if (hookStack.getItem() instanceof HookItem) {
            hook = ((HookItem) hookStack.getItem()).getHookType();
        }
        return hook;
    }

    @Override
    @Nullable
    public ICapabilityProvider initCapabilities(@Nonnull ItemStack stack, @Nullable CompoundNBT nbt) {
        return new FishingRodEquipementHandler();
    }

    @Override
    public void addInformation(@Nonnull ItemStack stack, @Nullable World world, List<ITextComponent> tooltips, ITooltipFlag tooltipFlag) {
        if (this.getDamage(stack) >= this.getMaxDamage(stack)) {
            tooltips.add(new TranslationTextComponent("aquaculture.fishing_rod.broken").setStyle(new Style().setItalic(true).setColor(TextFormatting.GRAY)));
        }
        Hook hook = getHookType(stack);
        if (hook != null) {
            tooltips.add(new TranslationTextComponent(hook.getItem().getTranslationKey()).setStyle(new Style().setColor(hook.getColor())));
        }
        super.addInformation(stack, world, tooltips, tooltipFlag);
    }

    public static class FishingRodEquipementHandler implements ICapabilitySerializable<INBT> {
        private final IItemHandler items = new ItemStackHandler(2) {
            @Override
            protected int getStackLimit(int slot, @Nonnull ItemStack stack) {
                return 1;
            }
        };

        @Override
        @Nonnull
        public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> capability, @Nullable Direction direction) {
            if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
                return LazyOptional.of(this::getItems).cast();
            }
            return LazyOptional.empty();
        }

        @Override
        public INBT serializeNBT() {
            return CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.writeNBT(this.getItems(), null);
        }

        @Override
        public void deserializeNBT(INBT nbt) {
            CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.readNBT(this.getItems(), null, nbt);
        }

        @Nonnull
        public IItemHandler getItems() {
            return items;
        }
    }
}
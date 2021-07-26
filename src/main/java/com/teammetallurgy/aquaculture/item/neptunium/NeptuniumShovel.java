package com.teammetallurgy.aquaculture.item.neptunium;

import com.teammetallurgy.aquaculture.Aquaculture;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ShovelItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nonnull;

public class NeptuniumShovel extends ShovelItem {

    public NeptuniumShovel(Tier tier, float damage, float speed) {
        super(tier, damage, speed, new Item.Properties().tab(Aquaculture.GROUP));
    }

    @Override
    public void inventoryTick(@Nonnull ItemStack stack, Level world, Entity entity, int itemSlot, boolean isSelected) {
        if (entity instanceof Player && stack.getItem() == this) {
            Player player = (Player) entity;
            stack.getOrCreateTag().putBoolean("inWater", player.isEyeInFluid(FluidTags.WATER));
        }
    }

    @Override
    public float getDestroySpeed(@Nonnull ItemStack stack, BlockState state) {
        float defaultSpeed = super.getDestroySpeed(stack, state);
        boolean isInWater = stack.hasTag() && stack.getTag() != null && stack.getTag().getBoolean("inWater");
        return isInWater ? (defaultSpeed * 5.0F) * 5.0F : defaultSpeed;
    }
}
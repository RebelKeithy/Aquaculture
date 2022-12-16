package com.teammetallurgy.aquaculture.item.neptunium;

import com.teammetallurgy.aquaculture.Aquaculture;
import com.teammetallurgy.aquaculture.init.AquaItems;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nonnull;

public class NeptuniumPickaxe extends PickaxeItem {

    public NeptuniumPickaxe(Tier tier, int attackDamage, float attackSpeed) {
        super(tier, attackDamage, attackSpeed, new Item.Properties());
    }

    @Override
    public void inventoryTick(@Nonnull ItemStack stack, Level world, Entity entity, int itemSlot, boolean isSelected) {
        if (entity instanceof Player player && stack.getItem() == this) {
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
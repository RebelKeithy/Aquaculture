package com.teammetallurgy.aquaculture.item.neptunium;

import com.teammetallurgy.aquaculture.Aquaculture;
import com.teammetallurgy.aquaculture.entity.NeptuniumTridentEntity;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.TridentItem;
import net.minecraft.stats.Stats;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public class NeptuniumTrident extends TridentItem {

    public NeptuniumTrident() {
        super(new Item.Properties().group(Aquaculture.TAB).maxDamage(2500));
    }

    @Override
    public void onPlayerStoppedUsing(@Nonnull ItemStack stack, @Nonnull World world, LivingEntity livingEntity, int timeLeft) { //Copied from Trident. Adding own trident entity
        if (livingEntity instanceof PlayerEntity) {
            PlayerEntity player = (PlayerEntity) livingEntity;
            int useDuration = this.getUseDuration(stack) - timeLeft;
            if (useDuration >= 10) {
                int riptideModifier = EnchantmentHelper.getRiptideModifier(stack);
                if (riptideModifier <= 0 || player.isWet()) {
                    if (!world.isRemote) {
                        stack.damageItem(1, player, (living) -> living.sendBreakAnimation(livingEntity.getActiveHand()));
                        if (riptideModifier == 0) {
                            NeptuniumTridentEntity trident = new NeptuniumTridentEntity(world, player, stack);
                            trident.shoot(player, player.rotationPitch, player.rotationYaw, 0.0F, 2.5F + (float) riptideModifier * 0.5F, 1.0F);
                            if (player.abilities.isCreativeMode) {
                                trident.pickupStatus = AbstractArrowEntity.PickupStatus.CREATIVE_ONLY;
                            }
                            world.addEntity(trident);
                            world.playMovingSound(null, trident, SoundEvents.ITEM_TRIDENT_THROW, SoundCategory.PLAYERS, 1.0F, 1.0F);
                            if (!player.abilities.isCreativeMode) {
                                player.inventory.deleteStack(stack);
                            }
                        }
                    }
                    player.addStat(Stats.ITEM_USED.get(this));
                    if (riptideModifier > 0) {
                        float yaw = player.rotationYaw;
                        float pitch = player.rotationPitch;
                        float yawPitchNegative = -MathHelper.sin(yaw * 0.017453292F) * MathHelper.cos(pitch * 0.017453292F);
                        float sinPitch = -MathHelper.sin(pitch * 0.017453292F);
                        float yawPitch = MathHelper.cos(yaw * 0.017453292F) * MathHelper.cos(pitch * 0.017453292F);
                        float sqrt = MathHelper.sqrt(yawPitchNegative * yawPitchNegative + sinPitch * sinPitch + yawPitch * yawPitch);
                        float riptide = 3.0F * ((1.0F + (float) riptideModifier) / 4.0F);
                        yawPitchNegative *= riptide / sqrt;
                        sinPitch *= riptide / sqrt;
                        yawPitch *= riptide / sqrt;
                        player.addVelocity((double) yawPitchNegative, (double) sinPitch, (double) yawPitch);
                        player.startSpinAttack(20);
                        if (player.onGround) {
                            player.move(MoverType.SELF, new Vec3d(0.0D, 1.1999999284744263D, 0.0D));
                        }
                        SoundEvent sound;
                        if (riptideModifier >= 3) {
                            sound = SoundEvents.ITEM_TRIDENT_RIPTIDE_3;
                        } else if (riptideModifier == 2) {
                            sound = SoundEvents.ITEM_TRIDENT_RIPTIDE_2;
                        } else {
                            sound = SoundEvents.ITEM_TRIDENT_RIPTIDE_1;
                        }
                        world.playMovingSound(null, player, sound, SoundCategory.PLAYERS, 1.0F, 1.0F);
                    }
                }
            }
        }
    }
}
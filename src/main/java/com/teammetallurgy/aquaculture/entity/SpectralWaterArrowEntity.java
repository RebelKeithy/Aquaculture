package com.teammetallurgy.aquaculture.entity;

import com.teammetallurgy.aquaculture.init.AquaEntities;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.SpectralArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.network.NetworkHooks;
import net.neoforged.neoforge.network.PlayMessages;

import javax.annotation.Nonnull;

public class SpectralWaterArrowEntity extends SpectralArrow {

    public SpectralWaterArrowEntity(EntityType<? extends SpectralArrow> arrow, Level level) {
        super(arrow, level);
    }

    public SpectralWaterArrowEntity(Level level, LivingEntity livingEntity, ItemStack stack) {
        super(level, livingEntity, stack);
    }

    public SpectralWaterArrowEntity(PlayMessages.SpawnEntity spawnPacket, Level level) {
        super(level, 0, 0, 0, ItemStack.EMPTY);
    }

    @Override
    protected float getWaterInertia() {
        return 1.0F;
    }

    @Override
    @Nonnull
    public EntityType<?> getType() {
        return AquaEntities.SPECTRAL_WATER_ARROW.get();
    }

    @Override
    @Nonnull
    public Packet<ClientGamePacketListener> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}

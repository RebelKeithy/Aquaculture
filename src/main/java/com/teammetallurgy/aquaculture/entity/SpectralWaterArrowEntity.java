package com.teammetallurgy.aquaculture.entity;

import com.teammetallurgy.aquaculture.init.AquaEntities;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.SpectralArrow;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.network.NetworkHooks;
import net.neoforged.neoforge.network.PlayMessages;

import javax.annotation.Nonnull;

public class SpectralWaterArrowEntity extends SpectralArrow {

    public SpectralWaterArrowEntity(PlayMessages.SpawnEntity spawnPacket, Level world) {
        super(world, 0, 0, 0);
    }

    public SpectralWaterArrowEntity(EntityType<? extends SpectralArrow> arrow, Level world) {
        super(arrow, world);
    }

    public SpectralWaterArrowEntity(Level world, LivingEntity livingEntity) {
        super(world, livingEntity);
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

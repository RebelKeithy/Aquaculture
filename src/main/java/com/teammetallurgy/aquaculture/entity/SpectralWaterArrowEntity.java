package com.teammetallurgy.aquaculture.entity;

import com.teammetallurgy.aquaculture.init.AquaEntities;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.SpectralArrowEntity;
import net.minecraft.network.IPacket;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.FMLPlayMessages;
import net.minecraftforge.fml.network.NetworkHooks;

import javax.annotation.Nonnull;

public class SpectralWaterArrowEntity extends SpectralArrowEntity {

    public SpectralWaterArrowEntity(FMLPlayMessages.SpawnEntity spawnPacket, World world) {
        super(world, 0, 0, 0);
    }

    public SpectralWaterArrowEntity(EntityType<? extends SpectralArrowEntity> arrow, World world) {
        super(arrow, world);
    }

    public SpectralWaterArrowEntity(World world, LivingEntity livingEntity) {
        super(world, livingEntity);
    }

    @Override
    protected float getWaterDrag() {
        return 1.0F;
    }

    @Override
    @Nonnull
    public EntityType<?> getType() {
        return AquaEntities.SPECTRAL_WATER_ARROW;
    }

    @Override
    @Nonnull
    public IPacket<?> createSpawnPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}

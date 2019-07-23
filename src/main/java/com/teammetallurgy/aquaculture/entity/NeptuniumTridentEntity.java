package com.teammetallurgy.aquaculture.entity;

import com.teammetallurgy.aquaculture.init.AquaEntities;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.TridentEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.IPacket;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.FMLPlayMessages;
import net.minecraftforge.fml.network.NetworkHooks;

import javax.annotation.Nonnull;

public class NeptuniumTridentEntity extends TridentEntity {

    public NeptuniumTridentEntity(FMLPlayMessages.SpawnEntity spawPacket, World world) {
        super(world, 0, 0, 0);
    }

    public NeptuniumTridentEntity(EntityType<? extends NeptuniumTridentEntity> tridentEntity, World world) {
        super(tridentEntity, world);
    }

    public NeptuniumTridentEntity(World world, LivingEntity livingEntity, ItemStack stack) {
        super(world, livingEntity, stack);
    }

    @Override
    @Nonnull
    public EntityType<?> getType() {
        return AquaEntities.NEPTUNIUM_TRIDENT;
    }

    @Override
    @Nonnull
    public IPacket<?> createSpawnPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}
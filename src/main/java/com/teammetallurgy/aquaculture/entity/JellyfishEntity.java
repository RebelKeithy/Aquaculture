package com.teammetallurgy.aquaculture.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.SquidEntity;
import net.minecraft.world.World;

public class JellyfishEntity extends SquidEntity {

    public JellyfishEntity(EntityType<? extends SquidEntity> entityType, World world) {
        super(entityType, world);
    }
}
package com.teammetallurgy.aquaculture.item.neptunium;

import com.teammetallurgy.aquaculture.entity.SpectralWaterArrowEntity;
import com.teammetallurgy.aquaculture.entity.WaterArrowEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.Item;

import javax.annotation.Nonnull;

public class NeptuniumBow extends BowItem {

    public NeptuniumBow() {
        super(new Item.Properties().stacksTo(1).durability(2500));
    }

    @Override
    @Nonnull
    public AbstractArrow customArrow(@Nonnull AbstractArrow arrowEntity) {
        if (arrowEntity.getType() == EntityType.ARROW) {
            Entity shooter = arrowEntity.getOwner();
            if (shooter instanceof LivingEntity) {
                return new WaterArrowEntity(arrowEntity.level(), (LivingEntity) shooter);
            }
        }
        if (arrowEntity.getType() == EntityType.SPECTRAL_ARROW) {
            Entity shooter = arrowEntity.getOwner();
            if (shooter instanceof LivingEntity) {
                return new SpectralWaterArrowEntity(arrowEntity.level(), (LivingEntity) shooter);
            }
        }
        return super.customArrow(arrowEntity);
    }
}
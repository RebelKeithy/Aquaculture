package com.teammetallurgy.aquaculture.item.neptunium;

import com.teammetallurgy.aquaculture.Aquaculture;
import com.teammetallurgy.aquaculture.entity.SpectralWaterArrowEntity;
import com.teammetallurgy.aquaculture.entity.WaterArrowEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.BowItem;
import net.minecraft.item.Item;

import javax.annotation.Nonnull;

public class NeptuniumBow extends BowItem {

    public NeptuniumBow() {
        super(new Item.Properties().group(Aquaculture.GROUP).maxStackSize(1).maxDamage(2500));
    }

    @Override
    @Nonnull
    public AbstractArrowEntity customArrow(@Nonnull AbstractArrowEntity arrowEntity) {
        if (arrowEntity.getType() == EntityType.ARROW) {
            Entity shooter = arrowEntity.func_234616_v_();
            if (shooter instanceof LivingEntity) {
                return new WaterArrowEntity(arrowEntity.world, (LivingEntity) shooter);
            }
        }
        if (arrowEntity.getType() == EntityType.SPECTRAL_ARROW) {
            Entity shooter = arrowEntity.func_234616_v_();
            if (shooter instanceof LivingEntity) {
                return new SpectralWaterArrowEntity(arrowEntity.world, (LivingEntity) shooter);
            }
        }
        return super.customArrow(arrowEntity);
    }
}
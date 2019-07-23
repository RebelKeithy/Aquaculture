package com.teammetallurgy.aquaculture.item.neptunium;

import com.teammetallurgy.aquaculture.Aquaculture;
import com.teammetallurgy.aquaculture.entity.WaterArrowEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.BowItem;
import net.minecraft.item.Item;

import javax.annotation.Nonnull;

public class NeptuniumBow extends BowItem {

    public NeptuniumBow() {
        super(new Item.Properties().group(Aquaculture.TAB).maxStackSize(1).maxDamage(2500));
    }

    @Override
    @Nonnull
    public AbstractArrowEntity customeArrow(@Nonnull AbstractArrowEntity arrow) {
        return new WaterArrowEntity(arrow.world, arrow.world.getPlayerByUuid(arrow.shootingEntity));
    }
}
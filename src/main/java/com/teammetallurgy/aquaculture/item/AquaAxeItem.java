package com.teammetallurgy.aquaculture.item;

import net.minecraft.item.AxeItem;
import net.minecraft.item.IItemTier;
import net.minecraft.item.Item;

public class AquaAxeItem extends AxeItem {

    public AquaAxeItem(IItemTier tier, float attackDamage, float attackSpeed, Item.Properties builder) {
        super(tier, attackDamage, attackSpeed, builder);
    }
}
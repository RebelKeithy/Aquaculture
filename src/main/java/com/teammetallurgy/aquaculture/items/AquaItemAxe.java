package com.teammetallurgy.aquaculture.items;

import net.minecraft.item.IItemTier;
import net.minecraft.item.Item;
import net.minecraft.item.ItemAxe;

public class AquaItemAxe extends ItemAxe {

    public AquaItemAxe(IItemTier tier, float attackDamage, float attackSpeed, Item.Properties builder) {
        super(tier, attackDamage, attackSpeed, builder);
    }
}
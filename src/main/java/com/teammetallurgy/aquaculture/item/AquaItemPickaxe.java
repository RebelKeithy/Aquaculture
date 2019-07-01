package com.teammetallurgy.aquaculture.item;

import net.minecraft.item.IItemTier;
import net.minecraft.item.Item;
import net.minecraft.item.PickaxeItem;

public class AquaItemPickaxe extends PickaxeItem {

    public AquaItemPickaxe(IItemTier tier, int attackDamage, float attackSpeed, Item.Properties builder) {
        super(tier, attackDamage, attackSpeed, builder);
    }
}
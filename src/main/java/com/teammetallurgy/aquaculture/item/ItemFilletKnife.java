package com.teammetallurgy.aquaculture.item;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.teammetallurgy.aquaculture.Aquaculture;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IItemTier;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;

import javax.annotation.Nonnull;

public class ItemFilletKnife extends SwordItem {
    private final float attackDamage;

    public ItemFilletKnife(IItemTier material) {
        super(material, 0, 0.0F, new Item.Properties().group(Aquaculture.TAB).defaultMaxDamage((int) (material.getMaxUses() * 0.75F))); //Setting values to 0, since overriding vanilla behaviour anyways
        this.attackDamage = material.getAttackDamage() / 2;
    }

    @Override
    public boolean canApplyAtEnchantingTable(@Nonnull ItemStack stack, Enchantment enchantment) {
        return super.canApplyAtEnchantingTable(stack, enchantment) && canApplyEnchantment(enchantment);
    }

    private boolean canApplyEnchantment(Enchantment enchantment) {
        return enchantment != Enchantments.LOOTING && enchantment != Enchantments.SWEEPING;
    }

    @Override
    public float getAttackDamage() {
        return this.attackDamage;
    }

    @Override
    @Nonnull
    public Multimap<String, AttributeModifier> getAttributeModifiers(@Nonnull EquipmentSlotType slotType) {
        Multimap<String, AttributeModifier> map = HashMultimap.create();
        if (slotType == EquipmentSlotType.MAINHAND) {
            map.put(SharedMonsterAttributes.ATTACK_DAMAGE.getName(), new AttributeModifier(ATTACK_DAMAGE_MODIFIER, "Weapon modifier", (double) this.attackDamage, AttributeModifier.Operation.ADDITION));
            map.put(SharedMonsterAttributes.ATTACK_SPEED.getName(), new AttributeModifier(ATTACK_SPEED_MODIFIER, "Weapon modifier", (double) -2.2F, AttributeModifier.Operation.ADDITION));
        }
        return map;
    }
}
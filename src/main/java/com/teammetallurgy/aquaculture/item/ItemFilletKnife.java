package com.teammetallurgy.aquaculture.item;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import com.teammetallurgy.aquaculture.Aquaculture;
import com.teammetallurgy.aquaculture.api.AquacultureAPI;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IItemTier;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.util.text.IFormattableTextComponent;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

public class ItemFilletKnife extends SwordItem {
    private final float attackDamage;
    private final Multimap<Attribute, AttributeModifier> attributes;

    public ItemFilletKnife(IItemTier material) {
        super(material, 0, 0.0F, new Item.Properties().group(Aquaculture.GROUP).defaultMaxDamage((int) (material.getMaxUses() * 0.75F))); //Setting values to 0, since overriding vanilla behaviour anyways
        this.attackDamage = material.getAttackDamage() / 2;

        ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
        builder.put(Attributes.field_233823_f_, new AttributeModifier(ATTACK_DAMAGE_MODIFIER, "Weapon modifier", this.attackDamage, AttributeModifier.Operation.ADDITION));
        builder.put(Attributes.field_233825_h_, new AttributeModifier(ATTACK_SPEED_MODIFIER, "Weapon modifier", -2.2F, AttributeModifier.Operation.ADDITION));
        this.attributes = builder.build();
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
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(@Nonnull EquipmentSlotType slotType) {
        return slotType == EquipmentSlotType.MAINHAND ? this.attributes : ImmutableMultimap.of();
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void addInformation(@Nonnull ItemStack stack, @Nullable World world, @Nonnull List<ITextComponent> tooltip, @Nonnull ITooltipFlag tooltipFlag) {
        if (this.getTier() == AquacultureAPI.MATS.NEPTUNIUM) {
            IFormattableTextComponent unbrekable = new TranslationTextComponent("aquaculture.unbreakable");
            tooltip.add(unbrekable.func_240703_c_(unbrekable.getStyle().func_240712_a_(TextFormatting.DARK_GRAY).func_240713_a_(true)));
        }
        super.addInformation(stack, world, tooltip, tooltipFlag);
    }
}
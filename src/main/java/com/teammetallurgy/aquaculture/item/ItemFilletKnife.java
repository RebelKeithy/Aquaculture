package com.teammetallurgy.aquaculture.item;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import com.teammetallurgy.aquaculture.Aquaculture;
import com.teammetallurgy.aquaculture.api.AquacultureAPI;
import net.minecraft.block.BlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IItemTier;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.util.math.BlockPos;
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
        builder.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(ATTACK_DAMAGE_MODIFIER, "Weapon modifier", this.attackDamage, AttributeModifier.Operation.ADDITION));
        builder.put(Attributes.ATTACK_SPEED, new AttributeModifier(ATTACK_SPEED_MODIFIER, "Weapon modifier", -2.2F, AttributeModifier.Operation.ADDITION));
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
    public boolean hitEntity(@Nonnull ItemStack stack, @Nonnull LivingEntity target, @Nonnull LivingEntity attacker) {
        return this.getTier() == AquacultureAPI.MATS.NEPTUNIUM || super.hitEntity(stack, target, attacker);
    }

    @Override
    public boolean onBlockDestroyed(@Nonnull ItemStack stack, @Nonnull World world, @Nonnull BlockState state, @Nonnull BlockPos pos, @Nonnull LivingEntity entityLiving) {
        return getTier() == AquacultureAPI.MATS.NEPTUNIUM || super.onBlockDestroyed(stack, world, state, pos, entityLiving);
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void addInformation(@Nonnull ItemStack stack, @Nullable World world, @Nonnull List<ITextComponent> tooltip, @Nonnull ITooltipFlag tooltipFlag) {
        if (this.getTier() == AquacultureAPI.MATS.NEPTUNIUM) {
            IFormattableTextComponent unbreakable = new TranslationTextComponent("aquaculture.unbreakable");
            tooltip.add(unbreakable.mergeStyle(unbreakable.getStyle().setFormatting(TextFormatting.DARK_GRAY).setBold(true)));
        }
        super.addInformation(stack, world, tooltip, tooltipFlag);
    }
}
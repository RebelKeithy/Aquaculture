package com.teammetallurgy.aquaculture.item.neptunium;

import com.teammetallurgy.aquaculture.Aquaculture;
import com.teammetallurgy.aquaculture.init.AquaItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.ModifiableAttributeInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import javax.annotation.Nonnull;
import java.util.UUID;

@Mod.EventBusSubscriber(modid = Aquaculture.MOD_ID)
public class NeptuniumArmor extends ArmorItem {
    private static final AttributeModifier INCREASED_SWIM_SPEED = new AttributeModifier(UUID.fromString("d820cadc-2d19-421c-b19f-4c1f5b84a418"), "Neptunium Boots swim speed boost", 1.5D, AttributeModifier.Operation.ADDITION);
    private String texture;

    public NeptuniumArmor(IArmorMaterial armorMaterial, EquipmentSlotType equipmentSlot) {
        super(armorMaterial, equipmentSlot, new Item.Properties().group(Aquaculture.GROUP));
    }

    @Override
    public void onArmorTick(@Nonnull ItemStack stack, World world, PlayerEntity player) {
        ModifiableAttributeInstance swimSpeed = player.getAttribute(ForgeMod.SWIM_SPEED.get());
        if (player.areEyesInFluid(FluidTags.WATER)) {
            if (this.slot == EquipmentSlotType.HEAD) {
                player.addPotionEffect(new EffectInstance(Effects.NIGHT_VISION, 20, 0, false, false, false));
            } else if (this.slot == EquipmentSlotType.CHEST) {
                player.addPotionEffect(new EffectInstance(Effects.WATER_BREATHING, 20, 0, false, false, false));
            } else if (this.slot == EquipmentSlotType.LEGS) {
                if (!player.isCrouching()) {
                    player.setMotion(player.getMotion().add(0, player.fallDistance, 0));
                }
            } else if (this.slot == EquipmentSlotType.FEET) {
                if (!world.isRemote) {
                    if (swimSpeed != null && !swimSpeed.hasModifier(INCREASED_SWIM_SPEED)) {
                        swimSpeed.applyNonPersistentModifier(INCREASED_SWIM_SPEED);
                    }
                }
            }
        }
    }

    @SubscribeEvent
    public static void onLivingTick(LivingEvent.LivingUpdateEvent event) {
        LivingEntity livingEntity = event.getEntityLiving();
        ModifiableAttributeInstance swimSpeed = livingEntity.getAttribute(ForgeMod.SWIM_SPEED.get());
        if (livingEntity instanceof PlayerEntity) {
            if (livingEntity.getItemStackFromSlot(EquipmentSlotType.FEET).getItem() != AquaItems.NEPTUNIUM_BOOTS) {
                if (!livingEntity.world.isRemote) {
                    if (swimSpeed != null && swimSpeed.hasModifier(INCREASED_SWIM_SPEED)) {
                        swimSpeed.removeModifier(INCREASED_SWIM_SPEED);
                    }
                }
            }
            if (!livingEntity.areEyesInFluid(FluidTags.WATER)) {
                if (swimSpeed != null && swimSpeed.hasModifier(INCREASED_SWIM_SPEED)) {
                    swimSpeed.removeModifier(INCREASED_SWIM_SPEED);
                }
            }
        }
    }

    public Item setArmorTexture(String string) {
        this.texture = string;
        return this;
    }

    @Override
    public String getArmorTexture(@Nonnull ItemStack stack, Entity entity, EquipmentSlotType slot, String layer) {
        return "aquaculture:textures/armor/" + this.texture + ".png";
    }
}

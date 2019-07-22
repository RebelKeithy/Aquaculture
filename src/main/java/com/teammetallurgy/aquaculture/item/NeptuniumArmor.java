package com.teammetallurgy.aquaculture.item;

import com.teammetallurgy.aquaculture.Aquaculture;
import com.teammetallurgy.aquaculture.init.AquaItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.MoverType;
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
import net.minecraftforge.fml.common.Mod;

import javax.annotation.Nonnull;

@Mod.EventBusSubscriber(modid = Aquaculture.MOD_ID)
public class NeptuniumArmor extends ArmorItem {
    private String texture;

    public NeptuniumArmor(IArmorMaterial armorMaterial, EquipmentSlotType equipmentSlot, Item.Properties builder) {
        super(armorMaterial, equipmentSlot, builder);
    }

    @Override
    public void onArmorTick(@Nonnull ItemStack stack, World world, PlayerEntity player) {
        if (player.areEyesInFluid(FluidTags.WATER)) {
            if (this.slot == EquipmentSlotType.HEAD) {
                player.addPotionEffect(new EffectInstance(Effects.NIGHT_VISION, 1, 0, false, false, false));
            } else if (this.slot == EquipmentSlotType.CHEST) {
                player.addPotionEffect(new EffectInstance(Effects.WATER_BREATHING, 1, 0, false, false, false));
            } else if(this.slot == EquipmentSlotType.LEGS) {
                System.out.println(player.getMotion().y);
                player.setMotion(player.getMotion().add(0, player.getMotion().y, 0));
            } else if (this.slot == EquipmentSlotType.FEET) {
                player.addPotionEffect(new EffectInstance(Effects.SPEED, 1, 0, false, false, false));
                player.move(MoverType.PLAYER, player.getMotion()); //Make the swimming react to the swiftness potion
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
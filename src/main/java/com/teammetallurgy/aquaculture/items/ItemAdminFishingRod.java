package com.teammetallurgy.aquaculture.items;

import com.google.common.base.Preconditions;
import com.teammetallurgy.aquaculture.handlers.EntityCustomFishHook;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemFishingRod;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.*;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;

public class ItemAdminFishingRod extends ItemFishingRod {

    public ItemAdminFishingRod(int d) {
        setMaxDamage(d);
        setMaxStackSize(1);
        addPropertyOverride(new ResourceLocation("cast"), (stack, world, entity) -> {
            if (entity != null) {
                boolean isMain = entity.getHeldItemMainhand() == stack;
                boolean isOff = entity.getHeldItemOffhand() == stack;

                if (entity.getHeldItemMainhand().getItem() instanceof ItemAdminFishingRod) {
                    isOff = false;
                }
                return (isMain || isOff) && entity instanceof EntityPlayer && ((EntityPlayer) entity).fishEntity != null ? 1.0F : 0.0F;
            }
            return 0.0F;
        });
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean isFull3D() {
        return true;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean shouldRotateAroundWhenRendering() {
        return true;
    }

    @Override
    @Nonnull
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, @Nonnull EnumHand hand) {
        ItemStack heldStack = player.getHeldItem(hand);

        if (player.fishEntity != null) {
            // int i = entityplayer.fishEntity.handleHookRetraction();
            // itemstack.damageItem(i, entityplayer);
            player.swingArm(hand);

            if (!heldStack.hasTagCompound())
                heldStack.setTagCompound(new NBTTagCompound());

            NBTTagCompound tag = heldStack.getTagCompound();
            Preconditions.checkNotNull(tag, "tagCompound");
            tag.setBoolean("using", false);
        } else {
            world.playSound(null, player.posX, player.posY, player.posZ, SoundEvents.ENTITY_BOBBER_THROW, SoundCategory.NEUTRAL, 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));
            if (!world.isRemote) {
                world.spawnEntity(new EntityCustomFishHook(world, player, true));
            }
            player.swingArm(hand);

            if (!heldStack.hasTagCompound())
                heldStack.setTagCompound(new NBTTagCompound());

            NBTTagCompound tag = heldStack.getTagCompound();
            Preconditions.checkNotNull(tag, "tagCompound");
            tag.setBoolean("using", true);
        }
        return new ActionResult<>(EnumActionResult.SUCCESS, heldStack);
    }
}
package com.teammetallurgy.aquaculture.items;

import com.teammetallurgy.aquaculture.handlers.EntityCustomFishHook;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.*;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemAquacultureWoodenFishingRod extends ItemAquaculture {
    // public IIcon usingIcon;
    public String type;
    public int enchantability;

    public ItemAquacultureWoodenFishingRod(int d, int enchantability, String type) {
        super();
        setMaxDamage(d);
        setMaxStackSize(1);
        this.type = type;
        this.enchantability = enchantability;
        addPropertyOverride(new ResourceLocation("cast"), new IItemPropertyGetter() {

            @Override
            public float apply(ItemStack stack, World world, EntityLivingBase entity) {
                if (entity != null && ((EntityPlayer) entity).fishEntity != null && stack != null && entity.getHeldItemMainhand() == stack) {
                    return 1.0F;
                }

                return 0;
            }
        });
    }

    @SideOnly(Side.CLIENT)
    /**
     * Returns True is the item is renderer in full 3D when hold.
     */
    @Override
    public boolean isFull3D() {
        return true;
    }

    @SideOnly(Side.CLIENT)
    /**
     * Returns true if this item should be rotated by 180 degrees around the Y axis when being held in an entities
     * hands.
     */
    @Override
    public boolean shouldRotateAroundWhenRendering() {
        return true;
    }

    @Override
    public boolean isItemTool(ItemStack par1ItemStack) {
        return true;
    }

    @Override
    public int getItemEnchantability() {
        return enchantability;
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(ItemStack itemstack, World world, EntityPlayer entityplayer, EnumHand hand) {
        if (entityplayer.fishEntity != null) {
            int i = entityplayer.fishEntity.handleHookRetraction();
            itemstack.damageItem(i, entityplayer);
            entityplayer.swingArm(hand);

            if (!itemstack.hasTagCompound())
                itemstack.setTagCompound(new NBTTagCompound());

            NBTTagCompound tag = itemstack.getTagCompound();
            tag.setBoolean("using", false);
        } else {
            world.playSound(null, entityplayer.posX, entityplayer.posY, entityplayer.posZ, SoundEvents.ENTITY_BOBBER_THROW, SoundCategory.NEUTRAL, 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));
            if (!world.isRemote) {
                world.spawnEntityInWorld(new EntityCustomFishHook(world, entityplayer));
            }
            entityplayer.swingArm(hand);

            if (!itemstack.hasTagCompound())
                itemstack.setTagCompound(new NBTTagCompound());

            NBTTagCompound tag = itemstack.getTagCompound();
            tag.setBoolean("using", true);
        }
        return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, itemstack);
    }
}

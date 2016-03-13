package com.teammetallurgy.aquaculture.items;

import com.teammetallurgy.aquaculture.Aquaculture;
import com.teammetallurgy.aquaculture.handlers.EntityCustomFishHook;

import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemAdminFishingRod extends ItemAquaculture {

    public ItemAdminFishingRod(int d) {
        super();
        setMaxDamage(d);
        setMaxStackSize(1);
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
    public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer entityplayer) {
        if (entityplayer.fishEntity != null) {
            // int i = entityplayer.fishEntity.handleHookRetraction();
            // itemstack.damageItem(i, entityplayer);
            entityplayer.swingItem();

            if (!itemstack.hasTagCompound())
                itemstack.setTagCompound(new NBTTagCompound());

            NBTTagCompound tag = itemstack.getTagCompound();
            tag.setBoolean("using", false);
        } else {
            world.playSoundAtEntity(entityplayer, "random.bow", 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));
            if (!world.isRemote) {
                world.spawnEntityInWorld(new EntityCustomFishHook(world, entityplayer, true));
            }
            entityplayer.swingItem();

            if (!itemstack.hasTagCompound())
                itemstack.setTagCompound(new NBTTagCompound());

            NBTTagCompound tag = itemstack.getTagCompound();
            tag.setBoolean("using", true);
        }
        return itemstack;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public ModelResourceLocation getModel(ItemStack stack, EntityPlayer player, int useRemaining) {

        if (player.fishEntity != null && stack != null && stack.getItem() != null) {
            return new ModelResourceLocation(Aquaculture.MOD_ID + ":admin_fishing_rod_cast", "inventory");
        }

        return null;
    }
}

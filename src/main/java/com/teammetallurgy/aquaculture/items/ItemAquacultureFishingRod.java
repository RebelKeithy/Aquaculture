package com.teammetallurgy.aquaculture.items;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.google.common.collect.Sets;
import com.teammetallurgy.aquaculture.Aquaculture;
import com.teammetallurgy.aquaculture.handlers.EntityCustomFishHook;

import net.minecraft.block.Block;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Set;

public class ItemAquacultureFishingRod extends ItemTool {
    private static Set<Block> effectiveBlockSet = Sets.newHashSet(new Block[] {});
    // public IIcon usingIcon;
    public String type;
    public int enchantability;

    public ItemAquacultureFishingRod(int d, int enchantability, String type) {
        super(0F, EnumHelper.addToolMaterial("Fishing" + type, 0, d, 0, 0, enchantability), effectiveBlockSet);
        setMaxDamage(d);
        setMaxStackSize(1);
        this.type = type;
        this.enchantability = enchantability;
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
    public int getItemEnchantability() {
        return enchantability;
    }

    @Override
    public Multimap<String, AttributeModifier> getAttributeModifiers(ItemStack stack) {
        return HashMultimap.create();
    }

    @Override
    public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer entityplayer) {
        if (entityplayer.fishEntity != null) {
            int i = entityplayer.fishEntity.handleHookRetraction();
            itemstack.damageItem(i, entityplayer);
            entityplayer.swingItem();

            if (!itemstack.hasTagCompound())
                itemstack.setTagCompound(new NBTTagCompound());

            NBTTagCompound tag = itemstack.getTagCompound();
            tag.setBoolean("using", false);
        } else {
            world.playSoundAtEntity(entityplayer, "random.bow", 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));
            if (!world.isRemote) {
                world.spawnEntityInWorld(new EntityCustomFishHook(world, entityplayer));
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
            Item item = stack.getItem();
            if (item == AquacultureItems.ironFishingRod) {
                return new ModelResourceLocation(Aquaculture.MOD_ID + ":iron_fishing_rod_cast", "inventory");
            }

            if (item == AquacultureItems.goldFishingRod) {
                return new ModelResourceLocation(Aquaculture.MOD_ID + ":gold_fishing_rod_cast", "inventory");
            }

            if (item == AquacultureItems.diamondFishingRod) {
                return new ModelResourceLocation(Aquaculture.MOD_ID + ":diamond_fishing_rod_cast", "inventory");
            }
        }

        return null;
    }
    /*
    @Override
    public IIcon getIcon(ItemStack stack, int renderPass, EntityPlayer player, ItemStack usingItem, int useRemaining) {
        if (!stack.hasTagCompound())
            stack.setTagCompound(new NBTTagCompound());
    
        NBTTagCompound tag = stack.getTagCompound();
    
        if (tag.hasKey("using"))
            ;
        {
            boolean using = tag.getBoolean("using");
    
            if (using) {
                return usingIcon;
            }
        }
    
        return itemIcon;
    }
    
    @SideOnly(Side.CLIENT)
    @Override
    public void registerIcons(IIconRegister par1IconRegister) {
        super.registerIcons(par1IconRegister);
    
        usingIcon = par1IconRegister.registerIcon("aquaculture:" + type + "FishingRodUsing");
    }
    */
}

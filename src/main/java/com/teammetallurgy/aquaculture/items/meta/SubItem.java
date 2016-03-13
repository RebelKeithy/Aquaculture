package com.teammetallurgy.aquaculture.items.meta;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class SubItem {
    public int itemID;
    public int damage;
    public MetaItem item;

    public String unlocalizedName;

    public SubItem(MetaItem metaItem) {

        item = metaItem;
        damage = item.addSubItem(this);
    }

    /**
     * returns this;
     */
    public SubItem setCreativeTab(CreativeTabs par1CreativeTabs) {
        item.setCreativeTab(par1CreativeTabs);
        return this;
    }

    /**
     * Sets the unlocalized name of this item to the string passed as the parameter, prefixed by "item."
     */
    public SubItem setUnlocalizedName(String par1Str) {
        this.unlocalizedName = par1Str;
        return this;
    }

    /*public SubItem setTextureName(String str) {
        this.textureName = str;
        return this;
    }*/

    public ItemStack getItemStack() {
        return getItemStack(1);
    }

    public ItemStack getItemStack(int i) {
        return new ItemStack(item, i, damage);
    }

    public String getUnlocalizedName(ItemStack par1ItemStack) {
        return unlocalizedName;
    }

    public ItemStack onEaten(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
        return par1ItemStack;
    }

    public int getMaxItemUseDuration(ItemStack par1ItemStack) {
        return 0;
    }

    public EnumAction getItemUseAction(ItemStack par1ItemStack) {
        return EnumAction.NONE;
    }

    public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
        return par1ItemStack;
    }
}

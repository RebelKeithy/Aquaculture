package com.teammetallurgy.aquaculture.items.meta;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.ArrayList;
import java.util.List;

public class MetaItem extends Item {
    ArrayList<SubItem> subItems;

    public MetaItem() {
        super();
        this.setHasSubtypes(true);
        this.setMaxDamage(0);
    }

    public int addSubItem(SubItem subItem) {
        if (subItems == null)
            subItems = new ArrayList<SubItem>();

        subItems.add(subItem);
        return subItems.size() - 1;
    }

    @Override
    public String getUnlocalizedName(ItemStack itemStack) {
        int i = MathHelper.clamp_int(itemStack.getItemDamage(), 0, subItems.size());
        String uname = subItems.get(i).getUnlocalizedName(itemStack);
        uname = uname.replace(" ", "_");
        return "item." + uname;
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @SideOnly(Side.CLIENT)
    /**
     * returns a list of items with the same ID, but different meta (eg: dye returns 16 items)
     */
    @Override
    public void getSubItems(Item par1, CreativeTabs par2CreativeTabs, List par3List) {
        for (SubItem item : subItems) {
            par3List.add(item.getItemStack());
        }
    }

    /*
    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIconFromDamage(int par1) {
        if (par1 < subItems.size())
            return subItems.get(par1).getIcon();
    
        return this.itemIcon;
    }
    */

    /**
     * Returns the icon index of the stack given as argument.
     */
    /*
     * @SideOnly(Side.CLIENT) public final Icon getIconIndex(ItemStack par1ItemStack) { int damage = par1ItemStack.getItemDamage(); if(subItems.contains(damage)) { return
     * subItems.get(damage).getIconFromDamage(par1ItemStack); } return this.getIconFromDamage(par1ItemStack.getItemDamage()); }
     */
    /*
    @Override
    public IIcon getIcon(ItemStack stack, int renderPass, EntityPlayer player, ItemStack usingItem, int useRemaining) {
        int damage = stack.getItemDamage();
        if (damage < subItems.size()) {
            return subItems.get(damage).getIcon(stack, renderPass, player, usingItem, useRemaining);
        }
    
        return getIcon(stack, renderPass);
    }
    
    @Override
    public IIcon getIcon(ItemStack stack, int pass) {
        int damage = stack.getItemDamage();
        if (damage < subItems.size()) {
            return subItems.get(damage).getIcon(stack, pass);
        }
    
        return getIcon(stack, pass);
    }
    
    @SideOnly(Side.CLIENT)
    @Override
    public void registerIcons(IIconRegister par1IconRegister) {
        for (SubItem item : subItems) {
            item.registerIcons(par1IconRegister);
        }
    }
    */

    // ItemRedirects
    @Override
    public ItemStack onItemUseFinish(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
        int damage = par1ItemStack.getItemDamage();
        if (damage < subItems.size()) {
            return subItems.get(damage).onEaten(par1ItemStack, par2World, par3EntityPlayer);
        }

        return par1ItemStack;
    }

    /**
     * How long it takes to use or consume an item
     */
    @Override
    public int getMaxItemUseDuration(ItemStack par1ItemStack) {
        int damage = par1ItemStack.getItemDamage();
        if (damage < subItems.size()) {
            return subItems.get(damage).getMaxItemUseDuration(par1ItemStack);
        }

        return 0;
    }

    /**
     * returns the action that specifies what animation to play when the items is being used
     */
    @Override
    public EnumAction getItemUseAction(ItemStack par1ItemStack) {
        int damage = par1ItemStack.getItemDamage();
        if (damage < subItems.size()) {
            return subItems.get(damage).getItemUseAction(par1ItemStack);
        }

        return EnumAction.NONE;
    }

    @Override
    public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
        int damage = par1ItemStack.getItemDamage();
        if (damage < subItems.size()) {
            return subItems.get(damage).onItemRightClick(par1ItemStack, par2World, par3EntityPlayer);
        }

        return par1ItemStack;
    }
}

package com.teammetallurgy.aquaculture.items.meta;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.ArrayList;

public class MetaItemFood extends ItemFood {

    ArrayList<SubItemFood> subItems;

    public MetaItemFood() {
        super(0, 0, false);
        this.setHasSubtypes(true);
        this.setMaxDamage(0);
    }

    public int addSubItem(SubItemFood subItem) {
        if (subItems == null)
            subItems = new ArrayList<SubItemFood>();

        subItems.add(subItem);
        return subItems.size() - 1;
    }

    @Override
    public String getUnlocalizedName(ItemStack itemStack) {
        int i = MathHelper.clamp_int(itemStack.getItemDamage(), 0, subItems.size() - 1);
        String uname = subItems.get(i).getUnlocalizedName(itemStack);
        uname = uname.replace(" ", "_");
        return "item." + uname;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void getSubItems(Item item, CreativeTabs tab, NonNullList<ItemStack> itemStackList) {
        for (SubItemFood subItem : subItems) {
            itemStackList.add(subItem.getItemStack());
        }
    }

    // ItemRedirects
    @Override
    public ItemStack onItemUseFinish(ItemStack itemStack, World world, EntityLivingBase player) {
        int damage = itemStack.getItemDamage();
        if (damage >= 0 && damage < subItems.size()) {
            return subItems.get(damage).onEaten(itemStack, world, player);
        }

        return itemStack;
    }

    @Override
    public int getMaxItemUseDuration(ItemStack itemStack) {
        int damage = itemStack.getItemDamage();
        if (damage >= 0 && damage < subItems.size()) {
            return subItems.get(damage).getMaxItemUseDuration(itemStack);
        }

        return 0;
    }

    @Override
    public EnumAction getItemUseAction(ItemStack itemStack) {
        int damage = itemStack.getItemDamage();
        if (damage >= 0 && damage < subItems.size()) {
            return subItems.get(damage).getItemUseAction(itemStack);
        }

        return EnumAction.NONE;
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
        ItemStack itemStack = player.getHeldItem(hand);

        int damage = itemStack.getItemDamage();
        if (damage >= 0 && damage < subItems.size()) {
            ItemStack result = subItems.get(damage).onItemRightClick(itemStack, world, player, hand);
            return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, result);
        }

        return new ActionResult<ItemStack>(EnumActionResult.FAIL, itemStack);
    }

}

package com.teammetallurgy.aquaculture.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public class ItemTreasureChest extends Item {

    public ItemTreasureChest(Properties properties) {
        super(properties);
        /*loot.addLoot(Items.IRON_INGOT, 40);
        loot.addLoot(Items.GOLD_INGOT, 30);
        loot.addLoot(Items.DIAMOND, 15);
        loot.addLoot(Items.EMERALD, 15);*/ //TODO Move to loot table
    }

    @Override
    @Nonnull
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, @Nonnull EnumHand hand) {
        ItemStack heldStack = player.getHeldItem(hand);

        /*ItemStack randomLoot = loot.getRandomLoot();

        EntityItem entityitem = new EntityItem(player.world, player.posX, player.posY, player.posZ, randomLoot);
        world.spawnEntity(entityitem);*/

        heldStack.shrink(1);

        return super.onItemRightClick(world, player, hand);
    }
}
package com.teammetallurgy.aquaculture.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public class ItemLockbox extends Item {

    public ItemLockbox(Properties properties) {
        super(properties);
        /*loot.addLoot(Items.IRON_INGOT, 8); //TODO Move to loot table
        loot.addLoot(Items.GOLD_INGOT, 7);
        loot.addLoot(Items.GOLDEN_APPLE, 5);
        loot.addLoot(Items.DANDELION_YELLOW, 8);
        loot.addLoot(Items.REDSTONE, 8);
        loot.addLoot(Items.BOOK, 10);
        loot.addLoot(Items.PAPER, 10);
        loot.addLoot(Items.COMPASS, 10);
        loot.addLoot(Items.CLOCK, 10);
        loot.addLoot(Items.GLOWSTONE_DUST, 7);
        loot.addLoot(Items.GUNPOWDER, 10);
        loot.addLoot(Items.QUARTZ, 7);*/
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
package com.teammetallurgy.aquaculture.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public class ItemBox extends Item {

    public ItemBox(Item.Properties builder) {
        super(builder);
        /*loot = new WeightedLootSet(); //TODO Make it a loot table
        loot.addLoot(Blocks.STONE, 5, 1, 1);
        loot.addLoot(Blocks.DIRT, 5, 1, 1);
        loot.addLoot(Blocks.COBBLESTONE, 5);
        loot.addLoot(new ItemStack(Blocks.PLANKS, 1, 0), 1);
        loot.addLoot(new ItemStack(Blocks.PLANKS, 1, 1), 1);
        loot.addLoot(new ItemStack(Blocks.PLANKS, 1, 2), 1);
        loot.addLoot(new ItemStack(Blocks.PLANKS, 1, 3), 1);
        loot.addLoot(new ItemStack(Blocks.SAPLING, 1, 0), 1);
        loot.addLoot(new ItemStack(Blocks.SAPLING, 1, 1), 1);
        loot.addLoot(new ItemStack(Blocks.SAPLING, 1, 2), 1);
        loot.addLoot(new ItemStack(Blocks.SAPLING, 1, 3), 1);
        loot.addLoot(new ItemStack(Blocks.LOG, 1, 0), 1);
        loot.addLoot(new ItemStack(Blocks.LOG, 1, 1), 1);
        loot.addLoot(new ItemStack(Blocks.LOG, 1, 2), 1);
        loot.addLoot(new ItemStack(Blocks.LOG, 1, 3), 1);
        loot.addLoot(Blocks.GRAVEL, 5);
        loot.addLoot(new ItemStack(Items.COAL, 1, 0), 3);
        loot.addLoot(new ItemStack(Items.COAL, 1, 1), 3);
        loot.addLoot(Items.WHEAT_SEEDS, 3);
        loot.addLoot(Items.STICK, 5);
        loot.addLoot(Items.BOWL, 3);
        loot.addLoot(Items.LEATHER_HELMET, 2);
        loot.addLoot(Items.LEATHER_CHESTPLATE, 2);
        loot.addLoot(Items.LEATHER_LEGGINGS, 2);
        loot.addLoot(Items.LEATHER_BOOTS, 2);
        loot.addLoot(Items.FLINT, 4);
        loot.addLoot(Items.CLAY_BALL, 4);
        loot.addLoot(Items.BUCKET, 1);
        loot.addLoot(Items.LEATHER, 4);
        loot.addLoot(Items.SLIME_BALL, 1);
        loot.addLoot(Blocks.REEDS, 1);
        loot.addLoot(Items.BONE, 5);
        loot.addLoot(Items.ROTTEN_FLESH, 5);
        loot.addLoot(Items.GLASS_BOTTLE, 1);
        loot.addLoot(Items.CARROT, 1);
        loot.addLoot(Items.POTATO, 1);
        loot.addLoot(Blocks.VINE, 1);
        loot.addLoot(Blocks.TALLGRASS, 3);
        loot.addLoot(Items.STRING, 3);
        loot.addLoot(Items.FEATHER, 4);
        loot.addLoot(Items.APPLE, 1);*/
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
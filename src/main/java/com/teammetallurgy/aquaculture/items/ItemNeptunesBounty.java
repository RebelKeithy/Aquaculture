package com.teammetallurgy.aquaculture.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public class ItemNeptunesBounty extends Item {

    public ItemNeptunesBounty(Item.Properties properties) {
        super(properties);
    }

    public void initLoot() {
        /*if (Config.enableNeptuniumTools) { //TODO to loot table
            loot.addLoot(AquaItems.NEPTUNIUM_AXE, 1, 1, 1);
            loot.addLoot(AquaItems.NEPTUNIUM_PICKAXE, 1, 1, 1);
            loot.addLoot(AquaItems.NEPTUNIUM_SHOVEL, 1, 1, 1);
            loot.addLoot(AquaItems.NEPTUNIUM_HOE, 1, 1, 1);
            loot.addLoot(AquaItems.NEPTUNIUM_SWORD, 1, 1, 1);
        }
        if (Config.enableNeptuniumArmor) {
            loot.addLoot(AquaItems.NEPTUNIUM_HELMET, 1, 1, 1);
            loot.addLoot(AquaItems.NEPTUNIUM_PLATE, 1, 1, 1);
            loot.addLoot(AquaItems.NEPTUNIUM_LEGS, 1, 1, 1);
            loot.addLoot(AquaItems.NEPTUNIUM_BOOTS, 1, 1, 1);
        }
        if (Config.enableNeptuniumLoot) {
            loot.addLoot(AquaItems.NEPTUNIUM_BAR, 2, 1, 4);
        }*/
    }

    @Override
    @Nonnull
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, @Nonnull EnumHand hand) {
        ItemStack heldStack = player.getHeldItem(hand);

        /*ItemStack item = loot.getRandomLoot();

        EntityItem entityitem = new EntityItem(player.world, player.posX, player.posY, player.posZ, item);
        world.spawnEntity(entityitem);*/

        heldStack.shrink(1);

        return super.onItemRightClick(world, player, hand);
    }
}
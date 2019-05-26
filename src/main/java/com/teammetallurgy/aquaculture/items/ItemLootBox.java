package com.teammetallurgy.aquaculture.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.storage.loot.LootContext;

import javax.annotation.Nonnull;
import java.util.List;

public class ItemLootBox extends Item {
    private final ResourceLocation lootTable;

    public ItemLootBox(Properties properties, ResourceLocation lootTable) {
        super(properties);
        this.lootTable = lootTable;
    }

    @Override
    @Nonnull
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, @Nonnull EnumHand hand) {
        ItemStack heldStack = player.getHeldItem(hand);
        if (world.isRemote || lootTable == null) return new ActionResult<>(EnumActionResult.FAIL, heldStack);

        if (world instanceof WorldServer) {
            WorldServer worldServer = (WorldServer) world;
            LootContext.Builder builder = new LootContext.Builder(worldServer);
            List<ItemStack> loot = worldServer.getServer().getLootTableManager().getLootTableFromLocation(lootTable).generateLootForPools(world.rand, builder.build());
            this.giveItem(player, loot.get(0));
            return new ActionResult<>(EnumActionResult.SUCCESS, heldStack);
        }
        return super.onItemRightClick(world, player, hand);
    }

    /*
     * Gives the specified ItemStack to the player
     */
    private void giveItem(EntityPlayer player, @Nonnull ItemStack stack) {
        if (!player.inventory.addItemStackToInventory(stack)) {
            player.dropItem(stack, false);
        } else if (player instanceof EntityPlayerMP) {
            ((EntityPlayerMP) player).sendContainerToPlayer(player.inventoryContainer);
        }
    }
}
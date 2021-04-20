package com.teammetallurgy.aquaculture.misc;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.items.IItemHandler;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StackHelper {

    /*
     * Gives the specified ItemStack to the player
     */
    public static void giveItem(PlayerEntity player, @Nonnull ItemStack stack) {
        if (!player.inventory.addItemStackToInventory(stack)) {
            player.dropItem(stack, false);
        } else if (player instanceof ServerPlayerEntity) {
            ((ServerPlayerEntity) player).sendContainerToPlayer(player.container);
        }
    }

    /*
     * Stores the TileEntity in ItemStack form, making it contain its inventory etc.
     */
    public static ItemStack storeTEInStack(@Nonnull ItemStack stack, TileEntity tileEntity) {
        stack.setTagInfo("BlockEntityTag", tileEntity.write(new CompoundNBT()));
        return stack;
    }

    public static void dropInventory(World world, BlockPos pos, IItemHandler handler) {
        for (int slot = 0; slot < handler.getSlots(); ++slot) {
            InventoryHelper.spawnItemStack(world, pos.getX(), pos.getY(), pos.getZ(), handler.getStackInSlot(slot));
        }
    }

    public static Hand getUsedHand(@Nonnull ItemStack stackMainHand, Class<? extends Item> clazz) {
        return clazz.isAssignableFrom(stackMainHand.getItem().getClass()) ? Hand.MAIN_HAND : Hand.OFF_HAND;
    }

    public static Ingredient mergeIngredient(Ingredient i1, Ingredient i2) {
        List<ItemStack> stackList = new ArrayList<>();
        stackList.addAll(Arrays.asList(i1.getMatchingStacks()));
        stackList.addAll(Arrays.asList(i2.getMatchingStacks()));
        return ingredientFromStackList(stackList);
    }

    public static Ingredient ingredientFromStackList(List<ItemStack> stackList) {
        return Ingredient.fromItemListStream(stackList.stream().map(Ingredient.SingleItemList::new));
    }
}
package com.teammetallurgy.aquaculture.item;

import com.teammetallurgy.aquaculture.Aquaculture;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;

import javax.annotation.Nonnull;
import java.util.List;

public class LootBoxItem extends Item {
    private final ResourceLocation lootTable;

    public LootBoxItem(ResourceLocation lootTable) {
        super(new Item.Properties().tab(Aquaculture.GROUP));
        this.lootTable = lootTable;
    }

    @Override
    @Nonnull
    public InteractionResultHolder<ItemStack> use(Level world, Player player, @Nonnull InteractionHand hand) {
        ItemStack heldStack = player.getItemInHand(hand);
        if (world.isClientSide || this.lootTable == null) return new InteractionResultHolder<>(InteractionResult.FAIL, heldStack);

        if (world instanceof ServerLevel) {
            ServerLevel worldServer = (ServerLevel) world;
            LootContext.Builder builder = new LootContext.Builder(worldServer);
            List<ItemStack> loot = worldServer.getServer().getLootTables().get(this.lootTable).getRandomItems(builder.create(LootContextParamSets.EMPTY));
            if (!loot.isEmpty()) {
                ItemStack lootStack = loot.get(0);
                player.displayClientMessage(new TranslatableComponent("aquaculture.loot.open", lootStack.getHoverName()).withStyle(ChatFormatting.YELLOW), true);
                this.giveItem(player, lootStack);
                heldStack.shrink(1);
                return new InteractionResultHolder<>(InteractionResult.SUCCESS, heldStack);
            }
        }

        return super.use(world, player, hand);
    }

    /*
     * Gives the specified ItemStack to the player
     */
    private void giveItem(Player player, @Nonnull ItemStack stack) {
        if (!player.getInventory().add(stack)) {
            player.drop(stack, false);
        } else if (player instanceof ServerPlayer) {
            player.inventoryMenu.sendAllDataToRemote();
        }
    }
}
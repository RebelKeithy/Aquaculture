package com.teammetallurgy.aquaculture.items;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.teammetallurgy.aquaculture.Aquaculture;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.ServerWorld;
import net.minecraft.world.World;
import net.minecraft.world.storage.loot.*;
import net.minecraft.world.storage.loot.conditions.ILootCondition;
import net.minecraft.world.storage.loot.conditions.LootConditionManager;
import net.minecraft.world.storage.loot.functions.ILootFunction;
import net.minecraft.world.storage.loot.functions.LootFunctionManager;
import net.minecraftforge.fml.loading.FMLPaths;

import javax.annotation.Nonnull;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class LootBoxItem extends Item {
    private final ResourceLocation lootTable;

    public LootBoxItem(Properties properties, ResourceLocation lootTable) {
        super(properties);
        this.lootTable = lootTable;
    }

    public static final Gson GSON_INSTANCE = (new GsonBuilder()).registerTypeAdapter(RandomValueRange.class, new RandomValueRange.Serializer()).registerTypeAdapter(BinomialRange.class, new BinomialRange.Serializer()).registerTypeAdapter(ConstantRange.class, new ConstantRange.Serializer()).registerTypeAdapter(IntClamper.class, new IntClamper.Serializer()).registerTypeAdapter(LootPool.class, new LootPool.Serializer()).registerTypeAdapter(LootTable.class, new LootTable.Serializer()).registerTypeHierarchyAdapter(LootEntry.class, new LootEntryManager.Serializer()).registerTypeHierarchyAdapter(ILootFunction.class, new LootFunctionManager.Serializer()).registerTypeHierarchyAdapter(ILootCondition.class, new LootConditionManager.Serializer()).registerTypeHierarchyAdapter(LootContext.EntityTarget.class, new LootContext.EntityTarget.Serializer()).setPrettyPrinting().create();

    @Override
    @Nonnull
    public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, @Nonnull Hand hand) {
        ItemStack heldStack = player.getHeldItem(hand);
        if (world.isRemote || this.lootTable == null) return new ActionResult<>(ActionResultType.FAIL, heldStack);

        if (world instanceof ServerWorld) {
            ServerWorld worldServer = (ServerWorld) world;
            try {
                FileWriter writer = new FileWriter(new File(FMLPaths.GAMEDIR.get().toFile(), Aquaculture.MOD_ID + ".json"));
                writer.append(GSON_INSTANCE.toJson(worldServer.getServer().getLootTableManager().getLootTableFromLocation(LootTables.GAMEPLAY_FISHING_FISH)));
                writer.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }

            LootContext.Builder builder = new LootContext.Builder(worldServer);
            List<ItemStack> loot = worldServer.getServer().getLootTableManager().getLootTableFromLocation(this.lootTable).generate(builder.build(LootParameterSets.EMPTY));
            this.giveItem(player, loot.get(0));
            return new ActionResult<>(ActionResultType.SUCCESS, heldStack);
        }
        return super.onItemRightClick(world, player, hand);
    }

    /*
     * Gives the specified ItemStack to the player
     */
    private void giveItem(PlayerEntity player, @Nonnull ItemStack stack) {
        if (!player.inventory.addItemStackToInventory(stack)) {
            player.dropItem(stack, false);
        } else if (player instanceof ServerPlayerEntity) {
            ((ServerPlayerEntity) player).sendContainerToPlayer(player.container);
        }
    }
}
package com.teammetallurgy.aquaculture.entity;

import com.teammetallurgy.aquaculture.init.AquaEntities;
import com.teammetallurgy.aquaculture.init.AquaItems;
import com.teammetallurgy.aquaculture.misc.AquaConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.FishingBobberEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.IPacket;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ServerWorld;
import net.minecraft.world.World;
import net.minecraft.world.storage.loot.*;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.ItemFishedEvent;
import net.minecraftforge.fml.network.FMLPlayMessages;
import net.minecraftforge.fml.network.NetworkHooks;

import javax.annotation.Nonnull;
import java.util.List;

public class AquaFishingBobberEntity extends FishingBobberEntity {
    private final int luck;

    public AquaFishingBobberEntity(FMLPlayMessages.SpawnEntity spawPacket, World world) {
        super(world, Minecraft.getInstance().player, 0, 0, 0);
        this.luck = 0;

    }

    public AquaFishingBobberEntity(PlayerEntity player, World world, int luck, int lureSpeed) {
        super(player, world, luck, lureSpeed);
        this.luck = luck;
        this.angler.fishingBobber = this;
    }

    @Override
    @Nonnull
    public EntityType<?> getType() {
        return AquaEntities.BOBBER;
    }

    @Override
    @Nonnull
    public IPacket<?> createSpawnPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    @Override
    public int handleHookRetraction(@Nonnull ItemStack stack) {
        if (AquaConfig.BASIC_OPTIONS.debugMode.get() && stack.getItem() == AquaItems.NEPTUNIUM_FISHING_ROD) {
            if (this.world instanceof ServerWorld) {
                ServerWorld serverWorld = (ServerWorld) this.world;
                LootContext.Builder builder = new LootContext.Builder(serverWorld).withParameter(LootParameters.POSITION, new BlockPos(this)).withParameter(LootParameters.TOOL, stack).withRandom(this.rand).withLuck((float) this.luck + this.angler.getLuck());
                builder.withParameter(LootParameters.KILLER_ENTITY, this.angler).withParameter(LootParameters.THIS_ENTITY, this);
                LootTable table = serverWorld.getServer().getLootTableManager().getLootTableFromLocation(LootTables.GAMEPLAY_FISHING);
                List<ItemStack> list = table.generate(builder.build(LootParameterSets.FISHING));
                MinecraftForge.EVENT_BUS.post(new ItemFishedEvent(list, 0, this));

                for (ItemStack loot : list) {
                    ItemEntity itemEntity = new ItemEntity(this.world, this.posX, this.posY, this.posZ, loot);
                    double x = this.angler.posX - this.posX;
                    double y = this.angler.posY - this.posY;
                    double z = this.angler.posZ - this.posZ;
                    itemEntity.setMotion(x * 0.1D, y * 0.1D + Math.sqrt(Math.sqrt(x * x + y * y + z * z)) * 0.08D, z * 0.1D);
                    this.world.addEntity(itemEntity);
                }
                this.remove();
            }
            return 0;
        } else {
            return super.handleHookRetraction(stack);
        }
    }
}
package com.teammetallurgy.aquaculture.entity;

import com.teammetallurgy.aquaculture.api.fishing.Hook;
import com.teammetallurgy.aquaculture.api.fishing.Hooks;
import com.teammetallurgy.aquaculture.init.AquaEntities;
import com.teammetallurgy.aquaculture.init.AquaItems;
import com.teammetallurgy.aquaculture.misc.AquaConfig;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.item.ExperienceOrbEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.projectile.FishingBobberEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.IPacket;
import net.minecraft.stats.Stats;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ServerWorld;
import net.minecraft.world.World;
import net.minecraft.world.storage.loot.*;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.ItemFishedEvent;
import net.minecraftforge.fml.network.FMLPlayMessages;
import net.minecraftforge.fml.network.NetworkHooks;

import javax.annotation.Nonnull;
import java.util.Collections;
import java.util.List;

public class AquaFishingBobberEntity extends FishingBobberEntity {
    private final Hook hook;
    private final int luck;

    public AquaFishingBobberEntity(FMLPlayMessages.SpawnEntity spawPacket, World world) {
        super(world, Minecraft.getInstance().player, 0, 0, 0);
        this.luck = 0;
        this.hook = null;
    }

    public AquaFishingBobberEntity(PlayerEntity player, World world, int luck, int lureSpeed, Hook hook) {
        super(player, world, luck, lureSpeed);
        this.luck = luck;
        this.angler.fishingBobber = this;
        this.hook = hook;
        if (this.hook == Hooks.HEAVY) {
            this.setMotion(this.getMotion().mul(0.6D, 0.15D, 0.6D));
        }
        if (this.hook == Hooks.LIGHT) {
            this.setMotion(this.getMotion().mul(1.5D, 1.0D, 1.5D));
        }
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
        boolean isAdminRod = AquaConfig.BASIC_OPTIONS.debugMode.get() && stack.getItem() == AquaItems.NEPTUNIUM_FISHING_ROD;
        if (!this.world.isRemote && this.angler != null) {
            int rodDamage = 0;
            ItemFishedEvent event = null;
            if (this.caughtEntity != null && !isAdminRod) {
                this.bringInHookedEntity();
                CriteriaTriggers.FISHING_ROD_HOOKED.trigger((ServerPlayerEntity) this.angler, stack, this, Collections.emptyList());
                this.world.setEntityState(this, (byte) 31);
                rodDamage = this.caughtEntity instanceof ItemEntity ? 3 : 5;
            } else if ((this.ticksCatchable > 0 || isAdminRod) && this.world instanceof ServerWorld) {
                ServerWorld serverWorld = (ServerWorld) this.world;
                LootContext.Builder builder = new LootContext.Builder(serverWorld).withParameter(LootParameters.POSITION, new BlockPos(this)).withParameter(LootParameters.TOOL, stack).withRandom(this.rand).withLuck((float) this.luck + this.angler.getLuck());
                builder.withParameter(LootParameters.KILLER_ENTITY, this.angler).withParameter(LootParameters.THIS_ENTITY, this);
                LootTable lootTable = serverWorld.getServer().getLootTableManager().getLootTableFromLocation(LootTables.GAMEPLAY_FISHING);
                List<ItemStack> lootEntries = lootTable.generate(builder.build(LootParameterSets.FISHING));
                List<ItemStack> goldHookLoot = lootTable.generate(builder.build(LootParameterSets.FISHING));
                event = new ItemFishedEvent(lootEntries, this.inGround ? 2 : 1, this);
                MinecraftForge.EVENT_BUS.post(event);
                if (event.isCanceled()) {
                    this.remove();
                    return event.getRodDamage();
                }
                CriteriaTriggers.FISHING_ROD_HOOKED.trigger((ServerPlayerEntity) this.angler, stack, this, lootEntries);

                spawnLoot(lootEntries);
                if (this.hook == Hooks.DOUBLE) {
                    if (rand.nextDouble() <= 0.10D) {
                        spawnLoot(goldHookLoot);
                        this.playSound(SoundEvents.ENTITY_FISHING_BOBBER_SPLASH, 0.25F, 1.0F + (this.rand.nextFloat() - this.rand.nextFloat()) * 0.4F);
                    }
                }
                rodDamage = 1;
            }
            if (this.inGround) {
                rodDamage = 2;
            }
            this.remove();
            return event == null ? rodDamage : event.getRodDamage();
        } else {
            return 0;
        }
    }

    private void spawnLoot(List<ItemStack> lootEntries) {
        for (ItemStack loot : lootEntries) {
            ItemEntity lootEntity = new ItemEntity(this.world, this.posX, this.posY, this.posZ, loot);
            double x = this.angler.posX - this.posX;
            double y = this.angler.posY - this.posY;
            double z = this.angler.posZ - this.posZ;
            lootEntity.setMotion(x * 0.1D, y * 0.1D + Math.sqrt(Math.sqrt(x * x + y * y + z * z)) * 0.08D, z * 0.1D);
            this.world.addEntity(lootEntity);
            this.angler.world.addEntity(new ExperienceOrbEntity(this.angler.world, this.angler.posX, this.angler.posY + 0.5D, this.angler.posZ + 0.5D, this.rand.nextInt(6) + 1));
            if (loot.getItem().isIn(ItemTags.FISHES)) {
                this.angler.addStat(Stats.FISH_CAUGHT, 1);
            }
        }
    }
}
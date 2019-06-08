package com.teammetallurgy.aquaculture.entity;

import com.teammetallurgy.aquaculture.init.AquaEntities;
import com.teammetallurgy.aquaculture.init.AquaItems;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityFishHook;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.storage.loot.LootContext;
import net.minecraft.world.storage.loot.LootTableList;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.Objects;

public class EntityAquaFishHook extends EntityFishHook {

    public EntityAquaFishHook(World world) { //TODO Remove in 1.14, not needed
        super(world, Minecraft.getInstance().player); //Breaks Aquaculture completely on servers. Only here to allow for development on 1.13, while waiting for 1.14
    }

    public EntityAquaFishHook(World world, EntityPlayer player) {
        super(world, player);
    }

    @Override
    @Nonnull
    public EntityType<?> getType() {
        return AquaEntities.FISH_HOOK;
    }

    @Override
    protected void init(@Nonnull EntityPlayer player) {
        this.setSize(0.25F, 0.25F);
        this.ignoreFrustumCheck = true;
        this.angler = player;
        this.angler.fishEntity = this;
    }

    @Override
    public int handleHookRetraction(@Nonnull ItemStack stack) {
        if (stack.getItem() == AquaItems.ADMIN_FISHING_ROD) {
            if (this.world instanceof WorldServer) {
                LootContext.Builder builder = (new LootContext.Builder((WorldServer) this.world)).withPosition(new BlockPos(this));
                List<ItemStack> list = Objects.requireNonNull(this.world.getServer()).getLootTableManager().getLootTableFromLocation(LootTableList.GAMEPLAY_FISHING).generateLootForPools(this.rand, builder.build());

                for (ItemStack fish : list) {
                    EntityItem entityitem = new EntityItem(this.world, this.posX, this.posY, this.posZ, fish);
                    double x = this.angler.posX - this.posX;
                    double y = this.angler.posY - this.posY;
                    double z = this.angler.posZ - this.posZ;
                    double height = (double) MathHelper.sqrt(x * x + y * y + z * z);
                    entityitem.motionX = x * 0.1D;
                    entityitem.motionY = y * 0.1D + (double) MathHelper.sqrt(height) * 0.08D;
                    entityitem.motionZ = z * 0.1D;
                    this.world.spawnEntity(entityitem);
                    this.angler.world.spawnEntity(new EntityXPOrb(this.angler.world, this.angler.posX, this.angler.posY + 0.5D, this.angler.posZ + 0.5D, this.rand.nextInt(6) + 1));
                    if (fish.getItem().isIn(ItemTags.FISHES)) {
                        this.angler.addStat(StatList.FISH_CAUGHT, 1);
                    }
                }
                this.remove();
            }
            return 0;
        } else {
            return super.handleHookRetraction(stack);
        }
    }
}
package com.teammetallurgy.aquaculture.entity;

import com.teammetallurgy.aquaculture.init.AquaEntities;
import com.teammetallurgy.aquaculture.init.AquaItems;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.item.ExperienceOrbEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.FishingBobberEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Stats;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ServerWorld;
import net.minecraft.world.World;
import net.minecraft.world.storage.loot.*;

import javax.annotation.Nonnull;
import java.util.List;

public class AquaFishingBobberEntity extends FishingBobberEntity {
    private final int luck;

    public AquaFishingBobberEntity(PlayerEntity player, World world, int luck, int lureSpeed) {
        super(player, world, luck, lureSpeed);
        this.luck = luck;
    }

    @Override
    @Nonnull
    public EntityType<?> getType() {
        return AquaEntities.FISH_HOOK;
    }

    @Override
    public int handleHookRetraction(@Nonnull ItemStack stack) {
        if (stack.getItem() == AquaItems.ADMIN_FISHING_ROD) {
            if (this.world instanceof ServerWorld) {
                ServerWorld serverWorld = (ServerWorld) this.world;
                LootContext.Builder builder = new LootContext.Builder(serverWorld).withParameter(LootParameters.field_216286_f, new BlockPos(this)).
                        withParameter(LootParameters.field_216289_i, stack).
                        withRandom(this.rand).withLuck((float) this.luck + this.field_146042_b.getLuck());
                builder.withParameter(LootParameters.field_216284_d, this.field_146042_b).withParameter(LootParameters.field_216281_a, this);
                LootTable loottable = serverWorld.getServer().getLootTableManager().getLootTableFromLocation(LootTables.GAMEPLAY_FISHING);
                List<ItemStack> list = loottable.func_216113_a(builder.build(LootParameterSets.field_216262_c));

                for (ItemStack loot : list) {
                    ItemEntity entityItem = new ItemEntity(this.world, this.posX, this.posY, this.posZ, loot);
                    double x = this.field_146042_b.posX - this.posX;
                    double y = this.field_146042_b.posY - this.posY;
                    double z = this.field_146042_b.posZ - this.posZ;
                    entityItem.setMotion(x * 0.1D, y * 0.1D + Math.sqrt(Math.sqrt(x * x + y * y + z * z)) * 0.08D, z * 0.1D);
                    this.world.func_217376_c(entityItem);
                    this.field_146042_b.world.func_217376_c(new ExperienceOrbEntity(this.field_146042_b.world, this.field_146042_b.posX, this.field_146042_b.posY + 0.5D, this.field_146042_b.posZ + 0.5D, this.rand.nextInt(6) + 1));
                    if (loot.getItem().isIn(ItemTags.FISHES)) {
                        this.field_146042_b.addStat(Stats.FISH_CAUGHT, 1);
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
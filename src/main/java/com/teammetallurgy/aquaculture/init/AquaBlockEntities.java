package com.teammetallurgy.aquaculture.init;

import com.mojang.datafixers.types.Type;
import com.teammetallurgy.aquaculture.Aquaculture;
import com.teammetallurgy.aquaculture.block.blockentity.NeptunesBountyBlockEntity;
import com.teammetallurgy.aquaculture.block.blockentity.TackleBoxBlockEntity;
import net.minecraft.Util;
import net.minecraft.core.registries.Registries;
import net.minecraft.util.datafix.fixes.References;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import javax.annotation.Nonnull;
import java.util.function.Supplier;

public class AquaBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY_DEFERRED = DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, Aquaculture.MOD_ID);
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<NeptunesBountyBlockEntity>> NEPTUNES_BOUNTY = register("neptunes_bounty", () -> BlockEntityType.Builder.of(NeptunesBountyBlockEntity::new, AquaBlocks.NEPTUNES_BOUNTY.get()));
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<TackleBoxBlockEntity>> TACKLE_BOX = register("tackle_box", () -> BlockEntityType.Builder.of(TackleBoxBlockEntity::new, AquaBlocks.TACKLE_BOX.get()));

    public static <T extends BlockEntity> DeferredHolder<BlockEntityType<?>, BlockEntityType<T>> register(@Nonnull String name, @Nonnull Supplier<BlockEntityType.Builder<T>> initializer) {
        Type<?> type = Util.fetchChoiceType(References.BLOCK_ENTITY, Aquaculture.MOD_ID + ":" + name);
        return BLOCK_ENTITY_DEFERRED.register(name, () -> initializer.get().build(type));
    }
}
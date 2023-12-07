package com.teammetallurgy.aquaculture.init;

import com.teammetallurgy.aquaculture.Aquaculture;
import com.teammetallurgy.aquaculture.inventory.container.TackleBoxContainer;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.neoforged.neoforge.common.extensions.IMenuTypeExtension;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import javax.annotation.Nonnull;
import java.util.function.Supplier;

public class AquaGuis {
    public static final DeferredRegister<MenuType<?>> MENU_DEFERRED = DeferredRegister.create(Registries.MENU, Aquaculture.MOD_ID);
    public static final DeferredHolder<MenuType<?>, MenuType<TackleBoxContainer>> TACKLE_BOX = register(() -> IMenuTypeExtension.create((windowID, inv, data) -> {
        BlockPos pos = data.readBlockPos();
        return new TackleBoxContainer(windowID, pos, inv);
    }), "tackle_box");

    private static <T extends AbstractContainerMenu> DeferredHolder<MenuType<?>, MenuType<T>> register(@Nonnull Supplier<MenuType<T>> initializer, @Nonnull String name) {
        return MENU_DEFERRED.register(name, initializer);
    }
}
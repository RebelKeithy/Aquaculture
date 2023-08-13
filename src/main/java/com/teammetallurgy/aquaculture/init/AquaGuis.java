package com.teammetallurgy.aquaculture.init;

import com.teammetallurgy.aquaculture.Aquaculture;
import com.teammetallurgy.aquaculture.inventory.container.TackleBoxContainer;
import net.minecraft.core.BlockPos;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import javax.annotation.Nonnull;
import java.util.function.Supplier;

public class AquaGuis {
    public static final DeferredRegister<MenuType<?>> MENU_DEFERRED = DeferredRegister.create(ForgeRegistries.MENU_TYPES, Aquaculture.MOD_ID);
    public static final RegistryObject<MenuType<TackleBoxContainer>> TACKLE_BOX = register(() -> IForgeMenuType.create((windowID, inv, data) -> {
        BlockPos pos = data.readBlockPos();
        return new TackleBoxContainer(windowID, pos, inv);
    }), "tackle_box");

    private static <T extends AbstractContainerMenu> RegistryObject<MenuType<T>> register(@Nonnull Supplier<MenuType<T>> initializer, @Nonnull String name) {
        return MENU_DEFERRED.register(name, initializer);
    }
}
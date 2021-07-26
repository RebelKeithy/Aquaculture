package com.teammetallurgy.aquaculture.init;

import com.google.common.collect.Lists;
import com.teammetallurgy.aquaculture.Aquaculture;
import com.teammetallurgy.aquaculture.inventory.container.TackleBoxContainer;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ObjectHolder;

import javax.annotation.Nonnull;
import java.util.List;

@Mod.EventBusSubscriber(modid = Aquaculture.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
@ObjectHolder(Aquaculture.MOD_ID)
public class AquaGuis {
    public static List<MenuType> CONTAINERS = Lists.newArrayList();
    public static final MenuType<TackleBoxContainer> TACKLE_BOX = register(IForgeContainerType.create((windowID, inv, data) -> {
        BlockPos pos = data.readBlockPos();
        return new TackleBoxContainer(windowID, pos, inv);
    }), "tackle_box");

    private static <T extends AbstractContainerMenu> MenuType<T> register(@Nonnull MenuType<T> container, @Nonnull String name) {
        container.setRegistryName(new ResourceLocation(Aquaculture.MOD_ID, name));
        CONTAINERS.add(container);
        return container;
    }

    @SubscribeEvent
    public static void registerContainers(RegistryEvent.Register<MenuType<?>> event) {
        for (MenuType container : CONTAINERS) {
            event.getRegistry().register(container);
        }
    }
}
package com.teammetallurgy.aquaculture.init;

import com.teammetallurgy.aquaculture.Aquaculture;
import com.teammetallurgy.aquaculture.entity.EntityAquaFishHook;
import net.minecraft.entity.EntityType;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ObjectHolder;

@Mod.EventBusSubscriber(modid = Aquaculture.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
@ObjectHolder(Aquaculture.MOD_ID)
public class AquaEntities {
    public static final EntityType FISH_HOOK = EntityType.register(Aquaculture.MOD_ID + ":" + "hook", EntityType.Builder.create(EntityAquaFishHook.class, EntityAquaFishHook::new).tracker(64, 5, true));
}
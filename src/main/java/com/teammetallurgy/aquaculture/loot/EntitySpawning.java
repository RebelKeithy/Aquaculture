package com.teammetallurgy.aquaculture.loot;

import com.teammetallurgy.aquaculture.Aquaculture;
import com.teammetallurgy.aquaculture.init.AquaEntities;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Aquaculture.MOD_ID)
public class EntitySpawning {

    @SubscribeEvent(priority = EventPriority.HIGH)
    public static void biomeLoading(BiomeLoadingEvent event) {
        AquaEntities.addEntitySpawns(event);
        FishReadFromJson.addFishSpawns(event);
    }
}
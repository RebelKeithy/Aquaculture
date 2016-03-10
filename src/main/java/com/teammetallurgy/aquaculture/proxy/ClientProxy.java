package com.teammetallurgy.aquaculture.proxy;

import com.teammetallurgy.aquaculture.Aquaculture;
import com.teammetallurgy.aquaculture.items.AquacultureItems;

import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraftforge.client.model.ModelLoader;

public class ClientProxy extends CommonProxy {
    @Override
    public void registerParticles() {
    }

    @Override
    public void registerModelRenderers() {
        // TODO: Entity Rendering
        // RenderingRegistry.registerEntityRenderingHandler(EntityCustomFishHook.class, new RenderFish());
    }

    @Override
    public void registerItemModels() {
        ModelLoader.setCustomModelResourceLocation(AquacultureItems.adminFishingRod, 0, new ModelResourceLocation(Aquaculture.MOD_ID + ":admin_fishing_rod", "inventory"));
        ModelLoader.setCustomModelResourceLocation(AquacultureItems.adminFishingRod, 1, new ModelResourceLocation(Aquaculture.MOD_ID + ":admin_fishing_rod_cast", "inventory"));
        ModelLoader.setCustomModelResourceLocation(AquacultureItems.ironFishingRod, 0, new ModelResourceLocation(Aquaculture.MOD_ID + ":iron_fishing_rod", "inventory"));
        ModelLoader.setCustomModelResourceLocation(AquacultureItems.ironFishingRod, 1, new ModelResourceLocation(Aquaculture.MOD_ID + ":iron_fishing_rod_cast", "inventory"));
        ModelLoader.setCustomModelResourceLocation(AquacultureItems.goldFishingRod, 0, new ModelResourceLocation(Aquaculture.MOD_ID + ":gold_fishing_rod", "inventory"));
        ModelLoader.setCustomModelResourceLocation(AquacultureItems.goldFishingRod, 1, new ModelResourceLocation(Aquaculture.MOD_ID + ":gold_fishing_rod_cast", "inventory"));
        ModelLoader.setCustomModelResourceLocation(AquacultureItems.diamondFishingRod, 0, new ModelResourceLocation(Aquaculture.MOD_ID + ":diamond_fishing_rod", "inventory"));
        ModelLoader.setCustomModelResourceLocation(AquacultureItems.diamondFishingRod, 1, new ModelResourceLocation(Aquaculture.MOD_ID + ":diamond_fishing_rod_cast", "inventory"));
    }
}

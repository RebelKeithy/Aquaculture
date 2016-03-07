package com.teammetallurgy.aquaculture.proxy;

import com.teammetallurgy.aquaculture.handlers.EntityCustomFishHook;

import cpw.mods.fml.client.registry.RenderingRegistry;

import net.minecraft.client.renderer.entity.RenderFish;

public class ClientProxy extends CommonProxy {
    public void registerParticles() {
    }

    public void registerModelRenderers() {
        RenderingRegistry.registerEntityRenderingHandler(EntityCustomFishHook.class, new RenderFish());
    }
}

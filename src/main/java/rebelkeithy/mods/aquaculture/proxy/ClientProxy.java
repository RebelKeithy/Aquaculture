package rebelkeithy.mods.aquaculture.proxy;

import cpw.mods.fml.client.registry.RenderingRegistry;
import net.minecraft.client.renderer.entity.RenderFish;
import rebelkeithy.mods.aquaculture.handlers.EntityCustomFishHook;

public class ClientProxy extends CommonProxy {
    public void registerParticles() {
    }

    public void registerModelRenderers() {
        RenderingRegistry.registerEntityRenderingHandler(EntityCustomFishHook.class, new RenderFish());
    }
}

package rebelkeithy.mods.aquaculture;

import net.minecraft.client.renderer.entity.RenderFish;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class ClientProxy extends CommonProxy {
	public void registerParticles() {
	}

	public void registerModelRenderers() {
		RenderingRegistry.registerEntityRenderingHandler(EntityCustomFishHook.class, new RenderFish());
	}
}

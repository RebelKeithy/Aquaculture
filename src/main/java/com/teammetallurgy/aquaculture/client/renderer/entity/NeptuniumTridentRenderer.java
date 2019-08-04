package com.teammetallurgy.aquaculture.client.renderer.entity;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.TridentRenderer;
import net.minecraft.entity.projectile.TridentEntity;
import net.minecraft.util.ResourceLocation;

public class NeptuniumTridentRenderer extends TridentRenderer {

    public NeptuniumTridentRenderer(EntityRendererManager manager) {
        super(manager);
    }

    @Override
    protected ResourceLocation getEntityTexture(TridentEntity tridentEntity) {
        return TRIDENT;
    }
}

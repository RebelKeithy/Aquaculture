package com.teammetallurgy.aquaculture.client.renderer.tileentity.model;

import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.client.renderer.model.Model;

public class TackleBoxModel extends Model {
    private RendererModel base;
    private RendererModel lid;
    private RendererModel handle;

    public TackleBoxModel() {
        this.textureWidth = 64;
        this.textureHeight = 32;
        this.lid = new RendererModel(this, 0, 0);
        this.lid.setRotationPoint(7.0F, 12.0F, 4.0F);
        this.lid.addBox(-7.0F, -3.0F, -8.0F, 14, 3, 8, 0.0F);
        this.base = new RendererModel(this, 0, 11);
        this.base.setRotationPoint(0.0F, 18.0F, 4.0F);
        this.base.addBox(0.0F, -6.0F, -8.0F, 14, 6, 8, 0.0F);
        this.handle = new RendererModel(this, 36, 0);
        this.handle.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.handle.addBox(-2.0F, -4.0F, -5.0F, 4, 1, 2, 0.0F);
        this.lid.addChild(this.handle);
    }

    public void render(float scale) {
        this.lid.render(scale);
        this.base.render(scale);
    }

    public RendererModel getLid() {
        return this.lid;
    }
}
package com.teammetallurgy.aquaculture.client.renderer.entity.model;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class FishSmallModel<T extends Entity> extends EntityModel<T> { //Based on TropicalFishA
    private final RendererModel finRight;
    private final RendererModel finTop;
    private final RendererModel tail;
    private final RendererModel finLeft;
    private final RendererModel body;
    private final RendererModel finBottom;

    public FishSmallModel() {
        this.textureWidth = 32;
        this.textureHeight = 32;
        this.body = new RendererModel(this, 0, 0);
        this.body.setRotationPoint(0.0F, 22.0F, 0.0F);
        this.body.addBox(-1.0F, -1.5F, -3.0F, 2, 3, 6, 0.0F);
        this.finTop = new RendererModel(this, 10, -5);
        this.finTop.setRotationPoint(0.0F, 20.5F, -3.0F);
        this.finTop.addBox(0.0F, -3.0F, 0.0F, 0, 3, 6, 0.0F);
        this.tail = new RendererModel(this, 22, -6);
        this.tail.setRotationPoint(0.0F, 22.0F, 3.0F);
        this.tail.addBox(0.0F, -1.5F, 0.0F, 0, 3, 6, 0.0F);
        this.finLeft = new RendererModel(this, 2, 12);
        this.finLeft.setRotationPoint(1.0F, 22.5F, 0.0F);
        this.finLeft.addBox(0.0F, -1.0F, 0.0F, 2, 2, 0, 0.0F);
        this.finLeft.rotateAngleZ = 0.7853981852531433F;
        this.finRight = new RendererModel(this, 2, 16);
        this.finRight.setRotationPoint(-1.0F, 22.5F, 0.0F);
        this.finRight.addBox(-2.0F, -1.0F, 0.0F, 2, 2, 0, 0.0F);
        this.finRight.rotateAngleZ = -0.7853981852531433F;
        this.finBottom = new RendererModel(this, 10, 5);
        this.finBottom.setRotationPoint(0.0F, 23.5F, -3.0F);
        this.finBottom.addBox(0.0F, 0.0F, 0.0F, 0, 3, 6, 0.0F);
    }

    @Override
    public void render(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
        this.setRotationAngles(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
        this.body.render(scale);
        this.finTop.render(scale);
        this.tail.render(scale);
        this.finLeft.render(scale);
        this.finRight.render(scale);
        this.finBottom.render(scale);
    }

    @Override
    public void setRotationAngles(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor) {
        float movement = 1.0F;
        if (!entity.isInWater()) {
            movement = 1.5F;
        }
        this.tail.rotateAngleY = -movement * 0.45F * MathHelper.sin(0.6F * ageInTicks);
    }
}
package com.teammetallurgy.aquaculture.client.renderer.entity.model;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class FishMediumModel<T extends Entity> extends EntityModel<T> { //Based on Cod
    private final RendererModel body;
    private final RendererModel finTop;
    private final RendererModel head;
    private final RendererModel headFront;
    private final RendererModel finRight;
    private final RendererModel finLeft;
    private final RendererModel tail;
    private final RendererModel bottomFin;

    public FishMediumModel() {
        this.textureWidth = 32;
        this.textureHeight = 32;
        this.body = new RendererModel(this, 0, 0);
        this.body.setRotationPoint(0.0F, 22.0F, 0.0F);
        this.body.addBox(-1.0F, -2.0F, 0.0F, 2, 4, 7, 0.0F);
        this.head = new RendererModel(this, 11, 0);
        this.head.setRotationPoint(0.0F, 22.0F, 0.0F);
        this.head.addBox(-1.0F, -2.0F, -3.0F, 2, 4, 3, 0.0F);
        this.headFront = new RendererModel(this, 0, 0);
        this.headFront.setRotationPoint(0.0F, 22.0F, -3.0F);
        this.headFront.addBox(-1.0F, -2.0F, -1.0F, 2, 3, 1, 0.0F);
        this.finRight = new RendererModel(this, 26, 4);
        this.finRight.setRotationPoint(-1.0F, 23.0F, 0.0F);
        this.finRight.addBox(-2.0F, 0.0F, -1.0F, 2, 0, 2, 0.0F);
        this.finRight.rotateAngleZ = -0.7853981633974483F;
        this.finLeft = new RendererModel(this, 22, 4);
        this.finLeft.setRotationPoint(1.0F, 23.0F, 0.0F);
        this.finLeft.addBox(0.0F, 0.0F, -1.0F, 2, 0, 2, 0.0F);
        this.finLeft.rotateAngleZ = 0.7853981633974483F;
        this.tail = new RendererModel(this, 22, 3);
        this.tail.setRotationPoint(0.0F, 22.0F, 7.0F);
        this.tail.addBox(0.0F, -2.0F, 0.0F, 0, 4, 4, 0.0F);
        this.finTop = new RendererModel(this, 20, -6);
        this.finTop.setRotationPoint(0.0F, 19.0F, 0.0F);
        this.finTop.addBox(0.0F, -1.0F, -1.0F, 0, 2, 6, 0.0F);
        this.bottomFin = new RendererModel(this, 20, 5);
        this.bottomFin.setRotationPoint(0.0F, 25.0F, 0.0F);
        this.bottomFin.addBox(0.0F, -1.0F, -1.0F, 0, 2, 6, 0.0F);
    }

    @Override
    public void render(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
        this.setRotationAngles(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
        this.body.render(scale);
        this.head.render(scale);
        this.headFront.render(scale);
        this.finRight.render(scale);
        this.finLeft.render(scale);
        this.tail.render(scale);
        this.finTop.render(scale);
        this.bottomFin.render(scale);
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
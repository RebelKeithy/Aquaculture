package com.teammetallurgy.aquaculture.client.renderer.entity.model;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class FishCatchfishModel<T extends Entity> extends EntityModel<T> { //Based on Cod
    private final RendererModel head;
    private final RendererModel top_fin;
    private final RendererModel tail;
    private final RendererModel leftFin;
    private final RendererModel body;
    private final RendererModel rightFin;
    private final RendererModel headFront;
    private final RendererModel bottomFin;
    private final RendererModel leftWhisker;
    private final RendererModel rightWhisker;

    public FishCatchfishModel() {
        this.textureWidth = 32;
        this.textureHeight = 32;
        this.tail = new RendererModel(this, 22, 3);
        this.tail.setRotationPoint(0.0F, 22.0F, 7.0F);
        this.tail.addBox(0.0F, -2.0F, 0.0F, 0, 4, 4, 0.0F);
        this.headFront = new RendererModel(this, 0, 0);
        this.headFront.setRotationPoint(0.0F, 22.5F, -3.0F);
        this.headFront.addBox(-1.0F, -2.0F, -1.0F, 2, 3, 1, 0.0F);
        this.rightFin = new RendererModel(this, 26, 4);
        this.rightFin.setRotationPoint(-1.0F, 22.0F, 1.0F);
        this.rightFin.addBox(-2.0F, 0.0F, -1.0F, 2, 0, 2, 0.0F);
        this.setRotateAngle(rightFin, -1.5707963267948966F, 0.0F, -0.7853981633974483F);
        this.leftFin = new RendererModel(this, 22, 4);
        this.leftFin.setRotationPoint(1.0F, 22.0F, 1.5F);
        this.leftFin.addBox(0.0F, 0.0F, -1.0F, 2, 0, 2, 0.0F);
        this.setRotateAngle(leftFin, -1.5707963267948966F, 0.0F, 0.7853981633974483F);
        this.top_fin = new RendererModel(this, 20, 7);
        this.top_fin.setRotationPoint(0.0F, 19.0F, 0.0F);
        this.top_fin.addBox(0.0F, -1.0F, 0.0F, 0, 2, 6, 0.0F);
        this.bottomFin = new RendererModel(this, 20, 5);
        this.bottomFin.setRotationPoint(0.0F, 25.0F, 0.0F);
        this.bottomFin.addBox(0.0F, -1.0F, 0.0F, 0, 2, 6, 0.0F);
        this.head = new RendererModel(this, 11, 0);
        this.head.setRotationPoint(-1.0F, 22.5F, 0.0F);
        this.head.addBox(-1.0F, -2.0F, -3.0F, 4, 3, 3, 0.0F);
        this.rightWhisker = new RendererModel(this, 28, 17);
        this.rightWhisker.setRotationPoint(-1.0F, 0.0F, -2.0F);
        this.rightWhisker.addBox(-2.0F, -1.0F, -0.5F, 2, 2, 0, 0.0F);
        this.body = new RendererModel(this, 0, 0);
        this.body.setRotationPoint(0.0F, 22.0F, 0.0F);
        this.body.addBox(-1.0F, -2.0F, 0.0F, 2, 4, 7, 0.0F);
        this.leftWhisker = new RendererModel(this, 28, 15);
        this.leftWhisker.setRotationPoint(3.0F, 0.0F, -2.0F);
        this.leftWhisker.addBox(0.0F, -1.0F, -0.5F, 2, 2, 0, 0.0F);
        this.head.addChild(this.rightWhisker);
        this.head.addChild(this.leftWhisker);
    }

    @Override
    public void render(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
        this.tail.render(scale);
        this.headFront.render(scale);
        this.rightFin.render(scale);
        this.leftFin.render(scale);
        this.top_fin.render(scale);
        this.bottomFin.render(scale);
        this.head.render(scale);
        this.body.render(scale);
    }

    @Override
    public void setRotationAngles(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor) {
        float movement = 1.0F;
        if (!entity.isInWater()) {
            movement = 1.5F;
        }
        this.tail.rotateAngleY = -movement * 0.45F * MathHelper.sin(0.6F * ageInTicks);
    }

    public void setRotateAngle(RendererModel RendererModel, float x, float y, float z) {
        RendererModel.rotateAngleX = x;
        RendererModel.rotateAngleY = y;
        RendererModel.rotateAngleZ = z;
    }
}
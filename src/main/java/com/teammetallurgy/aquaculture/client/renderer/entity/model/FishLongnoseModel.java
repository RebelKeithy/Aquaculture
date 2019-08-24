package com.teammetallurgy.aquaculture.client.renderer.entity.model;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class FishLongnoseModel <T extends Entity> extends EntityModel<T> { //Based on Salmon
    private final RendererModel nose;
    private final RendererModel finRight;
    private final RendererModel finLeft;
    private final RendererModel bodyFront;
    private final RendererModel bodyRear;
    private final RendererModel bodyFrontFinTop;
    private final RendererModel bodyFrontFinBottom;
    private final RendererModel bodyRearTail;
    private final RendererModel bodyRearFinBottom;
    private final RendererModel bodyRearFinTop;

    public FishLongnoseModel() {
        this.textureWidth = 32;
        this.textureHeight = 32;
        this.bodyRear = new RendererModel(this, 0, 13);
        this.bodyRear.setRotationPoint(0.0F, 20.0F, 8.0F);
        this.bodyRear.addBox(-1.5F, -2.5F, 0.0F, 3, 4, 8, 0.0F);
        this.finRight = new RendererModel(this, -4, 0);
        this.finRight.setRotationPoint(-1.5F, 21.5F, 4.0F);
        this.finRight.addBox(-2.0F, 0.0F, 0.0F, 2, 0, 2, 0.0F);
        this.finRight.rotateAngleZ = -0.7853981633974483F;
        this.nose = new RendererModel(this, 22, 0);
        this.nose.setRotationPoint(0.0F, 20.5F, 0.0F);
        this.nose.addBox(-1.0F, -2.0F, -3.0F, 2, 2, 3, 0.0F);
        this.bodyFrontFinTop = new RendererModel(this, 2, 1);
        this.bodyFrontFinTop.setRotationPoint(0.0F, -4.5F, 5.0F);
        this.bodyFrontFinTop.addBox(0.0F, 0.0F, 0.0F, 0, 2, 3, 0.0F);
        this.bodyRearFinTop = new RendererModel(this, 0, 2);
        this.bodyRearFinTop.setRotationPoint(0.0F, -4.5F, -1.0F);
        this.bodyRearFinTop.addBox(0.0F, 0.0F, 1.0F, 0, 2, 4, 0.0F);
        this.finLeft = new RendererModel(this, 0, 0);
        this.finLeft.setRotationPoint(1.5F, 21.5F, 4.0F);
        this.finLeft.addBox(0.0F, 0.0F, 0.0F, 2, 0, 2, 0.0F);
        this.finLeft.rotateAngleZ = 0.7853981633974483F;
        this.bodyFrontFinBottom = new RendererModel(this, 0, 25);
        this.bodyFrontFinBottom.setRotationPoint(0.0F, 1.5F, 5.0F);
        this.bodyFrontFinBottom.addBox(0.0F, 0.0F, 0.0F, 0, 2, 3, 0.0F);
        this.bodyRearTail = new RendererModel(this, 20, 10);
        this.bodyRearTail.setRotationPoint(0.0F, 0.0F, 8.0F);
        this.bodyRearTail.addBox(0.0F, -2.5F, 0.0F, 0, 4, 6, 0.0F);
        this.bodyFront = new RendererModel(this, 0, 0);
        this.bodyFront.setRotationPoint(0.0F, 20.0F, 0.0F);
        this.bodyFront.addBox(-1.5F, -2.5F, 0.0F, 3, 4, 8, 0.0F);
        this.bodyRearFinBottom = new RendererModel(this, 0, 22);
        this.bodyRearFinBottom.setRotationPoint(0.0F, 1.5F, -1.0F);
        this.bodyRearFinBottom.addBox(0.0F, 0.0F, 1.0F, 0, 2, 4, 0.0F);
        this.bodyFront.addChild(this.bodyFrontFinTop);
        this.bodyRear.addChild(this.bodyRearFinTop);
        this.bodyFront.addChild(this.bodyFrontFinBottom);
        this.bodyRear.addChild(this.bodyRearTail);
        this.bodyRear.addChild(this.bodyRearFinBottom);
    }

    @Override
    public void render(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
        this.bodyRear.render(scale);
        this.finRight.render(scale);
        this.nose.render(scale);
        this.finLeft.render(scale);
        this.bodyFront.render(scale);
    }

    @Override
    public void setRotationAngles(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor) {
        float f = 1.0F;
        float f1 = 1.0F;
        if (!entity.isInWater()) {
            f = 1.3F;
            f1 = 1.7F;
        }
        this.bodyRear.rotateAngleY = -f * 0.25F * MathHelper.sin(f1 * 0.6F * f);
    }
}
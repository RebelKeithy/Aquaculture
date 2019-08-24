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
    private final RendererModel finTopFront;
    private final RendererModel finFrontBottom;
    private final RendererModel finRearBottom;
    private final RendererModel finTopRear;
    private final RendererModel tail;

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
        this.finTopFront = new RendererModel(this, 2, 1);
        this.finTopFront.setRotationPoint(0.0F, -4.5F, 5.0F);
        this.finTopFront.addBox(0.0F, 0.0F, 0.0F, 0, 2, 3, 0.0F);
        this.finTopRear = new RendererModel(this, 0, 2);
        this.finTopRear.setRotationPoint(0.0F, -4.5F, -1.0F);
        this.finTopRear.addBox(0.0F, 0.0F, 1.0F, 0, 2, 4, 0.0F);
        this.finLeft = new RendererModel(this, 0, 0);
        this.finLeft.setRotationPoint(1.5F, 21.5F, 4.0F);
        this.finLeft.addBox(0.0F, 0.0F, 0.0F, 2, 0, 2, 0.0F);
        this.finLeft.rotateAngleZ = 0.7853981633974483F;
        this.finFrontBottom = new RendererModel(this, 0, 25);
        this.finFrontBottom.setRotationPoint(0.0F, 1.5F, 5.0F);
        this.finFrontBottom.addBox(0.0F, 0.0F, 0.0F, 0, 2, 3, 0.0F);
        this.tail = new RendererModel(this, 20, 10);
        this.tail.setRotationPoint(0.0F, 0.0F, 8.0F);
        this.tail.addBox(0.0F, -2.5F, 0.0F, 0, 4, 6, 0.0F);
        this.bodyFront = new RendererModel(this, 0, 0);
        this.bodyFront.setRotationPoint(0.0F, 20.0F, 0.0F);
        this.bodyFront.addBox(-1.5F, -2.5F, 0.0F, 3, 4, 8, 0.0F);
        this.finRearBottom = new RendererModel(this, 0, 22);
        this.finRearBottom.setRotationPoint(0.0F, 1.5F, -1.0F);
        this.finRearBottom.addBox(0.0F, 0.0F, 1.0F, 0, 2, 4, 0.0F);
        this.bodyFront.addChild(this.finTopFront);
        this.bodyFront.addChild(this.finFrontBottom);
        this.bodyRear.addChild(this.finTopRear);
        this.bodyRear.addChild(this.tail);
        this.bodyRear.addChild(this.finRearBottom);
    }

    @Override
    public void render(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
        this.nose.render(scale);
        this.bodyFront.render(scale);
        this.bodyRear.render(scale);
        this.finRight.render(scale);
        this.finLeft.render(scale);
    }

    @Override
    public void setRotationAngles(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor) {
        float f = 1.0F;
        float f1 = 1.0F;
        if (!entity.isInWater()) {
            f = 1.3F;
            f1 = 1.7F;
        }
        float moveAmount = 0.15F; //Default for Salmon is 0.25F
        this.bodyRear.rotateAngleY = -f * moveAmount * MathHelper.sin(f1 * 0.6F * ageInTicks);
    }
}
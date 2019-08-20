package com.teammetallurgy.aquaculture.client.renderer.entity.model;

import com.mojang.blaze3d.platform.GlStateManager;
import com.teammetallurgy.aquaculture.entity.TurtleLandEntity;
import net.minecraft.client.renderer.entity.model.QuadrupedModel;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.util.math.MathHelper;

import javax.annotation.Nonnull;

public class TurtleLandModel<T extends TurtleLandEntity> extends QuadrupedModel<T> {
    private final RendererModel tail;
    private final RendererModel shell2;
    private final RendererModel shell2_1;

    public TurtleLandModel(float scale) {
        super(11, scale);
        this.textureWidth = 64;
        this.textureHeight = 32;
        this.headModel = new RendererModel(this, 24, 0);
        this.headModel.setRotationPoint(0.0F, 0.2F, -4.0F);
        this.headModel.addBox(-1.0F, -1.0F, -0.5F, 2, 2, 2, -0.2F);
        this.field_78148_b = new RendererModel(this, 0, 0); //Body
        this.field_78148_b.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.field_78148_b.addBox(-2.5F, -1.0F, -3.0F, 5, 2, 6, 0.0F);
        this.field_78146_d = new RendererModel(this, 16, 0);
        this.field_78146_d.setRotationPoint(2.5F, 1.0F, -3.0F);
        this.field_78146_d.addBox(-0.5F, -1.0F, -0.5F, 1, 2, 1, 0.1F);
        this.setRotateAngle(field_78146_d, -0.5235987755982988F, -0.5235987755982988F, 0.0F);
        this.field_78149_c = new RendererModel(this, 16, 3);
        this.field_78149_c.setRotationPoint(-2.5F, 1.0F, 3.0F);
        this.field_78149_c.addBox(-0.5F, -1.0F, -0.5F, 1, 2, 1, 0.1F);
        this.setRotateAngle(field_78149_c, 0.5235987755982988F, 5.759586531581287F, 0.0F);
        this.field_78147_e = new RendererModel(this, 20, 3);
        this.field_78147_e.setRotationPoint(-2.5F, 1.0F, -3.0F);
        this.field_78147_e.addBox(-0.5F, -1.0F, -0.5F, 1, 2, 1, 0.1F);
        this.setRotateAngle(field_78147_e, -0.5235987755982988F, 0.5235987755982988F, 0.0F);
        this.field_78144_f = new RendererModel(this, 20, 0);
        this.field_78144_f.setRotationPoint(2.5F, 1.0F, 3.0F);
        this.field_78144_f.addBox(-0.5F, -1.0F, -0.5F, 1, 2, 1, 0.1F);
        this.setRotateAngle(field_78144_f, 0.5235987755982988F, 0.5235987755982988F, 0.0F);
        this.shell2 = new RendererModel(this, 0, 8);
        this.shell2.setRotationPoint(0.0F, -1.5F, 0.0F);
        this.shell2.addBox(-2.0F, -0.5F, -2.5F, 4, 1, 5, 0.0F);
        this.shell2_1 = new RendererModel(this, 0, 14);
        this.shell2_1.setRotationPoint(0.0F, 1.0F, 0.0F);
        this.shell2_1.addBox(-2.0F, -0.5F, -2.5F, 4, 1, 5, 0.0F);
        this.tail = new RendererModel(this, 26, 0);
        this.tail.setRotationPoint(0.0F, 0.5F, 3.2F);
        this.tail.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1, -0.2F);
        this.field_78148_b.addChild(this.shell2_1);
        this.field_78148_b.addChild(this.shell2);
    }

    @Override
    public void render(@Nonnull T turtle, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
        this.setRotationAngles(turtle, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
        if (this.isChild) {
            GlStateManager.pushMatrix();
            GlStateManager.scalef(0.75F, 0.75F, 0.75F);
            GlStateManager.translatef(0.0F, 16.0F * scale, 0.065F);
            this.headModel.render(scale);
            GlStateManager.popMatrix();
            GlStateManager.pushMatrix();
            GlStateManager.scalef(0.5F, 0.5F, 0.5F);
            GlStateManager.translatef(0.0F, 24.0F * scale, 0.0F);
            this.field_78148_b.render(scale); // Body
            this.field_78149_c.render(scale);
            this.field_78146_d.render(scale);
            this.field_78147_e.render(scale);
            this.field_78144_f.render(scale);
            this.tail.render(scale);
            GlStateManager.popMatrix();
        } else {
            this.headModel.render(scale);
            this.field_78148_b.render(scale); // Body
            this.field_78149_c.render(scale);
            this.field_78146_d.render(scale);
            this.field_78147_e.render(scale);
            this.field_78144_f.render(scale);
            this.tail.render(scale);
        }
    }

    @Override
    public void setRotationAngles(T turtle, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor) {
        this.headModel.rotateAngleX = headPitch * 0.017453292F;
        this.headModel.rotateAngleY = netHeadYaw * 0.017453292F;
        this.field_78149_c.rotateAngleX = 0.5235987755982988F + (MathHelper.cos(limbSwing * 0.6662F * 0.6F) * 0.5F * limbSwingAmount);
        this.field_78146_d.rotateAngleX = -0.5235987755982988F + (MathHelper.cos(limbSwing * 0.6662F * 0.6F + 3.1415927F) * 0.5F * limbSwingAmount);
        this.field_78147_e.rotateAngleX = -0.5235987755982988F + (MathHelper.cos(limbSwing * 0.6662F * 0.6F + 3.1415927F) * 0.5F * limbSwingAmount);
        this.field_78144_f.rotateAngleX = 0.5235987755982988F + (MathHelper.cos(limbSwing * 0.6662F * 0.6F) * 0.5F * limbSwingAmount);
        this.tail.rotateAngleY = MathHelper.cos(limbSwing * 0.6662F) * limbSwingAmount;
    }

    private void setRotateAngle(RendererModel RendererModel, float x, float y, float z) {
        RendererModel.rotateAngleX = x;
        RendererModel.rotateAngleY = y;
        RendererModel.rotateAngleZ = z;
    }
}
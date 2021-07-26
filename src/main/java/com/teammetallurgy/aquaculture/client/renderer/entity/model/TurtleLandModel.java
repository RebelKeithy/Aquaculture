package com.teammetallurgy.aquaculture.client.renderer.entity.model;

import com.google.common.collect.ImmutableList;
import com.teammetallurgy.aquaculture.entity.TurtleLandEntity;
import net.minecraft.client.model.QuadrupedModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.util.Mth;

import javax.annotation.Nonnull;

public class TurtleLandModel<T extends TurtleLandEntity> extends QuadrupedModel<T> {
    private final ModelPart tail;
    private final ModelPart shellTop;
    private final ModelPart belly;

    public TurtleLandModel(ModelPart part) {
        super(part, false, 1.1F, 1.5F, 2.0F, 2.0F, 24);
        this.tail = part.getChild("tail");
        this.shellTop = part.getChild("shell_top");
        this.belly = part.getChild("belly");

        /*
        this.head = new ModelPart(this, 24, 0);
        this.head.setPos(0.0F, 22.2F, -4.0F);
        this.head.addBox(-1.0F, -1.0F, -0.5F, 2, 2, 2, -0.2F);
        this.body = new ModelPart(this, 0, 0);
        this.body.setPos(0.0F, 22.0F, 0.0F);
        this.body.addBox(-2.5F, -1.0F, -3.0F, 5, 2, 6, 0.0F);
        this.belly = new ModelPart(this, 0, 14);
        this.belly.setPos(0.0F, 1.0F, 0.0F);
        this.belly.addBox(-2.0F, -0.5F, -2.5F, 4, 1, 5, 0.0F);
        this.shellTop = new ModelPart(this, 0, 8);
        this.shellTop.setPos(0.0F, -1.5F, 0.0F);
        this.shellTop.addBox(-2.0F, -0.5F, -2.5F, 4, 1, 5, 0.0F);
        this.leg0 = new ModelPart(this, 16, 3);
        this.leg0.setPos(-2.5F, 23.0F, 3.0F);
        this.leg0.addBox(-0.5F, -1.0F, -0.5F, 1, 2, 1, 0.1F);
        this.setRotateAngle(leg0, 0.5235987755982988F, 5.759586531581287F, 0.0F);
        this.leg1 = new ModelPart(this, 16, 0);
        this.leg1.setPos(2.5F, 23.0F, -3.0F);
        this.leg1.addBox(-0.5F, -1.0F, -0.5F, 1, 2, 1, 0.1F);
        this.setRotateAngle(leg1, -0.5235987755982988F, -0.5235987755982988F, 0.0F);
        this.leg2 = new ModelPart(this, 20, 3);
        this.leg2.setPos(-2.5F, 23.0F, -3.0F);
        this.leg2.addBox(-0.5F, -1.0F, -0.5F, 1, 2, 1, 0.1F);
        this.setRotateAngle(leg2, -0.5235987755982988F, 0.5235987755982988F, 0.0F);
        this.leg3 = new ModelPart(this, 20, 0);
        this.leg3.setPos(2.5F, 23.0F, 3.0F);
        this.leg3.addBox(-0.5F, -1.0F, -0.5F, 1, 2, 1, 0.1F);
        this.setRotateAngle(leg3, 0.5235987755982988F, 0.5235987755982988F, 0.0F);
        this.tail = new ModelPart(this, 26, 0);
        this.tail.setPos(0.0F, 22.5F, 3.2F);
        this.tail.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1, -0.2F);
        this.body.addChild(this.belly);
        this.body.addChild(this.shellTop);*/
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition modelDefinition = createBodyMesh(0, CubeDeformation.NONE);
        PartDefinition def = modelDefinition.getRoot();

        return LayerDefinition.create(modelDefinition, 64, 32);
    }

    @Override
    @Nonnull
    protected Iterable<ModelPart> bodyParts() {
        return ImmutableList.of(this.body, this.rightHindLeg, this.leftHindLeg, this.rightFrontLeg, this.leftFrontLeg, this.tail, this.shellTop, this.belly);
    }

    @Override
    public void setupAnim(@Nonnull T turtle, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.head.xRot = headPitch * 0.017453292F;
        this.head.yRot = netHeadYaw * 0.017453292F;
        this.rightHindLeg.xRot = 0.5235987755982988F + (Mth.cos(limbSwing * 5.0F) * 1.4F * limbSwingAmount);
        this.leftHindLeg.xRot = -0.5235987755982988F + -(Mth.cos(limbSwing * 5.0F) * 1.4F * limbSwingAmount);
        this.rightFrontLeg.xRot = -0.5235987755982988F + -(Mth.cos(limbSwing * 5.0F) * 1.4F * limbSwingAmount);
        this.leftFrontLeg.xRot = 0.5235987755982988F + (Mth.cos(limbSwing * 5.0F) * 1.4F * limbSwingAmount);
        this.tail.yRot = Mth.cos(limbSwing * 0.4662F) * 0.6F * limbSwingAmount;
    }
}
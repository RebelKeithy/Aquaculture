package com.teammetallurgy.aquaculture.client.renderer.entity.model;

import com.google.common.collect.ImmutableList;
import com.teammetallurgy.aquaculture.entity.TurtleLandEntity;
import net.minecraft.client.model.QuadrupedModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
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
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition modelDefinition = new MeshDefinition();
        PartDefinition def = modelDefinition.getRoot();
        def.addOrReplaceChild("head", CubeListBuilder.create().texOffs(24, 0).addBox(-1.0F, -1.0F, -0.5F, 2, 2, 2), PartPose.offset(0.0F, 22.2F, -4.0F));
        def.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-2.5F, -1.0F, -3.0F, 5, 2, 6), PartPose.offset(0.0F, 22.0F, 0.0F));
        def.addOrReplaceChild("belly", CubeListBuilder.create().texOffs(0, 14).addBox(-2.0F, -0.5F, -2.5F, 4, 1, 5), PartPose.offset(0.0F, 23.0F, 0.0F));
        def.addOrReplaceChild("shell_top", CubeListBuilder.create().texOffs(0, 8).addBox(-2.0F, -0.5F, -2.5F, 4, 1, 5), PartPose.offset(0.0F, 20.5F, 0.0F));
        def.addOrReplaceChild("right_hind_leg", CubeListBuilder.create().texOffs(16, 3).addBox(-0.5F, -1.0F, -0.5F, 1, 2, 1), PartPose.offsetAndRotation(-2.5F, 23.0F, 3.0F, 0.5235987755982988F, 5.759586531581287F, 0.0F));
        def.addOrReplaceChild("left_hind_leg", CubeListBuilder.create().texOffs(16, 0).addBox(-0.5F, -1.0F, -0.5F, 1, 2, 1), PartPose.offsetAndRotation(2.5F, 23.0F, -3.0F, -0.5235987755982988F, -0.5235987755982988F, 0.0F));
        def.addOrReplaceChild("right_front_leg", CubeListBuilder.create().texOffs(20, 3).addBox(-0.5F, -1.0F, -0.5F, 1, 2, 1), PartPose.offsetAndRotation(-2.5F, 23.0F, -3.0F, -0.5235987755982988F, 0.5235987755982988F, 0.0F));
        def.addOrReplaceChild("left_front_leg", CubeListBuilder.create().texOffs(20, 0).addBox(-0.5F, -1.0F, -0.5F, 1, 2, 1), PartPose.offsetAndRotation(2.5F, 23.0F, 3.0F, 0.5235987755982988F, 0.5235987755982988F, 0.0F));
        def.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(26, 0).addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1), PartPose.offset(0.0F, 22.5F, 3.2F));
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
package com.teammetallurgy.aquaculture.client.renderer.entity.model;

import com.google.common.collect.ImmutableList;
import net.minecraft.client.model.ListModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;

import javax.annotation.Nonnull;

public class FishSmallModel<T extends Entity> extends ListModel<T> { //Based on TropicalFishA
    private final ModelPart finRight;
    private final ModelPart finTop;
    private final ModelPart tail;
    private final ModelPart finLeft;
    private final ModelPart body;
    private final ModelPart finBottom;

    public FishSmallModel(ModelPart part) {
        this.finRight = part.getChild("fin_right");
        this.finTop = part.getChild("fin_top");
        this.tail = part.getChild("tail");
        this.finLeft = part.getChild("fin_left");
        this.body = part.getChild("body");
        this.finBottom = part.getChild("fin_bottom");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition modelDefinition = new MeshDefinition();
        PartDefinition def = modelDefinition.getRoot();
        def.addOrReplaceChild("fin_right", CubeListBuilder.create().texOffs(2, 16).addBox(-2.0F, -1.0F, 0.0F, 2, 2, 0), PartPose.offsetAndRotation(-1.0F, 22.5F, 0.0F, 0.0F, 0.0F, -0.7853981852531433F));
        def.addOrReplaceChild("fin_top", CubeListBuilder.create().texOffs(10, -5).addBox(0.0F, -3.0F, 0.0F, 0, 3, 6), PartPose.offset(0.0F, 20.5F, -3.0F));
        def.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(22, -6).addBox(0.0F, -1.5F, 0.0F, 0, 3, 6), PartPose.offset(0.0F, 22.0F, 3.0F));
        def.addOrReplaceChild("fin_left", CubeListBuilder.create().texOffs(2, 12).addBox(0.0F, -1.0F, 0.0F, 2, 2, 0), PartPose.offsetAndRotation(1.0F, 22.5F, 0.0F, 0.0F, 0.0F, 0.7853981852531433F));
        def.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, -1.5F, -3.0F, 2, 3, 6), PartPose.offset(0.0F, 22.0F, 0.0F));
        def.addOrReplaceChild("fin_bottom", CubeListBuilder.create().texOffs(10, 5).addBox(0.0F, 0.0F, 0.0F, 0, 3, 6), PartPose.offset(0.0F, 23.5F, -3.0F));
        return LayerDefinition.create(modelDefinition, 32, 32);
    }

    @Override
    @Nonnull
    public Iterable<ModelPart> parts() {
        return ImmutableList.of(this.body, this.tail, this.finRight, this.finLeft, this.finTop, this.finBottom);
    }

    @Override
    public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        float movement = 1.0F;
        if (!entity.isInWater()) {
            movement = 1.5F;
        }
        this.tail.yRot = -movement * 0.45F * Mth.sin(0.6F * ageInTicks);
    }
}
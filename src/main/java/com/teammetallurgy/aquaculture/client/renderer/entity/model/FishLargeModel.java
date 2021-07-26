package com.teammetallurgy.aquaculture.client.renderer.entity.model;

import com.google.common.collect.ImmutableList;
import net.minecraft.client.model.ListModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;

import javax.annotation.Nonnull;

public class FishLargeModel<T extends Entity> extends ListModel<T> { //Based on cod
    private final ModelPart head;
    private final ModelPart topFin;
    private final ModelPart tail;
    private final ModelPart leftFin;
    private final ModelPart body;
    private final ModelPart rightFin;
    private final ModelPart headFront;
    private final ModelPart bottomFin;

    public FishLargeModel(ModelPart part) {
        this.head = part.getChild("head");
        this.topFin = part.getChild("top_fin");
        this.tail = part.getChild("tail");
        this.leftFin = part.getChild("left_fin");
        this.body = part.getChild("body");
        this.rightFin = part.getChild("right_fin");
        this.headFront = part.getChild("head_front");
        this.bottomFin = part.getChild("bottom_fin");

        /*this.bottomFin = new ModelPart(this, 20, 6);
        this.bottomFin.setPos(0.0F, 26.0F, 0.0F);
        this.bottomFin.addBox(0.0F, -1.0F, 0.0F, 0, 2, 6, 0.0F);
        this.head = new ModelPart(this, 11, 0);
        this.head.setPos(0.0F, 22.0F, 0.0F);
        this.head.addBox(-1.0F, -2.0F, -3.0F, 2, 4, 3, 0.0F);
        this.headFront = new ModelPart(this, 0, 0);
        this.headFront.setPos(0.0F, 22.0F, -3.0F);
        this.headFront.addBox(-1.0F, -2.0F, -1.0F, 2, 3, 1, 0.0F);
        this.leftFin = new ModelPart(this, 22, 4);
        this.leftFin.setPos(1.0F, 24.0F, 0.0F);
        this.leftFin.addBox(0.0F, 0.0F, -1.0F, 2, 0, 2, 0.0F);
        this.leftFin.zRot = 0.7853981633974483F;
        this.rightFin = new ModelPart(this, 26, 4);
        this.rightFin.setPos(-1.0F, 24.0F, 0.0F);
        this.rightFin.addBox(-2.0F, 0.0F, -1.0F, 2, 0, 2, 0.0F);
        this.rightFin.zRot = -0.7853981633974483F;
        this.topFin = new ModelPart(this, 18, -7);
        this.topFin.setPos(0.0F, 19.0F, 0.0F);
        this.topFin.addBox(0.0F, -1.0F, -1.0F, 0, 2, 7, 0.0F);
        this.tail = new ModelPart(this, 21, 2);
        this.tail.setPos(0.0F, 21.5F, 7.0F);
        this.tail.addBox(0.0F, -2.0F, 0.0F, 0, 5, 5, 0.0F);
        this.body = new ModelPart(this, 0, 1);
        this.body.setPos(0.0F, 22.0F, 0.0F);
        this.body.addBox(-1.0F, -2.0F, 0.0F, 2, 5, 7, 0.0F);*/
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition modelDefinition = new MeshDefinition();
        PartDefinition def = modelDefinition.getRoot();

        return LayerDefinition.create(modelDefinition, 32, 32);
    }

    @Override
    @Nonnull
    public Iterable<ModelPart> parts() {
        return ImmutableList.of(this.head, this.topFin, this.tail, this.leftFin, this.body, this.rightFin, this.headFront, this.bottomFin);
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
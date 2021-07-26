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

public class FishCathfishModel<T extends Entity> extends ListModel<T> { //Based on Cod
    private final ModelPart head;
    private final ModelPart headFront;
    private final ModelPart body;
    private final ModelPart topFin;
    private final ModelPart leftFin;
    private final ModelPart rightFin;
    private final ModelPart bottomFin;
    private final ModelPart tail;
    private final ModelPart leftWhisker;
    private final ModelPart rightWhisker;

    public FishCathfishModel(ModelPart part) {
        this.head = part.getChild("head");
        this.headFront = part.getChild("head_front");
        this.body = part.getChild("body");
        this.topFin = part.getChild("top_fin");
        this.leftFin = part.getChild("left_fin");
        this.rightFin = part.getChild("right_fin");
        this.bottomFin = part.getChild("bottom_fin");
        this.tail = part.getChild("tail");
        this.leftWhisker = part.getChild("left_whisker");
        this.rightWhisker = part.getChild("right_whisker");

        /*
        this.head = new ModelPart(this, 11, 0);
        this.head.setPos(-1.0F, 22.5F, 0.0F);
        this.head.addBox(-1.0F, -2.0F, -3.0F, 4, 3, 3, 0.0F);
        this.tail = new ModelPart(this, 22, 3);
        this.tail.setPos(0.0F, 22.0F, 7.0F);
        this.tail.addBox(0.0F, -2.0F, 0.0F, 0, 4, 4, 0.0F);
        this.rightFin = new ModelPart(this, 26, 4);
        this.rightFin.setPos(-1.0F, 22.0F, 1.0F);
        this.rightFin.addBox(-2.0F, 0.0F, -1.0F, 2, 0, 2, 0.0F);
        this.setRotateAngle(rightFin, -1.5707963267948966F, 0.0F, -0.7853981633974483F);
        this.rightWhisker = new ModelPart(this, 28, 17);
        this.rightWhisker.setPos(-1.0F, 0.0F, -2.0F);
        this.rightWhisker.addBox(-2.0F, -1.0F, -0.5F, 2, 2, 0, 0.0F);
        this.headFront = new ModelPart(this, 0, 0);
        this.headFront.setPos(0.0F, 22.5F, -3.0F);
        this.headFront.addBox(-1.0F, -2.0F, -1.0F, 2, 3, 1, 0.0F);
        this.leftWhisker = new ModelPart(this, 28, 15);
        this.leftWhisker.setPos(3.0F, 0.0F, -2.0F);
        this.leftWhisker.addBox(0.0F, -1.0F, -0.5F, 2, 2, 0, 0.0F);
        this.topFin = new ModelPart(this, 20, 7);
        this.topFin.setPos(0.0F, 19.0F, 0.0F);
        this.topFin.addBox(0.0F, -1.0F, 0.0F, 0, 2, 6, 0.0F);
        this.leftFin = new ModelPart(this, 26, 2);
        this.leftFin.setPos(1.0F, 21.9F, 1.5F);
        this.leftFin.addBox(0.0F, 0.0F, -1.0F, 2, 0, 2, 0.0F);
        this.setRotateAngle(leftFin, -1.5707963267948966F, 0.0F, 0.7853981633974483F);
        this.body = new ModelPart(this, 0, 0);
        this.body.setPos(0.0F, 22.0F, 0.0F);
        this.body.addBox(-1.0F, -2.0F, 0.0F, 2, 4, 7, 0.0F);
        this.bottomFin = new ModelPart(this, 20, 5);
        this.bottomFin.setPos(0.0F, 25.0F, 0.0F);
        this.bottomFin.addBox(0.0F, -1.0F, 0.0F, 0, 2, 6, 0.0F);
        this.head.addChild(this.rightWhisker);
        this.head.addChild(this.leftWhisker);*/
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition modelDefinition = new MeshDefinition();
        PartDefinition def = modelDefinition.getRoot();

        return LayerDefinition.create(modelDefinition, 32, 32);
    }

    @Override
    @Nonnull
    public Iterable<ModelPart> parts() {
        return ImmutableList.of(this.head, this.headFront, this.body, this.topFin, this.leftFin, this.rightFin, this.bottomFin, this.tail);
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
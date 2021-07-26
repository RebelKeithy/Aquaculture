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

public class FishMediumModel<T extends Entity> extends ListModel<T> { //Based on Cod
    private final ModelPart body;
    private final ModelPart finTop;
    private final ModelPart head;
    private final ModelPart headFront;
    private final ModelPart finRight;
    private final ModelPart finLeft;
    private final ModelPart tail;
    private final ModelPart bottomFin;

    public FishMediumModel(ModelPart part) {
        this.body = part.getChild("body");
        this.finTop = part.getChild("fin_top");
        this.head = part.getChild("head");
        this.headFront = part.getChild("head_front");
        this.finRight = part.getChild("fin_right");
        this.finLeft = part.getChild("fin_left");
        this.tail = part.getChild("tail");
        this.bottomFin = part.getChild("bottom_fin");
        /*
        this.body = new ModelPart(this, 0, 0);
        this.body.setPos(0.0F, 22.0F, 0.0F);
        this.body.addBox(-1.0F, -2.0F, 0.0F, 2, 4, 7, 0.0F);
        this.head = new ModelPart(this, 11, 0);
        this.head.setPos(0.0F, 22.0F, 0.0F);
        this.head.addBox(-1.0F, -2.0F, -3.0F, 2, 4, 3, 0.0F);
        this.headFront = new ModelPart(this, 0, 0);
        this.headFront.setPos(0.0F, 22.0F, -3.0F);
        this.headFront.addBox(-1.0F, -2.0F, -1.0F, 2, 3, 1, 0.0F);
        this.finRight = new ModelPart(this, 26, 4);
        this.finRight.setPos(-1.0F, 23.0F, 0.0F);
        this.finRight.addBox(-2.0F, 0.0F, -1.0F, 2, 0, 2, 0.0F);
        this.finRight.zRot = -0.7853981633974483F;
        this.finLeft = new ModelPart(this, 22, 4);
        this.finLeft.setPos(1.0F, 23.0F, 0.0F);
        this.finLeft.addBox(0.0F, 0.0F, -1.0F, 2, 0, 2, 0.0F);
        this.finLeft.zRot = 0.7853981633974483F;
        this.tail = new ModelPart(this, 22, 3);
        this.tail.setPos(0.0F, 22.0F, 7.0F);
        this.tail.addBox(0.0F, -2.0F, 0.0F, 0, 4, 4, 0.0F);
        this.finTop = new ModelPart(this, 20, -6);
        this.finTop.setPos(0.0F, 19.0F, 0.0F);
        this.finTop.addBox(0.0F, -1.0F, -1.0F, 0, 2, 6, 0.0F);
        this.bottomFin = new ModelPart(this, 20, 5);
        this.bottomFin.setPos(0.0F, 25.0F, 0.0F);
        this.bottomFin.addBox(0.0F, -1.0F, -1.0F, 0, 2, 6, 0.0F);*/
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition modelDefinition = new MeshDefinition();
        PartDefinition def = modelDefinition.getRoot();

        return LayerDefinition.create(modelDefinition, 32, 32);
    }

    @Override
    @Nonnull
    public Iterable<ModelPart> parts() {
        return ImmutableList.of(this.body, this.head, this.headFront, this.finRight, this.finLeft, this.tail, this.finTop, this.bottomFin);
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
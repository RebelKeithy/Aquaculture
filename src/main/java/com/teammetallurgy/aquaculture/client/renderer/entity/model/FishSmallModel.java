package com.teammetallurgy.aquaculture.client.renderer.entity.model;

import com.google.common.collect.ImmutableList;
import net.minecraft.client.model.ColorableHierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;

import javax.annotation.Nonnull;

public class FishSmallModel<T extends Entity> extends ColorableHierarchicalModel<T> { //Based on TropicalFishA
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

        /*
        this.body = new ModelPart(this, 0, 0);
        this.body.setPos(0.0F, 22.0F, 0.0F);
        this.body.addBox(-1.0F, -1.5F, -3.0F, 2, 3, 6, 0.0F);
        this.finTop = new ModelPart(this, 10, -5);
        this.finTop.setPos(0.0F, 20.5F, -3.0F);
        this.finTop.addBox(0.0F, -3.0F, 0.0F, 0, 3, 6, 0.0F);
        this.tail = new ModelPart(this, 22, -6);
        this.tail.setPos(0.0F, 22.0F, 3.0F);
        this.tail.addBox(0.0F, -1.5F, 0.0F, 0, 3, 6, 0.0F);
        this.finLeft = new ModelPart(this, 2, 12);
        this.finLeft.setPos(1.0F, 22.5F, 0.0F);
        this.finLeft.addBox(0.0F, -1.0F, 0.0F, 2, 2, 0, 0.0F);
        this.finLeft.zRot = 0.7853981852531433F;
        this.finRight = new ModelPart(this, 2, 16);
        this.finRight.setPos(-1.0F, 22.5F, 0.0F);
        this.finRight.addBox(-2.0F, -1.0F, 0.0F, 2, 2, 0, 0.0F);
        this.finRight.zRot = -0.7853981852531433F;
        this.finBottom = new ModelPart(this, 10, 5);
        this.finBottom.setPos(0.0F, 23.5F, -3.0F);
        this.finBottom.addBox(0.0F, 0.0F, 0.0F, 0, 3, 6, 0.0F);*/
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition modelDefinition = new MeshDefinition();
        PartDefinition def = modelDefinition.getRoot();

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
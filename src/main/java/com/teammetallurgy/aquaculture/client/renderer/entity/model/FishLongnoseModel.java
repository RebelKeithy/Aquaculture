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

public class FishLongnoseModel <T extends Entity> extends ListModel<T> { //Based on Salmon
    private final ModelPart nose;
    private final ModelPart finRight;
    private final ModelPart finLeft;
    private final ModelPart bodyFront;
    private final ModelPart bodyRear;
    private final ModelPart finTopFront;
    private final ModelPart finFrontBottom;
    private final ModelPart finRearBottom;
    private final ModelPart finTopRear;
    private final ModelPart tail;

    public FishLongnoseModel(ModelPart part) {
        this.nose = part.getChild("nose");
        this.finRight = part.getChild("fin_right");
        this.finLeft = part.getChild("fin_left");
        this.bodyFront = part.getChild("body_front");
        this.bodyRear = part.getChild("body_rear");
        this.finTopFront = part.getChild("fin_top_front");
        this.finFrontBottom = part.getChild("fin_front_bottom");
        this.finRearBottom = part.getChild("fin_rear_bottom");
        this.finTopRear = part.getChild("fin_top_rear");
        this.tail = part.getChild("tail");
        /*
        this.bodyRear = new ModelPart(this, 0, 13);
        this.bodyRear.setPos(0.0F, 20.0F, 8.0F);
        this.bodyRear.addBox(-1.5F, -2.5F, 0.0F, 3, 4, 8, 0.0F);
        this.finRight = new ModelPart(this, -4, 0);
        this.finRight.setPos(-1.5F, 21.5F, 4.0F);
        this.finRight.addBox(-2.0F, 0.0F, 0.0F, 2, 0, 2, 0.0F);
        this.finRight.zRot = -0.7853981633974483F;
        this.nose = new ModelPart(this, 22, 0);
        this.nose.setPos(0.0F, 20.5F, 0.0F);
        this.nose.addBox(-1.0F, -2.0F, -3.0F, 2, 2, 3, 0.0F);
        this.finTopFront = new ModelPart(this, 2, 1);
        this.finTopFront.setPos(0.0F, -4.5F, 5.0F);
        this.finTopFront.addBox(0.0F, 0.0F, 0.0F, 0, 2, 3, 0.0F);
        this.finTopRear = new ModelPart(this, 0, 2);
        this.finTopRear.setPos(0.0F, -4.5F, -1.0F);
        this.finTopRear.addBox(0.0F, 0.0F, 1.0F, 0, 2, 4, 0.0F);
        this.finLeft = new ModelPart(this, 0, 0);
        this.finLeft.setPos(1.5F, 21.5F, 4.0F);
        this.finLeft.addBox(0.0F, 0.0F, 0.0F, 2, 0, 2, 0.0F);
        this.finLeft.zRot = 0.7853981633974483F;
        this.finFrontBottom = new ModelPart(this, 0, 25);
        this.finFrontBottom.setPos(0.0F, 1.5F, 5.0F);
        this.finFrontBottom.addBox(0.0F, 0.0F, 0.0F, 0, 2, 3, 0.0F);
        this.tail = new ModelPart(this, 20, 10);
        this.tail.setPos(0.0F, 0.0F, 8.0F);
        this.tail.addBox(0.0F, -2.5F, 0.0F, 0, 4, 6, 0.0F);
        this.bodyFront = new ModelPart(this, 0, 0);
        this.bodyFront.setPos(0.0F, 20.0F, 0.0F);
        this.bodyFront.addBox(-1.5F, -2.5F, 0.0F, 3, 4, 8, 0.0F);
        this.finRearBottom = new ModelPart(this, 0, 22);
        this.finRearBottom.setPos(0.0F, 1.5F, -1.0F);
        this.finRearBottom.addBox(0.0F, 0.0F, 1.0F, 0, 2, 4, 0.0F);
        this.bodyFront.addChild(this.finTopFront);
        this.bodyFront.addChild(this.finFrontBottom);
        this.bodyRear.addChild(this.finTopRear);
        this.bodyRear.addChild(this.tail);
        this.bodyRear.addChild(this.finRearBottom);*/
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition modelDefinition = new MeshDefinition();
        PartDefinition def = modelDefinition.getRoot();

        return LayerDefinition.create(modelDefinition, 32, 32);
    }

    @Override
    @Nonnull
    public Iterable<ModelPart> parts() {
        return ImmutableList.of(this.bodyFront, this.bodyRear, this.nose, this.finRight, this.finLeft);
    }

    @Override
    public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        float f = 1.0F;
        float f1 = 1.0F;
        if (!entity.isInWater()) {
            f = 1.3F;
            f1 = 1.7F;
        }
        float moveAmount = 0.15F; //Default for Salmon is 0.25F
        this.bodyRear.yRot = -f * moveAmount * Mth.sin(f1 * 0.6F * ageInTicks);
    }
}
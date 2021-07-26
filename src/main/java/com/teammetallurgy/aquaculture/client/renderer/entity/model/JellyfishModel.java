package com.teammetallurgy.aquaculture.client.renderer.entity.model;

import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.ListModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;

import javax.annotation.Nonnull;

public class JellyfishModel<T extends Entity> extends ListModel<T> {
    private final ModelPart head;
    private final ModelPart tentaclesMain;
    private final ModelPart tentaclesLeft;
    private final ModelPart tentaclesRight;
    private final ModelPart frill;
    private final ModelPart heart;

    public JellyfishModel(ModelPart part) {
        this.head = part.getChild("head");
        this.tentaclesMain = part.getChild("tentacles_main");
        this.tentaclesLeft = part.getChild("tentacles_left");
        this.tentaclesRight = part.getChild("tentacles_right");
        this.frill = part.getChild("frill");
        this.heart = part.getChild("heart");

        /*
        this.heart = new ModelPart(this, 34, 0);
        this.heart.setPos(0.0F, 0.0F, 0.0F);
        this.heart.addBox(-1.5F, -1.5F, -2.0F, 3, 3, 3, 0.0F);
        this.head = new ModelPart(this, 14, 0);
        this.head.setPos(0.0F, 21.0F, -3.0F);
        this.head.addBox(-2.5F, -2.5F, -5.0F, 5, 5, 5, 0.0F);
        this.frill = new ModelPart(this, 0, 0);
        this.frill.setPos(0.0F, 0.0F, -2.0F);
        this.frill.addBox(-3.0F, -3.0F, 0.0F, 6, 6, 1, 0.0F);
        this.tentaclesMain = new ModelPart(this, 0, 0);
        this.tentaclesMain.setPos(0.0F, 21.0F, -3.5F);
        this.tentaclesMain.addBox(0.0F, -3.0F, 0.0F, 0, 6, 14, 0.0F);
        this.tentaclesRight = new ModelPart(this, 0, 14);
        this.tentaclesRight.setPos(0.0F, 21.0F, -3.5F);
        this.tentaclesRight.addBox(-2.0F, -3.0F, 0.0F, 0, 6, 12, 0.0F);
        this.tentaclesLeft = new ModelPart(this, 0, 8);
        this.tentaclesLeft.setPos(0.0F, 21.0F, -3.5F);
        this.tentaclesLeft.addBox(2.0F, -3.0F, 0.0F, 0, 6, 12, 0.0F);
        this.head.addChild(this.heart);
        this.head.addChild(this.frill);*/
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition modelDefinition = new MeshDefinition();
        PartDefinition def = modelDefinition.getRoot();

        return LayerDefinition.create(modelDefinition, 64, 32);
    }

    @Override
    @Nonnull
    public Iterable<ModelPart> parts() {
        return ImmutableList.of(this.head, this.tentaclesMain, this.tentaclesLeft, this.tentaclesRight);
    }

    @Override
    public void renderToBuffer(@Nonnull PoseStack matrixStack, @Nonnull VertexConsumer builder, int p_225598_3_, int p_225598_4_, float p_225598_5_, float p_225598_6_, float p_225598_7_, float p_225598_8_) {
        //Actual rendering done in the layer. Only way I could figure out to keep the transparency
    }

    @Override
    public void setupAnim(@Nonnull T jellyfish, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        float stillMovement = 0.1F;
        if (!jellyfish.isInWater()) {
            stillMovement = 0.05F;
        }
        this.tentaclesLeft.yRot = -stillMovement * 0.25F * Mth.sin(0.3F * ageInTicks) + Mth.cos(limbSwing * 0.4662F) * 0.5F * limbSwingAmount;
        this.tentaclesMain.yRot = -stillMovement * 0.25F * Mth.sin(0.3F * ageInTicks) + Mth.cos(limbSwing * 0.4662F) * 0.5F * limbSwingAmount;
        this.tentaclesRight.yRot = -stillMovement * 0.25F * Mth.sin(0.3F * ageInTicks) + Mth.cos(limbSwing * 0.4662F) * 0.5F * limbSwingAmount;
    }
}
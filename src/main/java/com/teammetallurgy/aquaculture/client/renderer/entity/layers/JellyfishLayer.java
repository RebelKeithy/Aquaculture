package com.teammetallurgy.aquaculture.client.renderer.entity.layers;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.teammetallurgy.aquaculture.client.renderer.entity.model.JellyfishModel;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.LivingRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.entity.LivingEntity;

import javax.annotation.Nonnull;

public class JellyfishLayer<T extends LivingEntity> extends LayerRenderer<T, JellyfishModel<T>> {
    private final EntityModel<T> jellyfishModel = new JellyfishModel<>();

    public JellyfishLayer(IEntityRenderer<T, JellyfishModel<T>> renderer) {
        super(renderer);
    }

    @Override
    public void func_225628_a_(@Nonnull MatrixStack matrixStack, @Nonnull IRenderTypeBuffer buffer, int i, T jellyfish, float p_225628_5_, float p_225628_6_, float p_225628_7_, float p_225628_8_, float p_225628_9_, float p_225628_10_) {
        if (!jellyfish.isInvisible()) {
            this.getEntityModel().setModelAttributes(this.jellyfishModel);
            this.jellyfishModel.setLivingAnimations(jellyfish, p_225628_5_, p_225628_6_, p_225628_7_);
            this.jellyfishModel.func_225597_a_(jellyfish, p_225628_5_, p_225628_6_, p_225628_8_, p_225628_9_, p_225628_10_);
            IVertexBuilder lvt_11_1_ = buffer.getBuffer(RenderType.func_228644_e_(this.func_229139_a_(jellyfish)));
            this.jellyfishModel.func_225598_a_(matrixStack, lvt_11_1_, i, LivingRenderer.func_229117_c_(jellyfish, 0.0F), 1.0F, 1.0F, 1.0F, 1.0F);
        }
    }
}
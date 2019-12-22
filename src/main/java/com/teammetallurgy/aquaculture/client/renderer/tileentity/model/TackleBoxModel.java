/*package com.teammetallurgy.aquaculture.client.renderer.tileentity.model;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.model.Model;
import net.minecraft.client.renderer.model.ModelRenderer;

import javax.annotation.Nonnull;

public class TackleBoxModel extends Model { //TODO
    private ModelRenderer base;
    private ModelRenderer lid;
    private ModelRenderer handle;

    public TackleBoxModel() {
        super(RenderType::func_228639_c_);
        this.textureWidth = 64;
        this.textureHeight = 32;
        this.lid = new ModelRenderer(this, 0, 0);
        this.lid.setRotationPoint(7.0F, 12.0F, 4.0F);
        this.lid.func_228301_a_(-7.0F, -3.0F, -8.0F, 14, 3, 8, 0.0F);
        this.base = new ModelRenderer(this, 0, 11);
        this.base.setRotationPoint(0.0F, 18.0F, 4.0F);
        this.base.func_228301_a_(0.0F, -6.0F, -8.0F, 14, 6, 8, 0.0F);
        this.handle = new ModelRenderer(this, 36, 0);
        this.handle.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.handle.func_228301_a_(-2.0F, -4.0F, -5.0F, 4, 1, 2, 0.0F);
        this.lid.addChild(this.handle);
    }

    @Override
    public void func_225598_a_(@Nonnull MatrixStack matrixStack, @Nonnull IVertexBuilder builder, int i, int i1, float v, float v1, float v2, float v3) {
        this.lid.func_228309_a_(matrixStack, builder, i, i1, v, v1, v2, v3);
        this.base.func_228309_a_(matrixStack, builder, i, i1, v, v1, v2, v3);
    }

    public ModelRenderer getLid() {
        return this.lid;
    }
}*/
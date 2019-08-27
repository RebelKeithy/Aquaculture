package com.teammetallurgy.aquaculture.client.renderer.entity;

import com.mojang.blaze3d.platform.GlStateManager;
import com.teammetallurgy.aquaculture.Aquaculture;
import com.teammetallurgy.aquaculture.client.renderer.entity.model.JellyfishModel;
import com.teammetallurgy.aquaculture.entity.JellyfishEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class JellyfishRenderer extends MobRenderer<JellyfishEntity, JellyfishModel<JellyfishEntity>> {
    private static final ResourceLocation JELLYFISH = new ResourceLocation(Aquaculture.MOD_ID, "textures/entity/jellyfish/jellyfish.png");

    public JellyfishRenderer(EntityRendererManager manager) {
        super(manager, new JellyfishModel<>(), 0.4F);
    }

    @Override
    @Nullable
    protected ResourceLocation getEntityTexture(@Nonnull JellyfishEntity jellyfish) {
        return JELLYFISH;
    }

    @Override
    protected void applyRotations(JellyfishEntity jellyfish, float x, float y, float z) {
        float pitch = MathHelper.lerp(z, jellyfish.prevSquidPitch, jellyfish.squidPitch);
        float yaw = MathHelper.lerp(z, jellyfish.prevSquidYaw, jellyfish.squidYaw);
        GlStateManager.translatef(0.0F, 0.5F, 0.0F);
        GlStateManager.rotatef(180.0F - y, 0.0F, 1.0F, 0.0F);
        GlStateManager.rotatef(pitch, 1.0F, 0.0F, 0.0F);
        GlStateManager.rotatef(yaw, 0.0F, 1.0F, 0.0F);
        GlStateManager.translatef(0.0F, -1.2F, 0.0F);
    }

    @Override
    protected float handleRotationFloat(JellyfishEntity jellyfish, float rotation) {
        return MathHelper.lerp(rotation, jellyfish.lastTentacleAngle, jellyfish.tentacleAngle);
    }
}
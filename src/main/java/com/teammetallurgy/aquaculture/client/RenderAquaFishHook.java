package com.teammetallurgy.aquaculture.client;

import com.mojang.blaze3d.platform.GlStateManager;
import com.teammetallurgy.aquaculture.entity.AquaFishingBobberEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.HandSide;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Objects;

@OnlyIn(Dist.CLIENT)
public class RenderAquaFishHook extends EntityRenderer<AquaFishingBobberEntity> {
    private static final ResourceLocation FISH_PARTICLES = new ResourceLocation("textures/particle/particles.png");

    public RenderAquaFishHook(EntityRendererManager manager) {
        super(manager);
    }

    @Override
    public void doRender(AquaFishingBobberEntity bobber, double x, double y, double z, float entityYaw, float partialTicks) {
        PlayerEntity angler = bobber.getAngler();
        if (angler != null && !this.renderOutlines) {
            GlStateManager.pushMatrix();
            GlStateManager.translatef((float) x, (float) y, (float) z);
            GlStateManager.enableRescaleNormal();
            GlStateManager.scalef(0.5F, 0.5F, 0.5F);
            this.bindEntityTexture(bobber);
            Tessellator tessellator = Tessellator.getInstance();
            BufferBuilder builder = tessellator.getBuffer();
            GlStateManager.rotatef(180.0F - this.field_76990_c.playerViewY, 0.0F, 1.0F, 0.0F);
            GlStateManager.rotatef((float) (this.field_76990_c.options.thirdPersonView == 2 ? -1 : 1) * -this.field_76990_c.playerViewX, 1.0F, 0.0F, 0.0F);
            if (this.renderOutlines) {
                GlStateManager.enableColorMaterial();
                GlStateManager.setupSolidRenderingTextureCombine(this.getTeamColor(bobber));
            }

            builder.begin(7, DefaultVertexFormats.POSITION_TEX_NORMAL);
            builder.pos(-0.5D, -0.5D, 0.0D).tex(0.0D, 1.0D).normal(0.0F, 1.0F, 0.0F).endVertex();
            builder.pos(0.5D, -0.5D, 0.0D).tex(1.0D, 1.0D).normal(0.0F, 1.0F, 0.0F).endVertex();
            builder.pos(0.5D, 0.5D, 0.0D).tex(1.0D, 0.0D).normal(0.0F, 1.0F, 0.0F).endVertex();
            builder.pos(-0.5D, 0.5D, 0.0D).tex(0.0D, 0.0D).normal(0.0F, 1.0F, 0.0F).endVertex();
            tessellator.draw();
            if (this.renderOutlines) {
                GlStateManager.tearDownSolidRenderingTextureCombine();
                GlStateManager.disableColorMaterial();
            }

            GlStateManager.disableRescaleNormal();
            GlStateManager.popMatrix();
            int hand = angler.getPrimaryHand() == HandSide.RIGHT ? 1 : -1;
            ItemStack heldMain = angler.getHeldItemMainhand();
            if (!(heldMain.getItem() instanceof net.minecraft.item.FishingRodItem)) {
                hand = -hand;
            }

            float swingProgress = angler.getSwingProgress(partialTicks);
            float swingProgressSqrt = MathHelper.sin(MathHelper.sqrt(swingProgress) * (float) Math.PI);
            float yawOffset = MathHelper.func_219799_g(partialTicks, angler.prevRenderYawOffset, angler.renderYawOffset) * ((float) Math.PI / 180F);
            double sin = (double) MathHelper.sin(yawOffset);
            double cos = (double) MathHelper.cos(yawOffset);
            double handOffset = (double) hand * 0.35D;
            double anglerX;
            double anglerY;
            double anglerZ;
            double anglerEye;
            if ((this.field_76990_c.options == null || this.field_76990_c.options.thirdPersonView <= 0) && angler == Minecraft.getInstance().player) {
                double fov = Objects.requireNonNull(this.field_76990_c.options).fovSetting;
                fov = fov / 100.0D;
                Vec3d rod = new Vec3d((double) hand * -0.36D * fov, -0.045D * fov, 0.4D);
                rod = rod.rotatePitch(-MathHelper.func_219799_g(partialTicks, angler.prevRotationPitch, angler.rotationPitch) * ((float) Math.PI / 180F));
                rod = rod.rotateYaw(-MathHelper.func_219799_g(partialTicks, angler.prevRotationYaw, angler.rotationYaw) * ((float) Math.PI / 180F));
                rod = rod.rotateYaw(swingProgressSqrt * 0.5F);
                rod = rod.rotatePitch(-swingProgressSqrt * 0.7F);
                anglerX = MathHelper.func_219803_d((double) partialTicks, angler.prevPosX, angler.posX) + rod.x;
                anglerY = MathHelper.func_219803_d((double) partialTicks, angler.prevPosY, angler.posY) + rod.y;
                anglerZ = MathHelper.func_219803_d((double) partialTicks, angler.prevPosZ, angler.posZ) + rod.z;
                anglerEye = (double) angler.getEyeHeight();
            } else {
                anglerX = MathHelper.func_219803_d((double) partialTicks, angler.prevPosX, angler.posX) - cos * handOffset - sin * 0.8D;
                anglerY = angler.prevPosY + (double) angler.getEyeHeight() + (angler.posY - angler.prevPosY) * (double) partialTicks - 0.45D;
                anglerZ = MathHelper.func_219803_d((double) partialTicks, angler.prevPosZ, angler.posZ) - sin * handOffset + cos * 0.8D;
                anglerEye = angler.func_213287_bg() ? -0.1875D : 0.0D;
            }

            double hookX = MathHelper.func_219803_d((double) partialTicks, bobber.prevPosX, bobber.posX);
            double hookY = MathHelper.func_219803_d((double) partialTicks, bobber.prevPosY, bobber.posY) + 0.25D;
            double hookZ = MathHelper.func_219803_d((double) partialTicks, bobber.prevPosZ, bobber.posZ);
            double startX = (double) ((float) (anglerX - hookX));
            double startY = (double) ((float) (anglerY - hookY)) + anglerEye;
            double startZ = (double) ((float) (anglerZ - hookZ));
            GlStateManager.disableTexture();
            GlStateManager.disableLighting();
            builder.begin(3, DefaultVertexFormats.POSITION_COLOR);

            for (int size = 0; size <= 16; ++size) {
                float length = (float) size / 16.0F;
                //Line color
                builder.pos(x + startX * (double) length, y + startY * (double) (length * length + length) * 0.5D + 0.25D, z + startZ * (double) length).color(0, 0, 0, 255).endVertex();
            }

            tessellator.draw();
            GlStateManager.enableLighting();
            GlStateManager.enableTexture();
            super.doRender(bobber, x, y, z, entityYaw, partialTicks);
        }
    }

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(@Nonnull AquaFishingBobberEntity fishHook) {
        return FISH_PARTICLES;
    }
}
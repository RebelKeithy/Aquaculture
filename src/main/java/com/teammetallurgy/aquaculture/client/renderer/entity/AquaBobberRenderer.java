package com.teammetallurgy.aquaculture.client.renderer.entity;

import com.mojang.blaze3d.platform.GlStateManager;
import com.teammetallurgy.aquaculture.Aquaculture;
import com.teammetallurgy.aquaculture.entity.AquaFishingBobberEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.FishingRodItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.HandSide;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Objects;

public class AquaBobberRenderer extends EntityRenderer<AquaFishingBobberEntity> {
    private static final ResourceLocation BOBBER = new ResourceLocation(Aquaculture.MOD_ID, "textures/entity/rod/bobber.png");
    private static final ResourceLocation HOOK = new ResourceLocation(Aquaculture.MOD_ID, "textures/entity/rod/hook/hook.png");

    public AquaBobberRenderer(EntityRendererManager manager) {
        super(manager);
    }

    @Override
    public void doRender(@Nonnull AquaFishingBobberEntity bobber, double x, double y, double z, float entityYaw, float partialTicks) { //Render bobber and line
        PlayerEntity angler = bobber.getAngler();
        if (angler != null && !this.renderOutlines) {
            GlStateManager.pushMatrix();
            GlStateManager.translatef((float) x, (float) y, (float) z);
            GlStateManager.enableRescaleNormal();
            GlStateManager.scalef(0.5F, 0.5F, 0.5F);
            this.bindEntityTexture(bobber);
            Tessellator tessellator = Tessellator.getInstance();
            BufferBuilder builder = tessellator.getBuffer();
            GlStateManager.rotatef(180.0F - this.renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
            GlStateManager.rotatef((float) (this.renderManager.options.thirdPersonView == 2 ? -1 : 1) * -this.renderManager.playerViewX, 1.0F, 0.0F, 0.0F);
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
            if (!(heldMain.getItem() instanceof FishingRodItem)) {
                hand = -hand;
            }

            float swingProgress = angler.getSwingProgress(partialTicks);
            float swingProgressSqrt = MathHelper.sin(MathHelper.sqrt(swingProgress) * 3.1415927F);
            float yawOffset = MathHelper.lerp(partialTicks, angler.prevRenderYawOffset, angler.renderYawOffset) * 0.017453292F;
            double sin = MathHelper.sin(yawOffset);
            double cos = MathHelper.cos(yawOffset);
            double handOffset = (double) hand * 0.35D;
            double anglerX;
            double anglerY;
            double anglerZ;
            double anglerEye;
            double fov;
            if ((this.renderManager.options == null || this.renderManager.options.thirdPersonView <= 0) && angler == Minecraft.getInstance().player) {
                fov = Objects.requireNonNull(this.renderManager.options).fov;
                fov /= 100.0D;
                Vec3d rod = new Vec3d((double) hand * -0.36D * fov, -0.045D * fov, 0.4D);
                rod = rod.rotatePitch(-MathHelper.lerp(partialTicks, angler.prevRotationPitch, angler.rotationPitch) * 0.017453292F);
                rod = rod.rotateYaw(-MathHelper.lerp(partialTicks, angler.prevRotationYaw, angler.rotationYaw) * 0.017453292F);
                rod = rod.rotateYaw(swingProgressSqrt * 0.5F);
                rod = rod.rotatePitch(-swingProgressSqrt * 0.7F);
                anglerX = MathHelper.lerp(partialTicks, angler.prevPosX, angler.posX) + rod.x;
                anglerY = MathHelper.lerp(partialTicks, angler.prevPosY, angler.posY) + rod.y;
                anglerZ = MathHelper.lerp(partialTicks, angler.prevPosZ, angler.posZ) + rod.z;
                anglerEye = angler.getEyeHeight();
            } else {
                anglerX = MathHelper.lerp(partialTicks, angler.prevPosX, angler.posX) - cos * handOffset - sin * 0.8D;
                anglerY = angler.prevPosY + (double) angler.getEyeHeight() + (angler.posY - angler.prevPosY) * (double) partialTicks - 0.45D;
                anglerZ = MathHelper.lerp(partialTicks, angler.prevPosZ, angler.posZ) - sin * handOffset + cos * 0.8D;
                anglerEye = angler.shouldRenderSneaking() ? -0.1875D : 0.0D;
            }

            fov = MathHelper.lerp(partialTicks, bobber.prevPosX, bobber.posX);
            double d14 = MathHelper.lerp(partialTicks, bobber.prevPosY, bobber.posY) + 0.25D;
            double d9 = MathHelper.lerp(partialTicks, bobber.prevPosZ, bobber.posZ);
            double startX = (float) (anglerX - fov);
            double startY = (double) ((float) (anglerY - d14)) + anglerEye;
            double startZ = (float) (anglerZ - d9);
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

    @Override
    public boolean isMultipass() {
        return true;
    }

    @Override
    public void renderMultipass(@Nonnull AquaFishingBobberEntity bobber, double x, double y, double z, float entityYaw, float partialTicks) { //Render hook
        PlayerEntity angler = bobber.getAngler();
        if (angler != null && !this.renderOutlines) {
            GlStateManager.pushMatrix();
            GlStateManager.translatef((float) x, (float) y, (float) z);
            GlStateManager.enableRescaleNormal();
            GlStateManager.scalef(0.5F, 0.5F, 0.5F);
            if (bobber.getHook() != null) {
                this.bindTexture(bobber.getHook().getTexture());
            } else {
                this.bindTexture(HOOK);
            }
            Tessellator tessellator = Tessellator.getInstance();
            BufferBuilder builder = tessellator.getBuffer();
            GlStateManager.rotatef(180.0F - this.renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
            GlStateManager.rotatef((float) (this.renderManager.options.thirdPersonView == 2 ? -1 : 1) * -this.renderManager.playerViewX, 1.0F, 0.0F, 0.0F);
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
            super.renderMultipass(bobber, x, y, z, entityYaw, partialTicks);
        }
    }

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(@Nonnull AquaFishingBobberEntity fishHook) {
        return BOBBER;
    }
}
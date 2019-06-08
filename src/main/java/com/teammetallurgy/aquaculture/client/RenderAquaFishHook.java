package com.teammetallurgy.aquaculture.client;

import com.teammetallurgy.aquaculture.entity.EntityAquaFishHook;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFishingRod;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHandSide;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Objects;

@OnlyIn(Dist.CLIENT)
public class RenderAquaFishHook extends Render<EntityAquaFishHook> {
    private static final ResourceLocation FISH_PARTICLES = new ResourceLocation("textures/particle/particles.png");

    public RenderAquaFishHook(RenderManager manager) {
        super(manager);
    }

    @Override
    public void doRender(@Nonnull EntityAquaFishHook fishHook, double x, double y, double z, float entityYaw, float partialTicks) {
        EntityPlayer angler = fishHook.getAngler();
        if (angler != null && !this.renderOutlines) {
            GlStateManager.pushMatrix();
            GlStateManager.translatef((float) x, (float) y, (float) z);
            GlStateManager.enableRescaleNormal();
            GlStateManager.scalef(0.5F, 0.5F, 0.5F);
            this.bindEntityTexture(fishHook);
            Tessellator tessellator = Tessellator.getInstance();
            BufferBuilder builder = tessellator.getBuffer();
            GlStateManager.rotatef(180.0F - this.renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
            GlStateManager.rotatef((float) (this.renderManager.options.thirdPersonView == 2 ? -1 : 1) * -this.renderManager.playerViewX, 1.0F, 0.0F, 0.0F);
            if (this.renderOutlines) {
                GlStateManager.enableColorMaterial();
                GlStateManager.enableOutlineMode(this.getTeamColor(fishHook));
            }

            builder.begin(7, DefaultVertexFormats.POSITION_TEX_NORMAL);
            builder.pos(-0.5D, -0.5D, 0.0D).tex(0.03125D, 0.09375D).normal(0.0F, 1.0F, 0.0F).endVertex();
            builder.pos(0.5D, -0.5D, 0.0D).tex(0.0625D, 0.09375D).normal(0.0F, 1.0F, 0.0F).endVertex();
            builder.pos(0.5D, 0.5D, 0.0D).tex(0.0625D, 0.0625D).normal(0.0F, 1.0F, 0.0F).endVertex();
            builder.pos(-0.5D, 0.5D, 0.0D).tex(0.03125D, 0.0625D).normal(0.0F, 1.0F, 0.0F).endVertex();
            tessellator.draw();
            if (this.renderOutlines) {
                GlStateManager.disableOutlineMode();
                GlStateManager.disableColorMaterial();
            }

            GlStateManager.disableRescaleNormal();
            GlStateManager.popMatrix();
            int hand = angler.getPrimaryHand() == EnumHandSide.RIGHT ? 1 : -1;
            ItemStack heldStack = angler.getHeldItemMainhand();
            if (!(heldStack.getItem() instanceof ItemFishingRod)) {
                hand = -hand;
            }

            float swingProgress = angler.getSwingProgress(partialTicks);
            float swingProgressSqrt = MathHelper.sin(MathHelper.sqrt(swingProgress) * (float) Math.PI);
            float offset = (angler.prevRenderYawOffset + (angler.renderYawOffset - angler.prevRenderYawOffset) * partialTicks) * ((float) Math.PI / 180F);
            double sin = (double) MathHelper.sin(offset);
            double cos = (double) MathHelper.cos(offset);
            double handOffset = (double) hand * 0.35D;
            double anglerX;
            double anglery;
            double anglerZ;
            double anglerEye;
            if ((this.renderManager.options == null || this.renderManager.options.thirdPersonView <= 0) && angler == Minecraft.getInstance().player) {
                double fov = Objects.requireNonNull(this.renderManager.options).fovSetting;
                fov = fov / 100.0D;
                Vec3d rod = new Vec3d((double) hand * -0.36D * fov, -0.045D * fov, 0.4D);
                rod = rod.rotatePitch(-(angler.prevRotationPitch + (angler.rotationPitch - angler.prevRotationPitch) * partialTicks) * ((float) Math.PI / 180F));
                rod = rod.rotateYaw(-(angler.prevRotationYaw + (angler.rotationYaw - angler.prevRotationYaw) * partialTicks) * ((float) Math.PI / 180F));
                rod = rod.rotateYaw(swingProgressSqrt * 0.5F);
                rod = rod.rotatePitch(-swingProgressSqrt * 0.7F);
                anglerX = angler.prevPosX + (angler.posX - angler.prevPosX) * (double) partialTicks + rod.x;
                anglery = angler.prevPosY + (angler.posY - angler.prevPosY) * (double) partialTicks + rod.y;
                anglerZ = angler.prevPosZ + (angler.posZ - angler.prevPosZ) * (double) partialTicks + rod.z;
                anglerEye = (double) angler.getEyeHeight();
            } else {
                anglerX = angler.prevPosX + (angler.posX - angler.prevPosX) * (double) partialTicks - cos * handOffset - sin * 0.8D;
                anglery = angler.prevPosY + (double) angler.getEyeHeight() + (angler.posY - angler.prevPosY) * (double) partialTicks - 0.45D;
                anglerZ = angler.prevPosZ + (angler.posZ - angler.prevPosZ) * (double) partialTicks - sin * handOffset + cos * 0.8D;
                anglerEye = angler.isSneaking() ? -0.1875D : 0.0D;
            }

            double hookX = fishHook.prevPosX + (fishHook.posX - fishHook.prevPosX) * (double) partialTicks;
            double hookY = fishHook.prevPosY + (fishHook.posY - fishHook.prevPosY) * (double) partialTicks + 0.25D;
            double hookZ = fishHook.prevPosZ + (fishHook.posZ - fishHook.prevPosZ) * (double) partialTicks;
            double startX = (double) ((float) (anglerX - hookX));
            double startY = (double) ((float) (anglery - hookY)) + anglerEye;
            double startZ = (double) ((float) (anglerZ - hookZ));
            GlStateManager.disableTexture2D();
            GlStateManager.disableLighting();
            builder.begin(3, DefaultVertexFormats.POSITION_COLOR);

            for (int size = 0; size <= 16; ++size) {
                float length = (float) size / 16.0F;
                //Line color
                builder.pos(x + startX * (double) length, y + startY * (double) (length * length + length) * 0.5D + 0.25D, z + startZ * (double) length).color(0, 0, 0, 255).endVertex();
            }

            tessellator.draw();
            GlStateManager.enableLighting();
            GlStateManager.enableTexture2D();
            super.doRender(fishHook, x, y, z, entityYaw, partialTicks);
        }
    }

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(@Nonnull EntityAquaFishHook fishHook) {
        return FISH_PARTICLES;
    }
}
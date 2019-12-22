package com.teammetallurgy.aquaculture.client.renderer.entity;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.teammetallurgy.aquaculture.Aquaculture;
import com.teammetallurgy.aquaculture.entity.AquaFishingBobberEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.*;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.FishingRodItem;
import net.minecraft.item.IDyeableArmorItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.HandSide;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;

import javax.annotation.Nonnull;
import java.util.Objects;

public class AquaBobberRenderer extends EntityRenderer<AquaFishingBobberEntity> {
    private static final ResourceLocation BOBBER = new ResourceLocation(Aquaculture.MOD_ID, "textures/entity/rod/bobber/bobber.png");
    private static final ResourceLocation BOBBER_OVERLAY = new ResourceLocation(Aquaculture.MOD_ID, "textures/entity/rod/bobber/bobber_overlay.png");
    private static final ResourceLocation BOBBER_VANILLA = new ResourceLocation(Aquaculture.MOD_ID, "textures/entity/rod/bobber/bobber_vanilla.png");
    private static final ResourceLocation HOOK = new ResourceLocation(Aquaculture.MOD_ID, "textures/entity/rod/hook/hook.png");
    private static final RenderType BOBBER_RENDER = RenderType.func_228638_b_(BOBBER);
    private static final RenderType BOBBER_OVERLAY_RENDER = RenderType.func_228638_b_(BOBBER_OVERLAY);
    private static final RenderType BOBBER_VANILLA_RENDER = RenderType.func_228638_b_(BOBBER_VANILLA);
    private static final RenderType HOOK_RENDER = RenderType.func_228638_b_(HOOK);

    public AquaBobberRenderer(EntityRendererManager manager) {
        super(manager);
    }

    @Override
    public void func_225623_a_(@Nonnull AquaFishingBobberEntity bobber, float entityYaw, float partialTicks, @Nonnull MatrixStack matrixStack, @Nonnull IRenderTypeBuffer buffer, int i) {
        PlayerEntity angler = bobber.getAngler();
        if (angler != null) {
            matrixStack.func_227860_a_();
            //Bobber + Bobber Overlay
            matrixStack.func_227860_a_();
            matrixStack.func_227862_a_(0.5F, 0.5F, 0.5F);
            matrixStack.func_227863_a_(this.renderManager.func_229098_b_());
            matrixStack.func_227863_a_(Vector3f.field_229181_d_.func_229187_a_(180.0F));
            MatrixStack.Entry bobberMatrix = matrixStack.func_227866_c_();
            Matrix4f bobber4f = bobberMatrix.func_227870_a_();
            Matrix3f bobber3f = bobberMatrix.func_227872_b_();
            IVertexBuilder bobberOverlayVertex = bobber.hasBobber() ? buffer.getBuffer(BOBBER_OVERLAY_RENDER) : buffer.getBuffer(BOBBER_VANILLA_RENDER);
            renderPosTexture(bobberOverlayVertex, bobber4f, bobber3f, i, 0.0F, 0, 0, 1);
            renderPosTexture(bobberOverlayVertex, bobber4f, bobber3f, i, 1.0F, 0, 1, 1);
            renderPosTexture(bobberOverlayVertex, bobber4f, bobber3f, i, 1.0F, 1, 1, 0);
            renderPosTexture(bobberOverlayVertex, bobber4f, bobber3f, i, 0.0F, 1, 0, 0);
            if (bobber.hasBobber()) {
                IVertexBuilder bobberVertex = buffer.getBuffer(BOBBER_RENDER);
                renderPosTexture(bobberVertex, bobber4f, bobber3f, i, 0.0F, 0, 0, 1);
                renderPosTexture(bobberVertex, bobber4f, bobber3f, i, 1.0F, 0, 1, 1);
                renderPosTexture(bobberVertex, bobber4f, bobber3f, i, 1.0F, 1, 1, 0);
                renderPosTexture(bobberVertex, bobber4f, bobber3f, i, 0.0F, 1, 0, 0);
            }
            matrixStack.func_227865_b_();

            //Hook
            matrixStack.func_227860_a_();
            matrixStack.func_227862_a_(0.5F, 0.5F, 0.5F);
            matrixStack.func_227863_a_(this.renderManager.func_229098_b_());
            matrixStack.func_227863_a_(Vector3f.field_229181_d_.func_229187_a_(180.0F));
            MatrixStack.Entry matrixEntryHook = matrixStack.func_227866_c_();
            Matrix4f hook4f = matrixEntryHook.func_227870_a_();
            Matrix3f hook3f = matrixEntryHook.func_227872_b_();
            IVertexBuilder hookVertex = bobber.hasHook() ? buffer.getBuffer(RenderType.func_228638_b_(bobber.getHook().getTexture())) : buffer.getBuffer(HOOK_RENDER);
            renderPosTexture(hookVertex, hook4f, hook3f, i, 0.0F, 0, 0, 1);
            renderPosTexture(hookVertex, hook4f, hook3f, i, 1.0F, 0, 1, 1);
            renderPosTexture(hookVertex, hook4f, hook3f, i, 1.0F, 1, 1, 0);
            renderPosTexture(hookVertex, hook4f, hook3f, i, 0.0F, 1, 0, 0);
            matrixStack.func_227865_b_();

            int hand = angler.getPrimaryHand() == HandSide.RIGHT ? 1 : -1;
            ItemStack heldMain = angler.getHeldItemMainhand();
            if (!(heldMain.getItem() instanceof FishingRodItem)) {
                hand = -hand;
            }

            float swingProgress = angler.getSwingProgress(partialTicks);
            float swingProgressSqrt = MathHelper.sin(MathHelper.sqrt(swingProgress) * (float) Math.PI);
            float yawOffset = MathHelper.lerp(partialTicks, angler.prevRenderYawOffset, angler.renderYawOffset) * ((float) Math.PI / 180.0F);
            double sin = MathHelper.sin(yawOffset);
            double cos = MathHelper.cos(yawOffset);
            double handOffset = (double) hand * 0.35D;
            double anglerX;
            double anglerY;
            double anglerZ;
            float anglerEye;
            if ((this.renderManager.options == null || this.renderManager.options.thirdPersonView <= 0) && angler == Minecraft.getInstance().player) {
                double fov = Objects.requireNonNull(this.renderManager.options).fov;
                fov /= 100.0D;
                Vec3d rod = new Vec3d((double) hand * -0.36D * fov, -0.045D * fov, 0.4D);
                rod = rod.rotatePitch(-MathHelper.lerp(partialTicks, angler.prevRotationPitch, angler.rotationPitch) * ((float) Math.PI / 180F));
                rod = rod.rotateYaw(-MathHelper.lerp(partialTicks, angler.prevRotationYaw, angler.rotationYaw) * ((float) Math.PI / 180F));
                rod = rod.rotateYaw(swingProgressSqrt * 0.5F);
                rod = rod.rotatePitch(-swingProgressSqrt * 0.7F);
                anglerX = MathHelper.lerp(partialTicks, angler.prevPosX, angler.func_226277_ct_()) + rod.x;
                anglerY = MathHelper.lerp(partialTicks, angler.prevPosY, angler.func_226278_cu_()) + rod.y;
                anglerZ = MathHelper.lerp(partialTicks, angler.prevPosZ, angler.func_226281_cx_()) + rod.z;
                anglerEye = angler.getEyeHeight();
            } else {
                anglerX = MathHelper.lerp(partialTicks, angler.prevPosX, angler.func_226277_ct_()) - cos * handOffset - sin * 0.8D;
                anglerY = angler.prevPosY + (double) angler.getEyeHeight() + (angler.func_226278_cu_() - angler.prevPosY) * (double) partialTicks - 0.45D;
                anglerZ = MathHelper.lerp(partialTicks, angler.prevPosZ, angler.func_226281_cx_()) - sin * handOffset + cos * 0.8D;
                anglerEye = angler.isCrouching() ? -0.1875F : 0.0F;
            }

            double bobberX = MathHelper.lerp(partialTicks, bobber.prevPosX, bobber.func_226277_ct_());
            double bobberY = MathHelper.lerp(partialTicks, bobber.prevPosY, bobber.func_226278_cu_()) + 0.25D;
            double bobberZ = MathHelper.lerp(partialTicks, bobber.prevPosZ, bobber.func_226281_cx_());
            float startX = (float) (anglerX - bobberX);
            float startY = (float) (anglerY - bobberY) + anglerEye;
            float startZ = (float) (anglerZ - bobberZ);
            IVertexBuilder vertexBuilder = buffer.getBuffer(RenderType.func_228659_m_());
            Matrix4f matrix4f1 = matrixStack.func_227866_c_().func_227870_a_();

            //Line color
            ItemStack line = bobber.getFishingLine();
            float r = 0;
            float g = 0;
            float b = 0;
            if (!line.isEmpty()) {
                IDyeableArmorItem lineItem = (IDyeableArmorItem) line.getItem();
                if (lineItem.hasColor(line)) {
                    int colorInt = lineItem.getColor(line);
                    r = (float) (colorInt >> 16 & 255) / 255.0F;
                    g = (float) (colorInt >> 8 & 255) / 255.0F;
                    b = (float) (colorInt & 255) / 255.0F;
                }
            }
            for (int size = 0; size < 16; ++size) {
                renderPosColor(startX, startY, startZ, vertexBuilder, matrix4f1, divide(size, 16), r, g, b);
                renderPosColor(startX, startY, startZ, vertexBuilder, matrix4f1, divide(size + 1, 16), r, g, b);
            }
            matrixStack.func_227865_b_();
            super.func_225623_a_(bobber, entityYaw, partialTicks, matrixStack, buffer, i);
        }
    }

    @Override
    @Nonnull
    public ResourceLocation getEntityTexture(@Nonnull AquaFishingBobberEntity fishHook) {
        return BOBBER_VANILLA;
    }

    private static void renderPosTexture(IVertexBuilder builder, Matrix4f matrix4f, Matrix3f matrix3f, int i, float f, int x, int y, int z) {
        builder.func_227888_a_(matrix4f, f - 0.5F, (float) x - 0.5F, 0.0F).func_225586_a_(255, 255, 255, 255).func_225583_a_((float) y, (float) z).func_227891_b_(OverlayTexture.field_229196_a_).func_227886_a_(i).func_227887_a_(matrix3f, 0.0F, 1.0F, 0.0F).endVertex();
    }

    private static void renderPosColor(float x, float y, float z, IVertexBuilder builder, Matrix4f matrix4f, float f, float r, float g, float b) {
        builder.func_227888_a_(matrix4f, x * f, y * (f * f + f) * 0.5F + 0.25F, z * f).func_227885_a_(r, g, b, 1.0F).endVertex();
    }

    private static float divide(int value1, int value2) {
        return (float) value1 / (float) value2;
    }
}
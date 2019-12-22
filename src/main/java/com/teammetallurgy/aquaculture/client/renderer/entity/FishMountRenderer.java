package com.teammetallurgy.aquaculture.client.renderer.entity;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import com.teammetallurgy.aquaculture.Aquaculture;
import com.teammetallurgy.aquaculture.entity.AquaFishEntity;
import com.teammetallurgy.aquaculture.entity.FishMountEntity;
import com.teammetallurgy.aquaculture.entity.FishType;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.*;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.model.ModelManager;
import net.minecraft.client.renderer.model.ModelResourceLocation;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.fish.PufferfishEntity;
import net.minecraft.inventory.container.PlayerContainer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.Vec3d;

import javax.annotation.Nonnull;
import java.math.BigDecimal;
import java.math.MathContext;
import java.text.DecimalFormat;

public class FishMountRenderer extends EntityRenderer<FishMountEntity> {
    public static final ModelResourceLocation OAK = new ModelResourceLocation(new ResourceLocation(Aquaculture.MOD_ID, "oak_fish_mount"), "");
    public static final ModelResourceLocation SPRUCE = new ModelResourceLocation(new ResourceLocation(Aquaculture.MOD_ID, "spruce_fish_mount"), "");
    public static final ModelResourceLocation BIRCH = new ModelResourceLocation(new ResourceLocation(Aquaculture.MOD_ID, "birch_fish_mount"), "");
    public static final ModelResourceLocation JUNGLE = new ModelResourceLocation(new ResourceLocation(Aquaculture.MOD_ID, "jungle_fish_mount"), "");
    public static final ModelResourceLocation ACACIA = new ModelResourceLocation(new ResourceLocation(Aquaculture.MOD_ID, "acacia_fish_mount"), "");
    public static final ModelResourceLocation DARK_OAK = new ModelResourceLocation(new ResourceLocation(Aquaculture.MOD_ID, "dark_oak_fish_mount"), "");
    private final Minecraft mc = Minecraft.getInstance();

    public FishMountRenderer(EntityRendererManager renderManager) {
        super(renderManager);
    }

    @Override
    public void func_225623_a_(@Nonnull FishMountEntity fishMount, float entityYaw, float partialTicks, @Nonnull MatrixStack matrixStack, @Nonnull IRenderTypeBuffer buffer, int i) {
        super.func_225623_a_(fishMount, entityYaw, partialTicks, matrixStack, buffer, i);
        matrixStack.func_227860_a_();
        Direction direction = fishMount.getHorizontalFacing();
        Vec3d pos = this.func_225627_b_(fishMount, partialTicks);
        matrixStack.func_227861_a_(-pos.getX(), -pos.getY(), -pos.getZ());
        double multiplier = 0.46875D;
        matrixStack.func_227861_a_((double) direction.getXOffset() * multiplier, (double) direction.getYOffset() * multiplier, (double) direction.getZOffset() * multiplier);
        matrixStack.func_227863_a_(Vector3f.field_229179_b_.func_229187_a_(fishMount.rotationPitch));
        matrixStack.func_227863_a_(Vector3f.field_229181_d_.func_229187_a_(180.0F - fishMount.rotationYaw));
        BlockRendererDispatcher rendererDispatcher = this.mc.getBlockRendererDispatcher();
        ModelManager manager = rendererDispatcher.getBlockModelShapes().getModelManager();

        matrixStack.func_227860_a_();
        matrixStack.func_227861_a_(-0.5D, -0.5D, -0.5D);

        if (fishMount.getType().getRegistryName() != null) {
            ModelResourceLocation location = new ModelResourceLocation(fishMount.getType().getRegistryName(), ""); //Calling this instead of the fields for mod support'
            rendererDispatcher.getBlockModelRenderer().func_228804_a_(matrixStack.func_227866_c_(), buffer.getBuffer(Atlases.func_228782_g_()), null, manager.getModel(location), 1.0F, 1.0F, 1.0F, i, OverlayTexture.field_229196_a_);
        }
        matrixStack.func_227865_b_();

        this.renderFish(fishMount, pos, partialTicks, matrixStack, buffer, i);
    }

    private void renderFish(FishMountEntity fishMount, Vec3d pos, float partialTicks, MatrixStack matrixStack, IRenderTypeBuffer buffer, int i) {
        Entity entityFish = fishMount.entity;
        if (entityFish != null) {
            RenderSystem.pushMatrix();
            float depth = entityFish.getCollisionBorderSize();
            if (entityFish instanceof PufferfishEntity) {
                depth += 0.09F;
            } else if (entityFish instanceof AquaFishEntity && AquaFishEntity.TYPES.get(entityFish.getType()).equals(FishType.LONGNOSE)) {
                RenderSystem.translatef(-0.1F, -0.18F, 0);
            }
            RenderSystem.translatef(0, 0, depth);
            RenderSystem.rotatef(-90.0F, 1.0F, 0.0F, 0.0F);
            RenderSystem.rotatef(-90.0F, 0.0F, 1.0F, 0.0F);
            entityFish.setLocationAndAngles(pos.x, pos.y, pos.z, 0.0F, 0.0F);
            this.mc.getRenderManager().func_229084_a_(entityFish, 0.0D, 0.0D, 0.0D, 0.0F, partialTicks, matrixStack, buffer, i);
            RenderSystem.popMatrix();
        }
    }

    @Override
    @Nonnull
    public ResourceLocation getEntityTexture(@Nonnull FishMountEntity fishMount) {
        return PlayerContainer.field_226615_c_;
    }

    @Override
    @Nonnull
    public Vec3d func_225627_b_(FishMountEntity fishMount, float partialTicks) {
        return new Vec3d((float) fishMount.getHorizontalFacing().getXOffset() * 0.3F, -0.25D, (float) fishMount.getHorizontalFacing().getZOffset() * 0.3F);
    }

    @Override
    protected boolean canRenderName(@Nonnull FishMountEntity fishMount) {
        if (Minecraft.isGuiEnabled() && !fishMount.getDisplayedItem().isEmpty() && fishMount.getDisplayedItem().hasDisplayName() && this.renderManager.pointedEntity == fishMount) {
            double d0 = this.renderManager.func_229099_b_(fishMount);
            float sneaking = fishMount.func_226273_bm_() ? 32.0F : 64.0F;
            return d0 < (double) (sneaking * sneaking);
        } else {
            return false;
        }
    }

    @Override
    protected void func_225629_a_(@Nonnull FishMountEntity fishMount, @Nonnull String name, @Nonnull MatrixStack matrixStack, @Nonnull IRenderTypeBuffer buffer, int i) {
        super.func_225629_a_(fishMount, fishMount.entity.getDisplayName().getFormattedText(), matrixStack, buffer, i);

        ItemStack stack = fishMount.getDisplayedItem();
        if (stack.hasTag() && stack.getTag() != null && stack.getTag().contains("fishWeight")) {
            double weight = stack.getTag().getDouble("fishWeight");
            String lb = weight == 1.0D ? " lb" : " lbs";

            DecimalFormat df = new DecimalFormat("#,###.##");
            BigDecimal bd = new BigDecimal(weight);
            bd = bd.round(new MathContext(3));
            if (bd.doubleValue() > 999) {
                this.renderLivingLabel(fishMount, I18n.format("aquaculture.fishWeight.weight", df.format((int) bd.doubleValue()) + lb), matrixStack, buffer, 0, -0.25f, 0, i);
            } else {
                this.renderLivingLabel(fishMount, I18n.format("aquaculture.fishWeight.weight", bd + lb), matrixStack, buffer, 0, 0, -0.25f, i);
            }
        }
    }

    protected void renderLivingLabel(@Nonnull FishMountEntity fishMount, @Nonnull String name, @Nonnull MatrixStack matrixStack, @Nonnull IRenderTypeBuffer buffer, int i, float x, float y, float z) {
        double distance = this.renderManager.func_229099_b_(fishMount);
        if (distance <= 4096.0D) {

            boolean isSneaking = !fishMount.func_226273_bm_();
            float height = fishMount.getHeight() + 0.5F;
            matrixStack.func_227860_a_();
            matrixStack.func_227861_a_(0.0D, height, 0.0D);
            matrixStack.func_227863_a_(this.renderManager.func_229098_b_());
            matrixStack.func_227862_a_(-0.025F + x, -0.025F + y, 0.025F + z);
            Matrix4f matrix4f = matrixStack.func_227866_c_().func_227870_a_();
            float opacitySetting = Minecraft.getInstance().gameSettings.func_216840_a(0.25F);
            int opacity = (int) (opacitySetting * 255.0F) << 24;
            FontRenderer fontRenderer = this.getFontRendererFromRenderManager();
            float stringWidth = (float) (-fontRenderer.getStringWidth(name) / 2);
            fontRenderer.func_228079_a_(name, stringWidth, 0, 553648127, false, matrix4f, buffer, isSneaking, opacity, i);
            if (isSneaking) {
                fontRenderer.func_228079_a_(name, stringWidth, 0, -1, false, matrix4f, buffer, false, 0, i);
            }
            matrixStack.func_227865_b_();
        }
    }
}
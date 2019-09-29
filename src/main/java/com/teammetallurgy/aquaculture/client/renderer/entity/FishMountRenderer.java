package com.teammetallurgy.aquaculture.client.renderer.entity;

import com.mojang.blaze3d.platform.GlStateManager;
import com.teammetallurgy.aquaculture.Aquaculture;
import com.teammetallurgy.aquaculture.entity.FishMountEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockRendererDispatcher;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.model.ModelManager;
import net.minecraft.client.renderer.model.ModelResourceLocation;
import net.minecraft.client.renderer.texture.AtlasTexture;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
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
    public void doRender(FishMountEntity entity, double x, double y, double z, float entityYaw, float partialTicks) {
        GlStateManager.pushMatrix();
        BlockPos pos = entity.getHangingPosition();
        double translateX = (double) pos.getX() - entity.posX + x;
        double translateY = (double) pos.getY() - entity.posY + y;
        double translateZ = (double) pos.getZ() - entity.posZ + z;
        GlStateManager.translated(translateX + 0.5D, translateY + 0.5D, translateZ + 0.5D);
        GlStateManager.rotatef(entity.rotationPitch, 1.0F, 0.0F, 0.0F);
        GlStateManager.rotatef(180.0F - entity.rotationYaw, 0.0F, 1.0F, 0.0F);
        this.renderManager.textureManager.bindTexture(AtlasTexture.LOCATION_BLOCKS_TEXTURE);
        BlockRendererDispatcher rendererDispatcher = this.mc.getBlockRendererDispatcher();
        ModelManager modelmanager = rendererDispatcher.getBlockModelShapes().getModelManager();
        GlStateManager.pushMatrix();
        GlStateManager.translatef(-0.5F, -0.5F, -0.5F);
        if (this.renderOutlines) {
            GlStateManager.enableColorMaterial();
            GlStateManager.setupSolidRenderingTextureCombine(this.getTeamColor(entity));
        }

        if (entity.getType().getRegistryName() != null) {
            ModelResourceLocation location = new ModelResourceLocation(entity.getType().getRegistryName(), ""); //Calling this instead of the fields for mod support
            rendererDispatcher.getBlockModelRenderer().renderModelBrightnessColor(modelmanager.getModel(location), 1.0F, 1.0F, 1.0F, 1.0F);
        }
        if (this.renderOutlines) {
            GlStateManager.tearDownSolidRenderingTextureCombine();
            GlStateManager.disableColorMaterial();
        }

        GlStateManager.popMatrix();
        GlStateManager.enableLighting();
        GlStateManager.translatef(0.0F, 0.0F, 0.4375F);
        this.renderFish(entity, x, y, z);
        GlStateManager.enableLighting();
        GlStateManager.popMatrix();
        if (entity.getDistanceSq(mc.objectMouseOver.getHitVec()) < 0.24D) {
            this.renderName(entity, x + (double) ((float) entity.getHorizontalFacing().getXOffset() * 0.3F), y - 0.25D, z + (double) ((float) entity.getHorizontalFacing().getZOffset() * 0.3F));
        }
    }

    @Override
    @Nullable
    protected ResourceLocation getEntityTexture(@Nonnull FishMountEntity entity) {
        return null;
    }

    private void renderFish(FishMountEntity fishMount, double x, double y, double z) {
        Entity entityFish = fishMount.entity;
        if (entityFish != null) {
            GlStateManager.pushMatrix();
            GlStateManager.translatef(0, 0, -0.8F / 32F);
            GlStateManager.rotatef(-90.0F, 1.0F, 0.0F, 0.0F);
            GlStateManager.rotatef(-90.0F, 0.0F, 1.0F, 0.0F);
            entityFish.setLocationAndAngles(x, y, z, 0.0F, 0.0F);
            this.mc.getRenderManager().renderEntity(entityFish, 0, 0, 0, 0, 0, false);
            GlStateManager.popMatrix();
        }
    }

    protected void renderName(FishMountEntity entity, double x, double y, double z) {
        ItemStack stack = entity.getDisplayedItem();
        if (!stack.isEmpty() && entity.entity != null) {
            double distanceSq = entity.getDistanceSq(this.renderManager.info.getProjectedView());
            float sneaking = entity.shouldRenderSneaking() ? 32.0F : 64.0F;
            if (!(distanceSq >= (double) (sneaking * sneaking))) {
                String name = entity.entity.getDisplayName().getFormattedText();
                this.renderLivingLabel(entity, name, x, y, z, 64);

                if (stack.hasTag() && stack.getTag() != null && stack.getTag().contains("fishWeight")) {
                    double weight = stack.getTag().getDouble("fishWeight");
                    String lb = weight == 1.0D ? " lb" : " lbs";

                    DecimalFormat df = new DecimalFormat("#,###.##");
                    BigDecimal bd = new BigDecimal(weight);
                    bd = bd.round(new MathContext(3));
                    if (bd.doubleValue() > 999) {
                        this.renderLivingLabel(entity, I18n.format("aquaculture.fishWeight.weight", df.format((int) bd.doubleValue()) + lb), x, y - 0.25f, z, 64);
                    } else {
                        this.renderLivingLabel(entity, I18n.format("aquaculture.fishWeight.weight", bd + lb), x, y - 0.25f, z, 64);
                    }
                }
            }
        }
    }
}
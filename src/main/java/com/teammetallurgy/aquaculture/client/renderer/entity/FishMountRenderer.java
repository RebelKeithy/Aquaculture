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
import net.minecraft.entity.EntityType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nullable;
import java.math.BigDecimal;
import java.math.MathContext;
import java.text.DecimalFormat;

public class FishMountRenderer extends EntityRenderer<FishMountEntity> {
    public static final ModelResourceLocation LOCATION_MODEL = new ModelResourceLocation(new ResourceLocation(Aquaculture.MOD_ID, "fish_mount"), "");
    private final Minecraft mc = Minecraft.getInstance();

    public FishMountRenderer(EntityRendererManager renderManager) {
        super(renderManager);
    }

    @Override
    public void doRender(FishMountEntity entity, double x, double y, double z, float entityYaw, float partialTicks) {
        GlStateManager.pushMatrix();
        BlockPos blockpos = entity.getHangingPosition();
        double d0 = (double) blockpos.getX() - entity.posX + x;
        double d1 = (double) blockpos.getY() - entity.posY + y;
        double d2 = (double) blockpos.getZ() - entity.posZ + z;
        GlStateManager.translated(d0 + 0.5D, d1 + 0.5D, d2 + 0.5D);
        GlStateManager.rotatef(entity.rotationPitch, 1.0F, 0.0F, 0.0F);
        GlStateManager.rotatef(180.0F - entity.rotationYaw, 0.0F, 1.0F, 0.0F);
        this.renderManager.textureManager.bindTexture(AtlasTexture.LOCATION_BLOCKS_TEXTURE);
        BlockRendererDispatcher blockrendererdispatcher = this.mc.getBlockRendererDispatcher();
        ModelManager modelmanager = blockrendererdispatcher.getBlockModelShapes().getModelManager();
        GlStateManager.pushMatrix();
        GlStateManager.translatef(-0.5F, -0.5F, -0.5F);
        if (this.renderOutlines) {
            GlStateManager.enableColorMaterial();
            GlStateManager.setupSolidRenderingTextureCombine(this.getTeamColor(entity));
        }

        blockrendererdispatcher.getBlockModelRenderer().renderModelBrightnessColor(modelmanager.getModel(LOCATION_MODEL), 1.0F, 1.0F, 1.0F, 1.0F);
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
        this.renderName(entity, x + (double) ((float) entity.getHorizontalFacing().getXOffset() * 0.3F), y - 0.25D, z + (double) ((float) entity.getHorizontalFacing().getZOffset() * 0.3F));
    }

    @Override
    @Nullable
    protected ResourceLocation getEntityTexture(FishMountEntity entity) {
        return null;
    }

    private void renderFish(FishMountEntity fishMount, double x, double y, double z) {
        Entity entityFish = fishMount.entity;
        if (entityFish != null) {
            GlStateManager.pushMatrix();
            GlStateManager.translatef(0, 0, -2.5f / 32f);
            GlStateManager.rotatef(-90.0F, 1.0F, 0.0F, 0.0F);
            GlStateManager.rotatef(-90.0F, 0.0F, 1.0F, 0.0F);
            entityFish.setLocationAndAngles(x, y, z, 0.0F, 0.0F);
            this.mc.getRenderManager().renderEntity(entityFish, 0, 0, 0, 0, 0, false);
            GlStateManager.popMatrix();
        }
    }

    protected void renderName(FishMountEntity entity, double x, double y, double z) {
        ItemStack stack = entity.getDisplayedItem();
        if (!stack.isEmpty()) {
            double d0 = entity.getDistanceSq(this.renderManager.info.getProjectedView());
            float f = entity.shouldRenderSneaking() ? 32.0F : 64.0F;
            if (!(d0 >= (double) (f * f))) {
                String s = entity.getDisplayedItem().getDisplayName().getFormattedText();
                this.renderLivingLabel(entity, s, x, y, z, 64);

                if (stack.hasTag() && stack.getTag().contains("fishWeight")) {
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

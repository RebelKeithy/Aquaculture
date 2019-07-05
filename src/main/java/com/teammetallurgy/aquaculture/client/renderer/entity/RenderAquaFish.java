package com.teammetallurgy.aquaculture.client.renderer.entity;

import com.mojang.blaze3d.platform.GlStateManager;
import com.teammetallurgy.aquaculture.Aquaculture;
import com.teammetallurgy.aquaculture.client.renderer.entity.model.FishMediumModel;
import com.teammetallurgy.aquaculture.client.renderer.entity.model.FishSmallModel;
import com.teammetallurgy.aquaculture.entity.AquaFishEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.model.*;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class RenderAquaFish extends MobRenderer<AquaFishEntity, EntityModel<AquaFishEntity>> {
    private static final TropicalFishAModel<AquaFishEntity> TROPICAL_FISH_A_MODEL = new TropicalFishAModel<>();
    private static final TropicalFishBModel<AquaFishEntity> TROPICAL_FISH_B_MODEL = new TropicalFishBModel<>();
    private static final CodModel<AquaFishEntity> COD_MODEL = new CodModel<>();
    private static final SalmonModel<AquaFishEntity> SALMON_MODEL = new SalmonModel<>();
    private static final FishSmallModel<AquaFishEntity> SMALL_MODEL = new FishSmallModel<>();
    private static final FishMediumModel<AquaFishEntity> MEDIUM_MODEL = new FishMediumModel<>();

    public RenderAquaFish(EntityRendererManager manager) {
        super(manager, COD_MODEL, 0.3F);
        this.shadowSize = this.entityModel == SALMON_MODEL ? 0.4F : this.entityModel == TROPICAL_FISH_A_MODEL || this.entityModel == SMALL_MODEL ? 0.15F : 0.3F;
    }

    @Override
    public void doRender(@Nonnull AquaFishEntity fishEntity, double x, double y, double z, float entityYaw, float partialTicks) {
        ResourceLocation location = fishEntity.getType().getRegistryName();
        if (location != null) {
            switch (location.getPath()) {
                case "atlantic_herring":
                case "bluegill":
                    this.entityModel = SMALL_MODEL;
                    break;
                case "blackfish":
                case "bass":
                case "brown_trout":
                    this.entityModel = MEDIUM_MODEL;
                    break;
                case "pink_salmon":
                    this.entityModel = SALMON_MODEL;
                    break;
                default:
                    this.entityModel = COD_MODEL;
                    break;
            }
        }
        super.doRender(fishEntity, x, y, z, entityYaw, partialTicks);
    }

    @Override
    @Nullable
    protected ResourceLocation getEntityTexture(@Nonnull AquaFishEntity fishEntity) {
        ResourceLocation location = fishEntity.getType().getRegistryName();
        if (location != null) {
            return new ResourceLocation(Aquaculture.MOD_ID, "textures/entity/fish/" + location.getPath() + ".png");
        }
        return null;
    }

    @Override
    protected void applyRotations(AquaFishEntity fishEntity, float ageInTicks, float rotationYaw, float partialTicks) {
        super.applyRotations(fishEntity, ageInTicks, rotationYaw, partialTicks);
        float salmonRotation = 1.0F;
        float salmonMultiplier = 1.0F;
        if (this.entityModel == SALMON_MODEL) {
            if (!fishEntity.isInWater()) {
                salmonRotation = 1.3F;
                salmonMultiplier = 1.7F;
            }
        }
        float fishRotation = this.entityModel == SALMON_MODEL ? salmonRotation * 4.3F * MathHelper.sin(salmonMultiplier * 0.6F * ageInTicks) : 4.3F * MathHelper.sin(0.6F * ageInTicks);

        GlStateManager.rotatef(fishRotation, 0.0F, 1.0F, 0.0F);
        if (this.entityModel == SALMON_MODEL) {
            GlStateManager.translatef(0.0F, 0.0F, -0.4F);
        }
        if (!fishEntity.isInWater()) {
            if (this.entityModel == COD_MODEL || this.entityModel == MEDIUM_MODEL) {
                GlStateManager.translatef(0.1F, 0.1F, -0.1F);
            } else {
                GlStateManager.translatef(0.2F, 0.1F, 0.0F);
            }
            GlStateManager.rotatef(90.0F, 0.0F, 0.0F, 1.0F);
        }
    }

    @Override
    protected void preRenderCallback(AquaFishEntity fishEntity, float partialTickTime) {
        ResourceLocation location = fishEntity.getType().getRegistryName();
        float scale = 0.0F;
        if (location != null) {
            switch (location.getPath()) {
                case "blackfish":
                    scale = 1.2F;
                    break;
            }
        }
        if (scale > 0) {
            GlStateManager.scalef(scale, scale, scale);
        }
    }
}
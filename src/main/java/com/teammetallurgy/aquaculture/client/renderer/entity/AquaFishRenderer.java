package com.teammetallurgy.aquaculture.client.renderer.entity;

import com.mojang.blaze3d.platform.GlStateManager;
import com.teammetallurgy.aquaculture.Aquaculture;
import com.teammetallurgy.aquaculture.client.renderer.entity.model.*;
import com.teammetallurgy.aquaculture.entity.AquaFishEntity;
import com.teammetallurgy.aquaculture.entity.FishType;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.TropicalFishBModel;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class AquaFishRenderer extends MobRenderer<AquaFishEntity, EntityModel<AquaFishEntity>> {
    private static final TropicalFishBModel<AquaFishEntity> TROPICAL_FISH_B_MODEL = new TropicalFishBModel<>();
    private static final FishSmallModel<AquaFishEntity> SMALL_MODEL = new FishSmallModel<>();
    private static final FishMediumModel<AquaFishEntity> MEDIUM_MODEL = new FishMediumModel<>();
    private static final FishLargeModel<AquaFishEntity> LARGE_MODEL = new FishLargeModel<>();
    private static final FishLongnoseModel<AquaFishEntity> LONGNOSE_MODEL = new FishLongnoseModel<>();
    private static final FishCatchfishModel<AquaFishEntity> CATFISH_MODEL = new FishCatchfishModel<>();
    private static final JellyfishModel<AquaFishEntity> JELLYFISH_MODEL = new JellyfishModel<>();

    public AquaFishRenderer(EntityRendererManager manager) {
        super(manager, MEDIUM_MODEL, 0.3F);
        this.shadowSize = this.entityModel == LONGNOSE_MODEL || this.entityModel == LARGE_MODEL || this.entityModel == CATFISH_MODEL ? 0.4F : this.entityModel == TROPICAL_FISH_B_MODEL || this.entityModel == SMALL_MODEL ? 0.15F : 0.3F;
    }

    @Override
    public void doRender(@Nonnull AquaFishEntity fishEntity, double x, double y, double z, float entityYaw, float partialTicks) {
        switch (AquaFishEntity.SIZES.get(fishEntity.getType())) {
            case SMALL:
                this.entityModel = SMALL_MODEL;
                break;
            case LARGE:
                this.entityModel = LARGE_MODEL;
                break;
            case LONGNOSE:
                this.entityModel = LONGNOSE_MODEL;
                break;
            case CATFISH:
                this.entityModel = CATFISH_MODEL;
                break;
            case JELLYFISH:
                this.entityModel = JELLYFISH_MODEL;
                break;
            case TROPICAL:
                this.entityModel = TROPICAL_FISH_B_MODEL;
                break;
            case MEDIUM:
            default:
                this.entityModel = MEDIUM_MODEL;
                break;
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
        FishType fishType = AquaFishEntity.SIZES.get(fishEntity.getType());
        if (fishType != FishType.JELLYFISH) {
            float salmonRotation = 1.0F;
            float salmonMultiplier = 1.0F;
            if (fishType == FishType.LONGNOSE) {
                if (!fishEntity.isInWater()) {
                    salmonRotation = 1.3F;
                    salmonMultiplier = 1.7F;
                }
            }
            float fishRotation = fishType == FishType.LONGNOSE ? salmonRotation * 4.3F * MathHelper.sin(salmonMultiplier * 0.6F * ageInTicks) : 4.3F * MathHelper.sin(0.6F * ageInTicks);

            GlStateManager.rotatef(fishRotation, 0.0F, 1.0F, 0.0F);
            if (fishType == FishType.LONGNOSE) {
                GlStateManager.translatef(0.0F, 0.0F, -0.4F);
            }
            if (!fishEntity.isInWater()) {
                if (fishType == FishType.MEDIUM || fishType == FishType.LARGE || fishType == FishType.CATFISH) {
                    GlStateManager.translatef(0.1F, 0.1F, -0.1F);
                } else {
                    GlStateManager.translatef(0.2F, 0.1F, 0.0F);
                }
                GlStateManager.rotatef(90.0F, 0.0F, 0.0F, 1.0F);
            }
        }
    }

    @Override
    protected void preRenderCallback(AquaFishEntity fishEntity, float partialTickTime) {
        ResourceLocation location = fishEntity.getType().getRegistryName();
        float scale = 0.0F;
        if (location != null) {
            switch (location.getPath()) {
                case "minnow":
                    scale = 0.5F;
                    break;
                case "synodontis":
                    scale = 0.8F;
                    break;
                case "brown_trout":
                case "piranha":
                    scale = 0.9F;
                    break;
                case "pollock":
                    scale = 1.1F;
                    break;
                case "atlantic_cod":
                case "blackfish":
                case "catfish":
                case "tambaqui":
                    scale = 1.2F;
                    break;
                case "pacific_halibut":
                case "atlantic_halibut":
                case "capitaine":
                case "largemouth_bass":
                case "gar":
                case "arapaima":
                case "tuna":
                    scale = 1.4F;
                    break;
            }
        }
        if (scale > 0) {
            GlStateManager.scalef(scale, scale, scale);
        }
    }
}
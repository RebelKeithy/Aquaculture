package com.teammetallurgy.aquaculture.client.renderer.tileentity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import com.teammetallurgy.aquaculture.Aquaculture;
import com.teammetallurgy.aquaculture.block.TackleBoxBlock;
import com.teammetallurgy.aquaculture.block.blockentity.TackleBoxBlockEntity;
import com.teammetallurgy.aquaculture.client.ClientHandler;
import com.teammetallurgy.aquaculture.init.AquaBlocks;
import it.unimi.dsi.fastutil.ints.Int2IntFunction;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.blockentity.BrightnessCombiner;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.ChestBlock;
import net.minecraft.world.level.block.DoubleBlockCombiner;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nonnull;

public class TackleBoxRenderer <T extends TackleBoxBlockEntity> implements BlockEntityRenderer<T> {
    private static final ResourceLocation TACKLE_BOX_TEXTURE = new ResourceLocation(Aquaculture.MOD_ID, "textures/entity/tileentity/tackle_box.png");
    private static final RenderType TACKLE_BOX_RENDER = RenderType.entityCutout(TACKLE_BOX_TEXTURE);
    private final ModelPart base;
    private final ModelPart lid;

    public TackleBoxRenderer(BlockEntityRendererProvider.Context context) {
        ModelPart part = context.bakeLayer(ClientHandler.TACKLE_BOX);
        this.base = part.getChild("base");
        this.lid = part.getChild("lid");
    }

    public static LayerDefinition createLayer() {
        MeshDefinition meshDefinition = new MeshDefinition();
        PartDefinition partDefinition = meshDefinition.getRoot();
        PartDefinition lid = partDefinition.addOrReplaceChild("lid", CubeListBuilder.create().texOffs(0, 0).addBox(-7.0F, -3.0F, -8.0F, 14, 3, 8), PartPose.offset(7.0F, 12.0F, 4.0F));

        partDefinition.addOrReplaceChild("base", CubeListBuilder.create().texOffs(0, 11).addBox(0.0F, -6.0F, -8.0F, 14, 6, 8), PartPose.offset(0.0F, 18.0F, 4.0F));
        lid.addOrReplaceChild("handle", CubeListBuilder.create().texOffs(36, 0).addBox(-2.0F, -4.0F, -5.0F, 4, 1, 2), PartPose.offset(0.0F, 0.0F, 0.0F));
        return LayerDefinition.create(meshDefinition, 64, 32);
    }

    @Override
    public void render(@Nonnull T tackleBox, float partialTicks, @Nonnull PoseStack matrixStack, @Nonnull MultiBufferSource buffer, int combinedLight, int combinedOverlay) {
        Level world = tackleBox.getLevel();
        boolean hasWorld = world != null;
        BlockState state = hasWorld ? tackleBox.getBlockState() : AquaBlocks.TACKLE_BOX.get().defaultBlockState().setValue(ChestBlock.FACING, Direction.SOUTH);
        Block block = state.getBlock();
        if (block instanceof TackleBoxBlock) {
            matrixStack.pushPose();
            matrixStack.translate(0.5D, 0.5D, 0.5D);
            float facing = state.getValue(TackleBoxBlock.FACING).toYRot();
            matrixStack.mulPose(Axis.YP.rotationDegrees(-facing));
            matrixStack.translate(-0.5D, -0.5D, -0.5D);
            matrixStack.translate(0.0625F, 1.125F, 0.5F); //Translate
            matrixStack.mulPose(Axis.XN.rotationDegrees(-180)); //Flip

            DoubleBlockCombiner.NeighborCombineResult<?> callbackWrapper = DoubleBlockCombiner.Combiner::acceptNone;
            float angle = tackleBox.getOpenNess(partialTicks);
            angle = 1.0F - angle;
            angle = 1.0F - angle * angle * angle;
            int brightness = ((Int2IntFunction) callbackWrapper.apply(new BrightnessCombiner())).applyAsInt(combinedLight);
            VertexConsumer tackleBoxBuilder = buffer.getBuffer(TACKLE_BOX_RENDER);
            this.render(matrixStack, tackleBoxBuilder, this.base, this.lid, angle, brightness, combinedOverlay);
            matrixStack.popPose();
        }
    }

    private void render(PoseStack matrixStack, VertexConsumer builder, ModelPart base, ModelPart lid, float angle, int brightness, int combinedOverlay) {
        lid.xRot = -(angle * 1.5707964F);
        base.render(matrixStack, builder, brightness, combinedOverlay);
        lid.render(matrixStack, builder, brightness, combinedOverlay);
        lid.render(matrixStack, builder, brightness, combinedOverlay);
    }
}
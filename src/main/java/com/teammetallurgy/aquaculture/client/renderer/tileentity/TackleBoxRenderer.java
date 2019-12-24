package com.teammetallurgy.aquaculture.client.renderer.tileentity;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.teammetallurgy.aquaculture.Aquaculture;
import com.teammetallurgy.aquaculture.block.TackleBoxBlock;
import com.teammetallurgy.aquaculture.block.tileentity.TackleBoxTileEntity;
import com.teammetallurgy.aquaculture.init.AquaBlocks;
import it.unimi.dsi.fastutil.ints.Int2IntFunction;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ChestBlock;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.Vector3f;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.client.renderer.tileentity.DualBrightnessCallback;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.tileentity.IChestLid;
import net.minecraft.tileentity.TileEntityMerger;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public class TackleBoxRenderer <T extends TackleBoxTileEntity & IChestLid> extends TileEntityRenderer<T> {
    private static final ResourceLocation TACKLE_BOX_TEXTURE = new ResourceLocation(Aquaculture.MOD_ID, "textures/entity/tileentity/tackle_box.png");
    private static final RenderType TACKLE_BOX_RENDER = RenderType.func_228638_b_(TACKLE_BOX_TEXTURE);
    private ModelRenderer base;
    private ModelRenderer lid;
    private ModelRenderer handle;

    public TackleBoxRenderer(TileEntityRendererDispatcher dispatcher) {
        super(dispatcher);
        this.lid = new ModelRenderer(64, 32, 0, 0);
        this.lid.setRotationPoint(7.0F, 12.0F, 4.0F);
        this.lid.func_228301_a_(-7.0F, -3.0F, -8.0F, 14, 3, 8, 0.0F);
        this.base = new ModelRenderer(64, 32, 0, 11);
        this.base.setRotationPoint(0.0F, 18.0F, 4.0F);
        this.base.func_228301_a_(0.0F, -6.0F, -8.0F, 14, 6, 8, 0.0F);
        this.handle = new ModelRenderer(64, 32, 36, 0);
        this.handle.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.handle.func_228301_a_(-2.0F, -4.0F, -5.0F, 4, 1, 2, 0.0F);
        this.lid.addChild(this.handle);
    }

    @Override
    public void func_225616_a_(@Nonnull T tackleBox, float partialTicks, @Nonnull MatrixStack matrixStack, @Nonnull IRenderTypeBuffer buffer, int i, int i1) {
        World world = tackleBox.getWorld();
        boolean hasWorld = world != null;
        BlockState state = hasWorld ? tackleBox.getBlockState() : AquaBlocks.TACKLE_BOX.getDefaultState().with(ChestBlock.FACING, Direction.SOUTH);
        Block block = state.getBlock();
        if (block instanceof TackleBoxBlock) {
            matrixStack.func_227860_a_();
            float lvt_14_1_ = state.get(TackleBoxBlock.FACING).getHorizontalAngle();
            matrixStack.func_227861_a_(0.5D, 0.5D, 0.5D);
            matrixStack.func_227863_a_(Vector3f.field_229181_d_.func_229187_a_(-lvt_14_1_));
            matrixStack.func_227861_a_(-0.5D, -0.5D, -0.5D);
            matrixStack.func_227861_a_(0.0625F, 1.125F, 0.5F); //Translate
            matrixStack.func_227863_a_(Vector3f.field_229178_a_.func_229187_a_(-180)); //Flip
            TileEntityMerger.ICallbackWrapper<?> lvt_15_2_ = TileEntityMerger.ICallback::func_225537_b_;
            float angle = tackleBox.getLidAngle(partialTicks);
            angle = 1.0F - angle;
            angle = 1.0F - angle * angle * angle;
            int lvt_17_1_ = ((Int2IntFunction) lvt_15_2_.apply(new DualBrightnessCallback())).applyAsInt(i);
            IVertexBuilder tackleBoxBuilder = buffer.getBuffer(TACKLE_BOX_RENDER);
            this.func_228871_a_(matrixStack, tackleBoxBuilder, this.base, this.lid, angle, lvt_17_1_, i1);
            matrixStack.func_227865_b_();
        }
    }

    private void func_228871_a_(MatrixStack p_228871_1_, IVertexBuilder p_228871_2_, ModelRenderer base, ModelRenderer lid, float p_228871_6_, int p_228871_7_, int p_228871_8_) {
        lid.rotateAngleX = -(p_228871_6_ * 1.5707964F);
        base.func_228308_a_(p_228871_1_, p_228871_2_, p_228871_7_, p_228871_8_);
        lid.func_228308_a_(p_228871_1_, p_228871_2_, p_228871_7_, p_228871_8_);
    }
}
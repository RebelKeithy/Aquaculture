package com.teammetallurgy.aquaculture.client.gui.screen;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import com.teammetallurgy.aquaculture.Aquaculture;
import com.teammetallurgy.aquaculture.inventory.container.TackleBoxContainer;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

import javax.annotation.Nonnull;

public class TackleBoxScreen extends ContainerScreen<TackleBoxContainer> {
    private static final ResourceLocation TACKLE_BOX_GUI = new ResourceLocation(Aquaculture.MOD_ID, "textures/gui/container/tackle_box.png");

    public TackleBoxScreen(TackleBoxContainer tackleBoxContainer, PlayerInventory playerInventory, ITextComponent title) {
        super(tackleBoxContainer, playerInventory, title);
        this.ySize = 172;
    }

    @Override
    public void func_230430_a_(@Nonnull MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        this.func_230446_a_(matrixStack);
        super.func_230430_a_(matrixStack, mouseX, mouseY, partialTicks);
        this.func_230459_a_(matrixStack, mouseX, mouseY);
    }

    @Override
    protected void func_230451_b_(@Nonnull MatrixStack matrixStack, int mouseX, int mouseY) {
        this.field_230712_o_.func_238407_a_(matrixStack, this.field_230704_d_, 100.0F, 6.0F, 4210752);
        this.field_230712_o_.func_238407_a_(matrixStack, this.playerInventory.getDisplayName(), 8.0F, (float) (this.ySize - 96 + 4), 4210752);
    }

    @Override
    protected void func_230450_a_(@Nonnull MatrixStack matrixStack, float partialTicks, int mouseX, int mouseY) {
        RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        if (this.field_230706_i_ != null) {
            this.field_230706_i_.getTextureManager().bindTexture(TACKLE_BOX_GUI);
        }
        int x = (this.field_230708_k_ - this.xSize) / 2;
        int y = (this.field_230709_l_ - this.ySize) / 2;
        this.func_238474_b_(matrixStack, x, y, 0, 0, this.xSize, this.ySize);

        if (this.container.slotHook.isEnabled()) { //Only checking one slot, since they're all disabled at the same time
            if (this.container.slotHook.getHasStack()) {
                this.renderEmptySlot(matrixStack, x + 105, y + 43);
            } else {
                this.func_238474_b_(matrixStack, x + 105, y + 43, 176, 0, 18, 18);
            }
            if (this.container.slotBait.getHasStack()) {
                this.renderEmptySlot(matrixStack, x + 128, y + 43);
            } else {
                this.func_238474_b_(matrixStack, x + 128, y + 43, 176, 18, 18, 18);
            }
            if (this.container.slotLine.getHasStack()) {
                this.renderEmptySlot(matrixStack, x + 105, y + 66);
            } else {
                this.func_238474_b_(matrixStack, x + 105, y + 66, 176, 36, 18, 18);
            }
            if (this.container.slotBobber.getHasStack()) {
                this.renderEmptySlot(matrixStack, x + 128, y + 66);
            } else {
                this.func_238474_b_(matrixStack, x + 128, y + 66, 176, 54, 18, 18);
            }
        }
    }

    private void renderEmptySlot(@Nonnull MatrixStack matrixStack, int x, int y) {
        this.func_238474_b_(matrixStack, x, y, 7, 7, 18, 18);
    }
}
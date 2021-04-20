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
    public void render(@Nonnull MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(matrixStack);
        super.render(matrixStack, mouseX, mouseY, partialTicks);
        this.renderHoveredTooltip(matrixStack, mouseX, mouseY);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(@Nonnull MatrixStack matrixStack, int mouseX, int mouseY) {
        this.font.func_243248_b(matrixStack, this.title, 100.0F, 6.0F, 4210752);
        this.font.func_243248_b(matrixStack, this.playerInventory.getDisplayName(), 8.0F, (float) (this.ySize - 96 + 4), 4210752);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(@Nonnull MatrixStack matrixStack, float partialTicks, int mouseX, int mouseY) {
        RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        if (this.minecraft != null) {
            this.minecraft.getTextureManager().bindTexture(TACKLE_BOX_GUI);
        }
        int x = (this.width - this.xSize) / 2;
        int y = (this.height - this.ySize) / 2;
        this.blit(matrixStack, x, y, 0, 0, this.xSize, this.ySize);

        if (this.container.slotHook.isEnabled()) { //Only checking one slot, since they're all disabled at the same time
            if (this.container.slotHook.getHasStack()) {
                this.renderEmptySlot(matrixStack, x + 105, y + 43);
            } else {
                this.blit(matrixStack, x + 105, y + 43, 176, 0, 18, 18);
            }
            if (this.container.slotBait.getHasStack()) {
                this.renderEmptySlot(matrixStack, x + 128, y + 43);
            } else {
                this.blit(matrixStack, x + 128, y + 43, 176, 18, 18, 18);
            }
            if (this.container.slotLine.getHasStack()) {
                this.renderEmptySlot(matrixStack, x + 105, y + 66);
            } else {
                this.blit(matrixStack, x + 105, y + 66, 176, 36, 18, 18);
            }
            if (this.container.slotBobber.getHasStack()) {
                this.renderEmptySlot(matrixStack, x + 128, y + 66);
            } else {
                this.blit(matrixStack, x + 128, y + 66, 176, 54, 18, 18);
            }
        }
    }

    private void renderEmptySlot(@Nonnull MatrixStack matrixStack, int x, int y) {
        this.blit(matrixStack, x, y, 7, 7, 18, 18);
    }
}
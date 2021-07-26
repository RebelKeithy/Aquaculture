package com.teammetallurgy.aquaculture.client.gui.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.teammetallurgy.aquaculture.Aquaculture;
import com.teammetallurgy.aquaculture.inventory.container.TackleBoxContainer;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

import javax.annotation.Nonnull;

public class TackleBoxScreen extends AbstractContainerScreen<TackleBoxContainer> {
    private static final ResourceLocation TACKLE_BOX_GUI = new ResourceLocation(Aquaculture.MOD_ID, "textures/gui/container/tackle_box.png");

    public TackleBoxScreen(TackleBoxContainer tackleBoxContainer, Inventory playerInventory, Component title) {
        super(tackleBoxContainer, playerInventory, title);
        this.imageHeight = 172;
    }

    @Override
    public void render(@Nonnull PoseStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(matrixStack);
        super.render(matrixStack, mouseX, mouseY, partialTicks);
        this.renderTooltip(matrixStack, mouseX, mouseY);
    }

    @Override
    protected void renderLabels(@Nonnull PoseStack matrixStack, int mouseX, int mouseY) {
        this.font.draw(matrixStack, this.title, 100.0F, 6.0F, 4210752);
        this.font.draw(matrixStack, this.playerInventoryTitle, 8.0F, (float) (this.imageHeight - 96 + 4), 4210752);
    }

    @Override
    protected void renderBg(@Nonnull PoseStack matrixStack, float partialTicks, int mouseX, int mouseY) {
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, TACKLE_BOX_GUI);

        int x = (this.width - this.imageWidth) / 2;
        int y = (this.height - this.imageHeight) / 2;
        this.blit(matrixStack, x, y, 0, 0, this.imageWidth, this.imageHeight);

        if (this.menu.slotHook.isActive()) { //Only checking one slot, since they're all disabled at the same time
            if (this.menu.slotHook.hasItem()) {
                this.renderEmptySlot(matrixStack, x + 105, y + 43);
            } else {
                this.blit(matrixStack, x + 105, y + 43, 176, 0, 18, 18);
            }
            if (this.menu.slotBait.hasItem()) {
                this.renderEmptySlot(matrixStack, x + 128, y + 43);
            } else {
                this.blit(matrixStack, x + 128, y + 43, 176, 18, 18, 18);
            }
            if (this.menu.slotLine.hasItem()) {
                this.renderEmptySlot(matrixStack, x + 105, y + 66);
            } else {
                this.blit(matrixStack, x + 105, y + 66, 176, 36, 18, 18);
            }
            if (this.menu.slotBobber.hasItem()) {
                this.renderEmptySlot(matrixStack, x + 128, y + 66);
            } else {
                this.blit(matrixStack, x + 128, y + 66, 176, 54, 18, 18);
            }
        }
    }

    private void renderEmptySlot(@Nonnull PoseStack matrixStack, int x, int y) {
        this.blit(matrixStack, x, y, 7, 7, 18, 18);
    }
}
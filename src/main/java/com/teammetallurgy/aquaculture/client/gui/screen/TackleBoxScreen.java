package com.teammetallurgy.aquaculture.client.gui.screen;

import com.mojang.blaze3d.platform.GlStateManager;
import com.teammetallurgy.aquaculture.Aquaculture;
import com.teammetallurgy.aquaculture.inventory.container.TackleBoxContainer;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

public class TackleBoxScreen extends ContainerScreen<TackleBoxContainer> {
    private static final ResourceLocation TACKLE_BOX_GUI = new ResourceLocation(Aquaculture.MOD_ID, "textures/gui/container/tackle_box.png");

    public TackleBoxScreen(TackleBoxContainer tackleBoxContainer, PlayerInventory playerInventory, ITextComponent title) {
        super(tackleBoxContainer, playerInventory, title);
        this.ySize = 172;
    }

    @Override
    public void render(int mouseX, int mouseY, float partialTicks) {
        this.renderBackground();
        super.render(mouseX, mouseY, partialTicks);
        this.renderHoveredToolTip(mouseX, mouseY);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        this.font.drawString(this.title.getFormattedText(), 100.0F, 6.0F, 4210752);
        this.font.drawString(this.playerInventory.getDisplayName().getFormattedText(), 8.0F, (float) (this.ySize - 96 + 4), 4210752);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        if (this.minecraft != null) {
            this.minecraft.getTextureManager().bindTexture(TACKLE_BOX_GUI);
        }
        int x = (this.width - this.xSize) / 2;
        int y = (this.height - this.ySize) / 2;
        this.blit(x, y, 0, 0, this.xSize, this.ySize);

        IItemHandler tackleBoxInventory = this.container.tackleBox.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).orElseGet(null);
        ItemStack fishingRod = tackleBoxInventory.getStackInSlot(0);
        fishingRod.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(rodHandler -> {
            if (rodHandler.getStackInSlot(0).isEmpty()) {
                this.blit(x + 95, y + 43, 176, 0, 18, 18);
            } else {
                this.renderEmptySlot(x + 95, y + 43);
            }
            if (rodHandler.getStackInSlot(1).isEmpty()) {
                this.blit(x + 116, y + 43, 176, 18, 18, 18);
            } else {
                this.renderEmptySlot(x + 116, y + 43);
            }
            if (rodHandler.getStackInSlot(2).isEmpty()) {
                this.blit(x + 137, y + 43, 176, 36, 18, 18);
            } else {
                this.renderEmptySlot(x + 137, y + 43);
            }
        });
    }

    private void renderEmptySlot(int x, int y) {
        this.blit(x, y, 7, 7, 18, 18);
    }
}
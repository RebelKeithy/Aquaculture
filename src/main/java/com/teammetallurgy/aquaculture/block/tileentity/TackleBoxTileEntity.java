package com.teammetallurgy.aquaculture.block.tileentity;

import com.teammetallurgy.aquaculture.init.AquaBlocks;
import com.teammetallurgy.aquaculture.inventory.container.TackleBoxContainer;
import com.teammetallurgy.aquaculture.misc.AquacultureSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.ChestLidController;
import net.minecraft.world.level.block.entity.ContainerOpenersCounter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class TackleBoxTileEntity extends IItemHandlerTEBase implements MenuProvider {
    private final ContainerOpenersCounter openersCounter = new ContainerOpenersCounter() {
        @Override
        protected void onOpen(@Nonnull Level level, @Nonnull BlockPos pos, @Nonnull BlockState state) {
            TackleBoxTileEntity.playSound(level, pos, state, AquacultureSounds.TACKLE_BOX_OPEN);
        }

        @Override
        protected void onClose(@Nonnull Level level, @Nonnull BlockPos pos, @Nonnull BlockState state) {
            TackleBoxTileEntity.playSound(level, pos, state, AquacultureSounds.TACKLE_BOX_CLOSE);
        }

        @Override
        protected void openerCountChanged(@Nonnull Level level, @Nonnull BlockPos pos, @Nonnull BlockState state, int i, int i1) {
            TackleBoxTileEntity.this.signalOpenCount(level, pos, state, i, i1);
        }

        @Override
        protected boolean isOwnContainer(Player player) {
            return player.containerMenu instanceof TackleBoxContainer;
        }
    };
    private final ChestLidController lidController = new ChestLidController();

    public TackleBoxTileEntity(BlockPos pos, BlockState state) {
        super(AquaBlocks.AquaTileEntities.TACKLE_BOX, pos, state);
    }

    @Override
    @Nonnull
    protected IItemHandler createItemHandler() {
        return new ItemStackHandler(17);
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int windowID, @Nonnull Inventory playerInventory, @Nonnull Player player) {
        return new TackleBoxContainer(windowID, worldPosition, playerInventory);
    }

    public static void lidAnimateTick(Level level, BlockPos pos, BlockState state, TackleBoxTileEntity tackleBox) {
        tackleBox.lidController.tickLid();
    }

    protected void signalOpenCount(Level level, BlockPos pos, BlockState state, int i, int i1) {
        Block block = state.getBlock();
        level.blockEvent(pos, block, 1, i1);
    }

    static void playSound(Level level, BlockPos pos, BlockState state, SoundEvent sound) {
        if (level != null) {
            level.playSound(null, pos.getX() + 0.5D, pos.getY() + 0.5D, pos.getZ() + 0.5D, sound, SoundSource.BLOCKS, 0.5F, level.random.nextFloat() * 0.1F + 0.9F);
        }
    }

    @Override
    public boolean triggerEvent(int p_59114_, int p_59115_) {
        if (p_59114_ == 1) {
            this.lidController.shouldBeOpen(p_59115_ > 0);
            return true;
        } else {
            return super.triggerEvent(p_59114_, p_59115_);
        }
    }

    public void startOpen(Player player) {
        if (!this.remove && !player.isSpectator()) {
            this.openersCounter.incrementOpeners(player, this.getLevel(), this.getBlockPos(), this.getBlockState());
        }

    }

    public void stopOpen(Player player) {
        if (!this.remove && !player.isSpectator()) {
            this.openersCounter.decrementOpeners(player, this.getLevel(), this.getBlockPos(), this.getBlockState());
        }
    }

    public float getOpenNess(float partialTicks) {
        return this.lidController.getOpenness(partialTicks);
    }

    public static int getOpenCount(BlockGetter blockGetter, BlockPos pos) {
        BlockState blockstate = blockGetter.getBlockState(pos);
        if (blockstate.hasBlockEntity()) {
            BlockEntity blockentity = blockGetter.getBlockEntity(pos);
            if (blockentity instanceof TackleBoxTileEntity) {
                return ((TackleBoxTileEntity) blockentity).openersCounter.getOpenerCount();
            }
        }
        return 0;
    }

    public void recheckOpen() {
        if (!this.remove) {
            this.openersCounter.recheckOpeners(this.getLevel(), this.getBlockPos(), this.getBlockState());
        }
    }
}
package com.teammetallurgy.aquaculture.entity;

import com.teammetallurgy.aquaculture.api.AquacultureAPI;
import com.teammetallurgy.aquaculture.init.AquaEntities;
import com.teammetallurgy.aquaculture.init.AquaItems;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.RedstoneDiodeBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Pose;
import net.minecraft.entity.item.HangingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.FMLPlayMessages;
import net.minecraftforge.fml.network.NetworkHooks;
import net.minecraftforge.registries.ForgeRegistries;
import org.apache.commons.lang3.Validate;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.Nullable;

public class FishMountEntity extends HangingEntity {
    private static final Logger PRIVATE_LOGGER = LogManager.getLogger();
    private static final DataParameter<ItemStack> ITEM = EntityDataManager.createKey(FishMountEntity.class, DataSerializers.ITEMSTACK);
    private float itemDropChance = 1.0F;
    public Entity entity;

    public FishMountEntity(EntityType<? extends FishMountEntity> type, World world) {
        super(type, world);
    }

    public FishMountEntity(World worldIn, BlockPos blockPos, Direction direction) {
        super(AquaEntities.FISH_MOUNT, worldIn, blockPos);
        this.updateFacingWithBoundingBox(direction);
    }

    public FishMountEntity(FMLPlayMessages.SpawnEntity spawnEntity, World world) {
        this(AquaEntities.FISH_MOUNT, world);
    }


    @Override
    protected float getEyeHeight(Pose poseIn, EntitySize sizeIn) {
        return 0.0F;
    }

    @Override
    protected void registerData() {
        this.getDataManager().register(ITEM, ItemStack.EMPTY);
    }

    @Override
    protected void updateFacingWithBoundingBox(Direction facingDirectionIn) {
        Validate.notNull(facingDirectionIn);
        this.facingDirection = facingDirectionIn;
        if (facingDirectionIn.getAxis().isHorizontal()) {
            this.rotationPitch = 0.0F;
            this.rotationYaw = (float) (this.facingDirection.getHorizontalIndex() * 90);
        } else {
            this.rotationPitch = (float) (-90 * facingDirectionIn.getAxisDirection().getOffset());
            this.rotationYaw = 0.0F;
        }

        this.prevRotationPitch = this.rotationPitch;
        this.prevRotationYaw = this.rotationYaw;
        this.updateBoundingBox();
    }

    @Override
    public boolean onValidSurface() {
        if (!this.world.areCollisionShapesEmpty(this)) {
            return false;
        } else {
            BlockState blockstate = this.world.getBlockState(this.hangingPosition.offset(this.facingDirection.getOpposite()));
            return blockstate.getMaterial().isSolid() || this.facingDirection.getAxis().isHorizontal() && RedstoneDiodeBlock.isDiode(blockstate) ? this.world.getEntitiesInAABBexcluding(this, this.getBoundingBox(), IS_HANGING_ENTITY).isEmpty() : false;
        }
    }

    @Override
    protected void updateBoundingBox() {
        if (this.facingDirection != null) {
            double d0 = 0.46875D;
            this.posX = (double) this.hangingPosition.getX() + 0.5D - (double) this.facingDirection.getXOffset() * d0;
            this.posY = (double) this.hangingPosition.getY() + 0.5D - (double) this.facingDirection.getYOffset() * d0;
            this.posZ = (double) this.hangingPosition.getZ() + 0.5D - (double) this.facingDirection.getZOffset() * d0;
            double x1 = this.getWidthPixels() / 32.0D;
            double x2 = this.getWidthPixels() / 32.0D;
            double y1 = this.getHeightPixels() / 32.0D;
            double y2 = this.getHeightPixels() / 32.0D;
            double z1 = this.getWidthPixels() / 32.0D;
            double z2 = this.getWidthPixels() / 32.0D;
            switch (this.facingDirection.getAxis()) {
                case X:
                    x1 = (this.facingDirection.getXOffset() < 0 ? 3.0D : 1.0D) / 32.0D;
                    x2 = (this.facingDirection.getXOffset() > 0 ? 3.0D : 1.0D) / 32.0D;
                    break;
                case Y:
                    y1 = (this.facingDirection.getYOffset() < 0 ? 3.0D : 1.0D) / 32.0D;
                    y2 = (this.facingDirection.getYOffset() > 0 ? 3.0D : 1.0D) / 32.0D;
                    z1 = 8.0D / 32.0D;
                    z2 = 8.0D / 32.0D;
                    break;
                case Z:
                    z1 = (this.facingDirection.getZOffset() < 0 ? 3.0D : 1.0D) / 32.0D;
                    z2 = (this.facingDirection.getZOffset() > 0 ? 3.0D : 1.0D) / 32.0D;
            }

            this.setBoundingBox(new AxisAlignedBB(this.posX - x1, this.posY - y1, this.posZ - z1, this.posX + x2, this.posY + y2, this.posZ + z2));
        }
    }

    /**
     * Called by the /kill command.
     */
    @Override
    public void onKillCommand() {
        this.removeItem(this.getDisplayedItem());
        super.onKillCommand();
    }

    /**
     * Called when the entity is attacked.
     */
    @Override
    public boolean attackEntityFrom(DamageSource source, float amount) {
        if (this.isInvulnerableTo(source)) {
            return false;
        } else if (!source.isExplosion() && !this.getDisplayedItem().isEmpty()) {
            if (!this.world.isRemote) {
                this.dropItemOrSelf(source.getTrueSource(), false);
                this.playSound(SoundEvents.ENTITY_ITEM_FRAME_REMOVE_ITEM, 1.0F, 1.0F);
            }

            return true;
        } else {
            return super.attackEntityFrom(source, amount);
        }
    }

    @Override
    public int getWidthPixels() {
        return 12;
    }

    @Override
    public int getHeightPixels() {
        return 8;
    }

    /**
     * Checks if the entity is in range to render.
     */
    @Override
    @OnlyIn(Dist.CLIENT)
    public boolean isInRangeToRenderDist(double distance) {
        double d0 = 16.0D;
        d0 = d0 * 64.0D * getRenderDistanceWeight();
        return distance < d0 * d0;
    }

    /**
     * Called when this entity is broken. Entity parameter may be null.
     */
    @Override
    public void onBroken(@Nullable Entity brokenEntity) {
        this.playSound(SoundEvents.ENTITY_ITEM_FRAME_BREAK, 1.0F, 1.0F);
        this.dropItemOrSelf(brokenEntity, true);
    }

    @Override
    public void playPlaceSound() {
        this.playSound(SoundEvents.ENTITY_ITEM_FRAME_PLACE, 1.0F, 1.0F);
    }

    private void dropItemOrSelf(@Nullable Entity entityIn, boolean p_146065_2_) {
        if (!this.world.getGameRules().getBoolean(GameRules.DO_ENTITY_DROPS)) {
            if (entityIn == null) {
                this.removeItem(this.getDisplayedItem());
            }

        } else {
            ItemStack itemstack = this.getDisplayedItem();
            this.setDisplayedItem(ItemStack.EMPTY);
            if (entityIn instanceof PlayerEntity) {
                PlayerEntity playerentity = (PlayerEntity) entityIn;
                if (playerentity.abilities.isCreativeMode) {
                    this.removeItem(itemstack);
                    return;
                }
            }

            if (p_146065_2_) {
                this.entityDropItem(AquaItems.FISH_MOUNT);
            }

            if (!itemstack.isEmpty()) {
                itemstack = itemstack.copy();
                this.removeItem(itemstack);
                if (this.rand.nextFloat() < this.itemDropChance) {
                    this.entityDropItem(itemstack);
                }
            }

        }
    }

    /**
     * Removes the dot representing this frame's position from the map when the item frame is broken.
     */
    private void removeItem(ItemStack stack) {
//        stack.setItemFrame((ItemFrameEntity) null);
    }

    public ItemStack getDisplayedItem() {
        return this.getDataManager().get(ITEM);
    }

    public void setDisplayedItem(ItemStack stack) {
        this.setDisplayedItemWithUpdate(stack, true);
    }

    public void setDisplayedItemWithUpdate(ItemStack stack, boolean p_174864_2_) {
        if (!stack.isEmpty()) {
            stack = stack.copy();
            stack.setCount(1);
//            stack.setItemFrame(this);
        }

        this.getDataManager().set(ITEM, stack);
        if (!stack.isEmpty()) {
            this.playSound(SoundEvents.ENTITY_ITEM_FRAME_ADD_ITEM, 1.0F, 1.0F);
        }

        if (p_174864_2_ && this.hangingPosition != null) {
            this.world.updateComparatorOutputLevel(this.hangingPosition, Blocks.AIR);
        }

    }

    @Override
    public boolean replaceItemInInventory(int inventorySlot, ItemStack itemStackIn) {
        if (inventorySlot == 0) {
            this.setDisplayedItem(itemStackIn);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void notifyDataManagerChange(DataParameter<?> key) {
        if (key.equals(ITEM)) {
            ItemStack itemstack = this.getDisplayedItem();
            if(itemstack != null && !itemstack.isEmpty()) {
                EntityType entityType = ForgeRegistries.ENTITIES.getValue(itemstack.getItem().getRegistryName());
                if (entityType != null) {
                    this.entity = entityType.create(this.world);
                }
            } else {
                this.entity = null;
            }
        }
    }

    @Override
    public void read(CompoundNBT compound) {
        super.read(compound);
    }

    @Override
    public void writeAdditional(CompoundNBT compound) {
        super.writeAdditional(compound);
        if (!this.getDisplayedItem().isEmpty()) {
            compound.put("Item", this.getDisplayedItem().write(new CompoundNBT()));
            compound.putFloat("ItemDropChance", this.itemDropChance);
        }

        compound.putByte("Facing", (byte) this.facingDirection.getIndex());
    }

    @Override
    public void readAdditional(CompoundNBT compound) {
        super.readAdditional(compound);
        CompoundNBT compoundnbt = compound.getCompound("Item");
        if (compoundnbt != null && !compoundnbt.isEmpty()) {
            ItemStack itemstack = ItemStack.read(compoundnbt);
            if (itemstack.isEmpty()) {
                PRIVATE_LOGGER.warn("Unable to load item from: {}", (Object) compoundnbt);
            }

            ItemStack itemstack1 = this.getDisplayedItem();
            if (!itemstack1.isEmpty() && !ItemStack.areItemStacksEqual(itemstack, itemstack1)) {
                this.removeItem(itemstack1);
            }

            this.setDisplayedItemWithUpdate(itemstack, false);
            if (compound.contains("ItemDropChance", 99)) {
                this.itemDropChance = compound.getFloat("ItemDropChance");
            }
        }

        this.updateFacingWithBoundingBox(Direction.byIndex(compound.getByte("Facing")));
    }

    @Override
    public boolean processInitialInteract(PlayerEntity player, Hand hand) {
        ItemStack itemstack = player.getHeldItem(hand);
        if (!this.world.isRemote) {
            if (this.getDisplayedItem().isEmpty()) {
                if (!itemstack.isEmpty() && AquacultureAPI.FISH_DATA.getFish().contains(itemstack.getItem())) {
                    this.setDisplayedItem(itemstack);
                    if (!player.abilities.isCreativeMode) {
                        itemstack.shrink(1);
                    }
                }
            }
        }

        return true;
    }

    @Override
    public IPacket<?> createSpawnPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    @Override
    protected void setRotation(float yaw, float pitch) {
        super.setRotation(yaw, pitch);
        if (pitch == 0) {
            this.updateFacingWithBoundingBox(Direction.fromAngle(yaw));
        } else {
            this.updateFacingWithBoundingBox(pitch < 0 ? Direction.UP : Direction.DOWN);
        }
    }
}

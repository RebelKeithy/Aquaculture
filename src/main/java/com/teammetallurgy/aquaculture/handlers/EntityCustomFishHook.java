package com.teammetallurgy.aquaculture.handlers;

import com.teammetallurgy.aquaculture.enchantments.AquacultureEnchants;
import com.teammetallurgy.aquaculture.items.ItemAdminFishingRod;
import com.teammetallurgy.aquaculture.items.ItemAquacultureFishingRod;
import com.teammetallurgy.aquaculture.items.ItemFish;
import com.teammetallurgy.aquaculture.loot.FishLoot;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityFishHook;
import net.minecraft.init.Enchantments;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.stats.StatList;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.*;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.fml.common.registry.IThrowableEntity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.List;

public class EntityCustomFishHook extends EntityFishHook implements IThrowableEntity {
    private static final DataParameter<Integer> DATA_HOOKED_ENTITY = EntityDataManager.createKey(EntityFishHook.class, DataSerializers.VARINT);
    private boolean inGround;
    private int ticksInGround;
    private EntityPlayer angler;
    private int ticksInAir;
    private int ticksCatchable;
    private int ticksCaughtDelay;
    private int ticksCatchableDelay;
    private float fishApproachAngle;
    public Entity caughtEntity;
    private State currentState = State.FLYING;
    private int luck;
    private int lureSpeed;

    private boolean isAdmin = false;

    @SideOnly(Side.CLIENT)
    public EntityCustomFishHook(World world, EntityPlayer player, double x, double y, double z) {
        super(world, player, x, y, z);
        this.init(player);
        this.setPosition(x, y, z);
        this.prevPosX = this.posX;
        this.prevPosY = this.posY;
        this.prevPosZ = this.posZ;
    }

    public EntityCustomFishHook(World world, EntityPlayer player) {
        super(world, player);
        this.init(player);
        this.shoot();

        int shortCast = EnchantmentHelper.getEnchantmentLevel(AquacultureEnchants.shortcast, player.getHeldItemMainhand());
        int longCast = EnchantmentHelper.getEnchantmentLevel(AquacultureEnchants.longcast, player.getHeldItemMainhand());

        float velocity = 1.5F;
        if (shortCast > 0) {
            velocity *= (0.1F * shortCast);
        }
        if (longCast > 0) {
            velocity += velocity * (0.2F * longCast);
        }

        this.calculateVelocity(this.motionX, this.motionY, this.motionZ, velocity, 1.0F);
    }

    public EntityCustomFishHook(World world, EntityPlayer entityplayer, boolean isAdmin) {
        this(world, entityplayer);

        this.isAdmin = isAdmin;
    }

    public EntityCustomFishHook(World world) {
        this(world, null);
    }

    private void init(EntityPlayer p_190626_1_) {
        this.setSize(0.25F, 0.25F);
        this.ignoreFrustumCheck = true;
        this.angler = p_190626_1_;
        this.angler.fishEntity = this;
    }

    @Override
    public void setLureSpeed(int speed)
    {
        this.lureSpeed = speed;
    }

    @Override
    public void setLuck(int luck)
    {
        this.luck = luck;
    }

    private void shoot() {
        float f = this.angler.prevRotationPitch + (this.angler.rotationPitch - this.angler.prevRotationPitch);
        float f1 = this.angler.prevRotationYaw + (this.angler.rotationYaw - this.angler.prevRotationYaw);
        float f2 = MathHelper.cos(-f1 * 0.017453292F - (float) Math.PI);
        float f3 = MathHelper.sin(-f1 * 0.017453292F - (float) Math.PI);
        float f4 = -MathHelper.cos(-f * 0.017453292F);
        float f5 = MathHelper.sin(-f * 0.017453292F);
        double d0 = this.angler.prevPosX + (this.angler.posX - this.angler.prevPosX) - (double) f3 * 0.3D;
        double d1 = this.angler.prevPosY + (this.angler.posY - this.angler.prevPosY) + (double) this.angler.getEyeHeight();
        double d2 = this.angler.prevPosZ + (this.angler.posZ - this.angler.prevPosZ) - (double) f2 * 0.3D;
        this.setLocationAndAngles(d0, d1, d2, f1, f);
        this.motionX = (double) (-f3);
        this.motionY = (double) MathHelper.clamp(-(f5 / f4), -5.0F, 5.0F);
        this.motionZ = (double) (-f2);
        float f6 = MathHelper.sqrt(this.motionX * this.motionX + this.motionY * this.motionY + this.motionZ * this.motionZ);
        this.motionX *= 0.6D / (double) f6 + 0.5D + this.rand.nextGaussian() * 0.0045D;
        this.motionY *= 0.6D / (double) f6 + 0.5D + this.rand.nextGaussian() * 0.0045D;
        this.motionZ *= 0.6D / (double) f6 + 0.5D + this.rand.nextGaussian() * 0.0045D;
        float f7 = MathHelper.sqrt(this.motionX * this.motionX + this.motionZ * this.motionZ);
        this.rotationYaw = (float) (MathHelper.atan2(this.motionX, this.motionZ) * (180D / Math.PI));
        this.rotationPitch = (float) (MathHelper.atan2(this.motionY, (double) f7) * (180D / Math.PI));
        this.prevRotationYaw = this.rotationYaw;
        this.prevRotationPitch = this.rotationPitch;
    }

    @Override
    protected void entityInit() {
        this.getDataManager().register(DATA_HOOKED_ENTITY, 0);
    }

    @Override
    public void notifyDataManagerChange(@Nullable DataParameter<?> key) {
        if (DATA_HOOKED_ENTITY.equals(key)) {
            int i = this.getDataManager().get(DATA_HOOKED_ENTITY);
            this.caughtEntity = i > 0 ? this.world.getEntityByID(i - 1) : null;
        }
        super.notifyDataManagerChange(key);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean isInRangeToRenderDist(double distance) {
        return distance < 4096.0D;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void setPositionAndRotationDirect(double x, double y, double z, float yaw, float pitch, int posRotationIncrements, boolean teleport) {
    }

    @Override
    public void onUpdate() {
        super.onEntityUpdate();
        if (this.angler == null) {
            this.setDead();
        } else if (this.world.isRemote || !this.shouldStopFishing()) {
            if (this.inGround) {
                ++this.ticksInGround;

                if (this.ticksInGround >= 1200) {
                    this.setDead();
                    return;
                }
            }

            float f = 0.0F;
            BlockPos blockpos = new BlockPos(this);
            IBlockState iblockstate = this.world.getBlockState(blockpos);

            if (iblockstate.getMaterial() == Material.WATER) {
                f = BlockLiquid.getBlockLiquidHeight(iblockstate, this.world, blockpos);
            }

            if (this.currentState == State.FLYING) {
                if (this.caughtEntity != null) {
                    this.motionX = 0.0D;
                    this.motionY = 0.0D;
                    this.motionZ = 0.0D;
                    this.currentState = State.HOOKED_IN_ENTITY;
                    return;
                }

                if (f > 0.0F) {
                    this.motionX *= 0.3D;
                    this.motionY *= 0.2D;
                    this.motionZ *= 0.3D;
                    this.currentState = State.BOBBING;
                    return;
                }

                if (!this.world.isRemote) {
                    this.checkCollision();
                }

                if (!this.inGround && !this.onGround && !this.collidedHorizontally) {
                    ++this.ticksInAir;
                } else {
                    this.ticksInAir = 0;
                    this.motionX = 0.0D;
                    this.motionY = 0.0D;
                    this.motionZ = 0.0D;
                }
            } else {
                if (this.currentState == State.HOOKED_IN_ENTITY) {
                    if (this.caughtEntity != null) {
                        if (this.caughtEntity.isDead) {
                            this.caughtEntity = null;
                            this.currentState = State.FLYING;
                        } else {
                            this.posX = this.caughtEntity.posX;
                            double d2 = (double) this.caughtEntity.height;
                            this.posY = this.caughtEntity.getEntityBoundingBox().minY + d2 * 0.8D;
                            this.posZ = this.caughtEntity.posZ;
                            this.setPosition(this.posX, this.posY, this.posZ);
                        }
                    }
                    return;
                }

                if (this.currentState == State.BOBBING) {
                    this.motionX *= 0.9D;
                    this.motionZ *= 0.9D;
                    double d0 = this.posY + this.motionY - (double) blockpos.getY() - (double) f;

                    if (Math.abs(d0) < 0.01D) {
                        d0 += Math.signum(d0) * 0.1D;
                    }

                    this.motionY -= d0 * (double) this.rand.nextFloat() * 0.2D;

                    if (!this.world.isRemote && f > 0.0F) {
                        this.catchingFish(blockpos);
                    }
                }
            }

            if (iblockstate.getMaterial() != Material.WATER) {
                this.motionY -= 0.03D;
            }

            this.move(MoverType.SELF, this.motionX, this.motionY, this.motionZ);
            this.updateRotation();
            double d1 = 0.92D;
            this.motionX *= d1;
            this.motionY *= d1;
            this.motionZ *= d1;
            this.setPosition(this.posX, this.posY, this.posZ);
        }
    }

    private boolean shouldStopFishing() {
        ItemStack heldMain = this.angler.getHeldItemMainhand();
        ItemStack heldOff = this.angler.getHeldItemOffhand();
        boolean isMain = isFishingRod(heldMain);
        boolean isOff = isFishingRod(heldOff);

        if (!this.angler.isDead && this.angler.isEntityAlive() && (isMain || isOff) && this.getDistanceSq(this.angler) <= 1024.0D) {
            return false;
        } else {
            this.setDead();
            return true;
        }
    }

    private boolean isFishingRod(ItemStack stack) {
        Item item = stack.getItem();
        return (item instanceof ItemAquacultureFishingRod) || (item instanceof ItemAdminFishingRod);
    }

    private void updateRotation() {
        float f = MathHelper.sqrt(this.motionX * this.motionX + this.motionZ * this.motionZ);
        this.rotationYaw = (float) (MathHelper.atan2(this.motionX, this.motionZ) * (180D / Math.PI));

        for (this.rotationPitch = (float) (MathHelper.atan2(this.motionY, (double) f) * (180D / Math.PI)); this.rotationPitch - this.prevRotationPitch < -180.0F; this.prevRotationPitch -= 360.0F) {
            //Do nothing
        }

        while (this.rotationPitch - this.prevRotationPitch >= 180.0F) {
            this.prevRotationPitch += 360.0F;
        }

        while (this.rotationYaw - this.prevRotationYaw < -180.0F) {
            this.prevRotationYaw -= 360.0F;
        }

        while (this.rotationYaw - this.prevRotationYaw >= 180.0F) {
            this.prevRotationYaw += 360.0F;
        }

        this.rotationPitch = this.prevRotationPitch + (this.rotationPitch - this.prevRotationPitch) * 0.2F;
        this.rotationYaw = this.prevRotationYaw + (this.rotationYaw - this.prevRotationYaw) * 0.2F;
    }

    private void checkCollision() {
        Vec3d vec3d = new Vec3d(this.posX, this.posY, this.posZ);
        Vec3d vec3d1 = new Vec3d(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);
        RayTraceResult raytraceresult = this.world.rayTraceBlocks(vec3d, vec3d1, false, true, false);
        vec3d = new Vec3d(this.posX, this.posY, this.posZ);
        vec3d1 = new Vec3d(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);

        if (raytraceresult != null) {
            vec3d1 = new Vec3d(raytraceresult.hitVec.x, raytraceresult.hitVec.y, raytraceresult.hitVec.z);
        }

        Entity entity = null;
        List<Entity> list = this.world.getEntitiesWithinAABBExcludingEntity(this, this.getEntityBoundingBox().expand(this.motionX, this.motionY, this.motionZ).grow(1.0D));
        double d0 = 0.0D;

        for (Entity entity1 : list) {
            if (this.canBeHooked(entity1) && (entity1 != this.angler || this.ticksInAir >= 5)) {
                AxisAlignedBB alignedBB = entity1.getEntityBoundingBox().grow(0.30000001192092896D);
                RayTraceResult rayTraceResult = alignedBB.calculateIntercept(vec3d, vec3d1);

                if (rayTraceResult != null) {
                    double d1 = vec3d.squareDistanceTo(rayTraceResult.hitVec);

                    if (d1 < d0 || d0 == 0.0D) {
                        entity = entity1;
                        d0 = d1;
                    }
                }
            }
        }

        if (entity != null) {
            raytraceresult = new RayTraceResult(entity);
        }
        if (raytraceresult != null && raytraceresult.typeOfHit != RayTraceResult.Type.MISS) {
            if (raytraceresult.typeOfHit == RayTraceResult.Type.ENTITY) {
                this.caughtEntity = raytraceresult.entityHit;
                this.setHookedEntity();
            } else {
                this.inGround = true;
            }
        }
    }

    private void setHookedEntity() {
        this.getDataManager().set(DATA_HOOKED_ENTITY, this.caughtEntity.getEntityId() + 1);
    }

    private void catchingFish(BlockPos pos) {
        WorldServer worldserver = (WorldServer) this.world;
        int i = 1;
        BlockPos blockpos = pos.up();

        if (this.rand.nextFloat() < 0.25F && this.world.isRainingAt(blockpos)) {
            ++i;
        }

        if (this.rand.nextFloat() < 0.5F && !this.world.canSeeSky(blockpos)) {
            --i;
        }

        if (this.ticksCatchable > 0) {
            --this.ticksCatchable;

            if (this.ticksCatchable <= 0) {
                this.ticksCaughtDelay = 0;
                this.ticksCatchableDelay = 0;
            } else {
                this.motionY -= 0.2D * (double) this.rand.nextFloat() * (double) this.rand.nextFloat();
            }
        } else if (this.ticksCatchableDelay > 0) {
            this.ticksCatchableDelay -= i;

            if (this.ticksCatchableDelay > 0) {
                this.fishApproachAngle = (float) ((double) this.fishApproachAngle + this.rand.nextGaussian() * 4.0D);
                float f = this.fishApproachAngle * 0.017453292F;
                float f1 = MathHelper.sin(f);
                float f2 = MathHelper.cos(f);
                double d0 = this.posX + (double) (f1 * (float) this.ticksCatchableDelay * 0.1F);
                double d1 = (double) ((float) MathHelper.floor(this.getEntityBoundingBox().minY) + 1.0F);
                double d2 = this.posZ + (double) (f2 * (float) this.ticksCatchableDelay * 0.1F);
                IBlockState state = worldserver.getBlockState(new BlockPos(d0, d1 - 1.0D, d2));

                if (state.getMaterial() == Material.WATER) {
                    if (this.rand.nextFloat() < 0.15F) {
                        worldserver.spawnParticle(EnumParticleTypes.WATER_BUBBLE, d0, d1 - 0.10000000149011612D, d2, 1, (double) f1, 0.1D, (double) f2, 0.0D);
                    }

                    float f3 = f1 * 0.04F;
                    float f4 = f2 * 0.04F;
                    worldserver.spawnParticle(EnumParticleTypes.WATER_WAKE, d0, d1, d2, 0, (double) f4, 0.01D, (double) (-f3), 1.0D);
                    worldserver.spawnParticle(EnumParticleTypes.WATER_WAKE, d0, d1, d2, 0, (double) (-f4), 0.01D, (double) f3, 1.0D);
                }
            } else {
                if (this.angler != null) {
                    int barbed = EnchantmentHelper.getEnchantmentLevel(AquacultureEnchants.barbedHook, this.angler.getHeldItemMainhand());
                    ticksCatchable += 4 * barbed;
                }

                if (this.angler != null) {
                    int loot = EnchantmentHelper.getEnchantmentLevel(Enchantments.FORTUNE, this.angler.getHeldItemMainhand());
                    ticksCatchable += 4 * loot;
                }

                this.motionY = (double) (-0.4F * MathHelper.nextFloat(this.rand, 0.6F, 1.0F));
                this.playSound(SoundEvents.ENTITY_BOBBER_SPLASH, 0.25F, 1.0F + (this.rand.nextFloat() - this.rand.nextFloat()) * 0.4F);
                double d3 = this.getEntityBoundingBox().minY + 0.5D;
                worldserver.spawnParticle(EnumParticleTypes.WATER_BUBBLE, this.posX, d3, this.posZ, (int) (1.0F + this.width * 20.0F), (double) this.width, 0.0D, (double) this.width, 0.20000000298023224D);
                worldserver.spawnParticle(EnumParticleTypes.WATER_WAKE, this.posX, d3, this.posZ, (int) (1.0F + this.width * 20.0F), (double) this.width, 0.0D, (double) this.width, 0.20000000298023224D);
                this.ticksCatchable = MathHelper.getInt(this.rand, 20, 40);
            }
        } else if (this.ticksCaughtDelay > 0) {
            this.ticksCaughtDelay -= i;
            float f5 = 0.15F;

            if (this.ticksCaughtDelay < 20) {
                f5 = (float) ((double) f5 + (double) (20 - this.ticksCaughtDelay) * 0.05D);
            } else if (this.ticksCaughtDelay < 40) {
                f5 = (float) ((double) f5 + (double) (40 - this.ticksCaughtDelay) * 0.02D);
            } else if (this.ticksCaughtDelay < 60) {
                f5 = (float) ((double) f5 + (double) (60 - this.ticksCaughtDelay) * 0.01D);
            }

            if (this.rand.nextFloat() < f5) {
                float f6 = MathHelper.nextFloat(this.rand, 0.0F, 360.0F) * 0.017453292F;
                float f7 = MathHelper.nextFloat(this.rand, 25.0F, 60.0F);
                double d4 = this.posX + (double) (MathHelper.sin(f6) * f7 * 0.1F);
                double d5 = (double) ((float) MathHelper.floor(this.getEntityBoundingBox().minY) + 1.0F);
                double d6 = this.posZ + (double) (MathHelper.cos(f6) * f7 * 0.1F);
                IBlockState state = worldserver.getBlockState(new BlockPos((int) d4, (int) d5 - 1, (int) d6));

                if (state.getMaterial() == Material.WATER) {
                    worldserver.spawnParticle(EnumParticleTypes.WATER_SPLASH, d4, d5, d6, 2 + this.rand.nextInt(2), 0.10000000149011612D, 0.0D, 0.10000000149011612D, 0.0D);
                }
            }
            if (this.ticksCaughtDelay <= 0) {
                this.fishApproachAngle = MathHelper.nextFloat(this.rand, 0.0F, 360.0F);
                this.ticksCatchableDelay = MathHelper.getInt(this.rand, 20, 80);
            }
        } else {
            this.ticksCaughtDelay = MathHelper.getInt(this.rand, 100, 600);
            this.ticksCaughtDelay -= this.lureSpeed * 20 * 5;
        }
    }

    protected boolean canBeHooked(Entity entity) {
        return entity.canBeCollidedWith() || entity instanceof EntityItem;
    }

    @Override
    public int handleHookRetraction() {
        if (!this.world.isRemote && this.angler != null) {
            int i = 0;

            if (this.caughtEntity != null) {
                this.bringInHookedEntity();
                this.world.setEntityState(this, (byte) 31);
                i = this.caughtEntity instanceof EntityItem ? 3 : 5;
            } else if (this.ticksCatchable > 0 || isAdmin) {
                Biome currentBiome = world.getBiome(new BlockPos(MathHelper.floor(angler.posX), 0, MathHelper.floor(angler.posZ)));
                ItemStack fishLoot;
                int count = 1;
                float doubleOdds;

                int doubleHook = EnchantmentHelper.getEnchantmentLevel(AquacultureEnchants.doubleHook, this.angler.getHeldItemMainhand());
                if (doubleHook > 0) {
                    doubleOdds = 0.1F * doubleHook;

                    if (Math.random() < doubleOdds) {
                        count++;
                    }
                }

                do {
                    float fishOdds = 0.5F;

                    int magnetic = EnchantmentHelper.getEnchantmentLevel(AquacultureEnchants.magnetic, this.angler.getHeldItemMainhand());
                    int appealing = EnchantmentHelper.getEnchantmentLevel(AquacultureEnchants.appealing, this.angler.getHeldItemMainhand());

                    if (magnetic > 0) {
                        fishOdds -= (0.08 * magnetic);
                    } else if (appealing > 0) {
                        fishOdds += (0.08 * appealing);
                    }

                    float roll = (float) Math.random();

                    int heavyLine = 0;

                    if (this.angler != null) {
                        heavyLine = EnchantmentHelper.getEnchantmentLevel(AquacultureEnchants.heavyLine, angler.getHeldItemMainhand());
                    }

                    if (roll < fishOdds) {
                        if ((this.angler.getHeldItemMainhand().getItem() instanceof ItemAdminFishingRod) && this.angler.isSneaking())
                            fishLoot = FishLoot.instance().getRandomFish(Biome.getIdForBiome(currentBiome));
                        else
                            fishLoot = FishLoot.instance().getRandomFish(Biome.getIdForBiome(currentBiome), heavyLine);
                    } else {
                        fishLoot = FishLoot.instance().getRandomJunk(Biome.getIdForBiome(currentBiome));
                    }

                    if (!fishLoot.isEmpty()) {
                        EntityItem entityitem = new EntityItem(this.world, this.posX, this.posY, this.posZ, fishLoot);
                        double d0 = this.angler.posX - this.posX;
                        double d1 = this.angler.posY - this.posY;
                        double d2 = this.angler.posZ - this.posZ;
                        double d3 = (double) MathHelper.sqrt(d0 * d0 + d1 * d1 + d2 * d2);
                        double d4 = 0.1D;
                        entityitem.motionX = d0 * d4;
                        entityitem.motionY = d1 * d4 + (double) MathHelper.sqrt(d3) * 0.08D;
                        entityitem.motionZ = d2 * d4;
                        this.world.spawnEntity(entityitem);
                        this.angler.world.spawnEntity(new EntityXPOrb(this.angler.world, this.angler.posX, this.angler.posY + 0.5D, this.angler.posZ + 0.5D, this.rand.nextInt(6) + 1));
                        Item fish = fishLoot.getItem();
                        if (fish == Items.FISH || fish == Items.COOKED_FISH || fish instanceof ItemFish) {
                            this.angler.addStat(StatList.FISH_CAUGHT, 1);
                        }
                    }
                    count--;
                } while (count > 0);
                i = 1;
            }

            if (this.inGround) {
                i = 2;
            }
            this.setDead();
            return i;
        } else {
            return 0;
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void handleStatusUpdate(byte id) {
        if (id == 31 && this.world.isRemote && this.caughtEntity instanceof EntityPlayer && ((EntityPlayer) this.caughtEntity).isUser()) {
            this.bringInHookedEntity();
        }

        super.handleStatusUpdate(id);
    }

    @Override
    protected void bringInHookedEntity() {
        if (this.angler != null) {
            double d0 = this.angler.posX - this.posX;
            double d1 = this.angler.posY - this.posY;
            double d2 = this.angler.posZ - this.posZ;
            double d3 = 0.1D;
            this.caughtEntity.motionX += d0 * d3;
            this.caughtEntity.motionY += d1 * d3;
            this.caughtEntity.motionZ += d2 * d3;
        }
    }

    @Override
    public void setDead() {
        super.setDead();

        if (this.angler != null) {
            this.angler.fishEntity = null;
        }
    }

    @Override
    public Entity getThrower() {
        return this.angler;
    }

    @Override
    public void setThrower(Entity entity) {
        //Do nothing
    }

    enum State {
        FLYING,
        HOOKED_IN_ENTITY,
        BOBBING;
    }

    //Only kept to calculate velocity for Enchantments shortcast and longcast
    private void calculateVelocity(double par1, double par3, double par5, float par7, float par8) {
        float f2 = MathHelper.sqrt(par1 * par1 + par3 * par3 + par5 * par5);
        par1 /= f2;
        par3 /= f2;
        par5 /= f2;
        par1 += this.rand.nextGaussian() * 0.007499999832361937D * par8;
        par3 += this.rand.nextGaussian() * 0.007499999832361937D * par8;
        par5 += this.rand.nextGaussian() * 0.007499999832361937D * par8;
        par1 *= par7;
        par3 *= par7;
        par5 *= par7;
        this.motionX = par1;
        this.motionY = par3;
        this.motionZ = par5;
        float f3 = MathHelper.sqrt(par1 * par1 + par5 * par5);
        this.prevRotationYaw = this.rotationYaw = (float) (Math.atan2(par1, par5) * 180.0D / Math.PI);
        this.prevRotationPitch = this.rotationPitch = (float) (Math.atan2(par3, f3) * 180.0D / Math.PI);
        this.ticksInGround = 0;
    }
}
package rebelkeithy.mods.aquaculture;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityFishHook;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.stats.StatList;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeGenDesert;
import net.minecraft.world.biome.BiomeGenForest;
import net.minecraft.world.biome.BiomeGenHills;
import net.minecraft.world.biome.BiomeGenMushroomIsland;
import net.minecraft.world.biome.BiomeGenOcean;
import net.minecraft.world.biome.BiomeGenPlains;
import net.minecraft.world.biome.BiomeGenRiver;
import net.minecraft.world.biome.BiomeGenTaiga;


public class AquacultureEntityFishHook extends EntityFishHook
{
    private int xTile;
    private int yTile;
    private int zTile;
    private int inTile;
    private boolean inGround;
    public int shake;
    public EntityPlayer angler;
    private int ticksInGround;
    private int ticksInAir;
    private int ticksCatchable;
    public Entity bobber;
    private int fishPosRotationIncrements;
    private double fishX;
    private double fishY;
    private double fishZ;
    private double fishYaw;
    private double fishPitch;
    private double velocityX;
    private double velocityY;
    private double velocityZ;

    public AquacultureEntityFishHook(World par1World)
    {
        super(par1World);
        this.xTile = -1;
        this.yTile = -1;
        this.zTile = -1;
        this.inTile = 0;
        this.inGround = false;
        this.shake = 0;
        this.ticksInAir = 0;
        this.ticksCatchable = 0;
        this.bobber = null;
        this.setSize(0.25F, 0.25F);
        this.ignoreFrustumCheck = true;
    }

    @SideOnly(Side.CLIENT)
    public AquacultureEntityFishHook(World par1World, double par2, double par4, double par6, EntityPlayer par8EntityPlayer)
    {
        this(par1World);
        this.setPosition(par2, par4, par6);
        this.ignoreFrustumCheck = true;
        this.angler = par8EntityPlayer;
        par8EntityPlayer.fishEntity = this;
    }

    public AquacultureEntityFishHook(World par1World, EntityPlayer par2EntityPlayer)
    {
        super(par1World);
        this.xTile = -1;
        this.yTile = -1;
        this.zTile = -1;
        this.inTile = 0;
        this.inGround = false;
        this.shake = 0;
        this.ticksInAir = 0;
        this.ticksCatchable = 0;
        this.bobber = null;
        this.ignoreFrustumCheck = true;
        this.angler = par2EntityPlayer;
        this.angler.fishEntity = this;
        this.setSize(0.25F, 0.25F);
        this.setLocationAndAngles(par2EntityPlayer.posX, par2EntityPlayer.posY + 1.62D - (double)par2EntityPlayer.yOffset, par2EntityPlayer.posZ, par2EntityPlayer.rotationYaw, par2EntityPlayer.rotationPitch);
        this.posX -= (double)(MathHelper.cos(this.rotationYaw / 180.0F * (float)Math.PI) * 0.16F);
        this.posY -= 0.10000000149011612D;
        this.posZ -= (double)(MathHelper.sin(this.rotationYaw / 180.0F * (float)Math.PI) * 0.16F);
        this.setPosition(this.posX, this.posY, this.posZ);
        this.yOffset = 0.0F;
        float f = 0.4F;
        this.motionX = (double)(-MathHelper.sin(this.rotationYaw / 180.0F * (float)Math.PI) * MathHelper.cos(this.rotationPitch / 180.0F * (float)Math.PI) * f);
        this.motionZ = (double)(MathHelper.cos(this.rotationYaw / 180.0F * (float)Math.PI) * MathHelper.cos(this.rotationPitch / 180.0F * (float)Math.PI) * f);
        this.motionY = (double)(-MathHelper.sin(this.rotationPitch / 180.0F * (float)Math.PI) * f);
        this.calculateVelocity(this.motionX, this.motionY, this.motionZ, 1.5F, 1.0F);
    }

    protected void entityInit()
    {
    }

    public boolean isInRangeToRenderDist(double d)
    {
        double d1 = boundingBox.getAverageEdgeLength() * 4D;
        d1 *= 64D;
        return d < d1 * d1;
    }

    public void calculateVelocity(double par1, double par3, double par5, float par7, float par8)
    {
        float f2 = MathHelper.sqrt_double(par1 * par1 + par3 * par3 + par5 * par5);
        par1 /= (double)f2;
        par3 /= (double)f2;
        par5 /= (double)f2;
        par1 += this.rand.nextGaussian() * 0.007499999832361937D * (double)par8;
        par3 += this.rand.nextGaussian() * 0.007499999832361937D * (double)par8;
        par5 += this.rand.nextGaussian() * 0.007499999832361937D * (double)par8;
        par1 *= (double)par7;
        par3 *= (double)par7;
        par5 *= (double)par7;
        this.motionX = par1;
        this.motionY = par3;
        this.motionZ = par5;
        float f3 = MathHelper.sqrt_double(par1 * par1 + par5 * par5);
        this.prevRotationYaw = this.rotationYaw = (float)(Math.atan2(par1, par5) * 180.0D / Math.PI);
        this.prevRotationPitch = this.rotationPitch = (float)(Math.atan2(par3, (double)f3) * 180.0D / Math.PI);
        this.ticksInGround = 0;
    }

    public void setPositionAndRotation2(double d, double d1, double d2, float f,
            float f1, int i)
    {
        fishX = d;
        fishY = d1;
        fishZ = d2;
        fishYaw = f;
        fishPitch = f1;
        fishPosRotationIncrements = i;
        motionX = velocityX;
        motionY = velocityY;
        motionZ = velocityZ;
    }

    public void setVelocity(double d, double d1, double d2)
    {
        velocityX = motionX = d;
        velocityY = motionY = d1;
        velocityZ = motionZ = d2;
    }

    /**
     * Called to update the entity's position/logic.
     */
    public void onUpdate()
    {
        super.onUpdate();

        if (this.fishPosRotationIncrements > 0)
        {
            double d0 = this.posX + (this.fishX - this.posX) / (double)this.fishPosRotationIncrements;
            double d1 = this.posY + (this.fishY - this.posY) / (double)this.fishPosRotationIncrements;
            double d2 = this.posZ + (this.fishZ - this.posZ) / (double)this.fishPosRotationIncrements;
            double d3 = MathHelper.wrapAngleTo180_double(this.fishYaw - (double)this.rotationYaw);
            this.rotationYaw = (float)((double)this.rotationYaw + d3 / (double)this.fishPosRotationIncrements);
            this.rotationPitch = (float)((double)this.rotationPitch + (this.fishPitch - (double)this.rotationPitch) / (double)this.fishPosRotationIncrements);
            --this.fishPosRotationIncrements;
            this.setPosition(d0, d1, d2);
            this.setRotation(this.rotationYaw, this.rotationPitch);
        }
        else
        {
            if (!this.worldObj.isRemote)
            {
                ItemStack itemstack = this.angler.getCurrentEquippedItem();

                if (this.angler.isDead || !this.angler.isEntityAlive() || itemstack == null || itemstack.getItem() != Item.fishingRod || this.getDistanceSqToEntity(this.angler) > 1024.0D)
                {
                    this.setDead();
                    this.angler.fishEntity = null;
                    return;
                }

                if (this.bobber != null)
                {
                    if (!this.bobber.isDead)
                    {
                        this.posX = this.bobber.posX;
                        this.posY = this.bobber.boundingBox.minY + (double)this.bobber.height * 0.8D;
                        this.posZ = this.bobber.posZ;
                        return;
                    }

                    this.bobber = null;
                }
            }

            if (this.shake > 0)
            {
                --this.shake;
            }

            if (this.inGround)
            {
                int i = this.worldObj.getBlockId(this.xTile, this.yTile, this.zTile);

                if (i == this.inTile)
                {
                    ++this.ticksInGround;

                    if (this.ticksInGround == 1200)
                    {
                        this.setDead();
                    }

                    return;
                }

                this.inGround = false;
                this.motionX *= (double)(this.rand.nextFloat() * 0.2F);
                this.motionY *= (double)(this.rand.nextFloat() * 0.2F);
                this.motionZ *= (double)(this.rand.nextFloat() * 0.2F);
                this.ticksInGround = 0;
                this.ticksInAir = 0;
            }
            else
            {
                ++this.ticksInAir;
            }

            Vec3 vec3 = this.worldObj.getWorldVec3Pool().getVecFromPool(this.posX, this.posY, this.posZ);
            Vec3 vec31 = this.worldObj.getWorldVec3Pool().getVecFromPool(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);
            MovingObjectPosition movingobjectposition = this.worldObj.rayTraceBlocks(vec3, vec31);
            vec3 = this.worldObj.getWorldVec3Pool().getVecFromPool(this.posX, this.posY, this.posZ);
            vec31 = this.worldObj.getWorldVec3Pool().getVecFromPool(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);

            if (movingobjectposition != null)
            {
                vec31 = this.worldObj.getWorldVec3Pool().getVecFromPool(movingobjectposition.hitVec.xCoord, movingobjectposition.hitVec.yCoord, movingobjectposition.hitVec.zCoord);
            }

            Entity entity = null;
            List list = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, this.boundingBox.addCoord(this.motionX, this.motionY, this.motionZ).expand(1.0D, 1.0D, 1.0D));
            double d4 = 0.0D;
            double d5;

            for (int j = 0; j < list.size(); ++j)
            {
                Entity entity1 = (Entity)list.get(j);

                if (entity1.canBeCollidedWith() && (entity1 != this.angler || this.ticksInAir >= 5))
                {
                    float f = 0.3F;
                    AxisAlignedBB axisalignedbb = entity1.boundingBox.expand((double)f, (double)f, (double)f);
                    MovingObjectPosition movingobjectposition1 = axisalignedbb.calculateIntercept(vec3, vec31);

                    if (movingobjectposition1 != null)
                    {
                        d5 = vec3.distanceTo(movingobjectposition1.hitVec);

                        if (d5 < d4 || d4 == 0.0D)
                        {
                            entity = entity1;
                            d4 = d5;
                        }
                    }
                }
            }

            if (entity != null)
            {
                movingobjectposition = new MovingObjectPosition(entity);
            }

            if (movingobjectposition != null)
            {
                if (movingobjectposition.entityHit != null)
                {
                    if (movingobjectposition.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, this.angler), 0))
                    {
                        this.bobber = movingobjectposition.entityHit;
                    }
                }
                else
                {
                    this.inGround = true;
                }
            }

            if (!this.inGround)
            {
                this.moveEntity(this.motionX, this.motionY, this.motionZ);
                float f1 = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionZ * this.motionZ);
                this.rotationYaw = (float)(Math.atan2(this.motionX, this.motionZ) * 180.0D / Math.PI);

                for (this.rotationPitch = (float)(Math.atan2(this.motionY, (double)f1) * 180.0D / Math.PI); this.rotationPitch - this.prevRotationPitch < -180.0F; this.prevRotationPitch -= 360.0F)
                {
                    ;
                }

                while (this.rotationPitch - this.prevRotationPitch >= 180.0F)
                {
                    this.prevRotationPitch += 360.0F;
                }

                while (this.rotationYaw - this.prevRotationYaw < -180.0F)
                {
                    this.prevRotationYaw -= 360.0F;
                }

                while (this.rotationYaw - this.prevRotationYaw >= 180.0F)
                {
                    this.prevRotationYaw += 360.0F;
                }

                this.rotationPitch = this.prevRotationPitch + (this.rotationPitch - this.prevRotationPitch) * 0.2F;
                this.rotationYaw = this.prevRotationYaw + (this.rotationYaw - this.prevRotationYaw) * 0.2F;
                float f2 = 0.92F;

                if (this.onGround || this.isCollidedHorizontally)
                {
                    f2 = 0.5F;
                }

                byte b0 = 5;
                double d6 = 0.0D;

                for (int k = 0; k < b0; ++k)
                {
                    double d7 = this.boundingBox.minY + (this.boundingBox.maxY - this.boundingBox.minY) * (double)(k + 0) / (double)b0 - 0.125D + 0.125D;
                    double d8 = this.boundingBox.minY + (this.boundingBox.maxY - this.boundingBox.minY) * (double)(k + 1) / (double)b0 - 0.125D + 0.125D;
                    AxisAlignedBB axisalignedbb1 = AxisAlignedBB.getAABBPool().getAABB(this.boundingBox.minX, d7, this.boundingBox.minZ, this.boundingBox.maxX, d8, this.boundingBox.maxZ);

                    if (this.worldObj.isAABBInMaterial(axisalignedbb1, Material.water))
                    {
                        d6 += 1.0D / (double)b0;
                    }
                }

                if (d6 > 0.0D)
                {
                    if (this.ticksCatchable > 0)
                    {
                        --this.ticksCatchable;
                    }
                    else
                    {
                        short short1 = 300;

                        if (this.worldObj.canLightningStrikeAt(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.posY) + 1, MathHelper.floor_double(this.posZ)))
                        {
                            short1 = 150;
                        }

                        if (this.rand.nextInt(short1) == 0)
                        {
                            this.ticksCatchable = this.rand.nextInt(30) + 10;
                            this.motionY -= 0.20000000298023224D;
                            this.playSound("random.splash", 0.25F, 1.0F + (this.rand.nextFloat() - this.rand.nextFloat()) * 0.4F);
                            float f3 = (float)MathHelper.floor_double(this.boundingBox.minY);
                            int l;
                            float f4;
                            float f5;

                            for (l = 0; (float)l < 1.0F + this.width * 20.0F; ++l)
                            {
                                f5 = (this.rand.nextFloat() * 2.0F - 1.0F) * this.width;
                                f4 = (this.rand.nextFloat() * 2.0F - 1.0F) * this.width;
                                this.worldObj.spawnParticle("bubble", this.posX + (double)f5, (double)(f3 + 1.0F), this.posZ + (double)f4, this.motionX, this.motionY - (double)(this.rand.nextFloat() * 0.2F), this.motionZ);
                            }

                            for (l = 0; (float)l < 1.0F + this.width * 20.0F; ++l)
                            {
                                f5 = (this.rand.nextFloat() * 2.0F - 1.0F) * this.width;
                                f4 = (this.rand.nextFloat() * 2.0F - 1.0F) * this.width;
                                this.worldObj.spawnParticle("splash", this.posX + (double)f5, (double)(f3 + 1.0F), this.posZ + (double)f4, this.motionX, this.motionY, this.motionZ);
                            }
                        }
                    }
                }

                if (this.ticksCatchable > 0)
                {
                    this.motionY -= (double)(this.rand.nextFloat() * this.rand.nextFloat() * this.rand.nextFloat()) * 0.2D;
                }

                d5 = d6 * 2.0D - 1.0D;
                this.motionY += 0.03999999910593033D * d5;

                if (d6 > 0.0D)
                {
                    f2 = (float)((double)f2 * 0.9D);
                    this.motionY *= 0.8D;
                }

                this.motionX *= (double)f2;
                this.motionY *= (double)f2;
                this.motionZ *= (double)f2;
                this.setPosition(this.posX, this.posY, this.posZ);
            }
        }
    }

    public void writeEntityToNBT(NBTTagCompound nbttagcompound)
    {
        nbttagcompound.setShort("xTile", (short)xTile);
        nbttagcompound.setShort("yTile", (short)yTile);
        nbttagcompound.setShort("zTile", (short)zTile);
        nbttagcompound.setByte("inTile", (byte)inTile);
        nbttagcompound.setByte("shake", (byte)shake);
        nbttagcompound.setByte("inGround", (byte)(inGround ? 1 : 0));
    }

    public void readEntityFromNBT(NBTTagCompound nbttagcompound)
    {
        xTile = nbttagcompound.getShort("xTile");
        yTile = nbttagcompound.getShort("yTile");
        zTile = nbttagcompound.getShort("zTile");
        inTile = nbttagcompound.getByte("inTile") & 0xff;
        shake = nbttagcompound.getByte("shake") & 0xff;
        inGround = nbttagcompound.getByte("inGround") == 1;
    }

    public float getShadowSize()
    {
        return 0.0F;
    }

    public int catchFish(ItemStack itemstack, EntityPlayer entityplayer)
    {
    	
        byte byte0 = 0;
        if (bobber != null)
        {
            double d = angler.posX - posX;
            double d2 = angler.posY - posY;
            double d4 = angler.posZ - posZ;
            double d6 = MathHelper.sqrt_double(d * d + d2 * d2 + d4 * d4);
            double d8 = 0.10000000000000001D;
            bobber.motionX += d * d8;
            bobber.motionY += d2 * d8 + (double)MathHelper.sqrt_double(d6) * 0.080000000000000002D;
            bobber.motionZ += d4 * d8;
            byte0 = 3;
        }
        else if (ticksCatchable >= 1)
        {
        	BiomeGenBase currentBiome = worldObj.getBiomeGenForCoords(MathHelper.floor_double(angler.posX), MathHelper.floor_double(angler.posZ) );

        	int roll = rand.nextInt(1000) + 1; 	
        	ItemStack fishLoot;
        	do
        	{
        	if(currentBiome instanceof BiomeGenForest || currentBiome instanceof BiomeGenPlains || currentBiome instanceof BiomeGenRiver || currentBiome instanceof BiomeGenHills)
        	{
        		
	            if (roll >= 1 && roll <= 150) {
	            	fishLoot = AquacultureItems.fish.getFish("Bluegill");
	            } else if (roll >= 151 && roll <= 300) {
	            	fishLoot = AquacultureItems.fish.getFish("Bass");
	            } else if (roll >= 301 && roll <= 400) {
	            	fishLoot = AquacultureItems.fish.getFish("Perch");
	            } else if (roll >= 401 && roll <= 500) {
	            	fishLoot = AquacultureItems.fish.getFish("Brown Trout");
	            } else if (roll >= 501 && roll <= 570) {
	            	fishLoot = AquacultureItems.fish.getFish("Catfish");
	            } else if (roll >= 571 && roll <= 640) {
	            	fishLoot = AquacultureItems.fish.getFish("Carp");
	            } else if (roll >= 641 && roll <= 700) {
	            	fishLoot = AquacultureItems.fish.getFish("Muskellunge");
	            } else if (roll >= 701 && roll <= 800) {
	            	fishLoot = new ItemStack(AquacultureItems.Algae);
	            } else if (roll >= 801 && roll <= 825) {
	            	fishLoot = new ItemStack(Item.stick);
	            } else if (roll >= 826 && roll <= 850) {
	            	fishLoot = new ItemStack(AquacultureItems.Driftwood);
	            } else if (roll >= 851 && roll <= 875) {
	            	fishLoot = new ItemStack(Item.bootsLeather);
	            } else if (roll >= 876 && roll <= 895) {
	            	fishLoot = new ItemStack(AquacultureItems.TinCan);
	            } else if (roll >= 896 && roll <= 925) {
	            	fishLoot = new ItemStack(AquacultureItems.Box);
	            } else if (roll >= 926 && roll <= 949) {
	            	fishLoot = new ItemStack(AquacultureItems.Lockbox);
	            } else if (roll >= 950 && roll <= 959) {
	            	fishLoot = new ItemStack(AquacultureItems.TreasureChest);
	            } else if (roll >= 960 && roll <= 980) {
	            	fishLoot = new ItemStack(Item.bone);
	            } else if (roll >= 981 && roll <= 990) {
	            	fishLoot = AquacultureItems.fish.getFish("Goldfish");
	            } else if (roll >= 991 && roll <= 999) {
	            	fishLoot = new ItemStack(AquacultureItems.MessageInABottle);
	            } else if (roll >= 1000) {
	            	fishLoot = new ItemStack(AquacultureItems.NeptuniumBar);
	            } else {
	                fishLoot = new ItemStack(Item.appleRed);
	            }
        	} else if(currentBiome instanceof BiomeGenDesert)
	        	{
	            if (roll >= 1 && roll <= 250) {
	            	fishLoot = AquacultureItems.fish.getFish("Boulti");
	            } else if (roll >= 251 && roll <= 450) {
	            	fishLoot = AquacultureItems.fish.getFish("Capitaine");
	            } else if (roll >= 451 && roll <= 600) {
	            	fishLoot = AquacultureItems.fish.getFish("Bagrid");
	            } else if (roll >= 601 && roll <= 700) {
	            	fishLoot = AquacultureItems.fish.getFish("Syndontis");
	            } else if (roll >= 701 && roll <= 800) {
	            	fishLoot = new ItemStack(AquacultureItems.Algae);
	            } else if (roll >= 801 && roll <= 825) {
	            	fishLoot = new ItemStack(Item.stick);
	            } else if (roll >= 826 && roll <= 850) {
	            	fishLoot = new ItemStack(AquacultureItems.Driftwood);
	            } else if (roll >= 851 && roll <= 875) {
	            	fishLoot = new ItemStack(Item.bootsLeather);
	            } else if (roll >= 876 && roll <= 895) {
	            	fishLoot = new ItemStack(AquacultureItems.TinCan);
	            } else if (roll >= 896 && roll <= 925) {
	            	fishLoot = new ItemStack(AquacultureItems.Box);
	            } else if (roll >= 926 && roll <= 949) {
	            	fishLoot = new ItemStack(AquacultureItems.Lockbox);
	            } else if (roll >= 950 && roll <= 959) {
	            	fishLoot = new ItemStack(AquacultureItems.TreasureChest);
	            } else if (roll >= 960 && roll <= 980) {
	            	fishLoot = new ItemStack(Item.bone);
	            } else if (roll >= 981 && roll <= 990) {
	            	fishLoot = AquacultureItems.fish.getFish("Goldfish");
	            } else if (roll >= 991 && roll <= 999) {
	            	fishLoot = new ItemStack(AquacultureItems.MessageInABottle);
	            } else if (roll >= 1000) {
	            	fishLoot = new ItemStack(AquacultureItems.NeptuniumBar);
	            } else {
	                fishLoot = new ItemStack(Item.appleRed);
	            }
	        } else if(currentBiome instanceof BiomeGenTaiga || currentBiome instanceof BiomeGenTaiga)
	        	{
	            if (roll >= 1 && roll <= 250) {
	            	fishLoot = AquacultureItems.fish.getFish("Cod");
	            } else if (roll >= 251 && roll <= 450) {
	            	fishLoot = AquacultureItems.fish.getFish("Pollock");
	            } else if (roll >= 451 && roll <= 600) {
	            	fishLoot = AquacultureItems.fish.getFish("Herring");
	            } else if (roll >= 601 && roll <= 700) {
	            	fishLoot = AquacultureItems.fish.getFish("Rainbow Trout");
	            } else if (roll >= 701 && roll <= 800) {
	            	fishLoot = new ItemStack(AquacultureItems.Algae);
	            } else if (roll >= 801 && roll <= 825) {
	            	fishLoot = new ItemStack(Item.stick);
	            } else if (roll >= 826 && roll <= 850) {
	            	fishLoot = new ItemStack(AquacultureItems.Driftwood);
	            } else if (roll >= 851 && roll <= 875) {
	            	fishLoot = new ItemStack(Item.bootsLeather);
	            } else if (roll >= 876 && roll <= 895) {
	            	fishLoot = new ItemStack(AquacultureItems.TinCan);
	            } else if (roll >= 896 && roll <= 925) {
	            	fishLoot = new ItemStack(AquacultureItems.Box);
	            } else if (roll >= 926 && roll <= 949) {
	            	fishLoot = new ItemStack(AquacultureItems.Lockbox);
	            } else if (roll >= 950 && roll <= 959) {
	            	fishLoot = new ItemStack(AquacultureItems.TreasureChest);
	            } else if (roll >= 960 && roll <= 980) {
	            	fishLoot = new ItemStack(Item.bone);
	            } else if (roll >= 981 && roll <= 990) {
	            	fishLoot = AquacultureItems.fish.getFish("Goldfish");
	            } else if (roll >= 991 && roll <= 999) {
	            	fishLoot = new ItemStack(AquacultureItems.MessageInABottle);
	            } else if (roll >= 1000) {
	            	fishLoot = new ItemStack(AquacultureItems.NeptuniumBar);
	            } else {
	                fishLoot = new ItemStack(Item.appleRed);
	            }
		   } else if(currentBiome instanceof BiomeGenOcean)
	        	{
	            if (roll >= 1 && roll <= 150) {
	            	fishLoot = AquacultureItems.fish.getFish("Salmon");
	            } else if (roll >= 151 && roll <= 300) {
	            	fishLoot = AquacultureItems.fish.getFish("Tuna");
	            } else if (roll >= 301 && roll <= 400) {
	            	fishLoot = AquacultureItems.fish.getFish("Red Grouper");
	            } else if (roll >= 401 && roll <= 500) {
	            	fishLoot = AquacultureItems.fish.getFish("Swordfish");
	            } else if (roll >= 501 && roll <= 570) {
	            	fishLoot = AquacultureItems.fish.getFish("Shark");
	            } else if (roll >= 571 && roll <= 640) {
	            	fishLoot = AquacultureItems.fish.getFish("Whale");
	            } else if (roll >= 641 && roll <= 700) {
	            	fishLoot = AquacultureItems.fish.getFish("Squid");
	            } else if (roll >= 701 && roll <= 800) {
	            	fishLoot = new ItemStack(AquacultureItems.Algae);
	            } else if (roll >= 801 && roll <= 825) {
	            	fishLoot = new ItemStack(Item.stick);
	            } else if (roll >= 826 && roll <= 850) {
	            	fishLoot = new ItemStack(AquacultureItems.Driftwood);
	            } else if (roll >= 851 && roll <= 875) {
	            	fishLoot = new ItemStack(Item.bootsLeather);
	            } else if (roll >= 876 && roll <= 895) {
	            	fishLoot = new ItemStack(AquacultureItems.TinCan);
	            } else if (roll >= 896 && roll <= 925) {
	            	fishLoot = new ItemStack(AquacultureItems.Box);
	            } else if (roll >= 926 && roll <= 949) {
	            	fishLoot = new ItemStack(AquacultureItems.Lockbox);
	            } else if (roll >= 950 && roll <= 959) {
	            	fishLoot = new ItemStack(AquacultureItems.TreasureChest);
	            } else if (roll >= 960 && roll <= 980) {
	            	fishLoot = new ItemStack(Item.bone);
	            } else if (roll >= 981 && roll <= 990) {
	            	fishLoot = AquacultureItems.fish.getFish("Goldfish");
	            } else if (roll >= 991 && roll <= 999) {
	            	fishLoot = new ItemStack(AquacultureItems.MessageInABottle);
	            } else if (roll >= 1000) {
	            	fishLoot = new ItemStack(AquacultureItems.NeptuniumBar);
	            } else {
	                fishLoot = new ItemStack(Item.appleRed);
	            }
	       } else if(currentBiome instanceof BiomeGenMushroomIsland)
	        	{
	            if (roll >= 1 && roll <= 100) {
	            	fishLoot = AquacultureItems.fish.getFish("Bluegill");
	            } else if (roll >= 101 && roll <= 200) {
	            	fishLoot = AquacultureItems.fish.getFish("Bass");
	            } else if (roll >= 201 && roll <= 260) {
	            	fishLoot = AquacultureItems.fish.getFish("Perch");
	            } else if (roll >= 261 && roll <= 320) {
	            	fishLoot = AquacultureItems.fish.getFish("Brown Trout");
	            } else if (roll >= 321 && roll <= 370) {
	            	fishLoot = AquacultureItems.fish.getFish("Catfish");
	            } else if (roll >= 371 && roll <= 420) {
	            	fishLoot = AquacultureItems.fish.getFish("Carp");
	            } else if (roll >= 421 && roll <= 460) {
	            	fishLoot = AquacultureItems.fish.getFish("Muskellunge");
	            } else if (roll >= 461 && roll <= 580) {
	            	fishLoot = AquacultureItems.fish.getFish("Brown Shrooma");
	            } else if (roll >= 581 && roll <= 700) {
	            	fishLoot = AquacultureItems.fish.getFish("Red Shrooma");
	            } else if (roll >= 701 && roll <= 800) {
	            	fishLoot = new ItemStack(AquacultureItems.Algae);
	            } else if (roll >= 801 && roll <= 825) {
	            	fishLoot = new ItemStack(Item.stick);
	            } else if (roll >= 826 && roll <= 850) {
	            	fishLoot = new ItemStack(AquacultureItems.Driftwood);
	            } else if (roll >= 851 && roll <= 875) {
	            	fishLoot = new ItemStack(Item.bootsLeather);
	            } else if (roll >= 876 && roll <= 895) {
	            	fishLoot = new ItemStack(AquacultureItems.TinCan);
	            } else if (roll >= 896 && roll <= 925) {
	            	fishLoot = new ItemStack(AquacultureItems.Box);
	            } else if (roll >= 926 && roll <= 949) {
	            	fishLoot = new ItemStack(AquacultureItems.Lockbox);
	            } else if (roll >= 950 && roll <= 959) {
	            	fishLoot = new ItemStack(AquacultureItems.TreasureChest);
	            } else if (roll >= 960 && roll <= 980) {
	            	fishLoot = new ItemStack(Item.bone);
	            } else if (roll >= 981 && roll <= 990) {
	            	fishLoot = AquacultureItems.fish.getFish("Goldfish");
	            } else if (roll >= 991 && roll <= 999) {
	            	fishLoot = new ItemStack(AquacultureItems.MessageInABottle);
	            } else if (roll >= 1000) {
	            	fishLoot = new ItemStack(AquacultureItems.NeptuniumBar);
	            } else {
	                fishLoot = new ItemStack(Item.appleRed);
	            }
        	}else{
	            if (roll >= 1 && roll <= 150) {
	            	fishLoot = AquacultureItems.fish.getFish("Bluegill");
	            } else if (roll >= 151 && roll <= 300) {
	            	fishLoot = AquacultureItems.fish.getFish("Bass");
	            } else if (roll >= 301 && roll <= 400) {
	            	fishLoot = AquacultureItems.fish.getFish("Perch");
	            } else if (roll >= 401 && roll <= 500) {
	            	fishLoot = AquacultureItems.fish.getFish("Brown Trout");
	            } else if (roll >= 501 && roll <= 570) {
	            	fishLoot = AquacultureItems.fish.getFish("Catfish");
	            } else if (roll >= 571 && roll <= 640) {
	            	fishLoot = AquacultureItems.fish.getFish("Carp");
	            } else if (roll >= 641 && roll <= 700) {
	            	fishLoot = AquacultureItems.fish.getFish("Muskellunge");
	            } else if (roll >= 701 && roll <= 800) {
	            	fishLoot = new ItemStack(AquacultureItems.Algae);
	            } else if (roll >= 801 && roll <= 825) {
	            	fishLoot = new ItemStack(Item.stick);
	            } else if (roll >= 826 && roll <= 850) {
	            	fishLoot = new ItemStack(AquacultureItems.Driftwood);
	            } else if (roll >= 851 && roll <= 875) {
	            	fishLoot = new ItemStack(Item.bootsLeather);
	            } else if (roll >= 876 && roll <= 895) {
	            	fishLoot = new ItemStack(AquacultureItems.TinCan);
	            } else if (roll >= 896 && roll <= 925) {
	            	fishLoot = new ItemStack(AquacultureItems.Box);
	            } else if (roll >= 926 && roll <= 949) {
	            	fishLoot = new ItemStack(AquacultureItems.Lockbox);
	            } else if (roll >= 950 && roll <= 959) {
	            	fishLoot = new ItemStack(AquacultureItems.TreasureChest);
	            } else if (roll >= 960 && roll <= 980) {
	            	fishLoot = new ItemStack(Item.bone);
	            } else if (roll >= 981 && roll <= 990) {
	            	fishLoot = AquacultureItems.fish.getFish("Goldfish");
	            } else if (roll >= 991 && roll <= 999) {
	            	fishLoot = new ItemStack(AquacultureItems.MessageInABottle);
	            } else if (roll >= 1000) {
	            	fishLoot = new ItemStack(AquacultureItems.NeptuniumBar);
	            } else {
	                fishLoot = new ItemStack(Item.appleRed);
	            }
	            
        	}
        	} while(fishLoot.itemID != AquacultureItems.NeptuniumBar.itemID);
        	
        	EntityItem entityitem = new EntityItem(worldObj, posX, posY, posZ, fishLoot);
        	
            double d1 = angler.posX - posX;
            double d3 = angler.posY - posY;
            double d5 = angler.posZ - posZ;
            double d7 = MathHelper.sqrt_double(d1 * d1 + d3 * d3 + d5 * d5);
            double d9 = 0.10000000000000001D;
            entityitem.motionX = d1 * d9;
            entityitem.motionY = d3 * d9 + (double)MathHelper.sqrt_double(d7) * 0.080000000000000002D;
            entityitem.motionZ = d5 * d9;
            worldObj.spawnEntityInWorld(entityitem);
            angler.addStat(StatList.fishCaughtStat, 1);
            byte0 = 1;
        }
        if (inGround)
        {
            byte0 = 2;
        }
        setDead();
        angler.fishEntity = null;
        return byte0;
    }
}

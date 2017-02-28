package com.teammetallurgy.aquaculture.items.meta;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Optional.Interface;
import net.minecraftforge.fml.common.Optional.Method;

import squeek.applecore.api.food.FoodValues;
import squeek.applecore.api.food.IEdible;
import squeek.applecore.api.food.ItemFoodProxy;

@Interface(iface = "squeek.applecore.api.food.IEdible", modid = "AppleCore")
public class SubItemFood implements IEdible {
    public int damage;
    public MetaItemFood item;
    public String unlocalizedName;
    public final int itemUseDuration;
    private final int healAmount;
    private final float saturationModifier;
    private final boolean isWolfsFavoriteMeat;
    private boolean alwaysEdible;
    private Potion potion;
    private int potionDuration;
    private int potionAmplifier;
    private float potionEffectProbability;
    private int eatTime;

    public SubItemFood(MetaItemFood metaItem, int heal, float saturation, boolean isWolfFood) {
        item = metaItem;
        damage = item.addSubItem(this);

        this.itemUseDuration = 32;
        this.healAmount = heal;
        this.isWolfsFavoriteMeat = isWolfFood;
        this.saturationModifier = saturation;
        this.eatTime = 32;
    }

    public SubItemFood(MetaItemFood metaItem, int heal, boolean isWolfFood) {
        this(metaItem, heal, 0.6F, isWolfFood);
    }

    public SubItemFood setCreativeTab(CreativeTabs tab) {
        item.setCreativeTab(tab);
        return this;
    }

    public SubItemFood setUnlocalizedName(String name) {
        this.unlocalizedName = name;
        return this;
    }

    public ItemStack getItemStack() {
        return getItemStack(1);
    }

    public ItemStack getItemStack(int amount) {
        return new ItemStack(item, amount, damage);
    }

    public String getUnlocalizedName(ItemStack itemStack) {
        return unlocalizedName;
    }

    public SubItemFood setEatTime(int eatTime) {
        this.eatTime = eatTime;
        return this;
    }

    @Override
    @Method(modid = "AppleCore")
    public FoodValues getFoodValues(ItemStack itemStack) {
        return new FoodValues(this.getHealAmount(), this.getSaturationModifier());
    }

    @Method(modid = "AppleCore")
    public void onEatenAppleCore(ItemStack itemStack, EntityPlayer player) {
        player.getFoodStats().addStats(new ItemFoodProxy(this), itemStack);
    }

    public ItemStack onEaten(ItemStack par1ItemStack, World par2World, EntityLivingBase entity) {
        par1ItemStack.shrink(1);
        if (!(entity instanceof EntityPlayer)) {
            return par1ItemStack;
        }

        EntityPlayer player = (EntityPlayer) entity;

        if (Loader.isModLoaded("AppleCore"))
            onEatenAppleCore(par1ItemStack, player);
        else
            player.getFoodStats().addStats(this.getHealAmount(), this.getSaturationModifier());
        par2World.playSound(null, player.posX, player.posY, player.posZ, SoundEvents.ENTITY_PLAYER_BURP, SoundCategory.PLAYERS, 0.5F, par2World.rand.nextFloat() * 0.1F + 0.9F);
        this.onFoodEaten(par1ItemStack, par2World, player);
        return par1ItemStack;
    }

    protected void onFoodEaten(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
        if (!par2World.isRemote && this.potion != null && par2World.rand.nextFloat() < this.potionEffectProbability) {
            par3EntityPlayer.addPotionEffect(new PotionEffect(this.potion, this.potionDuration * 20, this.potionAmplifier));
        }
    }

    public int getMaxItemUseDuration(ItemStack par1ItemStack) {
        return eatTime;
    }

    public EnumAction getItemUseAction(ItemStack par1ItemStack) {
        return EnumAction.EAT;
    }

    public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer, EnumHand hand) {
        if (par3EntityPlayer.canEat(this.alwaysEdible)) {
            par3EntityPlayer.setActiveHand(hand);
        }

        return par1ItemStack;
    }

    public int getHealAmount() {
        return this.healAmount;
    }

    public float getSaturationModifier() {
        return this.saturationModifier;
    }

    public boolean isWolfsFavoriteMeat() {
        return this.isWolfsFavoriteMeat;
    }

    public SubItemFood setPotionEffect(Potion potion, int duration, int amplifier, float effectPribability) {
        this.potion = potion;
        this.potionDuration = duration;
        this.potionAmplifier = amplifier;
        this.potionEffectProbability = effectPribability;
        return this;
    }

    public SubItemFood setAlwaysEdible() {
        this.alwaysEdible = true;
        return this;
    }
}

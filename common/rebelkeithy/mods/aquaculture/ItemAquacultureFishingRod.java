package rebelkeithy.mods.aquaculture;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.ForgeSubscribe;
import rebelkeithy.mods.aquaculture.enchantments.AquacultureEnchants;
import rebelkeithy.mods.aquaculture.events.FishingCastEvent;

public class ItemAquacultureFishingRod extends Item {
    public Icon usingIcon;

    public ItemAquacultureFishingRod(int i, int d) {
        super(i);
        setMaxDamage(d);
        setMaxStackSize(1);
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SideOnly(Side.CLIENT)

    /**
     * Returns true if this item should be rotated by 180 degrees around the Y axis when being held in an entities
     * hands.
     */
    public boolean shouldRotateAroundWhenRendering() {
        return true;
    }


    public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer entityplayer) {
        if (entityplayer.fishEntity != null) {
            int i = entityplayer.fishEntity.catchFish();
            itemstack.damageItem(i, entityplayer);
            entityplayer.swingItem();

            if (!itemstack.hasTagCompound())
                itemstack.setTagCompound(new NBTTagCompound());

            NBTTagCompound tag = itemstack.getTagCompound();
            tag.setBoolean("using", false);
        } else {
            world.playSoundAtEntity(entityplayer, "random.bow", 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));
            if (!world.isRemote) {
                world.spawnEntityInWorld(new EntityCustomFishHook(world, entityplayer, itemstack));
            }
            entityplayer.swingItem();

            if (!itemstack.hasTagCompound())
                itemstack.setTagCompound(new NBTTagCompound());

            NBTTagCompound tag = itemstack.getTagCompound();
            tag.setBoolean("using", true);
        }
        return itemstack;
    }

    public Icon getIcon(ItemStack stack, int renderPass, EntityPlayer player, ItemStack usingItem, int useRemaining) {
        if (!stack.hasTagCompound())
            stack.setTagCompound(new NBTTagCompound());

        NBTTagCompound tag = stack.getTagCompound();

        if (tag.hasKey("using")) ;
        {
            boolean using = tag.getBoolean("using");

            if (using)
                return usingIcon;
        }

        return itemIcon;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void registerIcons(IconRegister par1IconRegister) {
        super.registerIcons(par1IconRegister);

        usingIcon = par1IconRegister.registerIcon("Aquaculture:IronFishingRodUsing");
    }


    /**
     * Point of Concept
     */
    @ForgeSubscribe
    public void casted(FishingCastEvent event) {
        int longCastLevel = EnchantmentHelper.getEnchantmentLevel(AquacultureEnchants.Longcast.effectId, event.itemStack);

        if (longCastLevel > 0) {
            event.velocity.addVector(longCastLevel, longCastLevel, longCastLevel);
        }
    }
}
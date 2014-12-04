package rebelkeithy.mods.aquaculture.items;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;

public class NeptuniumArmor extends ItemArmor {
    private String texture;

    public NeptuniumArmor(ArmorMaterial par2EnumArmorMaterial, int par3, int par4) {
        super(par2EnumArmorMaterial, par3, par4);
    }

    public Item setArmorTexture(String string) {
        texture = string;
        return this;
    }

    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, int slot, String layer) {
        return "aquaculture:armor/" + texture + ".png";
    }

    @Override
    public Item setUnlocalizedName(String par1Str) {
        super.setUnlocalizedName(par1Str);
        this.setTextureName(par1Str);
        return this;
    }

    @Override
    public void registerIcons(IIconRegister par1IconRegister) {
        this.itemIcon = par1IconRegister.registerIcon("aquaculture:" + this.getIconString());
    }
}

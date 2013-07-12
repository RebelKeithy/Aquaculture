package rebelkeithy.mods.aquaculture.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;

public class NeptuniumArmor extends ItemArmor
{
	private String texture;

	public NeptuniumArmor(int par1, EnumArmorMaterial par2EnumArmorMaterial, int par3, int par4) 
	{
		super(par1, par2EnumArmorMaterial, par3, par4);
	}

	public Item setArmorTexture(String string) 
	{
		texture = string;
		return this;
	}

	@Override
    public String getArmorTexture(ItemStack stack, Entity entity, int slot, int layer)
    {
		return "aquaculture:armor/" + texture + ".png";
	}

    /**
     * Sets the unlocalized name of this item to the string passed as the parameter, prefixed by "item."
     */
    public Item setUnlocalizedName(String par1Str)
    {
        super.setUnlocalizedName(par1Str);
        this.setTextureName(par1Str);
        return this;
    }
	
    public Item setTextureName(String par1Str)
    {
        super.func_111206_d(par1Str);
        return this;
    }

    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister par1IconRegister)
    {
        this.itemIcon = par1IconRegister.registerIcon("aquaculture:" + this.func_111208_A());
    }
}

package rebelkeithy.mods.aquaculture;

import net.minecraft.entity.Entity;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.IArmorTextureProvider;

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
		return "/armor/" + texture + ".png";
	}




}

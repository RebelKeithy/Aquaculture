package rebelkeithy.mods.aquaculture.items;

import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemSword;

public class AquaItemSword extends ItemSword
{

	public AquaItemSword(int par1, EnumToolMaterial par2EnumToolMaterial)
	{
		super(par1, par2EnumToolMaterial);
	}

    /**
     * Sets the unlocalized name of this item to the string passed as the parameter, prefixed by "item."
     */
    public Item setUnlocalizedName(String par1Str)
    {
        super.setUnlocalizedName(par1Str);
        this.setTextureName(par1Str.replaceAll("\\s",""));
        return this;
    }
	
    public Item setTextureName(String par1Str)
    {
        super.func_111206_d(par1Str);
        return this;
    }
}

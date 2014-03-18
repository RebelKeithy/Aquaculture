package rebelkeithy.mods.aquaculture.items;

import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemSpade;

public class AquaItemSpade extends ItemSpade
{

	public AquaItemSpade(int par1, EnumToolMaterial par2EnumToolMaterial)
	{
		super(par1, par2EnumToolMaterial);
	}

    /**
     * Sets the unlocalized name of this item to the string passed as the parameter, prefixed by "item."
     */
	@Override
    public Item setUnlocalizedName(String par1Str)
    {
        super.setUnlocalizedName(par1Str);
        this.setTextureName("aquaculture:" + par1Str.replaceAll("\\s",""));
        return this;
    }
	
}

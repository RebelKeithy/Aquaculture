package rebelkeithy.mods.aquaculture;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class AquacultureItem extends Item
{

	public AquacultureItem(int par1) 
	{
		super(par1);
	}

    /**
     * Sets the unlocalized name of this item to the string passed as the parameter, prefixed by "item."
     */
    public Item setUnlocalizedName(String par1Str)
    {
        super.setUnlocalizedName(par1Str);
        this.setTextureName("aquaculture:" + par1Str.replaceAll("\\s",""));
        return this;
    }
	
    public Item setTextureName(String par1Str)
    {
        super.func_111206_d(par1Str);
        return this;
    }
}

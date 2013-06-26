package rebelkeithy.mods.aquaculture;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class AquacultureTab extends CreativeTabs
{
	int itemID;

	public AquacultureTab(String par2Str) 
	{
		super(par2Str);
	}
	
	public void setItemID(int id)
	{
		itemID = id;
	}


    @SideOnly(Side.CLIENT)

    /**
     * the itemID for the item to be displayed on the tab
     */
    public int getTabIconItemIndex()
    {
    	if(Item.itemsList[itemID] != null)
    		return itemID;
    	else
    		return 1;
    }
}

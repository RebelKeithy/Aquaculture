package rebelkeithy.mods.aquaculture;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class AquacultureTab extends CreativeTabs {
	Item tabItem;

	public AquacultureTab(String par2Str) {
		super(par2Str);
	}

	public void setItem(Item item) {
		tabItem = item;
	}

	@Override
	public String getTranslatedTabLabel() {
		return LocalizationHelper.localize("itemGroup.Aquaculture");
	}

	/**
	 * The item to be displayed on the tab
	 */
	@SideOnly(Side.CLIENT)
	@Override
	public Item getTabIconItem() {
		return tabItem;
	}
}

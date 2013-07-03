package rebelkeithy.mods.aquaculture.enchantments;

import cpw.mods.fml.common.registry.LanguageRegistry;

public class EnchantmentHeavyLine extends EnchantmentFishingPole {

	public EnchantmentHeavyLine(int id, int weight) {
		super(id, weight);
		setName("heavyLine");
		LanguageRegistry.instance().addStringLocalization("enchantment.heavyLine", "Heavy Line");
	}
	
    @Override
    public int getMaxLevel() {
        return 5;
    }
    
    public int getMinEnchantability(int par1)
    {
        return 5 + 20 * (par1 - 1);
    }

    public int getMaxEnchantability(int par1)
    {
        return super.getMinEnchantability(par1) + 50;
    }

}

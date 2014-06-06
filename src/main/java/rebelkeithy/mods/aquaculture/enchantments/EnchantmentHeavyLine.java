package rebelkeithy.mods.aquaculture.enchantments;

import cpw.mods.fml.common.registry.LanguageRegistry;

public class EnchantmentHeavyLine extends EnchantmentFishingPole {

	public EnchantmentHeavyLine(int id, int weight) {
		super(id, weight);
		setName("atumHeavyLine");
	}

	@Override
	public int getMaxLevel() {
		return 5;
	}

	@Override
	public int getMinEnchantability(int par1) {
		return 5 + 20 * (par1 - 1);
	}

	@Override
	public int getMaxEnchantability(int par1) {
		return super.getMinEnchantability(par1) + 50;
	}
}

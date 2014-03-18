package rebelkeithy.mods.aquaculture.enchantments;

import rebelkeithy.mods.aquaculture.LocalizationHelper;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class EnchantmentHeavyLine extends EnchantmentFishingPole {

	public EnchantmentHeavyLine(int id, int weight) {
		super(id, weight);
		setName("heavyLine");
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

	@Override
	public String getTranslatedName(int par1) {
		return LocalizationHelper.localize("enchantment.heavyLine") + RomanNumeral.convertToRoman(par1);
	}
}

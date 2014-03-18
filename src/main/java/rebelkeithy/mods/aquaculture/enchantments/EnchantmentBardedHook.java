package rebelkeithy.mods.aquaculture.enchantments;

import net.minecraft.enchantment.Enchantment;
import cpw.mods.fml.common.registry.LanguageRegistry;

/**
 * @author Freyja
 */
public class EnchantmentBardedHook extends EnchantmentFishingPole {

	public EnchantmentBardedHook(int id, int weight) {
		super(id, weight);
		setName("barbedHook");
	}

	@Override
	public int getMaxLevel() {
		return 5;
	}

	@Override
	public boolean canApplyTogether(Enchantment enchantment) {
		return !((enchantment instanceof EnchantmentDoubleHook) || (enchantment instanceof EnchantmentBardedHook));
	}

	@Override
	public int getMinEnchantability(int par1) {
		return 15;
	}

	@Override
	public int getMaxEnchantability(int par1) {
		return super.getMinEnchantability(par1) + 50;
	}
	
	/*@Override
	public String getTranslatedName(int par1) {
		return LocalizationHelper.localize("enchantment.barbedHook") + RomanNumeral.convertToRoman(par1);
	}*/
}

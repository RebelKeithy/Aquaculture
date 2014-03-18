package rebelkeithy.mods.aquaculture.enchantments;

import net.minecraft.enchantment.Enchantment;
import cpw.mods.fml.common.registry.LanguageRegistry;

/**
 * @author Freyja
 */
public class EnchantmentFastcast extends EnchantmentFishingPole {

	public EnchantmentFastcast(int id, int weight) {
		super(id, weight);
		setName("fastCast");
	}

	@Override
	public int getMaxLevel() {
		return 5;
	}

	@Override
	public boolean canApplyTogether(Enchantment enchantment) {
		return !((enchantment instanceof EnchantmentShortCast) || (enchantment instanceof EnchantmentLongCast) || (enchantment instanceof EnchantmentFastcast));
	}

	/*@Override
	public String getTranslatedName(int par1) {
		return LocalizationHelper.localize("enchantment.fastCast") + RomanNumeral.convertToRoman(par1);
	}*/
}

package rebelkeithy.mods.aquaculture.enchantments;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraftforge.common.util.EnumHelper;

public class EnchantmentSlotReserve extends Enchantment {

	private static EnumEnchantmentType enumSlotReserve = EnumHelper.addEnchantmentType("slotReserve");
	
	public EnchantmentSlotReserve(int par1) {
		super(par1, 0, enumSlotReserve);
	}

}

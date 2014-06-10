package rebelkeithy.mods.aquaculture.enchantments;

import net.minecraft.enchantment.EnchantmentDigging;
import net.minecraft.item.ItemStack;
import rebelkeithy.mods.aquaculture.items.ItemAdminFishingRod;
import rebelkeithy.mods.aquaculture.items.ItemAquacultureFishingRod;

public class EnchantmentEfficiencyFishing extends EnchantmentDigging {

	protected EnchantmentEfficiencyFishing(int id, int weight) {
		super(id, weight);
	}

	@Override
	public int getMaxLevel() {
		return 5;
	}

	@Override
	public boolean canApply(ItemStack itemStack) {
		return ((itemStack.getItem() instanceof ItemAquacultureFishingRod) || (itemStack.getItem() instanceof ItemAdminFishingRod));
	}

	@Override
	public boolean canApplyAtEnchantingTable(ItemStack stack) {
		return canApply(stack);
	}
}

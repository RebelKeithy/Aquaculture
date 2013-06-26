package rebelkeithy.mods.aquaculture.enchantments;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.ItemStack;
import rebelkeithy.mods.aquaculture.ItemAdminFishingRod;
import rebelkeithy.mods.aquaculture.ItemAquacultureFishingRod;

/**
 * @author Freyja
 */
public abstract class EnchantmentFishingPole extends Enchantment {

    public EnchantmentFishingPole(int id, int weight) {
        super(id, weight, AquacultureEnchants.enumFishingPole);
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

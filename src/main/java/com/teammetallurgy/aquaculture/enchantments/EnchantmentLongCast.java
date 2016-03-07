package rebelkeithy.mods.aquaculture.enchantments;

import net.minecraft.enchantment.Enchantment;

/**
 * @author Freyja
 */
public class EnchantmentLongCast extends EnchantmentFishingPole {

    public EnchantmentLongCast(int id, int weight) {
        super(id, weight);
        setName("aquacultureLongCast");
    }

    @Override
    public int getMaxLevel() {
        return 5;
    }

    @Override
    public boolean canApplyTogether(Enchantment enchantment) {
        return !((enchantment instanceof EnchantmentShortCast) || (enchantment instanceof EnchantmentLongCast));
    }

    @Override
    public int getMinEnchantability(int par1) {
        return 1 + 10 * (par1 - 1);
    }

    @Override
    public int getMaxEnchantability(int par1) {
        return super.getMinEnchantability(par1) + 50;
    }
}

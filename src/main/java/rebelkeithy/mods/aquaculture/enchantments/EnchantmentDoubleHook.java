package rebelkeithy.mods.aquaculture.enchantments;

import net.minecraft.enchantment.Enchantment;

/**
 * @author Freyja
 */
public class EnchantmentDoubleHook extends EnchantmentFishingPole {

    public EnchantmentDoubleHook(int id, int weight) {
        super(id, weight);
        setName("aquacultureDoubleHook");
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
}

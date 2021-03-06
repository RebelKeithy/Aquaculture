package rebelkeithy.mods.aquaculture.enchantments;

import cpw.mods.fml.common.registry.LanguageRegistry;
import net.minecraft.enchantment.Enchantment;

/**
 * @author Freyja
 */
public class EnchantmentMagnetic extends EnchantmentFishingPole {

    public EnchantmentMagnetic(int id, int weight) {
        super(id, weight);
        setName("magnetic");
        LanguageRegistry.instance().addStringLocalization("enchantment.magnetic", "Magnetic");
    }

    @Override
    public int getMaxLevel() {
        return 5;
    }

    @Override
    public boolean canApplyTogether(Enchantment enchantment) {
        return !((enchantment instanceof EnchantmentMagnetic) || (enchantment instanceof EnchantmentAppealing));
    }
    
    public int getMinEnchantability(int par1)
    {
        return 12 + (par1 - 1) * 20;
    }

    public int getMaxEnchantability(int par1)
    {
        return this.getMinEnchantability(par1) + 25;
    }
}

package rebelkeithy.mods.aquaculture.enchantments;

import cpw.mods.fml.common.registry.LanguageRegistry;
import net.minecraft.enchantment.Enchantment;

/**
 * @author Freyja
 */
public class EnchantmentShortCast extends EnchantmentFishingPole {

    public EnchantmentShortCast(int id, int weight) {
        super(id, weight);
        setName("shortCast");
        LanguageRegistry.instance().addStringLocalization("enchantment.shortCast", "Short Cast");
    }

    @Override
    public int getMaxLevel() {
        return 5;
    }

    @Override
    public boolean canApplyTogether(Enchantment enchantment) {
        return !((enchantment instanceof EnchantmentShortCast) || (enchantment instanceof EnchantmentLongCast) || (enchantment instanceof EnchantmentFastcast));
    }
    
    public int getMinEnchantability(int par1)
    {
        return 1 + 10 * (par1 - 1);
    }

    public int getMaxEnchantability(int par1)
    {
        return super.getMinEnchantability(par1) + 50;
    }
}

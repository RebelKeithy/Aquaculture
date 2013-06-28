package rebelkeithy.mods.aquaculture.enchantments;

import cpw.mods.fml.common.registry.LanguageRegistry;
import net.minecraft.enchantment.Enchantment;

/**
 * @author Freyja
 */
public class EnchantmentLongCast extends EnchantmentFishingPole {

    public EnchantmentLongCast(int id, int weight) {
        super(id, weight);
        setName("longCast");
        LanguageRegistry.instance().addStringLocalization("enchantment.longCast", "Long Cast");
    }

    @Override
    public int getMaxLevel() {
        return 5;
    }

    @Override
    public boolean canApplyTogether(Enchantment enchantment) {
        return !((enchantment instanceof EnchantmentShortCast) || (enchantment instanceof EnchantmentLongCast) || (enchantment instanceof EnchantmentFastcast));
    }
}

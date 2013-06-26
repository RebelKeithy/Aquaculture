package rebelkeithy.mods.aquaculture.enchantments;

import cpw.mods.fml.common.registry.LanguageRegistry;
import net.minecraft.enchantment.Enchantment;

/**
 * @author Freyja
 */
public class EnchantmentAppealing extends EnchantmentFishingPole {

    public EnchantmentAppealing(int id, int weight) {
        super(id, weight);
        setName("appealing");
        LanguageRegistry.instance().addStringLocalization("enchantment.appealing", "Appealing");
    }

    @Override
    public int getMaxLevel() {
        return 5;
    }

    @Override
    public boolean canApplyTogether(Enchantment enchantment) {
        return !((enchantment instanceof EnchantmentMagnetic) || (enchantment instanceof EnchantmentAppealing));
    }
}

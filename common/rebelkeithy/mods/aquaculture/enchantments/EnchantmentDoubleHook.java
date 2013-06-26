package rebelkeithy.mods.aquaculture.enchantments;

import cpw.mods.fml.common.registry.LanguageRegistry;
import net.minecraft.enchantment.Enchantment;

/**
 * @author Freyja
 */
public class EnchantmentDoubleHook extends EnchantmentFishingPole {

    public EnchantmentDoubleHook(int id, int weight) {
        super(id, weight);
        setName("doubleHook");
        LanguageRegistry.instance().addStringLocalization("enchantment.doubleHook", "Double Hook");
    }

    @Override
    public int getMaxLevel() {
        return 5;
    }

    @Override
    public boolean canApplyTogether(Enchantment enchantment) {
        return !((enchantment instanceof EnchantmentDoubleHook) || (enchantment instanceof EnchantmentBardedHook));
    }
}

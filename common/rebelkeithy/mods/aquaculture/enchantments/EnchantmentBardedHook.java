package rebelkeithy.mods.aquaculture.enchantments;

import cpw.mods.fml.common.registry.LanguageRegistry;
import net.minecraft.enchantment.Enchantment;

/**
 * @author Freyja
 */
public class EnchantmentBardedHook extends EnchantmentFishingPole {

    public EnchantmentBardedHook(int id, int weight) {
        super(id, weight);
        setName("barbedHook");
        LanguageRegistry.instance().addStringLocalization("enchantment.barbedHook", "Barbed Hook");
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

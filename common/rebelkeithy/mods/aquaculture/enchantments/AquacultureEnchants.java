package rebelkeithy.mods.aquaculture.enchantments;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraftforge.common.EnumHelper;
import rebelkeithy.mods.aquaculture.Config;

/**
 * @author Freyja
 */
public class AquacultureEnchants {

    public static EnumEnchantmentType enumFishingPole;

    public static EnchantmentFishingPole appealing;
    public static EnchantmentFishingPole magnetic;
    public static EnchantmentFishingPole longcast;
    public static EnchantmentFishingPole shortcast;
    //public static EnchantmentFishingPole fastcast;
    public static EnchantmentFishingPole doubleHook;
    public static EnchantmentFishingPole barbedHook;
    
    public static Enchantment effeciencyFishing;

    public static void init() {
        enumFishingPole = EnumHelper.addEnchantmentType("fishingPole");

        appealing = new EnchantmentAppealing(Config.appealingID, 3);
        magnetic = new EnchantmentMagnetic(Config.magneticID, 3);
        longcast = new EnchantmentLongCast(Config.longcastID, 7);
        shortcast = new EnchantmentShortCast(Config.shortcastID, 7);
        //fastcast = new EnchantmentFastcast(Config.fastcastID, 7);
        doubleHook = new EnchantmentDoubleHook(Config.doubleHookID, 1);
        barbedHook = new EnchantmentBardedHook(Config.barbedHookID, 1);
        
        effeciencyFishing = new EnchantmentEfficiencyFishing(Config.effeciencyID, 10);
    }
}

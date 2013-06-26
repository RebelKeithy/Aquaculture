package rebelkeithy.mods.aquaculture.enchantments;

import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraftforge.common.EnumHelper;
import rebelkeithy.mods.aquaculture.Config;

/**
 * @author Freyja
 *         Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class AquacultureEnchants {

    public static EnumEnchantmentType enumFishingPole;

    public static EnchantmentFishingPole appealing;
    public static EnchantmentFishingPole magnetic;
    public static EnchantmentFishingPole Longcast;
    public static EnchantmentFishingPole shortcast;
    public static EnchantmentFishingPole fastcast;
    public static EnchantmentFishingPole doubleHook;
    public static EnchantmentFishingPole barbedHook;

    public static void init() {
        enumFishingPole = EnumHelper.addEnchantmentType("fishingPole");

        appealing = new EnchantmentAppealing(Config.appealingID, 3);
        magnetic = new EnchantmentMagnetic(Config.magneticID, 3);
        Longcast = new EnchantmentLongCast(Config.longcastID, 7);
        shortcast = new EnchantmentShortCast(Config.shortcastID, 7);
        fastcast = new EnchantmentFastcast(Config.fastcastID, 7);
        doubleHook = new EnchantmentDoubleHook(Config.doubleHookID, 1);
        barbedHook = new EnchantmentBardedHook(Config.barbedHookID, 1);
    }
}

package com.teammetallurgy.aquaculture.enchantments;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * @author Freyja
 */
public class AquacultureEnchants {

    public static EnumEnchantmentType enumFishingPole;

    public static EnchantmentFishingPole appealing;
    public static EnchantmentFishingPole magnetic;
    public static EnchantmentFishingPole longcast;
    public static EnchantmentFishingPole shortcast;
    // public static EnchantmentFishingPole fastcast;
    public static EnchantmentFishingPole doubleHook;
    public static EnchantmentFishingPole barbedHook;
    public static EnchantmentFishingPole heavyLine;

    public static void init() {
        enumFishingPole = EnumHelper.addEnchantmentType("fishingPole");

        appealing = new EnchantmentAppealing(Enchantment.Rarity.RARE);
        appealing.setRegistryName("appealing");
        GameRegistry.register(appealing);

        magnetic = new EnchantmentMagnetic(Enchantment.Rarity.RARE);
        magnetic.setRegistryName("magnetic");
        GameRegistry.register(magnetic);

        longcast = new EnchantmentLongCast(Enchantment.Rarity.COMMON);
        longcast.setRegistryName("long_cast");
        GameRegistry.register(longcast);

        shortcast = new EnchantmentShortCast(Enchantment.Rarity.COMMON);
        shortcast.setRegistryName("short_cast");
        GameRegistry.register(shortcast);

        // fastcast = new EnchantmentFastcast(Config.fastcastID, 7);

        doubleHook = new EnchantmentDoubleHook(Enchantment.Rarity.VERY_RARE);
        doubleHook.setRegistryName("double_hook");
        GameRegistry.register(doubleHook);

        barbedHook = new EnchantmentBardedHook(Enchantment.Rarity.VERY_RARE);
        barbedHook.setRegistryName("barbed_hook");
        GameRegistry.register(barbedHook);

        heavyLine = new EnchantmentHeavyLine(Enchantment.Rarity.UNCOMMON);
        heavyLine.setRegistryName("heavy_line");
        GameRegistry.register(heavyLine);

    }

}

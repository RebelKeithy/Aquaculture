package rebelkeithy.mods.aquaculture;

import java.io.File;

import rebelkeithy.mods.aquaculture.enchantments.EnchantmentSlotReserve;
import cpw.mods.fml.common.FMLLog;
import net.minecraft.enchantment.Enchantment;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;

public enum Config {
	INSTANCE;
	
	/*
	 * Enchantments
	 */

	public static final String categoryEnchantments = "ENCHANTMENTS";

	public static int appealingID = 60;
	public static int magneticID = 61;
	public static int longcastID = 62;
	public static int shortcastID = 63;
	public static int fastcastID = 64;
	public static int barbedHookID = 65;
	public static int doubleHookID = 66;
	public static int effeciencyID = 67;
	public static int heavyLineID = 68;

	public void init(File file) {
		Configuration config = new Configuration(file);
		config.load();

		appealingID = isIdAvailableOrSet(config.get(categoryEnchantments, "Appealing", appealingID)).getInt();
		magneticID = isIdAvailableOrSet(config.get(categoryEnchantments, "Magnetic", magneticID)).getInt();
		longcastID = isIdAvailableOrSet(config.get(categoryEnchantments, "Long Cast", longcastID)).getInt();
		shortcastID = isIdAvailableOrSet(config.get(categoryEnchantments, "Short Cast", shortcastID)).getInt();
		// fastcastID = isIdAvailableOrSet(config.get(categoryEnchantments, "Fast Cast", fastcastID)).getInt();
		barbedHookID = isIdAvailableOrSet(config.get(categoryEnchantments, "Barbed Hook", barbedHookID)).getInt();
		doubleHookID = isIdAvailableOrSet(config.get(categoryEnchantments, "Double Hook", doubleHookID)).getInt();
		effeciencyID = isIdAvailableOrSet(config.get(categoryEnchantments, "Efficency", effeciencyID)).getInt();
		heavyLineID = isIdAvailableOrSet(config.get(categoryEnchantments, "Heavy Line", heavyLineID)).getInt();

		if(config.hasChanged())
			config.save();
	}

	private Property isIdAvailableOrSet(Property property) {
		
		int propertyInt = property.getInt();
		
		if(Enchantment.enchantmentsList[propertyInt] == null) {
			return property;
		}
		
		for(int i = 0; i < Enchantment.enchantmentsList.length; i++) {
			if(Enchantment.enchantmentsList[i] == null) {
				new EnchantmentSlotReserve(i);
				property.set(i);
				return property;
			}
		}

		throw new RuntimeException("No more enchantment ids are available");
		
	}
}

package rebelkeithy.mods.aquaculture.handlers;

import java.io.File;

import net.minecraftforge.common.config.Configuration;

public class Config {

    /*
     * Enchantments
     */
    public static final String categoryEnchantments = "ENCHANTMENTS";

    public static int appealingID = 60;
    public static int magneticID = 69;
    public static int longcastID = 70;
    public static int shortcastID = 63;
    public static int fastcastID = 64;
    public static int barbedHookID = 65;
    public static int doubleHookID = 66;
    public static int heavyLineID = 68;

    public void init(File file) {
        Configuration config = new Configuration(file);
        config.load();

        appealingID = config.get(categoryEnchantments, "Appealing", appealingID).getInt(appealingID);
        magneticID = config.get(categoryEnchantments, "Magnetic", magneticID).getInt(magneticID);
        longcastID = config.get(categoryEnchantments, "Long Cast", longcastID).getInt(longcastID);
        shortcastID = config.get(categoryEnchantments, "Short Cast", shortcastID).getInt(shortcastID);
        // fastcastID = isIdAvailableOrSet(config.get(categoryEnchantments, "Fast Cast", fastcastID)).getInt();
        barbedHookID = config.get(categoryEnchantments, "Barbed Hook", barbedHookID).getInt(barbedHookID);
        doubleHookID = config.get(categoryEnchantments, "Double Hook", doubleHookID).getInt(doubleHookID);
        heavyLineID = config.get(categoryEnchantments, "Heavy Line", heavyLineID).getInt(heavyLineID);

        if (config.hasChanged())
            config.save();
    }
    
}

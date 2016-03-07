package com.teammetallurgy.aquaculture.handlers;

import java.io.File;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map.Entry;

import com.teammetallurgy.aquaculture.loot.BiomeType;
import com.teammetallurgy.aquaculture.loot.FishLoot;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
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
    
    public static final String categoryFishRarity = "FISH_RARITY";
    public static HashMap<String, Integer> fishRarity = new HashMap<String, Integer>();
    
    public static final String categoryJunkRarity = "JUNK_RARITY";
    public static HashMap<String, Integer> junkRarity = new HashMap<String, Integer>();
    
    static {
    	// Freshwater
    	fishRarity.put("Bluegill", 30);
    	fishRarity.put("Perch", 30);
    	fishRarity.put("Gar", 20);
    	fishRarity.put("Bass", 10);
    	fishRarity.put("Muskellunge", 10);
    	fishRarity.put("Brown Trout", 10);
    	fishRarity.put("Catfish", 5);
    	fishRarity.put("Carp", 1);

        // Saltwater
    	fishRarity.put("Blowfish", 60);
    	fishRarity.put("Red Grouper", 30);
    	fishRarity.put("Salmon",  10);
    	fishRarity.put("Tuna", 10);
    	fishRarity.put("Swordfish", 5);
    	fishRarity.put("Shark", 1);
    	fishRarity.put("Whale", 1);
    	fishRarity.put("Squid", 40);
    	fishRarity.put("Jellyfish", 40);

        // Brackish
    	fishRarity.put("Frog", 1);
    	fishRarity.put("Turtle", 1);
    	fishRarity.put("Leech", 30);

        // Jungle
    	fishRarity.put("Pirahna", 60);
    	fishRarity.put("Electric Eel", 25);
    	fishRarity.put("Tambaqui", 5);
    	fishRarity.put("Arapaima", 1);

        // Tundra
    	fishRarity.put("Cod",  10);
    	fishRarity.put("Pollock",  25);
    	fishRarity.put("Herring",  60);
    	fishRarity.put("Halibut",  1);
    	fishRarity.put("Pink Salmon",  10);
    	fishRarity.put("Rainbow Trout", 20);
    	fishRarity.put("Blackfish", 45);

        // Desert
    	fishRarity.put("Capitaine", 1);
    	fishRarity.put("Boulti", 30);
    	fishRarity.put("Bagrid", 20);
    	fishRarity.put("Syndontis", 60);

        // Mushroom Island
    	fishRarity.put("Red Shrooma", 20);
    	fishRarity.put("Brown Shrooma", 20);
    	
    	// Junk
    	junkRarity.put("Seaweed", 25);
    	junkRarity.put("Algae", 25);
    	junkRarity.put("Driftwood", 25);
    	junkRarity.put("Tin Can", 30);
    	junkRarity.put("Box", 25);
    	junkRarity.put("Lockbox", 23);
    	junkRarity.put("Treasure Chest", 10);
    	junkRarity.put("Stick", 20);
    	junkRarity.put("Bone", 20);
    	junkRarity.put("Leather Boots", 20);
    	junkRarity.put("Apple", 20);
    	junkRarity.put("Goldfish", 10);
    	junkRarity.put("Message In A Bottle", 23);
    	junkRarity.put("Neptunes Bounty", 1);

    }

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

        for(Entry<String, Integer> entry : fishRarity.entrySet()) {
        	int rarity = entry.getValue();
        	String name = entry.getKey().replace(' ', '_').toLowerCase(Locale.US);
        	rarity = config.getInt(name, categoryFishRarity, rarity, 1, 100, "");
        	entry.setValue(rarity);
        }

        for(Entry<String, Integer> entry : junkRarity.entrySet()) {
        	int rarity = entry.getValue();
        	String name = entry.getKey().replace(' ', '_').toLowerCase(Locale.US);
        	rarity = config.getInt(name, categoryJunkRarity, rarity, 1, 100, "");
        	entry.setValue(rarity);
        }

        if (config.hasChanged())
            config.save();
    }
    
}

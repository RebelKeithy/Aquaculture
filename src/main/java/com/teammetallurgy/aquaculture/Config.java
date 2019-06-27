/*
public class Config { //TODO Old config, patially kept for reference atm.
    static { //Should be order in json
        // Saltwater

        // Freshwater
        fishRarity.put("Bluegill", 30);
        fishRarity.put("Perch", 30);
        fishRarity.put("Gar", 20);
        fishRarity.put("Bass", 10);
        fishRarity.put("Muskellunge", 10);
        fishRarity.put("Brown Trout", 10);
        fishRarity.put("Catfish", 5);
        fishRarity.put("Carp", 1);

        // Brackish

        // Topical

        // Arctic
        fishRarity.put("Pollock", 25);
        fishRarity.put("Herring", 60);
        fishRarity.put("Halibut", 1);
        fishRarity.put("Pink Salmon", 10);
        fishRarity.put("Rainbow Trout", 20);
        fishRarity.put("Blackfish", 45);

        // Arid
        fishRarity.put("Capitaine", 1);
        fishRarity.put("Boulti", 30);
        fishRarity.put("Bagrid", 20);
        fishRarity.put("Syndontis", 60);

        // Mushroom
    }

    private static void loadConfiguration() {
        enableNeptuniumArmor = config.get(categoryBasic, "Enable Neptunium armor", true).getBoolean(); //Not possible in 1.14
        enableNeptuniumTools = config.get(categoryBasic, "Enable Neptunium tools", true).getBoolean(); //Not possible in 1.14
        enableNeptuniumLoot = config.get(categoryBasic, "Enable Neptunium loot", true).getBoolean(); //Maybe possible, will have to look into if IConditionSerializer works for loot tables
    }
}*/
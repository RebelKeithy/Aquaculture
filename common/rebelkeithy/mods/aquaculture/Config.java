package rebelkeithy.mods.aquaculture;

import net.minecraft.enchantment.Enchantment;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.Property;

import java.io.File;

public class Config
{

	public static int fishID = 22000;
	/*
	public static int seaweedID = 22001;
	public static int algaeID = 22002;
	public static int whaleSteakID = 22003;
	public static int fishFilletID = 22004;
	public static int cookedWhaleSteakID = 22005;
	public static int cookedFilletID = 22006;
	public static int whaleBurgerID = 22007;
	public static int driftwoodID = 22008;
	public static int neptuniumIngotID = 22009;
	public static int tinCanID = 22010;
	public static int bottleID = 22011;
	public static int boxID = 22012;
	public static int lockBoxID = 22013;
	public static int treasureChestID = 22014;
	*/
	public static int neptuniumPickaxeID = 22015;
	public static int neptuniumShovelID = 22016;
	public static int neptuniumAxeID = 22017;
	public static int neptuniumHoeID = 22018;
	public static int neptuniumSwordID = 22019;
	public static int neptuniumHelmetID = 22020;
	public static int neptuniumPlateID = 22021;
	public static int neptuniumLegsID = 22022;
	public static int neptuniumBootsID = 22023;
	public static int rodID = 22024;
	public static int adminRodID = 22025;
	
	public static int lootID = 22001;
	
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

	public static void init(File file)
	{
		Configuration config = new Configuration(file);
		config.load();

		rodID = config.getItem("Fishing Rod ID", rodID).getInt();
		adminRodID = config.getItem("Admin Rod ID", adminRodID).getInt();
		
		fishID = config.getItem("Fish", fishID).getInt();
		lootID = config.getItem("Loot", lootID).getInt();
		/*
		seaweedID = config.getItem("Seaweed", seaweedID).getInt();
		algaeID = config.getItem("AlgaeID", algaeID).getInt();
		whaleSteakID = config.getItem("Raw Whale Steak", whaleSteakID).getInt();
		fishFilletID = config.getItem("Raw Fish Fillet", fishFilletID).getInt();
		cookedWhaleSteakID = config.getItem("Cooked Whale Steak", cookedWhaleSteakID).getInt();
		cookedFilletID = config.getItem("Cooked Fish Fillet", cookedFilletID).getInt();
		whaleBurgerID = config.getItem("Whale Burger", whaleBurgerID).getInt();

		driftwoodID = config.getItem("Driftwood", driftwoodID).getInt();
		neptuniumIngotID = config.getItem("Neptunium Ingot", neptuniumIngotID).getInt();
		tinCanID = config.getItem("Tin Can", tinCanID).getInt();
		bottleID = config.getItem("Message In A Bottle", bottleID).getInt();
		boxID = config.getItem("Box", boxID).getInt();
		lockBoxID = config.getItem("Lock Box", lockBoxID).getInt();
		treasureChestID = config.getItem("Treasure Chest", treasureChestID).getInt();
		 */
		neptuniumPickaxeID = config.getItem("Neptunium Pickaxe", neptuniumPickaxeID).getInt();
		neptuniumShovelID = config.getItem("Neptunium Shovel", neptuniumShovelID).getInt();
		neptuniumAxeID = config.getItem("Neptunium Axe", neptuniumAxeID).getInt();
		neptuniumHoeID = config.getItem("Neptunium Hoe", neptuniumHoeID).getInt();
		neptuniumSwordID = config.getItem("Neptunium Sword", neptuniumSwordID).getInt();

		neptuniumHelmetID = config.getItem("Neptunium Helmet", neptuniumHelmetID).getInt();
		neptuniumPlateID = config.getItem("Neptunium Chest", neptuniumPlateID).getInt();
		neptuniumLegsID = config.getItem("Neptunium Legs", neptuniumLegsID).getInt();
		neptuniumBootsID = config.getItem("Neptunium Boots", neptuniumBootsID).getInt();
		
        appealingID = isIdAvailableOrSet(config.get(categoryEnchantments, "Appealing", appealingID)).getInt();
        magneticID = isIdAvailableOrSet(config.get(categoryEnchantments, "Magnetic", magneticID)).getInt();
        longcastID = isIdAvailableOrSet(config.get(categoryEnchantments, "Long Cast", longcastID)).getInt();
        shortcastID = isIdAvailableOrSet(config.get(categoryEnchantments, "Short Cast", shortcastID)).getInt();
        fastcastID = isIdAvailableOrSet(config.get(categoryEnchantments, "Fast Cast", fastcastID)).getInt();
        barbedHookID = isIdAvailableOrSet(config.get(categoryEnchantments, "Barbed Hook", barbedHookID)).getInt();
        doubleHookID = isIdAvailableOrSet(config.get(categoryEnchantments, "Double Hook", doubleHookID)).getInt();
		
        if(config.hasChanged()) 
        	config.save();
	}
	
    private static Property isIdAvailableOrSet(Property property) {
        int propertyInt = property.getInt();

        if (Enchantment.enchantmentsList[propertyInt] == null) {
            return property;
        }

        for (int i = 0; i < Enchantment.enchantmentsList.length; i++) {
            if (Enchantment.enchantmentsList[propertyInt] == null) {
                property.set(i);
                return property;
            }
        }

        throw new RuntimeException("No more enchantment ids are available");
    }
}

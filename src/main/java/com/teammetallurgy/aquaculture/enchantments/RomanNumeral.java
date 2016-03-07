package rebelkeithy.mods.aquaculture.enchantments;

public class RomanNumeral {
    public static String convertToRoman(int i) {
        if (i == 1) {
            return " I";
        }
        if (i == 2) {
            return " II";
        }
        if (i == 3) {
            return " III";
        }
        if (i == 4) {
            return " IV";
        }
        if (i == 5) {
            return " V";
        }
        return "";
    }
}

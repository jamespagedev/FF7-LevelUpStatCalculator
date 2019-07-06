/**
 * Created by James Page on 4/15/2017.
 */
package ClassFiles;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class Weapons extends Stats {
    public String weaponName;
    public int numOfMateriaSlots;
    public static final String[][] allWeapons = // order is sync to Calculator.Characters array
            {
                    // Cloud Weapons
                    {
                            "Buster Sword", "Mythril Saber", "Hardedge", "Force Stealer",
                            "Butterfly Edge", "Rune Blade", "Yoshiyuki", "Murasame",
                            "Organics", "Nail Bat", "Enhance Sword", "Crystal Sword",
                            "Apocalypse", "Heaven's Cloud", "Ragnarok", "Ultima Weapon"
                    },
                    // Barret Weapons
                    {
                            "Gatling Gun", "Assault Gun", "Atomic Scissors", "Cannon Ball",
                            "W Machine Gun", "Hard Vulcan", "Enemy Launcher", "Drill Arm",
                            "Chainsaw", "Rocket Punch", "Microlaser", "Solid Bazooka",
                            "A*M Cannon", "Maximum Ray", "Pile Bunker", "Missing Score"
                    },
                    // Tifa Weapons
                    {
                            "Leather Glove", "Metal Knuckle", "Mythril Claw", "Motor Drive",
                            "Grand Glove", "Tiger Fang", "Platinum Fist", "Powersoul",
                            "Diamond Knuckle", "Work Glove", "Dragon Claw", "Kaiser Knuckle",
                            "Crystal Grab", "God's Hand", "Master Fist", "Premium Heart"
                    },
                    // Aeris Weapons
                    {
                            "Guard Rod", "Mythril Rod", "Striking Staff", "Full Metal Staff",
                            "Wizard Staff", "Umbrella", "Fairy Tale", "Prism Staff",
                            "Wiser Staff", "Aurora Rod", "Princess Guard"
                    },
                    // Red XIII Weapons
                    {
                            "Mythril Clip", "Magic Comb", "Diamond Pin", "Silver Barrette",
                            "Seraph Comb", "Platinum Barrette", "Gold Barrette", "Hairpin",
                            "Adaman Clip", "Centclip", "Crystal Comb", "Spriggan Clip",
                            "Behemoth Horn", "Limited Moon"
                    },
                    // Yuffie Weapons
                    {
                            "4-Point Shuriken", "Boomerang", "Wind Slash", "Pinwheel",
                            "Twin Viper", "Razor Ring", "Magic Shuriken", "Hawkeye",
                            "Superball", "Spiral Shuriken", "Crystal Cross", "Rising Sun",
                            "Oritsuru", "Conformer"
                    },
                    // Cait Sith Weapons
                    {
                            "Yellow Megaphone", "White Megaphone", "Green Megaphone", "Black Megaphone",
                            "Silver Megaphone", "Blue Megaphone", "Trumpet Shell", "Red Megaphone",
                            "Gold Megaphone", "Crystal Megaphone", "Battle Trumpet", "Starlight Phone",
                            "HP Shout"
                    },
                    // Vincent Weapons
                    {
                            "Quicksilver", "Peacemaker", "Sniper CR", "Shotgun",
                            "Shortbarrel", "Silver Rifle", "Buntline", "Lariat",
                            "Long Barrel R", "Winchester", "Supershot ST", "Outsider",
                            "Death Penalty"
                    },
                    // Cid Weapons
                    {
                            "Spear", "Slash Lance", "Dragoon Lance", "Trident",
                            "Mop", "Viper Halberd", "Mast Ax", "Javelin",
                            "Partisan", "Scimitar", "Flayer", "Spirit Lance",
                            "Grow Lance", "Venus Gospel"
                    }
            };
    public static final int[][][] allWeaponsAttributes = {
            // Cloud Weapons Attributes
            {
                    // materiaSlots, hp, mp, str, dex, vit, mag, spr, lck
                    {2, 0, 0, 0, 0, 0, 2, 0, 0}, // Cloud's 1st weapon
                    {3, 0, 0, 0, 0, 0, 4, 0, 0}, // Cloud's 2nd weapon
                    {4, 0, 0, 0, 0, 0, 6, 0, 0}, // Cloud's 3rd weapon
                    {3, 0, 0, 0, 0, 0, 7, 0, 0}, // Cloud's 4th weapon
                    {4, 0, 0, 0, 0, 0, 8, 0, 0}, // Cloud's 5th weapon
                    {4, 0, 0, 0, 0, 0, 9, 0, 0}, // Cloud's 6th weapon
                    {2, 0, 0, 0, 0, 0, 9, 0, 0}, // Cloud's 7th weapon
                    {5, 0, 0, 0, 0, 0, 12, 0, 0}, // Cloud's 8th weapon
                    {6, 0, 0, 0, 0, 0, 15, 0, 0}, // Cloud's 9th weapon
                    {0, 0, 0, 0, 0, 0, 0, 0, 0}, // Cloud's 10th weapon
                    {8, 0, 0, 0, 0, 0, 16, 0, 0}, // Cloud's 11th weapon
                    {6, 0, 0, 0, 0, 0, 19, 0, 0}, // Cloud's 12th weapon
                    {3, 0, 0, 0, 0, 0, 43, 16, 0}, // Cloud's 13th weapon
                    {6, 0, 0, 0, 0, 0, 31, 0, 0}, // Cloud's 14th weapon
                    {6, 0, 0, 0, 0, 0, 43, 35, 0}, // Cloud's 15th weapon
                    {8, 0, 0, 0, 0, 0, 51, 24, 0} // Cloud's 16th weapon
            },
            // Barret Weapons Attributes
            {
                    // materiaSlots, hp, mp, str, dex, vit, mag, spr, lck
                    {1, 0, 0, 0, 0, 0, 0, 0, 0}, // Barret's 1st weapon
                    {2, 0, 0, 0, 0, 0, 1, 0, 0}, // Barret's 2nd weapon
                    {4, 0, 0, 0, 0, 0, 4, 0, 0}, // Barret's 3rd weapon
                    {3, 0, 0, 0, 0, 0, 2, 0, 0}, // Barret's 4th weapon
                    {3, 0, 0, 0, 0, 0, 3, 0, 0}, // Barret's 5th weapon
                    {4, 0, 0, 0, 0, 0, 8, 0, 0}, // Barret's 6th weapon
                    {5, 0, 0, 0, 0, 0, 7, 0, 0}, // Barret's 7th weapon
                    {4, 0, 0, 0, 0, 0, 0, 0, 0}, // Barret's 8th weapon
                    {5, 0, 0, 0, 0, 0, 10, 0, 0}, // Barret's 9th weapon
                    {0, 0, 0, 0, 0, 0, 0, 0, 0}, // Barret's 10th weapon
                    {6, 0, 0, 0, 0, 0, 13, 0, 0}, // Barret's 11th weapon
                    {8, 0, 0, 0, 0, 0, 15, 0, 0}, // Barret's 12th weapon
                    {6, 0, 0, 0, 0, 0, 16, 0, 0}, // Barret's 13th weapon
                    {6, 0, 0, 0, 0, 0, 30, 0, 0}, // Barret's 14th weapon
                    {6, 0, 0, 0, 0, 0, 0, 0, 0}, // Barret's 15th weapon
                    {8, 0, 0, 0, 0, 0, 49, 0, 0} // Barret's 16th weapon
            },
            // Tifa Weapons Attributes
            {
                    // materiaSlots, hp, mp, str, dex, vit, mag, spr, lck
                    {1, 0, 0, 0, 0, 0, 0, 0, 0}, // Tifa's 1st weapon
                    {2, 0, 0, 0, 0, 0, 1, 0, 0}, // Tifa's 2nd weapon
                    {3, 0, 0, 0, 0, 0, 3, 0, 0}, // Tifa's 3rd weapon
                    {3, 0, 0, 0, 0, 0, 6, 0, 0}, // Tifa's 4th weapon
                    {4, 0, 0, 0, 0, 0, 6, 0, 0}, // Tifa's 5th weapon
                    {4, 0, 0, 0, 0, 0, 8, 0, 0}, // Tifa's 6th weapon
                    {4, 0, 0, 0, 0, 0, 7, 0, 0}, // Tifa's 7th weapon
                    {4, 0, 0, 0, 0, 0, 7, 0, 0}, // Tifa's 8th weapon
                    {5, 0, 0, 0, 0, 0, 10, 0, 0}, // Tifa's 9th weapon
                    {0, 0, 0, 0, 0, 0, 3, 0, 0}, // Tifa's 10th weapon
                    {6, 0, 0, 0, 0, 0, 13, 0, 0}, // Tifa's 11th weapon
                    {8, 0, 0, 0, 0, 0, 13, 0, 0}, // Tifa's 12th weapon
                    {6, 0, 0, 0, 0, 0, 16, 0, 0}, // Tifa's 13th weapon
                    {4, 0, 0, 0, 0, 0, 34, 0, 0}, // Tifa's 14th weapon
                    {6, 0, 0, 0, 0, 0, 0, 0, 0}, // Tifa's 15th weapon
                    {8, 0, 0, 0, 0, 0, 32, 0, 0} // Tifa's 16th weapon
            },
            // Aeris Weapons Attributes
            {
                    // materiaSlots, hp, mp, str, dex, vit, mag, spr, lck
                    {1, 0, 0, 0, 0, 0, 2, 4, 0}, // Aeris's 1st weapon
                    {2, 0, 0, 0, 0, 0, 3, 0, 0}, // Aeris's 2nd weapon
                    {4, 0, 0, 0, 0, 0, 7, 0, 0}, // Aeris's 3rd weapon
                    {3, 0, 0, 0, 0, 0, 4, 0, 0}, // Aeris's 4th weapon
                    {3, 0, 0, 0, 0, 0, 6, 0, 0}, // Aeris's 5th weapon
                    {0, 0, 0, 0, 0, 20, 10, 0, 0}, // Aeris's 6th weapon
                    {7, 0, 0, 0, 0, 0, 8, 0, 0}, // Aeris's 7th weapon
                    {4, 0, 0, 0, 0, 0, 10, 0, 0}, // Aeris's 8th weapon
                    {4, 0, 0, 0, 0, 0, 7, 0, 0}, // Aeris's 9th weapon
                    {5, 0, 0, 0, 0, 0, 14, 0, 0}, // Aeris's 10th weapon
                    {7, 0, 0, 0, 0, 12, 22, 20, 0}, // Aeris's 11th weapon
            },
            // Red XIII Weapons Attributes
            {
                    // materiaSlots, hp, mp, str, dex, vit, mag, spr, lck
                    {3, 0, 0, 0, 0, 0, 6, 0, 0}, // Red XIII's 1st weapon
                    {3, 0, 0, 0, 0, 0, 4, 0, 0}, // Red XIII's 2nd weapon
                    {4, 0, 0, 0, 0, 0, 8, 0, 0}, // Red XIII's 3rd weapon
                    {4, 0, 0, 0, 0, 0, 10, 0, 0}, // Red XIII's 4th weapon
                    {4, 0, 0, 0, 0, 0, 14, 0, 0}, // Red XIII's 5th weapon
                    {4, 0, 0, 0, 0, 0, 12, 0, 0}, // Red XIII's 6th weapon
                    {5, 0, 0, 0, 0, 0, 13, 0, 0}, // Red XIII's 7th weapon
                    {0, 0, 0, 0, 0, 0, 15, 0, 0}, // Red XIII's 8th weapon
                    {6, 0, 0, 0, 0, 0, 15, 0, 0}, // Red XIII's 9th weapon
                    {8, 0, 0, 0, 0, 0, 22, 0, 0}, // Red XIII's 10th weapon
                    {6, 0, 0, 0, 0, 0, 20, 0, 0}, // Red XIII's 11th weapon
                    {6, 0, 0, 0, 0, 0, 55, 0, 0}, // Red XIII's 12th weapon
                    {6, 0, 0, 0, 0, 35, 26, 18, 0}, // Red XIII's 13th weapon
                    {8, 0, 0, 0, 0, 0, 31, 0, 0}, // Red XIII's 14th weapon
            },
            // Yuffie Weapons Attributes
            {
                    // materiaSlots, hp, mp, str, dex, vit, mag, spr, lck
                    {3, 0, 0, 0, 0, 0, 6, 0, 0}, // Yuffie's 1st weapon
                    {4, 0, 0, 0, 0, 0, 7, 0, 0}, // Yuffie's 2nd weapon
                    {3, 0, 0, 0, 0, 0, 7, 0, 0}, // Yuffie's 3rd weapon
                    {4, 0, 0, 0, 0, 0, 9, 0, 0}, // Yuffie's 4th weapon
                    {4, 0, 0, 0, 0, 0, 8, 0, 0}, // Yuffie's 5th weapon
                    {5, 0, 0, 0, 0, 0, 12, 0, 0}, // Yuffie's 6th weapon
                    {3, 0, 0, 0, 10, 0, 0, 0, 0}, // Yuffie's 7th weapon
                    {6, 0, 0, 0, 0, 0, 14, 0, 0}, // Yuffie's 8th weapon
                    {0, 0, 0, 0, 0, 0, 10, 0, 0}, // Yuffie's 9th weapon
                    {8, 0, 0, 0, 0, 0, 18, 0, 0}, // Yuffie's 10th weapon
                    {6, 0, 0, 0, 0, 0, 18, 0, 0}, // Yuffie's 11th weapon
                    {4, 0, 0, 0, 0, 0, 16, 0, 0}, // Yuffie's 12th weapon
                    {8, 0, 0, 0, 0, 0, 38, 0, 0}, // Yuffie's 13th weapon
                    {8, 0, 0, 0, 0, 0, 42, 0, 0}, // Yuffie's 14th weapon
            },
            // Cait Sith Weapons Attributes
            {
                    // materiaSlots, hp, mp, str, dex, vit, mag, spr, lck
                    {4, 0, 0, 0, 0, 0, 8, 0, 0}, // Cait Sith's 1st weapon
                    {3, 0, 0, 0, 0, 0, 8, 0, 0}, // Cait Sith's 2nd weapon
                    {4, 0, 0, 0, 0, 0, 9, 0, 0}, // Cait Sith's 3rd weapon
                    {4, 0, 0, 0, 0, 0, 10, 0, 0}, // Cait Sith's 4th weapon
                    {8, 0, 0, 0, 0, 0, 14, 0, 0}, // Cait Sith's 5th weapon
                    {5, 0, 0, 0, 0, 0, 10, 0, 0}, // Cait Sith's 6th weapon
                    {0, 0, 0, 0, 0, 0, 2, 0, 0}, // Cait Sith's 7th weapon
                    {6, 0, 0, 0, 0, 0, 15, 0, 0}, // Cait Sith's 8th weapon
                    {8, 0, 0, 0, 0, 0, 28, 0, 0}, // Cait Sith's 9th weapon
                    {6, 0, 0, 0, 0, 0, 20, 0, 0}, // Cait Sith's 10th weapon
                    {6, 0, 0, 0, 0, 0, 0, 0, 0}, // Cait Sith's 11th weapon
                    {8, 0, 0, 0, 0, 30, 31, 0, 0}, // Cait Sith's 12th weapon
                    {8, 0, 0, 0, 0, 0, 44, 0, 0}, // Cait Sith's 13th weapon
            },
            // Vincent Weapons Attributes
            {
                    // materiaSlots, hp, mp, str, dex, vit, mag, spr, lck
                    {4, 0, 0, 0, 0, 0, 10, 0, 0}, // Vincent's 1st weapon
                    {3, 0, 0, 0, 0, 0, 8, 0, 0}, // Vincent's 2nd weapon
                    {4, 0, 0, 0, 0, 0, 7, 0, 0}, // Vincent's 3rd weapon
                    {4, 0, 0, 0, 0, 0, 12, 0, 0}, // Vincent's 4th weapon
                    {5, 0, 0, 0, 0, 0, 14, 0, 0}, // Vincent's 5th weapon
                    {0, 0, 0, 0, 0, 0, 8, 0, 0}, // Vincent's 6th weapon
                    {4, 0, 0, 0, 0, 0, 18, 0, 0}, // Vincent's 7th weapon
                    {6, 0, 0, 0, 0, 0, 16, 0, 0}, // Vincent's 8th weapon
                    {8, 0, 0, 0, 0, 0, 14, 0, 0}, // Vincent's 9th weapon
                    {6, 0, 0, 0, 0, 0, 18, 0, 0}, // Vincent's 10th weapon
                    {6, 0, 0, 0, 0, 0, 52, 0, 0}, // Vincent's 11th weapon
                    {8, 0, 0, 0, 0, 0, 48, 0, 0}, // Vincent's 12th weapon
                    {8, 0, 0, 0, 0, 0, 34, 0, 0}, // Vincent's 13th weapon
            },
            // Cid Weapons Attributes
            {
                    // materiaSlots, hp, mp, str, dex, vit, mag, spr, lck
                    {4, 0, 0, 0, 0, 0, 8, 0, 0}, // Cid's 1st weapon
                    {5, 0, 0, 0, 0, 0, 10, 0, 0}, // Cid's 2nd weapon
                    {8, 0, 0, 0, 0, 0, 7, 0, 0}, // Cid's 3rd weapon
                    {6, 0, 0, 0, 0, 0, 12, 0, 0}, // Cid's 4th weapon
                    {0, 0, 0, 0, 0, 0, 3, 0, 0}, // Cid's 5th weapon
                    {4, 0, 0, 0, 0, 0, 13, 0, 0}, // Cid's 6th weapon
                    {6, 0, 0, 0, 0, 0, 15, 0, 0}, // Cid's 7th weapon
                    {5, 0, 0, 0, 0, 0, 12, 0, 0}, // Cid's 8th weapon
                    {6, 0, 0, 0, 0, 0, 17, 0, 0}, // Cid's 9th weapon
                    {2, 0, 0, 0, 0, 0, 20, 0, 0}, // Cid's 10th weapon
                    {6, 0, 0, 0, 0, 0, 20, 0, 0}, // Cid's 11th weapon
                    {4, 0, 0, 0, 0, 0, 43, 20, 0}, // Cid's 12th weapon
                    {6, 0, 0, 0, 0, 0, 31, 0, 0}, // Cid's 13th weapon
                    {8, 0, 0, 0, 0, 0, 42, 0, 0}, // Cid's 14th weapon
            }
    };

    //**************************************************************************************
    public Weapons(){}

    //**************************************************************************************
    public void setNumOfMateriaSlots(int mSlots){
        this.numOfMateriaSlots = mSlots;
    }

    public int getNumOfMateriaSlots(){
        return this.numOfMateriaSlots;
    }

    //**************************************************************************************
    public static String[] getCloudWeapons(){
        return allWeapons[0];
    }

    public static boolean isCloudWeapon(String wpnName){
        return Arrays.asList(getCloudWeapons()).contains(wpnName);
    }

    public void setCloudWeapon(String wpnName){
        int allWeaponAttributesCharacterIndex = 0;
        int allWeaponAttributesMateriaSlotsIndex = 0;
        int allWeaponAttributesMagicIndex = 6;
        int allWeaponAttributesSpiritIndex = 7;

        for (int weapon = 0; weapon < getCloudWeapons().length; weapon++){
            if(wpnName.equals(getCloudWeapons()[weapon])){
                setNumOfMateriaSlots(getAllWeaponsAttributes()[allWeaponAttributesCharacterIndex][weapon][allWeaponAttributesMateriaSlotsIndex]);
                setMagic(getAllWeaponsAttributes()[allWeaponAttributesCharacterIndex][weapon][allWeaponAttributesMagicIndex]);
                setSpirit(getAllWeaponsAttributes()[allWeaponAttributesCharacterIndex][weapon][allWeaponAttributesSpiritIndex]);
            }
        }
    }

    public static String[] getBarretWeapons(){
        return allWeapons[1];
    }

    public static boolean isBarretWeapon(String wpnName){
        return Arrays.asList(getBarretWeapons()).contains(wpnName);
    }

    public void setBarretWeapon(String wpnName){
        int allWeaponAttributesCharacterIndex = 1;
        int allWeaponAttributesMateriaSlotsIndex = 0;
        int allWeaponAttributesMagicIndex = 6;

        for (int weapon = 0; weapon < getBarretWeapons().length; weapon++){
            if(wpnName.equals(getBarretWeapons()[weapon])){
                setNumOfMateriaSlots(getAllWeaponsAttributes()[allWeaponAttributesCharacterIndex][weapon][allWeaponAttributesMateriaSlotsIndex]);
                setMagic(getAllWeaponsAttributes()[allWeaponAttributesCharacterIndex][weapon][allWeaponAttributesMagicIndex]);
            }
        }
    }

    public static String[] getTifaWeapons(){
        return allWeapons[2];
    }

    public static boolean isTifaWeapon(String wpnName){
        return Arrays.asList(getTifaWeapons()).contains(wpnName);
    }

    public void setTifaWeapon(String wpnName){
        int allWeaponAttributesCharacterIndex = 2;
        int allWeaponAttributesMateriaSlotsIndex = 0;
        int allWeaponAttributesMagicIndex = 6;

        for (int weapon = 0; weapon < getTifaWeapons().length; weapon++){
            if(wpnName.equals(getTifaWeapons()[weapon])){
                setNumOfMateriaSlots(getAllWeaponsAttributes()[allWeaponAttributesCharacterIndex][weapon][allWeaponAttributesMateriaSlotsIndex]);
                setMagic(getAllWeaponsAttributes()[allWeaponAttributesCharacterIndex][weapon][allWeaponAttributesMagicIndex]);
            }
        }
    }

    public static String[] getAerisWeapons(){
        return allWeapons[3];
    }

    public static boolean isAerisWeapon(String wpnName){
        return Arrays.asList(getAerisWeapons()).contains(wpnName);
    }

    public void setAerisWeapon(String wpnName){
        int allWeaponAttributesCharacterIndex = 3;
        int allWeaponAttributesMateriaSlotsIndex = 0;
        int allWeaponAttributesVitalityIndex = 5;
        int allWeaponAttributesMagicIndex = 6;
        int allWeaponAttributesSpiritIndex = 7;

        for (int weapon = 0; weapon < getAerisWeapons().length; weapon++){
            if(wpnName.equals(getAerisWeapons()[weapon])){
                setNumOfMateriaSlots(getAllWeaponsAttributes()[allWeaponAttributesCharacterIndex][weapon][allWeaponAttributesMateriaSlotsIndex]);
                setVitality(getAllWeaponsAttributes()[allWeaponAttributesCharacterIndex][weapon][allWeaponAttributesVitalityIndex]);
                setMagic(getAllWeaponsAttributes()[allWeaponAttributesCharacterIndex][weapon][allWeaponAttributesMagicIndex]);
                setSpirit(getAllWeaponsAttributes()[allWeaponAttributesCharacterIndex][weapon][allWeaponAttributesSpiritIndex]);
            }
        }
    }

    public static String[] getRedXIIIWeapons(){
        return allWeapons[4];
    }

    public static boolean isRedXIIIWeapon(String wpnName){
        return Arrays.asList(getRedXIIIWeapons()).contains(wpnName);
    }

    public void setRedXIIIWeapon(String wpnName){
        int allWeaponAttributesCharacterIndex = 4;
        int allWeaponAttributesMateriaSlotsIndex = 0;
        int allWeaponAttributesVitalityIndex = 5;
        int allWeaponAttributesMagicIndex = 6;
        int allWeaponAttributesSpiritIndex = 7;

        for (int weapon = 0; weapon < getRedXIIIWeapons().length; weapon++){
            if(wpnName.equals(getRedXIIIWeapons()[weapon])){
                setNumOfMateriaSlots(getAllWeaponsAttributes()[allWeaponAttributesCharacterIndex][weapon][allWeaponAttributesMateriaSlotsIndex]);
                setVitality(getAllWeaponsAttributes()[allWeaponAttributesCharacterIndex][weapon][allWeaponAttributesVitalityIndex]);
                setMagic(getAllWeaponsAttributes()[allWeaponAttributesCharacterIndex][weapon][allWeaponAttributesMagicIndex]);
                setSpirit(getAllWeaponsAttributes()[allWeaponAttributesCharacterIndex][weapon][allWeaponAttributesSpiritIndex]);
            }
        }
    }

    public static String[] getYuffieWeapons(){
        return allWeapons[5];
    }

    public static boolean isYuffieWeapon(String wpnName){
        return Arrays.asList(getYuffieWeapons()).contains(wpnName);
    }

    public void setYuffieWeapon(String wpnName){
        int allWeaponAttributesCharacterIndex = 5;
        int allWeaponAttributesMateriaSlotsIndex = 0;
        int allWeaponAttributesDexterityIndex = 4;
        int allWeaponAttributesMagicIndex = 6;

        for (int weapon = 0; weapon < getYuffieWeapons().length; weapon++){
            if(wpnName.equals(getYuffieWeapons()[weapon])){
                setNumOfMateriaSlots(getAllWeaponsAttributes()[allWeaponAttributesCharacterIndex][weapon][allWeaponAttributesMateriaSlotsIndex]);
                setDexterity(getAllWeaponsAttributes()[allWeaponAttributesCharacterIndex][weapon][allWeaponAttributesDexterityIndex]);
                setMagic(getAllWeaponsAttributes()[allWeaponAttributesCharacterIndex][weapon][allWeaponAttributesMagicIndex]);
            }
        }
    }

    public static String[] getCaitSithWeapons(){
        return allWeapons[6];
    }

    public static boolean isCaitSithWeapon(String wpnName){
        return Arrays.asList(getCaitSithWeapons()).contains(wpnName);
    }

    public void setCaitSithWeapon(String wpnName){
        int allWeaponAttributesCharacterIndex = 6;
        int allWeaponAttributesMateriaSlotsIndex = 0;
        int allWeaponAttributesVitalityIndex = 5;
        int allWeaponAttributesMagicIndex = 6;

        for (int weapon = 0; weapon < getCaitSithWeapons().length; weapon++){
            if(wpnName.equals(getCaitSithWeapons()[weapon])){
                setNumOfMateriaSlots(getAllWeaponsAttributes()[allWeaponAttributesCharacterIndex][weapon][allWeaponAttributesMateriaSlotsIndex]);
                setVitality(getAllWeaponsAttributes()[allWeaponAttributesCharacterIndex][weapon][allWeaponAttributesVitalityIndex]);
                setMagic(getAllWeaponsAttributes()[allWeaponAttributesCharacterIndex][weapon][allWeaponAttributesMagicIndex]);
            }
        }
    }

    public static String[] getVincentWeapons(){
        return allWeapons[7];
    }

    public static boolean isVincentWeapon(String wpnName){
        return Arrays.asList(getVincentWeapons()).contains(wpnName);
    }

    public void setVincentWeapon(String wpnName){
        int allWeaponAttributesCharacterIndex = 7;
        int allWeaponAttributesMateriaSlotsIndex = 0;
        int allWeaponAttributesMagicIndex = 6;

        for (int weapon = 0; weapon < getVincentWeapons().length; weapon++){
            if(wpnName.equals(getVincentWeapons()[weapon])){
                setNumOfMateriaSlots(getAllWeaponsAttributes()[allWeaponAttributesCharacterIndex][weapon][allWeaponAttributesMateriaSlotsIndex]);
                setMagic(getAllWeaponsAttributes()[allWeaponAttributesCharacterIndex][weapon][allWeaponAttributesMagicIndex]);
            }
        }
    }

    public static String[] getCidWeapons(){
        return allWeapons[8];
    }

    public static boolean isCidWeapon(String wpnName){
        return Arrays.asList(getCidWeapons()).contains(wpnName);
    }

    public void setCidWeapon(String wpnName){
        int allWeaponAttributesCharacterIndex = 8;
        int allWeaponAttributesMateriaSlotsIndex = 0;
        int allWeaponAttributesMagicIndex = 6;
        int allWeaponAttributesSpiritIndex = 7;

        for (int weapon = 0; weapon < getCidWeapons().length; weapon++){
            if(wpnName.equals(getCidWeapons()[weapon])){
                setNumOfMateriaSlots(getAllWeaponsAttributes()[allWeaponAttributesCharacterIndex][weapon][allWeaponAttributesMateriaSlotsIndex]);
                setMagic(getAllWeaponsAttributes()[allWeaponAttributesCharacterIndex][weapon][allWeaponAttributesMagicIndex]);
                setSpirit(getAllWeaponsAttributes()[allWeaponAttributesCharacterIndex][weapon][allWeaponAttributesSpiritIndex]);
            }
        }
    }

    //**************************************************************************************
    public static String[][] getAllWeapons(){
        return allWeapons;
    }

    public static int[][][] getAllWeaponsAttributes(){
        return allWeaponsAttributes;
    }

    //**************************************************************************************
    public void setWeaponName(String wpnName){
        this.weaponName = wpnName;
    }

    public String getWeaponName(){
        return this.weaponName;
    }

    public void setNoWeapon(){
        this.weaponName = null;
        setNumOfMateriaSlots(0);
        resetStats();
    }

    public void setWeapon(String wpnName){
        // Reset weapon first
        setNoWeapon();

        // Set Weapon Name
        setWeaponName(wpnName);

        List<Predicate<String>> isCharacterWeapon = Arrays.asList(
                Weapons::isCloudWeapon,
                Weapons::isBarretWeapon,
                Weapons::isTifaWeapon,
                Weapons::isAerisWeapon,
                Weapons::isRedXIIIWeapon,
                Weapons::isYuffieWeapon,
                Weapons::isCaitSithWeapon,
                Weapons::isVincentWeapon,
                Weapons::isCidWeapon
        );

        Runnable[] setCharacterWeapon = {
                () -> setCloudWeapon(wpnName),
                () -> setBarretWeapon(wpnName),
                () -> setTifaWeapon(wpnName),
                () -> setAerisWeapon(wpnName),
                () -> setRedXIIIWeapon(wpnName),
                () -> setYuffieWeapon(wpnName),
                () -> setCaitSithWeapon(wpnName),
                () -> setVincentWeapon(wpnName),
                () -> setCidWeapon(wpnName),
        };
        for (int characterIndex = 0; characterIndex < isCharacterWeapon.size(); characterIndex++){
            if (isCharacterWeapon.get(characterIndex).test(wpnName)){
                setCharacterWeapon[characterIndex].run();
            }
        }
    }
}
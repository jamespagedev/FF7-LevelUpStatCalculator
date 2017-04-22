/**
 * Created by James Page on 4/15/2017.
 */
package Main;

public class Weapons extends Stats {
    private String weapon;
    private int materiaSlots;
    private String[] charWeaponList;
    private final String[][] allWeapons = // order is sync to Calculator.Characters array
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

    //**************************************************************************************

    Weapons(){}

    //**************************************************************************************

    private void setMateriaSlots(int mSlots){
        this.materiaSlots = mSlots;
    }

    public int getMateriaSlots(){
        return this.materiaSlots;
    }

    //**************************************************************************************

    public String[][] getAllWeapons(){
        return this.allWeapons;
    }

    //**************************************************************************************

    public void setCharWeaponList(String character){
        // Note: order must match between characters and array of all weapons

        if (character.equals(Calculator.Characters[0])){
            this.charWeaponList = allWeapons[0];
        } else if (character.equals(Calculator.Characters[1])){
            this.charWeaponList = allWeapons[1];
        } else if (character.equals(Calculator.Characters[2])){
            this.charWeaponList = allWeapons[2];
        } else if (character.equals(Calculator.Characters[3])){
            this.charWeaponList = allWeapons[3];
        } else if (character.equals(Calculator.Characters[4])){
            this.charWeaponList = allWeapons[4];
        } else if (character.equals(Calculator.Characters[5])){
            this.charWeaponList = allWeapons[5];
        } else if (character.equals(Calculator.Characters[6])){
            this.charWeaponList = allWeapons[6];
        } else if (character.equals(Calculator.Characters[7])){
            this.charWeaponList = allWeapons[7];
        } else if (character.equals(Calculator.Characters[8])){
            this.charWeaponList = allWeapons[8];
        }
    }

    public String[] getCharWeaponList(){
        return this.charWeaponList;
    }

    //**************************************************************************************

    public void setWeapon(String wpn){
        // Reset weapon first
        this.weapon = null;
        setMateriaSlots(0);
        resetStats();

        // Cloud Weapons
        if (wpn.equals(allWeapons[0][0])){
            this.weapon = wpn;
            setMateriaSlots(2);
            setMagic(2);
        } else if (wpn.equals(allWeapons[0][1])){
            this.weapon = wpn;
            setMateriaSlots(3);
            setMagic(4);
        } else if (wpn.equals(allWeapons[0][2])){
            this.weapon = wpn;
            setMateriaSlots(4);
            setMagic(6);
        } else if (wpn.equals(allWeapons[0][3])){
            this.weapon = wpn;
            setMateriaSlots(3);
            setMagic(7);
        } else if (wpn.equals(allWeapons[0][4])){
            this.weapon = wpn;
            setMateriaSlots(4);
            setMagic(8);
        } else if (wpn.equals(allWeapons[0][5])){
            this.weapon = wpn;
            setMateriaSlots(4);
            setMagic(9);
        } else if (wpn.equals(allWeapons[0][6])){
            this.weapon = wpn;
            setMateriaSlots(2);
            setMagic(9);
        } else if (wpn.equals(allWeapons[0][7])){
            this.weapon = wpn;
            setMateriaSlots(5);
            setMagic(12);
        } else if (wpn.equals(allWeapons[0][8])){
            this.weapon = wpn;
            setMateriaSlots(6);
            setMagic(15);
        } else if (wpn.equals(allWeapons[0][9])){
            this.weapon = wpn;
        } else if (wpn.equals(allWeapons[0][10])){
            this.weapon = wpn;
            setMateriaSlots(8);
            setMagic(16);
        } else if (wpn.equals(allWeapons[0][11])){
            this.weapon = wpn;
            setMateriaSlots(6);
            setMagic(19);
        } else if (wpn.equals(allWeapons[0][12])){
            this.weapon = wpn;
            setMateriaSlots(3);
            setMagic(43);
            setSpirit(16);
        } else if (wpn.equals(allWeapons[0][13])){
            this.weapon = wpn;
            setMateriaSlots(6);
            setMagic(31);
        } else if (wpn.equals(allWeapons[0][14])){
            this.weapon = wpn;
            setMateriaSlots(6);
            setMagic(43);
            setSpirit(35);
        } else if (wpn.equals(allWeapons[0][15])){
            this.weapon = wpn;
            setMateriaSlots(8);
            setMagic(51);
            setSpirit(24);
        }
        // Barret Weapons
        else if (wpn.equals(allWeapons[1][0])){
            this.weapon = wpn;
            setMateriaSlots(1);
        } else if (wpn.equals(allWeapons[1][1])){
            this.weapon = wpn;
            setMateriaSlots(2);
            setMagic(1);
        } else if (wpn.equals(allWeapons[1][2])){
            this.weapon = wpn;
            setMateriaSlots(4);
            setMagic(4);
        } else if (wpn.equals(allWeapons[1][3])){
            this.weapon = wpn;
            setMateriaSlots(3);
            setMagic(2);
        } else if (wpn.equals(allWeapons[1][4])){
            this.weapon = wpn;
            setMateriaSlots(3);
            setMagic(3);
        } else if (wpn.equals(allWeapons[1][5])){
            this.weapon = wpn;
            setMateriaSlots(4);
            setMagic(8);
        } else if (wpn.equals(allWeapons[1][6])){
            this.weapon = wpn;
            setMateriaSlots(5);
            setMagic(7);
        } else if (wpn.equals(allWeapons[1][7])){
            this.weapon = wpn;
            setMateriaSlots(4);
        } else if (wpn.equals(allWeapons[1][8])){
            this.weapon = wpn;
            setMateriaSlots(5);
            setMagic(10);
        } else if (wpn.equals(allWeapons[1][9])){
            this.weapon = wpn;
        } else if (wpn.equals(allWeapons[1][10])){
            this.weapon = wpn;
            setMateriaSlots(6);
            setMagic(13);
        } else if (wpn.equals(allWeapons[1][11])){
            this.weapon = wpn;
            setMateriaSlots(8);
            setMagic(15);
        } else if (wpn.equals(allWeapons[1][12])){
            this.weapon = wpn;
            setMateriaSlots(6);
            setMagic(16);
        } else if (wpn.equals(allWeapons[1][13])){
            this.weapon = wpn;
            setMateriaSlots(6);
            setMagic(30);
        } else if (wpn.equals(allWeapons[1][14])){
            this.weapon = wpn;
            setMateriaSlots(6);
        } else if (wpn.equals(allWeapons[1][15])){
            this.weapon = wpn;
            setMateriaSlots(8);
            setMagic(49);
        }
        // Tifa Weapons
        else if (wpn.equals(allWeapons[2][0])){
            this.weapon = wpn;
            setMateriaSlots(1);
        } else if (wpn.equals(allWeapons[2][1])){
            this.weapon = wpn;
            setMateriaSlots(2);
            setMagic(1);
        } else if (wpn.equals(allWeapons[2][2])){
            this.weapon = wpn;
            setMateriaSlots(3);
            setMagic(3);
        } else if (wpn.equals(allWeapons[2][3])){
            this.weapon = wpn;
            setMateriaSlots(3);
            setMagic(6);
        } else if (wpn.equals(allWeapons[2][4])){
            this.weapon = wpn;
            setMateriaSlots(4);
            setMagic(6);
        } else if (wpn.equals(allWeapons[2][5])){
            this.weapon = wpn;
            setMateriaSlots(4);
            setMagic(8);
        } else if (wpn.equals(allWeapons[2][6])){
            this.weapon = wpn;
            setMateriaSlots(4);
            setMagic(7);
        } else if (wpn.equals(allWeapons[2][7])){
            this.weapon = wpn;
            setMateriaSlots(4);
            setMagic(7);
        } else if (wpn.equals(allWeapons[2][8])){
            this.weapon = wpn;
            setMateriaSlots(5);
            setMagic(10);
        } else if (wpn.equals(allWeapons[2][9])){
            this.weapon = wpn;
            setMagic(3);
        } else if (wpn.equals(allWeapons[2][10])){
            this.weapon = wpn;
            setMateriaSlots(6);
            setMagic(13);
        } else if (wpn.equals(allWeapons[2][11])){
            this.weapon = wpn;
            setMateriaSlots(8);
            setMagic(13);
        } else if (wpn.equals(allWeapons[2][12])){
            this.weapon = wpn;
            setMateriaSlots(6);
            setMagic(16);
        } else if (wpn.equals(allWeapons[2][13])){
            this.weapon = wpn;
            setMateriaSlots(4);
            setMagic(34);
        } else if (wpn.equals(allWeapons[2][14])){
            this.weapon = wpn;
            setMateriaSlots(6);
        } else if (wpn.equals(allWeapons[2][15])){
            this.weapon = wpn;
            setMateriaSlots(8);
            setMagic(32);
        }
        // Aeris Weapons
        else if (wpn.equals(allWeapons[3][0])){
            this.weapon = wpn;
            setMateriaSlots(1);
            setMagic(2);
            setSpirit(4);
        } else if (wpn.equals(allWeapons[3][1])){
            this.weapon = wpn;
            setMateriaSlots(2);
            setMagic(3);
        } else if (wpn.equals(allWeapons[3][2])){
            this.weapon = wpn;
            setMateriaSlots(4);
            setMagic(7);
        } else if (wpn.equals(allWeapons[3][3])){
            this.weapon = wpn;
            setMateriaSlots(3);
            setMagic(4);
        } else if (wpn.equals(allWeapons[3][4])){
            this.weapon = wpn;
            setMateriaSlots(3);
            setMagic(6);
        } else if (wpn.equals(allWeapons[3][5])){
            this.weapon = wpn;
            setVitality(20);
            setMagic(10);
        } else if (wpn.equals(allWeapons[3][6])){
            this.weapon = wpn;
            setMateriaSlots(7);
            setMagic(8);
        } else if (wpn.equals(allWeapons[3][7])){
            this.weapon = wpn;
            setMateriaSlots(4);
            setMagic(10);
        } else if (wpn.equals(allWeapons[3][8])){
            this.weapon = wpn;
            setMateriaSlots(4);
            setMagic(7);
        } else if (wpn.equals(allWeapons[3][9])){
            this.weapon = wpn;
            setMateriaSlots(5);
            setMagic(14);
        } else if (wpn.equals(allWeapons[3][10])){
            this.weapon = wpn;
            setMateriaSlots(7);
            setVitality(12);
            setMagic(22);
            setSpirit(20);
        }
        // Red XIII Weapons
        else if (wpn.equals(allWeapons[4][0])){
            this.weapon = wpn;
            setMateriaSlots(3);
            setMagic(6);
        } else if (wpn.equals(allWeapons[4][1])){
            this.weapon = wpn;
            setMateriaSlots(3);
            setMagic(4);
        } else if (wpn.equals(allWeapons[4][2])){
            this.weapon = wpn;
            setMateriaSlots(4);
            setMagic(8);
        } else if (wpn.equals(allWeapons[4][3])){
            this.weapon = wpn;
            setMateriaSlots(4);
            setMagic(10);
        } else if (wpn.equals(allWeapons[4][4])){
            this.weapon = wpn;
            setMateriaSlots(4);
            setMagic(14);
        } else if (wpn.equals(allWeapons[4][5])){
            this.weapon = wpn;
            setMateriaSlots(4);
            setMagic(12);
        } else if (wpn.equals(allWeapons[4][6])){
            this.weapon = wpn;
            setMateriaSlots(5);
            setMagic(13);
        } else if (wpn.equals(allWeapons[4][7])){
            this.weapon = wpn;
            setMagic(15);
        } else if (wpn.equals(allWeapons[4][8])){
            this.weapon = wpn;
            setMateriaSlots(6);
            setMagic(15);
        } else if (wpn.equals(allWeapons[4][9])){
            this.weapon = wpn;
            setMateriaSlots(8);
            setMagic(22);
        } else if (wpn.equals(allWeapons[4][10])){
            this.weapon = wpn;
            setMateriaSlots(6);
            setMagic(20);
        } else if (wpn.equals(allWeapons[4][11])){
            this.weapon = wpn;
            setMateriaSlots(6);
            setMagic(55);
        } else if (wpn.equals(allWeapons[4][12])){
            this.weapon = wpn;
            setMateriaSlots(6);
            setVitality(35);
            setMagic(26);
            setSpirit(18);
        } else if (wpn.equals(allWeapons[4][13])){
            this.weapon = wpn;
            setMateriaSlots(8);
            setMagic(31);
        }
        // Yuffie Weapons
        else if (wpn.equals(allWeapons[5][0])){
            this.weapon = wpn;
            setMateriaSlots(3);
            setMagic(6);
        } else if (wpn.equals(allWeapons[5][1])){
            this.weapon = wpn;
            setMateriaSlots(4);
            setMagic(7);
        } else if (wpn.equals(allWeapons[5][2])){
            this.weapon = wpn;
            setMateriaSlots(3);
            setMagic(7);
        } else if (wpn.equals(allWeapons[5][3])){
            this.weapon = wpn;
            setMateriaSlots(4);
            setMagic(9);
        } else if (wpn.equals(allWeapons[5][4])){
            this.weapon = wpn;
            setMateriaSlots(4);
            setMagic(8);
        } else if (wpn.equals(allWeapons[5][5])){
            this.weapon = wpn;
            setMateriaSlots(5);
            setMagic(12);
        } else if (wpn.equals(allWeapons[5][6])){
            this.weapon = wpn;
            setMateriaSlots(3);
            setDexterity(10);
        } else if (wpn.equals(allWeapons[5][7])){
            this.weapon = wpn;
            setMateriaSlots(6);
            setMagic(14);
        } else if (wpn.equals(allWeapons[5][8])){
            this.weapon = wpn;
            setMagic(10);
        } else if (wpn.equals(allWeapons[5][9])){
            this.weapon = wpn;
            setMateriaSlots(8);
            setMagic(18);
        } else if (wpn.equals(allWeapons[5][10])){
            this.weapon = wpn;
            setMateriaSlots(6);
            setMagic(18);
        } else if (wpn.equals(allWeapons[5][11])){
            this.weapon = wpn;
            setMateriaSlots(4);
            setMagic(16);
        } else if (wpn.equals(allWeapons[5][12])){
            this.weapon = wpn;
            setMateriaSlots(8);
            setMagic(38);
        } else if (wpn.equals(allWeapons[5][13])) {
            this.weapon = wpn;
            setMateriaSlots(8);
            setMagic(42);
        }
        // Cait Sith Weapons
        else if (wpn.equals(allWeapons[6][0])){
            this.weapon = wpn;
            setMateriaSlots(4);
            setMagic(8);
        } else if (wpn.equals(allWeapons[6][1])){
            this.weapon = wpn;
            setMateriaSlots(3);
            setMagic(8);
        } else if (wpn.equals(allWeapons[6][2])){
            this.weapon = wpn;
            setMateriaSlots(4);
            setMagic(9);
        } else if (wpn.equals(allWeapons[6][3])){
            this.weapon = wpn;
            setMateriaSlots(4);
            setMagic(10);
        } else if (wpn.equals(allWeapons[6][4])){
            this.weapon = wpn;
            setMateriaSlots(8);
            setMagic(14);
        } else if (wpn.equals(allWeapons[6][5])){
            this.weapon = wpn;
            setMateriaSlots(5);
            setMagic(10);
        } else if (wpn.equals(allWeapons[6][6])){
            this.weapon = wpn;
            setMagic(2);
        } else if (wpn.equals(allWeapons[6][7])){
            this.weapon = wpn;
            setMateriaSlots(6);
            setMagic(15);
        } else if (wpn.equals(allWeapons[6][8])){
            this.weapon = wpn;
            setMateriaSlots(8);
            setMagic(28);
        } else if (wpn.equals(allWeapons[6][9])){
            this.weapon = wpn;
            setMateriaSlots(6);
            setMagic(20);
        } else if (wpn.equals(allWeapons[6][10])){
            this.weapon = wpn;
            setMateriaSlots(6);
        } else if (wpn.equals(allWeapons[6][11])){
            this.weapon = wpn;
            setMateriaSlots(8);
            setVitality(30);
            setMagic(31);
        } else if (wpn.equals(allWeapons[6][12])){
            this.weapon = wpn;
            setMateriaSlots(8);
            setMagic(44);
        }
        // Vincent Weapons
        else if (wpn.equals(allWeapons[7][0])){
            this.weapon = wpn;
            setMateriaSlots(4);
            setMagic(10);
        } else if (wpn.equals(allWeapons[7][1])){
            this.weapon = wpn;
            setMateriaSlots(3);
            setMagic(8);
        } else if (wpn.equals(allWeapons[7][2])){
            this.weapon = wpn;
            setMateriaSlots(4);
            setMagic(7);
        } else if (wpn.equals(allWeapons[7][3])){
            this.weapon = wpn;
            setMateriaSlots(4);
            setMagic(12);
        } else if (wpn.equals(allWeapons[7][4])){
            this.weapon = wpn;
            setMateriaSlots(5);
            setMagic(14);
        } else if (wpn.equals(allWeapons[7][5])){
            this.weapon = wpn;
            setMagic(8);
        } else if (wpn.equals(allWeapons[7][6])){
            this.weapon = wpn;
            setMateriaSlots(4);
            setMagic(18);
        } else if (wpn.equals(allWeapons[7][7])){
            this.weapon = wpn;
            setMateriaSlots(6);
            setMagic(16);
        } else if (wpn.equals(allWeapons[7][8])){
            this.weapon = wpn;
            setMateriaSlots(8);
            setMagic(14);
        } else if (wpn.equals(allWeapons[7][9])){
            this.weapon = wpn;
            setMateriaSlots(6);
            setMagic(18);
        } else if (wpn.equals(allWeapons[7][10])){
            this.weapon = wpn;
            setMateriaSlots(6);
            setMagic(52);
        } else if (wpn.equals(allWeapons[7][11])){
            this.weapon = wpn;
            setMateriaSlots(8);
            setMagic(48);
        } else if (wpn.equals(allWeapons[7][12])){
            this.weapon = wpn;
            setMateriaSlots(8);
            setMagic(34);
        }
        // Cid Weapons
        else if (wpn.equals(allWeapons[8][0])){
            this.weapon = wpn;
            setMateriaSlots(4);
            setMagic(8);
        } else if (wpn.equals(allWeapons[8][1])){
            this.weapon = wpn;
            setMateriaSlots(5);
            setMagic(10);
        } else if (wpn.equals(allWeapons[8][2])){
            this.weapon = wpn;
            setMateriaSlots(8);
            setMagic(7);
        } else if (wpn.equals(allWeapons[8][3])){
            this.weapon = wpn;
            setMateriaSlots(6);
            setMagic(12);
        } else if (wpn.equals(allWeapons[8][4])){
            this.weapon = wpn;
            setMateriaSlots(0);
            setMagic(3);
        } else if (wpn.equals(allWeapons[8][5])){
            this.weapon = wpn;
            setMateriaSlots(4);
            setMagic(13);
        } else if (wpn.equals(allWeapons[8][6])){
            this.weapon = wpn;
            setMateriaSlots(6);
            setMagic(15);
        } else if (wpn.equals(allWeapons[8][7])){
            this.weapon = wpn;
            setMateriaSlots(5);
            setMagic(12);
        } else if (wpn.equals(allWeapons[8][8])){
            this.weapon = wpn;
            setMateriaSlots(6);
            setMagic(17);
        } else if (wpn.equals(allWeapons[8][9])){
            this.weapon = wpn;
            setMateriaSlots(2);
            setMagic(20);
        } else if (wpn.equals(allWeapons[8][10])){
            this.weapon = wpn;
            setMateriaSlots(6);
            setMagic(20);
        } else if (wpn.equals(allWeapons[8][11])){
            this.weapon = wpn;
            setMateriaSlots(4);
            setMagic(43);
            setSpirit(20);
        } else if (wpn.equals(allWeapons[8][12])){
            this.weapon = wpn;
            setMateriaSlots(6);
            setMagic(31);
        } else if (wpn.equals(allWeapons[8][13])){
            this.weapon = wpn;
            setMateriaSlots(8);
            setMagic(42);
        }
    }

    public String getWeapon(){
        return this.weapon;
    }

}
/**
 * Created by James Page on 4/15/2017.
 */
package Main;

public class Armors extends Stats {
    private String armor;
    private int materiaSlots;

    private final String[] armorsList = {
            "Bronze Bangle", "Iron Bangle", "Titan Bangle", "Mythril Armlet",
            "Four Slot", "Shinra Beta", "Carbon Bangle", "Platinum Bangle",
            "Silver Armlet", "Gold Armlet", "Edincoat", "Adaman Bangle",
            "Dragon Armlet", "Gigas Armlet", "Diamond Bangle", "Rune Armor",
            "Aurora Armlet", "Wizard Bracelet", "Fire Armlet", "Bolt Armlet",
            "Crystal Bangle", "Force Bracelet", "Imperial Guard", "Warrior Bangle",
            "Shinra Alpha", "Ziedrick", "Precious Watch", "Chocobracelet",
            "Minerva Band", "Escort Guard", "Aegis Armlet", "Mystile"
    };

    //**************************************************************************************

    Armors(){}

    //**************************************************************************************

    private void setMateriaSlots(int mSlots){
        this.materiaSlots = mSlots;
    }

    public int getMateriaSlots(){
        return this.materiaSlots;
    }

    //**************************************************************************************

    protected String[] getArmorsList(){
        return this.armorsList;
    }

    //**************************************************************************************

    protected void setArmor(String arm){
        // Reset armor stats to 0 first
        this.armor = null;
        setMateriaSlots(0);
        resetStats();

        if (arm.equals(armorsList[0])){
            this.armor = arm;
        } else if (arm.equals(armorsList[1])){
            this.armor = arm;
            setMateriaSlots(1);
        } else if (arm.equals(armorsList[2])){
            this.armor = arm;
            setMateriaSlots(2);
        } else if (arm.equals(armorsList[3])){
            this.armor = arm;
            setMateriaSlots(2);
        } else if (arm.equals(armorsList[4])){
            this.armor = arm;
            setMateriaSlots(4);
        } else if (arm.equals(armorsList[5])){
            this.armor = arm;
            setMateriaSlots(4);
        } else if (arm.equals(armorsList[6])){
            this.armor = arm;
            setMateriaSlots(3);
        } else if (arm.equals(armorsList[7])){
            this.armor = arm;
            setMateriaSlots(2);
        } else if (arm.equals(armorsList[8])){
            this.armor = arm;
            setMateriaSlots(4);
        } else if (arm.equals(armorsList[9])){
            this.armor = arm;
            setMateriaSlots(4);
        } else if (arm.equals(armorsList[10])){
            this.armor = arm;
            setMateriaSlots(7);
            setMagic(5);
        } else if (arm.equals(armorsList[11])){
            this.armor = arm;
            setMateriaSlots(2);
        } else if (arm.equals(armorsList[12])){
            this.armor = arm;
            setMateriaSlots(6);
        } else if (arm.equals(armorsList[13])){
            this.armor = arm;
            setMateriaSlots(5);
            setStrength(30);
        } else if (arm.equals(armorsList[14])){
            this.armor = arm;
            setMateriaSlots(5);
        } else if (arm.equals(armorsList[15])){
            this.armor = arm;
            setMateriaSlots(4);
        } else if (arm.equals(armorsList[16])){
            this.armor = arm;
            setMateriaSlots(4);
        } else if (arm.equals(armorsList[17])){
            this.armor = arm;
            setMateriaSlots(8);
            setMagic(20);
        } else if (arm.equals(armorsList[18])){
            this.armor = arm;
            setMateriaSlots(4);
        } else if (arm.equals(armorsList[19])){
            this.armor = arm;
            setMateriaSlots(4);
        } else if (arm.equals(armorsList[20])){
            this.armor = arm;
            setMateriaSlots(6);
        } else if (arm.equals(armorsList[21])){
            this.armor = arm;
            setMateriaSlots(5);
            setMagic(20);
        } else if (arm.equals(armorsList[22])){
            this.armor = arm;
            setMateriaSlots(6);
        } else if (arm.equals(armorsList[23])){
            this.armor = arm;
            setMateriaSlots(4);
            setStrength(20);
        } else if (arm.equals(armorsList[24])){
            this.armor = arm;
            setMateriaSlots(6);
        } else if (arm.equals(armorsList[25])){
            this.armor = arm;
            setStrength(20);
            setMagic(20);
        } else if (arm.equals(armorsList[26])){
            this.armor = arm;
            setMateriaSlots(8);
        } else if (arm.equals(armorsList[27])){
            this.armor = arm;
            setMateriaSlots(4);
            setDexterity(30);
            setLuck(20);
        } else if (arm.equals(armorsList[28])){
            this.armor = arm;
            setMateriaSlots(6);
        } else if (arm.equals(armorsList[29])){
            this.armor = arm;
            setMateriaSlots(6);
        } else if (arm.equals(armorsList[30])){
            this.armor = arm;
            setMateriaSlots(4);
        } else if (arm.equals(armorsList[31])){
            this.armor = arm;
            setMateriaSlots(6);
        }
    }

    protected String getArmor(){
        return this.armor;
    }

    //**************************************************************************************
}

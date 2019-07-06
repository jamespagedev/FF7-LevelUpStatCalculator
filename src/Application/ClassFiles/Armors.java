/**
 * Created by James Page on 4/15/2017.
 */
package ClassFiles;

public class Armors extends Stats {
    public String armorName;
    public int numOfMateriaSlots;

    public final String[] armorsList = {
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

    public Armors(){}

    //**************************************************************************************

    public void setNumOfMateriaSlots(int mSlots){
        this.numOfMateriaSlots = mSlots;
    }

    public int getNumOfMateriaSlots(){
        return this.numOfMateriaSlots;
    }

    //**************************************************************************************

    public String[] getArmorsList(){
        return this.armorsList;
    }

    //**************************************************************************************

    public void setArmorName(String armName){
        // Reset armor stats to 0 first
        this.armorName = null;
        setNumOfMateriaSlots(0);
        resetStats();

        if (armName.equals(armorsList[0])){
            this.armorName = armName;
        } else if (armName.equals(armorsList[1])){
            this.armorName = armName;
            setNumOfMateriaSlots(1);
        } else if (armName.equals(armorsList[2])){
            this.armorName = armName;
            setNumOfMateriaSlots(2);
        } else if (armName.equals(armorsList[3])){
            this.armorName = armName;
            setNumOfMateriaSlots(2);
        } else if (armName.equals(armorsList[4])){
            this.armorName = armName;
            setNumOfMateriaSlots(4);
        } else if (armName.equals(armorsList[5])){
            this.armorName = armName;
            setNumOfMateriaSlots(4);
        } else if (armName.equals(armorsList[6])){
            this.armorName = armName;
            setNumOfMateriaSlots(3);
        } else if (armName.equals(armorsList[7])){
            this.armorName = armName;
            setNumOfMateriaSlots(2);
        } else if (armName.equals(armorsList[8])){
            this.armorName = armName;
            setNumOfMateriaSlots(4);
        } else if (armName.equals(armorsList[9])){
            this.armorName = armName;
            setNumOfMateriaSlots(4);
        } else if (armName.equals(armorsList[10])){
            this.armorName = armName;
            setNumOfMateriaSlots(7);
            setMagic(5);
        } else if (armName.equals(armorsList[11])){
            this.armorName = armName;
            setNumOfMateriaSlots(2);
        } else if (armName.equals(armorsList[12])){
            this.armorName = armName;
            setNumOfMateriaSlots(6);
        } else if (armName.equals(armorsList[13])){
            this.armorName = armName;
            setNumOfMateriaSlots(5);
            setStrength(30);
        } else if (armName.equals(armorsList[14])){
            this.armorName = armName;
            setNumOfMateriaSlots(5);
        } else if (armName.equals(armorsList[15])){
            this.armorName = armName;
            setNumOfMateriaSlots(4);
        } else if (armName.equals(armorsList[16])){
            this.armorName = armName;
            setNumOfMateriaSlots(4);
        } else if (armName.equals(armorsList[17])){
            this.armorName = armName;
            setNumOfMateriaSlots(8);
            setMagic(20);
        } else if (armName.equals(armorsList[18])){
            this.armorName = armName;
            setNumOfMateriaSlots(4);
        } else if (armName.equals(armorsList[19])){
            this.armorName = armName;
            setNumOfMateriaSlots(4);
        } else if (armName.equals(armorsList[20])){
            this.armorName = armName;
            setNumOfMateriaSlots(6);
        } else if (armName.equals(armorsList[21])){
            this.armorName = armName;
            setNumOfMateriaSlots(5);
            setMagic(20);
        } else if (armName.equals(armorsList[22])){
            this.armorName = armName;
            setNumOfMateriaSlots(6);
        } else if (armName.equals(armorsList[23])){
            this.armorName = armName;
            setNumOfMateriaSlots(4);
            setStrength(20);
        } else if (armName.equals(armorsList[24])){
            this.armorName = armName;
            setNumOfMateriaSlots(6);
        } else if (armName.equals(armorsList[25])){
            this.armorName = armName;
            setStrength(20);
            setMagic(20);
        } else if (armName.equals(armorsList[26])){
            this.armorName = armName;
            setNumOfMateriaSlots(8);
        } else if (armName.equals(armorsList[27])){
            this.armorName = armName;
            setNumOfMateriaSlots(4);
            setDexterity(30);
            setLuck(20);
        } else if (armName.equals(armorsList[28])){
            this.armorName = armName;
            setNumOfMateriaSlots(6);
        } else if (armName.equals(armorsList[29])){
            this.armorName = armName;
            setNumOfMateriaSlots(6);
        } else if (armName.equals(armorsList[30])){
            this.armorName = armName;
            setNumOfMateriaSlots(4);
        } else if (armName.equals(armorsList[31])){
            this.armorName = armName;
            setNumOfMateriaSlots(6);
        }
    }

    public String getArmorName(){
        return this.armorName;
    }

    //**************************************************************************************
}

/**
 * Created by James Page on 4/15/2017.
 */
package ClassFiles;

public class Accessories extends Stats {
    public String accessoryName;
    public final String[] accessoriesList = {
            "None",
            "Star Pendant", "Talisman", "Protect Vest", "Power Wrist",
            "Silver Glasses", "Headband", "White Cape", "Fire Ring",
            "Fury Ring", "Bolt Ring", "Fairy Ring", "Jewel Ring",
            "Earring", "Choco Feather", "Peace Ring", "Ice Ring",
            "Ribbon", "Water Ring", "Hypno Crown", "Circlet",
            "Safety Bit", "Protect Ring", "Poison Ring", "Reflect Ring",
            "Tetra Elemental", "Amulet", "Cursed Ring", "Champion Belt",
            "Sprint Shoes", "Cat's Bell", "Toughness Ring", "Sneak Glove"
    };

    //**************************************************************************************

    public Accessories(){}

    //**************************************************************************************

    public String[] getAccessoriesList(){
        return this.accessoriesList;
    }

    //**************************************************************************************

    public void setAccessoryName(String accName) {
        // Reset accessory stats to 0 first
        this.accessoryName = null;
        resetStats();
        if (accName.equals(accessoriesList[0])){
            this.accessoryName = accName;
        } else if (accName.equals(accessoriesList[1])){
            this.accessoryName = accName;
        } else if (accName.equals(accessoriesList[2])){
            this.accessoryName = accName;
            setSpirit(10);
        } else if (accName.equals(accessoriesList[3])){
            this.accessoryName = accName;
            setVitality(10);
        } else if (accName.equals(accessoriesList[4])){
            this.accessoryName = accName;
            setStrength(10);
        } else if (accName.equals(accessoriesList[5])){
            this.accessoryName = accName;
        } else if (accName.equals(accessoriesList[6])){
            this.accessoryName = accName;
        } else if (accName.equals(accessoriesList[7])){
            this.accessoryName = accName;
        } else if (accName.equals(accessoriesList[8])){
            this.accessoryName = accName;
        } else if (accName.equals(accessoriesList[9])){
            this.accessoryName = accName;
        } else if (accName.equals(accessoriesList[10])){
            this.accessoryName = accName;
        } else if (accName.equals(accessoriesList[11])){
            this.accessoryName = accName;
        } else if (accName.equals(accessoriesList[12])){
            this.accessoryName = accName;
        } else if (accName.equals(accessoriesList[13])){
            this.accessoryName = accName;
            setMagic(10);
        } else if (accName.equals(accessoriesList[14])){
            this.accessoryName = accName;
            setDexterity(10);
        } else if (accName.equals(accessoriesList[15])){
            this.accessoryName = accName;
        } else if (accName.equals(accessoriesList[16])){
            this.accessoryName = accName;
        } else if (accName.equals(accessoriesList[17])){
            this.accessoryName = accName;
        } else if (accName.equals(accessoriesList[18])){
            this.accessoryName = accName;
        } else if (accName.equals(accessoriesList[19])){
            this.accessoryName = accName;
        } else if (accName.equals(accessoriesList[20])){
            this.accessoryName = accName;
            setMagic(30);
            setSpirit(30);
        } else if (accName.equals(accessoriesList[21])){
            this.accessoryName = accName;
        } else if (accName.equals(accessoriesList[22])){
            this.accessoryName = accName;
        } else if (accName.equals(accessoriesList[23])){
            this.accessoryName = accName;
        } else if (accName.equals(accessoriesList[24])){
            this.accessoryName = accName;
        } else if (accName.equals(accessoriesList[25])){
            this.accessoryName = accName;
        } else if (accName.equals(accessoriesList[26])){
            this.accessoryName = accName;
            setLuck(10);
        } else if (accName.equals(accessoriesList[27])){
            this.accessoryName = accName;
            setStrength(35);
            setDexterity(15);
            setVitality(15);
            setMagic(35);
            setSpirit(15);
            setLuck(10);
        } else if (accName.equals(accessoriesList[28])){
            this.accessoryName = accName;
            setStrength(30);
            setVitality(30);
        } else if (accName.equals(accessoriesList[29])){
            this.accessoryName = accName;
        } else if (accName.equals(accessoriesList[30])){
            this.accessoryName = accName;
        } else if (accName.equals(accessoriesList[31])){
            this.accessoryName = accName;
            setVitality(50);
            setSpirit(50);
        } else if (accName.equals(accessoriesList[32])){
            this.accessoryName = accName;
        }
    }

    public String getAccessoryName(){
        return this.accessoryName;
    }
}

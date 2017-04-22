/**
 * Created by James Page on 4/15/2017.
 */
package Main;

public class Accessories extends Stats {
    private String accessory;
    private final String[] accessoriesList = {
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

    Accessories(){}

    //**************************************************************************************

    protected String[] getAccessoriesList(){
        return this.accessoriesList;
    }

    //**************************************************************************************

    protected void setAccessory(String acc){
        // Reset accessory stats to 0 first
        this.accessory = null;
        resetStats();

        if (acc.equals(accessoriesList[0])){
            this.accessory = acc;
        } else if (acc.equals(accessoriesList[1])){
            this.accessory = acc;
            setSpirit(10);
        } else if (acc.equals(accessoriesList[2])){
            this.accessory = acc;
            setVitality(10);
        } else if (acc.equals(accessoriesList[3])){
            this.accessory = acc;
            setStrength(10);
        } else if (acc.equals(accessoriesList[4])){
            this.accessory = acc;
        } else if (acc.equals(accessoriesList[5])){
            this.accessory = acc;
        } else if (acc.equals(accessoriesList[6])){
            this.accessory = acc;
        } else if (acc.equals(accessoriesList[7])){
            this.accessory = acc;
        } else if (acc.equals(accessoriesList[8])){
            this.accessory = acc;
        } else if (acc.equals(accessoriesList[9])){
            this.accessory = acc;
        } else if (acc.equals(accessoriesList[10])){
            this.accessory = acc;
        } else if (acc.equals(accessoriesList[11])){
            this.accessory = acc;
        } else if (acc.equals(accessoriesList[12])){
            this.accessory = acc;
            setMagic(10);
        } else if (acc.equals(accessoriesList[13])){
            this.accessory = acc;
            setDexterity(10);
        } else if (acc.equals(accessoriesList[14])){
            this.accessory = acc;
        } else if (acc.equals(accessoriesList[15])){
            this.accessory = acc;
        } else if (acc.equals(accessoriesList[16])){
            this.accessory = acc;
        } else if (acc.equals(accessoriesList[17])){
            this.accessory = acc;
        } else if (acc.equals(accessoriesList[18])){
            this.accessory = acc;
        } else if (acc.equals(accessoriesList[19])){
            this.accessory = acc;
            setMagic(30);
            setSpirit(30);
        } else if (acc.equals(accessoriesList[20])){
            this.accessory = acc;
        } else if (acc.equals(accessoriesList[21])){
            this.accessory = acc;
        } else if (acc.equals(accessoriesList[22])){
            this.accessory = acc;
        } else if (acc.equals(accessoriesList[23])){
            this.accessory = acc;
        } else if (acc.equals(accessoriesList[24])){
            this.accessory = acc;
        } else if (acc.equals(accessoriesList[25])){
            this.accessory = acc;
            setLuck(10);
        } else if (acc.equals(accessoriesList[26])){
            this.accessory = acc;
            setStrength(35);
            setDexterity(15);
            setVitality(15);
            setMagic(35);
            setSpirit(15);
            setLuck(10);
        } else if (acc.equals(accessoriesList[27])){
            this.accessory = acc;
            setStrength(30);
            setVitality(30);
        } else if (acc.equals(accessoriesList[28])){
            this.accessory = acc;
        } else if (acc.equals(accessoriesList[29])){
            this.accessory = acc;
        } else if (acc.equals(accessoriesList[30])){
            this.accessory = acc;
            setVitality(50);
            setSpirit(50);
        } else if (acc.equals(accessoriesList[31])){
            this.accessory = acc;
        }
    }

    protected String getAccessory(){
        return this.accessory;
    }
}

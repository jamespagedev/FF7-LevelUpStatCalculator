/**
 * Created by James Page on 4/18/2017.
 */
package ClassFiles;

import java.util.Arrays;

public class Materia extends Stats {
    public String materiaName;
    public boolean switchHpMpEnabled = false;
    public double hpIncDecPerc;
    public double mpIncDecPerc;
    public double dexIncDecPerc;
    public double magicIncDecPerc;
    public double luckIncDecPerc;
    public static final String[][] materiaLists = {
            {"Empty"},
            {       // Green Materia (Magic)
                    "Fire", "Ice", "Earth", "Lightning",
                    "Restore", "Heal", "Revive", "Seal",
                    "Mystify", "Transform", "Exit", "Poison",
                    "Gravity", "Barrier", "Comet", "Time",
                    "Destruct", "Contain", "Full Cure", "Shield",
                    "Ultima", "Master Magic"
            },
            {       // Yellow Materia (Command)
                    "Slash-All", "Double Cut", "W-Magic", "W-Summon",
                    "W-Item", "Steal", "Sense", "Throw",
                    "Morph", "Deathblow", "Manipulate", "Mime",
                    "Enemy Skill", "MasterCommand"
            },
            {       // Red Materia (Summon)
                    "Choco/Mog", "Shiva", "Ifrit", "Ramuh",
                    "Titan", "Odin", "Leviathan", "Bahamut",
                    "Kujata", "Alexander", "Pheonix", "Neo Bahamut",
                    "Hades", "Typhon", "Bahamut ZERO", "Knights of Round",
                    "Master Summon"
            },
            {
                    // Blue Materia (Support)
                    "All", "Counter", "Magic Counter", "MP Turbo",
                    "MP Absorb", "HP Absorb", "Elemental", "Added Effect",
                    "Sneak Attack", "Final Attack", "Added Cut", "Steal as well",
                    "Quadra Magic"
            },
            {
                    // Purple Materia (Independent)
                    "MP Plus", "HP Plus", "Speed Plus", "Magic Plus",
                    "Luck Plus", "Exp Plus", "Gil Plus", "Enemy Away",
                    "Enemy Lure", "Chocobo Lure", "Pre-Emptive", "Long Range",
                    "Mega All", "Counter Attack", "Cover", "Underwater",
                    "HP<->MP"
            }
    };

    //**************************************************************************************
    public Materia(){setMateriaName("");}
    public Materia(String mName){setMateriaName(mName);}

    //**************************************************************************************
    public void setHpIncDecPerc(double hpIncDec){
        this.hpIncDecPerc = hpIncDec;
    }

    public double getHpIncDecPerc(){
        return this.hpIncDecPerc;
    }

    //**************************************************************************************
    public void setSwitchHpMpEnabled(boolean swtchHpMp){
        this.switchHpMpEnabled = swtchHpMp;
    }

    public boolean isSitchHpMpEnabled(){
        return this.switchHpMpEnabled;
    }

    //**************************************************************************************
    public void setMpIncDecPerc(double mpIncDec){
        this.mpIncDecPerc = mpIncDec;
    }

    public double getMpIncDecPerc(){
        return this.mpIncDecPerc;
    }

    //**************************************************************************************
    public void setDexIncDecPerc(double dexIncDec){
        this.dexIncDecPerc = dexIncDec;
    }

    public double getDexIncDecPerc(){
        return this.dexIncDecPerc;
    }

    //**************************************************************************************
    public void setMagicIncDecPerc(double magIncDec){
        this.magicIncDecPerc = magIncDec;
    }

    public double getMagicIncDecPerc(){
        return this.magicIncDecPerc;
    }

    //**************************************************************************************
    public void setLuckIncDecPerc(double luckIncDec){
        this.luckIncDecPerc = luckIncDec;
    }

    public double getLuckIncDecPerc(){
        return this.luckIncDecPerc;
    }

    //**************************************************************************************

    public String[] getEmptyMateriaList(){
        return getMateriaLists()[0];
    }

    //**************************************************************************************

    public String[] getGreenMateriaList(){
        return getMateriaLists()[1];
    }

    //**************************************************************************************

    public String[] getYellowMateriaList(){
        return getMateriaLists()[2];
    }

    //**************************************************************************************

    public String[] getRedMateriaList(){
        return getMateriaLists()[3];
    }

    //**************************************************************************************

    public String[] getBlueMateriaList(){
        return getMateriaLists()[4];
    }

    //**************************************************************************************

    public String[] getPurpleMateriaList(){
        return getMateriaLists()[5];
    }

    //**************************************************************************************

    public String[][] getMateriaLists(){
        return materiaLists;
    }

    //**************************************************************************************

    public String getMateriaName(){
        return this.materiaName;
    }

    public void setMateriaName(String mName){
        // Reset materia stats before equipping new materia
        this.switchHpMpEnabled = false;
        setHpIncDecPerc(0.00);
        setMpIncDecPerc(0.00);
        setDexIncDecPerc(0.00);
        setMagicIncDecPerc(0.00);
        setLuckIncDecPerc(0.00);
        resetStats();

        // Materia is empty
        if (mName.equals(getEmptyMateriaList()[0])){
            this.materiaName = mName;
        }
        // Green Materia
        else if (mName.equals(getGreenMateriaList()[0])){
            this.materiaName = mName;
            setHpIncDecPerc(-0.02);
            setMpIncDecPerc(0.02);
            setStrength(-1);
            setMagic(1);
        } else if (mName.equals(getGreenMateriaList()[1])){
            this.materiaName = mName;
            setHpIncDecPerc(-0.02);
            setMpIncDecPerc(0.02);
            setStrength(-1);
            setMagic(1);
        } else if (mName.equals(getGreenMateriaList()[2])){
            this.materiaName = mName;
            setHpIncDecPerc(-0.02);
            setMpIncDecPerc(0.02);
            setStrength(-1);
            setMagic(1);
        } else if (mName.equals(getGreenMateriaList()[3])){
            this.materiaName = mName;
            setHpIncDecPerc(-0.02);
            setMpIncDecPerc(0.02);
            setStrength(-1);
            setMagic(1);
        } else if (mName.equals(getGreenMateriaList()[4])){
            this.materiaName = mName;
            setHpIncDecPerc(-0.02);
            setMpIncDecPerc(0.02);
            setStrength(-1);
            setMagic(1);
        } else if (mName.equals(getGreenMateriaList()[5])){
            this.materiaName = mName;
            setHpIncDecPerc(-0.02);
            setMpIncDecPerc(0.02);
            setStrength(-1);
            setMagic(1);
        } else if (mName.equals(getGreenMateriaList()[6])){
            this.materiaName = mName;
            setHpIncDecPerc(-0.05);
            setMpIncDecPerc(0.05);
            setStrength(-2);
            setVitality(-1);
            setMagic(2);
            setSpirit(1);
        } else if (mName.equals(getGreenMateriaList()[7])){
            this.materiaName = mName;
            setHpIncDecPerc(-0.02);
            setMpIncDecPerc(0.02);
            setStrength(-1);
            setMagic(1);
        } else if (mName.equals(getGreenMateriaList()[8])){
            this.materiaName = mName;
            setHpIncDecPerc(-0.02);
            setMpIncDecPerc(0.02);
            setStrength(-1);
            setMagic(1);
        } else if (mName.equals(getGreenMateriaList()[9])){
            this.materiaName = mName;
            setHpIncDecPerc(-0.02);
            setMpIncDecPerc(0.02);
            setStrength(-1);
            setMagic(1);
        } else if (mName.equals(getGreenMateriaList()[10])){
            this.materiaName = mName;
            setHpIncDecPerc(-0.02);
            setMpIncDecPerc(0.02);
            setStrength(-1);
            setMagic(1);
        } else if (mName.equals(getGreenMateriaList()[11])){
            this.materiaName = mName;
            setHpIncDecPerc(-0.02);
            setMpIncDecPerc(0.02);
            setStrength(-1);
            setMagic(1);
        } else if (mName.equals(getGreenMateriaList()[12])){
            this.materiaName = mName;
            setHpIncDecPerc(-0.02);
            setMpIncDecPerc(0.02);
            setStrength(-1);
            setMagic(1);
        } else if (mName.equals(getGreenMateriaList()[13])){
            this.materiaName = mName;
            setHpIncDecPerc(-0.05);
            setMpIncDecPerc(0.05);
            setStrength(-2);
            setVitality(-1);
            setMagic(2);
            setSpirit(1);
        } else if (mName.equals(getGreenMateriaList()[14])){
            this.materiaName = mName;
            setHpIncDecPerc(-0.05);
            setMpIncDecPerc(0.05);
            setStrength(-2);
            setVitality(-1);
            setMagic(2);
            setSpirit(1);
        } else if (mName.equals(getGreenMateriaList()[15])){
            this.materiaName = mName;
            setHpIncDecPerc(-0.05);
            setMpIncDecPerc(0.05);
            setStrength(-2);
            setVitality(-1);
            setMagic(2);
            setSpirit(1);
        } else if (mName.equals(getGreenMateriaList()[16])){
            this.materiaName = mName;
            setHpIncDecPerc(-0.05);
            setMpIncDecPerc(0.05);
            setStrength(-2);
            setVitality(-1);
            setMagic(2);
            setSpirit(1);
        } else if (mName.equals(getGreenMateriaList()[17])){
            this.materiaName = mName;
            setHpIncDecPerc(-0.10);
            setMpIncDecPerc(0.10);
            setStrength(-4);
            setVitality(-2);
            setMagic(4);
            setSpirit(2);
        } else if (mName.equals(getGreenMateriaList()[18])){
            this.materiaName = mName;
            setHpIncDecPerc(-0.10);
            setMpIncDecPerc(0.10);
            setStrength(-4);
            setVitality(-2);
            setMagic(4);
            setSpirit(2);
        } else if (mName.equals(getGreenMateriaList()[19])){
            this.materiaName = mName;
            setHpIncDecPerc(-0.10);
            setMpIncDecPerc(0.10);
            setStrength(-4);
            setVitality(-2);
            setMagic(4);
            setSpirit(2);
        } else if (mName.equals(getGreenMateriaList()[20])){
            this.materiaName = mName;
            setHpIncDecPerc(-0.10);
            setMpIncDecPerc(0.10);
            setStrength(-4);
            setVitality(-2);
            setMagic(4);
            setSpirit(2);
        } else if (mName.equals(getGreenMateriaList()[21])){
            this.materiaName = mName;
        }
        // Yellow Materia
        else if (mName.equals(getYellowMateriaList()[0])){
            this.materiaName = mName;
        } else if (mName.equals(getYellowMateriaList()[1])){
            this.materiaName = mName;
            setDexterity(2);
        } else if (mName.equals(getYellowMateriaList()[2])){
            this.materiaName = mName;
        } else if (mName.equals(getYellowMateriaList()[3])){
            this.materiaName = mName;
        } else if (mName.equals(getYellowMateriaList()[4])){
            this.materiaName = mName;
        } else if (mName.equals(getYellowMateriaList()[5])){
            this.materiaName = mName;
            setDexterity(2);
        } else if (mName.equals(getYellowMateriaList()[6])){
            this.materiaName = mName;
        } else if (mName.equals(getYellowMateriaList()[7])){
            this.materiaName = mName;
            setVitality(1);
        } else if (mName.equals(getYellowMateriaList()[8])){
            this.materiaName = mName;
        } else if (mName.equals(getYellowMateriaList()[9])){
            this.materiaName = mName;
            setLuck(1);
        } else if (mName.equals(getYellowMateriaList()[10])){
            this.materiaName = mName;
        } else if (mName.equals(getYellowMateriaList()[11])){
            this.materiaName = mName;
        } else if (mName.equals(getYellowMateriaList()[12])){
            this.materiaName = mName;
        } else if (mName.equals(getYellowMateriaList()[13])){
            this.materiaName = mName;
        }
        // Red Materia
        else if (mName.equals(getRedMateriaList()[0])){
            this.materiaName = mName;
            setHpIncDecPerc(-0.02);
            setMpIncDecPerc(0.02);
            setMagic(1);
        } else if (mName.equals(getRedMateriaList()[1])){
            this.materiaName = mName;
            setHpIncDecPerc(-0.02);
            setMpIncDecPerc(0.02);
            setMagic(1);
        } else if (mName.equals(getRedMateriaList()[2])){
            this.materiaName = mName;
            setHpIncDecPerc(-0.02);
            setMpIncDecPerc(0.02);
            setMagic(1);
        } else if (mName.equals(getRedMateriaList()[3])){
            this.materiaName = mName;
            setHpIncDecPerc(-0.02);
            setMpIncDecPerc(0.02);
            setMagic(1);
        } else if (mName.equals(getRedMateriaList()[4])){
            this.materiaName = mName;
            setHpIncDecPerc(-0.02);
            setMpIncDecPerc(0.02);
            setMagic(1);
        } else if (mName.equals(getRedMateriaList()[5])){
            this.materiaName = mName;
            setHpIncDecPerc(-0.05);
            setMpIncDecPerc(0.05);
            setMagic(1);
            setSpirit(1);
        } else if (mName.equals(getRedMateriaList()[6])){
            this.materiaName = mName;
            setHpIncDecPerc(-0.05);
            setMpIncDecPerc(0.05);
            setMagic(1);
            setSpirit(1);
        } else if (mName.equals(getRedMateriaList()[7])){
            this.materiaName = mName;
            setHpIncDecPerc(-0.05);
            setMpIncDecPerc(0.05);
            setMagic(1);
            setSpirit(1);
        } else if (mName.equals(getRedMateriaList()[8])){
            this.materiaName = mName;
            setHpIncDecPerc(-0.05);
            setMpIncDecPerc(0.05);
            setMagic(1);
            setSpirit(1);
        } else if (mName.equals(getRedMateriaList()[9])){
            this.materiaName = mName;
            setHpIncDecPerc(-0.05);
            setMpIncDecPerc(0.05);
            setMagic(1);
            setSpirit(1);
        } else if (mName.equals(getRedMateriaList()[10])){
            this.materiaName = mName;
            setHpIncDecPerc(-0.10);
            setMpIncDecPerc(0.10);
            setMagic(2);
            setSpirit(2);
        } else if (mName.equals(getRedMateriaList()[11])){
            this.materiaName = mName;
            setHpIncDecPerc(-0.10);
            setMpIncDecPerc(0.10);
            setMagic(2);
            setSpirit(2);
        } else if (mName.equals(getRedMateriaList()[12])){
            this.materiaName = mName;
            setHpIncDecPerc(-0.10);
            setMpIncDecPerc(0.15);
            setMagic(4);
            setSpirit(4);
        } else if (mName.equals(getRedMateriaList()[13])){
            this.materiaName = mName;
            setHpIncDecPerc(-0.10);
            setMpIncDecPerc(0.15);
            setMagic(4);
            setSpirit(4);
        } else if (mName.equals(getRedMateriaList()[14])){
            this.materiaName = mName;
            setHpIncDecPerc(-0.10);
            setMpIncDecPerc(0.15);
            setMagic(4);
            setSpirit(4);
        } else if (mName.equals(getRedMateriaList()[15])){
            this.materiaName = mName;
            setHpIncDecPerc(-0.10);
            setMpIncDecPerc(0.20);
            setMagic(8);
            setSpirit(8);
        }
        else if (mName.equals(getRedMateriaList()[16])){
            this.materiaName = mName;
        }
        // Blue Materia
        else if (Arrays.asList(getBlueMateriaList()).contains(mName)){
            this.materiaName = mName;
        }
        // Purple Materia
        else if (mName.equals(getPurpleMateriaList()[0])){
            this.materiaName = mName;
            setMpIncDecPerc(0.50);
        } else if (mName.equals(getPurpleMateriaList()[1])){
            this.materiaName = mName;
            setHpIncDecPerc(0.50);
        } else if (mName.equals(getPurpleMateriaList()[2])){
            this.materiaName = mName;
            setDexIncDecPerc(0.50);
        } else if (mName.equals(getPurpleMateriaList()[3])){
            this.materiaName = mName;
            setMagicIncDecPerc(0.50);
        } else if (mName.equals(getPurpleMateriaList()[4])){
            this.materiaName = mName;
            setLuckIncDecPerc(0.50);
        } else if (mName.equals(getPurpleMateriaList()[5])){
            this.materiaName = mName;
            setLuck(1);
        } else if (mName.equals(getPurpleMateriaList()[6])){
            this.materiaName = mName;
            setLuck(1);
        } else if (mName.equals(getPurpleMateriaList()[7])){
            this.materiaName = mName;
            setLuck(1);
        } else if (mName.equals(getPurpleMateriaList()[8])){
            this.materiaName = mName;
            setLuck(-1);
        } else if (mName.equals(getPurpleMateriaList()[9])){
            this.materiaName = mName;
            setLuck(1);
        } else if (mName.equals(getPurpleMateriaList()[10])){
            this.materiaName = mName;
            setDexterity(2);
        } else if (mName.equals(getPurpleMateriaList()[11])){
            this.materiaName = mName;
        } else if (mName.equals(getPurpleMateriaList()[12])){
            this.materiaName = mName;
        } else if (mName.equals(getPurpleMateriaList()[13])){
            this.materiaName = mName;
        } else if (mName.equals(getPurpleMateriaList()[14])){
            this.materiaName = mName;
            setVitality(1);
        } else if (mName.equals(getPurpleMateriaList()[15])){
            this.materiaName = mName;
        } else if (mName.equals(getPurpleMateriaList()[16])){
            this.materiaName = mName;
            setSwitchHpMpEnabled(true);
        } else if (mName.equals("")){
            this.materiaName = null;
        }
    }

    //**************************************************************************************
}

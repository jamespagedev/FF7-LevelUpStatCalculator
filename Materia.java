/**
 * Created by James Page on 4/18/2017.
 */
package Main;

public class Materia extends Stats {
    private String materia;
    private double hpIncDecPerc;
    private double mpIncDecPerc;
    protected static final String[][] materiaLists = {
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
                    "Hades", "Typhon", "Bahamut ZERO", "Knights of Round"
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

    protected String[] getGreenMateriaList(){
        return this.materiaLists[0];
    }

    //**************************************************************************************

    protected String[] getYellowMateriaList(){
        return this.materiaLists[1];
    }

    //**************************************************************************************

    protected String[] getRedMateriaList(){
        return this.materiaLists[2];
    }

    //**************************************************************************************

    protected String[] getBlueMateriaList(){
        return this.materiaLists[3];
    }

    //**************************************************************************************

    protected String[] getPurpleMateriaList(){
        return this.materiaLists[4];
    }

    //**************************************************************************************

    protected String[][] getMateriaLists(){
        return this.materiaLists;
    }

    //**************************************************************************************



    //**************************************************************************************
}

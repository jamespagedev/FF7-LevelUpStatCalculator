/**
 * Created by James Page on 4/18/2017.
 */
package Main;

import static Main.SupportMethods.createArrRange;

public class Characters extends Stats {
    protected static final String[] Characters = {"Cloud", "Barret", "Tifa", "Aeris", "Red XIII", "Yuffie", "Cait Sith", "Vincent", "Cid"};
    private String Character;
    private int level;
    protected static int[] levelRanges = createArrRange(1, 99);
    private int baseHp;
    private int baseMp;
    private int baseStrength;
    private int baseDexterity;
    private int baseVitality;
    private int baseMagic;
    private int baseSpirit;
    private int baseLuck;
    Weapons weapon = new Weapons();
    Armors armor = new Armors();
    Accessories accessory = new Accessories();
}
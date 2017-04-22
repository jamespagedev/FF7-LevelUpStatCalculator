/**
 * Created by James Page on 4/18/2017.
 */
package Main;

public abstract class Stats {
    private int hp;
    private int mp;
    private int strength;
    private int dexterity;
    private int vitality;
    private int magic;
    private int spirit;
    private int luck;

    //**************************************************************************************

    protected void setHp(int hp){
        this.hp = hp;
    }

    protected int getHp(){
        return this.hp;
    }

    //**************************************************************************************

    protected void setMp(int mp){
        this.mp = mp;
    }

    protected int getMp(){
        return this.mp;
    }

    //**************************************************************************************

    protected void setStrength(int str){
        this.strength = str;
    }

    protected int getStrength(){
        return this.strength;
    }

    //**************************************************************************************

    protected void setDexterity(int dex){
        this.dexterity = dex;
    }

    protected int getDexterity(){
        return this.dexterity;
    }

    //**************************************************************************************

    protected void setVitality(int vit){
        this.vitality = vit;
    }

    protected int getVitality(){
        return this.vitality;
    }

    //**************************************************************************************

    protected void setMagic(int mag){
        this.magic = mag;
    }

    protected int getMagic(){
        return this.magic;
    }

    //**************************************************************************************

    protected void setSpirit(int spr){
        this.spirit = spr;
    }

    protected int getSpirit(){
        return this.spirit;
    }

    //**************************************************************************************

    protected void setLuck(int lck){
        this.luck = lck;
    }

    protected int getLuck(){
        return this.luck;
    }

    //**************************************************************************************

    protected void resetStats(){
        this.hp = 0;
        this.mp = 0;
        this.strength = 0;
        this.dexterity = 0;
        this.vitality = 0;
        this.magic = 0;
        this.spirit = 0;
        this.luck = 0;
    }
}

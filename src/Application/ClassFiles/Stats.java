package ClassFiles;

public abstract class Stats {
    protected int baseHp;
    protected int baseMp;
    protected int baseStrength;
    protected int baseDexterity;
    protected int baseVitality;
    protected int baseMagic;
    protected int baseSpirit;
    protected int baseLuck;
    protected final int maxBaseHp = 9999;
    protected final int maxHp = 9999;
    protected final int minHp = 0;
    protected final int maxBaseMp = 999;
    protected final int maxMp = 999;
    protected final int minMp = 0;
    protected final int maxPrimaryStat = 255;
    protected final int minPrimaryStat = 0;
    public int hp;
    public int mp;
    public int strength;
    public int dexterity;
    public int vitality;
    public int magic;
    public int spirit;
    public int luck;

    //**************************************************************************************

    public void setHp(int hp){
        this.hp = hp;
    }

    public int getHp(){
        return this.hp;
    }

    //**************************************************************************************

    public void setMp(int mp){
        this.mp = mp;
    }

    public int getMp(){
        return this.mp;
    }

    //**************************************************************************************

    public void setStrength(int str){
        this.strength = str;
    }

    public int getStrength(){
        return this.strength;
    }

    //**************************************************************************************

    public void setDexterity(int dex){
        this.dexterity = dex;
    }

    public int getDexterity(){
        return this.dexterity;
    }

    //**************************************************************************************

    public void setVitality(int vit){
        this.vitality = vit;
    }

    public int getVitality(){
        return this.vitality;
    }

    //**************************************************************************************

    public void setMagic(int mag){
        this.magic = mag;
    }

    public int getMagic(){
        return this.magic;
    }

    //**************************************************************************************

    public void setSpirit(int spr){
        this.spirit = spr;
    }

    public int getSpirit(){
        return this.spirit;
    }

    //**************************************************************************************

    public void setLuck(int lck){
        this.luck = lck;
    }

    public int getLuck(){
        return this.luck;
    }



    //**************************************************************************************
    public void resetStats(){
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

/**
 * Created by James Page on 4/18/2017.
 */
package ClassFiles;

import java.util.HashMap;
import java.util.Map;

public class Characters extends Stats {
    protected static final String[] characterNames = {"Cloud", "Barret", "Tifa", "Aeris", "Red XIII", "Yuffie", "Cait Sith", "Vincent", "Cid"};
    private String characterName;
    public int level;
    private int[] levelRanges = setLevelRanges(1, 99);
    private Materia[] wpnMateriaSlots;
    private Materia[] armMateriaSlots;
    private boolean switchHpMpMateriaEquiped = false;
    private String[] charWeaponList;
    private Weapons weapon = new Weapons();
    private Armors armor = new Armors();
    private Accessories accessory = new Accessories();
    private final Materia materiaInventory = new Materia();

    //**************************************************************************************
    // Precondition - name must equal to the string in one of the indexes for array characterNames[]
    public Characters(String name){
        Map<String, Runnable> createCharacter = new HashMap<>();

        createCharacter.put(characterNames[0], () -> createCloud());
        createCharacter.put(characterNames[1], () -> createBarret());
        createCharacter.put(characterNames[2], () -> createTifa());
        createCharacter.put(characterNames[3], () -> createAeris());
        createCharacter.put(characterNames[4], () -> createRedXIII());
        createCharacter.put(characterNames[5], () -> createYuffie());
        createCharacter.put(characterNames[6], () -> createCaitSith());
        createCharacter.put(characterNames[7], () -> createVincent());
        createCharacter.put(characterNames[8], () -> createCid());

        createCharacter.get(name).run();
    }

    //**************************************************************************************
    public void setCharacterName(String name){
        this.characterName = name;
    }
    public String getCharacterName(){
        return this.characterName;
    }
    public static String[] getListOfCharacterNames(){
        return characterNames;
    }

    //**************************************************************************************
    public void equipWeapon(String wpn){
        weapon.setWeapon(wpn);
        setWpnMateriaSlots();
        updateStats();
    }

    public Weapons getEquipedWeapon(){
        return this.weapon;
    }

    // Precondition - Character name will be found in getListOfCharacterNames()
    public void setListOfCharacterWeapons(String name){
        for (int character = 0; character < getListOfCharacterNames().length; character++){
            if (name.equals(getListOfCharacterNames()[character])){
                this.charWeaponList = Weapons.getAllWeapons()[character];
                break;
            }
        }
    }

    public String[] getListOfCharacterWeapons(){
        return this.charWeaponList;
    }

    //**************************************************************************************
    public void equipArmor(String arm){
        armor.setArmorName(arm);
        setArmMateriaSlots();
        updateStats();
    }

    public Armors getEquipedArmor(){
        return this.armor;
    }

    //**************************************************************************************
    public void equipAccessory(String acc){
        accessory.setAccessoryName(acc);
        updateStats();
    }

    public Accessories getEquipedAccessory(){
        return this.accessory;
    }

    //**************************************************************************************
    public void setSwitchHpMpMateriaEquiped(boolean switchHpMpMateriaEquiped){
        this.switchHpMpMateriaEquiped = switchHpMpMateriaEquiped;
    }

    public boolean isSwitchHpMpMateriaEquiped(){
        return this.switchHpMpMateriaEquiped;
    }

    //**************************************************************************************
    //*************************************** Materia **************************************
    //**************************************************************************************
    public Materia setMateriaSlotEmpty(){
        return new Materia("Empty");
    }

    //**************************************************************************************
    //*********************************** Weapon Materia ***********************************
    //**************************************************************************************

    public void initalizeWpnMateriaSlotsFromEmpty(){
        this.wpnMateriaSlots = new Materia[this.getEquipedWeapon().getNumOfMateriaSlots()];
        for (int mSlot = 0; mSlot < this.getEquipedWeapon().getNumOfMateriaSlots(); mSlot++) {
            this.wpnMateriaSlots[mSlot] = setMateriaSlotEmpty();
        }
    }

    public void removeWpnMateriaSlots(Materia[] oldWpnMateriaSlots){
        this.wpnMateriaSlots = new Materia[this.getEquipedWeapon().getNumOfMateriaSlots()];
        for (int mSlotIndex = 0; mSlotIndex < getEquipedWeapon().getNumOfMateriaSlots(); mSlotIndex++){
            this.wpnMateriaSlots[mSlotIndex] = oldWpnMateriaSlots[mSlotIndex]; // keep equipped materia from old slot
        }
    }

    public void addWpnMateriaSlots(Materia[] oldWpnMateriaSlots){
        this.wpnMateriaSlots = new Materia[this.getEquipedWeapon().getNumOfMateriaSlots()];
        for (int mSlotIndex = 0; mSlotIndex < getEquipedWeapon().getNumOfMateriaSlots(); mSlotIndex++){
            if (mSlotIndex < oldWpnMateriaSlots.length){
                this.wpnMateriaSlots[mSlotIndex] = oldWpnMateriaSlots[mSlotIndex]; // keep equipped materia from old slot
            } else {
                this.wpnMateriaSlots[mSlotIndex] = setMateriaSlotEmpty(); // add materia slot
            }
        }
    }

    public void updateWpnMateriaSlots(){
        Materia[] oldWpnMateriaSlots = getWpnMateriaSlots();
        if (oldWpnMateriaSlots.length > getEquipedWeapon().getNumOfMateriaSlots()){ // old weapon has more materia slots
            removeWpnMateriaSlots(oldWpnMateriaSlots);
        } else { // old weapon has less than or equal to current weapon materia slots
            addWpnMateriaSlots(oldWpnMateriaSlots);
        }
    }



    public void setWpnMateriaSlots(){
        if (this.wpnMateriaSlots == null){
            initalizeWpnMateriaSlotsFromEmpty();
        } else {
            updateWpnMateriaSlots();
        }
    }

    public Materia[] getWpnMateriaSlots(){
        return this.wpnMateriaSlots;
    }

    public void equipMateriaInWeapon(int materiaSlot, String materiaName){ // materiaSlot == 1-8
        materiaSlot--;
        this.wpnMateriaSlots[materiaSlot].setMateriaName(materiaName);
        updateStats();
    }

    public Materia getMateriaInWpnSlot(int materiaSlot){
        materiaSlot--;
        return getWpnMateriaSlots()[materiaSlot];
    }

    //**************************************************************************************
    public void setArmMateriaSlots(){
        if (this.armMateriaSlots == null){
            this.armMateriaSlots = new Materia[this.getEquipedArmor().getNumOfMateriaSlots()];
            for (int mSlot = 0; mSlot < this.getEquipedArmor().getNumOfMateriaSlots(); mSlot++){
                this.armMateriaSlots[mSlot] = new Materia("Empty");
            }
        } else {
            Materia[] oldArmMateriaSlots = getArmMateriaSlots();
            this.armMateriaSlots = new Materia[this.getEquipedArmor().getNumOfMateriaSlots()];
            if (oldArmMateriaSlots.length > getEquipedArmor().getNumOfMateriaSlots()){
                for (int mSlotIndex = 0; mSlotIndex < getEquipedArmor().getNumOfMateriaSlots(); mSlotIndex++){
                    this.armMateriaSlots[mSlotIndex] = oldArmMateriaSlots[mSlotIndex];
                }
            } else {
                for (int mSlotIndex = 0; mSlotIndex < getEquipedArmor().getNumOfMateriaSlots(); mSlotIndex++){
                    if (mSlotIndex < oldArmMateriaSlots.length){
                        this.armMateriaSlots[mSlotIndex] = oldArmMateriaSlots[mSlotIndex];
                    } else {
                        this.armMateriaSlots[mSlotIndex] = new Materia("Empty");
                    }
                }
            }
        }
    }

    public Materia[] getArmMateriaSlots(){
        return this.armMateriaSlots;
    }

    public void equipMateriaInArmor(int materiaSlot, String materiaName){ // materiaSlot == 1-8
        materiaSlot--;
        this.armMateriaSlots[materiaSlot].setMateriaName(materiaName);
        updateStats();
    }

    public Materia getMateriaInArmSlot(int materiaSlot){
        materiaSlot--;
        return getArmMateriaSlots()[materiaSlot];
    }

    //**************************************************************************************
    public void setLevel(int lvl){
        this.level = lvl;
    }

    public int getLevel(){
        return this.level;
    }

    //**************************************************************************************

    public int[] setLevelRanges(int minLevel, int maxLevel){
        return SupportMethods.createArrRange(minLevel, maxLevel);
    }

    public int[] getLevelRanges(){
        return this.levelRanges;
    }

    //**************************************************************************************
    public void swapHpMp(){
        int tempHp = getHp();
        setHp(getMp());
        setMp(tempHp);
    }

    public void setBaseHp(int hp){
        if (hp > this.maxHp){
            this.baseHp = this.maxHp;
        } else if (hp < 0){
            this.baseHp = 0;
        } else {
            this.baseHp = hp;
        }
        updateStats();
    }

    public int getBaseHp(){
        return this.baseHp;
    }

    public int getMaxBaseHp(){ // use for checking setHp things
        return this.maxBaseHp;
    }

    public int getMaxHp(){ // use for checking HP <-> MP Materia
        return this.maxHp;
    }

    public int calculateBaseHpNumWithGear(int baseHpNum){
        int hpNumWithGear;
        if (baseHpNum > this.maxHp){
            hpNumWithGear = this.maxHp;
        } else if (baseHpNum < 0) {
            hpNumWithGear = 0;
        } else {
            hpNumWithGear = baseHpNum;
        }
        double materiaHpIncDecPerc = 0.00;

        // HP from Weapon
        hpNumWithGear += weapon.getHp();

        // HP from armor
        hpNumWithGear += armor.getHp();

        // HP from accessory
        hpNumWithGear += accessory.getHp();

        // HP from equiped materia (Weapon)
        if (getWpnMateriaSlots() != null) {
            for (Materia materia : getWpnMateriaSlots()) {
                materiaHpIncDecPerc += materia.getHpIncDecPerc();
                hpNumWithGear += materia.getHp();
            }
        }

        // HP from equiped materia (Armor)
        if (getArmMateriaSlots() != null) {
            for (Materia materia : getArmMateriaSlots()) {
                materiaHpIncDecPerc += materia.getHpIncDecPerc();
                hpNumWithGear += materia.getHp();
            }
        }
        hpNumWithGear += (int)((double)(hpNumWithGear * materiaHpIncDecPerc));

        if (hpNumWithGear > this.maxHp){
            return this.maxHp;
        } else if (hpNumWithGear < 0) {
            return 0;
        }
        return hpNumWithGear;
    }

    //**************************************************************************************
    public void setBaseMp(int mp){
        if (mp > this.maxMp){
            this.baseMp = this.maxMp;
        } else if (mp < 0){
            this.baseMp = 0;
        } else {
            this.baseMp = mp;
        }
        updateStats();
    }

    public int getBaseMp(){
        return this.baseMp;
    }

    public int getMaxBaseMp(){
        return this.maxBaseMp;
    } // use for checking setMp things

    public int getMaxMp(){
        return this.maxMp;
    } // use for checking HP <-> MP Materia

    public int calculateBaseMpNumWithGear(int baseMpNum){
        int mpNumWithGear;
        if (baseMpNum > this.maxMp){
            mpNumWithGear = this.maxMp;
        } else if (baseMpNum < 0) {
            mpNumWithGear = 0;
        } else {
            mpNumWithGear = baseMpNum;
        }
        double materiaMpIncDecPerc = 0.00;

        // MP from Weapon
        mpNumWithGear += weapon.getMp();

        // MP from armor
        mpNumWithGear += armor.getMp();

        // MP from accessory
        mpNumWithGear += accessory.getMp();

        // MP from equiped materia (Weapon)
        if (getWpnMateriaSlots() != null) {
            for (Materia materia : getWpnMateriaSlots()) {
                materiaMpIncDecPerc += materia.getMpIncDecPerc();
                mpNumWithGear += materia.getMp();
            }
        }

        // MP from equiped materia (Armor)
        if (getArmMateriaSlots() != null) {
            for (Materia materia : getArmMateriaSlots()) {
                materiaMpIncDecPerc += materia.getMpIncDecPerc();
                mpNumWithGear += materia.getMp();
            }
        }
        mpNumWithGear += (int)((double)(mpNumWithGear * materiaMpIncDecPerc));

        if (mpNumWithGear > this.maxMp){
            return this.maxMp;
        } else if (mpNumWithGear < 0) {
            return 0;
        }
        return mpNumWithGear;
    }

    //**************************************************************************************
    public void setBaseStrength(int str){
        if (str > this.maxPrimaryStat){
            this.baseStrength = this.maxPrimaryStat;
        } else if (str < 0){
            this.baseStrength = 0;
        } else {
            this.baseStrength = str;
        }
        updateStats();
    }

    public int getBaseStrength(){
        return this.baseStrength;
    }

    public int getMaxPrimaryStat(){
        return this.maxPrimaryStat;
    }

    public int calculateBaseStrNumWithGear(int baseStrNum){
        int strNumWithGear;
        if (baseStrNum > this.maxPrimaryStat){
            strNumWithGear = this.maxPrimaryStat;
        } else if (baseStrNum < 0) {
            strNumWithGear = 0;
        } else {
            strNumWithGear = baseStrNum;
        }

        // Strength from Weapon
        strNumWithGear += weapon.getStrength();

        // Strength from armor
        strNumWithGear += armor.getStrength();

        // Strength from accessory
        strNumWithGear += accessory.getStrength();

        // Strength from equiped materia (Weapon)
        if (getWpnMateriaSlots() != null) {
            for (Materia materia : getWpnMateriaSlots()) {
                strNumWithGear += materia.getStrength();
            }
        }

        // Strength from equiped materia (Armor)
        if (getArmMateriaSlots() != null) {
            for (Materia materia : getArmMateriaSlots()) {
                strNumWithGear += materia.getStrength();
            }
        }

        if (strNumWithGear > this.maxPrimaryStat){
            return this.maxPrimaryStat;
        } else if (strNumWithGear < 0) {
            return 0;
        }
        return strNumWithGear;
    }

    //**************************************************************************************
    public void setBaseDexterity(int dex){
        if (dex > this.maxPrimaryStat){
            this.baseDexterity = this.maxPrimaryStat;
        } else if (dex < 0){
            this.baseDexterity = 0;
        } else {
            this.baseDexterity = dex;
        }
        updateStats();
    }

    public int getBaseDexterity(){
        return this.baseDexterity;
    }

    public int calculateBaseDexNumWithGear(int baseDexNum){
        int dexNumWithGear;
        if (baseDexNum > this.maxPrimaryStat){
            dexNumWithGear = this.maxPrimaryStat;
        } else if (baseDexNum < 0) {
            dexNumWithGear = 0;
        } else {
            dexNumWithGear = baseDexNum;
        }
        double materiaDexIncDecPerc = 0.00;

        // Dexterity from Weapon
        dexNumWithGear += weapon.getDexterity();

        // Dexterity from armor
        dexNumWithGear += armor.getDexterity();

        // Dexterity from accessory
        dexNumWithGear += accessory.getDexterity();

        // Dexterity from equiped materia (Weapon)
        if (getWpnMateriaSlots() != null) {
            for (Materia materia : getWpnMateriaSlots()) {
                materiaDexIncDecPerc += materia.getDexIncDecPerc();
                dexNumWithGear += materia.getDexterity();
            }
        }

        // Dexterity from equiped materia (Armor)
        if (getArmMateriaSlots() != null) {
            for (Materia materia : getArmMateriaSlots()) {
                materiaDexIncDecPerc += materia.getDexIncDecPerc();
                dexNumWithGear += materia.getDexterity();
            }
        }
        dexNumWithGear += (int)((double)(dexNumWithGear * materiaDexIncDecPerc));

        if (dexNumWithGear > this.maxPrimaryStat){
            return this.maxPrimaryStat;
        } else if (dexNumWithGear < 0) {
            return 0;
        }
        return dexNumWithGear;
    }

    //**************************************************************************************
    public void setBaseVitality(int vit){
        if (vit > this.maxPrimaryStat){
            this.baseVitality = this.maxPrimaryStat;
        } else if (vit < 0){
            this.baseVitality = 0;
        } else {
            this.baseVitality = vit;
        }
        updateStats();
    }

    public int getBaseVitality(){
        return this.baseVitality;
    }

    public int calculateBaseVitNumWithGear(int baseVitNumAfterLevelUp){
        int vitNumWithGearAfterLevelUp;

        if (baseVitNumAfterLevelUp > this.maxPrimaryStat){
            vitNumWithGearAfterLevelUp = this.maxPrimaryStat;
        } else if (baseVitNumAfterLevelUp < 0) {
            vitNumWithGearAfterLevelUp = 0;
        } else {
            vitNumWithGearAfterLevelUp = baseVitNumAfterLevelUp;
        }

        // Vitality from Weapon
        vitNumWithGearAfterLevelUp += weapon.getVitality();

        // Vitality from armor
        vitNumWithGearAfterLevelUp += armor.getVitality();

        // Vitality from accessory
        vitNumWithGearAfterLevelUp += accessory.getVitality();

        // Vitality from equiped materia (Weapon)
        if (getWpnMateriaSlots() != null) {
            for (Materia materia : getWpnMateriaSlots()) {
                vitNumWithGearAfterLevelUp += materia.getVitality();
            }
        }

        // Vitality from equiped materia (Armor)
        if (getArmMateriaSlots() != null) {
            for (Materia materia : getArmMateriaSlots()) {
                vitNumWithGearAfterLevelUp += materia.getVitality();
            }
        }

        if (vitNumWithGearAfterLevelUp > this.maxPrimaryStat){
            return this.maxPrimaryStat;
        } else if (vitNumWithGearAfterLevelUp < 0) {
            return 0;
        }
        return vitNumWithGearAfterLevelUp;
    }

    //**************************************************************************************
    public void setBaseMagic(int mag){
        if (mag > this.maxPrimaryStat){
            this.baseMagic = this.maxPrimaryStat;
        } else if (mag < 0){
            this.baseMagic = 0;
        } else {
            this.baseMagic = mag;
        }
        updateStats();
    }

    public int getBaseMagic(){
        return this.baseMagic;
    }

    public int calculateBaseMagNumWithGear(int baseMagNum){
        int magNumWithGear;
        if (baseMagNum > this.maxPrimaryStat){
            magNumWithGear = this.maxPrimaryStat;
        } else if (baseMagNum < 0) {
            magNumWithGear = 0;
        } else {
            magNumWithGear = baseMagNum;
        }
        double materiaMagIncDecPerc = 0.00;

        // Magic from Weapon
        magNumWithGear += weapon.getMagic();

        // Magic from armor
        magNumWithGear += armor.getMagic();

        // Magic from accessory
        magNumWithGear += accessory.getMagic();

        // Magic from equiped materia (Weapon)
        if (getWpnMateriaSlots() != null) {
            for (Materia materia : getWpnMateriaSlots()) {
                materiaMagIncDecPerc += materia.getMagicIncDecPerc();
                magNumWithGear += materia.getMagic();
            }
        }

        // Magic from equiped materia (Armor)
        if (getArmMateriaSlots() != null) {
            for (Materia materia : getArmMateriaSlots()) {
                materiaMagIncDecPerc += materia.getMagicIncDecPerc();
                magNumWithGear += materia.getMagic();
            }
        }
        magNumWithGear += (int)((double)(magNumWithGear * materiaMagIncDecPerc));

        if (magNumWithGear > this.maxPrimaryStat){
            return this.maxPrimaryStat;
        } else if (magNumWithGear < 0) {
            return 0;
        }
        return magNumWithGear;
    }

    //**************************************************************************************
    public void setBaseSpirit(int spr){
        if (spr > this.maxPrimaryStat){
            this.baseSpirit = this.maxPrimaryStat;
        } else if (spr < 0){
            this.baseSpirit = 0;
        } else {
            this.baseSpirit = spr;
        }
        updateStats();
    }

    public int getBaseSpirit(){
        return this.baseSpirit;
    }

    public int calculateBaseSprNumWithGear(int baseSprNum){
        int sprNumWithGear;
        if (baseSprNum > this.maxPrimaryStat){
            sprNumWithGear = this.maxPrimaryStat;
        } else if (baseSprNum < 0) {
            sprNumWithGear = 0;
        } else {
            sprNumWithGear = baseSprNum;
        }

        // Spirit from Weapon
        sprNumWithGear += weapon.getSpirit();

        // Spirit from armor
        sprNumWithGear += armor.getSpirit();

        // Spirit from accessory
        sprNumWithGear += accessory.getSpirit();

        // Spirit from equiped materia (Weapon)
        if (getWpnMateriaSlots() != null) {
            for (Materia materia : getWpnMateriaSlots()) {
                sprNumWithGear += materia.getSpirit();
            }
        }

        // Spirit from equiped materia (Armor)
        if (getArmMateriaSlots() != null) {
            for (Materia materia : getArmMateriaSlots()) {
                sprNumWithGear += materia.getSpirit();
            }
        }

        if (sprNumWithGear > this.maxPrimaryStat){
            return this.maxPrimaryStat;
        } else if (sprNumWithGear < 0) {
            return 0;
        }
        return sprNumWithGear;
    }

    //**************************************************************************************
    public void setBaseLuck(int lck){
        if (lck > this.maxPrimaryStat){
            this.baseLuck = this.maxPrimaryStat;
        } else if (lck < 0){
            this.baseLuck = 0;
        } else {
            this.baseLuck = lck;
        }
        updateStats();
    }

    public int getBaseLuck(){
        return this.baseLuck;
    }

    public int calculateBaseLckNumWithGear(int baseLckNum){
        int lckNumWithGear;
        if (baseLckNum > this.maxPrimaryStat){
            lckNumWithGear = this.maxPrimaryStat;
        } else if (baseLckNum < 0) {
            lckNumWithGear = 0;
        } else {
            lckNumWithGear = baseLckNum;
        }
        double materiaLckIncDecPerc = 0.00;

        // Luck from Weapon
        lckNumWithGear += weapon.getLuck();

        // Luck from armor
        lckNumWithGear += armor.getLuck();

        // Luck from accessory
        lckNumWithGear += accessory.getLuck();

        // Luck from equiped materia (Weapon)
        if (getWpnMateriaSlots() != null) {
            for (Materia materia : getWpnMateriaSlots()) {
                materiaLckIncDecPerc += materia.getLuckIncDecPerc();
                lckNumWithGear += materia.getLuck();
            }
        }

        // Luck from equiped materia (Armor)
        if (getArmMateriaSlots() != null) {
            for (Materia materia : getArmMateriaSlots()) {
                materiaLckIncDecPerc += materia.getLuckIncDecPerc();
                lckNumWithGear += materia.getLuck();
            }
        }
        lckNumWithGear += (int)((double)(lckNumWithGear * materiaLckIncDecPerc));

        if (lckNumWithGear > this.maxPrimaryStat){
            return this.maxPrimaryStat;
        } else if (lckNumWithGear < 0) {
            return 0;
        }
        return lckNumWithGear;
    }

    //**************************************************************************************
    public void updateStats(){
        resetStats();
        setSwitchHpMpMateriaEquiped(false);

        // Stats from character
        int tempHp = getBaseHp();
        int tempMp = getBaseMp();
        int tempStr = getBaseStrength();
        int tempDex = getBaseDexterity();
        int tempVit = getBaseVitality();
        int tempMag = getBaseMagic();
        int tempSpr = getBaseSpirit();
        int tempLck = getBaseLuck();


        // Stats from Weapon
        tempHp += weapon.getHp();
        tempMp += weapon.getMp();
        tempStr += weapon.getStrength();
        tempDex += weapon.getDexterity();
        tempVit += weapon.getVitality();
        tempMag += weapon.getMagic();
        tempSpr += weapon.getSpirit();
        tempLck += weapon.getLuck();

        // Stats from armor
        tempHp += armor.getHp();
        tempMp += armor.getMp();
        tempStr += armor.getStrength();
        tempDex += armor.getDexterity();
        tempVit += armor.getVitality();
        tempMag += armor.getMagic();
        tempSpr += armor.getSpirit();
        tempLck += armor.getLuck();

        // Stats from accessory
        tempHp += accessory.getHp();
        tempMp += accessory.getMp();
        tempStr += accessory.getStrength();
        tempDex += accessory.getDexterity();
        tempVit += accessory.getVitality();
        tempMag += accessory.getMagic();
        tempSpr += accessory.getSpirit();
        tempLck += accessory.getLuck();

        // Stats from equiped materia
        double materiaHpIncDecPerc = 0.00;
        double materiaMpIncDecPerc = 0.00;
        double materiaDexIncDecPerc = 0.00;
        double materiaMagicIncDecPerc = 0.00;
        double materiaLuckIncDecPerc = 0.00;
        if (getWpnMateriaSlots() != null){
            for (Materia materia : getWpnMateriaSlots()){
                materiaHpIncDecPerc += materia.getHpIncDecPerc();
                materiaMpIncDecPerc += materia.getMpIncDecPerc();
                materiaDexIncDecPerc += materia.getDexIncDecPerc();
                materiaMagicIncDecPerc += materia.getMagicIncDecPerc();
                materiaLuckIncDecPerc += materia.getLuckIncDecPerc();
                tempHp += materia.getHp();
                tempMp += materia.getMp();
                tempStr += materia.getStrength();
                tempDex += materia.getDexterity();
                tempVit += materia.getVitality();
                tempMag += materia.getMagic();
                tempSpr += materia.getSpirit();
                tempLck += materia.getLuck();
                if (materia.isSitchHpMpEnabled()){
                    setSwitchHpMpMateriaEquiped(true);
                }
            }
        }
        if (getArmMateriaSlots() != null){
            for (Materia materia : getArmMateriaSlots()){
                materiaHpIncDecPerc += materia.getHpIncDecPerc();
                materiaMpIncDecPerc += materia.getMpIncDecPerc();
                materiaDexIncDecPerc += materia.getDexIncDecPerc();
                materiaMagicIncDecPerc += materia.getMagicIncDecPerc();
                materiaLuckIncDecPerc += materia.getLuckIncDecPerc();
                tempHp += materia.getHp();
                tempMp += materia.getMp();
                tempStr += materia.getStrength();
                tempDex += materia.getDexterity();
                tempVit += materia.getVitality();
                tempMag += materia.getMagic();
                tempSpr += materia.getSpirit();
                tempLck += materia.getLuck();
                if (materia.isSitchHpMpEnabled()){
                    setSwitchHpMpMateriaEquiped(true);
                }
            }
        }

        tempHp += (int)((double)(tempHp * materiaHpIncDecPerc));
        tempMp += (int)((double)(tempMp * materiaMpIncDecPerc));
        tempDex += (int)((double)(tempDex * materiaDexIncDecPerc));
        tempMag += (int)((double)(tempMag * materiaMagicIncDecPerc));
        tempLck += (int)((double)(tempLck * materiaLuckIncDecPerc));

        if (tempHp > this.maxHp){
            setHp(this.maxHp);
        } else if (tempHp < this.minHp){
            setHp(this.minHp);
        } else {
            setHp(tempHp);
        }

        if (tempMp > this.maxMp){
            setMp(this.maxMp);
        } else if (tempMp < this.minMp){
            setMp(this.minMp);
        } else {
            setMp(tempMp);
        }

        if (tempStr > this.maxPrimaryStat){
            setStrength(this.maxPrimaryStat);
        } else if (tempStr < this.minPrimaryStat){
            setStrength(this.minPrimaryStat);
        } else {
            setStrength(tempStr);
        }

        if (tempDex > this.maxPrimaryStat){
            setDexterity(this.maxPrimaryStat);
        } else if (tempDex < this.minPrimaryStat){
            setDexterity(this.minPrimaryStat);
        } else {
            setDexterity(tempDex);
        }

        if (tempVit > this.maxPrimaryStat){
            setVitality(this.maxPrimaryStat);
        } else if (tempVit < this.minPrimaryStat){
            setVitality(this.minPrimaryStat);
        } else {
            setVitality(tempVit);
        }

        if (tempMag > this.maxPrimaryStat){
            setMagic(this.maxPrimaryStat);
        } else if (tempMag < this.minPrimaryStat){
            setMagic(this.minPrimaryStat);
        } else {
            setMagic(tempMag);
        }

        if (tempSpr > this.maxPrimaryStat){
            setSpirit(this.maxPrimaryStat);
        } else if (tempSpr < this.minPrimaryStat){
            setSpirit(this.minPrimaryStat);
        } else {
            setSpirit(tempSpr);
        }

        if (tempLck > this.maxPrimaryStat){
            setLuck(this.maxPrimaryStat);
        } else if (tempLck < this.minPrimaryStat){
            setLuck(this.minPrimaryStat);
        } else {
            setLuck(tempLck);
        }

        // switch Mp/Hp if switch materia is equipped
        if (isSwitchHpMpMateriaEquiped()){
            swapHpMp();
        }
    }

    //**************************************************************************************
    public void createCloud(){
        setCharacterName(getListOfCharacterNames()[0]); // Cloud
        setLevel(6);
        setBaseHp(314);
        setBaseMp(54);
        setBaseStrength(20);
        setBaseDexterity(6);
        setBaseVitality(16);
        setBaseMagic(19);
        setBaseSpirit(17);
        setBaseLuck(14);
        setListOfCharacterWeapons(getCharacterName()); // List of unique weapons for cloud
        equipWeapon(getListOfCharacterWeapons()[0]); // Buster Sword - initial weapon
        equipArmor(armor.getArmorsList()[0]); // Bronze Bangle - initial armor
        equipAccessory(accessory.getAccessoriesList()[0]); // None - does not have accessory equipped
        equipMateriaInWeapon(1, materiaInventory.getGreenMateriaList()[3]); // Equipped lightning materia on weapon slot 1
        equipMateriaInWeapon(2, materiaInventory.getGreenMateriaList()[1]); // Equipped ice materia on weapon slot 2
    }

    public void createBarret(){
        setCharacterName(getListOfCharacterNames()[1]); // Barret
        setLevel(1);
        setBaseHp(222);
        setBaseMp(15);
        setBaseStrength(15);
        setBaseDexterity(5);
        setBaseVitality(13);
        setBaseMagic(11);
        setBaseSpirit(9);
        setBaseLuck(13);
        setListOfCharacterWeapons(getCharacterName()); // List of unique weapons for Barret
        equipWeapon(getListOfCharacterWeapons()[0]); // Gatling Gun - initial weapon
        equipArmor(armor.getArmorsList()[0]); // Bronze Bangle - initial armor
        equipAccessory(accessory.getAccessoriesList()[0]); // None - does not have accessory equipped
    }

    public void createTifa(){
        setCharacterName(getListOfCharacterNames()[2]); // Tifa
        setLevel(1);
        setBaseHp(219);
        setBaseMp(16);
        setBaseStrength(11);
        setBaseDexterity(7);
        setBaseVitality(11);
        setBaseMagic(11);
        setBaseSpirit(10);
        setBaseLuck(14);
        setListOfCharacterWeapons(getCharacterName()); // List of unique weapons for Tifa
        equipWeapon(getListOfCharacterWeapons()[0]); // Leather Glove - initial weapon
        equipArmor(armor.getArmorsList()[0]); // Bronze Bangle - initial armor
        equipAccessory(accessory.getAccessoriesList()[0]); // None - does not have accessory equipped
    }

    public void createAeris(){
        setCharacterName(getListOfCharacterNames()[3]); // Aeris
        setLevel(1);
        setBaseHp(177);
        setBaseMp(23);
        setBaseStrength(10);
        setBaseDexterity(5);
        setBaseVitality(11);
        setBaseMagic(13);
        setBaseSpirit(14);
        setBaseLuck(14);
        setListOfCharacterWeapons(getCharacterName()); // List of unique weapons for Aeris
        equipWeapon(getListOfCharacterWeapons()[0]); // Guard Rod - initial weapon
        equipArmor(armor.getArmorsList()[0]); // Bronze Bangle - initial armor
        equipAccessory(accessory.getAccessoriesList()[0]); // None - does not have accessory equipped
    }

    public void createRedXIII(){
        setCharacterName(getListOfCharacterNames()[4]); // Red XIII
        setLevel(1);
        setBaseHp(221);
        setBaseMp(17);
        setBaseStrength(10);
        setBaseDexterity(10);
        setBaseVitality(12);
        setBaseMagic(11);
        setBaseSpirit(10);
        setBaseLuck(14);
        setListOfCharacterWeapons(getCharacterName()); // List of unique weapons for Red XIII
        equipWeapon(getListOfCharacterWeapons()[0]); // Mythril Clip - initial weapon
        equipArmor(armor.getArmorsList()[3]); // Mythril Armlet - initial armor
        equipAccessory(accessory.getAccessoriesList()[0]); // None - does not have accessory equipped
        equipMateriaInWeapon(1, materiaInventory.getGreenMateriaList()[0]); // Equipped fire materia on weapon slot 1
        equipMateriaInWeapon(2, materiaInventory.getBlueMateriaList()[0]); // Equipped all materia on weapon slot 2
        equipMateriaInArmor(1, materiaInventory.getYellowMateriaList()[6]); // Equipped sense materia on armor slot 1
    }

    public void createYuffie(){
        setCharacterName(getListOfCharacterNames()[5]); // Yuffie
        setLevel(1);
        setBaseHp(100);
        setBaseMp(5);
        setBaseStrength(1);
        setBaseDexterity(1);
        setBaseVitality(1);
        setBaseMagic(1);
        setBaseSpirit(1);
        setBaseLuck(1);
        setListOfCharacterWeapons(getCharacterName()); // List of unique weapons for Yuffie
        equipWeapon(getListOfCharacterWeapons()[0]); // 4-Point Shuriken - initial weapon
        equipArmor(armor.getArmorsList()[6]); // Carbon Bangle - initial armor
        equipAccessory(accessory.getAccessoriesList()[0]); // None - does not have accessory equipped
        equipMateriaInWeapon(1, materiaInventory.getYellowMateriaList()[7]); // Equipped throw materia on weapon slot 1
    }

    public void createCaitSith(){
        setCharacterName(getListOfCharacterNames()[6]); // Cait Sith
        setLevel(1);
        setBaseHp(224);
        setBaseMp(18);
        setBaseStrength(10);
        setBaseDexterity(5);
        setBaseVitality(11);
        setBaseMagic(13);
        setBaseSpirit(11);
        setBaseLuck(15);
        setListOfCharacterWeapons(getCharacterName()); // List of unique weapons for Cait Sith
        equipWeapon(getListOfCharacterWeapons()[0]); // Yellow Megaphone - initial weapon
        equipArmor(armor.getArmorsList()[8]); // Silver Armlet - initial armor
        equipAccessory(accessory.getAccessoriesList()[0]); // None - does not have accessory equipped
        equipMateriaInWeapon(1, materiaInventory.getYellowMateriaList()[10]); // Equipped manipulate materia on weapon slot 1
        equipMateriaInWeapon(2, materiaInventory.getGreenMateriaList()[9]); // Equipped transform materia on weapon slot 1
    }

    public void createVincent(){
        setCharacterName(getListOfCharacterNames()[7]); // Vincent
        setLevel(1);
        setBaseHp(178);
        setBaseMp(18);
        setBaseStrength(9);
        setBaseDexterity(5);
        setBaseVitality(10);
        setBaseMagic(11);
        setBaseSpirit(11);
        setBaseLuck(14);
        setListOfCharacterWeapons(getCharacterName()); // List of unique weapons for Vincent
        equipWeapon(getListOfCharacterWeapons()[0]); // Quicksilver - initial weapon
        equipArmor(armor.getArmorsList()[8]); // Silver Armlet - initial armor
        equipAccessory(accessory.getAccessoriesList()[0]); // None - does not have accessory equipped
    }

    public void createCid(){
        setCharacterName(getListOfCharacterNames()[8]); // Cid
        setLevel(1);
        setBaseHp(223);
        setBaseMp(15);
        setBaseStrength(12);
        setBaseDexterity(6);
        setBaseVitality(12);
        setBaseMagic(11);
        setBaseSpirit(10);
        setBaseLuck(14);
        setListOfCharacterWeapons(getCharacterName()); // List of unique weapons for Cid
        equipWeapon(getListOfCharacterWeapons()[0]); // Spear - initial weapon
        equipArmor(armor.getArmorsList()[9]); // Gold Armlet - initial armor
        equipAccessory(accessory.getAccessoriesList()[0]); // None - does not have accessory equipped
    }
}
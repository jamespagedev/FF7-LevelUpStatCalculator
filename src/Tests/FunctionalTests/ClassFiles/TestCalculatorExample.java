/**
 * Created by James Page on 5/2/2017.
 */
package ClassFiles;

public class TestCalculatorExample extends Calculator {
    //**************************************************************************************************
    //***************************************** Display Methods ****************************************
    //**************************************************************************************************
    public static void printHpIncreaseDetails(Characters character){
        System.out.println("*************************** HP Increase Details ******************************");
        int staticHpIncrease = getHpGradient(getCharacterNameIndex(character.getCharacterName()), getLevelUpBracketIndex((character.getLevel() + 1)));
        double[] hpIncreaseRates = calculateHpLevelUpRates("Cloud", (character.getLevel() + 1), character.getBaseHp());
        int[] hpIncreaseResults = calculateHpLevelUpResults("Cloud", (character.getLevel() + 1), character.getBaseHp());


        System.out.println(character.getCharacterName() + "'s Static HP Increase = " + staticHpIncrease);
        String[] hpLevelUpRandomNumMsg = {"HP Level Up Random One:","HP Level Up Random Two:","HP Level Up Random Three:","HP Level Up Random Four:","HP Level Up Random Five:","HP Level Up Random Six:","HP Level Up Random Seven:","HP Level Up Random Eight:"};
        for (int i = 0; i < hpIncreaseResults.length; i++){
            System.out.println(hpLevelUpRandomNumMsg[i]);
            System.out.println("    HP Increase Rate = " + hpIncreaseRates[i] + "0%");
            System.out.println("    HP Increase Total = " + hpIncreaseResults[i] + " (" + character.calculateBaseHpNumWithGear(hpIncreaseResults[i]) + ")");
        }
        System.out.println();
    }

    public static void printMpIncreaseDetails(Characters character){
        System.out.println("*************************** MP Increase Details ******************************");
        int staticMpIncrease = getMpBaseGain(character.getLevel() + 1, getMpGradient(getCharacterNameIndex(character.getCharacterName()), getLevelUpBracketIndex((character.getLevel() + 1)))) ;
        double[] mpIncreaseRates = calculateMpLevelUpRates("Cloud", (character.getLevel() + 1), character.getBaseMp());
        int[] mpIncreaseResults = calculateMpLevelUpResults("Cloud", (character.getLevel() + 1), character.getBaseMp());


        System.out.println(character.getCharacterName() + "'s Static HP Increase = " + staticMpIncrease);
        String[] mpLevelUpRandomNumMsg = {"MP Level Up Random One:","MP Level Up Random Two:","MP Level Up Random Three:","MP Level Up Random Four:","MP Level Up Random Five:","MP Level Up Random Six:","MP Level Up Random Seven:","MP Level Up Random Eight:"};
        for (int i = 0; i < mpIncreaseResults.length; i++){
            System.out.println(mpLevelUpRandomNumMsg[i]);
            System.out.println("    MP Increase Rate = " + mpIncreaseRates[i] + "0%");
            System.out.println("    MP Increase Total = " + mpIncreaseResults[i] + " (" + character.calculateBaseMpNumWithGear(mpIncreaseResults[i]) + ")");
        }
        System.out.println();
    }

    public static void printStrIncreaseDetails(Characters character) {
        System.out.println("*************************** Str Increase Details ******************************");
        int[] staticStrIncrease = getPrimaryStatIncreaseAmounts(character.getCharacterName(), (character.getLevel() + 1), "Str", character.getBaseStrength());
        int[] strIncreaseResults = calculatePrimaryStatLevelUpResults(character.getCharacterName(), (character.getLevel() + 1), "Str", character.getBaseStrength());

        if (staticStrIncrease[0] != staticStrIncrease[staticStrIncrease.length - 1]){
            System.out.println(character.getCharacterName() + "'s Static Str Increase = " + staticStrIncrease[0] + " - " + staticStrIncrease[staticStrIncrease.length - 1]);
        } else {
            System.out.println(character.getCharacterName() + "'s Static Str Increase = " + staticStrIncrease[0]);
        }
        String[] strLevelUpRandomNumMsg = {"Str Increase Total Random One = ","Str Increase Total Random Two = ","Str Increase Total Random Three = ","Str Increase Total Random Four = ","Str Increase Total Random Five = ","Str Increase Total Random Six = ","Str Increase Total Random Seven = ","Str Increase Total Random Eight = "};
        for (int i = 0; i < strIncreaseResults.length; i++){
            System.out.println(strLevelUpRandomNumMsg[i] + strIncreaseResults[i] + " (" + character.calculateBaseStrNumWithGear(strIncreaseResults[i]) + ")");
        }
        System.out.println();
    }

    public static void printDexIncreaseDetails(Characters character) {
        System.out.println("*************************** Dex Increase Details ******************************");
        int[] staticDexIncrease = getPrimaryStatIncreaseAmounts(character.getCharacterName(), (character.getLevel() + 1), "Dex", character.getBaseDexterity());
        int[] dexIncreaseResults = calculatePrimaryStatLevelUpResults(character.getCharacterName(), (character.getLevel() + 1), "Dex", character.getBaseDexterity());

        if (staticDexIncrease[0] != staticDexIncrease[staticDexIncrease.length - 1]){
            System.out.println(character.getCharacterName() + "'s Static Dex Increase = " + staticDexIncrease[0] + " - " + staticDexIncrease[staticDexIncrease.length - 1]);
        } else {
            System.out.println(character.getCharacterName() + "'s Static Dex Increase = " + staticDexIncrease[0]);
        }
        String[] dexLevelUpRandomNumMsg = {"Dex Increase Total Random One = ","Dex Increase Total Random Two = ","Dex Increase Total Random Three = ","Dex Increase Total Random Four = ","Dex Increase Total Random Five = ","Dex Increase Total Random Six = ","Dex Increase Total Random Seven = ","Dex Increase Total Random Eight = "};
        for (int i = 0; i < dexIncreaseResults.length; i++){
            System.out.println(dexLevelUpRandomNumMsg[i] + dexIncreaseResults[i] + " (" + character.calculateBaseDexNumWithGear(dexIncreaseResults[i]) + ")");
        }
        System.out.println();
    }

    public static void printVitIncreaseDetails(Characters character) {
        System.out.println("*************************** Vit Increase Details ******************************");
        int[] staticVitIncrease = getPrimaryStatIncreaseAmounts(character.getCharacterName(), (character.getLevel() + 1), "Vit", character.getBaseVitality());
        int[] vitIncreaseResults = calculatePrimaryStatLevelUpResults(character.getCharacterName(), (character.getLevel() + 1), "Vit", character.getBaseVitality());

        if (staticVitIncrease[0] != staticVitIncrease[staticVitIncrease.length - 1]){
            System.out.println(character.getCharacterName() + "'s Static Vit Increase = " + staticVitIncrease[0] + " - " + staticVitIncrease[staticVitIncrease.length - 1]);
        } else {
            System.out.println(character.getCharacterName() + "'s Static Vit Increase = " + staticVitIncrease[0]);
        }
        String[] vitLevelUpRandomNumMsg = {"Vit Increase Total Random One = ","Vit Increase Total Random Two = ","Vit Increase Total Random Three = ","Vit Increase Total Random Four = ","Vit Increase Total Random Five = ","Vit Increase Total Random Six = ","Vit Increase Total Random Seven = ","Vit Increase Total Random Eight = "};
        for (int i = 0; i < vitIncreaseResults.length; i++){
            System.out.println(vitLevelUpRandomNumMsg[i] + vitIncreaseResults[i] + " (" + character.calculateBaseVitNumWithGear(vitIncreaseResults[i]) + ")");
        }
        System.out.println();
    }

    public static void printMagIncreaseDetails(Characters character) {
        System.out.println("*************************** Mag Increase Details ******************************");
        int[] staticMagIncrease = getPrimaryStatIncreaseAmounts(character.getCharacterName(), (character.getLevel() + 1), "Mag", character.getBaseMagic());
        int[] magIncreaseResults = calculatePrimaryStatLevelUpResults(character.getCharacterName(), (character.getLevel() + 1), "Mag", character.getBaseMagic());

        if (staticMagIncrease[0] != staticMagIncrease[staticMagIncrease.length - 1]){
            System.out.println(character.getCharacterName() + "'s Static Mag Increase = " + staticMagIncrease[0] + " - " + staticMagIncrease[staticMagIncrease.length - 1]);
        } else {
            System.out.println(character.getCharacterName() + "'s Static Mag Increase = " + staticMagIncrease[0]);
        }
        String[] magLevelUpRandomNumMsg = {"Mag Increase Total Random One = ","Mag Increase Total Random Two = ","Mag Increase Total Random Three = ","Mag Increase Total Random Four = ","Mag Increase Total Random Five = ","Mag Increase Total Random Six = ","Mag Increase Total Random Seven = ","Mag Increase Total Random Eight = "};
        for (int i = 0; i < magIncreaseResults.length; i++){
            System.out.println(magLevelUpRandomNumMsg[i] + magIncreaseResults[i] + " (" + character.calculateBaseMagNumWithGear(magIncreaseResults[i]) + ")");
        }
        System.out.println();
    }

    public static void printSprIncreaseDetails(Characters character) {
        System.out.println("*************************** Spr Increase Details ******************************");
        int[] staticSprIncrease = getPrimaryStatIncreaseAmounts(character.getCharacterName(), (character.getLevel() + 1), "Spr", character.getBaseSpirit());
        int[] sprIncreaseResults = calculatePrimaryStatLevelUpResults(character.getCharacterName(), (character.getLevel() + 1), "Spr", character.getBaseSpirit());

        if (staticSprIncrease[0] != staticSprIncrease[staticSprIncrease.length - 1]){
            System.out.println(character.getCharacterName() + "'s Static Spr Increase = " + staticSprIncrease[0] + " - " + staticSprIncrease[staticSprIncrease.length - 1]);
        } else {
            System.out.println(character.getCharacterName() + "'s Static Spr Increase = " + staticSprIncrease[0]);
        }
        String[] sprLevelUpRandomNumMsg = {"Spr Increase Total Random One = ","Spr Increase Total Random Two = ","Spr Increase Total Random Three = ","Spr Increase Total Random Four = ","Spr Increase Total Random Five = ","Spr Increase Total Random Six = ","Spr Increase Total Random Seven = ","Spr Increase Total Random Eight = "};
        for (int i = 0; i < sprIncreaseResults.length; i++){
            System.out.println(sprLevelUpRandomNumMsg[i] + sprIncreaseResults[i] + " (" + character.calculateBaseSprNumWithGear(sprIncreaseResults[i]) + ")");
        }
        System.out.println();
    }

    public static void printLckIncreaseDetails(Characters character) {
        System.out.println("*************************** Lck Increase Details ******************************");
        int[] staticLckIncrease = getLuckStatIncreaseAmounts(character.getCharacterName(), (character.getLevel() + 1), character.getBaseLuck());
        int[] lckIncreaseResults = calculateLuckStatLevelUpResults(character.getCharacterName(), (character.getLevel() + 1), character.getBaseLuck());

        if (staticLckIncrease[0] != staticLckIncrease[staticLckIncrease.length - 1]){
            System.out.println(character.getCharacterName() + "'s Static Lck Increase = " + staticLckIncrease[0] + " - " + staticLckIncrease[staticLckIncrease.length - 1]);
        } else {
            System.out.println(character.getCharacterName() + "'s Static Lck Increase = " + staticLckIncrease[0]);
        }
        String[] lckLevelUpRandomNumMsg = {"Lck Increase Total Random One = ","Lck Increase Total Random Two = ","Lck Increase Total Random Three = ","Lck Increase Total Random Four = ","Lck Increase Total Random Five = ","Lck Increase Total Random Six = ","Lck Increase Total Random Seven = ","Lck Increase Total Random Eight = "};
        for (int i = 0; i < lckIncreaseResults.length; i++){
            System.out.println(lckLevelUpRandomNumMsg[i] + lckIncreaseResults[i] + " (" + character.calculateBaseLckNumWithGear(lckIncreaseResults[i]) + ")");
        }
        System.out.println();
    }

    //**************************************************************************************************
    //********************************************** Main **********************************************
    //**************************************************************************************************
    public static void main(String[] args) {
        Characters cloud = new Characters("Cloud");
        /* Change character stuff here, eg:
         *
         * cloud.setLevel(98);
         * cloud.equipWeapon(getListOfCharacterWeapons()[5]) // "Rune Blade"
         * cloud.setBaseStrength(99);
         * ect...
         */

        System.out.println(cloud.getCharacterName() + "'s Next Level = " + (cloud.getLevel() + 1));
        System.out.println(cloud.getCharacterName() + "'s Current Base HP (With Gear) = " + cloud.getBaseHp() + " (" + cloud.getHp() + ")");
        System.out.println(cloud.getCharacterName() + "'s Current Base MP (With Gear) = " + cloud.getBaseMp() + " (" + cloud.getMp() + ")");
        System.out.println(cloud.getCharacterName() + "'s Current Base Str (With Gear) = " + cloud.getBaseStrength() + " (" + cloud.getStrength() + ")");
        System.out.println(cloud.getCharacterName() + "'s Current Base Dex (With Gear) = " + cloud.getBaseDexterity() + " (" + cloud.getDexterity() + ")");
        System.out.println(cloud.getCharacterName() + "'s Current Base Vit (With Gear) = " + cloud.getBaseVitality() + " (" + cloud.getVitality() + ")");
        System.out.println(cloud.getCharacterName() + "'s Current Base Mag (With Gear) = " + cloud.getBaseMagic() + " (" + cloud.getMagic() + ")");
        System.out.println(cloud.getCharacterName() + "'s Current Base Spr (With Gear) = " + cloud.getBaseSpirit() + " (" + cloud.getSpirit() + ")");
        System.out.println(cloud.getCharacterName() + "'s Current Base Lck (With Gear) = " + cloud.getBaseLuck() + " (" + cloud.getLuck() + ")");
        System.out.println();

        printHpIncreaseDetails(cloud);
        printMpIncreaseDetails(cloud);
        printStrIncreaseDetails(cloud);
        printDexIncreaseDetails(cloud);
        printVitIncreaseDetails(cloud);
        printMagIncreaseDetails(cloud);
        printSprIncreaseDetails(cloud);
        printLckIncreaseDetails(cloud);
    }
}

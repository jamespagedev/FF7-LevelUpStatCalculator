/**
 * Created by James Page on 2/24/2016.
 */
package ClassFiles;

import org.apache.commons.lang3.ArrayUtils;
import java.util.Arrays;

public abstract class Calculator
{
    //******************************************************************************************************************
    //**************************************************** Variables ***************************************************
    //******************************************************************************************************************
    public static final String[] characterNames = Characters.characterNames;
    public static final int[] rndNums = {1, 2, 3, 4, 5, 6, 7, 8};
    public static final int maxRndNum = rndNums[rndNums.length -1];
    public static final int minRndNum = rndNums[0];
    public static final int maxHpValue = 9999;
    public static final int maxMpValue = 999;
    public static final int maxPrimaryAndLuckStatsValue = 100; // your primary and luck stats can not increase beyond 100 naturally through levels (you can use source items to get 255 max)
    public static final String[] primaryStatName = {"Str", "Dex", "Vit", "Mag", "Spr"}; // Luck is excluded, because it has it's own stat variable tables

    public static final int[][] levelUpBrackets = // [levelUpBracket][nextLevel]
    {
        {2, 3, 4, 5, 6, 7, 8, 9, 10, 11},
        {12, 13, 14, 15, 16, 17, 18, 19, 20, 21},
        {22, 23, 24, 25, 26, 27, 28, 29, 30, 31},
        {32, 33, 34, 35, 36, 37, 38, 39, 40, 41},
        {42, 43, 44, 45, 46, 47, 48, 49, 50, 51},
        {52, 53, 54, 55, 56, 57, 58, 59, 60, 61},
        {62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81},
        {82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95, 96, 97, 98, 99}
    };

    //******************************************************************************************************************
    //*********************************************** HP Table Variables ***********************************************
    //******************************************************************************************************************
    public static final int[][] hpBaseTable =
            { // Level Up Bracket {2-11, 12-21, 22-31, 32-41, 42-51, 52-61, 62-81, 82-99}
                    {200, -40, -640, -1440, -2280, -3080, -2040, -200}, // Cloud
                    {200, 0, -760, -1840, -2840, -2840, -1160, 600}, // Barret
                    {200, 0, -520, -1520, -2520, -3000, -2160, -80}, // Tifa
                    {160, 0, -560, -1400, -2240, -2880, -2080, -400}, // Aeris
                    {200, -40, -640, -1520, -2360, -2760, -1840, -80}, // Red XIII
                    {200, 0, -560, -1320, -2160, -2960, -2560, -520}, // Yuffie
                    {200, -80, -640, -1560, -2760, -2600, -240, 2000}, // Cait Sith
                    {160, -80, -600, -1160, -2120, -2800, -2640, -400}, // Vincent
                    {200, -40, -640, -1640, -2360, -2560, -1720, 0} // Cid
            };

    public static final int[][] hpGradientTable =
            { // Level Up Bracket {2-11, 12-21, 22-31, 32-41, 42-51, 52-61, 62-81, 82-99}
                    {19, 42, 72, 100, 121, 137, 120, 98}, // Cloud
                    {22, 45, 82, 118, 143, 143, 115, 95}, // Barret
                    {19, 38, 64, 96, 121, 131, 117, 92}, // Tifa
                    {17, 36, 65, 93, 114, 126, 113, 93}, // Aeris
                    {21, 45, 75, 105, 126, 134, 119, 97}, // Red XIII
                    {18, 37, 64, 89, 111, 127, 120, 96}, // Yuffie
                    {24, 51, 80, 111, 141, 138, 99, 72}, // Cait Sith
                    {18, 41, 67, 86, 110, 123, 120, 92}, // Vincent
                    {23, 44, 73, 107, 125, 129, 115, 93} // Cid
            };

    //******************************************************************************************************************
    //*********************************************** MP Table Variables ***********************************************
    //******************************************************************************************************************
    public static final int[][] mpGradientTable =
            { // Level Up Bracket {2-11, 12-21, 22-31, 32-41, 42-51, 52-61, 62-81, 82-99}
                    {64, 78, 90, 101, 112, 112, 96, 73}, // Cloud
                    {57, 67, 77, 90, 102, 100, 84, 63}, // Barret
                    {60, 70, 84, 94, 104, 104, 92, 72}, // Tifa
                    {70, 84, 99, 112, 124, 120, 105, 82}, // Aeris
                    {58, 75, 86, 97, 108, 112, 94, 66}, // Red XIII
                    {58, 72, 80, 93, 106, 110, 85, 72}, // Yuffie
                    {60, 75, 83, 97, 108, 108, 94, 70}, // Cait Sith
                    {63, 80, 90, 96, 100, 105, 97, 84}, // Vincent
                    {54, 75, 83, 87, 94, 104, 89, 69} // Cid
            };
    public static final int[][] mpBaseTable =
            { // Level Up Bracket {2-11, 12-21, 22-31, 32-41, 42-51, 52-61, 62-81, 82-99}
                    {12, 0, -26, -58, -102, -102, -4, 180}, // Cloud
                    {10, 0, -20, -60, -108, -96, 0, 170}, // Barret
                    {10, 0, -28, -58, -98, -98, -26, 136}, // Tifa
                    {16, 0, -30, -68, -116, -96, -6, 188}, // Aeris
                    {12, -6, -28, -60, -104, -126, -16, 210}, // Red XIII
                    {10, -2, -20, -58, -110, -130, 20, 126}, // Yuffie
                    {12, -2, -20, -60, -104, -104, -20, 178}, // Cait Sith
                    {12, -6, -26, -44, -60, -86, 38, 74}, // Vincent
                    {10, -12, -26, -38, -66, -116, -24, 140} // Cid
            };

    //******************************************************************************************************************
    //****************************************** Primary Stat Table Variables ******************************************
    //******************************************************************************************************************
    public static int[][] primaryStatRankCurveTable = { // int[characterName][primaryStat]
            // Primary Stat {Str, Dex, Vit, Mag, Spr}
            {1, 26, 6, 3, 4}, // Cloud
            {5, 29, 2, 18, 14}, // Barret
            {6, 25, 18, 16, 9}, // Tifa
            {23, 28, 20, 0, 1}, // Aeris
            {12, 23, 11, 13, 9}, // Red XIII
            {16, 24, 19, 11, 10}, // Yuffie
            {19, 28, 22, 6, 4}, // Cait Sith
            {21, 28, 22, 6, 4}, // Vincent
            {11, 27, 7, 17, 15} // Cid
    };

    public static int[][] primaryStatBaseNumberTable = { // int[Character Rank Curve Number][Level Up Bracket Number]
            //  Level Up Bracket {2-11, 12-21, 22-31, 32-41, 42-51, 52-61, 62-81, 82-99}
            {12, 9, 9, 21, 44, 50, 57, 73}, // Character Rank Curve 0
            {13, 12, 11, 11, 17, 43, 53, 80}, // Character Rank Curve 1
            {12, 10, 11, 21, 32, 43, 56, 73}, // Character Rank Curve 2
            {12, 13, 11, 15, 33, 40, 51, 69}, // Character Rank Curve 3
            {10, 9, 8, 8, 30, 33, 40, 61}, // Character Rank Curve 4
            {12, 12, 14, 14, 23, 49, 55, 62}, // Character Rank Curve 5
            {10, 8, 5, 17, 17, 30, 50, 61}, // Character Rank Curve 6
            {11, 10, 11, 16, 27, 33, 37, 58}, // Character Rank Curve 7
            {12, 9, 10, 11, 29, 34, 49, 58}, // Character Rank Curve 8
            {9, 8, 8, 8, 26, 29, 42, 48}, // Character Rank Curve 9
            {9, 9, 7, 8, 11, 26, 48, 53}, // Character Rank Curve 10
            {11, 10, 12, 17, 21, 49, 48, 48}, // Character Rank Curve 11
            {9, 9, 6, 8, 29, 51, 57, 62}, // Character Rank Curve 12
            {10, 9, 7, 16, 22, 43, 45, 54}, // Character Rank Curve 13
            {8, 9, 11, 13, 16, 18, 40, 60}, // Character Rank Curve 14
            {9, 9, 11, 15, 23, 32, 48, 62}, // Character Rank Curve 15
            {10, 9, 10, 16, 22, 28, 49, 55}, // Character Rank Curve 16
            {10, 10, 10, 13, 21, 39, 45, 57}, // Character Rank Curve 17
            {10, 10, 9, 11, 21, 35, 51, 57}, // Character Rank Curve 18
            {9, 8, 9, 17, 32, 37, 42, 47}, // Character Rank Curve 19
            {10, 9, 8, 11, 29, 39, 47, 53}, // Character Rank Curve 20
            {8, 7, 4, 20, 31, 36, 37, 46}, // Character Rank Curve 21
            {9, 9, 10, 15, 21, 28, 35, 53}, // Character Rank Curve 22
            {9, 9, 8, 14, 18, 25, 44, 50}, // Character Rank Curve 23
            {8, 9, 12, 13, 22, 29, 38, 55}, // Character Rank Curve 24
            {7, 7, 1, 8, 13, 20, 42, 46}, // Character Rank Curve 25
            {6, 7, 6, 6, 10, 19, 36, 37}, // Character Rank Curve 26
            {6, 9, 8, 6, 7, 13, 31, 37}, // Character Rank Curve 27
            {5, 6, 7, 7, 9, 18, 38, 38}, // Character Rank Curve 28
            {5, 6, 4, 9, 14, 20, 24, 30} // Character Rank Curve 29
    };

    public static int[][] primaryStatGradientNumberTable = { // int[Character Rank Curve Number][Level Up Bracket Number]
            //  Level Up Bracket {2-11, 12-21, 22-31, 32-41, 42-51, 52-61, 62-81, 82-99}
            {130, 160, 160, 120, 70, 60, 50, 30}, // Character Rank Curve 0
            {120, 130, 133, 135, 120, 72, 55, 21}, // Character Rank Curve 1
            {130, 140, 140, 110, 90, 70, 48, 27}, // Character Rank Curve 2
            {130, 120, 135, 120, 85, 70, 53, 32}, // Character Rank Curve 3
            {120, 128, 130, 130, 77, 72, 61, 35}, // Character Rank Curve 4
            {120, 125, 117, 118, 93, 52, 44, 35}, // Character Rank Curve 5
            {110, 130, 145, 110, 100, 75, 44, 31}, // Character Rank Curve 6
            {120, 135, 130, 110, 85, 70, 60, 35}, // Character Rank Curve 7
            {100, 130, 125, 120, 77, 67, 43, 31}, // Character Rank Curve 8
            {110, 120, 122, 123, 80, 75, 55, 44}, // Character Rank Curve 9
            {100, 115, 124, 118, 107, 78, 42, 36}, // Character Rank Curve 10
            {110, 120, 115, 102, 91, 37, 40, 40}, // Character Rank Curve 11
            {100, 122, 140, 135, 83, 40, 30, 25}, // Character Rank Curve 12
            {110, 122, 130, 98, 83, 45, 44, 33}, // Character Rank Curve 13
            {110, 105, 104, 102, 93, 87, 51, 25}, // Character Rank Curve 14
            {115, 127, 121, 108, 86, 68, 41, 24}, // Character Rank Curve 15
            {114, 118, 114, 95, 82, 71, 37, 30}, // Character Rank Curve 16
            {112, 115, 111, 103, 83, 48, 39, 25}, // Character Rank Curve 17
            {100, 108, 115, 108, 83, 55, 31, 24}, // Character Rank Curve 18
            {100, 111, 112, 87, 53, 45, 39, 34}, // Character Rank Curve 19
            {100, 108, 114, 106, 63, 45, 33, 26}, // Character Rank Curve 20
            {100, 110, 127, 77, 50, 41, 40, 31}, // Character Rank Curve 21
            {100, 102, 101, 88, 70, 57, 45, 24}, // Character Rank Curve 22
            {100, 100, 107, 85, 77, 60, 30, 24}, // Character Rank Curve 23
            {95, 90, 88, 85, 62, 52, 39, 18}, // Character Rank Curve 24
            {80, 85, 115, 92, 78, 64, 27, 21}, // Character Rank Curve 25
            {72, 69, 76, 77, 68, 50, 22, 21}, // Character Rank Curve 26
            {70, 53, 63, 70, 69, 58, 28, 20}, // Character Rank Curve 27
            {70, 70, 70, 71, 67, 48, 16, 16}, // Character Rank Curve 28
            {65, 63, 76, 61, 49, 36, 28, 20} // Character Rank Curve 29
    };

    //******************************************************************************************************************
    //********************************************** Luck Table Variables **********************************************
    //******************************************************************************************************************
    public static int[][] luckStatBaseNumberTable = { // int[Character Name][Level Up Bracket Number]
            // Level Up Bracket {2-11, 12-21, 22-31, 32-41, 42-51, 52-61, 62-81, 82-99}
            {15, 16, 16, 17, 17, 17, 18, 19}, // Cloud
            {14, 15, 15, 15, 16, 17, 18, 20}, // Barret
            {14, 15, 15, 16, 17, 17, 17, 20}, // Tifa
            {14, 15, 15, 16, 16, 18, 17, 17}, // Aeris
            {14, 15, 15, 16, 16, 18, 17, 17}, // Red XIII
            {16, 17, 17, 17, 18, 18, 18, 19}, // Yuffie
            {14, 15, 15, 16, 17, 17, 17, 20}, // Cait Sith
            {14, 15, 15, 16, 17, 17, 17, 20}, // Vincent
            {14, 15, 15, 15, 16, 17, 18, 20} // Cid
    };

    public static int[][] luckStatGradientNumberTable = { // int[Character Name][Level Up Bracket Number]
            //  Level Up Bracket {2-11, 12-21, 22-31, 32-41, 42-51, 52-61, 62-81, 82-99}
            {20, 10, 10, 8, 8, 8, 7, 6}, // Cloud
            {15, 8, 8, 7, 6, 5, 4, 3}, // Barret
            {20, 10, 10, 9, 9, 10, 10, 6}, // Tifa
            {18, 8, 8, 8, 8, 5, 7, 7}, // Aeris
            {18, 8, 8, 8, 8, 5, 7, 7}, // Red XIII
            {20, 10, 10, 10, 8, 10, 10, 9}, // Yuffie
            {20, 10, 10, 9, 9, 10, 10, 6}, // Cait Sith
            {20, 10, 10, 9, 9, 10, 10, 6}, // Vincent
            {15, 8, 8, 7, 6, 5, 4, 3} // Cid
    };
    //******************************************************************************************************************
    //************************************************ Variables Methods ***********************************************
    //******************************************************************************************************************
    public static int getCharacterNameIndex(String characterName){
        if (Arrays.asList(characterNames).contains(characterName)) {
            for (int characterNameIndex = 0; characterNameIndex < characterNames.length - 1; characterNameIndex++) {
                if (characterName.toUpperCase().equals(characterNames[characterNameIndex].toUpperCase())) {
                    return characterNameIndex;
                }
            }
        }
        return 0; // Return default cloud if name was not found
    }

    public static int getLevelUpBracketIndex(int nextLevel){
        for (int levelBracketIndex = 0; levelBracketIndex < levelUpBrackets.length - 1; levelBracketIndex++){
            if (ArrayUtils.contains(levelUpBrackets[levelBracketIndex], nextLevel)){
                return levelBracketIndex;
            }
        }
        return levelUpBrackets.length - 1;
    }

    //******************************************************************************************************************
    //***************************************** Calculate Next Level HP Methods ****************************************
    //******************************************************************************************************************
    public static int getHpBase(int characterNameIndex, int levelBracketIndex){
        return hpBaseTable[characterNameIndex][levelBracketIndex];
    }

    public static int getHpGradient(int characterNameIndex, int levelBracketIndex){
        return hpGradientTable[characterNameIndex][levelBracketIndex];
    }

    public static int getHpBaseline(int nextLevel, int hpBase, int hpGradient){
        return hpBase + ((nextLevel - 1) * hpGradient);
    }

    public static int getHpDifference(int rndNumber, int hpBaseline, int hpValue) // returns 0 through 11
    {
        final int minHpDifference = 0;
        final int maxHpDifference = 11;

        int hpDifference = rndNumber + ((int)( 100 * (hpBaseline / (double)hpValue)) - 100);
        if (hpDifference < minHpDifference)
        {
            return minHpDifference;
        } else if (hpDifference > maxHpDifference) {
            return maxHpDifference;
        }
        return hpDifference;
    }

    public static double getHpIncreaseRate(int hpDifference){
        // Precondition - hpDifference is between 0 - 11
        double[]  hpIncreaseRate = {0.40, 0.50, 0.50, 0.60, 0.70, 0.80, 0.90, 1.00, 1.10, 1.20, 1.30, 1.50};
        return hpIncreaseRate[hpDifference];
    }

    public static int getHpIncreaseAmount(int hpGradient, double hpIncreaseRate){
        return (int) (hpGradient * hpIncreaseRate);
    }

    public static int getHpIncreaseResult(int hpValue, int hpIncreaseAmount){
        if (hpValue + hpIncreaseAmount > maxHpValue){
            return maxHpValue;
        }
        return hpValue + hpIncreaseAmount;
    }

    public static double[] calculateHpLevelUpRates(String characterName, int nextLevel, int hpValue){
        // (int[]) eightHpLevelUpResults -> function returns this variable -> variable holds the eight possible values hp will increase to after level up (capped at 9999)
        // (String) characterName -> method arguments()
        // (int) nextLevel -> method arguments()
        // (int) hpValue -> method arguments()
        // (int) characterNameIndex -> get from getCharacterNameIndex(characterName)
        // (int) levelBracketIndex -> get from getLevelUpBracketIndex(nextLevel)
        // (int) hpBase -> get from hpBaseTable[characterNameIndex][levelBracketIndex] class variable
        // (int) hpGradient -> get from hpGradientTable[characterNameIndex][levelBracketIndex] class variable
        // (int) hpBaseline -> hpBase + ((nextLevel - 1) * hpGradient)
        // (int) Rnd(1..8) -> use for loop with rndNum[]
            // (int) hpDifference -> rndNumber + ((int)( 100 * (hpBaseline / (double)hpValue)) - 100) -> capped range (0 through 11)
            // (double) hpIncreaseRate ->  get from getHpIncreaseRate(hpDifference)  -> capped range (0.40 through 1.50)

        double[] hpLevelUpRates = new double[8];
        int hpLevelUpRatesIndex = 0;
        int characterNameIndex = getCharacterNameIndex(characterName);
        int levelBracketIndex = getLevelUpBracketIndex(nextLevel);
        int hpBase = getHpBase(characterNameIndex, levelBracketIndex);
        int hpGradient = getHpGradient(characterNameIndex, levelBracketIndex);
        int hpBaseline = getHpBaseline(nextLevel, hpBase, hpGradient);
        int hpDifference;

        for (int rndNumber : rndNums) { // 1 through 8
            hpDifference = getHpDifference(rndNumber, hpBaseline, hpValue);
            hpLevelUpRates[hpLevelUpRatesIndex] = getHpIncreaseRate(hpDifference);
            hpLevelUpRatesIndex++;
        }
        return hpLevelUpRates;
    }

    public static int calculateHpLevelUpResult(String characterName, int nextLevel, int hpValue, int rndNumber){
        // (int) hpLevelUpResult -> function returns this variable -> variable holds the value hp will increase to after level up (capped at 9999)
        // (String) characterName -> method arguments()
        // (int) nextLevel -> method arguments()
        // (int) hpValue -> method arguments()
        // (int) characterNameIndex -> get from getCharacterNameIndex(characterName)
        // (int) levelBracketIndex -> get from getLevelUpBracketIndex(nextLevel)
        // (int) hpBase -> get from hpBaseTable[characterNameIndex][levelBracketIndex] class variable
        // (int) hpGradient -> get from hpGradientTable[characterNameIndex][levelBracketIndex] class variable
        // (int) hpBaseline -> hpBase + ((nextLevel - 1) * hpGradient)
        // (int) Rnd(1..8) -> use for loop with rndNum[]
        // (int) hpDifference -> rndNumber + ((int)( 100 * (hpBaseline / (double)hpValue)) - 100) -> capped range (0 through 11)
        // (double) hpIncreaseRate ->  get from getHpIncreaseRate(hpDifference)  -> capped range (0.40 through 1.50)
        // (int) hpIncreaseAmount -> (int) (hpGradient * hpIncreaseRate)
        // (int) hpIncreaseResult -> hpValue + hpIncreaseAmount

        int hpLevelUpResult;
        int characterNameIndex = getCharacterNameIndex(characterName);
        int levelBracketIndex = getLevelUpBracketIndex(nextLevel);
        int hpBase = getHpBase(characterNameIndex, levelBracketIndex);
        int hpGradient = getHpGradient(characterNameIndex, levelBracketIndex);
        int hpBaseline = getHpBaseline(nextLevel, hpBase, hpGradient);
        int hpDifference;
        double hpIncreaseRate;
        int hpIncreaseAmount;
        hpDifference = getHpDifference(rndNumber, hpBaseline, hpValue);
        hpIncreaseRate = getHpIncreaseRate(hpDifference);
        hpIncreaseAmount = getHpIncreaseAmount(hpGradient, hpIncreaseRate);
        hpLevelUpResult = getHpIncreaseResult(hpValue, hpIncreaseAmount);

        return hpLevelUpResult;
    }

    public static int[] calculateHpLevelUpResults(String characterName, int nextLevel, int hpValue){
        // (int[]) hpLevelUpResults -> function returns this variable -> variable holds the eight possible values hp will increase to after level up (capped at 9999)
        // (String) characterName -> method arguments()
        // (int) nextLevel -> method arguments()
        // (int) hpValue -> method arguments()
        // (int) characterNameIndex -> get from getCharacterNameIndex(characterName)
        // (int) levelBracketIndex -> get from getLevelUpBracketIndex(nextLevel)
        // (int) hpBase -> get from hpBaseTable[characterNameIndex][levelBracketIndex] class variable
        // (int) hpGradient -> get from hpGradientTable[characterNameIndex][levelBracketIndex] class variable
        // (int) hpBaseline -> hpBase + ((nextLevel - 1) * hpGradient)
        // (int) Rnd(1..8) -> use for loop with rndNum[]
            // (int) hpDifference -> rndNumber + ((int)( 100 * (hpBaseline / (double)hpValue)) - 100) -> capped range (0 through 11)
            // (double) hpIncreaseRate ->  get from getHpIncreaseRate(hpDifference)  -> capped range (0.40 through 1.50)
            // (int) hpIncreaseAmount -> (int) (hpGradient * hpIncreaseRate)
            // (int) hpIncreaseResult -> hpValue + hpIncreaseAmount

        int[] hpLevelUpResults = new int[8];
        int hpLevelUpResultsIndex = 0;
        int characterNameIndex = getCharacterNameIndex(characterName);
        int levelBracketIndex = getLevelUpBracketIndex(nextLevel);
        int hpBase = getHpBase(characterNameIndex, levelBracketIndex);
        int hpGradient = getHpGradient(characterNameIndex, levelBracketIndex);
        int hpBaseline = getHpBaseline(nextLevel, hpBase, hpGradient);
        int hpDifference;
        double hpIncreaseRate;
        int hpIncreaseAmount;

        for (int rndNumber : rndNums){ // 1 through 8
            hpDifference = getHpDifference(rndNumber, hpBaseline, hpValue);
            hpIncreaseRate = getHpIncreaseRate(hpDifference);
            hpIncreaseAmount = getHpIncreaseAmount(hpGradient, hpIncreaseRate);
            hpLevelUpResults[hpLevelUpResultsIndex] = getHpIncreaseResult(hpValue, hpIncreaseAmount);
            hpLevelUpResultsIndex++;
        }

        return hpLevelUpResults;
    }

    //******************************************************************************************************************
    //***************************************** Calculate Next Level MP Methods ****************************************
    //******************************************************************************************************************
    public static int getMpBase(int characterNameIndex, int levelBracketIndex){
        return mpBaseTable[characterNameIndex][levelBracketIndex];
    }

    public static int getMpGradient(int characterNameIndex, int levelBracketIndex){
        return mpGradientTable[characterNameIndex][levelBracketIndex];
    }

    public static int getMpBaseline(int nextLevel, int mpBase, int mpGradient){
        return mpBase + (((nextLevel - 1) * mpGradient) / 10);
    }

    public static int getMpBaseGain(int nextLevel, int mpGradient){
        return ((nextLevel * mpGradient) / 10) - (((nextLevel - 1) * mpGradient) / 10);
    }

    private static int getMpDifference(int rndNumber, int mpBaseline, int mpValue) // returns 1 through 11
    {
        int minMpDifference = 0;
        int maxMpDifference = 11;

        int mpDifference = rndNumber + ((int)( 100 * (mpBaseline / (double)mpValue)) - 100);
        if (mpDifference < minMpDifference)
        {
            return minMpDifference;
        } else if (mpDifference > maxMpDifference) {
            return maxMpDifference;
        }
        return mpDifference;
    }

    public static double getMpIncreaseRate(int mpDifference){
        switch (mpDifference)
        {
            case 0:
                return 0.20;
            case 1:
                return 0.30;
            case 2:
                return 0.30;
            case 3:
                return 0.50;
            case 4:
                return 0.70;
            case 5:
                return 0.80;
            case 6:
                return 0.90;
            case 7:
                return 1.00;
            case 8:
                return 1.10;
            case 9:
                return 1.20;
            case 10:
                return 1.40;
            default:
                return 1.60;
        }
    }

    public static int getMpIncreaseAmount(int mpBaseGain, double mpIncreaseRate){
        return (int) ((double)(mpBaseGain * mpIncreaseRate));
    }

    public static int getMpIncreaseResult(int mpValue, int mpIncreaseAmount){
        if (mpValue + mpIncreaseAmount > maxMpValue){
            return maxMpValue;
        }
        return mpValue + mpIncreaseAmount;
    }

    public static double[] calculateMpLevelUpRates(String characterName, int nextLevel, int mpValue){
        // (int[]) eightMpLevelUpRates -> function returns this variable -> variable holds the eight possible values mp will increase to after level up (capped at 999)
        // (String) characterName -> method arguments()
        // (int) nextLevel -> method arguments()
        // (int) mpValue -> method arguments()
        // (int) characterNameIndex -> get from getCharacterNameIndex(characterName)
        // (int) levelBracketIndex -> get from getLevelUpBracketIndex(nextLevel)
        // (int) mpBase -> get from mpBaseTable[characterNameIndex][levelBracketIndex] class variable
        // (int) mpGradient -> get from mpGradientTable[characterNameIndex][levelBracketIndex] class variable
        // (int) mpBaseline -> mpBase + (((nextLevel - 1) * mpGradient) / 10)
        // (int) Rnd(1..8) -> use for loop with rndNum[]
            // (int) mpDifference -> rndNumber + ((int)( 100 * (mpBaseline / (double)mpValue)) - 100) -> capped range (0 through 11)
            // (double) mpIncreaseRate ->  get from getMpIncreaseRate(hpDifference)  -> capped range (0.20 through 1.60)

        double[] eightMpLevelUpRates = new double[8];
        int eightMpLevelUpRatesIndex = 0;
        int characterNameIndex = getCharacterNameIndex(characterName);
        int levelBracketIndex = getLevelUpBracketIndex(nextLevel);
        int mpBase = getMpBase(characterNameIndex, levelBracketIndex);
        int mpGradient = getMpGradient(characterNameIndex, levelBracketIndex);
        int mpBaseline = getMpBaseline(nextLevel, mpBase, mpGradient);
        int mpDifference;

        for (int rndNumber : rndNums) { // 1 through 8
            mpDifference = getMpDifference(rndNumber, mpBaseline, mpValue);
            eightMpLevelUpRates[eightMpLevelUpRatesIndex] = getMpIncreaseRate(mpDifference);
            eightMpLevelUpRatesIndex++;
        }
        return eightMpLevelUpRates;
    }

    public static int calculateMpLevelUpResult(String characterName, int nextLevel, int mpValue, int rndNumber){
        // (int) mpLevelUpResult -> function returns this variable -> variable holds the value mp will increase to after level up (capped at 9999)
        // (String) characterName -> method arguments()
        // (int) nextLevel -> method arguments()
        // (int) mpValue -> method arguments()
        // (int) characterNameIndex -> get from getCharacterNameIndex(characterName)
        // (int) levelBracketIndex -> get from getLevelUpBracketIndex(nextLevel)
        // (int) mpBase -> get from mpBaseTable[characterNameIndex][levelBracketIndex] class variable
        // (int) mpGradient -> get from mpGradientTable[characterNameIndex][levelBracketIndex] class variable
        // (int) mpBaseline -> mpBase + ((nextLevel - 1) * mpGradient)
        // (int) Rnd(1..8) -> use for loop with rndNum[]
        // (int) mpDifference -> rndNumber + ((int)( 100 * (mpBaseline / (double)mpValue)) - 100) -> capped range (0 through 11)
        // (double) mpIncreaseRate ->  get from getMpIncreaseRate(mpDifference)  -> capped range (0.40 through 1.50)
        // (int) mpIncreaseAmount -> (int) (mpGradient * mpIncreaseRate)
        // (int) mpIncreaseResult -> mpValue + mpIncreaseAmount

        int mpLevelUpResult;
        int characterNameIndex = getCharacterNameIndex(characterName);
        int levelBracketIndex = getLevelUpBracketIndex(nextLevel);
        int mpBase = getMpBase(characterNameIndex, levelBracketIndex);
        int mpGradient = getMpGradient(characterNameIndex, levelBracketIndex);
        int mpBaseline = getMpBaseline(nextLevel, mpBase, mpGradient);
        int mpBaseGain = getMpBaseGain(nextLevel, mpGradient);
        int mpDifference;
        double mpIncreaseRate;
        int mpIncreaseAmount;
        mpDifference = getMpDifference(rndNumber, mpBaseline, mpValue);
        mpIncreaseRate = getMpIncreaseRate(mpDifference);
        mpIncreaseAmount = getMpIncreaseAmount(mpBaseGain, mpIncreaseRate);
        mpLevelUpResult = getMpIncreaseResult(mpValue, mpIncreaseAmount);

        return mpLevelUpResult;
    }

    public static int[] calculateMpLevelUpResults(String characterName, int nextLevel, int mpValue){
        // (int[]) eightMpLevelUpResults -> function returns this variable -> variable holds the eight possible values mp will increase to after level up (capped at 999)
        // (String) characterName -> method arguments()
        // (int) nextLevel -> method arguments()
        // (int) mpValue -> method arguments()
        // (int) characterNameIndex -> get from getCharacterNameIndex(characterName)
        // (int) levelBracketIndex -> get from getLevelUpBracketIndex(nextLevel)
        // (int) mpBase -> get from mpBaseTable[characterNameIndex][levelBracketIndex] class variable
        // (int) mpGradient -> get from mpGradientTable[characterNameIndex][levelBracketIndex] class variable
        // (int) mpBaseline -> mpBase + (((nextLevel - 1) * mpGradient) / 10)
        // (int) mpBaseGain -> ((nextLevel * mpGradient) / 10) - (((nextLevel - 1) * mpGradient) / 10);
        // (int) Rnd(1..8) -> use for loop with rndNum[]
            // (int) mpDifference -> rndNumber + ((int)( 100 * (mpBaseline / (double)mpValue)) - 100) -> capped range (0 through 11)
            // (double) mpIncreaseRate ->  get from getMpIncreaseRate(hpDifference)  -> capped range (0.20 through 1.60)
            // (int) mpIncreaseAmount -> (int) ((double)(mpBaseGain * mpIncreaseRate))
            // (int) mpIncreaseResult -> mpValue + mpIncreaseAmount

        int[] eightMpLevelUpResults = new int[8];
        int eightMpLevelUpResultsIndex = 0;
        int characterNameIndex = getCharacterNameIndex(characterName);
        int levelBracketIndex = getLevelUpBracketIndex(nextLevel);
        int mpBase = getMpBase(characterNameIndex, levelBracketIndex);
        int mpGradient = getMpGradient(characterNameIndex, levelBracketIndex);
        int mpBaseline = getMpBaseline(nextLevel, mpBase, mpGradient);
        int mpBaseGain = getMpBaseGain(nextLevel, mpGradient);
        int mpDifference;
        double mpIncreaseRate;
        int mpIncreaseAmount;

        for (int rndNumber : rndNums){ // 1 through 8
            mpDifference = getMpDifference(rndNumber, mpBaseline, mpValue);
            mpIncreaseRate = getMpIncreaseRate(mpDifference);
            mpIncreaseAmount = getMpIncreaseAmount(mpBaseGain, mpIncreaseRate);
            eightMpLevelUpResults[eightMpLevelUpResultsIndex] = getMpIncreaseResult(mpValue, mpIncreaseAmount);
            eightMpLevelUpResultsIndex++;
        }

        return eightMpLevelUpResults;
    }
    //******************************************************************************************************************
    //*********************************** Calculate Next Level Primary Stats Methods ***********************************
    //******************************************************************************************************************
    public static int getPrimaryStatRankCurve(String characterName, String pStatName){
        int defaultPrimaryStatRankCurve = 0;

        for (int characterNameIndex = 0; characterNameIndex < primaryStatRankCurveTable.length; characterNameIndex++){
            for (int pStatNameIndex = 0; pStatNameIndex < primaryStatRankCurveTable[characterNameIndex].length; pStatNameIndex++){
                if (characterName.toUpperCase().equals(characterNames[characterNameIndex].toUpperCase()) && // characterName
                        pStatName.toUpperCase().equals(primaryStatName[pStatNameIndex].toUpperCase())) { // pStatName
                    return primaryStatRankCurveTable[characterNameIndex][pStatNameIndex];
                }
            }
        }

        return defaultPrimaryStatRankCurve;
    }

    public static int getPrimaryStatBase(int pStatRankCurve, int levelBracketIndex){
        return primaryStatBaseNumberTable[pStatRankCurve][levelBracketIndex];
    }

    public static int getPrimaryStatGradient(int pStatRankCurve, int levelBracketIndex){
        return primaryStatGradientNumberTable[pStatRankCurve][levelBracketIndex];
    }

    public static int getPrimaryStatBaseline(int nextLevel, int pStatBase, int pStatGradient){
        return pStatBase + ((pStatGradient * nextLevel) / 100);
    }

    public static int getPrimaryStatDifference(int rndNumber, int pStatBaseline, int pStatValue){ // returns capped range between 0 through 11
        int pStatDifference = rndNumber + pStatBaseline - pStatValue;
        int minpStatDifference = 0;
        int maxpStatDifference = 11;

        if (pStatDifference < minpStatDifference)
        {
            return minpStatDifference;
        } else if (pStatDifference > maxpStatDifference) {
            return maxpStatDifference;
        }
        return pStatDifference;
    }

    public static int getPrimaryStatIncreaseAmount(int pStatDifference){
        if (pStatDifference < 4){
            return 0;
        } else if (pStatDifference < 7){
            return 1;
        } else if (pStatDifference < 10){
            return 2;
        }
        return 3;
    }

    public static int[] getPrimaryStatIncreaseAmounts(String characterName, int nextLevel, String pStatName, int pStatValue){
        int[] eightPrimaryStatIncreaseAmounts = new int[8];
        int eightPrimaryStatIncreaseAmountsIndex = 0;
        int levelBracketIndex = getLevelUpBracketIndex(nextLevel);
        int pStatRankCurve = getPrimaryStatRankCurve(characterName, pStatName);
        int pStatBase = getPrimaryStatBase(pStatRankCurve, levelBracketIndex);
        int pStatGradient = getPrimaryStatGradient(pStatRankCurve, levelBracketIndex);
        int pStatBaseline = getPrimaryStatBaseline(nextLevel, pStatBase, pStatGradient);
        int pStatDifference;

        for (int rndNumber : rndNums) { // 1 through 8
            pStatDifference = getPrimaryStatDifference(rndNumber, pStatBaseline, pStatValue);
            eightPrimaryStatIncreaseAmounts[eightPrimaryStatIncreaseAmountsIndex] = getPrimaryStatIncreaseAmount(pStatDifference);
            eightPrimaryStatIncreaseAmountsIndex++;
        }

        return eightPrimaryStatIncreaseAmounts;
    }

    public static int getPrimaryStatIncreaseResult(int pStatValue, int pStatIncreaseAmount){
        if (pStatValue + pStatIncreaseAmount > maxPrimaryAndLuckStatsValue){
            return maxPrimaryAndLuckStatsValue;
        }
        return pStatValue + pStatIncreaseAmount;
    }

    public static int calculatePrimaryStatLevelUpResult(String characterName, int nextLevel, String pStatName, int rndNumber, int pStatValue){
        /***************************************************************
         *     Method Calculates Primary Stat (Str | Dex | Vit | Mag | Spr)
         *     for next level up with eight different results.
         *     (Note): Luck Stat has it's own tables it uses,
         *             So luck will have it's own calculated method
         *
         *     Character = name
         *     Level Up Number = the level to be increased to
         *     Current Stat Name = Equal the name of the selected stat (eg: Str, Dex, Vit, Mag, Spr)
         *     Current Stat Value = Equal the number of the selected stat (eg: the number of str if str was selected) -> (eg: 20)
         *     Character stat rank curve = get from table of (Character Name) and (Stat Name, eg: str...) -> (0-29)
         *     Level Up Brackets = 2-11, 12-21, 22-31, 32-41, 42-51, 52-61, 62-81, 82-99
         *     Base Number = table of (Character Rank Curve) and (Level Up Bracket).
         *     Gradient Number = table of (Character Rank Curve) and (Level Up Bracket).
         *     Baseline Stat = (Base Number) + ((Gradient * Level Up Number) / 100)
         *     Stat Difference = Rnd(1..8) + Baseline Stat - Current Stat
         *     Difference | Stat Gain
         *         0-3    |     0
         *         4-6    |     1
         *         7-9    |     2
         *        10-11   |     3
         *
         *     (Note): Max Primary Stat is capped at 100 for all levels.
         *     This means if your stat is 100 or higher, the outcome of
         *     the increase upon the next level up will be 0.
         ****************************************************************
         *     Calculate Primary Stat (Luck) for next level up. It's the same as the other 5 stats,
         *     except luck uses a different Gradient and Base table. So instead of the table
         *     depending on level bracket and character rank curve (eg: 0-29), it depends on
         *     level bracket and the character name (eg: Cloud).
         *
         ***************************************************************/
        int primaryStatLevelUpResult;
        int levelBracketIndex = getLevelUpBracketIndex(nextLevel);
        int pStatRankCurve = getPrimaryStatRankCurve(characterName, pStatName);
        int pStatBase = getPrimaryStatBase(pStatRankCurve, levelBracketIndex);
        int pStatGradient = getPrimaryStatGradient(pStatRankCurve, levelBracketIndex);
        int pStatBaseline = getPrimaryStatBaseline(nextLevel, pStatBase, pStatGradient);
        int pStatDifference;
        int pStatIncreaseAmount;

        pStatDifference = getPrimaryStatDifference(rndNumber, pStatBaseline, pStatValue);
        pStatIncreaseAmount = getPrimaryStatIncreaseAmount(pStatDifference);
        primaryStatLevelUpResult = getPrimaryStatIncreaseResult(pStatValue, pStatIncreaseAmount);


        return primaryStatLevelUpResult;
    }

    public static int[] calculatePrimaryStatLevelUpResults(String characterName, int nextLevel, String pStatName, int pStatValue){
        /***************************************************************
         *     Method Calculates Primary Stat (Str | Dex | Vit | Mag | Spr)
         *     for next level up with eight different results.
         *     (Note): Luck Stat has it's own tables it uses,
         *             So luck will have it's own calculated method
         *
         *     Character = name
         *     Level Up Number = the level to be increased to
         *     Current Stat Name = Equal the name of the selected stat (eg: Str, Dex, Vit, Mag, Spr)
         *     Current Stat Value = Equal the number of the selected stat (eg: the number of str if str was selected) -> (eg: 20)
         *     Character stat rank curve = get from table of (Character Name) and (Stat Name, eg: str...) -> (0-29)
         *     Level Up Brackets = 2-11, 12-21, 22-31, 32-41, 42-51, 52-61, 62-81, 82-99
         *     Base Number = table of (Character Rank Curve) and (Level Up Bracket).
         *     Gradient Number = table of (Character Rank Curve) and (Level Up Bracket).
         *     Baseline Stat = (Base Number) + ((Gradient * Level Up Number) / 100)
         *     Stat Difference = Rnd(1..8) + Baseline Stat - Current Stat
         *     Difference | Stat Gain
         *         0-3    |     0
         *         4-6    |     1
         *         7-9    |     2
         *        10-11   |     3
         *
         *     (Note): Max Primary Stat is capped at 100 for all levels.
         *     This means if your stat is 100 or higher, the outcome of
         *     the increase upon the next level up will be 0.
         ****************************************************************
         *     Calculate Primary Stat (Luck) for next level up. It's the same as the other 5 stats,
         *     except luck uses a different Gradient and Base table. So instead of the table
         *     depending on level bracket and character rank curve (eg: 0-29), it depends on
         *     level bracket and the character name (eg: Cloud).
         *
         ***************************************************************/
        int[] eightPrimaryStatLevelUpResults = new int[8];
        int eightPrimaryStatLevelUpResultsIndex = 0;
        int levelBracketIndex = getLevelUpBracketIndex(nextLevel);
        int pStatRankCurve = getPrimaryStatRankCurve(characterName, pStatName);
        int pStatBase = getPrimaryStatBase(pStatRankCurve, levelBracketIndex);
        int pStatGradient = getPrimaryStatGradient(pStatRankCurve, levelBracketIndex);
        int pStatBaseline = getPrimaryStatBaseline(nextLevel, pStatBase, pStatGradient);
        int pStatDifference;
        int pStatIncreaseAmount;

        for (int rndNumber : rndNums){ // 1 through 8
            pStatDifference = getPrimaryStatDifference(rndNumber, pStatBaseline, pStatValue);
            pStatIncreaseAmount = getPrimaryStatIncreaseAmount(pStatDifference);
            eightPrimaryStatLevelUpResults[eightPrimaryStatLevelUpResultsIndex] = getPrimaryStatIncreaseResult(pStatValue, pStatIncreaseAmount);
            eightPrimaryStatLevelUpResultsIndex++;
        }

        return eightPrimaryStatLevelUpResults;
    }
    //******************************************************************************************************************
    //**************************************** Calculate Next Level Luck Methods ***************************************
    //******************************************************************************************************************
    public static int getLuckStatBase(int characterNameIndex, int levelBracketIndex){
        return luckStatBaseNumberTable[characterNameIndex][levelBracketIndex];
    }

    public static int getLuckStatGradient(int characterNameIndex, int levelBracketIndex){
        return luckStatGradientNumberTable[characterNameIndex][levelBracketIndex];
    }

    public static int getLuckStatBaseline(int nextLevel, int lckStatBase, int lckStatGradient){
        return lckStatBase + ((lckStatGradient * nextLevel) / 100);
    }

    public static int getLuckStatDifference(int rndNumber, int lckStatBaseline, int lckStatValue){ // returns capped range between 0 through 11
        int lckStatDifference = rndNumber + lckStatBaseline - lckStatValue;
        int minLuckStatDifference = 0;
        int maxLuckStatDifference = 11;

        if (lckStatDifference < minLuckStatDifference)
        {
            return minLuckStatDifference;
        } else if (lckStatDifference > maxLuckStatDifference) {
            return maxLuckStatDifference;
        }
        return lckStatDifference;
    }

    public static int getLuckStatIncreaseAmount(int lckStatDifference){
        if (lckStatDifference < 4){
            return 0;
        } else if (lckStatDifference < 7){
            return 1;
        } else if (lckStatDifference < 10){
            return 2;
        }
        return 3;
    }

    public static int[] getLuckStatIncreaseAmounts(String characterName, int nextLevel, int lckStatValue){
        int[] eightLuckStatIncreaseAmounts = new int[8];
        int eightLuckStatIncreaseAmountsIndex = 0;
        int characterNameIndex = getCharacterNameIndex(characterName);
        int levelBracketIndex = getLevelUpBracketIndex(nextLevel);
        int lckStatBase = getLuckStatBase(characterNameIndex, levelBracketIndex);
        int lckStatGradient = getLuckStatGradient(characterNameIndex, levelBracketIndex);
        int lckStatBaseline = getLuckStatBaseline(nextLevel, lckStatBase, lckStatGradient);
        int lckStatDifference;

        for (int rndNumber : rndNums) { // 1 through 8
            lckStatDifference = getLuckStatDifference(rndNumber, lckStatBaseline, lckStatValue);
            eightLuckStatIncreaseAmounts[eightLuckStatIncreaseAmountsIndex] = getLuckStatIncreaseAmount(lckStatDifference);
            eightLuckStatIncreaseAmountsIndex++;
        }

        return eightLuckStatIncreaseAmounts;
    }

    public static int getLuckStatIncreaseResult(int lckStatValue, int lckStatIncreaseAmount){
        if (lckStatValue + lckStatIncreaseAmount > maxPrimaryAndLuckStatsValue){
            return maxPrimaryAndLuckStatsValue;
        }
        return lckStatValue + lckStatIncreaseAmount;
    }

    public static int calculateLckLevelUpResult(String characterName, int nextLevel, int lckStatValue, int rndNum){
        int eightLuckStatLevelUpResults;
        int characterNameIndex = getCharacterNameIndex(characterName);
        int levelBracketIndex = getLevelUpBracketIndex(nextLevel);
        int lckStatBase = getLuckStatBase(characterNameIndex, levelBracketIndex);
        int lckStatGradient = getLuckStatGradient(characterNameIndex, levelBracketIndex);
        int lckStatBaseline = getLuckStatBaseline(nextLevel, lckStatBase, lckStatGradient);
        int lckStatDifference;
        int lckStatIncreaseAmount;
        lckStatDifference = getLuckStatDifference(rndNum, lckStatBaseline, lckStatValue);
        lckStatIncreaseAmount = getLuckStatIncreaseAmount(lckStatDifference);
        eightLuckStatLevelUpResults = getLuckStatIncreaseResult(lckStatValue, lckStatIncreaseAmount);

        return eightLuckStatLevelUpResults;
    }

    public static int[] calculateLuckStatLevelUpResults(String characterName, int nextLevel, int lckStatValue){
        /***************************************************************
         *     For calculating Primary Stat (Luck) for next level up....
         *     It's the same as the other 5 stats, except luck uses a
         *     different Gradient and Base table. So instead of the table
         *     depending on level bracket and character rank curve (eg: 0-29), it depends on
         *     level bracket and the character name (eg: Cloud).
         *
         ***************************************************************/
        int[] eightLuckStatLevelUpResults = new int[8];
        int eightLuckStatLevelUpResultsIndex = 0;
        int characterNameIndex = getCharacterNameIndex(characterName);
        int levelBracketIndex = getLevelUpBracketIndex(nextLevel);
        int lckStatBase = getLuckStatBase(characterNameIndex, levelBracketIndex);
        int lckStatGradient = getLuckStatGradient(characterNameIndex, levelBracketIndex);
        int lckStatBaseline = getLuckStatBaseline(nextLevel, lckStatBase, lckStatGradient);
        int lckStatDifference;
        int lckStatIncreaseAmount;

        for (int rndNumber : rndNums){ // 1 through 8
            lckStatDifference = getLuckStatDifference(rndNumber, lckStatBaseline, lckStatValue);
            lckStatIncreaseAmount = getLuckStatIncreaseAmount(lckStatDifference);
            eightLuckStatLevelUpResults[eightLuckStatLevelUpResultsIndex] = getLuckStatIncreaseResult(lckStatValue, lckStatIncreaseAmount);
            eightLuckStatLevelUpResultsIndex++;
        }

        return eightLuckStatLevelUpResults;
    }


}
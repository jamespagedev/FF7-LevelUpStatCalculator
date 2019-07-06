/**
 * Created by James Page on 5/20/2017.
 */
package ClassFiles;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public abstract class CalculateRangeLevels extends Calculator {
    public static boolean isDebuggerOn = false;

    //******************************************************************************************************************
    //******************************************************* HP *******************************************************
    //******************************************************************************************************************
    public static boolean isEndLevelHpValueAvailable(String characterName, int startLevel, int endLevel, int startHp, int endHp){
        ArrayList<Integer> checkEndLevelValues = getAllPossibleHpValuesAtEndLevel(characterName, startLevel, endLevel, startHp);
        return checkEndLevelValues.contains(endHp);
    }

    public static ArrayList<Integer> getAllPossibleHpValuesAtEndLevel(String characterName, int startLevel, int endLevel, int startHp){
        ArrayList<Integer> currentLevelHpValues = new ArrayList<>(Collections.singletonList(startHp));
        ArrayList<Integer> nextLevelHpValues = new ArrayList<>();
        for (int levelUp = startLevel + 1; levelUp <= endLevel; levelUp++){
            for (int hpValue: currentLevelHpValues){
                for (int rndNum: rndNums){
                    int levelUpHpValue = calculateHpLevelUpResult(characterName, levelUp, hpValue, rndNum);
                    if (!nextLevelHpValues.contains(levelUpHpValue)){
                        nextLevelHpValues.add(levelUpHpValue);
                    }
                }
            }
            currentLevelHpValues = new ArrayList<>(nextLevelHpValues);
            if (levelUp != endLevel) {
                nextLevelHpValues.clear();
            }
        }
        Collections.sort(nextLevelHpValues);
        if (nextLevelHpValues.isEmpty()){
            return currentLevelHpValues;
        }
        return nextLevelHpValues;
    }

    public static ArrayList<ArrayList<Integer>> getPossibleHpValuesAtAllLevels(String characterName, int startLevel, int endLevel, int startHp){
        ArrayList<ArrayList<Integer>> rangedLevelsHpValues = new ArrayList<>();
        rangedLevelsHpValues.add(new ArrayList<>(Collections.singletonList(startHp)));
        ArrayList<Integer> nextLevelHpValues = new ArrayList<>();
        int rangedLevelsHpValuesIndex = 0;
        for (int levelUp = startLevel + 1; levelUp <= endLevel; levelUp++){
            for (int hpValue : rangedLevelsHpValues.get(rangedLevelsHpValuesIndex)) {
                for (int rndNum : rndNums) {
                    int levelUpHpValue = calculateHpLevelUpResult(characterName, levelUp, hpValue, rndNum);
                    if (!nextLevelHpValues.contains(levelUpHpValue)) {
                        nextLevelHpValues.add(levelUpHpValue);
                    }
                }
            }
            Collections.sort(nextLevelHpValues);
            rangedLevelsHpValues.add(new ArrayList<>(nextLevelHpValues));
            nextLevelHpValues.clear();
            rangedLevelsHpValuesIndex++;
        }
        return rangedLevelsHpValues;
    }

    public static Integer getMaxHpValueAtEndLevel(String characterName, int startLevel, int endLevel, int startHp){
        ArrayList<Integer> hpValues = getAllPossibleHpValuesAtEndLevel(characterName, startLevel, endLevel, startHp);
        return hpValues.get(hpValues.size() - 1);
    }

    public static ArrayList<ArrayList<Integer>> calculateRangeLevelsHpResults(String characterName, int startLevel, int endLevel, int startHp, int endHp){
        ArrayList<ArrayList<Integer>> rangedLevelsHpValues = new ArrayList<>();
        rangedLevelsHpValues.add(new ArrayList<>(Collections.singletonList(startHp)));
        ArrayList<Integer> nextLevelHpValues = new ArrayList<>();
        int endLevelValue;
        int maxHpAtEndLevel = getMaxHpValueAtEndLevel(characterName, startLevel, endLevel, startHp);
        if (maxHpAtEndLevel == endHp){
            endLevelValue = getMaxHpValueAtEndLevel(characterName, startLevel, endLevel, startHp);
        } else if (isEndLevelHpValueAvailable(characterName, startLevel, endLevel, startHp, endHp)) {
            endLevelValue = endHp;
        } else {
            rangedLevelsHpValues.clear();
            return rangedLevelsHpValues;
        }

        // adds all possible values for every level up
        int rangedLevelsHpValuesIndex = 0;
        for (int levelUp = startLevel + 1; levelUp <= endLevel; levelUp++){
            if (levelUp == endLevel && nextLevelHpValues.isEmpty()) {
                nextLevelHpValues.add(endLevelValue);
            } else {
                for (int hpValue : rangedLevelsHpValues.get(rangedLevelsHpValuesIndex)) {
                    for (int rndNum : rndNums) {
                        int levelUpHpValue = calculateHpLevelUpResult(characterName, levelUp, hpValue, rndNum);
                        if (!nextLevelHpValues.contains(levelUpHpValue)) {
                            nextLevelHpValues.add(levelUpHpValue);
                        }
                    }
                }
            }
            Collections.sort(nextLevelHpValues);
            rangedLevelsHpValues.add(new ArrayList<>(nextLevelHpValues));
            nextLevelHpValues.clear();
            rangedLevelsHpValuesIndex++;
        }

        // removes all values for every level up that doesn't path to the end level value
        int currentLevel = endLevel;
        int currentLevelValuesIndex = rangedLevelsHpValues.size() - 1;
        int previousLevelIndex = currentLevelValuesIndex - 1;
        for (int previousLevel = currentLevel - 1; previousLevel > startLevel; previousLevel--){
            ArrayList<Integer> tempRangedLevelsHpValues = new ArrayList<>(rangedLevelsHpValues.get(previousLevelIndex));
            int previousLevelHpValueIndex = 0;
            for (int previousLevelHpValue: tempRangedLevelsHpValues){
                boolean valueLinkFound = false;
                int rndNum = 8;
                while (!valueLinkFound && rndNum > 0){
                    int prevToNextLevelHpValue = calculateHpLevelUpResult(characterName, currentLevel, previousLevelHpValue, rndNum);
                    if (rangedLevelsHpValues.get(currentLevelValuesIndex).contains(prevToNextLevelHpValue)){
                        valueLinkFound = true;
                        rndNum = 0;
                    }
                    rndNum--;
                }
                if (!valueLinkFound){
                    rangedLevelsHpValues.get(previousLevelIndex).remove(previousLevelHpValueIndex);
                } else {
                    previousLevelHpValueIndex++;
                }
            }
            currentLevelValuesIndex--;
            previousLevelIndex--;
            currentLevel--;
        }
        return rangedLevelsHpValues;
    }

    public static Integer getMinHpValueAtEndLevel(String characterName, int startLevel, int endLevel, int startHp){
        ArrayList<Integer> hpValues = getAllPossibleHpValuesAtEndLevel(characterName, startLevel, endLevel, startHp);
        return hpValues.get(0);
    }

    //******************************************************************************************************************
    //******************************************************* MP *******************************************************
    //******************************************************************************************************************
    public static boolean isEndLevelMpValueAvailable(String characterName, int startLevel, int endLevel, int startMp, int endMp){
        ArrayList<Integer> checkEndLevelValues = getAllPossibleMpValuesAtEndLevel(characterName, startLevel, endLevel, startMp);
        return checkEndLevelValues.contains(endMp);
    }

    public static ArrayList<Integer> getAllPossibleMpValuesAtEndLevel(String characterName, int startLevel, int endLevel, int startMp){
        ArrayList<Integer> currentLevelMpValues = new ArrayList<>(Collections.singletonList(startMp));
        ArrayList<Integer> nextLevelMpValues = new ArrayList<>();
        for (int levelUp = startLevel + 1; levelUp <= endLevel; levelUp++){
            for (int mpValue: currentLevelMpValues){
                for (int rndNum: rndNums){
                    int levelUpMpValue = calculateMpLevelUpResult(characterName, levelUp, mpValue, rndNum);
                    if (!nextLevelMpValues.contains(levelUpMpValue)){
                        nextLevelMpValues.add(levelUpMpValue);
                    }
                }
            }
            currentLevelMpValues = new ArrayList<>(nextLevelMpValues);
            if (levelUp != endLevel) {
                nextLevelMpValues.clear();
            }
        }
        Collections.sort(nextLevelMpValues);
        if (nextLevelMpValues.isEmpty()){
            return currentLevelMpValues;
        }
        return nextLevelMpValues;
    }

    public static ArrayList<ArrayList<Integer>> getPossibleMpValuesAtAllLevels(String characterName, int startLevel, int endLevel, int startMp){
        ArrayList<ArrayList<Integer>> rangedLevelsMpValues = new ArrayList<>();
        rangedLevelsMpValues.add(new ArrayList<>(Collections.singletonList(startMp)));
        ArrayList<Integer> nextLevelMpValues = new ArrayList<>();
        int rangedLevelsMpValuesIndex = 0;
        for (int levelUp = startLevel + 1; levelUp <= endLevel; levelUp++){
            for (int mpValue : rangedLevelsMpValues.get(rangedLevelsMpValuesIndex)) {
                for (int rndNum : rndNums) {
                    int levelUpMpValue = calculateMpLevelUpResult(characterName, levelUp, mpValue, rndNum);
                    if (!nextLevelMpValues.contains(levelUpMpValue)) {
                        nextLevelMpValues.add(levelUpMpValue);
                    }
                }
            }
            Collections.sort(nextLevelMpValues);
            rangedLevelsMpValues.add(new ArrayList<>(nextLevelMpValues));
            nextLevelMpValues.clear();
            rangedLevelsMpValuesIndex++;
        }
        return rangedLevelsMpValues;
    }

    public static Integer getMaxMpValueAtEndLevel(String characterName, int startLevel, int endLevel, int startMp){
        ArrayList<Integer> mpValues = getAllPossibleMpValuesAtEndLevel(characterName, startLevel, endLevel, startMp);
        return mpValues.get(mpValues.size() - 1);
    }

    public static ArrayList<ArrayList<Integer>> calculateRangeLevelsMpResults(String characterName, int startLevel, int endLevel, int startMp, int endMp){
        ArrayList<ArrayList<Integer>> rangedLevelsMpValues = new ArrayList<>();
        rangedLevelsMpValues.add(new ArrayList<>(Collections.singletonList(startMp)));
        ArrayList<Integer> nextLevelMpValues = new ArrayList<>();
        int endLevelValue;
        int maxMpAtEndLevel = getMaxHpValueAtEndLevel(characterName, startLevel, endLevel, startMp);
        if (maxMpAtEndLevel == endMp){
            endLevelValue = getMaxMpValueAtEndLevel(characterName, startLevel, endLevel, startMp);
        } else if (isEndLevelMpValueAvailable(characterName, startLevel, endLevel, startMp, endMp)) {
            endLevelValue = endMp;
        } else {
            rangedLevelsMpValues.clear();
            return rangedLevelsMpValues;
        }

        // adds all possible values for every level up
        int rangedLevelsMpValuesIndex = 0;
        for (int levelUp = startLevel + 1; levelUp <= endLevel; levelUp++){
            if (levelUp == endLevel && nextLevelMpValues.isEmpty()) {
                nextLevelMpValues.add(endLevelValue);
            } else {
                for (int mpValue : rangedLevelsMpValues.get(rangedLevelsMpValuesIndex)) {
                    for (int rndNum : rndNums) {
                        int levelUpMpValue = calculateMpLevelUpResult(characterName, levelUp, mpValue, rndNum);
                        if (!nextLevelMpValues.contains(levelUpMpValue)) {
                            nextLevelMpValues.add(levelUpMpValue);
                        }
                    }
                }
            }
            Collections.sort(nextLevelMpValues);
            rangedLevelsMpValues.add(new ArrayList<>(nextLevelMpValues));
            nextLevelMpValues.clear();
            rangedLevelsMpValuesIndex++;
        }

        // removes all values for every level up that doesn't path to the end level value
        int currentLevel = endLevel;
        int currentLevelValuesIndex = rangedLevelsMpValues.size() - 1;
        int previousLevelIndex = currentLevelValuesIndex - 1;
        for (int previousLevel = currentLevel - 1; previousLevel > startLevel; previousLevel--){
            ArrayList<Integer> tempRangedLevelsMpValues = new ArrayList<>(rangedLevelsMpValues.get(previousLevelIndex));
            int previousLevelMpValueIndex = 0;
            for (int previousLevelMpValue: tempRangedLevelsMpValues){
                boolean valueLinkFound = false;
                int rndNum = 8;
                while (!valueLinkFound && rndNum > 0){
                    int prevToNextLevelMpValue = calculateMpLevelUpResult(characterName, currentLevel, previousLevelMpValue, rndNum);
                    if (rangedLevelsMpValues.get(currentLevelValuesIndex).contains(prevToNextLevelMpValue)){
                        valueLinkFound = true;
                        rndNum = 0;
                    }
                    rndNum--;
                }
                if (!valueLinkFound){
                    rangedLevelsMpValues.get(previousLevelIndex).remove(previousLevelMpValueIndex);
                } else {
                    previousLevelMpValueIndex++;
                }
            }
            currentLevelValuesIndex--;
            previousLevelIndex--;
            currentLevel--;
        }
        return rangedLevelsMpValues;
    }

    public static Integer getMinMpValueAtEndLevel(String characterName, int startLevel, int endLevel, int startMp){
        ArrayList<Integer> mpValues = getAllPossibleMpValuesAtEndLevel(characterName, startLevel, endLevel, startMp);
        return mpValues.get(0);
    }

    //******************************************************************************************************************
    //****************************************************** HP/MP *****************************************************
    //******************************************************************************************************************
    //-------------------------------------------- Support Functions (HP/MP) -------------------------------------------
    public static ArrayList<ArrayList<Integer>> getHpOrMpRndNumsUsedInLvls(String characterName, int startLvl, int endLvl, ArrayList<ArrayList<Integer>> rangedLvlValues, boolean isHpSelected){
        ArrayList<Integer> rndNumsUsedInLvl = new ArrayList<>();
        ArrayList<ArrayList<Integer>> rndNumsUsedInRangedLvls = new ArrayList<>();

        for (int lvlIndex = 0, nextLvlIndex = 1, nextLvl = startLvl + 1; lvlIndex < rangedLvlValues.size() - 1; lvlIndex++, nextLvlIndex++, nextLvl++){
            for(Integer value: rangedLvlValues.get(lvlIndex)){
                for(int rndNum: rndNums){ // HP is selected
                    if ((isHpSelected && rangedLvlValues.get(nextLvlIndex).contains(calculateHpLevelUpResult(characterName, nextLvl, value, rndNum)) ||
                            !isHpSelected && rangedLvlValues.get(nextLvlIndex).contains(calculateMpLevelUpResult(characterName, nextLvl, value, rndNum))) &&
                            !rndNumsUsedInLvl.contains(rndNum)){
                                rndNumsUsedInLvl.add(rndNum); // add random number used, only if it is not in the array
                    }
                }
            }
            Collections.sort(rndNumsUsedInLvl);
            rndNumsUsedInRangedLvls.add(new ArrayList<>(rndNumsUsedInLvl));
            rndNumsUsedInLvl.clear();
        }
        return rndNumsUsedInRangedLvls;
    }

    public static ArrayList<ArrayList<Integer>> updateHpMpValuesInArray(String characterName, int startLvl, boolean hpIsSelected,
                                                                                      ArrayList<ArrayList<Integer>> valuesArray){
        int currentLvl = startLvl + valuesArray.size() - 2;
        int rndNumsIndex = 0;
        boolean rndNumFound = false;
        boolean allRndNumsChecked = false;
        if (hpIsSelected){ // array is hp values
            for (int lvlIndex = valuesArray.size() - 2; lvlIndex >= 0; lvlIndex--, currentLvl--){
                for (int valueIndex = 0; valueIndex < valuesArray.get(lvlIndex).size(); valueIndex++){
                    while (!rndNumFound && !allRndNumsChecked){
                        if (valuesArray.get(lvlIndex + 1).contains(calculateHpLevelUpResult(characterName, currentLvl + 1, valuesArray.get(lvlIndex).get(valueIndex), rndNums[rndNumsIndex]))){
                            rndNumFound = true;
                        }
                        if (rndNums[rndNumsIndex] == 8){
                            allRndNumsChecked = true;
                        }
                        rndNumsIndex++;
                    }
                    if (!rndNumFound){
                        valuesArray.get(lvlIndex).remove(valueIndex);
                    }
                    rndNumFound = false;
                    allRndNumsChecked = false;
                    rndNumsIndex = 0;
                }
            }
        } else { // array is mp values
            for (int lvlIndex = valuesArray.size() - 2; lvlIndex >= 0; lvlIndex--, currentLvl--){
                for (int valueIndex = 0; valueIndex < valuesArray.get(lvlIndex).size(); valueIndex++){
                    while (!rndNumFound && !allRndNumsChecked){
                        if (valuesArray.get(lvlIndex + 1).contains(calculateMpLevelUpResult(characterName, currentLvl + 1, valuesArray.get(lvlIndex).get(valueIndex), rndNums[rndNumsIndex]))){
                            rndNumFound = true;
                        }
                        if (rndNums[rndNumsIndex] == 8){
                            allRndNumsChecked = true;
                        }
                        rndNumsIndex++;
                    }
                    if (!rndNumFound){
                        valuesArray.get(lvlIndex).remove(valueIndex);
                    }
                    rndNumFound = false;
                    allRndNumsChecked = false;
                    rndNumsIndex = 0;
                }
            }
        }

        return valuesArray;
    }

    public static ArrayList<ArrayList<ArrayList<Integer>>> omitValuesWithSingRndNumOnLevel(String characterName, boolean hpIsSelected,
                                                                                              int startLvl, int endLvl,
                                                                                              int currentLvlIndex, int currentLvl,
                                                                                              ArrayList<ArrayList<ArrayList<Integer>>> testHpMpRangedLevelValues,
                                                                                              Integer selectedRndNum){
        /* Pre-Condition: updatedResults Array must contain 2 dimensional arrays in the following order...
                          hpValuesArray
                          mpValuesArray
                          hpRndNumsArray
                          mpRndNumsArray
        */
        // omit values (hp or mp based on "selectedStat" passed as "hpValues" or "mpValues") in current level index using the "selectedRndNum"
        ArrayList<Integer> rndNumsUsedForValue = new ArrayList<>();
        int valueArray;
        int rndNumArray;
        int valueArrayNextLvlIndex = currentLvlIndex + 1;
        if (hpIsSelected){
            valueArray = 0;
            rndNumArray = 2;
        } else {
            valueArray = 1;
            rndNumArray = 3;
        }
        for (int valueIndex = 0; valueIndex < testHpMpRangedLevelValues.get(valueArray).get(currentLvlIndex).size(); valueIndex++){
            for(Integer rndNum: rndNums){
                if (hpIsSelected && testHpMpRangedLevelValues.get(valueArray).get(valueArrayNextLvlIndex).contains(calculateHpLevelUpResult(characterName, currentLvl + 1, testHpMpRangedLevelValues.get(valueArray).get(currentLvlIndex).get(valueIndex), rndNum))){
                    rndNumsUsedForValue.add(rndNum);
                } else if (testHpMpRangedLevelValues.get(valueArray).get(valueArrayNextLvlIndex).contains(calculateMpLevelUpResult(characterName, currentLvl + 1, testHpMpRangedLevelValues.get(valueArray).get(currentLvlIndex).get(valueIndex), rndNum))){
                    rndNumsUsedForValue.add(rndNum);
                }
            }
            if (rndNumsUsedForValue.size() == 1 && rndNumsUsedForValue.contains(selectedRndNum)){
                testHpMpRangedLevelValues.get(valueArray).get(currentLvlIndex).remove(valueIndex);
                // Update values in array first
                testHpMpRangedLevelValues.set(valueArray, updateHpMpValuesInArray(characterName, startLvl, hpIsSelected, testHpMpRangedLevelValues.get(valueArray)));
                // Now update random numbers used in values
                testHpMpRangedLevelValues.set(rndNumArray, getHpOrMpRndNumsUsedInLvls(characterName, startLvl, endLvl, testHpMpRangedLevelValues.get(valueArray), hpIsSelected));
            }
            rndNumsUsedForValue.clear();
        }

        return testHpMpRangedLevelValues;
    }

    public static ArrayList<ArrayList<ArrayList<Integer>>> omitRndNumsEachLevelContainingSingleRndNumInOppositeHpMpValue(ArrayList<ArrayList<ArrayList<Integer>>> testHpMpRangedLevelValues){
        int hpLvlRndNumsIndex = 2; // index for hpMpRangedLevelValues
        int mpLvlRndNumsIndex = 3; // index for hpMpRangedLevelValues

        // For each level, remove rndNums that are used as the only rndNum for the other value...
        for (int lvlIndex = 0; lvlIndex < testHpMpRangedLevelValues.get(hpLvlRndNumsIndex).size(); lvlIndex++) {
            // If hpRndNums only has 1 rndNum, remove it for mpRndNums if it is in there
            if (testHpMpRangedLevelValues.get(hpLvlRndNumsIndex).get(lvlIndex).size() == 1 && testHpMpRangedLevelValues.get(mpLvlRndNumsIndex).get(lvlIndex).contains(testHpMpRangedLevelValues.get(hpLvlRndNumsIndex).get(lvlIndex).get(0))){ // if hpRndNums has only 1 rndNum and the rndNum exists in mpRndNums
                // remove rndNum from mpRndNums
                testHpMpRangedLevelValues.get(mpLvlRndNumsIndex).get(lvlIndex).remove(testHpMpRangedLevelValues.get(mpLvlRndNumsIndex).get(lvlIndex).indexOf(testHpMpRangedLevelValues.get(hpLvlRndNumsIndex).get(lvlIndex).get(0)));
            }
            // If mpRndNums only has 1 rndNum, remove it for hpRndNums if it is in there
            if (testHpMpRangedLevelValues.get(mpLvlRndNumsIndex).get(lvlIndex).size() == 1 && testHpMpRangedLevelValues.get(hpLvlRndNumsIndex).get(lvlIndex).contains(testHpMpRangedLevelValues.get(mpLvlRndNumsIndex).get(lvlIndex).get(0))){ // if mpRndNums has only 1 rndNum and the rndNum exists in hpRndNums
                // remove rndNum from mpRndNums
                testHpMpRangedLevelValues.get(hpLvlRndNumsIndex).get(lvlIndex).remove(testHpMpRangedLevelValues.get(hpLvlRndNumsIndex).get(lvlIndex).indexOf(testHpMpRangedLevelValues.get(mpLvlRndNumsIndex).get(lvlIndex).get(0)));
            }
            if (testHpMpRangedLevelValues.get(hpLvlRndNumsIndex).get(lvlIndex).isEmpty() || testHpMpRangedLevelValues.get(mpLvlRndNumsIndex).get(lvlIndex).isEmpty()){ // all rndNums were removed because for this level, both mp/hp values only had 1 rndNum of the same value
                return testHpMpRangedLevelValues;
            }
        }

        return testHpMpRangedLevelValues;
    }

    public static boolean isNextLvlHpValueCompatibleWithCurrentLvlHpValues(String characterName, ArrayList<Integer> currentLvlHpValues, ArrayList<Integer> currentLvlRndNums, Integer nextLvlHpValue, int nextLvl){
        int lvlUpHpValue;
        for (Integer currentLvlHpValue: currentLvlHpValues){
            for (int currentLvlRndNum: currentLvlRndNums){
                lvlUpHpValue = calculateHpLevelUpResult(characterName, nextLvl, currentLvlHpValue, currentLvlRndNum);
                if (nextLvlHpValue.equals(lvlUpHpValue)){ // currentLvlHpValue levels up into nextLvlHpValue
                    return true;
                }
            }
        }
        return false; // not compatible
    }

    public static boolean isNextLvlMpValueCompatibleWithCurrentLvlMpValues(String characterName, ArrayList<Integer> currentLvlMpValues, ArrayList<Integer> currentLvlRndNums, Integer nextLvlMpValue, int nextLvl){
        int lvlUpMpValue;
        for (Integer currentLvlMpValue: currentLvlMpValues){
            for (int currentLvlRndNum: currentLvlRndNums){
                lvlUpMpValue = calculateMpLevelUpResult(characterName, nextLvl, currentLvlMpValue, currentLvlRndNum);
                if (nextLvlMpValue.equals(lvlUpMpValue)){ // currentLvlMpValue levels up into nextLvlMpValue
                    return true;
                }
            }
        }
        return false; // not compatible
    }

    public static ArrayList<ArrayList<ArrayList<Integer>>> updateLvlsHpMpValuesUsingRndNumsAndEndLvlValues(String characterName, int startLvl, int endLvl,
                                                                                                              ArrayList<ArrayList<ArrayList<Integer>>> origHpMpLvlsValuesRndNums){
        /* Precondition:
            /* All lvls rndNums must have at least 1 rndNum
            /* End lvl must contain the final value
        */

        int hpLvlValuesIndex = 0; // index for hpMpRangedLevelValues
        int mpLvlValuesIndex = 1; // index for hpMpRangedLevelValues
        int hpLvlRndNumsIndex = 2; // index for hpMpRangedLevelValues
        int mpLvlRndNumsIndex = 3; // index for hpMpRangedLevelValues
        int endLvlValuesIndex = origHpMpLvlsValuesRndNums.get(hpLvlValuesIndex).size() - 1;
        int firstValueIndex = 0;
        ArrayList<Integer> usableHpRndNums;
        ArrayList<Integer> usableMpRndNums;
        ArrayList<Integer> possibleHpValues;
        ArrayList<Integer> possibleNextLvlHpValues;
        ArrayList<Integer> possibleMpValues;
        ArrayList<Integer> possibleNextLvlMpValues;
        ArrayList<ArrayList<ArrayList<Integer>>> updatedHpMpLvlsValuesRndNums = new ArrayList<>();
        ArrayList<ArrayList<Integer>> lvlsHpValues = createEmptyLvls(startLvl, endLvl);
        ArrayList<ArrayList<Integer>> lvlsMpValues = createEmptyLvls(startLvl, endLvl);

        // fill in end values at the last level, so previous values can then be updated that calculate into the values of the higher levels
        lvlsHpValues.get(endLvlValuesIndex).add(origHpMpLvlsValuesRndNums.get(hpLvlValuesIndex).get(endLvlValuesIndex).get(firstValueIndex));
        lvlsMpValues.get(endLvlValuesIndex).add(origHpMpLvlsValuesRndNums.get(mpLvlValuesIndex).get(endLvlValuesIndex).get(firstValueIndex));

        // Fill in previous lvl values here starting at the level before the last level
        int currentLvlHpValueIndex = 0;
        int rndNumIndex = 0;
        boolean valueAddedOrAllRndNumsExhausted = false;
        boolean AllCurrentLvlValuesExhausted = false;

        boolean allRndNumsExhausted = false;
        Integer hpCalcValue;
        Integer mpCalcValue;
        for (int lvlIndex = endLvlValuesIndex - 1, nextLvlIndex = endLvlValuesIndex, lvl = endLvl - 1; lvlIndex >= 0; lvlIndex--, nextLvlIndex--, lvl--){ // Each lvl
            // Add hpValues in current level if they calculate into any hpValue in the level above
            possibleHpValues = new ArrayList<>(origHpMpLvlsValuesRndNums.get(hpLvlValuesIndex).get(lvlIndex));
            usableHpRndNums = new ArrayList<>(origHpMpLvlsValuesRndNums.get(hpLvlRndNumsIndex).get(lvlIndex));
            for (Integer hpValue: possibleHpValues){ // Each value in current lvl
                while (!valueAddedOrAllRndNumsExhausted){ // Each rndNum in current lvl
                    hpCalcValue = calculateHpLevelUpResult(characterName, lvl + 1, hpValue, usableHpRndNums.get(rndNumIndex));
                    // if next level contains the calculated lvlUp value using the current value... add the current value to the current level
                    if (lvlsHpValues.get(lvlIndex + 1).contains(hpCalcValue)){
                        lvlsHpValues.get(lvlIndex).add(hpValue);
                        valueAddedOrAllRndNumsExhausted = true;
                    }
                    // if no calculated lvlUp value could be found in the next level using the current value,
                    // do not add the value to current level, and move on to checking the next possible value in the current level
                    if (rndNumIndex == usableHpRndNums.size() - 1){
                        valueAddedOrAllRndNumsExhausted = true;
                    }
                    rndNumIndex++;
                } // end rndNums Loop
                valueAddedOrAllRndNumsExhausted = false; // reset for next use or not using anymore
                rndNumIndex = 0; // reset for next use or not using anymore
            } // end mpValues Loop
            usableHpRndNums.clear(); // reset for next use or not using anymore
            possibleHpValues.clear(); // reset for next use or not using anymore

            // Add mpValues in current level if they calculate into any hpValue in the level above
            possibleMpValues = new ArrayList<>(origHpMpLvlsValuesRndNums.get(mpLvlValuesIndex).get(lvlIndex));
            usableMpRndNums = new ArrayList<>(origHpMpLvlsValuesRndNums.get(mpLvlRndNumsIndex).get(lvlIndex));
            for (Integer mpValue: possibleMpValues){ // Each value in current lvl
                while (!valueAddedOrAllRndNumsExhausted){ // Each rndNum in current lvl
                    mpCalcValue = calculateMpLevelUpResult(characterName, lvl + 1, mpValue, usableMpRndNums.get(rndNumIndex));
                    // if next level contains the calculated lvlUp value using the current value... add the current value to the current level
                    if (lvlsMpValues.get(lvlIndex + 1).contains(mpCalcValue)){
                        lvlsMpValues.get(lvlIndex).add(mpValue);
                        valueAddedOrAllRndNumsExhausted = true;
                    }
                    // if no calculated lvlUp value could be found in the next level using the current value,
                    // do not add the value to current level, and move on to checking the next possible value in the current level
                    if (rndNumIndex == usableMpRndNums.size() - 1){
                        valueAddedOrAllRndNumsExhausted = true;
                    }
                    rndNumIndex++;
                } // end rndNums Loop
                valueAddedOrAllRndNumsExhausted = false; // reset for next use or not using anymore
                rndNumIndex = 0; // reset for next use or not using anymore
            } // end mpValues Loop
            usableMpRndNums.clear(); // reset for next use or not using anymore
            possibleMpValues.clear(); // reset for next use or not using anymore



        } // end lvl Loop

        // remove any hpValues at each lvl not being used to calculate into the next level
        boolean valueRemoved = false;
        while (!valueRemoved) {
            for (int lvlIndex = endLvlValuesIndex - 1, nextLvlIndex = endLvlValuesIndex, lvl = endLvl - 1; lvlIndex >= 0; lvlIndex--, nextLvlIndex--, lvl--) { // each level
                possibleHpValues = new ArrayList<>(lvlsHpValues.get(lvlIndex));
                usableHpRndNums = new ArrayList<>(origHpMpLvlsValuesRndNums.get(hpLvlRndNumsIndex).get(lvlIndex));
                possibleNextLvlHpValues = new ArrayList<>(lvlsHpValues.get(nextLvlIndex));
                for (Integer nextLvlHpValue : possibleNextLvlHpValues) {
                    if (!isNextLvlHpValueCompatibleWithCurrentLvlHpValues(characterName, possibleHpValues, usableHpRndNums, nextLvlHpValue, lvl + 1)) {
                        // remove nextLvlHpValue from next level
                        lvlsHpValues.get(nextLvlIndex).remove(lvlsHpValues.get(nextLvlIndex).indexOf(nextLvlHpValue));
                        valueRemoved = true;
                    }
                } // end nextLvlHpValues Loop
                possibleHpValues.clear(); // reset for next use or not using anymore
                usableHpRndNums.clear(); // reset for next use or not using anymore
                possibleNextLvlHpValues.clear(); // reset for next use or not using anymore

            }
            if (valueRemoved){
                valueRemoved = false;
            } else {
                valueRemoved = true;
            }
        }
        valueRemoved = false; // resets for mp value checking

        // remove any mpValues at each lvl not being used to calculate into the next level
        while (!valueRemoved) {
            for (int lvlIndex = endLvlValuesIndex - 1, nextLvlIndex = endLvlValuesIndex, lvl = endLvl - 1; lvlIndex >= 0; lvlIndex--, nextLvlIndex--, lvl--) { // each level
                possibleMpValues = new ArrayList<>(lvlsMpValues.get(lvlIndex));
                usableMpRndNums = new ArrayList<>(origHpMpLvlsValuesRndNums.get(mpLvlRndNumsIndex).get(lvlIndex));
                possibleNextLvlMpValues = new ArrayList<>(lvlsMpValues.get(nextLvlIndex));
                for (Integer nextLvlMpValue : possibleNextLvlMpValues) {
                    if (!isNextLvlMpValueCompatibleWithCurrentLvlMpValues(characterName, possibleMpValues, usableMpRndNums, nextLvlMpValue, lvl + 1)) {
                        // remove nextLvlMpValue from next level
                        lvlsMpValues.get(nextLvlIndex).remove(lvlsMpValues.get(nextLvlIndex).indexOf(nextLvlMpValue));
                        valueRemoved = true;
                    }
                } // end nextLvlHpValues Loop
                possibleMpValues.clear(); // reset for next use or not using anymore
                usableMpRndNums.clear(); // reset for next use or not using anymore
                possibleNextLvlMpValues.clear(); // reset for next use or not using anymore

            }
            if (valueRemoved){
                valueRemoved = false;
            } else {
                valueRemoved = true;
            }
        }

        updatedHpMpLvlsValuesRndNums.add(lvlsHpValues);
        updatedHpMpLvlsValuesRndNums.add(lvlsMpValues);
        updatedHpMpLvlsValuesRndNums.add(origHpMpLvlsValuesRndNums.get(hpLvlRndNumsIndex));
        updatedHpMpLvlsValuesRndNums.add(origHpMpLvlsValuesRndNums.get(mpLvlRndNumsIndex));

        return updatedHpMpLvlsValuesRndNums;
    }

    public static ArrayList<ArrayList<ArrayList<Integer>>> omitAndUpdateValuesWithSingleRndNumberEachLevel(String characterName, int startLvl, int endLvl,
                                                                                                              ArrayList<ArrayList<ArrayList<Integer>>> testHpMpRangedLevelValues){
        int hpLvlValuesIndex = 0; // index for hpMpRangedLevelValues
        int mpLvlValuesIndex = 1; // index for hpMpRangedLevelValues
        int hpLvlRndNumsIndex = 2; // index for hpMpRangedLevelValues
        int mpLvlRndNumsIndex = 3; // index for hpMpRangedLevelValues

        ArrayList<ArrayList<ArrayList<Integer>>> hpMpValuesRndNumsNotCompatible = new ArrayList<>();

        // For each level, remove rndNums that are used as the only rndNum for the other value...
        testHpMpRangedLevelValues = omitRndNumsEachLevelContainingSingleRndNumInOppositeHpMpValue(testHpMpRangedLevelValues);

        // Check if rndNums are empty for any levels, if so... return array as empty.
        for (int lvlIndex = 0; lvlIndex < testHpMpRangedLevelValues.get(hpLvlRndNumsIndex).size() - 1; lvlIndex++){
            if (testHpMpRangedLevelValues.get(hpLvlRndNumsIndex).get(lvlIndex).isEmpty() ||
                    testHpMpRangedLevelValues.get(mpLvlRndNumsIndex).get(lvlIndex).isEmpty()){
                return hpMpValuesRndNumsNotCompatible;
            }
        }

        // For each level, update values with updated usable rndNums
        testHpMpRangedLevelValues = updateLvlsHpMpValuesUsingRndNumsAndEndLvlValues(characterName, startLvl, endLvl, testHpMpRangedLevelValues);

        // Check if values are empty for any levels, if so... return array as empty.
        for (int lvlIndex = 0; lvlIndex < testHpMpRangedLevelValues.get(hpLvlValuesIndex).size(); lvlIndex++){
            if (testHpMpRangedLevelValues.get(hpLvlValuesIndex).get(lvlIndex).isEmpty() ||
                    testHpMpRangedLevelValues.get(mpLvlValuesIndex).get(lvlIndex).isEmpty()){
                return hpMpValuesRndNumsNotCompatible;
            }
        }

        return testHpMpRangedLevelValues;
    }

    public static boolean isCompatibleHpMpValuesRndNumsEmptyLvls(String characterName, int startLvl, int endLvl,
                                                                    ArrayList<ArrayList<ArrayList<Integer>>> testHpMpRangedLevelValues){

        int hpLvlValuesIndex = 0; // index for hpMpRangedLevelValues
        int mpLvlValuesIndex = 1; // index for hpMpRangedLevelValues
        int hpLvlRndNumsIndex = 2; // index for hpMpRangedLevelValues
        int mpLvlRndNumsIndex = 3; // index for hpMpRangedLevelValues


        for (int lvlIndex = 0; lvlIndex < testHpMpRangedLevelValues.get(hpLvlRndNumsIndex).size(); lvlIndex++){ // only checks until (endLvl - 1), since the last level contains no rndNums
            if (testHpMpRangedLevelValues.get(hpLvlValuesIndex).get(lvlIndex).isEmpty() ||
                    testHpMpRangedLevelValues.get(mpLvlValuesIndex).get(lvlIndex).isEmpty() ||
                    testHpMpRangedLevelValues.get(hpLvlRndNumsIndex).get(lvlIndex).isEmpty() ||
                    testHpMpRangedLevelValues.get(mpLvlRndNumsIndex).get(lvlIndex).isEmpty()){
                return true;
            }
        }

        // if values in last levels are empty, return true, else, return false
        return testHpMpRangedLevelValues.get(hpLvlValuesIndex).get(testHpMpRangedLevelValues.get(hpLvlValuesIndex).size() - 1).isEmpty() || testHpMpRangedLevelValues.get(mpLvlValuesIndex).get(testHpMpRangedLevelValues.get(mpLvlValuesIndex).size() - 1).isEmpty();
    }

    public static ArrayList<ArrayList<Integer>> createEmptyLvls(int startLvl, int endLvl){
        ArrayList<ArrayList<Integer>> emptyLeveledValues = new ArrayList<>();

        for (int lvlCounter = startLvl; lvlCounter <= endLvl; lvlCounter++){
            emptyLeveledValues.add(new ArrayList<Integer>());
        }

        return emptyLeveledValues;
    }

    public static int getLvlWithMostHpValues(ArrayList<ArrayList<Integer>> hpLvlValues, int startLvl){
        int lvlIndexWithMostValues = 0;

        for (int lvlIndex = 0; lvlIndex < hpLvlValues.size(); lvlIndex++){
            if (hpLvlValues.get(lvlIndex).size() >= hpLvlValues.get(lvlIndexWithMostValues).size()){
                lvlIndexWithMostValues = lvlIndex;
            }
        }
        return lvlIndexWithMostValues + startLvl;
    }

    public static int getLvlWithMostMpValues(ArrayList<ArrayList<Integer>> mpLvlValues, int startLvl){
        int lvlIndexWithMostValues = 0;

        for (int lvlIndex = 0; lvlIndex < mpLvlValues.size(); lvlIndex++){
            if (mpLvlValues.get(lvlIndex).size() >= mpLvlValues.get(lvlIndexWithMostValues).size()){
                lvlIndexWithMostValues = lvlIndex;
            }
        }
        return lvlIndexWithMostValues + startLvl;
    }

    public static int getFirstLvlWithMostHpMpValues(ArrayList<ArrayList<Integer>> hpRangedLeveledUsableValues, ArrayList<ArrayList<Integer>> mpRangedLeveledUsableValues, int startLvl){
        int lvlWithMostHpValues = getLvlWithMostHpValues(hpRangedLeveledUsableValues, startLvl); // used to find where the safe level is to hit any value.
        int lvlWithMostMpValues = getLvlWithMostMpValues(mpRangedLeveledUsableValues, startLvl); // used to find where the safe level is to hit any value.

        if (lvlWithMostHpValues <= lvlWithMostMpValues){
            return lvlWithMostHpValues;
        }
        return lvlWithMostMpValues;
    }

    public static boolean isLvlSafeForAllValues(String characterName, int startHp, int endHp, int startMp, int endMp,
                                                   ArrayList<ArrayList<Integer>> hpRangedLeveledUsableValues, ArrayList<ArrayList<Integer>> mpRangedLeveledUsableValues,
                                                   int testSafeLvl, int startLvl, int endLvl){
        // Summon function call showing all possible hp and mp values for the lvlWithMostValues,
            // Assign the results to two separate two dimensional arrays (hp and mp).
        int safeLvlTestingIndex = getLvlIndexFromCurrentLvl(testSafeLvl, startLvl);
        ArrayList<ArrayList<Integer>> allPossibleLvlsHpVals = getPossibleHpValuesAtAllLevels(characterName, startLvl, endLvl, startHp);
        ArrayList<ArrayList<Integer>> allPossibleLvlsMpVals = getPossibleMpValuesAtAllLevels(characterName, startLvl, endLvl, startMp);
        ArrayList<ArrayList<ArrayList<Integer>>> testHpMpRangedLevelValuesAndRndNums;

        // Test possibleLatestSafeLvl For Hp and Mp... to see if (allPossibleHpValsStartLvlToSafeLvl and allPossibleMpValsStartLvlToSafeLvl)
        // are the same as (hpRangedLeveledUsableValues and mpRangedLeveledUsableValues) up to the safeLvl in check
        if (!allPossibleLvlsHpVals.get(safeLvlTestingIndex).equals(hpRangedLeveledUsableValues.get(safeLvlTestingIndex))){
            return false;
        }
        if (!allPossibleLvlsMpVals.get(safeLvlTestingIndex).equals(mpRangedLeveledUsableValues.get(safeLvlTestingIndex))){
            return false;
        }
        // Now test all previous levels from possibleLatestSafeLvl
        for (int lvlIndex = 0; lvlIndex <= safeLvlTestingIndex; lvlIndex++){
            if (!allPossibleLvlsHpVals.get(lvlIndex).equals(hpRangedLeveledUsableValues.get(lvlIndex))){
                return false;
            }
            if (!allPossibleLvlsMpVals.get(lvlIndex).equals(mpRangedLeveledUsableValues.get(lvlIndex))){
                return false;
            }
        }


        // test each value combination at the level of lvlWithMostValues
            // if all combinations can be used, compare all values with an all possible values for this level (to ensure all values are indeed usable).
        // for each mpValue in mpRangedLeveledUsableValues.get(lvlIndexWithMostValues)
        for (Integer mpValue: mpRangedLeveledUsableValues.get(safeLvlTestingIndex)){ // each mpValue at possibleLatestSafeLvl
            for (Integer hpValue: hpRangedLeveledUsableValues.get(safeLvlTestingIndex)){ // each hpValue in conjunction with selected mp value at possibleLatestSafeLvl
                testHpMpRangedLevelValuesAndRndNums = combineHpMpSelectedRangedLvlsValuesAndRndNumsIfCompatible(characterName, testSafeLvl, endLvl, hpValue, endHp, mpValue, endMp);
                if (!isRangedLevelsHpMpValuesCompatibleAtEndLevel(characterName, testSafeLvl, endLvl, testHpMpRangedLevelValuesAndRndNums)){
                    return false;
                }
            }
        }

        // Every hpValue and mpValue at possibleLatestSafeLvl has been tested and passed, return true.
        return true;
    }

    public static int getSafeLvlWithAnyValueAcceptable(String characterName, int startHp, int endHp, int startMp, int endMp,
                                                          ArrayList<ArrayList<Integer>> hpRangedLeveledUsableValues, ArrayList<ArrayList<Integer>> mpRangedLeveledUsableValues,
                                                          int startLvl, int endLvl){
        int safeLvlTest = getFirstLvlWithMostHpMpValues(hpRangedLeveledUsableValues, mpRangedLeveledUsableValues, startLvl);
        boolean isLvlSafeForAllValues = isLvlSafeForAllValues(characterName, startHp, endHp, startMp, endMp, hpRangedLeveledUsableValues, mpRangedLeveledUsableValues, safeLvlTest, startLvl, endLvl);

        // Setup increment/decrement lvl to test
            // or return safeLvl (or 0 if there is no safe lvl) if the lvl cannot be incremented/decremented.
        if (safeLvlTest == startLvl || safeLvlTest == endLvl) {
            if (isLvlSafeForAllValues) {
                return safeLvlTest; // return safe lvl
            }
            return 0; // no safe level
        }

        // lvl was safe, increment lvls until safe lvl not found, then return latest safe lvl.
        while (isLvlSafeForAllValues && safeLvlTest <= endLvl){
            safeLvlTest++;
            if (safeLvlTest > endLvl){
                return safeLvlTest - 1; // return if last lvl if endLvl was tested and found safe
            }
            isLvlSafeForAllValues = isLvlSafeForAllValues(characterName, startHp, endHp, startMp, endMp, hpRangedLeveledUsableValues, mpRangedLeveledUsableValues, safeLvlTest, startLvl, endLvl);
            if (!isLvlSafeForAllValues){ // next lvl was not a safe lvl, return latest safe lvl (current lvl)
                return safeLvlTest - 1;
            }
        }

        // Reached if only "isLvlSafeForAllValues" was found false on the first test.
        // lvl was not safe, decrement lvl until safe lvl is found or until at start lvl.
        safeLvlTest--;
        while (safeLvlTest >= startLvl){
            isLvlSafeForAllValues = isLvlSafeForAllValues(characterName, startHp, endHp, startMp, endMp, hpRangedLeveledUsableValues, mpRangedLeveledUsableValues, safeLvlTest, startLvl, endLvl);
            if (isLvlSafeForAllValues){
                return safeLvlTest;
            }
            safeLvlTest--;
        }
        return 0;
    }

    public static ArrayList<Integer> getAndOmitRndNumOfMpRndNums(ArrayList<Integer> originalMpRndNums, int omitRndNum){
        ArrayList<Integer> newMpRndNums = new ArrayList<>();

        for (Integer rndNum: originalMpRndNums){
            newMpRndNums.add(rndNum);
        }

        if (newMpRndNums.contains(omitRndNum)){
            newMpRndNums.remove(newMpRndNums.indexOf(omitRndNum));
        }

        return newMpRndNums;
    }

    public static ArrayList<Integer> getAndOmitRndNumOfHpRndNums(ArrayList<Integer> originalHpRndNums, int omitRndNum){
        ArrayList<Integer> newHpRndNums = new ArrayList<>();

        for (Integer rndNum: originalHpRndNums){
            newHpRndNums.add(rndNum);
        }

        if (newHpRndNums.contains(omitRndNum)){
            newHpRndNums.remove(newHpRndNums.indexOf(omitRndNum));
        }

        return newHpRndNums;
    }

    public static int getFirstLvlIndexWithNonEmptyValueInTwoDimArray(ArrayList<ArrayList<Integer>> arrayWithValues){
        // returns 0 if full array is empty
        int latestLvlIndexWithMpValue = arrayWithValues.size() - 1;
        if (arrayWithValues.isEmpty()){
            return 0;
        }

        for (; latestLvlIndexWithMpValue >= 0; latestLvlIndexWithMpValue--){
            if (arrayWithValues.get(latestLvlIndexWithMpValue).isEmpty()){
                return (latestLvlIndexWithMpValue + 1);
            }
        }

        return latestLvlIndexWithMpValue;
    }

    public static int getLatestLvlIndexWithEmptyValueInTwoDimArray(ArrayList<ArrayList<Integer>> arrayWithValues){
        // returns 0 if no levels are empty
        int latestLvl = arrayWithValues.size() - 1;

        for (; latestLvl >= 0; latestLvl--){
            if (arrayWithValues.get(latestLvl).isEmpty()){
                return latestLvl;
            }
        }

        return 0;
    }

    public static ArrayList<Integer> getLvlPossibleValuesAfterCurrentValueFound(ArrayList<Integer> selectedLvlValues, Integer selectedValue){
        ArrayList<Integer> lvlPossibleValues = new ArrayList<>();

        for (int valueIndex = selectedLvlValues.indexOf(selectedValue) + 1; valueIndex < selectedLvlValues.size(); valueIndex++){
            lvlPossibleValues.add(selectedLvlValues.get(valueIndex));
        }

        return lvlPossibleValues;
    }

    public static ArrayList<Integer> getLvlPossibleValuesAtAndBeforeCurrentValueFound(ArrayList<Integer> selectedLvlValues, Integer selectedValue){
        ArrayList<Integer> lvlPossibleValues = new ArrayList<>();

        for (Integer value: selectedLvlValues){
            lvlPossibleValues.add(value);
            if (value.equals(selectedValue)){
                return lvlPossibleValues;
            }
        }

        return lvlPossibleValues;
    }

    public static ArrayList<Integer> getLvlPossibleValuesBeforeCurrentValueFound(ArrayList<Integer> selectedLvlValues, Integer selectedValue){
        ArrayList<Integer> lvlPossibleValues = new ArrayList<>();

        for (Integer value: selectedLvlValues){
            if (!value.equals(selectedValue)) {
                lvlPossibleValues.add(value);
            }
            if (value.equals(selectedValue)){
                return lvlPossibleValues;
            }
        }

        return lvlPossibleValues;
    }

    public static ArrayList<Integer> getLvlPossibleRndNumsAfterCurrentRndNumFound(ArrayList<Integer> selectedRndNums, Integer selectedRndNum){
        ArrayList<Integer> lvlPossibleRndNums = new ArrayList<>();

        for (int valueIndex = selectedRndNums.indexOf(selectedRndNum) + 1; valueIndex < selectedRndNums.size(); valueIndex++){
            lvlPossibleRndNums.add(selectedRndNums.get(valueIndex));
        }

        return lvlPossibleRndNums;
    }

    public static ArrayList<Integer> getRndNumsUpToCurrentRndNum(ArrayList<Integer> selectedRndNums, Integer selectedRndNum){
        ArrayList<Integer> lvlPossibleRndNums = new ArrayList<>();

        for(Integer rndNum: selectedRndNums){
            if (rndNum.equals(selectedRndNum)){
                return lvlPossibleRndNums;
            }
            lvlPossibleRndNums.add(rndNum);
        }
        return lvlPossibleRndNums;
    }

    public static ArrayList<Integer> getPossibleRndNumsExceptCurrentRndNum(ArrayList<Integer> selectedRndNums, Integer selectedRndNum){
        ArrayList<Integer> lvlPossibleRndNums = new ArrayList<>();

        for(Integer rndNum: selectedRndNums){
            if (!rndNum.equals(selectedRndNum)){
                lvlPossibleRndNums.add(rndNum);
            }
        }
        return lvlPossibleRndNums;
    }

    public static int getCurrentLvlFromLvlIndex(int lvlIndex, int startLvl){
        return lvlIndex + startLvl;
    }

    public static int getLvlIndexFromCurrentLvl(int currentLvl, int startLvl){
        return currentLvl - startLvl;
    }

    public static ArrayList<ArrayList<Integer>> incrementNextPossibleMpValue(ArrayList<ArrayList<ArrayList<Integer>>> hpMpRangedLeveledUsableValues,
                                                                                ArrayList<ArrayList<Integer>> hpSelectedLvlsValues, ArrayList<ArrayList<Integer>> mpSelectedLvlsValues,
                                                                                int startLvl, String characterName){
        int mpLvlValuesIndex = 1;
        int mpLvlRndNumsIndex = 3;
        int selectedValIndex = 0;
        int selectedRndNumIndex = 1;
        int lastestLvlIndexWithMpValue = getFirstLvlIndexWithNonEmptyValueInTwoDimArray(mpSelectedLvlsValues);
        int lvl = getCurrentLvlFromLvlIndex(lastestLvlIndexWithMpValue, startLvl);
        ArrayList<Integer> currentLvlPossibleMpValuesNotChecked;
        ArrayList<Integer> currentLvlPossibleMpRndNumsNotChecked;

        int tmpCalcMpVal;
        for (int lvlIndex = lastestLvlIndexWithMpValue; lvlIndex < mpSelectedLvlsValues.size() - 1; lvlIndex++, lvl++){ // level
            currentLvlPossibleMpValuesNotChecked = getLvlPossibleValuesAfterCurrentValueFound(hpMpRangedLeveledUsableValues.get(mpLvlValuesIndex).get(lvlIndex), mpSelectedLvlsValues.get(lvlIndex).get(selectedValIndex));
            for (int mpValue: currentLvlPossibleMpValuesNotChecked){ // value in possible values not yet checked
                currentLvlPossibleMpRndNumsNotChecked = getLvlPossibleRndNumsAfterCurrentRndNumFound(hpMpRangedLeveledUsableValues.get(mpLvlRndNumsIndex).get(lvlIndex), mpSelectedLvlsValues.get(lvlIndex).get(selectedRndNumIndex));
                if (currentLvlPossibleMpRndNumsNotChecked.contains(hpSelectedLvlsValues.get(lvlIndex).get(selectedRndNumIndex))){
                    currentLvlPossibleMpRndNumsNotChecked.remove(currentLvlPossibleMpRndNumsNotChecked.indexOf(hpSelectedLvlsValues.get(lvlIndex).get(selectedRndNumIndex)));
                }
                for (int rndNum: currentLvlPossibleMpRndNumsNotChecked){
                    tmpCalcMpVal = calculateMpLevelUpResult(characterName, lvl + 1, mpValue, rndNum);
                    if (mpSelectedLvlsValues.get(lvlIndex + 1).get(selectedValIndex).equals(tmpCalcMpVal)){
                        mpSelectedLvlsValues.get(lvlIndex).clear();
                        mpSelectedLvlsValues.get(lvlIndex).add(mpValue);
                        mpSelectedLvlsValues.get(lvlIndex).add(rndNum);
                        return mpSelectedLvlsValues;
                    }
                }
            }
            mpSelectedLvlsValues.get(lvlIndex).clear();
        }

        return mpSelectedLvlsValues;
    }

    public static ArrayList<ArrayList<Integer>> FillPreviousMpValuesUntilDesiredLvlIndexReached(ArrayList<ArrayList<ArrayList<Integer>>> testHpMpRangedLevelValues,
                                                                                ArrayList<ArrayList<Integer>> hpSelectedLvlsValues, ArrayList<ArrayList<Integer>> mpSelectedLvlsValues,
                                                                                int firstDesiredLvlIndexToFill, int firstEmptyMpValCurrentLvl, int startLvl, int endLvl, String characterName) {
        ArrayList<Integer> possibleMpLvlValues;
        ArrayList<Integer> possibleMpLvlRndNums;

        int mpLvlValuesIndex = 1;
        int mpLvlRndNumsIndex = 3;
        int selectedValIndex = 0;
        int selectedRndNumIndex = 1;
        int lastLvlIndexWithEmptyValue = getFirstLvlIndexWithNonEmptyValueInTwoDimArray(mpSelectedLvlsValues) - 1;
        int lvl = getCurrentLvlFromLvlIndex(lastLvlIndexWithEmptyValue, startLvl);
        int tmpCalcMpVal;
        for (int lvlIndex = lastLvlIndexWithEmptyValue; lvlIndex >= firstDesiredLvlIndexToFill; lvlIndex--, lvl--){ // level -> decremented
            possibleMpLvlValues = new ArrayList<>(testHpMpRangedLevelValues.get(mpLvlValuesIndex).get(lvlIndex));
            for (int mpValueIndex = 0; mpValueIndex < possibleMpLvlValues.size(); mpValueIndex++){ // Value -> starting at the first value
                possibleMpLvlRndNums = new ArrayList<>(testHpMpRangedLevelValues.get(mpLvlRndNumsIndex).get(lvlIndex));

                // Remove rndNum Used in selectedHpValues array, at this same level
                if (possibleMpLvlRndNums.contains(hpSelectedLvlsValues.get(lvlIndex).get(selectedRndNumIndex))){
                    possibleMpLvlRndNums.remove(possibleMpLvlRndNums.indexOf(hpSelectedLvlsValues.get(lvlIndex).get(selectedRndNumIndex)));
                }
                for(int rndNumIndex = 0; rndNumIndex < possibleMpLvlRndNums.size(); rndNumIndex++){ // rndNum -> starting at the first rndNum
                    tmpCalcMpVal = calculateMpLevelUpResult(characterName, lvl + 1, possibleMpLvlValues.get(mpValueIndex), possibleMpLvlRndNums.get(rndNumIndex));
                    if (mpSelectedLvlsValues.get(lvlIndex + 1).get(selectedValIndex).equals(tmpCalcMpVal)){
                        mpSelectedLvlsValues.get(lvlIndex).add(possibleMpLvlValues.get(mpValueIndex));
                        mpSelectedLvlsValues.get(lvlIndex).add(possibleMpLvlRndNums.get(rndNumIndex));
                        rndNumIndex = possibleMpLvlRndNums.size() - 1; // exit rndNum Loop
                        mpValueIndex = possibleMpLvlValues.size() - 1; // exit mpValue Loop
                    }
                }
                possibleMpLvlRndNums.clear();
            }
            possibleMpLvlValues.clear();
            if (mpSelectedLvlsValues.get(lvlIndex).isEmpty()){
                return mpSelectedLvlsValues;
            }
        }

        return mpSelectedLvlsValues;
    }

    public static ArrayList<ArrayList<Integer>> incrementNextPossibleMpValueAndFillPreviousValues(ArrayList<ArrayList<ArrayList<Integer>>> testHpMpRangedLevelValues,
                                                                                    ArrayList<ArrayList<Integer>> hpSelectedLvlsValues, ArrayList<ArrayList<Integer>> mpSelectedLvlsValues,
                                                                                    int firstEmptyMpValLvlIndex, int firstEmptyMpValCurrentLvl, int startLvl, int endLvl, String characterName){

        while(mpSelectedLvlsValues.get(firstEmptyMpValLvlIndex).isEmpty()) {
            // Try to increment next possible value in array, only if the 2nd to last level of the array does not have empty values.
            if (!mpSelectedLvlsValues.get(mpSelectedLvlsValues.size() - 2).isEmpty()) {
                mpSelectedLvlsValues = incrementNextPossibleMpValue(testHpMpRangedLevelValues, hpSelectedLvlsValues, mpSelectedLvlsValues, startLvl, characterName);

                // If no value could be incremented, then a value in the HP array needs to be decremented. So just return this array as empty with only the last level value filled in.
                if (mpSelectedLvlsValues.get(mpSelectedLvlsValues.size() - 2).isEmpty()) {
                    return mpSelectedLvlsValues;
                }
            }
            // fill previous values up till the firstEmptyMpValLvlIndex (or as far as it can).
            mpSelectedLvlsValues = FillPreviousMpValuesUntilDesiredLvlIndexReached(testHpMpRangedLevelValues, hpSelectedLvlsValues, mpSelectedLvlsValues, firstEmptyMpValLvlIndex, firstEmptyMpValCurrentLvl, startLvl, endLvl, characterName);
        }

        return mpSelectedLvlsValues;
    }

    public static ArrayList<ArrayList<Integer>> decrementHpValOnce(ArrayList<ArrayList<ArrayList<Integer>>> testHpMpRangedLevelValues,
                                                                                   ArrayList<ArrayList<Integer>> hpSelectedLvlsValues,
                                                                                   int firstEmptyHpValLvlIndex, int firstEmptyHpValCurrentLvl, int startLvl, int endLvl, String characterName){
        int hpLvlValuesIndex = 0;
        int hpLvlRndNumsIndex = 2;
        int selectedValIndex = 0;
        int lvlIndex = getFirstLvlIndexWithNonEmptyValueInTwoDimArray(hpSelectedLvlsValues);
        int lvl = getCurrentLvlFromLvlIndex(lvlIndex, startLvl);
        int tmpCalcHpVal;
        boolean rndNumOrValueChanged = false;
        ArrayList<Integer> possibleHpLvlValues;
        ArrayList<Integer> possibleHpLvlRndNums;

        /********************************************************************************
         ********************************************************************************
         ********************************************************************************
         ** Decrement hpValue by 1, clear ever level values not able to be decremented **
         ********************************************************************************
         ********************************************************************************
         ********************************************************************************/
        for(; lvlIndex < hpSelectedLvlsValues.size() - 1; lvlIndex++, lvl++) { // Level
            // get a list of hpValues before the hpValue used in hpSelectedLvlsValues at current level
            possibleHpLvlValues = getLvlPossibleValuesBeforeCurrentValueFound(testHpMpRangedLevelValues.get(hpLvlValuesIndex).get(lvlIndex), hpSelectedLvlsValues.get(lvlIndex).get(selectedValIndex));
            for (int hpValueIndex = possibleHpLvlValues.size() - 1; hpValueIndex >= 0; hpValueIndex--){
                possibleHpLvlRndNums = new ArrayList<>(testHpMpRangedLevelValues.get(hpLvlRndNumsIndex).get(lvlIndex)); // copy as pass by value
                for (int rndNumIndex = possibleHpLvlRndNums.size() - 1; rndNumIndex >= 0; rndNumIndex--) {
                    // Calculate next level value
                    tmpCalcHpVal = calculateHpLevelUpResult(characterName, lvl + 1, possibleHpLvlValues.get(hpValueIndex), possibleHpLvlRndNums.get(rndNumIndex));
                    if (hpSelectedLvlsValues.get(lvlIndex + 1).get(selectedValIndex).equals(tmpCalcHpVal)) {
                        hpSelectedLvlsValues.get(lvlIndex).clear();
                        hpSelectedLvlsValues.get(lvlIndex).add(possibleHpLvlValues.get(hpValueIndex));
                        hpSelectedLvlsValues.get(lvlIndex).add(possibleHpLvlRndNums.get(rndNumIndex));
                        rndNumOrValueChanged = true;
                        rndNumIndex = 0; // Exit rndNums Loop
                        hpValueIndex = 0; // Exit hpValues Loop
                    }
                }
                possibleHpLvlRndNums.clear(); // resets for next rndNums loop or rest of method
            }
            possibleHpLvlValues.clear(); // resets for next hpValue loop or rest of method
            if (!rndNumOrValueChanged){ // No rndNum or hpValue was changed
                // Clear the value/rndNum used in hpSelectedLvlsValues at current level
                hpSelectedLvlsValues.get(lvlIndex).clear();
            } else { // rndNum or hpValue was changed
                return hpSelectedLvlsValues;
            }
        }
        return hpSelectedLvlsValues;
    }

    public static ArrayList<ArrayList<Integer>> decrementAndFillHpValRndNumOnce(ArrayList<ArrayList<ArrayList<Integer>>> testHpMpRangedLevelValues,
                                                                            ArrayList<ArrayList<Integer>> hpSelectedLvlsValues, int firstLvlRndNumToNotUse,
                                                                            int firstEmptyHpValLvlIndex, int firstEmptyHpValCurrentLvl, int startLvl, int endLvl, String characterName){

        int hpLvlValuesIndex = 0;
        int hpLvlRndNumsIndex = 2;
        int selectedValIndex = 0;
        int selectedRndNumIndex = 1;
        int lvlIndex = firstEmptyHpValLvlIndex;
        int tmpLvlIndexHolder = lvlIndex;
        int lvl = firstEmptyHpValCurrentLvl;
        int tmpLvlHolder = lvl;
        int tmpCalcHpVal;
        boolean rndNumOrValueChanged = false;
        boolean rndNumsOrValuesDecrementedAgain = false;
        ArrayList<Integer> possibleHpLvlValues;
        ArrayList<Integer> possibleHpLvlRndNums;

        /******************************************************************************************
         ******************************************************************************************
         ******************************************************************************************
         ** Decrement rndNum or hpValue by 1, clear ever level values not able to be decremented **
         ******************************************************************************************
         ******************************************************************************************
         ******************************************************************************************/
        for(; lvlIndex < hpSelectedLvlsValues.size() - 1; lvlIndex++, lvl++){ // Level
            // get a list of hpValues up to (and including) the hpValue used in hpSelectedLvlsValues at current level
            possibleHpLvlValues = getLvlPossibleValuesAtAndBeforeCurrentValueFound(testHpMpRangedLevelValues.get(hpLvlValuesIndex).get(lvlIndex), hpSelectedLvlsValues.get(lvlIndex).get(selectedValIndex));
            for (int hpValueIndex = possibleHpLvlValues.size() - 1; hpValueIndex >= 0 ;hpValueIndex--) { // hpValue - From Maximum to Minimum, starting at the selected value
                if (possibleHpLvlValues.get(hpValueIndex).equals(hpSelectedLvlsValues.get(lvlIndex).get(selectedValIndex))) { // if Value in check is same as value in hpSelectedLvlsValues
                    // Only use the rndNums up to the rndNum already used in selected
                    possibleHpLvlRndNums = getRndNumsUpToCurrentRndNum(testHpMpRangedLevelValues.get(hpLvlRndNumsIndex).get(lvlIndex), hpSelectedLvlsValues.get(lvlIndex).get(selectedRndNumIndex));
                } else if (lvlIndex == firstEmptyHpValLvlIndex) { // On first level, and value in check does not equal to the value in hpSelectedLvlsValues.
                    //  use all available rndNums except the one already used in selected
                    possibleHpLvlRndNums = getPossibleRndNumsExceptCurrentRndNum(testHpMpRangedLevelValues.get(hpLvlRndNumsIndex).get(lvlIndex), firstLvlRndNumToNotUse);
                } else { // Not on first level, and value in check does not equal to the value in hpSelectedLvlsValues.
                    // use all available rndNums (including the one in hpSelectedLvlsValues)
                    possibleHpLvlRndNums = new ArrayList<>(testHpMpRangedLevelValues.get(hpLvlRndNumsIndex).get(lvlIndex)); // copy as pass by value
                }
                for (int rndNumIndex = possibleHpLvlRndNums.size() - 1; rndNumIndex >= 0; rndNumIndex--) {
                    // Calculate next level value
                    tmpCalcHpVal = calculateHpLevelUpResult(characterName, lvl + 1, possibleHpLvlValues.get(hpValueIndex), possibleHpLvlRndNums.get(rndNumIndex));
                    if (hpSelectedLvlsValues.get(lvlIndex + 1).get(selectedValIndex).equals(tmpCalcHpVal)){
                        hpSelectedLvlsValues.get(lvlIndex).clear();
                        hpSelectedLvlsValues.get(lvlIndex).add(possibleHpLvlValues.get(hpValueIndex));
                        hpSelectedLvlsValues.get(lvlIndex).add(possibleHpLvlRndNums.get(rndNumIndex));
                        rndNumOrValueChanged = true;
                        rndNumIndex = 0; // Exit rndNums Loop
                        hpValueIndex = 0; // Exit hpValues Loop
                    }
                }
                possibleHpLvlRndNums.clear(); // resets for next rndNums loop or rest of method
            }
            possibleHpLvlValues.clear(); // resets for next hpValue loop or rest of method
            if (!rndNumOrValueChanged){ // No rndNum or hpValue was changed
                // Clear the value/rndNum used in hpSelectedLvlsValues at current level
                hpSelectedLvlsValues.get(lvlIndex).clear();
            } else { // rndNum or hpValue was changed
                if (lvlIndex == firstEmptyHpValLvlIndex){ // On first level
                    // since only the rndNum or hpValue was changed on the first level,
                        // no values were cleared, thus we don't need to fill in previous level values,
                        // and can just return the hpSelectedLvlsValues as is.
                    return hpSelectedLvlsValues;
                } else { // Not on first level
                    // Save (lvlIndex - 1), and exit level loop
                    tmpLvlIndexHolder = lvlIndex - 1; // Save (lvlIndex - 1) for filling in values cleared on previous levels
                    tmpLvlHolder = lvl - 1;
                    lvlIndex = hpSelectedLvlsValues.size() - 1; // Exits level loop
                    lvl = endLvl; // Keeps current with lvlIndex
                }
            }
        }
        lvlIndex = tmpLvlIndexHolder; // latest level with empty hpValue/rndNum to fill in
        lvl = tmpLvlHolder; // runs in conjunction with lvlIndex
        // If rndNum or hpValue was not changed, return hpSelectedLvlsValues
        if (!rndNumOrValueChanged) {
            return hpSelectedLvlsValues;
        } else { // rndNum or hpValue was changed
            rndNumOrValueChanged = false; // reset
        }

        /************************************************************************************************
         ************************************************************************************************
         ************************************************************************************************
         ** Fill in previous levels with values cleared from maximum to minimum on rndNums and Values, **
         ** Up until the firstEmptyHpValLvlIndex is reached *********************************************
         ************************************************************************************************
         ************************************************************************************************
         ***********************************************************************************************/
        while (hpSelectedLvlsValues.get(firstEmptyHpValLvlIndex).isEmpty()) {
            if (isDebuggerOn){
                //System.out.println("lvl = " + lvl);
            }
            // lvlIndex should be latest level with empty values in hpSelectedLvlsValues
            for (; lvlIndex >= firstEmptyHpValLvlIndex; lvlIndex--, lvl--) {
                possibleHpLvlValues = new ArrayList<>(testHpMpRangedLevelValues.get(hpLvlValuesIndex).get(lvlIndex));
                for (int hpValueIndex = possibleHpLvlValues.size() - 1; hpValueIndex >= 0; hpValueIndex--) {
                    if (lvlIndex == firstEmptyHpValLvlIndex && !rndNumsOrValuesDecrementedAgain) { // On first level and further levels hpValues/rndNums have not been decremented
                        // get all possible rndNums excluding the firstLvlRndNumToNotUse
                        possibleHpLvlRndNums = getPossibleRndNumsExceptCurrentRndNum(testHpMpRangedLevelValues.get(hpLvlRndNumsIndex).get(lvlIndex), firstLvlRndNumToNotUse);
                    } else { // Not on first level or further levels hpValues/rndNums have been decremented.
                        // get all possible rndNums to use
                        possibleHpLvlRndNums = new ArrayList<>(testHpMpRangedLevelValues.get(hpLvlRndNumsIndex).get(lvlIndex));
                    }
                    for (int rndNumIndex = possibleHpLvlRndNums.size() - 1; rndNumIndex >= 0; rndNumIndex--) {
                        tmpCalcHpVal = calculateHpLevelUpResult(characterName, lvl + 1, possibleHpLvlValues.get(hpValueIndex), possibleHpLvlRndNums.get(rndNumIndex));
                        if (hpSelectedLvlsValues.get(lvlIndex + 1).get(selectedValIndex).equals(tmpCalcHpVal)) {
                            hpSelectedLvlsValues.get(lvlIndex).add(possibleHpLvlValues.get(hpValueIndex));
                            hpSelectedLvlsValues.get(lvlIndex).add(possibleHpLvlRndNums.get(rndNumIndex));
                            rndNumIndex = 0; // Exit rndNums Loop
                            hpValueIndex = 0; // Exit hpValues Loop
                        }
                    }
                    possibleHpLvlRndNums.clear();
                }
                possibleHpLvlValues.clear();
                if (hpSelectedLvlsValues.get(lvlIndex).isEmpty()){
                    lvlIndex = firstEmptyHpValLvlIndex; // Exit level loop, and prepare to decrement HP value in further levels by 1
                    lvl = getCurrentLvlFromLvlIndex(lvlIndex, startLvl); // keeps concurrent with lvlIndex
                }
            }
            // note: lvlIndex and lvl is now outside of scope (1 level below the first level to change values of this method)
            if (hpSelectedLvlsValues.get(firstEmptyHpValLvlIndex).isEmpty()){ // if was not able to fill value at current level
                hpSelectedLvlsValues = decrementHpValOnce(testHpMpRangedLevelValues, hpSelectedLvlsValues, firstEmptyHpValLvlIndex, firstEmptyHpValCurrentLvl, startLvl, endLvl, characterName);
                rndNumsOrValuesDecrementedAgain = true;
                lvlIndex = getLatestLvlIndexWithEmptyValueInTwoDimArray(hpSelectedLvlsValues); // Get latest level with no values, and get ready to fill in values up till firstEmptyHpValLvlIndex
                lvl = getCurrentLvlFromLvlIndex(lvlIndex, startLvl); // keeps concurrent with lvlIndex
            }
            if (hpSelectedLvlsValues.get(hpSelectedLvlsValues.size() - 2).isEmpty()){ // could not fill in value on the level before last
                // return array as empty with only last level value filled in.
                return hpSelectedLvlsValues;
            }
        }
        return hpSelectedLvlsValues;
    }

    public static boolean isRangedLevelsHpMpValuesCompatibleAtEachLevel(ArrayList<ArrayList<ArrayList<Integer>>> testHpMpRangedLevelValues,
                                                                                                                       int startLvl, int endLvl,
                                                                                                                       String characterName){
        /* Precondition...
            2nd to last levels of hp/mp values must at least be compatible with their rndNum/Values choices.
        */
        int testLvlCounter = 1;
        int testWhileCounter = 1;
        boolean debuggerOn = true;
        boolean localDebuggerOn = false;

        int hpLvlValuesIndex = 0;
        int mpLvlValuesIndex = 1;
        int hpLvlRndNumsIndex = 2;
        int mpLvlRndNumsIndex = 3;
        int selectedValIndex = 0;
        int selectedRndNumIndex = 1;
        ArrayList<ArrayList<Integer>> hpSelectedLvlsValues = createEmptyLvls(startLvl, endLvl); // Will later contain one value and one rnd number for each level
        ArrayList<ArrayList<Integer>> mpSelectedLvlsValues = createEmptyLvls(startLvl, endLvl); // Will later contain one value and one rnd number for each level
        ArrayList<Integer> mpLvlRndNums = new ArrayList<>();

        // add in final value in final level
        hpSelectedLvlsValues.get(hpSelectedLvlsValues.size() - 1).add(testHpMpRangedLevelValues.get(hpLvlValuesIndex).get(hpSelectedLvlsValues.size() - 1).get(0));
        mpSelectedLvlsValues.get(mpSelectedLvlsValues.size() - 1).add(testHpMpRangedLevelValues.get(mpLvlValuesIndex).get(mpSelectedLvlsValues.size() - 1).get(0));

        /* Debugging:
        int lvlDisplay = startLvl;
        for (ArrayList<Integer> hpValues: testHpMpRangedLevelValues.get(hpLvlValuesIndex)){
            System.out.println("*************************************** Lvl(" + lvlDisplay + ") ***************************************");
            System.out.println(hpValues);
            lvlDisplay++;
        }
        */

        // Start filling in values in arrays here at level before the last level (incrementing down levels)
        int currentLvl = endLvl - 1;
        Integer tmpHpVal;
        int tmpHpRndNum;
        int tmpCalcHpVal;
        Integer tmpMpVal;
        int tmpMpRndNum;
        int tmpCalcMpVal;
        int firstLvlRndNumToNotUse;
        for (int lvlIndex = hpSelectedLvlsValues.size() - 2; lvlIndex >= 0; lvlIndex--, currentLvl--){ // levels
            for (int hpValIndex = testHpMpRangedLevelValues.get(hpLvlValuesIndex).get(lvlIndex).size() - 1; hpValIndex >= 0; hpValIndex--){ // hpValues - from highest to lowest
                tmpHpVal = testHpMpRangedLevelValues.get(hpLvlValuesIndex).get(lvlIndex).get(hpValIndex);
                for (int hpRndNumIndex = testHpMpRangedLevelValues.get(hpLvlRndNumsIndex).get(lvlIndex).size() - 1; hpRndNumIndex >= 0; hpRndNumIndex--){ // hpRndNums - from highest to lowest
                    tmpHpRndNum = testHpMpRangedLevelValues.get(hpLvlRndNumsIndex).get(lvlIndex).get(hpRndNumIndex);
                    tmpCalcHpVal = calculateHpLevelUpResult(characterName, currentLvl + 1, tmpHpVal, tmpHpRndNum);
                    if (hpSelectedLvlsValues.get(lvlIndex + 1).get(selectedValIndex).equals(tmpCalcHpVal)){
                        hpSelectedLvlsValues.get(lvlIndex).add(tmpHpVal); // add value for for next level value
                        hpSelectedLvlsValues.get(lvlIndex).add(tmpHpRndNum); // add rndNumber used for next level value
                        mpLvlRndNums = getAndOmitRndNumOfMpRndNums(testHpMpRangedLevelValues.get(mpLvlRndNumsIndex).get(lvlIndex), tmpHpRndNum);
                        hpRndNumIndex = 0; // exit hpRndNums for loop (since rndNumber was found to be usable and added)
                        hpValIndex = 0; // exit value for loop (since value has been found to be usable and added)
                    }
                }
            }
            // cross-check add mp values here...
            for (int mpValIndex = 0; mpValIndex < testHpMpRangedLevelValues.get(mpLvlValuesIndex).get(lvlIndex).size(); mpValIndex++){
                tmpMpVal = testHpMpRangedLevelValues.get(mpLvlValuesIndex).get(lvlIndex).get(mpValIndex);
                for (int mpRndNumIndex = 0; mpRndNumIndex < mpLvlRndNums.size(); mpRndNumIndex++){
                    tmpMpRndNum = mpLvlRndNums.get(mpRndNumIndex);
                    tmpCalcMpVal = calculateMpLevelUpResult(characterName, currentLvl + 1, tmpMpVal, tmpMpRndNum);
                    if (tmpCalcMpVal == mpSelectedLvlsValues.get(lvlIndex + 1).get(selectedValIndex)){
                        mpSelectedLvlsValues.get(lvlIndex).add(tmpMpVal); // add value for for next level value
                        mpSelectedLvlsValues.get(lvlIndex).add(tmpMpRndNum); // add rndNumber used for next level value
                        mpLvlRndNums.clear();
                        mpRndNumIndex = 0; // exit hpRndNums for loop (since rndNumber was found to be usable and added)
                        mpValIndex = 0; // exit value for loop (since value has been found to be usable and added)
                    }
                }
            }

            // If no mp cross value could be found in current level, Try first to increment mp value by 1 until and fill in all previous values until current level.
            // If MP values could not be incremented/filled-in, decrement/fill in HP rndNum or Value used by 1, and try to fill-in/increment MP Values under the next while loop cycle.
            // If MP values still could not be incremented/filled-in, repeat process of comment line above this one until HP values can no longer be decremented.
                // or until MP and HP values have all been filled in (meaning all values are compatible, exit the while loop.
            // If HP values can no longer be decremented/filled-in, return empty array (or boolean compatible as false).
            while (mpSelectedLvlsValues.get(lvlIndex).isEmpty()){ // increment mp values only until they can't be incremented anymore or until empty level value has been filled
                // Note: (level 6 to 99 for cloud with hp/mp end values 9511/903),
                    //  it loops here 134,464 times before finding compatible hp/mp matches between levels 86 to 99. (roughly 5-10 seconds execution time)
                    // it loops here 1,071,467 times before finding no compatible combinations for hp/mp matches between lvl 85 to 99. (roughly 2 minutes execution time)
                    // ... So this will take roughly at least 2 minutes before it can finish in execution time... if the hp/mp end level values are not compatible
                mpSelectedLvlsValues = incrementNextPossibleMpValueAndFillPreviousValues(testHpMpRangedLevelValues, hpSelectedLvlsValues, mpSelectedLvlsValues, lvlIndex, currentLvl, startLvl, endLvl, characterName);

                // If MP Values could not be incremented by 1 to the point where current level MP value could not fill in.(meaning mp value in array at current level is empty),
                    // decrement hp rndNum/value by 1 in hpArray.
                if (mpSelectedLvlsValues.get(lvlIndex).isEmpty()) { // current level has no mp value
                    firstLvlRndNumToNotUse = hpSelectedLvlsValues.get(lvlIndex).get(selectedRndNumIndex); // helps if only first level value is changed, to not use the same rndNum
                    hpSelectedLvlsValues = decrementAndFillHpValRndNumOnce(testHpMpRangedLevelValues, hpSelectedLvlsValues, firstLvlRndNumToNotUse, lvlIndex, currentLvl, startLvl, endLvl, characterName);
                    // If HP values could not be decremented by 1, return array as empty.
                    if (hpSelectedLvlsValues.get(hpSelectedLvlsValues.size() - 2).isEmpty()){
                        return false; // return false because no compatible values could be found (meaning hp values could no longer be decremented).
                    }
                }
            } // end While Loop
        }

        //************
        //*Test Stuff*
        //************

        // check/print hpSelectedLvlsValues hpValues/rndNums
        //printHpAndRndValues(hpSelectedLvlsValues, startLvl);

        // check/print mpSelectedLvlsValues mpValues/rndNums
        //printMpAndRndValues(mpSelectedLvlsValues, startLvl);



        return true; // compatible values can be found at every level in between the startLvl and endLvl
    }

    public static boolean isRangedLevelsHpMpValuesCompatibleAtEndLevel(String characterName, int startLvl, int endLvl, ArrayList<ArrayList<ArrayList<Integer>>> hpMpRangedLevelValuesAndRndNums){
        ArrayList<ArrayList<ArrayList<Integer>>> testHpMpRangedLevelValues = SupportMethods.getCopyThreeDimArrListPassByValue(hpMpRangedLevelValuesAndRndNums);
        int hpLvlValuesIndex = 0; // index for hpMpRangedLevelValues
        int mpLvlValuesIndex = 1; // index for hpMpRangedLevelValues
        int hpLvlRndNumsIndex = 2; // index for hpMpRangedLevelValues
        int mpLvlRndNumsIndex = 3; // index for hpMpRangedLevelValues

        // Check if there is a level with any empty values/rndNums
        if (isCompatibleHpMpValuesRndNumsEmptyLvls(characterName, startLvl, endLvl, testHpMpRangedLevelValues)){
            return false;
        }

        // Check if arrays have empty values for each level. If so, they are not compatible.
        for (int lvlCheck = 0; lvlCheck < testHpMpRangedLevelValues.get(hpLvlValuesIndex).size(); lvlCheck++){
            if (testHpMpRangedLevelValues.get(hpLvlValuesIndex).get(lvlCheck).isEmpty() || testHpMpRangedLevelValues.get(1).get(lvlCheck).isEmpty()){
                return false;
            }
        }

        // Assign all possible combinations up to where there is less than 8 random numbers used for each level.
        return isRangedLevelsHpMpValuesCompatibleAtEachLevel(testHpMpRangedLevelValues, startLvl, endLvl, characterName); // If compatible, returns true... If not compatible, returns false.
    }

    public static ArrayList<ArrayList<ArrayList<Integer>>> combineHpMpSelectedRangedLvlsValuesAndRndNumsIfCompatible(String characterName, int startLvl, int endLvl, int startHp, int endHp, int startMp, int endMp){
        // Returns empty array if end lvl hp/mp values are found as not compatible.

        int hpLvlValuesIndex = 0; // index for hpMpRangedLevelValues
        int mpLvlValuesIndex = 1; // index for hpMpRangedLevelValues
        int firstValueIndex = 0;
        boolean isHpSelectedForRndNums;

        ArrayList<ArrayList<ArrayList<Integer>>> finalResults = new ArrayList<>();
        ArrayList<ArrayList<ArrayList<Integer>>> hpMpRangedLevelValuesAndRndNums = new ArrayList<>();
        ArrayList<ArrayList<Integer>> hpRangedLevelValues = calculateRangeLevelsHpResults(characterName, startLvl, endLvl, startHp, endHp);
        ArrayList<ArrayList<Integer>> mpRangedLevelValues = calculateRangeLevelsMpResults(characterName, startLvl, endLvl, startMp, endMp);
        //If an end lvl value could not be reached, return empty array
        if (hpRangedLevelValues.isEmpty() || mpRangedLevelValues.isEmpty()){
            return finalResults;
        }

        hpMpRangedLevelValuesAndRndNums.add(hpRangedLevelValues);
        hpMpRangedLevelValuesAndRndNums.add(mpRangedLevelValues);
        isHpSelectedForRndNums = true;
        ArrayList<ArrayList<Integer>> hpRndNumsUsed = getHpOrMpRndNumsUsedInLvls(characterName, startLvl, endLvl, hpMpRangedLevelValuesAndRndNums.get(hpLvlValuesIndex), isHpSelectedForRndNums);
        isHpSelectedForRndNums = false;
        ArrayList<ArrayList<Integer>> mpRndNumsUsed = getHpOrMpRndNumsUsedInLvls(characterName, startLvl, endLvl, hpMpRangedLevelValuesAndRndNums.get(mpLvlValuesIndex), isHpSelectedForRndNums);
        hpMpRangedLevelValuesAndRndNums.add(hpRndNumsUsed);
        hpMpRangedLevelValuesAndRndNums.add(mpRndNumsUsed);

        //If a level for both hp/mp values has only one rndNum and the same rndNum value... return empty array.
        for (int lvl = hpRndNumsUsed.size() - 1; lvl >= 0; lvl--){
            if ((hpRndNumsUsed.get(lvl).size() == 1 && mpRndNumsUsed.get(lvl).size() == 1) &&
                    (hpRndNumsUsed.get(lvl).get(firstValueIndex).equals(mpRndNumsUsed.get(lvl).get(firstValueIndex)))
                    ){
                return finalResults;
            }
        }

        // Second, Strip out values for one side, when there is the same rndNum (and only one) on the other side
        finalResults = omitAndUpdateValuesWithSingleRndNumberEachLevel(characterName, startLvl, endLvl, hpMpRangedLevelValuesAndRndNums);

        return finalResults;
    }

    public static ArrayList<ArrayList<ArrayList<Integer>>> getCompatibleHpMpValuesAfterSafeLvl(String characterName, int startLvl, int endLvl, ArrayList<ArrayList<ArrayList<Integer>>> hpMpRangedLevelValuesAndRndNums){
        // precondition: end lvl hp/mp values must be obtainable with given start lvl hp/mp values

        ArrayList<ArrayList<ArrayList<Integer>>> finalResults = new ArrayList<>();
        int hpLvlValuesIndex = 0; // index for hpMpRangedLevelValues
        int mpLvlValuesIndex = 1; // index for hpMpRangedLevelValues
        int hpLvlRndNumsIndex = 2; // index for hpMpRangedLevelValues
        int mpLvlRndNumsIndex = 3; // index for hpMpRangedLevelValues
        int hpPairedValueIndex = 0; // [hpValue, mpValue]
        int mpPairedValueIndex = 1; // [hpValue, mpValue]
        int endHp = hpMpRangedLevelValuesAndRndNums.get(hpLvlValuesIndex).get(hpMpRangedLevelValuesAndRndNums.get(hpLvlValuesIndex).size() - 1).get(0);
        int endMp = hpMpRangedLevelValuesAndRndNums.get(mpLvlValuesIndex).get(hpMpRangedLevelValuesAndRndNums.get(mpLvlValuesIndex).size() - 1).get(0);

        // Setup empty lvls in finalResults and testIsCompatibleValuesUsable
        ArrayList<ArrayList<ArrayList<Integer>>> compatibleHpMpValues = new ArrayList<>();
        ArrayList<ArrayList<ArrayList<Integer>>> compatibleHpMpValuesNotUpdated = new ArrayList<>();
        ArrayList<ArrayList<Boolean>> testIsCompatibleValuesUsable = new ArrayList<>();
        for (int lvlIndex = startLvl; lvlIndex <= endLvl; lvlIndex++){ // creates lvls with empty values for finalResults
            compatibleHpMpValues.add(new ArrayList<ArrayList<Integer>>());
            compatibleHpMpValuesNotUpdated.add(new ArrayList<ArrayList<Integer>>());
            testIsCompatibleValuesUsable.add(new ArrayList<Boolean>());
        }
        ////////////////////////////////////////////////////////////////////

        // set values for final level
            // Add final HP/MP value in finalResults
            // Add one false in testIsCompatibleValuesUsable
        boolean isUsableHpMpValue = false;
        int firstLvlIndex = 80; // 92max change for quick testing, 80 for quick ranged testing
        int endLvlIndex = compatibleHpMpValues.size() - 1;
        ArrayList<Integer> lvlHpMpValues = new ArrayList<>();
        ArrayList<Integer> nextLvlHpMpValues = new ArrayList<>();
        lvlHpMpValues.addAll(Arrays.asList(endHp, endMp));
        compatibleHpMpValues.get(endLvlIndex).add(new ArrayList<>(lvlHpMpValues));
        compatibleHpMpValuesNotUpdated.get(endLvlIndex).add(new ArrayList<>(lvlHpMpValues));
        testIsCompatibleValuesUsable.get(endLvlIndex).add(isUsableHpMpValue);
        lvlHpMpValues.clear();
        /////////////////////////////

        // Add usable HP/MP values that are compatible to each level... starting with the level before the end lvl.
            // also, for every compatible value added, add a isUsableHpMpValue to the corresponding lvlList in testIsCompatibleValuesUsable
        int nextLvlMpValue;
        int nextLvlHpValue;
        int nextLvlHpMpValuesFoundIndex;
        ArrayList<Integer> hpUsableRndNums;
        int savedLvlIndex;
        int savedLvl;
        for (int lvlIndex = compatibleHpMpValues.size() - 2, lvl = endLvl - 1; lvlIndex >= firstLvlIndex; lvlIndex--, lvl--){
            savedLvlIndex = lvlIndex;
            savedLvl = lvl;
            for (int mpValue: hpMpRangedLevelValuesAndRndNums.get(mpLvlValuesIndex).get(lvlIndex)){
                for (int mpRndNum: hpMpRangedLevelValuesAndRndNums.get(mpLvlRndNumsIndex).get(lvlIndex)){
                    nextLvlMpValue = calculateMpLevelUpResult(characterName, lvl + 1, mpValue, mpRndNum);
                    hpUsableRndNums = getAndOmitRndNumOfHpRndNums(hpMpRangedLevelValuesAndRndNums.get(hpLvlRndNumsIndex).get(lvlIndex), mpRndNum);
                    for (int hpValue: hpMpRangedLevelValuesAndRndNums.get(hpLvlValuesIndex).get(lvlIndex)){
                        for (int hpRndNum: hpUsableRndNums){
                            nextLvlHpValue = calculateHpLevelUpResult(characterName, lvl + 1, hpValue, hpRndNum);
                            lvlHpMpValues.addAll(Arrays.asList(hpValue, mpValue));
                            nextLvlHpMpValues.addAll(Arrays.asList(nextLvlHpValue, nextLvlMpValue));
                            if (compatibleHpMpValues.get(lvlIndex + 1).contains(nextLvlHpMpValues) && !(compatibleHpMpValues.get(lvlIndex).contains(lvlHpMpValues))) { // value is usable and has not been added, add value and boolean
                                compatibleHpMpValues.get(lvlIndex).add(new ArrayList<>(lvlHpMpValues));
                                compatibleHpMpValuesNotUpdated.get(lvlIndex).add(new ArrayList<>(lvlHpMpValues));
                                testIsCompatibleValuesUsable.get(lvlIndex).add(isUsableHpMpValue);
                            }
                            if (compatibleHpMpValues.get(lvlIndex + 1).contains(nextLvlHpMpValues)){ // value is usable, change next level boolean to true where value is usable
                                nextLvlHpMpValuesFoundIndex = compatibleHpMpValues.get(lvlIndex + 1).indexOf(nextLvlHpMpValues);
                                testIsCompatibleValuesUsable.get(lvlIndex + 1).set(nextLvlHpMpValuesFoundIndex, true);
                            }
                            nextLvlHpMpValues.clear();
                            lvlHpMpValues.clear();
                        }
                    }
                }
            }
            while (testIsCompatibleValuesUsable.get(lvlIndex + 1).contains(false) && lvl < endLvl){ // next level value was not used
                for (int i = 0; i < testIsCompatibleValuesUsable.get(lvlIndex + 1).size(); i++){ // for each value not used, remove both the comboValue and the boolean using identical index.
                    if (!testIsCompatibleValuesUsable.get(lvlIndex + 1).get(i)){
                        compatibleHpMpValues.get(lvlIndex + 1).remove(i);
                        testIsCompatibleValuesUsable.get(lvlIndex + 1).remove(i);
                        i--;
                    }
                }
                lvlIndex++;
                lvl++;
                // Reset all next lvl next lvl testIsCompatibleValuesUsable booleans to false
                for (int i = 0; i < testIsCompatibleValuesUsable.get(lvlIndex + 1).size(); i++){
                    testIsCompatibleValuesUsable.get(lvlIndex + 1).set(i, false);
                }
                // Confirm all next lvl values are used...
                    // for each paired value in next lvl, if used, set to true...
                for (int mpRndNum: hpMpRangedLevelValuesAndRndNums.get(mpLvlRndNumsIndex).get(lvlIndex)){
                    hpUsableRndNums = getAndOmitRndNumOfHpRndNums(hpMpRangedLevelValuesAndRndNums.get(hpLvlRndNumsIndex).get(lvlIndex), mpRndNum);
                    for (int hpRndNum: hpUsableRndNums){
                        for (ArrayList<Integer> hpMpPairedValue: compatibleHpMpValues.get(lvlIndex)){
                            nextLvlHpValue = calculateHpLevelUpResult(characterName, lvl + 1, hpMpPairedValue.get(hpPairedValueIndex), hpRndNum);
                            nextLvlMpValue = calculateMpLevelUpResult(characterName, lvl + 1, hpMpPairedValue.get(mpPairedValueIndex), mpRndNum);
                            nextLvlHpMpValues.addAll(Arrays.asList(nextLvlHpValue, nextLvlMpValue));
                            if (compatibleHpMpValues.get(lvlIndex + 1).contains(nextLvlHpMpValues)){
                                testIsCompatibleValuesUsable.get(lvlIndex + 1).set(compatibleHpMpValues.get(lvlIndex + 1).indexOf(nextLvlHpMpValues), true);
                            }
                            nextLvlHpMpValues.clear();
                        } // end for each hpMpPairedValue loop
                    } // end for each hpRndNum loop
                } // end for each mpRndNum loop
            if (!(testIsCompatibleValuesUsable.get(lvlIndex + 1).contains(false))){
                    lvlIndex = savedLvlIndex;
                    lvl = savedLvl;
                }
            } // end while paired value not used loop
        } // end for lvl loop



        return compatibleHpMpValues;
    }

    public static int getHpMpComboIndexWithMpValueFound(ArrayList<ArrayList<ArrayList<Integer>>> lvlHpMpValuesOrganized, Integer mpValue){
        int hpMpComboIndexFound = 0;
        int hpValueIndex = 0;
        int mpValuesIndex = 1;

        for (ArrayList<ArrayList<Integer>> hpMpValuesCombo: lvlHpMpValuesOrganized){
            if (hpMpValuesCombo.get(mpValuesIndex).contains(mpValue)){
                return hpMpComboIndexFound;
            }
            hpMpComboIndexFound++;
        }

        return -1; // not found
    }

    public static ArrayList<ArrayList<ArrayList<ArrayList<Integer>>>> organizeHpMpCompatibleValues (ArrayList<ArrayList<ArrayList<Integer>>> lvlsHpMpValues){
        ArrayList<ArrayList<ArrayList<ArrayList<Integer>>>> lvlsHpMpValuesCombined = new ArrayList<>();
        ArrayList<Integer> hpValues = new ArrayList<>();
        ArrayList<Integer> mpValues = new ArrayList<>();
        int hpValuesIndex = 0;
        int mpValuesIndex = 1;

        // arrange values into new array, so the first value is added to only one of the same 2nd value
        // create lvls
        for (int lvl = 0; lvl < lvlsHpMpValues.size(); lvl++){
            lvlsHpMpValuesCombined.add(new ArrayList<ArrayList<ArrayList<Integer>>>());
        }

        int hpMpComboIndexWithMpValueFound;
        for (int lvl = 0; lvl < lvlsHpMpValues.size(); lvl++){
            for (int hpMpCombo = 0; hpMpCombo < lvlsHpMpValues.get(lvl).size(); hpMpCombo++){
                hpMpComboIndexWithMpValueFound = getHpMpComboIndexWithMpValueFound(lvlsHpMpValuesCombined.get(lvl), lvlsHpMpValues.get(lvl).get(hpMpCombo).get(mpValuesIndex));
                if (hpMpComboIndexWithMpValueFound != -1){
                    // new array already has value, so just add value in the hp values array where the mp value array exists

                    lvlsHpMpValuesCombined.get(lvl).get(hpMpComboIndexWithMpValueFound).get(hpValuesIndex).add(lvlsHpMpValues.get(lvl).get(hpMpCombo).get(hpValuesIndex)); // add hpValue
                } else {
                    // new array does not contain mp value, so create new row of [[hpValue, hpvalue, hpvalue, ect...][mpValue]]
                    lvlsHpMpValuesCombined.get(lvl).add(new ArrayList<ArrayList<Integer>>()); // hpMpCombination Array
                    lvlsHpMpValuesCombined.get(lvl).get(lvlsHpMpValuesCombined.get(lvl).size() - 1).add(new ArrayList<>()); // hpValues array
                    lvlsHpMpValuesCombined.get(lvl).get(lvlsHpMpValuesCombined.get(lvl).size() - 1).add(new ArrayList<>()); // mpValues array
                    lvlsHpMpValuesCombined.get(lvl).get(lvlsHpMpValuesCombined.get(lvl).size() - 1).get(hpValuesIndex).add(lvlsHpMpValues.get(lvl).get(hpMpCombo).get(hpValuesIndex)); // add hpValue
                    lvlsHpMpValuesCombined.get(lvl).get(lvlsHpMpValuesCombined.get(lvl).size() - 1).get(mpValuesIndex).add(lvlsHpMpValues.get(lvl).get(hpMpCombo).get(mpValuesIndex)); // add mpValue
                }
            }
        }

        // Sort HP/MP Values
        for (int lvlIndex = 0; lvlIndex < lvlsHpMpValuesCombined.size(); lvlIndex++){
            for (int hpMpCombosInLvlIndex = 0; hpMpCombosInLvlIndex < lvlsHpMpValuesCombined.get(lvlIndex).size(); hpMpCombosInLvlIndex++){
                Collections.sort(lvlsHpMpValuesCombined.get(lvlIndex).get(hpMpCombosInLvlIndex).get(hpValuesIndex));
                Collections.sort(lvlsHpMpValuesCombined.get(lvlIndex).get(hpMpCombosInLvlIndex).get(mpValuesIndex));
            }
        }

        return lvlsHpMpValuesCombined;
    }

    public static ArrayList<ArrayList<ArrayList<ArrayList<Integer>>>> combineOrganizedHpMpCombos(ArrayList<ArrayList<ArrayList<ArrayList<Integer>>>> lvlsHpMpOrganizedCombos){
        int hpValuesIndex = 0;
        int mpValuesIndex = 1;
        int firstMpValueIndex = 0;

        for (int lvlIndex = 0; lvlIndex < lvlsHpMpOrganizedCombos.size(); lvlIndex++){ // lvl
            if (lvlsHpMpOrganizedCombos.get(lvlIndex).size() > 1) { // lvl contains more than one hpMpCombos
                for (int hpMpComboIndexInCheck = 0; hpMpComboIndexInCheck < lvlsHpMpOrganizedCombos.get(lvlIndex).size() - 1; hpMpComboIndexInCheck++) { // [[hpValues][mpValues]] : last combo will already be checked
                    for (int hpMpComboIndex = hpMpComboIndexInCheck + 1; hpMpComboIndex < lvlsHpMpOrganizedCombos.get(lvlIndex).size(); hpMpComboIndex++){
                        if (lvlsHpMpOrganizedCombos.get(lvlIndex).get(hpMpComboIndexInCheck).get(hpValuesIndex).equals(lvlsHpMpOrganizedCombos.get(lvlIndex).get(hpMpComboIndex).get(hpValuesIndex))){ // [hpValues] more than 1 of the same
                            // add mp value to same hpMpCombo as hpMpComboIndexInCheck
                            lvlsHpMpOrganizedCombos.get(lvlIndex).get(hpMpComboIndexInCheck).get(mpValuesIndex).add(lvlsHpMpOrganizedCombos.get(lvlIndex).get(hpMpComboIndex).get(mpValuesIndex).get(firstMpValueIndex));
                            // remove hpMpComboIndex in check
                            lvlsHpMpOrganizedCombos.get(lvlIndex).remove(hpMpComboIndex);
                            hpMpComboIndex--; // sets index back one, so the next combo is not skipped when checking...
                        }
                    }
                }
            }
        }

        int displayLvl = getCurrentLvlFromLvlIndex(0, 6);
        for (ArrayList<ArrayList<ArrayList<Integer>>> lvl: lvlsHpMpOrganizedCombos){
            System.out.println("********************** (Level " + displayLvl + ") **********************");
            for (ArrayList<ArrayList<Integer>> hpMpCombo: lvl){
                System.out.println(hpMpCombo);
            }
            displayLvl++;
        }

        return lvlsHpMpOrganizedCombos;
    }

    //------------------------------------------- Calculate Functions (HP/MP) ------------------------------------------
    public static ArrayList<ArrayList<ArrayList<ArrayList<Integer>>>> calculatePossibleRangeLevelsHpMpCombinations(String characterName, int startLvl, int endLvl, int startHp, int endHp, int startMp, int endMp, boolean hpIsPriority){
        /*/ Note: The format of returning the ArrayList<ArrayList<Integer>> is as follows...
            { LVL#
                { {hpValue, hpValue, hpvalue, ect...}, {mpValue, mpValue, ect...}}
                { {hpValue, hpValue, hpvalue, ect...}, {mpValue, mpValue, ect...}}
                { {hpValue, hpValue, hpvalue, ect...}, {mpValue, mpValue, ect...}}
                { {hpValue, hpValue, hpvalue, ect...}, {mpValue, mpValue, ect...}}
                ect...
            }
         */

        int hpLvlValuesIndex = 0; // index for hpMpRangedLevelValues
        int mpLvlValuesIndex = 1; // index for hpMpRangedLevelValues
        int hpLvlRndNumsIndex = 2; // index for hpMpRangedLevelValues
        int mpLvlRndNumsIndex = 3; // index for hpMpRangedLevelValues

        ArrayList<ArrayList<ArrayList<Integer>>> compatibleHpMpValueCombos;
        ArrayList<ArrayList<ArrayList<ArrayList<Integer>>>> finalResults = new ArrayList<>(); // lvl[hpValuesCompatibleWithMpValues[]]
        ArrayList<ArrayList<ArrayList<Integer>>> hpMpRangedLevelValuesAndRndNums = combineHpMpSelectedRangedLvlsValuesAndRndNumsIfCompatible(characterName, startLvl, endLvl, startHp, endHp, startMp, endMp);
        if (hpMpRangedLevelValuesAndRndNums.isEmpty()){
            return finalResults; // returns empty array if the values are not compatible.
        }

        // First, make sure the end values in these ranged levels are compatible (meaning they can be reached).
        boolean isEndValuesCompatible = isRangedLevelsHpMpValuesCompatibleAtEndLevel(characterName, startLvl, endLvl, hpMpRangedLevelValuesAndRndNums);
        if (!isEndValuesCompatible){
            return finalResults; // returns empty array if the values are not compatible.
        }

        compatibleHpMpValueCombos = getCompatibleHpMpValuesAfterSafeLvl(characterName, startLvl, endLvl, hpMpRangedLevelValuesAndRndNums);
        finalResults = organizeHpMpCompatibleValues(compatibleHpMpValueCombos);
        finalResults = combineOrganizedHpMpCombos(finalResults);

        return finalResults;
    }

    //******************************************************************************************************************
    //************************************************** Primary Stats *************************************************
    //******************************************************************************************************************
    public static boolean isEndLevelPrimaryStatValueAvailable(String characterName, int startLevel, int endLevel, int startPrimStat, int endPrimStat, String statType){
        ArrayList<Integer> checkEndLevelValues = getAllPossiblePrimaryStatValuesAtEndLevel(characterName, startLevel, endLevel, startPrimStat, statType);
        return checkEndLevelValues.contains(endPrimStat);
    }

    public static ArrayList<ArrayList<Integer>> calculateRangeLevelsPrimaryStatResults(String characterName, int startLevel, int endLevel, int startPrimStat, int endPrimStat, boolean maxPrimStatAtEndLevel, String statType){
        ArrayList<ArrayList<Integer>> rangedLevelsPrimaryStatValues = new ArrayList<>();
        rangedLevelsPrimaryStatValues.add(new ArrayList<>(Collections.singletonList(startPrimStat)));
        ArrayList<Integer> nextLevelPrimaryStatValues = new ArrayList<>();
        int endLevelValue;
        if (maxPrimStatAtEndLevel){
            endLevelValue = getMaxPrimaryStatValueAtEndLevel(characterName, startLevel, endLevel, startPrimStat, statType);
        } else if (isEndLevelPrimaryStatValueAvailable(characterName, startLevel, endLevel, startPrimStat, endPrimStat, statType)) {
            endLevelValue = endPrimStat;
        } else {
            rangedLevelsPrimaryStatValues.clear();
            return rangedLevelsPrimaryStatValues;
        }

        // adds all possible values for every level up
        int rangedLevelsPrimaryStatValuesIndex = 0;
        for (int levelUp = startLevel + 1; levelUp <= endLevel; levelUp++){
            if (levelUp == endLevel && nextLevelPrimaryStatValues.isEmpty()) {
                nextLevelPrimaryStatValues.add(endLevelValue);
            } else {
                for (int primStatValue : rangedLevelsPrimaryStatValues.get(rangedLevelsPrimaryStatValuesIndex)) {
                    for (int rndNum : rndNums) {
                        int levelUpPrimaryStatValue = calculatePrimaryStatLevelUpResult(characterName, levelUp, statType, rndNum, primStatValue);
                        if (!nextLevelPrimaryStatValues.contains(levelUpPrimaryStatValue)) {
                            nextLevelPrimaryStatValues.add(levelUpPrimaryStatValue);
                        }
                    }
                }
            }
            Collections.sort(nextLevelPrimaryStatValues);
            rangedLevelsPrimaryStatValues.add(new ArrayList<>(nextLevelPrimaryStatValues));
            nextLevelPrimaryStatValues.clear();
            rangedLevelsPrimaryStatValuesIndex++;
        }

        // removes all values for every level up that doesn't path to the end level value
        int currentLevel = endLevel;
        int currentLevelValuesIndex = rangedLevelsPrimaryStatValues.size() - 1;
        int previousLevelIndex = currentLevelValuesIndex - 1;
        for (int previousLevel = currentLevel - 1; previousLevel > startLevel; previousLevel--){
            ArrayList<Integer> tempRangedLevelsPrimaryStatValues = new ArrayList<>(rangedLevelsPrimaryStatValues.get(previousLevelIndex));
            int previousLevelPrimaryStatValueIndex = 0;
            for (int previousLevelPrimaryStatValue: tempRangedLevelsPrimaryStatValues){
                boolean valueLinkFound = false;
                int rndNum = 8;
                while (!valueLinkFound && rndNum > 0){
                    int prevToNextLevelPrimaryStatValue = calculatePrimaryStatLevelUpResult(characterName, currentLevel, statType, rndNum, previousLevelPrimaryStatValue);
                    if (rangedLevelsPrimaryStatValues.get(currentLevelValuesIndex).contains(prevToNextLevelPrimaryStatValue)){
                        valueLinkFound = true;
                        rndNum = 0;
                    }
                    rndNum--;
                }
                if (!valueLinkFound){
                    rangedLevelsPrimaryStatValues.get(previousLevelIndex).remove(previousLevelPrimaryStatValueIndex);
                } else {
                    previousLevelPrimaryStatValueIndex++;
                }
            }
            currentLevelValuesIndex--;
            previousLevelIndex--;
            currentLevel--;
        }
        return rangedLevelsPrimaryStatValues;
    }

    public static ArrayList<Integer> getAllPossiblePrimaryStatValuesAtEndLevel(String characterName, int startLevel, int endLevel, int startPrimStat, String statType){
        ArrayList<Integer> currentLevelPrimaryStatValues = new ArrayList<>(Collections.singletonList(startPrimStat));
        ArrayList<Integer> nextLevelPrimaryStatValues = new ArrayList<>();
        for (int levelUp = startLevel + 1; levelUp <= endLevel; levelUp++){
            for (int primStatValue: currentLevelPrimaryStatValues){
                for (int rndNum: rndNums){
                    int levelUpPrimaryStatValue = calculatePrimaryStatLevelUpResult(characterName, levelUp, statType, rndNum, primStatValue);
                    if (!nextLevelPrimaryStatValues.contains(levelUpPrimaryStatValue)){
                        nextLevelPrimaryStatValues.add(levelUpPrimaryStatValue);
                    }
                }
            }
            currentLevelPrimaryStatValues = new ArrayList<>(nextLevelPrimaryStatValues);
            if (levelUp != endLevel) {
                nextLevelPrimaryStatValues.clear();
            }
        }
        Collections.sort(nextLevelPrimaryStatValues);
        if (nextLevelPrimaryStatValues.isEmpty()){
            return currentLevelPrimaryStatValues;
        }
        return nextLevelPrimaryStatValues;
    }

    public static ArrayList<ArrayList<Integer>> getPossiblePrimaryStatValuesAtAllLevels(String characterName, int startLevel, int endLevel, int startPrimStat, String statType){
        ArrayList<ArrayList<Integer>> rangedLevelsPrimaryStatValues = new ArrayList<>();
        rangedLevelsPrimaryStatValues.add(new ArrayList<>(Collections.singletonList(startPrimStat)));
        ArrayList<Integer> nextLevelPrimaryStatValues = new ArrayList<>();
        int rangedLevelsPrimaryStatValuesIndex = 0;
        for (int levelUp = startLevel + 1; levelUp <= endLevel; levelUp++){
            for (int primStatValue : rangedLevelsPrimaryStatValues.get(rangedLevelsPrimaryStatValuesIndex)) {
                for (int rndNum : rndNums) {
                    int levelUpPrimaryStatValue = calculatePrimaryStatLevelUpResult(characterName, levelUp, statType, rndNum, primStatValue);
                    if (!nextLevelPrimaryStatValues.contains(levelUpPrimaryStatValue)) {
                        nextLevelPrimaryStatValues.add(levelUpPrimaryStatValue);
                    }
                }
            }
            Collections.sort(nextLevelPrimaryStatValues);
            rangedLevelsPrimaryStatValues.add(new ArrayList<>(nextLevelPrimaryStatValues));
            nextLevelPrimaryStatValues.clear();
            rangedLevelsPrimaryStatValuesIndex++;
        }
        return rangedLevelsPrimaryStatValues;
    }

    public static Integer getMaxPrimaryStatValueAtEndLevel(String characterName, int startLevel, int endLevel, int startPrimStat, String statType){
        ArrayList<Integer> primStatValues = getAllPossiblePrimaryStatValuesAtEndLevel(characterName, startLevel, endLevel, startPrimStat, statType);
        return primStatValues.get(primStatValues.size() - 1);
    }

    public static Integer getMinPrimaryStatValueAtEndLevel(String characterName, int startLevel, int endLevel, int startPrimStat, String statType){
        ArrayList<Integer> primStatValues = getAllPossiblePrimaryStatValuesAtEndLevel(characterName, startLevel, endLevel, startPrimStat, statType);
        return primStatValues.get(0);
    }

    //******************************************************************************************************************
    //****************************************************** Luck ******************************************************
    //******************************************************************************************************************
    public static boolean isEndLevelLckValueAvailable(String characterName, int startLevel, int endLevel, int startLck, int endLck){
        ArrayList<Integer> checkEndLevelValues = getAllPossibleLckValuesAtEndLevel(characterName, startLevel, endLevel, startLck);
        return checkEndLevelValues.contains(endLck);
    }

    public static ArrayList<ArrayList<Integer>> calculateRangeLevelsLckResults(String characterName, int startLevel, int endLevel, int startLck, int endLck, boolean maxLckAtEndLevel){
        ArrayList<ArrayList<Integer>> rangedLevelsLckValues = new ArrayList<>();
        rangedLevelsLckValues.add(new ArrayList<>(Collections.singletonList(startLck)));
        ArrayList<Integer> nextLevelLckValues = new ArrayList<>();
        int endLevelValue;
        if (maxLckAtEndLevel){
            endLevelValue = getMaxLckValueAtEndLevel(characterName, startLevel, endLevel, startLck);
        } else if (isEndLevelLckValueAvailable(characterName, startLevel, endLevel, startLck, endLck)) {
            endLevelValue = endLck;
        } else {
            rangedLevelsLckValues.clear();
            return rangedLevelsLckValues;
        }

        // adds all possible values for every level up
        int rangedLevelsLckValuesIndex = 0;
        for (int levelUp = startLevel + 1; levelUp <= endLevel; levelUp++){
            if (levelUp == endLevel && nextLevelLckValues.isEmpty()) {
                nextLevelLckValues.add(endLevelValue);
            } else {
                for (int LckValue : rangedLevelsLckValues.get(rangedLevelsLckValuesIndex)) {
                    for (int rndNum : rndNums) {
                        int levelUpLckValue = calculateLckLevelUpResult(characterName, levelUp, LckValue, rndNum);
                        if (!nextLevelLckValues.contains(levelUpLckValue)) {
                            nextLevelLckValues.add(levelUpLckValue);
                        }
                    }
                }
            }
            Collections.sort(nextLevelLckValues);
            rangedLevelsLckValues.add(new ArrayList<>(nextLevelLckValues));
            nextLevelLckValues.clear();
            rangedLevelsLckValuesIndex++;
        }

        // removes all values for every level up that doesn't path to the end level value
        int currentLevel = endLevel;
        int currentLevelValuesIndex = rangedLevelsLckValues.size() - 1;
        int previousLevelIndex = currentLevelValuesIndex - 1;
        for (int previousLevel = currentLevel - 1; previousLevel > startLevel; previousLevel--){
            ArrayList<Integer> tempRangedLevelsLckValues = new ArrayList<>(rangedLevelsLckValues.get(previousLevelIndex));
            int previousLevelLckValueIndex = 0;
            for (int previousLevelLckValue: tempRangedLevelsLckValues){
                boolean valueLinkFound = false;
                int rndNum = 8;
                while (!valueLinkFound && rndNum > 0){
                    int prevToNextLevelLckValue = calculateLckLevelUpResult(characterName, currentLevel, previousLevelLckValue, rndNum);
                    if (rangedLevelsLckValues.get(currentLevelValuesIndex).contains(prevToNextLevelLckValue)){
                        valueLinkFound = true;
                        rndNum = 0;
                    }
                    rndNum--;
                }
                if (!valueLinkFound){
                    rangedLevelsLckValues.get(previousLevelIndex).remove(previousLevelLckValueIndex);
                } else {
                    previousLevelLckValueIndex++;
                }
            }
            currentLevelValuesIndex--;
            previousLevelIndex--;
            currentLevel--;
        }
        return rangedLevelsLckValues;
    }

    public static ArrayList<Integer> getAllPossibleLckValuesAtEndLevel(String characterName, int startLevel, int endLevel, int startLck){
        ArrayList<Integer> currentLevelLckValues = new ArrayList<>(Collections.singletonList(startLck));
        ArrayList<Integer> nextLevelLckValues = new ArrayList<>();
        for (int levelUp = startLevel + 1; levelUp <= endLevel; levelUp++){
            for (int LckValue: currentLevelLckValues){
                for (int rndNum: rndNums){
                    int levelUpLckValue = calculateLckLevelUpResult(characterName, levelUp, LckValue, rndNum);
                    if (!nextLevelLckValues.contains(levelUpLckValue)){
                        nextLevelLckValues.add(levelUpLckValue);
                    }
                }
            }
            currentLevelLckValues = new ArrayList<>(nextLevelLckValues);
            if (levelUp != endLevel) {
                nextLevelLckValues.clear();
            }
        }
        Collections.sort(nextLevelLckValues);
        if (nextLevelLckValues.isEmpty()){
            return currentLevelLckValues;
        }
        return nextLevelLckValues;
    }

    public static ArrayList<ArrayList<Integer>> getPossibleLckValuesAtAllLevels(String characterName, int startLevel, int endLevel, int startLck){
        ArrayList<ArrayList<Integer>> rangedLevelsLckValues = new ArrayList<>();
        rangedLevelsLckValues.add(new ArrayList<>(Collections.singletonList(startLck)));
        ArrayList<Integer> nextLevelLckValues = new ArrayList<>();
        int rangedLevelsLckValuesIndex = 0;
        for (int levelUp = startLevel + 1; levelUp <= endLevel; levelUp++){
            for (int LckValue : rangedLevelsLckValues.get(rangedLevelsLckValuesIndex)) {
                for (int rndNum : rndNums) {
                    int levelUpLckValue = calculateLckLevelUpResult(characterName, levelUp, LckValue, rndNum);
                    if (!nextLevelLckValues.contains(levelUpLckValue)) {
                        nextLevelLckValues.add(levelUpLckValue);
                    }
                }
            }
            Collections.sort(nextLevelLckValues);
            rangedLevelsLckValues.add(new ArrayList<>(nextLevelLckValues));
            nextLevelLckValues.clear();
            rangedLevelsLckValuesIndex++;
        }
        return rangedLevelsLckValues;
    }

    public static Integer getMaxLckValueAtEndLevel(String characterName, int startLevel, int endLevel, int startLck){
        ArrayList<Integer> LckValues = getAllPossibleLckValuesAtEndLevel(characterName, startLevel, endLevel, startLck);
        return LckValues.get(LckValues.size() - 1);
    }

    public static Integer getMinLckValueAtEndLevel(String characterName, int startLevel, int endLevel, int startLck){
        ArrayList<Integer> LckValues = getAllPossibleLckValuesAtEndLevel(characterName, startLevel, endLevel, startLck);
        return LckValues.get(0);
    }
}

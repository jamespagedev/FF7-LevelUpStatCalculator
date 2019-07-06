/**
 * Created by James Page on 5/30/2017.
 */
package ClassFiles;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class CalculateRangedLevelsConsoleInterface extends CalculateRangeLevels {
    public Characters characterSelected;
    public boolean[] statsSelected = {false, false, false, false, false, false, false, false, false}; // HP, MP, HP/MP, Str, Dex, Vit, Mag, Spr, Lck
    public boolean maxHpPriorityOverMp = true;
    public final String[] statSelections = {"1. HP", "2. MP", "3. HP and MP", "4. Str", "5. Dex", "6. Vit", "7. Mag", "8. Spr", "9. Lck", "10. All Stats", "11. Remove All Stats", "12. Finished"};
    public final String[] statSelectionTypes = {"HP", "MP", "HP and MP", "Str", "Dex", "Vit", "Mag", "Spr", "Lck", "All Stats", "Remove All Stats", "Finished"};
    public final String statsHpAndMpSelectionType = statSelectionTypes[3];
    public boolean[] endLevelMaxStatsValuesSelected = {false, false, false, false, false, false, false, false}; // HP, MP, Str, Dex, Vit, Mag, Spr, Lck
    public int finalLevel;
    public int[] endLevelStatsValues = {0, 0, 0, 0, 0, 0, 0, 0}; // HP, MP, Str, Dex, Vit, Mag, Spr, Lck
    public boolean[] displayResultsSelected = {false, false, false};
    public final String[] displayResultsSelections = {
            "1. Display All Possible Stat(s) Values from Starting Level to End Level",
            "2. Display All Required Stat(s) Values from Starting Level to End Level Stat(s) Max Value(s)",
            "3. Display All Required Stat(s) Values from Starting Level to End Level Stat(s) Specific Value(s)",};
    public final int consoleLineSeparatorLength = "***************************************************************".length();
    public Scanner stdIn = new Scanner(System.in);

    //******************************************************************************************************************
    //*************************************************** Constructor **************************************************
    //******************************************************************************************************************
    public CalculateRangedLevelsConsoleInterface(){
        setup();
    }

    //******************************************************************************************************************
    //********************************************* Class Variable Methods *********************************************
    //******************************************************************************************************************
    public void setCharacterSelected(Characters character){
        this.characterSelected = character;
    }

    public Characters getCharacterSelected(){return this.characterSelected;}

    public boolean isMaxHpPriorityOverMp(){
        return this.maxHpPriorityOverMp;
    }

    public void setMaxHpPriorityOverMp(boolean maxHpHasPriorityOverMp){
        this.maxHpPriorityOverMp = maxHpHasPriorityOverMp;
    }

    public void setStatsSelected(boolean selectStat, int index){
        this.statsSelected[index] = selectStat;
    }

    public void setAllStatsSelected(boolean selectAllStats){
        if (selectAllStats){
            setStatsSelected(false, 0);
            setStatsSelected(false, 1);
            for (int i = 2; i < getStatsSelected().length; i++){
                setStatsSelected(true, i);
            }
        } else {
            for (int i = 0; i < getStatsSelected().length; i++){
                setStatsSelected(false, i);
            }
        }
    }

    public void setHpAndMpSelected(){
        setStatsSelected(false, 0);
        setStatsSelected(false, 1);
        setStatsSelected(true, 2);
    }

    public boolean areAnyStatsSelected(){
        for (boolean statSelected: getStatsSelected()){
            if (statSelected){
                return true;
            }
        }
        return false;
    }

    public boolean isHpMpStatsSelected(){
        return getStatsSelected()[0] && getStatsSelected()[1];
    }

    public boolean isHpOrMpStatsSelected(){
        return getStatsSelected()[0] || getStatsSelected()[1];
    }

    public boolean isHpAndMpStatSelected(){
        return getStatsSelected()[2];
    }

    public boolean isHpOnlyStatSelected(){
        return getStatsSelected()[0];
    }

    public boolean isMpOnlyStatSelected(){
        return getStatsSelected()[1];
    }

    public boolean isStrStatSelected(){
        return getStatsSelected()[3];
    }

    public boolean isDexStatSelected(){
        return getStatsSelected()[4];
    }

    public boolean isVitStatSelected(){
        return getStatsSelected()[5];
    }

    public boolean isMagStatSelected(){
        return getStatsSelected()[6];
    }

    public boolean isSprStatSelected(){
        return getStatsSelected()[7];
    }

    public boolean isLckStatSelected(){
        return getStatsSelected()[8];
    }

    public boolean[] getStatsSelected(){
        return this.statsSelected;
    }

    public String[] getStatSelections(){
        return this.statSelections;
    }

    public String[] getStatSelectionTypes(){
        return this.statSelectionTypes;
    }

    public String getStatsHpAndMpSelectionType(){
        return this.statsHpAndMpSelectionType;
    }

    public void setEndLevelMaxStatsValuesSelected(boolean value, int index){
        this.endLevelMaxStatsValuesSelected[index] = value;
    }

    public boolean[] getEndLevelMaxStatsValuesSelected(){
        return this.endLevelMaxStatsValuesSelected;
    }

    public int getFinalLevel(){
        return this.finalLevel;
    }

    public void setFinalLevel(int level){
        this.finalLevel = level;
    }

    public int[] getEndLevelStatsValues(){
        return this.endLevelStatsValues;
    }

    public boolean[] getDisplayResultsSelected(){
        return this.displayResultsSelected;
    }

    public void setDisplayResultsSelected(int selectionIndex){
        for (int i = 0; i < getDisplayResultsSelected().length; i++){
            this.displayResultsSelected[i] = false;
        }
        this.displayResultsSelected[selectionIndex] = true;
    }

    public String[] getDisplayResultsSelections(){
        return this.displayResultsSelections;
    }

    //******************************************************************************************************************
    //************************************************* Support Methods ************************************************
    //******************************************************************************************************************
    public void printErrorMessage(String msg){
        java.awt.Toolkit.getDefaultToolkit().beep();
        System.out.println(msg);
    }

    public void displayStatsCurrentlySelected(String statsSelectedDefaultDisplayTitle){
        String statsSelected = statsSelectedDefaultDisplayTitle;
        String statSelectedSeparator = " | ";
        for (int index = 0; index < getStatsSelected().length; index++){
            if (getStatsSelected()[index]){
                statsSelected += getStatSelectionTypes()[index] + statSelectedSeparator;
            }
        }
        if (statsSelected.equals(statsSelectedDefaultDisplayTitle)){
            statsSelected += "None";
        } else {
            statsSelected = statsSelected.substring(0, statsSelected.length() - statSelectedSeparator.length());
        }

        System.out.println(statsSelected);
    }

    public void displayStatsAvailableToSelect(){
        ArrayList<String> availableStatSelections = new ArrayList<>();
        int statsSelectedIndex = 0;

        // Configure display of available stat selections
        for (int index = 0; index < getStatsSelected().length; index++){
            if (!getStatsSelected()[index]){
                availableStatSelections.add(getStatSelections()[index]);
            }
        }
        availableStatSelections.add(getStatSelections()[getStatSelections().length - 3]);
        availableStatSelections.add(getStatSelections()[getStatSelections().length - 2]);
        availableStatSelections.add(getStatSelections()[getStatSelections().length - 1]);

        // Print available stat selections
        for (String availableStatSelection: availableStatSelections){
            System.out.println(availableStatSelection);
        }
    }

    public void displayCharacterInfo(){
        System.out.println("Character Name: " + getCharacterSelected().getCharacterName());
        System.out.println(getCharacterSelected().getCharacterName() + " Starting Level: " + getCharacterSelected().getLevel());
        if(getStatsSelected()[0] || isHpAndMpStatSelected()){
            System.out.println(getCharacterSelected().getCharacterName() + " Starting Base HP: " + getCharacterSelected().getBaseHp());
        }
        if(getStatsSelected()[1] || isHpAndMpStatSelected()){
            System.out.println(getCharacterSelected().getCharacterName() + " Starting Base MP: " + getCharacterSelected().getBaseMp());
        }
        if(getStatsSelected()[3]){
            System.out.println(getCharacterSelected().getCharacterName() + " Starting Base Str: " + getCharacterSelected().getBaseStrength());
        }
        if(getStatsSelected()[4]){
            System.out.println(getCharacterSelected().getCharacterName() + " Starting Base Dex: " + getCharacterSelected().getBaseDexterity());
        }
        if(getStatsSelected()[5]){
            System.out.println(getCharacterSelected().getCharacterName() + " Starting Base Vit: " + getCharacterSelected().getBaseVitality());
        }
        if(getStatsSelected()[6]){
            System.out.println(getCharacterSelected().getCharacterName() + " Starting Base Mag: " + getCharacterSelected().getBaseMagic());
        }
        if(getStatsSelected()[7]){
            System.out.println(getCharacterSelected().getCharacterName() + " Starting Base Spr: " + getCharacterSelected().getBaseSpirit());
        }
        if(getStatsSelected()[8]){
            System.out.println(getCharacterSelected().getCharacterName() + " Starting Base Lck: " + getCharacterSelected().getBaseLuck());
        }
    }

    public boolean getYesOrNoAnswer(String msg){
        boolean usrAnsGiven = false;
        String usrAns;

        while (!usrAnsGiven){
            System.out.print(msg);
            usrAns = this.stdIn.next();
            if (usrAns.toUpperCase().startsWith("Y")){
                return true;
            } else if (usrAns.toUpperCase().startsWith("N")){
                usrAnsGiven = true;
            } else {
                printErrorMessage("\tError: " + usrAns + " is not a valid answer.");
            }
        }
        return false;
    }

    public String createConsoleLineSeparator() {
        int length = consoleLineSeparatorLength;
        final StringBuilder builder = new StringBuilder();
        while (length-- > 0) {
            builder.append("*");
        }
        return builder.toString();
    }

    public String createConsoleLineSeparator(String heading) {
        int length = consoleLineSeparatorLength;
        final int position = (length - heading.length() - 1) / 2;
        final StringBuilder builder = new StringBuilder();
        while (builder.length() < position) {
            builder.append("*");
        }

        builder.append(' ').append(heading).append(' ');

        while (builder.length() < length) {
            builder.append("*");
        }
        return builder.toString();
    }

    //******************************************************************************************************************
    //********************************************** Setup User Selections *********************************************
    //******************************************************************************************************************
    public void setup(){
        setCharacterSelected(setupCharacter());
        selectStatsToCalculate();
        setupCharacterStartingLevelAndStats();
        setupDisplayResultsSelection();
        setupCharacterFinalLevelAndStats();
    }

    public Characters setupCharacter(){
        printConsoleLineSeparator("Character Selection");
        boolean isSelectedCharacterValid = false;

        for (int selection = 1, character = 0; character < Characters.getListOfCharacterNames().length; selection++, character++){
            System.out.println(selection + ". " + Characters.characterNames[character]);
        }

        int selectedCharacterNum = 1;
        while (!isSelectedCharacterValid) {
            System.out.print("Select a character (1 - " + (Characters.getListOfCharacterNames().length) + "): ");
            selectedCharacterNum = this.stdIn.nextInt();
            if (selectedCharacterNum <= Characters.getListOfCharacterNames().length && selectedCharacterNum > 0){
                isSelectedCharacterValid = true;
            } else {
                printErrorMessage("\tError: Invalid character selection.");
            }
        }
        String characterName = Characters.getListOfCharacterNames()[selectedCharacterNum - 1];

        return new Characters(characterName);
    }

    public void selectStatsToCalculate(){
        int statSelection;
        boolean statSelectionDone = false;

        printConsoleLineSeparator("Select Stats To Calculate");
        displayStatsAvailableToSelect();
        System.out.println();

        while(!statSelectionDone){
            displayStatsCurrentlySelected("Current stats selected: ");
            System.out.println();
            System.out.print("Enter number to add/remove a selected stat to calculate for range of levels (1 - " + getStatSelectionTypes().length + "): ");
            statSelection = this.stdIn.nextInt();

            if (!(statSelection > 0 && statSelection <= getStatSelectionTypes().length)){
                printErrorMessage("Invalid Selection.");
            } else if(statSelection <= getStatsSelected().length){
                if (getStatSelectionTypes()[statSelection].equals(getStatsHpAndMpSelectionType())) {
                    if (!isHpAndMpStatSelected()) {
                        setHpAndMpSelected();
                    } else {
                        setStatsSelected(false, 2);
                    }
                } else if(statSelection == 1 && isHpAndMpStatSelected()) {
                    setStatsSelected(true, 1);
                    setStatsSelected(false, 2);
                } else if(statSelection == 2 && isHpAndMpStatSelected()) {
                    setStatsSelected(true, 0);
                    setStatsSelected(false, 2);
                } else {
                    setStatsSelected(!getStatsSelected()[statSelection - 1], statSelection - 1);
                }
            } else if(statSelection == getStatSelectionTypes().length - 2){
                setAllStatsSelected(true);
            } else if(statSelection == getStatSelectionTypes().length - 1){
                setAllStatsSelected(false);
            }

            if (isHpAndMpStatSelected() && (isHpOrMpStatsSelected())){
                setHpAndMpSelected();
            } else if (isHpMpStatsSelected()){
                setHpAndMpSelected();
            }

            if (statSelection == getStatSelectionTypes().length){
                if (areAnyStatsSelected()){
                    statSelectionDone = true;
                } else {
                    printErrorMessage("Error: No stats have been selected. Please select at least one stat...");
                }
            }
            System.out.println();
        }

    }

    public void setupCharacterStartingLevelAndStats(){
        boolean selectionValid = false;
        int startingLevel;
        int startingHp;
        int startingMp;
        int startingStr;
        int startingDex;
        int startingVit;
        int startingMag;
        int startingSpr;
        int startingLck;


        printConsoleLineSeparator("Configure Starting Level and Stats");
        displayCharacterInfo();
        System.out.println();
        if (getYesOrNoAnswer("Change " + getCharacterSelected().getCharacterName() + " starting level and stats? (y/n): ")){
            while (!selectionValid){
                System.out.print("Enter Starting Level (1 - 98): ");
                startingLevel = this.stdIn.nextInt();
                if (startingLevel > 0 && startingLevel < 99){
                    getCharacterSelected().setLevel(startingLevel);
                    selectionValid = true;
                } else {
                    printErrorMessage("\tError: " + startingLevel + " is not a valid starting Level");
                }
            }
            selectionValid = false;
            while (!selectionValid && (isHpOnlyStatSelected() || isHpAndMpStatSelected())){
                System.out.print("Enter Starting Level Base HP (1 - 9999): ");
                startingHp = this.stdIn.nextInt();
                if (startingHp > 1 && startingHp <= 9999){
                    getCharacterSelected().setBaseHp(startingHp);
                    selectionValid = true;
                } else {
                    printErrorMessage("\tError: " + startingHp + " is not a valid HP Value");
                }
            }
            selectionValid = false;
            while (!selectionValid && (isMpOnlyStatSelected() || isHpAndMpStatSelected())){
                System.out.print("Enter Starting Level Base MP (1 - 999): ");
                startingMp = this.stdIn.nextInt();
                if (startingMp > 1 && startingMp <= 999){
                    getCharacterSelected().setBaseMp(startingMp);
                    selectionValid = true;
                } else {
                    printErrorMessage("\tError: " + startingMp + " is not a valid MP Value");
                }
            }
            selectionValid = false;
            while (!selectionValid && isStrStatSelected()){
                System.out.print("Enter Starting Level Base Str (1 - 255): ");
                startingStr = this.stdIn.nextInt();
                if (startingStr > 1 && startingStr <= 255){
                    getCharacterSelected().setBaseStrength(startingStr);
                    selectionValid = true;
                } else {
                    printErrorMessage("\tError: " + startingStr + " is not a valid Base Str Value");
                }
            }
            selectionValid = false;
            while (!selectionValid && isDexStatSelected()){
                System.out.print("Enter Starting Level Base Dex (1 - 255): ");
                startingDex = this.stdIn.nextInt();
                if (startingDex > 1 && startingDex <= 255){
                    getCharacterSelected().setBaseDexterity(startingDex);
                    selectionValid = true;
                } else {
                    printErrorMessage("\tError: " + startingDex + " is not a valid Base Dex Value");
                }
            }
            selectionValid = false;
            while (!selectionValid && isVitStatSelected()){
                System.out.print("Enter Starting Level Base Vit (1 - 255): ");
                startingVit = this.stdIn.nextInt();
                if (startingVit > 1 && startingVit <= 255){
                    getCharacterSelected().setBaseVitality(startingVit);
                    selectionValid = true;
                } else {
                    printErrorMessage("\tError: " + startingVit + " is not a valid Base Vit Value");
                }
            }
            selectionValid = false;
            while (!selectionValid && isMagStatSelected()){
                System.out.print("Enter Starting Level Base Mag (1 - 255): ");
                startingMag = this.stdIn.nextInt();
                if (startingMag > 1 && startingMag <= 255){
                    getCharacterSelected().setBaseMagic(startingMag);
                    selectionValid = true;
                } else {
                    printErrorMessage("\tError: " + startingMag + " is not a valid Base Mag Value");
                }
            }
            selectionValid = false;
            while (!selectionValid && isSprStatSelected()){
                System.out.print("Enter starting Base Spr (1 - 255): ");
                startingSpr = this.stdIn.nextInt();
                if (startingSpr > 1 && startingSpr <= 255){
                    getCharacterSelected().setBaseSpirit(startingSpr);
                    selectionValid = true;
                } else {
                    printErrorMessage("\tError: " + startingSpr + " is not a valid Base Spr Value");
                }
            }
            selectionValid = false;
            while (!selectionValid && isLckStatSelected()){
                System.out.print("Enter starting Base Lck (1 - 255): ");
                startingLck = this.stdIn.nextInt();
                if (startingLck > 1 && startingLck <= 255){
                    getCharacterSelected().setBaseLuck(startingLck);
                    selectionValid = true;
                } else {
                    printErrorMessage("\tError: " + startingLck + " is not a valid Base Lck Value");
                }
            }
            System.out.println();
            displayCharacterInfo();
        }
    }

    public void setupDisplayResultsSelection(){
        boolean selectionValid = false;
        int selectedDisplayResults;


        printConsoleLineSeparator("Select Display Results");
        for (int i = 0; i < getDisplayResultsSelections().length; i++){
            System.out.println(getDisplayResultsSelections()[i]);
        }
        System.out.println();

        while (!selectionValid){
            System.out.print("Enter a number to select the display results(1 - " + getDisplayResultsSelections().length + "): ");

            selectedDisplayResults = this.stdIn.nextInt();
            if (selectedDisplayResults > 0 && selectedDisplayResults <= getDisplayResultsSelections().length){
                setDisplayResultsSelected(selectedDisplayResults - 1);  // -1 for indexing
                selectionValid = true;
            } else {
                printErrorMessage("\tError: " + selectedDisplayResults + " is not a valid Selection");
            }
        }
    }

    public void setupCharacterFinalLevelAndStats(){
        boolean selectionValid = false;
        int endLevel;
        int endMinHp;
        int endMaxHp;
        int usrHpValueEntered;
        int endMinMp;
        int endMaxMp;
        int usrMpValueEntered;
        int endMinStr;
        int endMaxStr;
        int usrStrValueEntered;
        int endMinDex;
        int endMaxDex;
        int usrDexValueEntered;
        int endMinVit;
        int endMaxVit;
        int usrVitValueEntered;
        int endMinMag;
        int endMaxMag;
        int usrMagValueEntered;
        int endMinSpr;
        int endMaxSpr;
        int usrSprValueEntered;
        int endMinLck;
        int endMaxLck;
        int usrLckValueEntered;

        printConsoleLineSeparator("Configure Final Level and Stats");

        while (!selectionValid){
            if (getCharacterSelected().getLevel() == 98){
                System.out.print("Enter Final Level (99): ");
            } else {
                System.out.print("Enter Final Level (" + (getCharacterSelected().getLevel() + 1) + " - 99): ");
            }

            endLevel = this.stdIn.nextInt();
            if (endLevel > getCharacterSelected().getLevel() && endLevel <= 99){
                setFinalLevel(endLevel);
                selectionValid = true;
            } else {
                printErrorMessage("\tError: " + endLevel + " is not a valid Final Level");
            }
        }
        selectionValid = false;

        if (getDisplayResultsSelected()[1]){ // Display All Required Stat(s) Values from Starting Level to End Level Stat(s) Max Value(s)
            if (getStatsSelected()[0]){ // HP (and not MP)
                this.endLevelStatsValues[0] = getMaxHpValueAtEndLevel(getCharacterSelected().getCharacterName(), getCharacterSelected().getLevel(), getFinalLevel(), getCharacterSelected().getBaseHp());
            }
            if (getStatsSelected()[1]){ // MP (and not HP)
                this.endLevelStatsValues[1] = getMaxMpValueAtEndLevel(getCharacterSelected().getCharacterName(), getCharacterSelected().getLevel(), getFinalLevel(), getCharacterSelected().getBaseMp());
            }
            if (getStatsSelected()[2]){ // HP and MP

            }
            if (getStatsSelected()[3]){ // Str
                this.endLevelStatsValues[2] = getMaxPrimaryStatValueAtEndLevel(getCharacterSelected().getCharacterName(), getCharacterSelected().getLevel(), getFinalLevel(), getCharacterSelected().getBaseStrength(), getStatSelectionTypes()[3]);
            }
            if (getStatsSelected()[4]){ // Dex
                this.endLevelStatsValues[3] = getMaxPrimaryStatValueAtEndLevel(getCharacterSelected().getCharacterName(), getCharacterSelected().getLevel(), getFinalLevel(), getCharacterSelected().getBaseDexterity(), getStatSelectionTypes()[4]);
            }
            if (getStatsSelected()[5]){ // Vit
                this.endLevelStatsValues[4] = getMaxPrimaryStatValueAtEndLevel(getCharacterSelected().getCharacterName(), getCharacterSelected().getLevel(), getFinalLevel(), getCharacterSelected().getBaseVitality(), getStatSelectionTypes()[5]);
            }
            if (getStatsSelected()[6]){ // Mag
                this.endLevelStatsValues[5] = getMaxPrimaryStatValueAtEndLevel(getCharacterSelected().getCharacterName(), getCharacterSelected().getLevel(), getFinalLevel(), getCharacterSelected().getBaseMagic(), getStatSelectionTypes()[6]);
            }
            if (getStatsSelected()[7]){ // Spr
                this.endLevelStatsValues[6] = getMaxPrimaryStatValueAtEndLevel(getCharacterSelected().getCharacterName(), getCharacterSelected().getLevel(), getFinalLevel(), getCharacterSelected().getBaseSpirit(), getStatSelectionTypes()[7]);
            }
            if (getStatsSelected()[8]){ // Lck
                this.endLevelStatsValues[7] = getMaxLckValueAtEndLevel(getCharacterSelected().getCharacterName(), getCharacterSelected().getLevel(), getFinalLevel(), getCharacterSelected().getBaseLuck());
            }
        } else if (getDisplayResultsSelected()[2]){ // Display All Required Stat(s) Values from Starting Level to End Level Stat(s) Specific Value(s)
            if (getStatsSelected()[0]){ // HP (and not MP)
                endMinHp = getMinHpValueAtEndLevel(getCharacterSelected().getCharacterName(), getCharacterSelected().getLevel(), getFinalLevel(), getCharacterSelected().getBaseHp());
                endMaxHp = getMaxHpValueAtEndLevel(getCharacterSelected().getCharacterName(), getCharacterSelected().getLevel(), getFinalLevel(), getCharacterSelected().getBaseHp());
                while (!selectionValid){
                    System.out.print("Enter End Level Base HP Value (" + endMinHp + " - " + endMaxHp + "): ");

                    usrHpValueEntered = this.stdIn.nextInt();
                    if (isEndLevelHpValueAvailable(getCharacterSelected().getCharacterName(), getCharacterSelected().getLevel(), getFinalLevel(), getCharacterSelected().getBaseHp(), usrHpValueEntered)){
                        this.endLevelStatsValues[0] = usrHpValueEntered;
                        selectionValid = true;
                    } else {
                        printErrorMessage("\tError: " + usrHpValueEntered + " is not a valid Base HP Value at level " + getCharacterSelected().getLevel());
                    }
                }
                selectionValid = false;
            }
            if (getStatsSelected()[1]){ // MP (and not HP)
                endMinMp = getMinMpValueAtEndLevel(getCharacterSelected().getCharacterName(), getCharacterSelected().getLevel(), getFinalLevel(), getCharacterSelected().getBaseMp());
                endMaxMp = getMaxMpValueAtEndLevel(getCharacterSelected().getCharacterName(), getCharacterSelected().getLevel(), getFinalLevel(), getCharacterSelected().getBaseMp());
                while (!selectionValid){
                    System.out.print("Enter End Level Base MP Value (" + endMinMp + " - " + endMaxMp + "): ");

                    usrMpValueEntered = this.stdIn.nextInt();
                    if (isEndLevelMpValueAvailable(getCharacterSelected().getCharacterName(), getCharacterSelected().getLevel(), getFinalLevel(), getCharacterSelected().getBaseMp(), usrMpValueEntered)){
                        this.endLevelStatsValues[1] = usrMpValueEntered;
                        selectionValid = true;
                    } else {
                        printErrorMessage("\tError: " + usrMpValueEntered + " is not a valid Base MP Value at level " + getCharacterSelected().getLevel());
                    }
                }
                selectionValid = false;
            }
            if (getStatsSelected()[2]){ // HP and MP

            }
            if (getStatsSelected()[3]){ // Str
                endMinStr = getMinPrimaryStatValueAtEndLevel(getCharacterSelected().getCharacterName(), getCharacterSelected().getLevel(), getFinalLevel(), getCharacterSelected().getBaseStrength(), getStatSelectionTypes()[3]);
                endMaxStr = getMaxPrimaryStatValueAtEndLevel(getCharacterSelected().getCharacterName(), getCharacterSelected().getLevel(), getFinalLevel(), getCharacterSelected().getBaseStrength(), getStatSelectionTypes()[3]);
                while (!selectionValid){
                    System.out.print("Enter End Level Base Str Value (" + endMinStr + " - " + endMaxStr + "): ");

                    usrStrValueEntered = this.stdIn.nextInt();
                    if (isEndLevelPrimaryStatValueAvailable(getCharacterSelected().getCharacterName(), getCharacterSelected().getLevel(), getFinalLevel(), getCharacterSelected().getBaseStrength(), usrStrValueEntered, getStatSelectionTypes()[3])){
                        this.endLevelStatsValues[2] = usrStrValueEntered;
                        selectionValid = true;
                    } else {
                        printErrorMessage("\tError: " + usrStrValueEntered + " is not a valid Base Str Value at level " + getCharacterSelected().getLevel());
                    }
                }
                selectionValid = false;
            }
            if (getStatsSelected()[4]){ // Dex
                endMinDex = getMinPrimaryStatValueAtEndLevel(getCharacterSelected().getCharacterName(), getCharacterSelected().getLevel(), getFinalLevel(), getCharacterSelected().getBaseDexterity(), getStatSelectionTypes()[4]);
                endMaxDex = getMaxPrimaryStatValueAtEndLevel(getCharacterSelected().getCharacterName(), getCharacterSelected().getLevel(), getFinalLevel(), getCharacterSelected().getBaseDexterity(), getStatSelectionTypes()[4]);
                while (!selectionValid){
                    System.out.print("Enter End Level Base Dex Value (" + endMinDex + " - " + endMaxDex + "): ");

                    usrDexValueEntered = this.stdIn.nextInt();
                    if (isEndLevelPrimaryStatValueAvailable(getCharacterSelected().getCharacterName(), getCharacterSelected().getLevel(), getFinalLevel(), getCharacterSelected().getBaseDexterity(), usrDexValueEntered, getStatSelectionTypes()[4])){
                        this.endLevelStatsValues[3] = usrDexValueEntered;
                        selectionValid = true;
                    } else {
                        printErrorMessage("\tError: " + usrDexValueEntered + " is not a valid Base Dex Value at level " + getCharacterSelected().getLevel());
                    }
                }
                selectionValid = false;
            }
            if (getStatsSelected()[5]){ // Vit
                endMinVit = getMinPrimaryStatValueAtEndLevel(getCharacterSelected().getCharacterName(), getCharacterSelected().getLevel(), getFinalLevel(), getCharacterSelected().getBaseVitality(), getStatSelectionTypes()[5]);
                endMaxVit = getMaxPrimaryStatValueAtEndLevel(getCharacterSelected().getCharacterName(), getCharacterSelected().getLevel(), getFinalLevel(), getCharacterSelected().getBaseVitality(), getStatSelectionTypes()[5]);
                while (!selectionValid){
                    System.out.print("Enter End Level Base Vit Value (" + endMinVit + " - " + endMaxVit + "): ");

                    usrVitValueEntered = this.stdIn.nextInt();
                    if (isEndLevelPrimaryStatValueAvailable(getCharacterSelected().getCharacterName(), getCharacterSelected().getLevel(), getFinalLevel(), getCharacterSelected().getBaseVitality(), usrVitValueEntered, getStatSelectionTypes()[5])){
                        this.endLevelStatsValues[4] = usrVitValueEntered;
                        selectionValid = true;
                    } else {
                        printErrorMessage("\tError: " + usrVitValueEntered + " is not a valid Base Vit Value at level " + getCharacterSelected().getLevel());
                    }
                }
                selectionValid = false;
            }
            if (getStatsSelected()[6]){ // Mag
                endMinMag = getMinPrimaryStatValueAtEndLevel(getCharacterSelected().getCharacterName(), getCharacterSelected().getLevel(), getFinalLevel(), getCharacterSelected().getBaseMagic(), getStatSelectionTypes()[6]);
                endMaxMag = getMaxPrimaryStatValueAtEndLevel(getCharacterSelected().getCharacterName(), getCharacterSelected().getLevel(), getFinalLevel(), getCharacterSelected().getBaseMagic(), getStatSelectionTypes()[6]);
                while (!selectionValid){
                    System.out.print("Enter End Level Base Mag Value (" + endMinMag + " - " + endMaxMag + "): ");

                    usrMagValueEntered = this.stdIn.nextInt();
                    if (isEndLevelPrimaryStatValueAvailable(getCharacterSelected().getCharacterName(), getCharacterSelected().getLevel(), getFinalLevel(), getCharacterSelected().getBaseMagic(), usrMagValueEntered, getStatSelectionTypes()[6])){
                        this.endLevelStatsValues[5] = usrMagValueEntered;
                        selectionValid = true;
                    } else {
                        printErrorMessage("\tError: " + usrMagValueEntered + " is not a valid Base Mag Value at level " + getCharacterSelected().getLevel());
                    }
                }
                selectionValid = false;
            }
            if (getStatsSelected()[7]){ // Spr
                endMinSpr = getMinPrimaryStatValueAtEndLevel(getCharacterSelected().getCharacterName(), getCharacterSelected().getLevel(), getFinalLevel(), getCharacterSelected().getBaseSpirit(), getStatSelectionTypes()[7]);
                endMaxSpr = getMaxPrimaryStatValueAtEndLevel(getCharacterSelected().getCharacterName(), getCharacterSelected().getLevel(), getFinalLevel(), getCharacterSelected().getBaseSpirit(), getStatSelectionTypes()[7]);
                while (!selectionValid){
                    System.out.print("Enter End Level Base Spr Value (" + endMinSpr + " - " + endMaxSpr + "): ");

                    usrSprValueEntered = this.stdIn.nextInt();
                    if (isEndLevelPrimaryStatValueAvailable(getCharacterSelected().getCharacterName(), getCharacterSelected().getLevel(), getFinalLevel(), getCharacterSelected().getBaseSpirit(), usrSprValueEntered, getStatSelectionTypes()[7])){
                        this.endLevelStatsValues[6] = usrSprValueEntered;
                        selectionValid = true;
                    } else {
                        printErrorMessage("\tError: " + usrSprValueEntered + " is not a valid Base Spr Value at level " + getCharacterSelected().getLevel());
                    }
                }
                selectionValid = false;
            }
            if (getStatsSelected()[8]){ // Lck
                endMinLck = getMinLckValueAtEndLevel(getCharacterSelected().getCharacterName(), getCharacterSelected().getLevel(), getFinalLevel(), getCharacterSelected().getBaseLuck());
                endMaxLck = getMaxLckValueAtEndLevel(getCharacterSelected().getCharacterName(), getCharacterSelected().getLevel(), getFinalLevel(), getCharacterSelected().getBaseLuck());
                while (!selectionValid){
                    System.out.print("Enter End Level Base Lck Value (" + endMinLck + " - " + endMaxLck + "): ");

                    usrLckValueEntered = this.stdIn.nextInt();
                    if (isEndLevelLckValueAvailable(getCharacterSelected().getCharacterName(), getCharacterSelected().getLevel(), getFinalLevel(), getCharacterSelected().getBaseLuck(), usrLckValueEntered)){
                        this.endLevelStatsValues[7] = usrLckValueEntered;
                        selectionValid = true;
                    } else {
                        printErrorMessage("\tError: " + usrLckValueEntered + " is not a valid Base Lck Value at level " + getCharacterSelected().getLevel());
                    }
                }
            }
        }
    }

    //******************************************************************************************************************
    //************************************** Format HP Level Arrays For Printing ***************************************
    //******************************************************************************************************************

    public ArrayList<ArrayList<Integer>> getPrintableEndLvlRangesOfHpValues(ArrayList<Integer> endLevelHpValues){
        ArrayList<ArrayList<Integer>> rangesOfHpValues = new ArrayList<>();
        int startHpRange;
        int endHpRange = 0;
        boolean valueCanIncreament = true;

        // Create multiple arrays containing (beginning - ending) value(s) per array
        for (int i = 0; i < endLevelHpValues.size(); i++){
            startHpRange = endLevelHpValues.get(i);
            while (valueCanIncreament && i < endLevelHpValues.size()){
                if (i == endLevelHpValues.size() - 1){
                    i++;
                    valueCanIncreament = false;
                } else if (endLevelHpValues.get(i) + 1 == endLevelHpValues.get(i+1)){
                    endHpRange = endLevelHpValues.get(i+1);
                    i++;
                } else {
                    valueCanIncreament = false;
                }
            }
            if (endHpRange != 0){
                rangesOfHpValues.add(new ArrayList<>(Arrays.asList(startHpRange, endHpRange)));
            } else {
                rangesOfHpValues.add(new ArrayList<>(Collections.singletonList(startHpRange)));
            }
            valueCanIncreament = true;
            endHpRange = 0;
        }
        return rangesOfHpValues;
    }

    public ArrayList<ArrayList<ArrayList<Integer>>> getPrintableRequiredRangesOfHpValues(ArrayList<ArrayList<Integer>> requiredHpValuesForRangedLevels){
        ArrayList<ArrayList<ArrayList<Integer>>> printableRequiredRangesOfHpValues = new ArrayList<>();

        for (ArrayList<Integer> levelHpValues: requiredHpValuesForRangedLevels){
            printableRequiredRangesOfHpValues.add(getPrintableEndLvlRangesOfHpValues(levelHpValues));
        }
        return printableRequiredRangesOfHpValues;
    }

    //******************************************************************************************************************
    //************************************** Format MP Level Arrays For Printing ***************************************
    //******************************************************************************************************************

    public ArrayList<ArrayList<Integer>> getPrintableEndLvlRangesOfMpValues(ArrayList<Integer> endLevelMpValues){
        ArrayList<ArrayList<Integer>> rangesOfMpValues = new ArrayList<>();
        int startMpRange;
        int endMpRange = 0;
        boolean valueCanIncreament = true;

        // Create multiple arrays containing (beginning - ending) value(s) per array
        for (int i = 0; i < endLevelMpValues.size(); i++){
            startMpRange = endLevelMpValues.get(i);
            while (valueCanIncreament && i < endLevelMpValues.size()){
                if (i == endLevelMpValues.size() - 1){
                    i++;
                    valueCanIncreament = false;
                } else if (endLevelMpValues.get(i) + 1 == endLevelMpValues.get(i+1)){
                    endMpRange = endLevelMpValues.get(i+1);
                    i++;
                } else {
                    valueCanIncreament = false;
                }
            }
            if (endMpRange != 0){
                rangesOfMpValues.add(new ArrayList<>(Arrays.asList(startMpRange, endMpRange)));
            } else {
                rangesOfMpValues.add(new ArrayList<>(Collections.singletonList(startMpRange)));
            }
            valueCanIncreament = true;
            endMpRange = 0;
        }
        return rangesOfMpValues;
    }

    public ArrayList<ArrayList<ArrayList<Integer>>> getPrintableRequiredRangesOfMpValues(ArrayList<ArrayList<Integer>> requiredMpValuesForRangedLevels){
        ArrayList<ArrayList<ArrayList<Integer>>> printableRequiredRangesOfMpValues = new ArrayList<>();

        for (ArrayList<Integer> levelMpValues: requiredMpValuesForRangedLevels){
            printableRequiredRangesOfMpValues.add(getPrintableEndLvlRangesOfMpValues(levelMpValues));
        }
        return printableRequiredRangesOfMpValues;
    }

    //******************************************************************************************************************
    //********************************* Format Primary Stats Level Arrays For Printing *********************************
    //******************************************************************************************************************

    public ArrayList<ArrayList<Integer>> getPrintableEndLvlRangesOfPrimaryStatValues(ArrayList<Integer> endLevelPrimaryStatValues){
        ArrayList<ArrayList<Integer>> rangesOfPrimaryStatValues = new ArrayList<>();
        int startPrimaryStatRange;
        int endPrimaryStatRange = 0;
        boolean valueCanIncreament = true;

        // Create multiple arrays containing (beginning - ending) value(s) per array
        for (int i = 0; i < endLevelPrimaryStatValues.size(); i++){
            startPrimaryStatRange = endLevelPrimaryStatValues.get(i);
            while (valueCanIncreament && i < endLevelPrimaryStatValues.size()){
                if (i == endLevelPrimaryStatValues.size() - 1){
                    i++;
                    valueCanIncreament = false;
                } else if (endLevelPrimaryStatValues.get(i) + 1 == endLevelPrimaryStatValues.get(i+1)){
                    endPrimaryStatRange = endLevelPrimaryStatValues.get(i+1);
                    i++;
                } else {
                    valueCanIncreament = false;
                }
            }
            if (endPrimaryStatRange != 0){
                rangesOfPrimaryStatValues.add(new ArrayList<>(Arrays.asList(startPrimaryStatRange, endPrimaryStatRange)));
            } else {
                rangesOfPrimaryStatValues.add(new ArrayList<>(Collections.singletonList(startPrimaryStatRange)));
            }
            valueCanIncreament = true;
            endPrimaryStatRange = 0;
        }
        return rangesOfPrimaryStatValues;
    }

    public ArrayList<ArrayList<ArrayList<Integer>>> getPrintableRequiredRangesOfPrimaryStatValues(ArrayList<ArrayList<Integer>> requiredPrimaryStatValuesForRangedLevels){
        ArrayList<ArrayList<ArrayList<Integer>>> printableRequiredRangesOfPrimaryStatValues = new ArrayList<>();

        for (ArrayList<Integer> levelPrimaryStatValues: requiredPrimaryStatValuesForRangedLevels){
            printableRequiredRangesOfPrimaryStatValues.add(getPrintableEndLvlRangesOfPrimaryStatValues(levelPrimaryStatValues));
        }
        return printableRequiredRangesOfPrimaryStatValues;
    }

    //******************************************************************************************************************
    //************************************** Format Lck Level Arrays For Printing **************************************
    //******************************************************************************************************************

    public ArrayList<ArrayList<Integer>> getPrintableEndLvlRangesOfLckValues(ArrayList<Integer> endLevelLckValues){
        ArrayList<ArrayList<Integer>> rangesOfLckValues = new ArrayList<>();
        int startLckRange;
        int endLckRange = 0;
        boolean valueCanIncreament = true;

        // Create multiple arrays containing (beginning - ending) value(s) per array
        for (int i = 0; i < endLevelLckValues.size(); i++){
            startLckRange = endLevelLckValues.get(i);
            while (valueCanIncreament && i < endLevelLckValues.size()){
                if (i == endLevelLckValues.size() - 1){
                    i++;
                    valueCanIncreament = false;
                } else if (endLevelLckValues.get(i) + 1 == endLevelLckValues.get(i+1)){
                    endLckRange = endLevelLckValues.get(i+1);
                    i++;
                } else {
                    valueCanIncreament = false;
                }
            }
            if (endLckRange != 0){
                rangesOfLckValues.add(new ArrayList<>(Arrays.asList(startLckRange, endLckRange)));
            } else {
                rangesOfLckValues.add(new ArrayList<>(Collections.singletonList(startLckRange)));
            }
            valueCanIncreament = true;
            endLckRange = 0;
        }
        return rangesOfLckValues;
    }

    public ArrayList<ArrayList<ArrayList<Integer>>> getPrintableRequiredRangesOfLckValues(ArrayList<ArrayList<Integer>> requiredLckValuesForRangedLevels){
        ArrayList<ArrayList<ArrayList<Integer>>> printableRequiredRangesOfLckValues = new ArrayList<>();

        for (ArrayList<Integer> levelLckValues: requiredLckValuesForRangedLevels){
            printableRequiredRangesOfLckValues.add(getPrintableEndLvlRangesOfLckValues(levelLckValues));
        }
        return printableRequiredRangesOfLckValues;
    }

    //******************************************************************************************************************
    //******************************************** Print HP Result Methods *********************************************
    //******************************************************************************************************************

    public void printAllPossibleHpValuesForEndLevel(int endLevel){
        // Initialize Variables
        ArrayList<Integer> endLevelHpValues = getAllPossibleHpValuesAtEndLevel(getCharacterSelected().getCharacterName(), getCharacterSelected().getLevel(), endLevel, getCharacterSelected().getBaseHp());
        ArrayList<ArrayList<Integer>> rangesOfHpValues = getPrintableEndLvlRangesOfHpValues(endLevelHpValues);
        String hpValuesOutputTitle = "Possible HP values:";
        int printLineCounter = 1;

        // Print Results

        PrintValuesOutputTitle(hpValuesOutputTitle);
        for (ArrayList<Integer> rangedHpValues: rangesOfHpValues){
            if (rangedHpValues.size() > 1){
                System.out.println(rangedHpValues.get(0) + " - " + rangedHpValues.get(1));
            } else {
                System.out.println(rangedHpValues.get(0));
            }
            if (printLineCounter < rangesOfHpValues.size()){
                PrintSubValuesAfterOutputTitle();
            }
            printLineCounter++;
        }
    }

    public void printRequiredHpValuesForEndLevelValue(ArrayList<ArrayList<ArrayList<Integer>>> rangesOfHpValues, int levelIndex){
        // Initialize Variables
        String hpValuesOutputTitle = "Possible HP values:";
        int printLineCounter = 1;

        // Print Results
        PrintValuesOutputTitle(hpValuesOutputTitle);
        for (ArrayList<Integer> rangedHpValues: rangesOfHpValues.get(levelIndex)){
            if (rangedHpValues.size() > 1){
                System.out.println(rangedHpValues.get(0) + " - " + rangedHpValues.get(1));
            } else {
                System.out.println(rangedHpValues.get(0));
            }
            if (printLineCounter < rangesOfHpValues.size()){
                PrintSubValuesAfterOutputTitle();
            }
            printLineCounter++;
        }

    }

    //******************************************************************************************************************
    //******************************************** Print MP Result Methods *********************************************
    //******************************************************************************************************************
    public void printAllPossibleMpValuesForEndLevel(int endLevel){
        // Initialize Variables
        ArrayList<Integer> endLevelMpValues = getAllPossibleMpValuesAtEndLevel(getCharacterSelected().getCharacterName(), getCharacterSelected().getLevel(), endLevel, getCharacterSelected().getBaseMp());
        ArrayList<ArrayList<Integer>> rangesOfMpValues = getPrintableEndLvlRangesOfMpValues(endLevelMpValues);
        String mpValuesOutputTitle = "Possible MP values:";
        int printLineCounter = 1;

        // Print Results
        PrintValuesOutputTitle(mpValuesOutputTitle);
        for (ArrayList<Integer> rangedMpValues: rangesOfMpValues){
            if (rangedMpValues.size() > 1){
                System.out.println(rangedMpValues.get(0) + " - " + rangedMpValues.get(1));
            } else {
                System.out.println(rangedMpValues.get(0));
            }
            if (printLineCounter < rangesOfMpValues.size()){
                PrintSubValuesAfterOutputTitle();
            }
            printLineCounter++;
        }
    }

    public void printRequiredMpValuesForEndLevelValue(ArrayList<ArrayList<ArrayList<Integer>>> rangesOfMpValues, int levelIndex){
        // Initialize Variables
        String mpValuesOutputTitle = "Possible MP values:";
        int printLineCounter = 1;

        // Print Results
        PrintValuesOutputTitle(mpValuesOutputTitle);
        for (ArrayList<Integer> rangedMpValues: rangesOfMpValues.get(levelIndex)){
            if (rangedMpValues.size() > 1){
                System.out.println(rangedMpValues.get(0) + " - " + rangedMpValues.get(1));
            } else {
                System.out.println(rangedMpValues.get(0));
            }
            if (printLineCounter < rangesOfMpValues.size()){
                PrintSubValuesAfterOutputTitle();
            }
            printLineCounter++;
        }

    }

    //******************************************************************************************************************
    //*************************************** Print Primary Stats Result Methods ***************************************
    //******************************************************************************************************************
    public void printAllPossiblePrimaryStatValuesForEndLevel(int endLevel, int startPrimStatValue, String primStatType){
        // Initialize Variables
        ArrayList<Integer> endLevelStrValues = getAllPossiblePrimaryStatValuesAtEndLevel(getCharacterSelected().getCharacterName(), getCharacterSelected().getLevel(), endLevel, startPrimStatValue, primStatType);
        ArrayList<ArrayList<Integer>> rangesOfPrimaryStatValues = getPrintableEndLvlRangesOfPrimaryStatValues(endLevelStrValues);
        String mpValuesOutputTitle = "Possible " + primStatType + " values:";
        int printLineCounter = 1;

        // Print Results
        PrintValuesOutputTitle(mpValuesOutputTitle);
        for (ArrayList<Integer> rangedPrimaryStatValues: rangesOfPrimaryStatValues){
            if (rangedPrimaryStatValues.size() > 1){
                System.out.println(rangedPrimaryStatValues.get(0) + " - " + rangedPrimaryStatValues.get(1));
            } else {
                System.out.println(rangedPrimaryStatValues.get(0));
            }
            if (printLineCounter < rangesOfPrimaryStatValues.size()){
                PrintSubValuesAfterOutputTitle();
            }
            printLineCounter++;
        }
    }

    public void printRequiredPrimaryStatValuesForEndLevelValue(ArrayList<ArrayList<ArrayList<Integer>>> rangesOfPrimaryStatValues, int levelIndex, String primaryStatType){
        // Initialize Variables
        String primStatValuesOutputTitle = "Possible " + primaryStatType + " values:";
        int printLineCounter = 1;

        // Print Results
        PrintValuesOutputTitle(primStatValuesOutputTitle);
        for (ArrayList<Integer> rangedMpValues: rangesOfPrimaryStatValues.get(levelIndex)){
            if (rangedMpValues.size() > 1){
                System.out.println(rangedMpValues.get(0) + " - " + rangedMpValues.get(1));
            } else {
                System.out.println(rangedMpValues.get(0));
            }
            if (printLineCounter < rangesOfPrimaryStatValues.size()){
                PrintSubValuesAfterOutputTitle();
            }
            printLineCounter++;
        }

    }

    //******************************************************************************************************************
    //******************************************** Print Lck Result Methods ********************************************
    //******************************************************************************************************************

    public void printAllPossibleLckValuesForEndLevel(int endLevel){
        // Initialize Variables
        ArrayList<Integer> endLevelLckValues = getAllPossibleLckValuesAtEndLevel(getCharacterSelected().getCharacterName(), getCharacterSelected().getLevel(), endLevel, getCharacterSelected().getBaseLuck());
        ArrayList<ArrayList<Integer>> rangesOfLckValues = getPrintableEndLvlRangesOfLckValues(endLevelLckValues);
        String hpValuesOutputTitle = "Possible Lck values:";
        int printLineCounter = 1;

        // Print Results

        PrintValuesOutputTitle(hpValuesOutputTitle);
        for (ArrayList<Integer> rangedLckValues: rangesOfLckValues){
            if (rangedLckValues.size() > 1){
                System.out.println(rangedLckValues.get(0) + " - " + rangedLckValues.get(1));
            } else {
                System.out.println(rangedLckValues.get(0));
            }
            if (printLineCounter < rangesOfLckValues.size()){
                PrintSubValuesAfterOutputTitle();
            }
            printLineCounter++;
        }
    }

    public void printRequiredLckValuesForEndLevelValue(ArrayList<ArrayList<ArrayList<Integer>>> rangesOfLckValues, int levelIndex){
        // Initialize Variables
        String lckValuesOutputTitle = "Possible Lck values:";
        int printLineCounter = 1;

        // Print Results
        PrintValuesOutputTitle(lckValuesOutputTitle);
        for (ArrayList<Integer> rangedLckValues: rangesOfLckValues.get(levelIndex)){
            if (rangedLckValues.size() > 1){
                System.out.println(rangedLckValues.get(0) + " - " + rangedLckValues.get(1));
            } else {
                System.out.println(rangedLckValues.get(0));
            }
            if (printLineCounter < rangesOfLckValues.size()){
                PrintSubValuesAfterOutputTitle();
            }
            printLineCounter++;
        }

    }

    //******************************************************************************************************************
    //********************************************** Print Console Methods *********************************************
    //******************************************************************************************************************
    public void printConsoleLineSeparator() {

        System.out.println();
        System.out.println(createConsoleLineSeparator());
        System.out.println(createConsoleLineSeparator());
        System.out.println(createConsoleLineSeparator());
        System.out.println();
    }

    public void printConsoleLineSeparator(String heading) {

        System.out.println();
        System.out.println(createConsoleLineSeparator());
        System.out.println(createConsoleLineSeparator(heading));
        System.out.println(createConsoleLineSeparator());
        System.out.println();
    }

    public void printDisplayCharacterLevelUpLine(String characterName, int levelUp){
        System.out.println(createConsoleLineSeparator(characterName + ": " + "Level " + levelUp));
    }

    public void PrintSubValuesAfterOutputTitle(){
        int maxHeadingLength = "Possible HP / MP Values: ".length();
        StringBuilder subHeading = new StringBuilder();
        while (subHeading.length() < maxHeadingLength){
            subHeading.append(" ");
        }
        System.out.print(subHeading);
    }

    public void PrintValuesOutputTitle(String heading){
        int maxHeadingLength = "Possible HP / MP Values: ".length();
        final int position = (maxHeadingLength - heading.length() - 1) / 2;
        final StringBuilder builder = new StringBuilder();
        builder.append(heading);
        while (builder.length() < maxHeadingLength) {
            builder.append(".");
        }
        System.out.print(builder.toString());
    }

    public void printFinalResults(){
        //this.characterSelected.setBaseHp(595);
        //this.characterSelected.setLevel(15);
        int level = getCharacterSelected().getLevel();
        int endLevel = getFinalLevel();
        int endLevelDesiredHp = 0;
        boolean endLevelGetMaxHpResult = true;

        printConsoleLineSeparator("Displaying Calculated Results");
        if (getDisplayResultsSelected()[0]){ // Display all possible values for ranged levels
            for (int levelUp = getCharacterSelected().getLevel() + 1; levelUp <= getFinalLevel(); levelUp++) {
                printDisplayCharacterLevelUpLine(getCharacterSelected().getCharacterName(), levelUp);
                if (getStatsSelected()[0]) { // HP (and not MP) is selected
                    printAllPossibleHpValuesForEndLevel(levelUp);
                    System.out.println();
                }
                if (getStatsSelected()[1]) { // MP (and not HP) is selected
                    printAllPossibleMpValuesForEndLevel(levelUp);
                    System.out.println();
                }
                if (getStatsSelected()[2]) { // HP and MP is selected

                }
                if (getStatsSelected()[3]) { // Str
                    printAllPossiblePrimaryStatValuesForEndLevel(levelUp, getCharacterSelected().getBaseStrength(), getStatSelectionTypes()[3]);
                    System.out.println();
                }
                if (getStatsSelected()[4]) { // Dex
                    printAllPossiblePrimaryStatValuesForEndLevel(levelUp, getCharacterSelected().getBaseDexterity(), getStatSelectionTypes()[4]);
                    System.out.println();
                }
                if (getStatsSelected()[5]) { // Vit
                    printAllPossiblePrimaryStatValuesForEndLevel(levelUp, getCharacterSelected().getBaseVitality(), getStatSelectionTypes()[5]);
                    System.out.println();
                }
                if (getStatsSelected()[6]) { // Mag
                    printAllPossiblePrimaryStatValuesForEndLevel(levelUp, getCharacterSelected().getBaseMagic(), getStatSelectionTypes()[6]);
                    System.out.println();
                }
                if (getStatsSelected()[7]) { // Spr
                    printAllPossiblePrimaryStatValuesForEndLevel(levelUp, getCharacterSelected().getBaseSpirit(), getStatSelectionTypes()[7]);
                    System.out.println();
                }
                if (getStatsSelected()[8]) { // Lck
                    printAllPossibleLckValuesForEndLevel(levelUp);
                    System.out.println();
                }
            }
        } else if (getDisplayResultsSelected()[1] || getDisplayResultsSelected()[2]){ // Display values required to get to specific value at end level
            ArrayList<ArrayList<ArrayList<Integer>>> rangesOfHpValues = new ArrayList<>();
            ArrayList<ArrayList<ArrayList<Integer>>> rangesOfMpValues = new ArrayList<>();
            ArrayList<ArrayList<ArrayList<Integer>>> rangesOfStrValues = new ArrayList<>();
            ArrayList<ArrayList<ArrayList<Integer>>> rangesOfDexValues = new ArrayList<>();
            ArrayList<ArrayList<ArrayList<Integer>>> rangesOfVitValues = new ArrayList<>();
            ArrayList<ArrayList<ArrayList<Integer>>> rangesOfMagValues = new ArrayList<>();
            ArrayList<ArrayList<ArrayList<Integer>>> rangesOfSprValues = new ArrayList<>();
            ArrayList<ArrayList<ArrayList<Integer>>> rangesOfLckValues = new ArrayList<>();

            // Initialize result array(s)
            if (getStatsSelected()[0]) { // HP (and not MP) is selected
                if (getDisplayResultsSelected()[1]) { // Display max value selected
                    setEndLevelMaxStatsValuesSelected(true, 0);
                }
                ArrayList<ArrayList<Integer>> requiredHpValuesForRangedLevels = calculateRangeLevelsHpResults(getCharacterSelected().getCharacterName(), getCharacterSelected().getLevel(), endLevel, getCharacterSelected().getBaseHp(), getEndLevelStatsValues()[0]);
                rangesOfHpValues = getPrintableRequiredRangesOfHpValues(requiredHpValuesForRangedLevels);
            }
            if (getStatsSelected()[1]) { // MP (and not HP) is selected
                if (getDisplayResultsSelected()[1]) { // Display max value selected
                    setEndLevelMaxStatsValuesSelected(true, 1);
                }
                ArrayList<ArrayList<Integer>> requiredMpValuesForRangedLevels = calculateRangeLevelsMpResults(getCharacterSelected().getCharacterName(), getCharacterSelected().getLevel(), endLevel, getCharacterSelected().getBaseMp(), getEndLevelStatsValues()[1]);
                rangesOfMpValues = getPrintableRequiredRangesOfMpValues(requiredMpValuesForRangedLevels);
            }
            if (getStatsSelected()[2]) { // HP and MP is selected

            }
            if (getStatsSelected()[3]) { // Str
                if (getDisplayResultsSelected()[1]) { // Display max value selected
                    setEndLevelMaxStatsValuesSelected(true, 2);
                }
                ArrayList<ArrayList<Integer>> requiredPrimaryStatValuesForRangedLevels = calculateRangeLevelsPrimaryStatResults(getCharacterSelected().getCharacterName(), getCharacterSelected().getLevel(), endLevel, getCharacterSelected().getBaseStrength(), getEndLevelStatsValues()[2], getEndLevelMaxStatsValuesSelected()[2], getStatSelectionTypes()[3]);
                rangesOfStrValues = getPrintableRequiredRangesOfPrimaryStatValues(requiredPrimaryStatValuesForRangedLevels);
            }
            if (getStatsSelected()[4]) { // Dex
                if (getDisplayResultsSelected()[1]) { // Display max value selected
                    setEndLevelMaxStatsValuesSelected(true, 3);
                }
                ArrayList<ArrayList<Integer>> requiredPrimaryStatValuesForRangedLevels = calculateRangeLevelsPrimaryStatResults(getCharacterSelected().getCharacterName(), getCharacterSelected().getLevel(), endLevel, getCharacterSelected().getBaseDexterity(), getEndLevelStatsValues()[3], getEndLevelMaxStatsValuesSelected()[3], getStatSelectionTypes()[4]);
                rangesOfDexValues = getPrintableRequiredRangesOfPrimaryStatValues(requiredPrimaryStatValuesForRangedLevels);
            }
            if (getStatsSelected()[5]) { // Vit
                if (getDisplayResultsSelected()[1]) { // Display max value selected
                    setEndLevelMaxStatsValuesSelected(true, 4);
                }
                ArrayList<ArrayList<Integer>> requiredPrimaryStatValuesForRangedLevels = calculateRangeLevelsPrimaryStatResults(getCharacterSelected().getCharacterName(), getCharacterSelected().getLevel(), endLevel, getCharacterSelected().getBaseVitality(), getEndLevelStatsValues()[4], getEndLevelMaxStatsValuesSelected()[4], getStatSelectionTypes()[5]);
                rangesOfVitValues = getPrintableRequiredRangesOfPrimaryStatValues(requiredPrimaryStatValuesForRangedLevels);
            }
            if (getStatsSelected()[6]) { // Mag
                if (getDisplayResultsSelected()[1]) { // Display max value selected
                    setEndLevelMaxStatsValuesSelected(true, 5);
                }
                ArrayList<ArrayList<Integer>> requiredPrimaryStatValuesForRangedLevels = calculateRangeLevelsPrimaryStatResults(getCharacterSelected().getCharacterName(), getCharacterSelected().getLevel(), endLevel, getCharacterSelected().getBaseSpirit(), getEndLevelStatsValues()[5], getEndLevelMaxStatsValuesSelected()[5], getStatSelectionTypes()[6]);
                rangesOfMagValues = getPrintableRequiredRangesOfPrimaryStatValues(requiredPrimaryStatValuesForRangedLevels);
            }
            if (getStatsSelected()[7]) { // Spr
                if (getDisplayResultsSelected()[1]) { // Display max value selected
                    setEndLevelMaxStatsValuesSelected(true, 6);
                }
                ArrayList<ArrayList<Integer>> requiredPrimaryStatValuesForRangedLevels = calculateRangeLevelsPrimaryStatResults(getCharacterSelected().getCharacterName(), getCharacterSelected().getLevel(), endLevel, getCharacterSelected().getBaseMagic(), getEndLevelStatsValues()[6], getEndLevelMaxStatsValuesSelected()[6], getStatSelectionTypes()[7]);
                rangesOfSprValues = getPrintableRequiredRangesOfPrimaryStatValues(requiredPrimaryStatValuesForRangedLevels);
            }
            if (getStatsSelected()[8]) { // Lck
                if (getDisplayResultsSelected()[1]) { // Display max value selected
                    setEndLevelMaxStatsValuesSelected(true, 7);
                }
                ArrayList<ArrayList<Integer>> requiredLckValuesForRangedLevels = calculateRangeLevelsLckResults(getCharacterSelected().getCharacterName(), getCharacterSelected().getLevel(), endLevel, getCharacterSelected().getBaseLuck(), getEndLevelStatsValues()[7], getEndLevelMaxStatsValuesSelected()[7]);
                rangesOfLckValues = getPrintableRequiredRangesOfLckValues(requiredLckValuesForRangedLevels);
            }

            // Print result array(s)
            for (int levelUp = getCharacterSelected().getLevel(), printResultsCounter = 0; levelUp <= getFinalLevel(); levelUp++, printResultsCounter++) {
                printDisplayCharacterLevelUpLine(getCharacterSelected().getCharacterName(), levelUp);
                if (getStatsSelected()[0]) { // HP (and not MP) is selected
                    printRequiredHpValuesForEndLevelValue(rangesOfHpValues, printResultsCounter);
                    System.out.println();
                }
                if (getStatsSelected()[1]) { // MP (and not HP) is selected
                    printRequiredMpValuesForEndLevelValue(rangesOfMpValues, printResultsCounter);
                    System.out.println();
                }
                if (getStatsSelected()[2]) { // HP and MP is selected

                }
                if (getStatsSelected()[3]) { // Str
                    printRequiredPrimaryStatValuesForEndLevelValue(rangesOfStrValues, printResultsCounter, getStatSelectionTypes()[3]);
                    System.out.println();
                }
                if (getStatsSelected()[4]) { // Dex
                    printRequiredPrimaryStatValuesForEndLevelValue(rangesOfDexValues, printResultsCounter, getStatSelectionTypes()[4]);
                    System.out.println();
                }
                if (getStatsSelected()[5]) { // Vit
                    printRequiredPrimaryStatValuesForEndLevelValue(rangesOfVitValues, printResultsCounter, getStatSelectionTypes()[5]);
                    System.out.println();
                }
                if (getStatsSelected()[6]) { // Mag
                    printRequiredPrimaryStatValuesForEndLevelValue(rangesOfMagValues, printResultsCounter, getStatSelectionTypes()[6]);
                    System.out.println();
                }
                if (getStatsSelected()[7]) { // Spr
                    printRequiredPrimaryStatValuesForEndLevelValue(rangesOfSprValues, printResultsCounter, getStatSelectionTypes()[7]);
                    System.out.println();
                }
                if (getStatsSelected()[8]) { // Lck
                    printRequiredLckValuesForEndLevelValue(rangesOfLckValues, printResultsCounter);
                    System.out.println();
                }
            }
        }

        //printAllPossibleHpValuesForEndLevel(endLevel);
        //calculator.printMaxHpResultForEndLevel(cloud, endLevel);
        //calculator.printAvailableHpValuesForAllLevelUps(cloud, endLevel);
        //calculator.printLevelHpResultPathsToEndResult(cloud, endLevel, endLevelDesiredHp, endLevelGetMaxHpResult);
        printConsoleLineSeparator();
    }

    //******************************************************************************************************************
    //********************************************* Methods no longer used *********************************************
    //******************************************************************************************************************
    /*
    public void calculateAndPrintHpLevelUpResults(Characters character, int endLevel, int endLevelDesiredHp, boolean endLevelGetMaxHpResult){
        System.out.println("Calculating results...");
        ArrayList<ArrayList<Integer>> hpResults = calculateRangeLevelsHpResultsBruteForce(character.getCharacterName(), character.getLevel(), endLevel, character.getBaseHp(), endLevelDesiredHp, endLevelGetMaxHpResult);
        System.out.println("Calculations completed.");
        System.out.println("Displaying results:");
        if (!hpResults.isEmpty()) {
            printRangeHpLevels(character.getLevel(), hpResults);
        }
    }

    public void printRangeHpLevels(int startLevel, ArrayList<ArrayList<Integer>> hpLevelUpResults){
        if (hpLevelUpResults.size() < 5000){ // print arrays to output
            printRangeHpLevelsOutput(startLevel, hpLevelUpResults);
        } else { // print arrays to file(s)
            try {
                createRangeHpLevelFiles(startLevel, hpLevelUpResults);
            } catch (Exception e){
                System.out.println("Could not create file(s).");
            }

        }
    }

    public void printRangeHpLevelsOutput(int startLevel, ArrayList<ArrayList<Integer>> hpLevelUpResults){
        ArrayList<String> printResults = new ArrayList<>();
        //printResults.add(getStartLevelRangeDisplayLine(startLevel));

        for (int i = 0; i < hpLevelUpResults.size(); i++){
            String levelRangeResultLine = new String();
            int levelUpCounter = startLevel + 1;
            for (int j = 0; j < hpLevelUpResults.get(i).size(); j++){
                if (j != hpLevelUpResults.get(i).size() - 1) {
                    levelRangeResultLine += "lvl " + levelUpCounter + ": " + hpLevelUpResults.get(i).get(j) + " | ";
                    levelUpCounter++;
                } else {
                    levelRangeResultLine += "lvl " + levelUpCounter + ": " + hpLevelUpResults.get(i).get(j);
                }
            }
            printResults.add(levelRangeResultLine);
        }
        for (String result: printResults){
            System.out.println(result);
        }
    }
    */
    //******************************************************************************************************************
    //*********************************************** Start Program Here ***********************************************
    //******************************************************************************************************************
    public static void main(String[] args) {
        CalculateRangedLevelsConsoleInterface calculator = new CalculateRangedLevelsConsoleInterface();

        calculator.printFinalResults();
    }
}

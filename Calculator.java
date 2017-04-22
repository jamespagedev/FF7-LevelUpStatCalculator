/**
 * Created by James Page on 2/24/2016.
 */
package Main;

import java.util.Scanner;

public class Calculator extends CalculateFormulas
{
    Scanner stdIn = new Scanner(System.in);
    CalculateFormulas Formulas = new CalculateFormulas();

    protected static final String[] Characters = {"Cloud", "Barret", "Tifa", "Aeris", "Red XIII", "Yuffie", "Cait Sith", "Vincent", "Cid"};
    private static final int[] CharacterSelection = {1, 2, 3, 4, 5, 6, 7, 8, 9};
    private static final String[] Stats = {"Hit Points", "Magic Points", "Strength", "Vitality", "Magic", "Spirit", "Dex", "Luck", "All"};
    private static final int[] StatsSelection = {1, 2, 3, 4, 5, 6, 7, 8, 9};
    private String CharacterSelected;
    private String StatsSelected;
    private int StatValue;
    private int NextLevelUp;
    protected static final int[][] CharacterHpMpBaseGrdLevelRanges =
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
    private int LevelUpElementInLevelBracket;
    private static final int[][] CharactersHpGradient =
        {
            {19, 42, 72, 100, 121, 137, 120, 98},
            {22, 45, 82, 118, 143, 143, 115, 95},
            {19, 38, 64, 96, 121, 131, 117, 92},
            {17, 36, 65, 93, 114, 126, 113, 93},
            {21, 45, 75, 105, 126, 134, 119, 97},
            {18, 37, 64, 89, 111, 127, 120, 96},
            {24, 51, 80, 111, 141, 138, 99, 72},
            {18, 41, 67, 86, 110, 123, 120, 92},
            {23, 44, 73, 107, 125, 129, 115, 93}
        }; // Order of arrays must match order of Characters array {"Cloud", "Barret", "Tifa", "Aeris", "Red XIII", "Yuffie", "Cait Sith", "Vincent", "Cid"}
    private static final int[][] CharactersHpBase =
        {
            {200, -40, -640, -1440, -2280, -3080, -2040, -200},
            {200, 0, -760, -1840, -2840, -2840, -1160, 600},
            {200, 0, -520, -1520, -2520, -3000, -2160, -80},
            {160, 0, -560, -1400, -2240, -2880, -2080, -400},
            {200, -40, -640, -1520, -2360, -2760, -1840, -80},
            {200, 0, -560, -1320, -2160, -2960, -2560, -520},
            {200, -80, -640, -1560, -2760, -2600, -240, 2000},
            {160, -80, -600, -1160, -2120, -2800, -2640, -400},
            {200, -40, -640, -1640, -2360, -2560, -1720, 0}
        }; // Order of arrays must match order of Characters array {"Cloud", "Barret", "Tifa", "Aeris", "Red XIII", "Yuffie", "Cait Sith", "Vincent", "Cid"}
    private static final int[][] CharactersMpGradient =
        {
            {64, 78, 90, 101, 112, 112, 96, 73},
            {57, 67, 77, 90, 102, 100, 84, 63},
            {60, 70, 84, 94, 104, 104, 92, 72},
            {70, 84, 99, 112, 124, 120, 105, 82},
            {58, 75, 86, 97, 108, 112, 94, 66},
            {58, 72, 80, 93, 106, 110, 85, 72},
            {60, 75, 83, 97, 108, 108, 94, 70},
            {63, 80, 90, 96, 100, 105, 97, 84},
            {54, 75, 83, 87, 94, 104, 89, 69}
        }; // Order of arrays must match order of Characters array {"Cloud", "Barret", "Tifa", "Aeris", "Red XIII", "Yuffie", "Cait Sith", "Vincent", "Cid"}
    private static final int[][] CharactersMpBase =
        {
            {12, 0, -26, -58, -102, -102, -4, 180},
            {10, 0, -20, -60, -108, -96, 0, 170},
            {10, 0, -28, -58, -98, -98, -26, 136},
            {16, 0, -30, -68, -116, -96, -6, 188},
            {12, -6, -28, -60, -104, -126, -16, 210},
            {10, -2, -20, -58, -110, -130, 20, 126},
            {12, -2, -20, -60, -104, -104, -20, 178},
            {12, -6, -26, -44, -60, -86, 38, 74},
            {10, -12, -26, -38, -66, -116, -24, 140}
        }; // Order of arrays must match order of Characters array {"Cloud", "Barret", "Tifa", "Aeris", "Red XIII", "Yuffie", "Cait Sith", "Vincent", "Cid"}
    private int CharacterHpGradientSelector;
    private int CharacterHpBaseSelector;
    private int CharacterMpGradientSelector;
    private int CharacterMpBaseSelector;
    private int HpGradient;
    private int MpGradient;
    private int HpBase;
    private int MpBase;
    private int minRandomNumber = 1;
    private int maxRandomNumber = 8;
    private int BaseLine;
    private int MaxDifference;


    //******************************************************************

    public Calculator()
    {
        SelectCharacter();
        SelectStats();
        SetStatValue();
        SetNextLevel();
        SetStatLevelUpInfo();
    }

    //******************************************************************

    private void SelectCharacter()
    {
        PrintCharacterSelectionMessage();
        int CharacterSelectedNumber = stdIn.nextInt();

        this.CharacterSelected = this.Characters[CharacterSelectedNumber - 1];
    }

    private void SelectStats()
    {
        PrintSelectStatsMessage();
        int StatsSelectionNumber = stdIn.nextInt();

        this.StatsSelected = Stats[StatsSelectionNumber - 1];
    }

    private void SetNextLevel()
    {
        System.out.print("Enter next level up number (2 - 99): ");
        this.NextLevelUp = stdIn.nextInt();
    }

    private void SetStatValue()
    {
        System.out.print("Enter current " + this.StatsSelected + " stat points: ");
        this.StatValue = stdIn.nextInt();
    }

    private void SetStatLevelUpInfo()
    {
        if (Stats[0].equals(this.StatsSelected)) // Hit Points Selected
        {
            SelectLevelUpBracket();
            SelectCharacterHpGradient();
            SelectCharacterHpBase();
        }
        else if (Stats[1].equals(this.StatsSelected)) // Magic Points Selected
        {
            SelectLevelUpBracket();
            SelectCharacterMpGradient();
            SelectCharacterMpBase();
        }
    }

    public void SelectLevelUpBracket()
    {
        for (int levelBracket = 0; levelBracket < CharacterHpMpBaseGrdLevelRanges.length;levelBracket++)
        {
            for (int levelBracketElement = 0; levelBracketElement < CharacterHpMpBaseGrdLevelRanges[levelBracket].length; levelBracketElement++)
            {
                if (CharacterHpMpBaseGrdLevelRanges[levelBracket][levelBracketElement] == this.NextLevelUp)
                {
                    this.LevelUpElementInLevelBracket = levelBracket;
                }
            }
        }
    }

    public void SelectCharacterHpGradient()
    {
        if (this.CharacterSelected.equals(Characters[0]))
        {
            this.CharacterHpGradientSelector = 0;
        }
        else if (this.CharacterSelected.equals(Characters[1]))
        {
            this.CharacterHpGradientSelector = 1;
        }
        else if (this.CharacterSelected.equals(Characters[2]))
        {
            this.CharacterHpGradientSelector = 2;
        }
        else if (this.CharacterSelected.equals(Characters[3]))
        {
            this.CharacterHpGradientSelector = 3;
        }
        else if (this.CharacterSelected.equals(Characters[4]))
        {
            this.CharacterHpGradientSelector = 4;
        }
        else if (this.CharacterSelected.equals(Characters[5]))
        {
            this.CharacterHpGradientSelector = 5;
        }
        else if (this.CharacterSelected.equals(Characters[6]))
        {
            this.CharacterHpGradientSelector = 6;
        }
        else if (this.CharacterSelected.equals(Characters[7]))
        {
            this.CharacterHpGradientSelector = 7;
        }
        else if (this.CharacterSelected.equals(Characters[8]))
        {
            this.CharacterHpGradientSelector = 8;
        }
        SelectHpGradient();
    }

    public void SelectHpGradient()
    {
        this.HpGradient = CharactersHpGradient[this.CharacterHpGradientSelector][this.LevelUpElementInLevelBracket];
    }

    public void SelectCharacterHpBase()
    {
        if (this.CharacterSelected.equals(Characters[0]))
        {
            this.CharacterHpBaseSelector = 0;
        }
        else if (this.CharacterSelected.equals(Characters[1]))
        {
            this.CharacterHpBaseSelector = 1;
        }
        else if (this.CharacterSelected.equals(Characters[2]))
        {
            this.CharacterHpBaseSelector = 2;
        }
        else if (this.CharacterSelected.equals(Characters[3]))
        {
            this.CharacterHpBaseSelector = 3;
        }
        else if (this.CharacterSelected.equals(Characters[4]))
        {
            this.CharacterHpBaseSelector = 4;
        }
        else if (this.CharacterSelected.equals(Characters[5]))
        {
            this.CharacterHpBaseSelector = 5;
        }
        else if (this.CharacterSelected.equals(Characters[6]))
        {
            this.CharacterHpBaseSelector = 6;
        }
        else if (this.CharacterSelected.equals(Characters[7]))
        {
            this.CharacterHpBaseSelector = 7;
        }
        else if (this.CharacterSelected.equals(Characters[8]))
        {
            this.CharacterHpBaseSelector = 8;
        }
        SelectHpBase();
    }

    public void SelectHpBase()
    {
        this.HpBase = CharactersHpBase[this.CharacterHpBaseSelector][this.LevelUpElementInLevelBracket];
    }

    public void SelectCharacterMpGradient()
    {
        if (this.CharacterSelected.equals(Characters[0]))
        {
            this.CharacterMpGradientSelector = 0;
        }
        else if (this.CharacterSelected.equals(Characters[1]))
        {
            this.CharacterMpGradientSelector = 1;
        }
        else if (this.CharacterSelected.equals(Characters[2]))
        {
            this.CharacterMpGradientSelector = 2;
        }
        else if (this.CharacterSelected.equals(Characters[3]))
        {
            this.CharacterMpGradientSelector = 3;
        }
        else if (this.CharacterSelected.equals(Characters[4]))
        {
            this.CharacterMpGradientSelector = 4;
        }
        else if (this.CharacterSelected.equals(Characters[5]))
        {
            this.CharacterMpGradientSelector = 5;
        }
        else if (this.CharacterSelected.equals(Characters[6]))
        {
            this.CharacterMpGradientSelector = 6;
        }
        else if (this.CharacterSelected.equals(Characters[7]))
        {
            this.CharacterMpGradientSelector = 7;
        }
        else if (this.CharacterSelected.equals(Characters[8]))
        {
            this.CharacterMpGradientSelector = 8;
        }
        SelectMpGradient();
    }

    public void SelectMpGradient()
    {
        this.MpGradient = CharactersMpGradient[this.CharacterMpGradientSelector][this.LevelUpElementInLevelBracket];
    }

    public void SelectCharacterMpBase()
    {
        if (this.CharacterSelected.equals(Characters[0]))
        {
            this.CharacterMpBaseSelector = 0;
        }
        else if (this.CharacterSelected.equals(Characters[1]))
        {
            this.CharacterMpBaseSelector = 1;
        }
        else if (this.CharacterSelected.equals(Characters[2]))
        {
            this.CharacterMpBaseSelector = 2;
        }
        else if (this.CharacterSelected.equals(Characters[3]))
        {
            this.CharacterMpBaseSelector = 3;
        }
        else if (this.CharacterSelected.equals(Characters[4]))
        {
            this.CharacterMpBaseSelector = 4;
        }
        else if (this.CharacterSelected.equals(Characters[5]))
        {
            this.CharacterMpBaseSelector = 5;
        }
        else if (this.CharacterSelected.equals(Characters[6]))
        {
            this.CharacterMpBaseSelector = 6;
        }
        else if (this.CharacterSelected.equals(Characters[7]))
        {
            this.CharacterMpBaseSelector = 7;
        }
        else if (this.CharacterSelected.equals(Characters[8]))
        {
            this.CharacterMpBaseSelector = 8;
        }
        SelectMpBase();
    }

    public void SelectMpBase()
    {
        this.MpBase = CharactersMpBase[this.CharacterMpBaseSelector][this.LevelUpElementInLevelBracket];
    }

    //******************************************************************

    public String GetCharacterSelected()
    {
        return this.CharacterSelected;
    }

    public String GetStats()
    {
        return this.StatsSelected;
    }

    public int GetNextLevelUp()
    {
        return this.NextLevelUp;
    }

    public int GetStatValue()
    {
        return this.StatValue;
    }

    public int GetHpGradient()
    {
        return this.HpGradient;
    }

    public int GetHpBase()
    {
        return this.HpBase;
    }

    public int GetMpGradient()
    {
        return this.MpGradient;
    }

    public int GetMpBase()
    {
        return this.MpBase;
    }

    public int GetMaxRandomNumber()
    {
        return this.maxRandomNumber;
    }

    public int GetBaseLine()
    {
        return this.BaseLine;
    }

    public int GetMaxDifference()
    {
        return this.MaxDifference;
    }

    //******************************************************************

    private void PrintCharacterSelectionMessage()
    {
        for (int CharacterNum = 0; CharacterNum < CharacterSelection.length;CharacterNum++)
        {
            System.out.println(CharacterSelection[CharacterNum] + " - " + Characters[CharacterNum]);
        }
        System.out.print("Enter number to select the character: ");
    }

    private void PrintSelectStatsMessage()
    {
        for (int StatsNum = 0; StatsNum < StatsSelection.length;StatsNum++)
        {
            System.out.println(StatsSelection[StatsNum] + " - " + Stats[StatsNum]);
        }
        System.out.print("Enter number to select the Stat(s): ");
    }

    public void PrintCharacterSelected()
    {
        System.out.print(this.CharacterSelected);
    }

    public void PrintSelectedStats()
    {
        System.out.print(this.StatsSelected);
    }

    public void PrintResults()
    {
        if (Stats[0].equals(this.StatsSelected)) // Hit Points Selected
        {
            PrintHpLevelUpResults();
        }
        else if (Stats[1].equals(this.StatsSelected)) // Magic Points Selected
        {
            PrintMpLevelUpResults();
        }
    }

    private void PrintHpLevelUpResults()
    {
        this.BaseLine = Formulas.GetHpBaseLine(this.HpBase, this.NextLevelUp, this.HpGradient);

        for (int randomNumber = this.minRandomNumber; randomNumber <= this.maxRandomNumber; randomNumber++)
        {
            int difference = Formulas.GetDifference(randomNumber, this.BaseLine, this.StatValue);
            double StatIncreasePercentage = Formulas.GetHpStatIncreasePercentage(difference);
            int possibleHpIncrease = (int) ((double)(this.HpGradient * StatIncreasePercentage));
            int hpIncreaseTotal = this.StatValue + possibleHpIncrease;
            int hpIncreaseTotalWithStarterMateria = hpIncreaseTotal - (int)((double)(hpIncreaseTotal * 0.04));
            if (randomNumber == this.minRandomNumber)
            {
                // For Cloud
                System.out.println("(Current HP " + this.StatValue + ") Minimum HP increase chance " + StatIncreasePercentage + "0% of " + this.HpGradient + ":::: Total HP upon level with starter materia = <" +  hpIncreaseTotalWithStarterMateria + "> | Total Base HP upon level = <" + hpIncreaseTotal + ">");
            }
            else if (randomNumber == this.maxRandomNumber)
            {
                // For Cloud
                System.out.println("(Current HP " + this.StatValue + ") Maximum HP increase chance " + StatIncreasePercentage + "0% of " + this.HpGradient + ":::: Total HP upon level with starter materia = <" +  hpIncreaseTotalWithStarterMateria + "> | Total Base HP upon level = <" + hpIncreaseTotal + ">");
            }
            else
            {
                // For Cloud
                System.out.println("(Current HP " + this.StatValue + ") HP increase chance " + StatIncreasePercentage + "0% of " + this.HpGradient + ":::: Total HP upon level with starter materia = <" +  hpIncreaseTotalWithStarterMateria + "> | Total Base HP upon level = <" + hpIncreaseTotal + ">");
            }

        }

    }

    private void PrintMpLevelUpResults()
    {
        this.BaseLine = Formulas.GetMpBaseLine(this.MpBase, this.NextLevelUp, this.MpGradient);
        int mpBaseGain = Formulas.GetMpBaseGain(this.NextLevelUp, this.MpGradient);

        for (int randomNumber = this.minRandomNumber; randomNumber <= this.maxRandomNumber; randomNumber++)
        {
            int difference = Formulas.GetDifference(randomNumber, this.BaseLine, this.StatValue);
            double statIncreasePercentage = Formulas.GetMpStatIncreasePercentage(difference);
            int possibleMpIncrease = (int) ((double)(mpBaseGain * statIncreasePercentage));
            int mpIncreaseTotal = this.StatValue + possibleMpIncrease;
            int mpIncreaseTotalWithStarterMateria = mpIncreaseTotal + (int)((double)(mpIncreaseTotal * 0.04));
            if (randomNumber == this.minRandomNumber)
            {
                // For Cloud
                System.out.println("(Current MP " + this.StatValue + ") Minimum MP increase chance" + statIncreasePercentage + "0% of " + this.MpGradient + ":::: Total MP upon level with starter materia = <" +  mpIncreaseTotalWithStarterMateria + "> | Total Base MP upon level = <" + mpIncreaseTotal + ">");
            }
            else if (randomNumber == this.maxRandomNumber)
            {
                // For Cloud
                System.out.println("(Current MP " + this.StatValue + ") Maximum MP increase chance" + statIncreasePercentage + "0% of " + this.MpGradient + ":::: Total MP upon level with starter materia = <" +  mpIncreaseTotalWithStarterMateria + "> | Total Base MP upon level = <" + mpIncreaseTotal + ">");
            }
            else
            {
                // For Cloud
                System.out.println("(Current MP " + this.StatValue + ") MP increase chance" + statIncreasePercentage + "0% of " + this.MpGradient + ":::: Total MP upon level with starter materia = <" +  mpIncreaseTotalWithStarterMateria + "> | Total Base MP upon level = <" + mpIncreaseTotal + ">");
            }
        }

    }
}

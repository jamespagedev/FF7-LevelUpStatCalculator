/**
 * Created by James Page on 2/24/2016.
 */
package Main;

public class CalculateFormulas
{

    CalculateFormulas()
    {}

    //******************************************************************

    public int GetHpBaseLine(int base, int nextLevel, int gradient)
    {
        return base + ((nextLevel - 1) * gradient);
    }

    public int GetMpBaseLine(int base, int nextLevel, int gradient)
    {
        return base + (((nextLevel - 1) * gradient) / 10);
    }

    public int GetMpBaseGain(int nextLevel, int gradient)
    {
        return ((nextLevel * gradient) / 10) - (((nextLevel - 1) * gradient) / 10);
    }

    public int GetDifference(int randomNumber, int baseLine, int currentStatNumber)
    {
        int minDifference = 0;
        int maxDifference = 11;
        int difference;

        difference = randomNumber + ((int)( 100 * (baseLine / (double)currentStatNumber)) - 100);
        if (difference < minDifference)
        {
            return minDifference;
        }
        else if (difference > maxDifference)
        {
            return maxDifference;
        }
        return difference;
    }

    public double GetHpStatIncreasePercentage(int difference)
    {
        switch (difference)
        {
            case 0:
                return 0.40;
            case 1:
                return 0.50;
            case 2:
                return 0.50;
            case 3:
                return 0.60;
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
                return 1.30;
            case 11:
                return 1.50;
            default:
                System.out.println("Difference " + difference + "is not a valid value!");
                return -1;
        }
    }

    public double GetMpStatIncreasePercentage(int difference)
    {
        switch (difference)
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
            case 11:
                return 1.60;
            default:
                System.out.println("Difference " + difference + "is not a valid value!");
                return -1;
        }
    }
}

/**
 * Created by James Page on 4/8/2017.
 */
package Main;

import java.awt.*;

public class SupportMethods {
    protected static int[] createArrRange(int start, int end) {
        int[] numArr = new int[end];
        for (int num = start, element = 0; num <= end; num++, element++){
            numArr[element] = num;
        }
        return numArr;
    }

    protected static String[] createArrRange(String firstElement, int startNum, int endNum) {
        String[] strArr = new String[endNum+1];
        strArr[0] = firstElement;
        for (int num = startNum, element = 1; num <= endNum; num++, element++){
            strArr[element] = Integer.toString(num);
        }
        return strArr;
    }

    protected static String getLongestStrFromArr(String[] arr){
        int index = 0;
        int elementLength = arr[0].length();
        for(int i = 1; i < arr.length; i++) {
            if(arr[i].length() > elementLength) {
                index = i; elementLength = arr[i].length();
            }
        }
        return arr[index];
    }

    protected static String getLongestStrFromArr(String[][] arr){
        int firstIndex = 0;
        int secondIndex = 0;
        int elementLength = arr[0][0].length();
        for(int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++)
            {
                if(arr[i][j].length() > elementLength) {
                    firstIndex = i;
                    secondIndex = j;
                    elementLength = arr[i][j].length();
                }
            }
        }
        return arr[firstIndex][secondIndex];
    }

    protected static boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
        } catch(NumberFormatException e) {
            return false;
        } catch(NullPointerException e) {
            return false;
        }
        // String can be changed into an integer
        return true;
    }

    protected static GridBagConstraints setGbc(int gridx, int gridy, int gridWidth, int gridHeight, int ipadx, int ipady, String anchorLocation, double weightx, double weighty, Insets insets){
        GridBagConstraints gbc = new GridBagConstraints();

        if (anchorLocation.toUpperCase().equals("NORTHWEST")){
            gbc.anchor = GridBagConstraints.NORTHWEST;
        } else if (anchorLocation.toUpperCase().equals("NORTH")){
            gbc.anchor = GridBagConstraints.NORTH;
        } else if (anchorLocation.toUpperCase().equals("NORTHEAST")){
            gbc.anchor = GridBagConstraints.NORTHEAST;
        } else if (anchorLocation.toUpperCase().equals("WEST")){
            gbc.anchor = GridBagConstraints.WEST;
        } else if (anchorLocation.toUpperCase().equals("EAST")){
            gbc.anchor = GridBagConstraints.EAST;
        } else if (anchorLocation.toUpperCase().equals("SOUTHWEST")){
            gbc.anchor = GridBagConstraints.SOUTHWEST;
        } else if (anchorLocation.toUpperCase().equals("SOUTH")){
            gbc.anchor = GridBagConstraints.SOUTH;
        } else if (anchorLocation.toUpperCase().equals("SOUTHEAST")){
            gbc.anchor = GridBagConstraints.SOUTHEAST;
        } else {
            gbc.anchor = GridBagConstraints.CENTER;
        }

        gbc.gridx = gridx; // column
        gbc.gridy = gridy; // row
        gbc.gridwidth = gridWidth; // number of columns
        gbc.gridheight = gridHeight; // number of rows
        gbc.ipadx = ipadx; // width of object
        gbc.ipady = ipady; // height of object
        gbc.weightx = weightx; // shifts rows to side of set anchor
        gbc.weighty = weighty; // shifts columns to side of set anchor
        gbc.insets = insets; // placement inside cell
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.fill = GridBagConstraints.VERTICAL;

        return gbc;
    }

    protected static Insets setInsets(int top, int left, int bottom, int right){
        Insets insets = new Insets(top,left,bottom,right);
        return insets;
    }
}
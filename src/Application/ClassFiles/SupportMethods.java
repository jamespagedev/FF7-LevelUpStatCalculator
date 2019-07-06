/**
 * Created by James Page on 4/8/2017.
 */
package ClassFiles;

import java.awt.*;
import java.util.ArrayList;
import java.util.stream.Collectors;

public abstract class SupportMethods {
    public static ArrayList<ArrayList<ArrayList<Integer>>> getCopyThreeDimArrListPassByValue(ArrayList<ArrayList<ArrayList<Integer>>> origThreeDimArrList){
        ArrayList<ArrayList<ArrayList<Integer>>> copiedThreeDimArrList = new ArrayList<>();
        ArrayList<ArrayList<Integer>> copiedArr;

        for (ArrayList<ArrayList<Integer>> origArr: origThreeDimArrList){
            copiedArr = new ArrayList<>(origArr.stream().map(x -> new ArrayList<>(x)).collect(Collectors.toList()));
            copiedThreeDimArrList.add(copiedArr);
        }
        return copiedThreeDimArrList;
    }

    public static int[] createArrRange(int start, int end) {
        int[] numArr = new int[end];
        for (int num = start, element = 0; num <= end; num++, element++){
            numArr[element] = num;
        }
        return numArr;
    }

    public static String[] createArrRange(String firstElement, int startNum, int endNum) {
        String[] strArr = new String[endNum+1];
        strArr[0] = firstElement;
        for (int num = startNum, element = 1; num <= endNum; num++, element++){
            strArr[element] = Integer.toString(num);
        }
        return strArr;
    }

    public static String[] convertIntArrToStrArr(int[] arrInput){
        String[] arrOutput = new String[arrInput.length];

        int index = 0;
        for(int num : arrInput){
            arrOutput[index] = Integer.toString(num);
            index++;
        }
        return arrOutput;
    }

    public static String getLongestStrFromArr(String[] arr){
        int index = 0;
        int elementLength = arr[0].length();
        for(int i = 1; i < arr.length; i++) {
            if(arr[i].length() > elementLength) {
                index = i; elementLength = arr[i].length();
            }
        }
        return arr[index];
    }

    public static String getLongestStrFromArr(String[][] arr){
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

    public static boolean isInteger(String s) {
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

    public static GridBagConstraints setGbc(int gridx, int gridy, int gridWidth, int gridHeight, int ipadx, int ipady, String anchorLocation, double weightx, double weighty, Insets insets, boolean fillCell){
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
        gbc.weightx = weightx; // shifts columns to side of set anchor
        gbc.weighty = weighty; // shifts rows to side of set anchor
        gbc.insets = insets; // placement inside cell
        if (fillCell){
            gbc.fill = GridBagConstraints.BOTH;
        }

        return gbc;
    }

    public static Insets setInsets(int top, int left, int bottom, int right){
        Insets insets = new Insets(top,left,bottom,right);
        return insets;
    }
}
package com.anand.coding.dsalgo.problems;

import java.util.ArrayList;
import java.util.List;

/**
 * There are n cells in a line each having value 0(inactive) or 1(active).
 *
 *  1. If left and right cells have same status, the cell becomes inactive in one day.
 *  2. Else, the cell becomes active.
 *  3. left of the first and right of the last cell are always 0(inactive).
 *
 *  This is a XOR operation.
 *
 *  Find the state of the cells after n days.
 */
public class CompetingCells {

    public static List<Integer> competeCell(int[] states, int days) {

        List<Integer> resultList = new ArrayList<>();

        int n = states.length + 2;

        boolean[] stateArray = new boolean[n];

        stateArray[0] = stateArray[n-1] = false;

        for (int i=0; i<states.length; i++) {
            stateArray[i+1] = states[i] == 1;
        }


        while(days-->0) {
            boolean newStateOfPreviousCell=false;
            for (int i= 1; i < n-1; i++) {
                boolean newState = stateArray[i-1] ^ stateArray[i+1];

                stateArray[i-1] = newStateOfPreviousCell;
                newStateOfPreviousCell = newState;
            }
            stateArray[n-2] = newStateOfPreviousCell;
        }

        for (int i=1; i<n-1; i++) {
            resultList.add(stateArray[i] ? 1 : 0);
        }
        return resultList;
    }

    /**
     *
     * @param args
     */
    public static void main(String [] args){


        int [] arr = {1,1,1,0,1,1,1,1};
        competeCell(arr, 2).forEach(System.out::print);
    }
}

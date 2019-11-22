package com.anand.coding.problems;

import com.anand.coding.dsalgo.array.Array;

import java.util.*;

/**
 * There are n cells in a line each having value 0(inactive) or 1(active).
 *
 *  1. If a cell has two neighbours and left and right cells have same status, the cell becomes active in one day.
 *  2. Else, the cell becomes inactive.
 *
 *  Find the state of the cells after n days.
 *
 * Trick: states is periodic (repeats after a cycle).
 */
public class _02_PrisonCells {

    public static int[] prisonAfterNDays(int[] states, int days) {

        int n = states.length;
        boolean[] stateArray = new boolean[n];

        Set<String> visited = new LinkedHashSet<>();

        for (int i=0; i<states.length; i++) {
            stateArray[i] = (states[i] == 1);
        }
        visited.add(toString(stateArray));

        for(int day=1; day<=days;) {

            boolean newStateOfPreviousCell=false;
            for (int i=1; i < n-1; i++) {
                boolean newState = stateArray[i-1] == stateArray[i+1];

                stateArray[i-1] = newStateOfPreviousCell;
                newStateOfPreviousCell = newState;
            }
            stateArray[n-2] = newStateOfPreviousCell;
            stateArray[n-1] = false;

            if(visited.contains(toString(stateArray))){
                //System.out.println(toString(stateArray) + " " + day);
                days %=day-1; //cycle end day-1
                if(days==0){
                    days = day-1;
                }
                break;
            } else {
                visited.add(toString(stateArray));
                day++;
            }
        }

        String result = new ArrayList<>(visited).get(days);

        int [] ans = new int[states.length];
        for (int i=0; i<n-1; i++) {
            ans[i] = (result.charAt(i)=='0' ? 0 : 1);
        }
        return ans;
    }

    private static String toString(boolean []A){
        StringBuilder sb = new StringBuilder();
        for (int i=0; i<A.length; i++) {
            sb.append(A[i] ? 1 : 0);
        }
        return sb.toString();
    }

    /**
     *
     * @param args
     */
    public static void main(String [] args){

        int [] arr = {1,1,1,0,1,1,1,1};
        Array.display(prisonAfterNDays(arr, 2));

        int [] arr1 = {0,1,0,1,1,0,0,1};
        Array.display(prisonAfterNDays(arr1,7));

        int [] arr2 = {1,0,0,1,0,0,1,0};
        Array.display(prisonAfterNDays(arr2,1000000000));

        int [] arr3 = {0,0,0,1,1,0,1,0};
        Array.display(prisonAfterNDays(arr3,574));
    }
}

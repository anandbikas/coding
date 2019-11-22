package com.anand.coding.problems;

import java.util.*;

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
 *
 * Trick: states is periodic (repeats after a cycle).
 */
public class _01_CompetingCells {

    public static List<Integer> competeCell(int[] states, int days) {

        int n = states.length + 2;
        boolean[] stateArray = new boolean[n];

        Set<String> visited = new LinkedHashSet<>();

        stateArray[0] = stateArray[n-1] = false;
        for (int i=0; i<states.length; i++) {
            stateArray[i+1] = (states[i]==1);
        }
        visited.add(toString(stateArray));

        for(int day=1; day<=days;) {

            boolean newStateOfPreviousCell=false;
            for (int i=1; i < n-1; i++) {
                boolean newState = stateArray[i-1] ^ stateArray[i+1];

                stateArray[i-1] = newStateOfPreviousCell;
                newStateOfPreviousCell = newState;
            }
            stateArray[n-2] = newStateOfPreviousCell;

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

        List<Integer> ans = new ArrayList<>();
        for (int i=0; i<n-1; i++) {
            ans.add((result.charAt(i)=='0' ? 0 : 1));
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
        competeCell(arr, 1).forEach(System.out::print);

        System.out.println();
        competeCell(arr, 5000).forEach(System.out::print);

    }
}

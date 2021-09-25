package com.anand.coding.problems.scheduling;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Find maximum events attended from events[](start,end) where an event can be attended for at least one day.
 * Only one event can be attended at a time.
 *
 */
public class _06_MaxEventsAttendDUS {

    //Disjoint Union Sets
    private static int[] parent;
    public static int find(int i){
        if(parent[i]==i)
            return i;
        return parent[i]=find(parent[i]);
    }
    public static void merge(int i,int j){
        parent[i]=j;
    }

    public static int maxEvents(int[][] events) {

        int maxEndDate = Arrays.stream(events).mapToInt(event -> event[1]).max().orElse(0);

        parent=new int[maxEndDate+2];
        for(int i=0;i<=maxEndDate+1;i++){
            parent[i]=i;
        }
        Arrays.sort(events, Comparator.comparingInt(event -> event[1]));

        int maxEventsAttended=0;
        for(int[] event:events){
            int parent=find(event[0]);

            if(parent<=event[1]){
                int parentForNextDay=find(parent+1);
                merge(parent,parentForNextDay);
                maxEventsAttended++;
            }
        }
        return maxEventsAttended;
    }

    /**
     *
     * @param args
     */
    public static void main(String [] args){

        int [][]A = {{1,2},{2,3},{3,4}};
        System.out.println(maxEvents(A));

        int [][]B = {{1,2},{2,3},{3,4},{1,2}};
        System.out.println(maxEvents(B));

        int [][]C = {{1,3},{1,3},{1,3},{3,4}};
        System.out.println(maxEvents(C));

        int [][]D = {{1,4},{4,4},{2,2},{3,4},{1,1}};
        System.out.println(maxEvents(D) + "= 4");

        int [][]E = {{1,5},{1,5},{1,5},{2,3},{2,3}};
        System.out.println(maxEvents(E) + "= 5");

        int [][]F = {{1,2},{2,3},{3,4},{2,2}};
        System.out.println(maxEvents(F) + "= 4");
    }
}

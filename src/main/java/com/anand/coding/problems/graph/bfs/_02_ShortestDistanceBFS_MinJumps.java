package com.anand.coding.problems.graph.bfs;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * Given an array of integers arr with initial position at index 0.
 * In one step you can jump from index i to index: i+1, i-1, j (A[i]==A[j])
 *
 * Return the minimum number of steps to reach the last index of the array.
 *
 * leetcode.com/problems/jump-game-iv
 */
public class _02_ShortestDistanceBFS_MinJumps {

    public static int minJumps(int[] A) {

        Map<Integer, List<Integer>> map = new HashMap<>();
        for(int i=A.length-1; i>=0; i--) {
            if(!map.containsKey(A[i])) {
                map.put(A[i],new ArrayList<>());
            }
            map.get(A[i]).add(i);
        }

        boolean[] visited = new boolean[A.length];
        Queue<Integer> q = new ArrayDeque<>();
        q.add(0);
        visited[0]=true;

        for(int dist=0; !q.isEmpty(); dist++) {

            for(int size=q.size(); size>0 ; size--) {
                int loc = q.remove();
                if(loc==A.length-1){
                    return dist;
                }

                // BFS all possible moves if any
                if(loc>0 && !visited[loc-1]) {
                    q.add(loc-1);
                    visited[loc-1]=true;
                }
                if(loc<A.length && !visited[loc+1]) {
                    q.add(loc+1);
                    visited[loc+1]=true;
                }
                for(int x: map.get(A[loc])) {
                    if(!visited[x]){
                        q.add(x);
                        visited[x]=true;
                    }
                }
                map.get(A[loc]).clear();
            }
        }

        return -1;
    }

    /**
     *
     * @param args
     */
    public static void main(String []args){
        int []A = {5,7,7,7,7,5,6,11};

        System.out.println(minJumps(A));
    }
}

package com.anand.coding.problems.graph;

import com.anand.coding.dsalgo.graph.adjacencylist.Pair;

import java.util.*;

/**
 * Given n tasks and their completion times along with the task dependencies(u->v).
 * Find minimum time to complete all tasks where task can run in parallel provided dependency is satisfied.
 *
 *
 * Hint: Longest path in DAG
 *
 * Important links:
 * https://stackoverflow.com/questions/20864641/finding-minimum-completion-time-of-scheduled-tasks-with-topological-sort
 * https://leetcode.com/discuss/interview-question/389939/Google-or-Phone-Screen-or-Longest-Path-in-DAG/351183
 * https://www.geeksforgeeks.org/longest-path-in-a-directed-acyclic-graph-dynamic-programming/
 * https://www.geeksforgeeks.org/minimum-time-taken-by-each-job-to-be-completed-given-by-a-directed-acyclic-graph/
 *
 */
public class _99a_MinTimeToCompleteAllTasksInDAG {

    HashMap<Integer, Set<Pair<Integer,Integer>>> vertices = new HashMap<>();

    /**
     * DFS with memoization DP (DFS topological sorting)
     *
     * @param tasks
     * @param dependencies
     * @param size
     * @return
     */
    public int minTimeCompletionDfs(int[] tasks, int[][] dependencies, int size) {

        //Populate graph
        for(int u=1; u<=size; u++){
            vertices.put(u, new HashSet<>());
        }
        for(int []dependency: dependencies) {
            int u = dependency[0], v = dependency[1];
            vertices.get(u).add(new Pair<>(v,tasks[v]));
        }

        //Calculate max time using DFS
        int maxTime=0;
        boolean [] visited= new boolean[size+1];
        Integer []DP = new Integer[size+1];

        for(int u=1; u<=size; u++) {
            if(!visited[u]) {
                maxTime = Math.max(minTimeCompletionDfs(u, tasks, visited, DP), maxTime);
            }
        }
        return maxTime;
    }

    public int minTimeCompletionDfs(int u, int[] tasks, boolean [] visited, Integer []DP){
        visited[u]= true;

        if(DP[u]!=null){
            return DP[u];
        }

        int maxTime=0;
        for(Pair<Integer, Integer> v: vertices.get(u)){
            if(!visited[v.key]){
                maxTime = Math.max(minTimeCompletionDfs(v.key, tasks, visited, DP), maxTime);
            }
        }
        return DP[u]=maxTime+tasks[u];
    }

    /**
     *
     * @param args
     */
    public static void main(String []args){

        int [][] dependencies = {{1,2},{2,3},{4,3},{4,5}};
        int [] tasks = {0,1,4,2,4,2};
        System.out.println(new _99a_MinTimeToCompleteAllTasksInDAG().minTimeCompletionDfs(tasks, dependencies, tasks.length-1));
    }
}
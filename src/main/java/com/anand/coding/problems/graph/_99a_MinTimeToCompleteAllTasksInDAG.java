package com.anand.coding.problems.graph;

import java.util.*;
import java.util.stream.IntStream;

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
 * https://leetcode.com/problems/parallel-courses-iii/description
 */
public class _99a_MinTimeToCompleteAllTasksInDAG {

    HashMap<Integer, List<Integer>> vertices = new HashMap<>();

    public int minTimeCompletionDfs(int[] tasks, int[][] dependencies, int size) {
        //Populate graph
        IntStream.rangeClosed(1,size).forEach(u -> vertices.put(u, new ArrayList<>()));
        Arrays.stream(dependencies).forEach(dep -> vertices.get(dep[0]).add(dep[1]));

        //Calculate max time using DFS with memoization DP (DFS topological sorting)
        Integer [] DP = new Integer[size+1];
        int max=0;
        for(int u=1; u<=size; u++) {
            max = Math.max(minTimeCompletionDfs(u, tasks, DP), max);
        }
        return max;
    }

    public int minTimeCompletionDfs(int u, int[] tasks, Integer []DP) {
        if(DP[u]!=null) {
            return DP[u];
        }

        int max=0;
        for(int v: vertices.get(u)) {
            max = Math.max(minTimeCompletionDfs(v, tasks, DP), max);
        }
        return DP[u] = max + tasks[u-1];
    }

    /**
     *
     * @param args
     */
    public static void main(String []args){

        int [][] dependencies = {{1,2},{2,3},{4,3},{4,5}};
        int [] tasks = {1,4,2,4,2};
        System.out.println(new _99a_MinTimeToCompleteAllTasksInDAG().minTimeCompletionDfs(tasks, dependencies, tasks.length));

        int [][] dependencies1 = {{1,5},{2,5},{3,5},{3,4},{4,5}};
        int [] tasks1 = {1,2,3,4,5};
        System.out.println(new _99a_MinTimeToCompleteAllTasksInDAG().minTimeCompletionDfs(tasks1, dependencies1, tasks1.length));
    }
}
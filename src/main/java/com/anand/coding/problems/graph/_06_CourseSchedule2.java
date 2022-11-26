package com.anand.coding.problems.graph;

import java.util.*;
import java.util.stream.IntStream;

/**
 * For n courses there are prerequisites[(v,u),..] denotes u is dependant on v.
 * Find the order of course, return empty list if cycle is detected.
 *
 * Example:
 * n=2 , prerequisites = [[1,0],[2,0],[3,1],[3,2]]
 *
 * course order : [0,2,1,3]
 *
 * Solution
 * 1. BFS Using in-degree calculation
 * 2. DFS Recursive with cycle detection
 */
public class _06_CourseSchedule2 {

    private final HashMap<Integer,LinkedList<Integer>> vertices = new HashMap<>();

    public int [] topologicalSortingDfsRec(int n, int[][] E){
        IntStream.range(0,n).forEach(u->vertices.put(u, new LinkedList<>()));
        Arrays.stream(E).forEach(edge -> vertices.get(edge[1]).add(edge[0]));

        Set<Integer> visited = new HashSet<>();
        Stack<Integer> sortedVerticesStack = new Stack<>();

        for(Integer u: vertices.keySet()) {
            if(!visited.contains(u)) {
                if(topoSortHasCycleDfs(u, visited, sortedVerticesStack, new HashSet<>())){
                    return new int[]{};
                }
            }
        }

        int [] res = new int[n]; int i=0;
        while(!sortedVerticesStack.isEmpty()){
            res[i++]=sortedVerticesStack.pop();
        }

        return res;
    }

    private boolean topoSortHasCycleDfs(Integer u, Set<Integer> visited, Stack<Integer> sortedVerticesStack, Set<Integer> inPathStack) {

        inPathStack.add(u);
        visited.add(u);

        for(Integer v: vertices.get(u)) {
            if(inPathStack.contains(v)) {
                return true;
            }
            if(!visited.contains(v)) {
                if(topoSortHasCycleDfs(v, visited, sortedVerticesStack, inPathStack)){
                    return true;
                }
            }
        }
        sortedVerticesStack.push(u);
        inPathStack.remove(u);
        return false;
    }

    public static void main(String[] args) {

        int [][]A = {{1,0},{2,0},{3,1},{3,2}};
        int []res = new _06_CourseSchedule2().topologicalSortingDfsRec(4,A);
        System.out.println(Arrays.toString(res));

        int []res1 = new _06_CourseSchedule2().topologicalSortingDfsRec(2,new int[][]{{0,1},{1,0}});
        System.out.println(Arrays.toString(res1));
    }
}

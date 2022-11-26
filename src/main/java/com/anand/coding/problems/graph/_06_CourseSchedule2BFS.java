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
public class _06_CourseSchedule2BFS {

    private final HashMap<Integer,LinkedList<Integer>> vertices = new HashMap<>();

    public int [] topologicalSortingDfsRec(int n, int[][] E){
        IntStream.range(0,n).forEach(u->vertices.put(u, new LinkedList<>()));
        Arrays.stream(E).forEach(edge -> vertices.get(edge[1]).add(edge[0]));

        Queue<Integer> queue = new ArrayDeque<>(n);

        Map<Integer,Integer> inDegrees = new HashMap<>();
        for(Integer u: vertices.keySet()) {
            inDegrees.put(u, 0);
        }

        for(Integer u: vertices.keySet()){
            for(Integer v: vertices.get(u)) {
                inDegrees.put(v, inDegrees.get(v)+1);
            }
        }

        vertices.keySet().stream().filter(u->inDegrees.get(u)==0).forEach(queue::add);

        int [] res = new int[n]; int i=0;
        while(!queue.isEmpty()) {
            Integer u = queue.remove();
            res[i++]=u;

            for(Integer v: vertices.get(u)) {
                //Reduce inDegree once its parent is processed.
                inDegrees.put(v,inDegrees.get(v)-1);
                if(inDegrees.get(v)==0) {
                    queue.add(v);
                }
            }
        }

        return i<n ? new int[0] : res;
    }

    public static void main(String[] args) {

        int [][]A = {{1,0},{2,0},{3,1},{3,2}};
        int []res = new _06_CourseSchedule2BFS().topologicalSortingDfsRec(4,A);
        System.out.println(Arrays.toString(res));

        int []res1 = new _06_CourseSchedule2BFS().topologicalSortingDfsRec(2,new int[][]{{0,1},{1,0}});
        System.out.println(Arrays.toString(res1));
    }
}

package com.anand.coding.problems.graph.disjointset;

/**
 * Find size of the longest set S[k] from an array A[] containing unique elements in the range 0 to n-1;
 * such that S[k] = {A[k], A[A[k]] ...}
 *
 * Ex:
 * A = [5,4,0,3,1,6,2]
 * Output: 4
 * Explanation:
 * S[0] = {A[0], A[5], A[6], A[2]} = {5, 6, 2, 0}
 *
 * leetcode.com/problems/array-nesting
 *
 * Hint See: DisjointUnionSets.java
 */
public class _00_ArrayNesting {

    public static int depth(int[] Parent) {

        int maxDepth=0;
        boolean []visited = new boolean[Parent.length];

        for(int i=0; i<Parent.length; i++) {
            if(visited[i]) {
                continue;
            }
            int depth=1;
            for(int k=i; k!=Parent[k] && Parent[k]!=i; visited[k]=true, depth++, k=Parent[k]); // Parent[k]!=i to prevent loop

            maxDepth = Math.max(maxDepth, depth);
        }
        return maxDepth;
    }

    /**
     *
     * @param args
     */
    public static void main(String [] args) {

        System.out.println(depth(new int []{5,4,0,3,1,6,2}));
        System.out.println(depth(new int []{0,1,2}));
    }
}

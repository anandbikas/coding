package com.anand.coding.problems.disjointset;

/**
 * Find size of the longest set S[k] from an array A[] containing unique elements in the range 0 to n-1;
 * such that S[k] = {A[k], A[A[k]] ...}
 *
 * Ex:
 * A = [5,4,0,3,1,6,2]
 * Output: 4
 * Explanation:
 * S[0] = {A[0], A[5], A[6], A[2]} = {5, 6, 2, 0}
 */
public class _01_NestedArray {

    public static int nestedArray(int[] A) {

        int maxDepth=0;
        boolean []visited = new boolean[A.length];

        for(int i=0; i<A.length; i++){
            if(visited[i]){
                continue;
            }
            int depth=1;
            for(int k=i; k!=A[k] && A[k]!=i; visited[k]=true, depth++, k=A[k]); //  k!=A[k] may not require

            maxDepth = Math.max(maxDepth, depth);
        }
        return maxDepth;
    }

    /**
     *
     * @param args
     */
    public static void main(String [] args) {

        System.out.println(nestedArray(new int []{5,4,0,3,1,6,2}));
        System.out.println(nestedArray(new int []{0,1,2}));
    }
}

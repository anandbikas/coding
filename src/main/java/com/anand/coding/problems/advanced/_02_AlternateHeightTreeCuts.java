package com.anand.coding.problems.advanced;

/**
 *
 * Given height of trees in a row.
 * Find minimum number of trees to cut in order to make the tree heights alternate.
 *
 */
public class _02_AlternateHeightTreeCuts {


    /**
     *
     * @param A
     * @return
     */
    public static int alternateHeightTreeCuts(int [] A){

        int n= A.length;

        if(n<=1){
            return 0;
        }

        if(n==2){
            return A[0]==A[1] ? 1: 0;
        }

        //Calculate number of cuts for odd index with lower height
        int oddCuts = 0;
        for(int i=1 ; i<n-1; i+=2){
            if(!(A[i-1] > A[i] && A[i+1] > A[i])){
                oddCuts++;
            }
        }
        if((n-1)%2==1){
            if(!(A[n-2]>A[n-1])){
                oddCuts++;
            }
        }

        //Calculate number of cuts for even index with lower height
        int evenCuts = 0;
        if(!(A[1] > A[0])){
            evenCuts++;
        }
        for(int i=2 ; i<n-1; i+=2){
            if(!(A[i-1] > A[i] && A[i+1] > A[i])){
                evenCuts++;
            }
        }
        if((n-1)%2==0){
            if(!(A[n-2]>A[n-1])){
                evenCuts++;
            }
        }

        // Result is minimum of the cuts for the two possible arrangement order.
        return Math.min(oddCuts, evenCuts);
    }

    /**
     *
     * @param args
     */
    public static void main(String [] args){

        System.out.println(alternateHeightTreeCuts(new int[] {2,3,4,5,6}));

        System.out.println(alternateHeightTreeCuts(new int[] {2,3,4,5,6}));

        System.out.println(alternateHeightTreeCuts(new int[] {2,6,8,2,1}));
        System.out.println(alternateHeightTreeCuts(new int[] {2,6,8,9,1}));

    }
}

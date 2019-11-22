package com.anand.coding.problems.advanced;

/**
 * Given the heights of bars (of width 1) adjacently lined up in a row.
 * Find the unit of water trapped in between the bars.
 *
 */
public class _01_TrappingRainWater {


    /**
     * @param A
     * @return
     */
    public static int trap(int[] A) {

        int n = A.length;
        if(n<=2){
            return 0;
        }

        int []LeftMax = new int[n];
        int []RightMax = new int[n];

        int max = A[0];
        for(int i=1; i<n-1; i++) {
            LeftMax[i] = max;
            if(max<A[i]){
                max = A[i];
            }
        }

        max = A[n-1];
        for(int i=n-2; i>0; i--) {
            RightMax[i] = max;
            if(max<A[i]){
                max = A[i];
            }
        }

        int result = 0;
        for(int i=1; i<n-1; i++){

            int diff = Math.min(LeftMax[i], RightMax[i])-A[i];

            if(diff>0){
                result+=diff;
            }
        }
        return result;
    }

    /**
     * @param args
     */
    public static void main(String[] args) {

        System.out.println(trap(new int[]{2,0,2}));


        int[] A = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        System.out.println(trap(A));

        System.out.println(trap(new int[]{0,2,0}));
    }
}

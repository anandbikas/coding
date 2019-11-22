package com.anand.coding.problems.dp;

/**
 * FrogJump: optimized DP solution to check if a frog can reach the destination by jumping.
 *
 * There are n(<1100) stones in a river of x (<2^31) units. The stones are sorted as per distance.
 * The frog starts from 0th stone and can jump either k-1, k or k+1 units where k is the previous jump unit.
 *
 * Find if the frog can cross the river.
 *
 */
public class _13_FrogJump {

    /**
     *
     * @param stones
     * @return
     */
    public static boolean canCross(int[] stones) {

        int n = stones.length;
        if(n<=1){
            return true;
        }

        int [][]DP  = new int[n][n];     //Stone->Stone
        int []MaxJump = new int[n];      //Stone : max jump to reach stone i.

        // From stone 1st stone 2nd stone
        if(stones[1]-stones[0] == 1){
            DP[0][1] = 1;
        }

        //From stone i to stone j.
        for(int i=1; i<n; i++){

            //Optimization: Store max jump to reach i in MaxJump[i];
            //To be used to shorten horizontal/vertical traversing.
            for(int x=i-1; x>=0; x--){
                if(MaxJump[i]<DP[x][i]){
                    MaxJump[i] = DP[x][i];
                }
            }

            for(int j=i+1; j<n && j<=i+MaxJump[i]+1; j++){

                //Check how it reached on stone i. Is there any reach with jump either (dist-1, dist, dist+1);
                int dist = stones[j]-stones[i];

                for(int k=i-1; k>=0; k--){
                    if(DP[k][i]>0 && Math.abs(DP[k][i]-dist)<=1){
                        DP[i][j]=dist;
                        break;
                    }
                    //If max jump reached, break
                    if(DP[k][i]==MaxJump[i]){
                        break;
                    }
                }
            }
        }

        printDPArray(DP,n-1,n-1);

        //Check is there a reach on the last stone?
        for(int i=n-1; i>=0; i--){
            if(DP[i][n-1]>0){
                return true;
            }
            //If max reached, break
            if(DP[i][n-1]==MaxJump[n-1]){
                break;
            }
        }
        return false;
    }

    public static void main(String []args){

        int [] stones = {0,1,3,5,6,8,12,17};
        System.out.println(canCross(stones));

        int [] stones1 = {0,1,2,3,4,8,9,11};
        System.out.println(canCross(stones1));

        int [] stones2 = {0,1,3,4,5,7,9,10,12};
        System.out.println(canCross(stones2));

        int [] stones3 = {0,1,3,6,10,15,16,21};
        System.out.println(canCross(stones3));

        int []A = {0,1,3,4,5,7,8,9,11,12,13,15,16,17,19,20};
        System.out.println(canCross(A));

    }

    /**
     *
     * @param DP
     * @param n
     * @param m
     */
    public static void printDPArray(int [][] DP, int n, int m)
    {
        System.out.println();
        for(int i=0; i<=n; i++){

            for(int j =0; j<=m; j++){
                System.out.print(String.format("%4d", DP[i][j]));
            }
            System.out.println();
        }
    }
}

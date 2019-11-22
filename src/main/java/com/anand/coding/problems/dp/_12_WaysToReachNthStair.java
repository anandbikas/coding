package com.anand.coding.problems.dp;

/**
 * For a n stairs ladder, count the number of ways to reach the nth stair
 * provided one can take steps 1,2,....k in one hop.
 *
 * For k=2, nth stair can be reached from n-1th or n-2th stair.
 *    So, ways(n) = ways(n-1) + ways(n-2) which is Fibonnaci series.
 *
 * Similarly for n = k,
 *        ways(n) = ways(n-1) + ways(n-2) + ... + ways(n-k)
 *
 * example1: n=4, k=2;
 *   ways(0) = 1
 *   ways(1) = 1
 *   ways(2) = 2
 *   ways(3) = 3
 *   ways(4) = 5
 *
 *
 * example2: n=4, k=3;
 *   ways(0) = 1
 *   ways(1) = 1
 *   ways(2) = 2
 *   ways(3) = 4
 *   ways(4) = 7
 *
 */
public class _12_WaysToReachNthStair {

    /**
     *
     * @param n
     * @param k
     */
    public static int ways(int n, int k){

        if(n<=1){
            return n;
        }
        /**
         * 1. Instead of taking k variables as in fibonacci series, take a circular queue of k elements
         * 2. Calculate first k terms, including 0th term which is 1.
         * 3. Maintain sum of previous k terms to calculate next term.
         *
         * 4. once next term is calculated, insert it in the queue (the last one will automatically be overwritten in the queue)
         *
         */

        int [] Q = new int[k];

        Q[0]=1; Q[1]=1;

        int sumOfKTerms = 2;

        for(int i=2; i<=k-1; i++){
            Q[i] = sumOfKTerms;
            sumOfKTerms += Q[i];

            if(i==n){
                return Q[i];
            }
        }

        int front=0;
        int rear=0;

        int iThTerm = 0;
        for(int i=k; i<=n; i++){
            iThTerm = sumOfKTerms;

            //this is like #slidingWindow
            sumOfKTerms = sumOfKTerms + iThTerm - Q[front];
            front = (front+1)%k;

            Q[rear] = iThTerm;
            rear = (rear+1)%k;
        }
        return iThTerm;
    }

    public static void main(String []args){

        for(int n=0; n<=10; n++) {
            System.out.println(ways(n, 3));
        }
    }
}

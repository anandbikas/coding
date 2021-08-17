package com.anand.coding.problems.heap;

import java.util.*;

/**
 * KthLargestInStream object will be instantiated and called as:
 * KthLargestInStream obj = new KthLargestInStream(k, nums);
 *
 *  int val = obj.add(val);  should add the value and return contemporary kth largest element.
 */
public class _05_KthLargestInStream {

    private PriorityQueue<Integer> pq;
    private PriorityQueue<Integer> pqK;
    private int k;

    public _05_KthLargestInStream(int k, int[] nums) {
        this.k=k;
        this.pq  = new PriorityQueue<>(Comparator.comparing(Integer::intValue).reversed());
        this.pqK = new PriorityQueue<>(k);
        Arrays.stream(nums).forEach(pq::add);

        for(int i=0; i<k && !pq.isEmpty(); i++){
            pqK.add(pq.remove());
        }
    }

    public Integer add(int val) {
        if(pqK.size()<k){
            pqK.add(val);
        } else {
            pq.add(val);
            if(pqK.peek()<pq.peek()){
                pq.add(pqK.remove());
                pqK.add(pq.remove());
            }
        }
        return pqK.peek();
    }


    /**
     *
     * @param args
     */
    public static void main(String [] args) {
        _05_KthLargestInStream kthLargestInStream = new _05_KthLargestInStream(3,new int[]{4, 5, 8, 2});

        Arrays.asList(3,5,9,10,4).forEach(x->
                System.out.println(kthLargestInStream.add(x))
        );
    }
}

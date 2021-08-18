package com.anand.coding.problems.heap;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Find median in a stream of numbers.
 *
 */
public class _10_MedianInStream {

    private PriorityQueue<Integer> left;
    private PriorityQueue<Integer> right;

    public _10_MedianInStream() {
        this.left  = new PriorityQueue<>(Comparator.comparing(Integer::intValue).reversed());
        this.right = new PriorityQueue<>();
    }

    public void addNum(int x) {

        if(left.size()>0 && x<=left.peek()){
            left.add(x);
        } else {
           right.add(x);
        }

        if(left.size()>right.size()+1){
            right.add(left.remove());
        } else if(right.size()>left.size()+1){
            left.add(right.remove());
        }
    }

    public Double findMedian() {

        if(left.size()>right.size()){
            return left.peek().doubleValue();
        } else if(right.size()>left.size()){
            return right.peek().doubleValue();
        } else {
            if(left.isEmpty()) {
                return null;
            }
            return (left.peek()+right.peek())/2.0;
        }
    }


    /**
     *
     * @param args
     */
    public static void main(String [] args) {
        _10_MedianInStream medianInStream = new _10_MedianInStream();

        System.out.println(medianInStream.findMedian());

        Arrays.asList(1,2,3).forEach(x-> {
            medianInStream.addNum(x);
            System.out.println(medianInStream.findMedian());
        });
    }
}

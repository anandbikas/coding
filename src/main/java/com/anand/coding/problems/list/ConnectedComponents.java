package com.anand.coding.problems.list;

import com.anand.coding.dsalgo.list.LinkedList;

import java.util.stream.IntStream;

/**
 * Given linked list with unique integers and a subset of the list values as array nums
 * Find number of connected components in nums where two values are connected if they appear consecutively in the list.
 */
public class ConnectedComponents {

    public static int connectedComponents(LinkedList<Integer> list, int[] nums) {
        boolean []map = new boolean[10000];
        for(int x : nums) map[x]=true;

        int count=0;
        for(LinkedList.Node<Integer> node = list.node(1); node!=null; node=node.next) {
            if(map[node.data]) {
                if(node.next==null || !map[node.next.data])
                    count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        LinkedList<Integer> list = new LinkedList<>();
        IntStream.rangeClosed(0,9).forEach(list::add);

        System.out.println(connectedComponents(list, new int[]{0,1,3}));

    }
}

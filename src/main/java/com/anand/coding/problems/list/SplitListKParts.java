package com.anand.coding.problems.list;

import com.anand.coding.dsalgo.list.LinkedList;
import com.anand.coding.dsalgo.list.LinkedList.Node;

import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * Given the head of a singly linked list and an integer k, split the linked list into k consecutive linked list parts.
 */
public class SplitListKParts {

    public static Node<Integer>[] splitListToParts(Node<Integer> head, int k) {

        Node<Integer> [] res = new Node[k];
        if(head==null) return res;

        int size=0;
        for(Node<Integer> node = head; node!=null; node=node.next) size++;

        int cnt=1, rem=0;
        if(size>k){
            cnt = size/k;
            rem = size%k;
        }

        int p=-1;
        res[++p]=head;
        Node<Integer> node=head;
        for(int i=1; node.next!=null; ) {
            if(i==cnt){
                if(rem>0){
                    node=node.next; rem--;
                }
                res[++p] = node.next;
                node.next=null;
                node = res[p];
                i=1;
            } else {
                node=node.next; i++;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        LinkedList<Integer> list = new LinkedList<>();
        IntStream.rangeClosed(1,10).forEach(list::add);

        Node<Integer>[] splitList = splitListToParts(list.node(1),3);
        Arrays.stream(splitList).forEach(System.out::println);

    }
}

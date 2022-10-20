package com.anand.coding.problems.list;

import com.anand.coding.dsalgo.list.LinkedList;
import com.anand.coding.dsalgo.list.LinkedList.Node;

import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * Move odd indexed elements to the left and even ones to the right.
 */
public class OddEvenList {

    public static Node<Integer> oddEvenList(Node<Integer> head) {
        if(head==null){
            return null;
        }

        Node<Integer> evenHeader = new Node<>(null);
        Node<Integer> evenEnd = evenHeader;

        Node<Integer> oddEnd = head;
        while(oddEnd.next!=null) {
            evenEnd = evenEnd.next = oddEnd.next;
            if(evenEnd.next==null){
                break;
            }
            oddEnd = oddEnd.next = evenEnd.next;
        }
        evenEnd.next = null;
        oddEnd.next = evenHeader.next;

        return head;
    }

    public static void main(String [] args) {
        LinkedList<Integer> list = new LinkedList<>();
        IntStream.rangeClosed(1,10).forEach(list::add);

        Node<Integer> node = oddEvenList(list.node(1));
        list.display();

    }
}

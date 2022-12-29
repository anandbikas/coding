package com.anand.coding.dsalgo.tree.binary;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.PriorityQueue;

import static com.anand.coding.dsalgo.tree.binary.BinarySearchTree.BSTIterator;

/**
 * Binary Search Tree Util
 */
public class BinarySearchTreeUtil<T extends Comparable<T>> {

    /**
     *
     * @param bst1
     * @param bst2
     * @return
     */
    public List<T> sortedElements(BinarySearchTree<T> bst1, BinarySearchTree<T> bst2) {
        Iterator<T> t1 = bst1.iterator();
        Iterator<T> t2 = bst2.iterator();

        List<T> res = new ArrayList<>();

        T x = t1.next(), y = t2.next();
        while(!(x==null || y==null)) {
            if(x.compareTo(y)<0){
                res.add(x);
                x=t1.next();
            } else {
                res.add(y);
                y=t2.next();
            }
        }

        if(x==null && y!=null) {
            res.add(y);
            while(t2.hasNext()) res.add(t2.next());
        }
        if(y==null && x!=null) {
            res.add(x);
            while(t1.hasNext()) res.add(t1.next());
        }

        return res;
    }

    /**
     *
     * @param bstList
     * @return
     */
    public List<T> sortedElements(List<BinarySearchTree<T>> bstList) {
        PriorityQueue<BSTIterator<T>> pq = new PriorityQueue<>(bstList.size(), Comparator.comparing(BSTIterator::peek));

        bstList.stream().filter(Objects::nonNull)
                .map(BinarySearchTree::iterator).filter(it->it.peek()!=null).forEach(pq::add);

        List<T> res = new ArrayList<>();
        while (!pq.isEmpty()) {
            BSTIterator<T> it = pq.remove();
            res.add(it.next());
            if(it.hasNext()) {
                pq.add(it);
            }
        }

        return res;
    }

    /**
     *
     * @param args
     */
    public static void main(String [] args){

        /*
         *               5
         *           3      7
         *        2   4  6   8
         */
        final BinarySearchTree<Integer> bst1 = new BinarySearchTree<>();
        for(int x : new int[]{5,7,3,6,4,8,2}) {
            bst1.insertRec(x);
        }

        /*
         *               4
         *           3      9
         */
        final BinarySearchTree<Integer> bst2 = new BinarySearchTree<>();
        for(int x : new int[]{4,3,9}) {
            bst2.insertRec(x);
        }

        BinarySearchTreeUtil<Integer> bstUtil = new BinarySearchTreeUtil<>();

        System.out.println(bstUtil.sortedElements(bst1, bst2));
        System.out.println(bstUtil.sortedElements(Arrays.asList(bst1, bst2)));

    }
}

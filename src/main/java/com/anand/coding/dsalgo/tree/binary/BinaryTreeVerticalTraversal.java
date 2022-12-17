package com.anand.coding.dsalgo.tree.binary;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Queue;
import java.util.ArrayDeque;
import java.util.TreeMap;

/**
 * Binary Tree
 */
public class BinaryTreeVerticalTraversal<T extends Comparable<T>> extends BinaryTree<T> {

    public BinaryTreeVerticalTraversal(){
        super();
    }

    public BinaryTreeVerticalTraversal(final T data){
        super(data);
    }

    /**
     * verticalOrderTraversal using HashMap
     */
    public void verticalOrderTraversal(){
        Map<Integer, List<T>> map= new HashMap<>();

        Range verticalRange = new Range();
        verticalOrderTraversal(root, map, verticalRange);

        System.out.println("verticalOrderTraversal :");
        for(int vLevel = verticalRange.left; vLevel<=verticalRange.right; vLevel++) {
            System.out.println("verticalLevel " + vLevel + " : " + map.get(vLevel));
        }
    }

    /**
     * This requires BFS traversal so that output be in top-down order.
     *
     * @param node
     * @param map
     * @param range
     */
    private void verticalOrderTraversal(Node<T> node, Map<Integer, List<T>> map, Range range){

        Queue<NodeInfo> queue = new ArrayDeque<>(); //new LinkedList<>();
        if(node!=null){
            queue.add(new NodeInfo(node, 0,0));
        }

        while(!queue.isEmpty()){
            NodeInfo nodeInfo = queue.remove();
            node = nodeInfo.node;
            int vLevel = nodeInfo.vLevel;

            if(vLevel<range.left)  range.left = vLevel;
            if(vLevel>range.right) range.right = vLevel;

            map.computeIfAbsent(vLevel, k->new ArrayList<>()).add(nodeInfo.node.data);

            if(node.left!=null)  queue.add(new NodeInfo(node.left, vLevel-1));
            if(node.right!=null) queue.add(new NodeInfo(node.right, vLevel+1));
        }
    }

    /**
     * verticalOrderTraversal using HashMap
     */
    public void verticalOrderTraversalSorted(){
        Map<Integer, List<List<T>>> map= new HashMap<>();

        Range verticalRange = new Range();
        verticalOrderTraversalSorted(root, map, verticalRange);

        System.out.println("verticalOrderTraversalSorted :");
        for(int vLevel = verticalRange.left; vLevel<=verticalRange.right; vLevel++) {
            List<T> vLevelList = new ArrayList<>();
            for(List<T> l : map.get(vLevel)){
                Collections.sort(l);
                vLevelList.addAll(l);
            }
            System.out.println("verticalLevel " + vLevel + " : " + vLevelList);
        }
    }

    /**
     * This requires BFS traversal so that output be in top-down order.
     *
     * @param node
     * @param map
     * @param range
     */
    private void verticalOrderTraversalSorted(Node<T> node, Map<Integer, List<List<T>>> map, Range range){

        Queue<NodeInfo> queue = new ArrayDeque<>(); //new LinkedList<>();
        if(node!=null){
            queue.add(new NodeInfo(node, 0,0));
        }

        while(!queue.isEmpty()){
            NodeInfo nodeInfo = queue.remove();
            node = nodeInfo.node;
            int vLevel = nodeInfo.vLevel;
            int hLevel = nodeInfo.hLevel;

            if(vLevel<range.left)  range.left = vLevel;
            if(vLevel>range.right) range.right = vLevel;

            List<List<T>> levelList = map.computeIfAbsent(vLevel, k->new ArrayList<>());
            while(levelList.size()<=hLevel) {
                map.get(vLevel).add(new ArrayList<>()); //TODO: optimise
            }
            levelList.get(hLevel).add(nodeInfo.node.data);

            if(node.left!=null)  queue.add(new NodeInfo(node.left, vLevel-1, hLevel+1));
            if(node.right!=null) queue.add(new NodeInfo(node.right, vLevel+1, hLevel+1));
        }
    }

    private class NodeInfo {
        Node<T> node;
        int vLevel, hLevel;

        public NodeInfo(Node<T> node, int vLevel) {
            this.node = node;this.vLevel = vLevel;
        }

        public NodeInfo(Node<T> node, int vLevel, int hLevel) {
            this.node = node; this.vLevel = vLevel; this.hLevel = hLevel;
        }
    }

    public void verticalOrderTraversalSortedTreeMap(){
        Map<Integer, List<List<T>>> map= new TreeMap<>();

        verticalOrderTraversalSortedTreeMap(root, map);

        System.out.println("verticalOrderTraversalSortedTreeMap :");
        for(int vLevel : map.keySet()) {
            List<T> vLevelList = new ArrayList<>();
            for(List<T> l : map.get(vLevel)){
                Collections.sort(l);
                vLevelList.addAll(l);
            }
            System.out.println("verticalLevel " + vLevel + " : " + vLevelList);
        }
    }

    /**
     * This requires BFS traversal so that output be in top-down order.
     *
     * @param node
     * @param map
     */
    private void verticalOrderTraversalSortedTreeMap(Node<T> node, Map<Integer, List<List<T>>> map){

        Queue<NodeInfo> queue = new ArrayDeque<>(); //new LinkedList<>();
        if(node!=null){
            queue.add(new NodeInfo(node, 0,0));
        }

        while(!queue.isEmpty()){
            NodeInfo nodeInfo = queue.remove();
            node = nodeInfo.node;
            int vLevel = nodeInfo.vLevel;
            int hLevel = nodeInfo.hLevel;

            List<List<T>> levelList = map.computeIfAbsent(vLevel, k->new ArrayList<>());
            while(levelList.size()<=hLevel) {
                map.get(vLevel).add(new ArrayList<>()); //TODO: optimise
            }
            levelList.get(hLevel).add(nodeInfo.node.data);

            if(node.left!=null)  queue.add(new NodeInfo(node.left, vLevel-1, hLevel+1));
            if(node.right!=null) queue.add(new NodeInfo(node.right, vLevel+1, hLevel+1));
        }
    }

    /**
     *
     * @param args
     */
    public static void main(String [] args){
        /*
         * Binary Tree.
         *                     1
         *                 2      3
         *               4  5    6  7
         *                     8
         */
        final BinaryTreeVerticalTraversal<Integer> tree = new BinaryTreeVerticalTraversal<>(1);
        tree.insertAsLeftChild(2, 1);
        tree.insertAsRightChild(3, 1);
        tree.insertAsLeftChild(4,2);
        tree.insertAsRightChild(5,2);
        tree.insertAsLeftChild(6,3);
        tree.insertAsRightChild(7, 3);
        tree.insertAsLeftChild(8,6);

        tree.verticalOrderTraversal();
        tree.verticalOrderTraversalSorted();
        tree.verticalOrderTraversalSortedTreeMap();
        tree.verticalOrderTraversalBruteForce();
    }
}

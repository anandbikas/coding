package com.anand.coding.dsalgo.tree.map;

import com.anand.coding.dsalgo.stack.ArrayStack;
import com.anand.coding.dsalgo.stack.Stack;

import java.util.ArrayList;
import java.util.List;

/**
 * BSTMap
 */
public class BSTMap<K extends Comparable<K>, V> {

    protected Node<K,V> root;

    public BSTMap(){
        super();
    }

    /**
     *
     */
    public void inOrderTraversalRec(){
        System.out.println("inOrderTraversalRec");
        inOrderTraversalRec(this.root);
        System.out.println();
    }

    /**
     * Print in order left, root, right
     * @param node
     */
    private void inOrderTraversalRec(final Node node){
        if(node == null){
            return;
        }
        inOrderTraversalRec(node.left);
        System.out.print(node.value + "  ");
        inOrderTraversalRec(node.right);
    }

    /**
     * 
     * @param key
     * @param value
     * @return
     */
    public Node put(K key, V value){

        Node<K,V> parent = null;
        Node<K,V> pivotNode = root;

        while(!(pivotNode == null || pivotNode.key.compareTo(key)==0)){
            parent = pivotNode;
            pivotNode = pivotNode.key.compareTo(key) > 0 ? pivotNode.left : pivotNode.right;
        }

        if(pivotNode!=null) {
            //Duplicate node overwritten.
            pivotNode.value = value;
            return pivotNode;
        }

        pivotNode = new Node<>(key, value);
        if(parent == null){
            this.root = pivotNode;
        } else if(parent.key.compareTo(key)>0){
            parent.left = pivotNode;
        } else {
            parent.right = pivotNode;
        }
        return pivotNode;
    }

    /**
     *
     * @param key
     * @return
     */
    public V get(K key){

        Node<K,V> pivotNode=root;
        while(!(pivotNode == null || pivotNode.key.compareTo(key)==0)){
            pivotNode = pivotNode.key.compareTo(key) > 0 ? pivotNode.left : pivotNode.right;
        }
        return pivotNode==null ? null : pivotNode.value;
    }

    /**
     *
     * @param key
     * @return
     */
    public boolean contains(K key){
        return get(key)!=null;
    }

    /**
     * InOrderTraversal gives sorted list.
     *
     * @return
     */
    public List<K> keySet(){

        List<K> sortedList = new ArrayList<>();

        Stack<Node> stack = new ArrayStack<>();

        for(Node node = root; node!= null; node=node.left){
            stack.push(node);
        }
        while(!stack.isEmpty()){
            Node node = stack.pop();
            sortedList.add((K)node.key);

            for(node=node.right; node!= null; node = node.left){
                stack.push(node);
            }
        }

        return sortedList;
    }

    /**
     * InOrderTraversal gives sorted list.
     *
     * @return
     */
    public List<V> values(){

        List<V> sortedList = new ArrayList<>();

        Stack<Node> stack = new ArrayStack<>();

        for(Node node = root; node!= null; node=node.left){
            stack.push(node);
        }
        while(!stack.isEmpty()){
            Node node = stack.pop();
            sortedList.add((V)node.value);

            for(node=node.right; node!= null; node = node.left){
                stack.push(node);
            }
        }

        return sortedList;
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return "BSTMap{" +
                "root=" + root +
                '}';
    }

    /**
     * Main function to test the code.
     *
     * @param args
     */
    public static void main(String args[]){

        /*
         *               5
         *           3      7
         *        2   4  6   8
         */
        final BSTMap<String, Integer> bstMap = new BSTMap<>();

        for(int x : new int[]{ 5,7,3,6,4,8,2}) {
            bstMap.put("key-" + x, x);
        }

        System.out.println(bstMap);

        bstMap.inOrderTraversalRec();
        System.out.println("bstMap.get(key-6): " + bstMap.get("key-6"));

        //Duplicate value overwritten
        bstMap.put("key-8", 16);
        bstMap.put("key-8", 24);

        bstMap.inOrderTraversalRec();
        System.out.println("bstMap.get(key-8): " + bstMap.get("key-8"));

        System.out.println(bstMap.keySet());
        System.out.println(bstMap.values());
        System.out.println();


        //Multi-value example
        final BSTMap<String, List<Integer>> bstMap1 = new BSTMap<>();

        for(int x : new int[]{ 5,7,3,6,4,8,2}) {
            if (!bstMap1.contains("key-"+x)) {
                bstMap1.put("key-"+x, new ArrayList<>());
            }
            bstMap1.get("key-"+x).add(x);
        }
        bstMap1.get("key-8").add(16);
        bstMap1.get("key-8").add(24);

        System.out.println(bstMap1);

        bstMap1.inOrderTraversalRec();
        System.out.println("bstMap1.get(key-6): " + bstMap1.get("key-6"));
        System.out.println("bstMap1.get(key-8): " + bstMap1.get("key-8"));

        System.out.println(bstMap1.keySet());
        System.out.println(bstMap1.values());

    }
}

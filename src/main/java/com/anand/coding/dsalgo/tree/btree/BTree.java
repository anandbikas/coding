package com.anand.coding.dsalgo.tree.btree;

import java.util.ArrayList;
import java.util.List;

/**
 * B-Tree (Self Balancing Search Tree): It is a fat and dwarf tree. The height of a B-Tree is kept low by putting
 * maximum possible keys in a B-Tree node.
 * This ensures efficient tree operations than other trees due to lower height.
 *
 * B-Tree properties:
 * 1. degree = t, means a node should have at least t children except for the leaf node.
 * 2. min_length of a node: t-1
 * 3. max_length of a node: 2t-1
 * 4. min_length of root node = 1
 * 5. all keys are sorted in increasing order.
 * 6. All leaves are at same level.
 * 7. B trees grows/shrinks from the upward/root, while BST from downward/leaf.
 *
 *
 * Degree vs Order:
 * B-Tree is a m-way tree:
 * 1. order = m, means a node can have at most m children and m-1 keys.
 *
 */
public class BTree<K extends Comparable<K>,V> {

    private BNode<K,V> root;

    // BTree degree.
    private int t;

    /**
     *
     * @param t
     */
    public BTree(int t) {
        this.t = t;
    }

    /**
     *
     * @param key
     * @return
     */
    public BNode<K,V> findNodeForKey(K key) {
        return findNodeForKey(root, key);
    }


    /**
     * Find a BTree node which contains the key, null if not found
     *
     * @param node
     * @param key
     * @return
     */
    private BNode<K,V> findNodeForKey(BNode<K,V> node, K key) {

        if(node==null){
            return null;
        }

        int keyIndex = node.getKeyIndex(key);

        if(keyIndex!=-1 && key.compareTo(node.keyValueList[keyIndex].getKey())==0){
            return node;
        }

        // If not found, traverse down the children
        if(node.isLeaf()){
            return null;
        }

        return findNodeForKey(node.children[keyIndex + 1], key);
    }

    /**
     *
     * @param key
     * @return
     */
    private V get(K key) {
        return get(root,key);
    }

    /**
     * Find value for a given key, null if not found
     *
     * @param node
     * @param key
     * @return
     */
    private V get(BNode<K,V> node, K key) {

        if(node==null){
            return null;
        }

        int keyIndex = node.getKeyIndex(key);

        if(keyIndex!=-1 && key.compareTo(node.keyValueList[keyIndex].getKey())==0){
            return node.keyValueList[keyIndex].getValue();
        }

        // If not found, traverse down the children
        if(node.isLeaf()){
            return null;
        }

        return get(node.children[keyIndex + 1], key);
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
     *
     */
    public void display(){
        display(root);
        System.out.println();
    }

    /**
     *
     * @param node
     */
    private void display(BNode<K,V> node){

        if(node==null){
            return;
        }

        //Traverse through n keys.
        //Left-Child-Node -> Key ...
        //At the end traverse Right-Child-Node of last key.

        int i;
        if(node.isLeaf()) {
            for (i = 0; i < node.n; i++) {
                System.out.print(node.keyValueList[i] + ", ");
            }
        } else {
            for (i = 0; i < node.n; i++) {
                display(node.children[i]);
                System.out.print(node.keyValueList[i] + ", ");
            }
            display(node.children[i]);
        }
    }

    /**
     *
     * @param key
     * @return
     */
    public void insert(K key, V value) {

        if (root == null) {
            root = new BNode<>(t);
            root.insertAsSorted(key,value);
            return;
        }

        BNode<K,V> parent = null;
        BNode<K,V> node = root;
        int childIndex = 0;

        while (true) {

            //Proactive Split
            if (node.isFull()) {
                if (parent == null) {
                    parent = new BNode<>(t);
                    root = parent;
                }
                parent.splitChild(childIndex, node);

                if(key.compareTo(parent.keyValueList[childIndex].getKey())==0){
                    //Duplicate Data rejected
                    return;
                }

                if (key.compareTo(parent.keyValueList[childIndex].getKey()) < 0) {
                    node = parent.children[childIndex];
                } else {
                    node = parent.children[childIndex+1];
                }
            }


            int keyIndex = node.getKeyIndex(key);

            if (key.compareTo(node.keyValueList[keyIndex].getKey()) == 0) {
                //Duplicate Data rejected
                //For upsert, we can update the value of the key.
                return;
            }

            // If not found, traverse down the children
            if (node.isLeaf()) {
                break;
            }
            parent = node;
            childIndex = keyIndex+1;
            node = node.children[childIndex];
        }

        //Insert into the leaf node.
        node.insertAsSorted(key, value);
    }

    /**
     *
     * @param key
     */
    public void delete(K key) {
        //TODO: Implementation
    }

    /**
     *
     * @param args
     */
    public static void main(String [] args){

        BTree<Integer, String> bTree = new BTree<>(3);

        int A[] = {10,20,30,40,50,60,70,80,90,40,91,92,93,94,95,96,97,98,99,100,101,102,103,104,105,106,107,108};

        for(int x: A){
            bTree.insert(x, String.format("v-%s",x));
        }

        bTree.display();

        for(int x: A){
            System.out.println(bTree.findNodeForKey(x) + "\n");
        }
        System.out.println(bTree.findNodeForKey(13) + "\n");

        System.out.println(bTree.get(90) + "\n");
        System.out.println(bTree.contains(90) + "\n");


        //Multi-value example
        BTree<Integer, List<String>> bTree1 = new BTree<>(3);

        int B[] = {10,20,30,40,50};

        for(int i=0; i<3; i++) {
            for (int x : B) {
                if (!bTree1.contains(x)) {
                    bTree1.insert(x, new ArrayList<>());
                }
                bTree1.get(x).add(String.format("v%d-%s",i, x));
            }
        }
        bTree1.display();

        for(int x: B){
            System.out.println(bTree1.findNodeForKey(x) + "\n");
        }
        System.out.println(bTree1.findNodeForKey(13) + "\n");

        System.out.println(bTree1.get(40) + "\n");
        System.out.println(bTree1.contains(40) + "\n");
    }
}
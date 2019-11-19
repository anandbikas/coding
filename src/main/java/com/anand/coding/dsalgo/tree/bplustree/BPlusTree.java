package com.anand.coding.dsalgo.tree.bplustree;

import java.util.ArrayList;
import java.util.List;

/**
 * BPlusTree:
 *
 * Its like BTree except...
 *
 * 1. Internal nodes only contain keys.
 * 2. Leaf nodes contain both key and value.
 * 3. The keys in internal nodes are also stored in leaf nodes along with their values.
 *    (Note: Best way to store an internal key at the beginning of its right leaf child)
 *
 * 4. All leaf nodes are connected to facilitate linear traversal.
 *
 */
public class BPlusTree<K extends Comparable<K>, V> {

    private BPlusNode<K,V> root;

    //Stores the first ever created node, which is the start point for traversing through leaf nodes.
    BPlusNodeLeaf<K,V> firstNode;

    // BPlusTree degree.
    private int t;

    /**
     *
     * @param t
     */
    public BPlusTree(int t) {
        this.t = t;
    }

    /**
     *
     * @param key
     * @return
     */
    public BPlusNode<K,V> findNodeForKey(K key) {
        return findNodeForKey(root, key);
    }

    /**
     * Find a BPlusTree node which contains the key, null if not found
     *
     * @param node
     * @param key
     * @return
     */
    private BPlusNode<K,V> findNodeForKey(BPlusNode<K,V> node, K key) {

        if(node==null){
            return null;
        }

        int keyIndex = node.getKeyIndex(key);

        if(keyIndex!=-1 && key.compareTo(node.getKey(keyIndex))==0){
            return node;
        }

        // If not found, traverse down the children
        if(node instanceof BPlusNodeLeaf){
            return null;
        }

        return findNodeForKey(((BPlusNodeInternal<K, V>) node).getChildren(keyIndex + 1), key);
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
    private V get(BPlusNode<K,V> node, K key) {

        if(node==null){
            return null;
        }

        int keyIndex = node.getKeyIndex(key);

        if(keyIndex!=-1 && key.compareTo(node.getKey(keyIndex))==0){

            //If this is the leaf node, get the value
            if(node instanceof BPlusNodeLeaf){
                return (V)((BPlusNodeLeaf) node).keyValueList[keyIndex].getValue();
            }
            //Else, we need to traverse down to the leftmost leaf node (of its right child) and get the first element.
            else {
                node = ((BPlusNodeInternal<K, V>) node).getChildren(keyIndex+1);
                while(node instanceof BPlusNodeInternal){
                    node = ((BPlusNodeInternal<K, V>) node).getChildren(0);
                }
                return (V)((BPlusNodeLeaf) node).keyValueList[0].getValue();
            }
        }

        // If not found, traverse down the children
        if(node instanceof BPlusNodeLeaf){
            return null;
        }

        return get(((BPlusNodeInternal<K, V>) node).getChildren(keyIndex + 1), key);
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
    private void display(){

        for(BPlusNodeLeaf<K,V> node=firstNode; node != null; node=node.next){

            //Traverse through n keys (all situated at the leaf nodes).
            for (int i = 0; i < node.size(); i++) {
                System.out.print(node.getKey(i) + ", ");
            }
        }
        System.out.println();
    }


    /**
     *
     * @param key
     * @return
     */
    public void insert(K key, V value) {

        if (root == null) {
            root = new BPlusNodeLeaf<>(t);
            root.insertAsSorted(key,value);
            firstNode = (BPlusNodeLeaf<K,V>) root;
            return;
        }

        BPlusNodeInternal<K,V> parent = null;
        BPlusNode<K,V> node = root;
        int childIndex = 0;

        while (true) {

            //Proactive Split
            if (node.isFull()) {
                if (parent == null) {
                    parent = new BPlusNodeInternal<>(t);
                    root = parent;
                }
                parent.splitChild(childIndex, node);

                if(key.compareTo(parent.getKey(childIndex))==0){
                    //Duplicate Data rejected
                    return;
                }

                if (key.compareTo(parent.getKey(childIndex)) < 0) {
                    node = parent.getChildren(childIndex);
                } else {
                    node = parent.getChildren(childIndex+1);
                }
            }

            int keyIndex = node.getKeyIndex(key);

            if(keyIndex!=-1 && key.compareTo(node.getKey(keyIndex))==0){
                //Duplicate Data rejected
                //For upsert, we can update the value of the key.
                return;
            }

            // If not found, traverse down the children
            if (node instanceof BPlusNodeLeaf) {
                break;
            }
            parent = (BPlusNodeInternal<K, V>) node;
            childIndex = keyIndex+1;
            node = ((BPlusNodeInternal<K, V>)node).getChildren(childIndex);
        }

        //Insert into the leaf node.
        node.insertAsSorted(key,value);
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

        BPlusTree<Integer, String> bPlusTree = new BPlusTree<>(3);

        int A[] = {10,20,30,40,50,60,70,80,90,40,91,92,93,94,95,96,97,98,99,100,101,102,103,104,105,106,107,108};

        for(int x: A){
            bPlusTree.insert(x, String.format("v-%s",x));
        }

        bPlusTree.display();

        for(int x: A){
            System.out.println(bPlusTree.findNodeForKey(x) + "\n");
        }

        System.out.println(bPlusTree.findNodeForKey(13) + "\n");

        System.out.println(bPlusTree.get(90) + "\n");
        System.out.println(bPlusTree.contains(90) + "\n");

        //Multi-value example
        BPlusTree<Integer, List<String>> bPlusTree1 = new BPlusTree<>(3);

        int B[] = {10,20,30,40,50};

        for(int i=0; i<3; i++) {
            for (int x : B) {
                if (!bPlusTree1.contains(x)) {
                    bPlusTree1.insert(x, new ArrayList<>());
                }
                bPlusTree1.get(x).add(String.format("v%d-%s",i, x));
            }
        }
        bPlusTree1.display();

        for(int x: B){
            System.out.println(bPlusTree1.findNodeForKey(x) + "\n");
        }
        System.out.println(bPlusTree1.findNodeForKey(13) + "\n");

        System.out.println(bPlusTree1.get(40) + "\n");
        System.out.println(bPlusTree1.contains(40) + "\n");
    }
}
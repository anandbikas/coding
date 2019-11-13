package com.anand.coding.dsalgo.tree.btree;

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
 */
public class BTree<K extends Comparable<K>> {

    private BNode<K> root;

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
    public BNode<K> findNodeForKey(K key) {
        return findNodeForKey(root, key);
    }

    /**
     * Find a BTree node which contains the key, null if not found
     *
     * @param node
     * @param key
     * @return
     */
    private BNode<K> findNodeForKey(BNode<K> node, K key) {

        if(node==null){
            return null;
        }

        //Binary Search
        int left=0, right = node.n-1;
        while (left <= right) {
            int mid = left + (right-left)/2;

            if(key.compareTo(node.keys[mid])==0){
                return node;
            }

            if (key.compareTo(node.keys[mid])<0) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        // If not found, traverse down the children
        if(node.isLeaf()){
            return null;
        }
        return findNodeForKey(node.children[right+1], key);
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
    private void display(BNode<K> node){

        if(node==null){
            return;
        }

        //Traverse through n keys.
        //Left-Child-Node -> Key ...
        //At the end traverse Right-Child-Node of last key.

        int i;
        if(node.isLeaf()) {
            for (i = 0; i < node.n; i++) {
                System.out.print(node.keys[i] + ", ");
            }
        } else {
            for (i = 0; i < node.n; i++) {
                display(node.children[i]);
                System.out.print(node.keys[i] + ", ");
            }
            display(node.children[i]);
        }
    }

    /**
     *
     * @param key
     * @return
     */
    public void insert(K key) {

        if (root == null) {
            root = new BNode<>(t);
            root.insertAsSorted(key);
            return;
        }

        BNode<K> parent = null;
        BNode<K> node = root;
        int childIndex = 0;

        while (true) {

            //Proactive Split
            if (node.isFull()) {
                if (parent == null) {
                    parent = new BNode<>(t);
                    root = parent;
                }
                parent.splitChild(childIndex, node);

                if(key.compareTo(parent.keys[childIndex])==0){
                    //Duplicate Data rejected
                    return;
                }

                if (key.compareTo(parent.keys[childIndex]) < 0) {
                    node = parent.children[childIndex];
                } else {
                    node = parent.children[childIndex+1];
                }
            }

            //Binary Search
            int left = 0, right = node.n - 1;
            while (left <= right) {
                int mid = left + (right - left) / 2;

                if (key.compareTo(node.keys[mid]) == 0) {
                    //Duplicate Data rejected
                    //For upsert, we can update the value of the key.
                    return;
                }

                if (key.compareTo(node.keys[mid]) < 0) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }

            // If not found, traverse down the children
            if (node.isLeaf()) {
                break;
            }
            parent = node;
            childIndex = right+1;
            node = node.children[childIndex];
        }

        //Insert into the leaf node.
        node.insertAsSorted(key);
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

        BTree<Integer> bTree = new BTree<>(3);

        int A[] = {10,20,30,40,50,60,70,80,90,40,91,92,93,94,95,96,97,98,99,100,101,102,103,104,105,106,107,108};
        //int A[] = {10,20,30,40,50,60,70,80,90,45, 51,52};

        for(int x: A){
            bTree.insert(x);
        }

        bTree.display();

        for(int x: A){
            System.out.println(bTree.findNodeForKey(x) + "\n");
        }
    }
}
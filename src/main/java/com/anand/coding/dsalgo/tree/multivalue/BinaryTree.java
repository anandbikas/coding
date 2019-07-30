package com.anand.coding.dsalgo.tree.multivalue;

/**
 * Multi-Value-Node Binary Tree
 *
 */
public class BinaryTree<T extends Comparable<T>> {

    protected Node<T> root;

    public BinaryTree(){
        super();
    }

    public BinaryTree(final T data){
        root = new Node<>(data);
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
     * @param root
     */
    private void inOrderTraversalRec(final Node root){
        if(root == null){
            return;
        }
        inOrderTraversalRec(root.getLeft());
        root.getDataList().forEach(data ->{
            System.out.print(data + "  ");
        });
        inOrderTraversalRec(root.getRight());
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return "BinaryTree{" +
                "root=" + root +
                '}';
    }
}

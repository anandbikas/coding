package com.anand.coding.dsalgo.tree;

/**
 * Binary Seach Tree
 */
public class BinarySearchTree extends BinaryTree {

    public BinarySearchTree(){
        super();
    }

    /**
     *
     * @param data
     * @return
     */
    public Node insert(int data){

        Node parent = null;
        Node pivotNode = root;

        while(pivotNode != null && pivotNode.getData()!=data){
            parent = pivotNode;
            pivotNode = pivotNode.getData() > data ? pivotNode.getLeft() : pivotNode.getRight();
        }

        if(pivotNode==null){
            pivotNode = new Node(data);
            if(parent == null){
                root = pivotNode;
            } else if(parent.getData()>data){
                parent.setLeft(pivotNode);
            } else {
                parent.setRight(pivotNode);
            }
        }
        return pivotNode;
    }

    /**
     *
     * @param data
     */
    public void insertRec(int data){
        root = insertRec(root, data);
    }

    /**
     *
     * @param root
     * @param data
     * @return
     */
    private Node insertRec(Node root, int data){

        if(root==null){
            root = new Node(data);
            return root;
        }

        if(root.getData()>data) {
            root.setLeft(insertRec(root.getLeft(), data));
        } else {
            root.setRight(insertRec(root.getRight(), data));
        }
        return root;
    }

    /**
     *
     * @param data
     * @return
     */
    public Node search(int data){

        Node pivotNode=root;
        while(pivotNode != null && pivotNode.getData()!=data){
            pivotNode = pivotNode.getData() > data ? pivotNode.getLeft() : pivotNode.getRight();
        }
        return pivotNode;
    }

    /**
     *
     * @param data
     * @return
     */
    @Override
    public Node searchRec(int data){
        return searchRec(root, data);
    }

    /**
     *
     * @param root
     * @param data
     * @return
     */
    private Node searchRec(Node root, int data){

        if(root==null || root.getData()==data){
            return root;
        }
        return searchRec(root.getData()>data ? root.getLeft() : root.getRight(), data);
    }

    /**
     * Delete the pivotNode matching the data
     *
     * @param data
     * @return
     */
    public Node delete(int data){

        Node parent = null;
        Node pivotNode = root;

        //Find the pivotNode and its parent.
        while(pivotNode != null && pivotNode.getData() != data){
            parent = pivotNode;
            pivotNode = pivotNode.getData() > data ? pivotNode.getLeft() : pivotNode.getRight();
        }

        // if pivotNode not found, return
        if(pivotNode == null){
            return null;
        }

        /**
         * Deletion Algorithm:
         *  1. If right of pivotNode is null,
         *     1.1 Replace pivotNode with its left (may be null or a valid node)
         *
         *  2. Else if left of right of pivotNode is null, i.e; pivotNode.right is the inOrder successor
         *     2.1 left of pivotNode becomes left of right of pivotNode
         *     2.2 Replace pivotNode with right of pivotNode
         *
         *  3. Else Find the inOrder successor of pivotNode along with its parent.
         *     3.1 right of inOrderSuccessor becomes left of inOrderSuccessor's parent
         *     3.2 left and right of pivotNode becomes left and right of inOrderSuccessor respectively
         *     3.3 Replace pivotNode with inOrderSuccessor
         *
         */
        Node nodeToShift;
        if(pivotNode.getRight() == null) {
            nodeToShift = pivotNode.getLeft();

        } else if (pivotNode.getRight().getLeft() == null){
            nodeToShift = pivotNode.getRight();
            nodeToShift.setLeft(pivotNode.getLeft());

        } else {
            //Find inorder successor of pivotNode
            Node parentOfNodeToShift = pivotNode.getRight();
            nodeToShift = parentOfNodeToShift.getLeft();
            while(nodeToShift.getLeft()!= null){
                parentOfNodeToShift=nodeToShift;
                nodeToShift=nodeToShift.getLeft();
            }

            parentOfNodeToShift.setLeft(nodeToShift.getRight());

            nodeToShift.setLeft(pivotNode.getLeft());
            nodeToShift.setRight(pivotNode.getRight());
        }

        if(parent == null){
            root = nodeToShift;
        } else if(parent.getLeft() == pivotNode){
            parent.setLeft(nodeToShift);
        } else {
            parent.setRight(nodeToShift);
        }

        pivotNode.setLeft(null);
        pivotNode.setRight(null);
        return pivotNode;
    }

    /**
     *
     * @param data
     */
    public void deleteRec(int data){
        //TODO: fix
        root = deleteRec(root, data);
    }

    /**
     *
     * @param root
     * @param data
     * @return
     */
    private Node deleteRec(Node root, int data){

        //TODO: Incomplete
        if(root==null){
            return root;
        }

        if(root.getRight()==null){
            return root.getLeft();
        }

        if(root.getData()>data) {
            root.setLeft(insertRec(root.getLeft(), data));
        } else {
            root.setRight(insertRec(root.getRight(), data));
        }
        return root;
    }

    /**
     * Find the minimum value node
     */
    public Node min(){
        if(root==null){
            return null;
        }
        Node node=root;
        for(; node.getLeft()!=null; node=node.getLeft());
        return node;
    }

    /**
     * Find the maximum value node
     * @return
     */
    public Node max(){
        if(root==null){
            return null;
        }
        Node node=root;
        for(; node.getRight()!=null; node=node.getRight());
        return node;
    }

    /**
     * Find the lowest common ancestor node of the two given nodes
     *
     * @return
     */
    public Node lowestCommonAncestor(int data1, int data2){
        if(data1==data2){
            return null;
        }
        if(data1 > data2) {
            int temp = data2;
            data2 = data1;
            data1 = temp;
        }
        Node node = root;
        while(node != null){
            if(node.getData()<data1){
                node = node.getRight();
            } else if(node.getData()>data2){
                node = node.getLeft();
            } else {
                break;
            }
        }
        if(searchRec(node, data1)==null || searchRec(node, data2)==null){
            return null;
        }
        return node;
    }

    /**
     *
     */
    @Override
    public void printAllPaths() {
        System.out.println("printAllPaths");
        printAllPaths(root);
        System.out.println();
    }

    /**
     * Print all the paths of the tree.
     * Use binary search tree feature to print the path by traversing from root to child
     *
     * @param root
     */
    private void printAllPaths(final Node root){
        if(root == null){
            return;
        }
        if(root.getLeft()==null && root.getRight()==null){
            for(Node node = this.root; node!=null;){
                System.out.print(node.getData() + "  ");
                node = node.getData() > root.getData() ? node.getLeft() : node.getRight();
            }
            System.out.println();
            return;
        }
        printAllPaths(root.getLeft());
        printAllPaths(root.getRight());
    }

    /**
     * Main function to test the code.
     *
     * @param args
     */
    public static void main(String args[]){

        /**
         *               5
         *           3      7
         *        2   4  6   8
         */
        final BinarySearchTree bst = new BinarySearchTree();

        for(int x : new int[]{ 5,7,3,6,4,8,2}) {
            bst.insertRec(x);
        }

        System.out.println(bst);
        bst.inOrderTraversalRec();
        bst.printAllPaths();

        System.out.println("bst.height(): " + bst.height());
        System.out.println("bst.search(8): " + bst.search(8));
        System.out.println("bst.searchRec(8): " + bst.searchRec(8));

        System.out.println("bst.isBinarySearchTree(): " + bst.isBinarySearchTree());

        System.out.println("bst.lowestCommonAncestor(4, 8): " + bst.lowestCommonAncestor(4, 8));
        System.out.println("bst.lowestCommonAncestor(4, 11): " + bst.lowestCommonAncestor(4, 11));

        System.out.println("bst.min():" + bst.min());
        System.out.println("bst.max():" + bst.max());

        System.out.println("bst.delete(4): " + bst.delete(4));
        bst.inOrderTraversalRec();
        System.out.println("bst.search(4): " + bst.search(4));
        System.out.println("bst.delete(9): " + bst.delete(4));
    }
}

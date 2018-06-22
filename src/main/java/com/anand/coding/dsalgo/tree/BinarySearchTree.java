package com.anand.coding.dsalgo.tree;

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
     *
     * @param data
     * @return
     */
    public Node delete(int data){

        Node parent = null;
        Node pivotNode = root;

        while(pivotNode != null && pivotNode.getData() != data){
            parent = pivotNode;
            pivotNode = pivotNode.getData() > data ? pivotNode.getLeft() : pivotNode.getRight();
        }

        if(pivotNode == null){
            // Data not found
            return null;
        }

        Node nodeToShift;
        if(pivotNode.getRight() == null) {
            nodeToShift = pivotNode.getLeft();

        } else if (pivotNode.getRight().getLeft() == null){
            nodeToShift = pivotNode.getRight();
            nodeToShift.setLeft(pivotNode.getLeft());

        } else {
            //Get inorder successor of pivotNode
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
     *
     */
    public int min(){
        if(root==null){
            return 0;
        }
        Node node=root;
        for(; node.getLeft()!=null; node=node.getLeft());
        return node.getData();
    }

    /**
     *
     */
    public int max(){
        if(root==null){
            return 0;
        }
        Node node=root;
        for(; node.getRight()!=null; node=node.getRight());
        return node.getData();
    }


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
        System.out.println("Height = " + bst.height());

        bst.inOrderTraversalRec();
        System.out.println(bst.search(8));
        System.out.println(bst.searchRec(8));

        System.out.println(bst.delete(4));

        bst.inOrderTraversalRec();
        System.out.println(bst.search(7));

        System.out.println(bst.isBinarySearchTree());

        System.out.println(bst.min());
        System.out.println(bst.max());
    }
}

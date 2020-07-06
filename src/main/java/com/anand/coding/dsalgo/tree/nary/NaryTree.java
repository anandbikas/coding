package com.anand.coding.dsalgo.tree.nary;

import com.anand.coding.dsalgo.queue.ArrayCircularQueue;
import com.anand.coding.dsalgo.queue.Queue;
import com.anand.coding.dsalgo.stack.ArrayStack;
import com.anand.coding.dsalgo.stack.Stack;

/**
 * Nary Tree
 *
 * A Nary tree has n nodes called children.
 *
 * 1. A Nary tee with n nodes has exactly n-1 edges
 * 2. Maximum number of nodes at level l is N pow(l-1).
 * 3. Maximum number of nodes in a Nary tree of height h is N pow(h) – 1.
 * 4. In a Nary Tree with n nodes, minimum possible height is ceil(logN(n+1))
 * 5. Number of nodes in a complete Nary tree lies betwwen N pow(h-1) to N pow(h) -1
 */
public class NaryTree <T extends Comparable<T>>{

    private NaryNode<T> root;
    private int N;

    public NaryTree(int N){
        super();
        this.N = N;
    }

    /**
     *
     * @param data
     * @param N
     */
    public NaryTree(final T data, int N){
        this.N = N;
        root = new NaryNode<T>(data, N);

    }

    /**
     *
     */
    public void preOrderTraversalRec(){
        System.out.println("preOrderTraversalRec");
        preOrderTraversalRec(root);
        System.out.println();
    }

    /**
     * Print in order root, children (left to right)
     * @param root
     */
    private void preOrderTraversalRec(final NaryNode root){
        if(root == null){
            return;
        }
        System.out.print(root.data + "  ");

        for(int i=0; i<N; i++){
            preOrderTraversalRec(root.getChild(i));
        }
    }

    /**
     *
     */
    public void postOrderTraversalRec(){
        System.out.println("postOrderTraversalRec");
        postOrderTraversalRec(root);
        System.out.println();
    }

    /**
     * Print in order children (left to right), root
     * @param root
     */
    private void postOrderTraversalRec(final NaryNode root){
        if(root == null){
            return;
        }
        for(int i=0; i<N; i++){
            postOrderTraversalRec(root.getChild(i));
        }
        System.out.print(root.data + "  ");
    }

    /**
     * Print in order root, children (left to right)
     * Without recursion
     */
    public void preOrderTraversal(){
        System.out.println("preOrderTraversal");

        Stack<NaryNode> stack = new ArrayStack<>();

        if(root!=null){
            stack.push(root);
        }
        while(!stack.isEmpty()) {
            NaryNode node = stack.pop();
            System.out.print(node.data + "  ");

            for(int i=N-1; i>=0; i--){
                if(node.getChild(i) != null) {
                    stack.push(node.getChild(i));
                }
            }
        }
        System.out.println();
    }
    
    /**
     * Print in order children (left to right), root
     * Use loop with the help of two stacks.
     *
     * Space complexity: O(height)
     */
    public void postOrderTraversal(){
        System.out.println("postOrderTraversal");

        Stack<NaryNode> stack = new ArrayStack<>();
        Stack<NaryNode> rootStack = new ArrayStack<>();


        if(root != null){
            stack.push(root);
        }
        while(!stack.isEmpty()){
            NaryNode node = stack.pop();

            if(node==null){
                //Process root node
                System.out.print(rootStack.pop().data + "  ");

            } else {
                //Push null to indicate a root needs to be processed from rootStack
                stack.push(null);
                rootStack.push(node);

                for(int i=N-1; i>=0; i--){
                    if(node.getChild(i) != null) {
                        stack.push(node.getChild(i));
                    }
                }
            }
        }

        System.out.println();
    }

    /**
     * Print in level order
     * Use loop with the help of a queue
     */
    public void levelOrderTraversal(){
        System.out.println("levelOrderTraversal");

        Queue<NaryNode> queue = new ArrayCircularQueue<>();

        if(root!=null){
            queue.insert(root);
        }

        while(!queue.isEmpty()){
            NaryNode node = queue.delete();
            System.out.print(node.data + "  ");

            for(int i=0; i<N; i++){
                if(node.getChild(i) != null) {
                    queue.insert(node.getChild(i));
                }
            }
        }
        System.out.println();
    }

    /**
     * Call printLevel method for each level
     */
    public void levelOrderTraversalBruteForce(){
        System.out.println("levelOrderTraversalBruteForce");

        for(int level = 1; level <=height(); level++){
            printLevel(level);
        }
        System.out.println();
    }

    /**
     *
     * @param level
     */
    public void printLevel(int level){
        System.out.println("printLevel " + level);
        printLevel(root, level, 1);
        System.out.println();
    }

    /**
     * Print data in a given level
     *
     * @param root
     * @param level
     * @param currentLevel this tags each node with its level, is handy to find the level of a node while processing.
     */
    private void printLevel(NaryNode root, int level, int currentLevel){
        if(root == null || currentLevel > level){
            return;
        }
        if(level == currentLevel){
            System.out.print( root.data + " ");
            return;
        }

        for(int i=0; i<N; i++){
            printLevel(root.getChild(i), level, currentLevel+1);
        }
    }

    /**
     *
     * @param data
     * @return
     */
    public NaryNode<T> searchRec(T data){
        return searchRec(root, data);
    }

    /**
     * If root is not the one, check in all children tree one by one.
     *
     * @param root
     * @param data
     * @return
     */
    private NaryNode<T> searchRec(NaryNode<T> root, T data){

        if(root==null || root.data.equals(data)){
            return root;
        }

        for(int i=0; i<N; i++){
            NaryNode<T> node = searchRec(root.getChild(i), data);
            if(node != null){
                return node;
            }
        }
        return null;
    }

    /**
     *
     */
    public void leftSideView(){
        System.out.println("leftSideView");
        leftSideView(root, 1, 1);
    }

    /**
     * Print all the nodes present in the left as seen from the left side
     *
     * @param root
     * @param currentLevel this tags each node with its level, is handy to find the level of a node while processing.
     * @param processingLevel decides how many levels have already been processed.
     * @return
     */
    private int leftSideView(NaryNode root, int currentLevel, int processingLevel){
        if(root== null){
            return processingLevel;
        }
        if(processingLevel == currentLevel) {
            System.out.println(root.data);
            processingLevel++;
        }


        for(int i=0; i<N; i++){
            processingLevel = leftSideView(root.getChild(i), currentLevel+1, processingLevel);
        }
        return processingLevel;
    }

    /**
     *
     */
    public void rightSideView(){
        System.out.println("rightSideView");
        rightSideView(root, 1, 1);
    }

    /**
     * Print all the nodes present in the right as seen from the right side
     *
     * @param root
     * @param currentLevel this tags each node with its level, is handy to find the level of a node while processing.
     * @param processingLevel decides how many levels have already been processed.
     * @return
     */
    private int rightSideView(NaryNode root, int currentLevel, int processingLevel){
        if(root== null){
            return processingLevel;
        }
        if(processingLevel == currentLevel) {
            System.out.println(root.data);
            processingLevel++;
        }


        for(int i=N-1; i>=0; i--){
            processingLevel = rightSideView(root.getChild(i), currentLevel+1, processingLevel);
        }
        return processingLevel;
    }

    /**
     *
     */
    public void printLeaves() {
        System.out.println("printLeaves");
        printLeaves(root);
        System.out.println();
    }

    /**
     * Print all the leave nodes
     *
     * @param root
     */
    private void printLeaves(final NaryNode root){
        if(root == null){
            return;
        }
        if(root.isLeafNode()){
            System.out.print(root.data + "  ");
            return;
        }
        for(int i=0; i<N; i++){
            printLeaves(root.getChild(i));
        }
    }

    /**
     *
     */
    public void printAllPaths() {
        System.out.println("printAllPaths");

        Stack<NaryNode> stack = new ArrayStack<>();
        printAllPaths(root, stack);
        System.out.println();
    }

    /**
     *  Print all the paths of the tree.
     *  with the help of a stack
     *
     * @param root
     * @param stack used to push the traversed node one by one.
     *              Pop the node once its path is processed.
     *              Pop the node once all the paths of its right subTree is processed.
     */
    private void printAllPaths(final NaryNode root, final Stack<NaryNode> stack){
        if(root == null){
            return;
        }
        stack.push(root);
        if(root.isLeafNode()){
            stack.display();
            stack.pop();
            return;
        }
        for(int i=0; i<N; i++){
            printAllPaths(root.getChild(i), stack);
        }

        //Once right subTree is processed, remove its parent
        stack.pop();
    }

    /**
     *
     * @return
     */
    public int height(){
        return height(root);
    }

    /**
     * Calculate height of the tree
     *
     * @param node
     * @return
     */
    public int height(NaryNode node){
        if(node==null) {
            return 0;
        }

        int maxHeightWithinChildren =0;

        for(int i=0; i<N; i++){
            maxHeightWithinChildren = Math.max(height(node.getChild(i)),maxHeightWithinChildren);
        }
        return maxHeightWithinChildren+1;
    }


    /**
     * Diameter of the tree (longest path in the tree)
     *
     * @return
     */
    public int diameter(){
        int[] A = diameterCalculator(root);
        return A[1];
    }

    /**
     * Just like height method. Twist is to calculate diameter as well.
     *
     * Return array of 2 elements: [height, diameter].
     * @param root
     * @return []
     */
    private int[] diameterCalculator(NaryNode<T> root){

        if(root == null){
            return new int[]{0,0};
        }

        int maxChildNodeHeight = 0;
        int secondMaxChildNodeHeight = 0;

        int maxChildNodeDiameter = 0;

        for(int i=0; i<N; i++) {
            int [] child = diameterCalculator(root.getChild(i));
            if(child[0]>=maxChildNodeHeight){
                secondMaxChildNodeHeight = maxChildNodeHeight;
                maxChildNodeHeight = child[0];
            }

            maxChildNodeDiameter = Math.max(child[1], maxChildNodeDiameter);
        }

        int thisNodeHeight = maxChildNodeHeight +1;
        int thisNodeDiameter = maxChildNodeHeight+secondMaxChildNodeHeight+1;

        int maxDiameterInThisTree = Math.max(maxChildNodeDiameter, thisNodeDiameter);
        return new int[]{thisNodeHeight, maxDiameterInThisTree};
    }

    /**
     *
     * @param tree
     * @return
     */
    public boolean isSimilar(NaryTree tree){
        return isSimilar(this.root, tree.root);
    }

    /**
     * Checks whether two trees are similar in structure
     *
     * @param root1
     * @param root2
     * @return
     */
    private boolean isSimilar(NaryNode root1, NaryNode root2){
        if(root1 == null && root2 == null){
            return true;
        }

        if(root1 == null || root2 ==null){
            return false;
        }

        boolean isSimilar = true;
        for(int i=0; i<N && isSimilar; i++) {
            isSimilar = isSimilar(root1.getChild(i), root2.getChild(i));
        }

        return isSimilar;
    }

    /**
     *
     * @param tree
     * @return
     */
    public boolean isCopy(NaryTree tree){
        return isCopy(this.root, tree.root);
    }

    /**
     * Checks whether two trees are similar in structure as well as data
     *
     * @param root1
     * @param root2
     * @return
     */
    private boolean isCopy(NaryNode root1, NaryNode root2){
        if(root1 == null && root2 == null){
            return true;
        }

        if(root1 == null || root2 ==null){
            return false;
        }


        boolean isCopy = root1.data.equals(root2.data);
        for(int i=0; i<N && isCopy; i++) {
            isCopy = isCopy(root1.getChild(i), root2.getChild(i));
        }

        return isCopy;
    }

    /**
     *
     * @return
     */
    public int numberOfNodes(){
        return numberOfNodes(root);
    }

    /**
     * Calculate number of nodes
     *
     * @param root
     * @return
     */
    private int numberOfNodes(NaryNode root){
        if(root==null){
            return 0;
        }

        int numberOfNodes = 1;
        for(int i=0; i<N; i++) {
            numberOfNodes += numberOfNodes(root.getChild(i));
        }
        return numberOfNodes;
    }

    /**
     *
     * @param data
     * @param nodeData
     * @param childIndex
     * @return
     */
    public boolean insertAsChild(final T data, final T nodeData, int childIndex){
        return insertAsChild(root, data, nodeData, childIndex);
    }

    /**
     * Insert a node to a provided childIndex of the provided node
     *
     * @param root
     * @param data
     * @param nodeData
     * @param childIndex
     * @return
     */
    private boolean insertAsChild(final NaryNode<T> root, final T data, final T nodeData, int childIndex){

        if(null == root){
            return false;
        }
        if(root.data.equals(nodeData)){
            final NaryNode<T> newNode = new NaryNode<>(data, N);
            newNode.setChild(childIndex, root.getChild(childIndex));
            root.setChild(childIndex, newNode);
            return true;

        } else{
            boolean isInserted = false;
            for(int i=0; i<N && !isInserted; i++) {
                isInserted = insertAsChild(root.getChild(i), data, nodeData, childIndex);
            }
            return isInserted;
        }
    }


    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return "NaryTree{" +
                "root=" + root +
                '}';
    }


    /**
     * Main function to test the code.
     *
     * @param args
     */
    public static void main(String[] args) {

        /*
         *                     1
         *                 2      3
         *               4  5    6  7
         *                     8
         */
        final NaryTree<Integer> naryTree = new NaryTree<>(1, 2);

        naryTree.insertAsChild(2, 1, 0);
        naryTree.insertAsChild(3, 1, 1);

        naryTree.insertAsChild(4, 2, 0);
        naryTree.insertAsChild(5, 2, 1);

        naryTree.insertAsChild(6, 3, 0);
        naryTree.insertAsChild(7, 3, 1);

        naryTree.insertAsChild(8, 6, 0);

        System.out.println(naryTree);

        naryTree.preOrderTraversal();
        naryTree.preOrderTraversalRec();

        naryTree.postOrderTraversalRec();
        naryTree.postOrderTraversal();

        naryTree.levelOrderTraversal();
        naryTree.levelOrderTraversalBruteForce();

        naryTree.printAllPaths();
        naryTree.printLevel(3);
        naryTree.printLeaves();

        naryTree.leftSideView();
        naryTree.rightSideView();

        System.out.println("naryTree.numberOfNodes(): " + naryTree.numberOfNodes());
        System.out.println("naryTree.isSimilar(naryTree): " + naryTree.isSimilar(naryTree));
        System.out.println("naryTree.isSimilar(naryTree): " + naryTree.isCopy(naryTree));
        System.out.println("naryTree.height(): " + naryTree.height());
        System.out.println("naryTree.searchRec(4) " + naryTree.searchRec(4));
        System.out.println("naryTree.searchRec(9) " + naryTree.searchRec(9));

        System.out.println(naryTree);
        System.out.println(naryTree.diameter());
    }
}

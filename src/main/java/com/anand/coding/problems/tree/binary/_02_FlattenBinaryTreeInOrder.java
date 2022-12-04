package com.anand.coding.problems.tree.binary;

import java.util.Stack;

/**
 * Given the root of a binary tree, flatten the tree (InOrder) into a "linked list" with the Node right as LinkedList next.
 *
 * leetcode.com/problems/increasing-order-search-tree
 */
public class _02_FlattenBinaryTreeInOrder {

    public Node flattenInOrder(Node root) {
        Node r = new Node();
        Node idx = r;

        Stack<Node> stack = new Stack<>();
        for(Node node = root; node!= null; node=node.left){
            stack.push(node);
        }

        while(!stack.isEmpty()){
            Node inOrderNode = stack.pop();
            for(Node node=inOrderNode.right; node!= null; node = node.left){
                stack.push(node);
            }
            inOrderNode.left=inOrderNode.right=null;
            idx = idx.right = inOrderNode;
        }

        return r.right;
    }

   public static class Node{
        int val;
        Node left,right;
   }

}

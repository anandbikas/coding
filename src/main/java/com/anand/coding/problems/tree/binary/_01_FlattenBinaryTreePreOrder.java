package com.anand.coding.problems.tree.binary;

import java.util.Stack;

/**
 * Given the root of a binary tree, flatten the tree (PreOrder) into a "linked list" with the Node right as LinkedList next.
 *
 * leetcode.com/problems/flatten-binary-tree-to-linked-list
 */
public class _01_FlattenBinaryTreePreOrder {

    public void flattenPreOrder(Node root) {
        Node r = new Node();
        Node idx = r;

        Stack<Node> stack = new Stack<>();
        if(root!=null){
            stack.push(root);
        }

        while(!stack.isEmpty()){
            Node preOrderNode = stack.pop();
            if(preOrderNode.right !=null) stack.push(preOrderNode.right);
            if(preOrderNode.left  !=null) stack.push(preOrderNode.left);

            preOrderNode.left=preOrderNode.right=null;
            idx = idx.right = preOrderNode;
        }
    }

   public static class Node{
        int val;
        Node left,right;
   }

}

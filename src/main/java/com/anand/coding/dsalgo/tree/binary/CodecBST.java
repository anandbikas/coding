package com.anand.coding.dsalgo.tree.binary;
import java.util.Stack;

/**
 * Serialize/Deserialize BST tree to/from string.
 *
 */
public class CodecBST {

    public static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int x) {
            val = x;
        }
    }

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if(root==null){
            return "";
        }
        Stack<TreeNode> stack = new Stack<TreeNode>(){{push(root);}};

        StringBuilder sb = new StringBuilder();
        while(!stack.isEmpty()){
            TreeNode node = stack.pop();
            sb.append(node.val).append(",");

            if(node.right!=null)stack.push(node.right);
            if(node.left!=null) stack.push(node.left);
        }
        for(int i=sb.length()-1; sb.charAt(i)==',' || sb.charAt(i)=='x'; i--){
            sb.setLength(i);
        }
        return sb.toString();
    }

    // Decodes encoded data to tree.
    public TreeNode deserialize(String data) {

        if(data==null || data.length()==0){
            return null;
        }
        System.out.println(data);
        String [] list = data.split(",");

        TreeNode root=new TreeNode(Integer.parseInt(list[0]));

        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);

        for(int i=1; i<list.length; i++){
            TreeNode newNode = new TreeNode(Integer.parseInt(list[i]));

            if (newNode.val < stack.peek().val) {
                stack.peek().left = newNode;
            } else {
                TreeNode node=stack.pop();
                for (;!stack.isEmpty() && newNode.val > stack.peek().val; node = stack.pop());
                node.right = newNode;
            }

            stack.push(newNode);
        }
        return root;
    }

    public static void main(String []args){
        CodecBST codec = new CodecBST();
        TreeNode root = codec.deserialize("2,1,4,3,5");

        System.out.println(codec.serialize(root));
    }
}
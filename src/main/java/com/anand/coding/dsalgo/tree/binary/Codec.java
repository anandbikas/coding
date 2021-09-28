package com.anand.coding.dsalgo.tree.binary;
import java.util.*;

/**
 * Serialize/Deserialize binary tree to/from string.
 *
 * Input: root = [1,2,3,null,null,4,5]
 * Output: [1,2,3,null,null,4,5]
 */
public class Codec {

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
        Queue<TreeNode> queue = new LinkedList<TreeNode>(){{add(root);}};

        StringBuilder sb = new StringBuilder();
        while(!queue.isEmpty()){
            TreeNode node = queue.remove();

            sb.append(node==null? "x," : node.val + ",");

            if(node!=null){
                queue.add(node.left);
                queue.add(node.right);
            }
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

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        int i=1;
        while (i<list.length){
            TreeNode node = queue.remove();

            if(list[i].charAt(0)=='x') node.left = null;
            else queue.add(node.left = new TreeNode(Integer.parseInt(list[i])));
            i++;
            if(i==list.length) break;

            if(list[i].charAt(0)=='x') node.right = null;
            else queue.add(node.right = new TreeNode(Integer.parseInt(list[i])));
            i++;
        }
        return root;
    }

    public static void main(String []args){
        Codec codec = new Codec();
        TreeNode root = codec.deserialize("1,2,3,x,x,4,5");

        System.out.println(codec.serialize(root));
    }
}
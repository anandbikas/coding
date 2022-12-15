package com.anand.coding.dsalgo.tree.binary;
import java.util.*;
import java.util.function.Function;

/**
 * Serialize/Deserialize binary tree to/from string.
 *
 * Input: root = [1,2,3,null,null,4,5]
 * Output: [1,2,3,null,null,4,5]
 */
public class Codec {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if(root==null) {
            return "";
        }

        Queue<TreeNode> q = new LinkedList<TreeNode>(){{ add(root); }};
        StringBuilder sb = new StringBuilder();

        while(!q.isEmpty()) {
            TreeNode node = q.remove();
            sb.append(node==null? "x," : node.val + ",");

            if(node!=null){
                q.add(node.left);
                q.add(node.right);
            }
        }

        int i=sb.length()-1; for(; sb.charAt(i)==',' || sb.charAt(i)=='x'; i--);
        sb.setLength(i+1);

        return sb.toString();
    }

    // Decodes encoded data to tree.
    public TreeNode deserialize(String data) {
        if(data==null || data.length()==0) {
            return null;
        }

        String [] list = data.split(",");

        TreeNode root=new TreeNode(Integer.parseInt(list[0]));
        Queue<TreeNode> q = new LinkedList<TreeNode>(){{ add(root); }};

        for(int i=1; i<list.length; i++) {
            TreeNode node = q.remove();

            if((node.left = nodeFor.apply(list[i++])) !=null ) q.add(node.left);
            if(i==list.length) break;
            if((node.right = nodeFor.apply(list[i])) !=null ) q.add(node.right);
        }
        return root;
    }

    Function<String, TreeNode> nodeFor = s -> s.charAt(0)=='x' ? null : new TreeNode(Integer.parseInt(s));

    public static void main(String []args) {
        Codec codec = new Codec();
        TreeNode root = codec.deserialize("1,2,3,x,x,4,5");

        System.out.println(codec.serialize(root));
    }

    public static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int x) { val = x; }
    }
}
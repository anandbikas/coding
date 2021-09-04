package com.anand.coding.problems.tree.dp;

import java.util.*;

/**
 * Given integers from 1 to n, find all the unique BSTs.
 *
 */
public class _02_UniqueBSTs {

    public static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val; this.left = left; this.right = right;
        }
    }

    public static List<TreeNode> uniqueBST(int n) {

        List<TreeNode> [][]DP = new List[n+2][n+2];
        for(int i=0; i<=n+1; i++){
            for(int j=0; j<=n+1; j++){
                DP[i][j] = new ArrayList<>();
            }
        }

        for(int i=1; i<=n; i++){
            DP[i][i].add(new TreeNode(i));
        }

        for(int k=2; k<=n; k++){
            for(int start=1; start<=n-k+1; start++) {
                for (int i=start; i<start+k; i++) {

                    List<TreeNode> leftTrees = DP[start][i-1];
                    List<TreeNode> rightTrees = DP[i+1][start+k-1];

                    if(leftTrees.isEmpty()){
                        for(TreeNode rightTree: rightTrees) {
                            DP[start][start+k-1].add(new TreeNode(i, null, rightTree));
                        }
                    } else if(rightTrees.isEmpty()) {
                        for(TreeNode leftTree: leftTrees) {
                            DP[start][start+k-1].add(new TreeNode(i, leftTree, null));
                        }
                    } else {
                        //Multiply left and right for this node tree.
                        for(TreeNode leftTree: leftTrees) {
                            for (TreeNode rightTree : rightTrees) {
                                DP[start][start + k - 1].add(new TreeNode(i, leftTree, rightTree));
                            }
                        }
                    }
                }
            }
        }

        return DP[1][n];
    }

    public static List<Integer> levelOrderTraversal(TreeNode node){

        List<Integer> list = new ArrayList<>();
        Queue<TreeNode> queue = new ArrayDeque<>(Arrays.asList(node));

        while(!queue.isEmpty()){
            node = queue.remove();
            list.add(node.val==0 ? null : node.val);

            if(node.left!=null){
                queue.add(node.left);
            }
            if(node.right!=null){
                queue.add(node.right);
            }
        }
        return list;

    }

    /**
     * @param args
     */
    public static void main(String[] args) {

        List<TreeNode> bstS = uniqueBST(5);
        System.out.println(bstS.size());
        bstS.forEach( tree -> System.out.println(levelOrderTraversal(tree)));
    }
}

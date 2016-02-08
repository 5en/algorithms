// https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/

package com.htyleo.algorithms;

public class LowestCommonAncestorOfABinaryTree {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        return helper(root, p, q).lca;
    }

    private Result helper(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return new Result();
        }

        Result lr = helper(root.left, p, q);
        if (lr.lca != null) {
            return lr;
        }

        Result rr = helper(root.right, p, q);
        if (rr.lca != null) {
            return rr;
        }

        Result result = new Result();
        boolean containsP = lr.containsP || rr.containsP || root == p;
        boolean containsQ = lr.containsQ || rr.containsQ || root == q;
        if (containsP && containsQ) {
            result.lca = root;
        }
        result.containsP = containsP;
        result.containsQ = containsQ;

        return result;
    }

    private static class Result {
        TreeNode lca;
        boolean  containsP;
        boolean  containsQ;
    }

    private static class TreeNode {
        int      val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}

// Find if a given binary tree has duplicate sub trees or not.
// (Two leaf nodes of a parent do not count as subtree)

package com.htyleo.algorithms;

import java.util.HashSet;
import java.util.Set;

public class IdenticalSubtrees {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);

        root.left = new TreeNode(2);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(4);

        root.right = new TreeNode(2);
        root.right.left = new TreeNode(3);
        root.right.right = new TreeNode(2);
        root.right.right.left = new TreeNode(3);
        root.right.right.right = new TreeNode(4);

        System.out.println(identicalSubtrees(root));
    }

    public static boolean identicalSubtrees(TreeNode root) {
        if (root == null) {
            return false;
        }

        Result result = identicalSubtreesSR(new HashSet<String>(), root);

        return result.found;
    }

    private static Result identicalSubtreesSR(Set<String> treeHashes, TreeNode root) {
        Result result = new Result();

        if (root == null) {
            return result;
        }

        Result leftResult = identicalSubtreesSR(treeHashes, root.left);
        Result rightResult = identicalSubtreesSR(treeHashes, root.right);

        result.found = leftResult.found || rightResult.found;
        if (result.found) {
            return result;
        }

        result.treeHash = String.valueOf(root.val);
        if (leftResult.treeHash != null) {
            result.treeHash = leftResult.treeHash + result.treeHash;
        }
        if (rightResult.treeHash != null) {
            result.treeHash = result.treeHash + rightResult.treeHash;
        }

        // leaves are not considered as subtrees
        if (root.left != null && root.right != null) {
            if (treeHashes.contains(result.treeHash)) {
                result.found = true;
            }
            treeHashes.add(result.treeHash);
        }

        return result;
    }

    private static class Result {
        // where identical subtrees has been found
        boolean found = false;

        // for null node, treeHash == null
        String  treeHash;
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

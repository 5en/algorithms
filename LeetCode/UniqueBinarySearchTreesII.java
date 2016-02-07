// https://leetcode.com/problems/unique-binary-search-trees-ii/

package com.htyleo.algorithms;

import java.util.ArrayList;
import java.util.List;

public class UniqueBinarySearchTreesII {
    public List<TreeNode> generateTrees(int n) {
        if (n < 1) {
            return new ArrayList<TreeNode>();
        }

        return generateTreesSR(1, n);
    }

    // results can be cached
    private List<TreeNode> generateTreesSR(int min, int max) {
        List<TreeNode> result = new ArrayList<TreeNode>();

        if (min > max) {
            result.add(null);
            return result;
        }

        for (int numRoot = min; numRoot <= max; numRoot++) {
            for (TreeNode leftSub : generateTreesSR(min, numRoot - 1)) {
                for (TreeNode rightSub : generateTreesSR(numRoot + 1, max)) {
                    TreeNode root = new TreeNode(numRoot);
                    root.left = leftSub;
                    root.right = rightSub;
                    result.add(root);
                }
            }
        }

        return result;
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

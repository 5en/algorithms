// https://leetcode.com/problems/kth-smallest-element-in-a-bst/

package com.htyleo.algorithms;

public class KthSmallestElementInBST {
    // in-order traversal
    public int kthSmallest(TreeNode root, int k) {
        Deque<TreeNode> stack = new ArrayDeque<TreeNode>();
        stack.push(root);
        TreeNode node = root;
        int count = 0;
        while (!stack.isEmpty()) {
            while (node != null) {
                stack.push(node);
                node = node.left;
            }
            
            node = stack.pop();
            count++;
            if (count == k) {
                return node.val;
            }
            
            node = node.right;
        }
        
        return -1;
    }

    public int kthSmallest2(TreeNode root, int k) {
        return kthSmallestSR(root, k).result;
    }

    // worst-case O(N) time, O(1) space
    private SearchResult kthSmallestSR(TreeNode root, int k) {
        if (root == null) {
            return new SearchResult(false, 0, 0);
        }

        SearchResult rLeft = kthSmallestSR(root.left, k);
        if (rLeft.found) {
            return rLeft;
        }

        if (rLeft.count == k - 1) {
            return new SearchResult(true, root.val, 0);
        }

        SearchResult rRight = kthSmallestSR(root.right, k - rLeft.count - 1);
        if (rRight.found) {
            return rRight;
        }

        return new SearchResult(false, 0, rLeft.count + 1 + rRight.count);
    }

    private static class SearchResult {
        public final boolean found;
        public final int     result;
        public final int     count; // # of nodes in this subtree. Meaningful only if !found

        public SearchResult(boolean found, int result, int count) {
            this.found = found;
            this.result = result;
            this.count = count;
        }
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

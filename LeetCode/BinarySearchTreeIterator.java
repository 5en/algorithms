// https://leetcode.com/problems/binary-search-tree-iterator/

import java.util.Deque;
import java.util.LinkedList;

public class BinarySearchTreeIterator {
    private Deque<TreeNode> stack = new LinkedList<TreeNode>();

    public BinarySearchTreeIterator(TreeNode root) {
        TreeNode curr = root;
        while (curr != null) {
            stack.push(curr);
            curr = curr.left;
        }
    }

    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        return !stack.isEmpty();
    }

    /** @return the next smallest number */
    public int next() {
        TreeNode node = stack.remove();
        TreeNode curr = node.right;
        while (curr != null) {
            stack.push(curr);
            curr = curr.left;
        }

        return node.val;
    }

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}

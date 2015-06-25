// https://leetcode.com/problems/count-complete-tree-nodes/

public class CountCompleteTreeNodes {
    public static int countNodes(TreeNode root) {
        int h = level(root);

        if (h == -1) { return 0; }
        if (h == 0) { return 1; }

        return (int)Math.pow(2, h) - 1 + numLastLevel(root, h);
    }

    private static int level(TreeNode root) {
        int result = -1;
        while (root != null) {
            result++;
            root = root.left;
        }

        return result;
    }

    private static int numLastLevel(TreeNode root, int h) {
        if (h == 1) {
            return root.right == null ? 1 : 2;
        }

        TreeNode minBiggerRoot = root.right;
        for (int i = 1; i <= h-1; i++) {
            minBiggerRoot = minBiggerRoot.left;
        }

        if (minBiggerRoot == null) {
            return numLastLevel(root.left, h-1);
        } else {
            return (int)Math.pow(2, h-1) + numLastLevel(root.right, h-1);
        }
    }

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}

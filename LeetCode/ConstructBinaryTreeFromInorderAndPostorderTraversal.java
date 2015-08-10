// https://leetcode.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal/

public class ConstructBinaryTreeFromInorderAndPostorderTraversal {
    public static TreeNode buildTree(int[] inorder, int[] postorder) {
        return buildTreeSR(inorder, 0, inorder.length-1, postorder, 0, postorder.length-1);
    }

    private static TreeNode buildTreeSR(int[] inorder, int inLow, int inHigh, int[] postorder, int postLow, int postHigh) {
        if (postLow > postHigh) {
            return null;
        }

        TreeNode root = new TreeNode(postorder[postHigh]);
        if (postLow == postHigh) {
            return root;
        }

        int in = inLow;
        for (; in <= inHigh; in++) {
            if (inorder[in] == root.val) {
                root.left = buildTreeSR(inorder, inLow, in-1, postorder, postLow, postLow+(in-1-inLow));
                root.right = buildTreeSR(inorder, in+1, inHigh, postorder, postLow+(in-1-inLow)+1, postHigh-1);
                break;
            }
        }

        return root;
    }

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}

// https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/

public class ConstructBinaryTreeFromPreorderAndInorderTraversal {
    public static TreeNode buildTree(int[] preorder, int[] inorder) {
        return buildTreeSR(preorder, 0, preorder.length-1, inorder, 0, inorder.length-1);
    }

    private static TreeNode buildTreeSR(int[] preorder, int preLow, int preHigh, int[] inorder, int inLow, int inHigh) {
        if (preLow > preHigh) {
            return null;
        }

        TreeNode root = new TreeNode(preorder[preLow]);
        if (preLow == preHigh) {
            return root;
        }

        int in = inLow;
        for (; in <= inHigh; in++) {
            if (inorder[in] == root.val) {
                root.left = buildTreeSR(preorder, preLow+1, preLow+1+(in-1-inLow), inorder, inLow, in-1);
                root.right = buildTreeSR(preorder, preLow+1+(in-1-inLow)+1, preHigh, inorder, in+1, inHigh);
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

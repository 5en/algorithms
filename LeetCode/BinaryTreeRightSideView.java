// https://leetcode.com/problems/binary-tree-right-side-view/

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BinaryTreeRightSideView {
    public static List<Integer> rightSideView(TreeNode root) {
        if (root == null) {
            return new LinkedList<Integer>();
        }

        List<Integer> result = new LinkedList<Integer>();

        Queue<TreeNode> main = new LinkedList<TreeNode>();
        Queue<TreeNode> aux = new LinkedList<TreeNode>();
        main.add(root);
        while (!main.isEmpty()) {
            result.add(main.peek().val); // right-most node on this level
            while (!main.isEmpty()) {
                TreeNode node = main.remove();
                if (node.right != null) {
                    aux.add(node.right);
                }
                if (node.left != null) {
                    aux.add(node.left);
                }
            }

            // swap main and aux
            Queue<TreeNode> tmp = main;
            main = aux;
            aux = tmp;
        }

        return result;
    }

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}

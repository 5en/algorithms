// Print all the root-to-leaf path

import java.util.ArrayList;
import java.util.List;

public class AllRootToLeafPath {
    public static void main(String[] args) {
        //          1
        //      2       3
        //   4    5
        //
        // 1 2 4
        // 1 2 5
        // 1 3

        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);

        for (StringBuilder sb : allPaths(root)) {
            System.out.println(sb.toString());
        }
    }

    public static List<StringBuilder> allPaths(TreeNode root) {
        if (root == null) {
            return new ArrayList<StringBuilder>();
        }

        List<StringBuilder> result = new ArrayList<StringBuilder>();
        result.addAll(allPaths(root.left));
        result.addAll(allPaths(root.right));
        for (StringBuilder sb : result) {
            sb.insert(0, root.val + " ");
        }
        if (result.isEmpty()) { // leaf node
            result.add(new StringBuilder().append(root.val));
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

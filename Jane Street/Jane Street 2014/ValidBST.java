// https://oj.leetcode.com/problems/validate-binary-search-tree/

public class ValidBST {
    public static void main(String[] args) {
        //          10
        //      7       12
        //    5   8         15
        Node<Integer> root = new Node<Integer>(10);
        root.left = new Node<Integer>(7);
        root.right = new Node<Integer>(12);
        root.left.left = new Node<Integer>(5);
        root.left.right = new Node<Integer>(8);
        root.right.right = new Node<Integer>(15);
        System.out.println(valid(root));

        //          10
        //      7       9
        //    5   8         15
        Node<Integer> root2 = new Node<Integer>(10);
        root2.left = new Node<Integer>(7);
        root2.right = new Node<Integer>(9);
        root2.left.left = new Node<Integer>(5);
        root2.left.right = new Node<Integer>(8);
        root2.right.right = new Node<Integer>(15);
        System.out.println(valid(root2));
    }

    public static boolean valid(Node<Integer> root) {
        if (root == null) {
            return true;
        }

        return validSR(root).valid;
    }

    private static SubTreeInfo validSR(Node<Integer> node) {
        // node must not be null

        int min = node.key;
        int max = node.key;

        if (node.left != null) {
            SubTreeInfo info = validSR(node.left);
            if (!info.valid || info.max >= node.key) {
                return new SubTreeInfo(false, 0, 0);
            }
            min = info.min;
        }

        if (node.right != null) {
            SubTreeInfo info = validSR(node.right);
            if (!info.valid || info.min <= node.key) {
                return new SubTreeInfo(false, 0, 0);
            }
            max = info.max;
        }

        return new SubTreeInfo(true, min, max);
    }

    private static class SubTreeInfo {
        public final boolean valid;
        public final int min;
        public final int max;

        public SubTreeInfo(boolean valid, int min, int max) {
            this.valid = valid;
            this.min = min;
            this.max = max;
        }
    }
}

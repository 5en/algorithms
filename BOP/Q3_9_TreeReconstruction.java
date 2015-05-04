import java.util.Arrays;
import java.util.List;

public class Q3_9_TreeReconstruction {
    public static void main(String[] args) {
        //          a
        //      b       c
        //    d        e f
        String[] preorder = new String[]{"a", "b", "d", "c", "e", "f"};
        String[] inorder = new String[]{"d", "b", "a", "e", "c", "f"};

        Node root = run(preorder, inorder);

        System.out.println(root.v);
        System.out.println(root.left.v);
        System.out.println(root.right.v);
        System.out.println(root.left.left.v);
        System.out.println(root.right.left.v);
        System.out.println(root.right.right.v);
    }

    public static Node run(String[] preorder, String[] inorder) {
        List<String> pre = Arrays.asList(preorder);
        List<String> in = Arrays.asList(inorder);

        return reconstruct(pre, in);
    }

    public static Node reconstruct(List<String> pre, List<String> in) {
        if (pre.size() == 1) {
            return new Node(pre.get(0));
        }

        String rootV = pre.get(0);
        Node root = new Node(rootV);

        int rootInPos = in.indexOf(rootV);
        int numLeftNodes = rootInPos;
        int numRightNodes = pre.size() - rootInPos - 1;

        if (numLeftNodes == 0) {
            root.left = null;
        } else {
            root.left = reconstruct(pre.subList(1, numLeftNodes + 1), in.subList(0, rootInPos));
        }

        if (numRightNodes == 0) {
            root.right = null;
        } else {
            root.right = reconstruct(pre.subList(numLeftNodes + 1, numLeftNodes + 1 + numRightNodes), in.subList(rootInPos + 1, in.size()));
        }

        return root;
    }

    private static class Node {
        public final String v;
        public Node left = null;
        public Node right = null;

        public Node(String v) {
            this.v = v;
        }
    }
}

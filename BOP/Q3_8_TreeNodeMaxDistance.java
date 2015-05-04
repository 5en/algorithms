public class Q3_8_TreeNodeMaxDistance {
    public static void main(String[] args) {
        /*
         *                  [1]
         *           [2]          [3]
         *       [4]     5    [6]     7          
         *    [8]                [9]
         */
        Node root = new Node(1);

        root.left = new Node(2);
        root.right = new Node(3);

        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.right.left = new Node(6);
        root.right.right = new Node(7);

        root.left.left.left = new Node(8);
        root.right.left.right = new Node(9);

        System.out.println(maxDist(root));
    }

    public static int maxDist(Node root) {
        if (root == null) {
            return -1;
        }

        int leftMaxDist = -1;
        int rightMaxDist = -1;
        int leftHeight = -1;
        int rightHeight = -1;
        
        if (root.left != null) {
            leftMaxDist = maxDist(root.left);
            leftHeight = root.left.height;
        }

        if (root.right != null) {
            rightMaxDist = maxDist(root.right);
            rightHeight = root.right.height;
        }

        root.height = max(leftHeight, rightHeight) + 1;
        return max(leftMaxDist, rightMaxDist, leftHeight + rightHeight + 2);
    }

    private static int max(int... a) {
        int max = Integer.MIN_VALUE;
        for (int x : a) {
            if (x > max) {
                max = x;
            }
        }

        return max;
    }
    
    private static class Node {
        public int key;
        public Node left = null;
        public Node right = null;
        public int height = 0;

        public Node(int key) {
            this.key = key;
        }
    }
}

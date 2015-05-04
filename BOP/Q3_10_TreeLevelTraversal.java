import java.util.LinkedList;
import java.util.List;

public class Q3_10_TreeLevelTraversal {
    public static void main(String[] args) {
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.right.right = new Node(6);
        root.left.right.left = new Node(7);
        root.left.right.right = new Node(8);
        root.left.right.right.left = new Node(9);
        
        List<List<Node>> levelInfo = calcLevelInfo(root);
        printLevelInfo(levelInfo);
    }
    
    public static void printLevelInfo(List<List<Node>> levelInfo) {
        for (int level=0; level<levelInfo.size(); level++) {
            System.out.print("Level " + level + ":");
            
            for (Node node : levelInfo.get(level)) {
                System.out.print(" " + node.v);
            }
            
            System.out.println();
        }
    }
    
    public static List<List<Node>> calcLevelInfo(Node root) {
        List<List<Node>> info = new LinkedList<List<Node>>();
        
        if (root == null) {
            return info;
        }
        
        int level = 0;
        info.add(new LinkedList<Node>());
        info.get(0).add(root);
        
        while (true) {
            info.add(new LinkedList<Node>());
            level++;
            
            for (Node node : info.get(level-1)) {
                if (node.left != null) {
                    info.get(level).add(node.left);
                }
                if (node.right != null) {
                    info.get(level).add(node.right);
                }
            }
            
            if (info.get(level).isEmpty()) {
                info.remove(level);
                break;
            }
        }
        
        return info;
    }
    
    private static class Node {
        public int v = 0;
        public Node left = null;
        public Node right = null;
        
        public Node(int v) {
            this.v = v;
        }
    }
}

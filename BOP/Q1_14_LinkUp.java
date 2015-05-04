import java.util.Queue;
import java.util.LinkedList;

public class Q1_14_LinkUp {
    public static void main(String[] args) {
        // . . A . .
        // C . B . C
        // . . A . .
        // . B . . .
        Node[][] matrix = new Node[4][5];
        for (int x = 0; x < matrix.length; x++) {
            for (int y = 0; y < matrix[0].length; y++) {
                matrix[x][y] = new Node("NULL", x, y);
            }
        }
        matrix[0][2].name = "A";
        matrix[1][1].name = "C";
        matrix[1][2].name = "B";
        matrix[1][4].name = "C";
        matrix[2][2].name = "A";
        matrix[3][1].name = "B";

        Problem p = new Problem(matrix);
        p.link(0, 2, 2, 2); // A
        p.link(1, 2, 3, 1); // B
        p.link(1, 1, 1, 4); // C
    }

    private static class Problem {
        private Node[][] matrix;
        
        public Problem(Node[][] matrix) {
            this.matrix = matrix;
        }
        
        // have checked matrix[x1][y1].name == matrix[x2][y2].name
        public void link(int x1, int y1, int x2, int y2) {
            int lenX = matrix.length;
            int lenY = matrix[0].length;

            Node start = matrix[x1][y1];
            Node end = matrix[x2][y2];

            boolean FLAG_FOUND = false;
            
            // init
            start.minCross = -1;
            start.minDist = 0;
            start.prev = null;
            Queue<Node> queue = new LinkedList<Node>();
            queue.add(start);
            
            // at most two crosses
            for (int i = 0; i < 3; i++) {
                Queue<Node> tmpQueue = new LinkedList<Node>();

                while (!queue.isEmpty()) {
                    Node from = queue.remove();
                    int fromX = from.x;
                    int fromY = from.y;

                    // find nodes on four directions
                    // <-
                    for (int x = fromX - 1; x >= 0; x--) {
                        Node to = matrix[x][fromY];
                        
                        if (!to.name.equals(end.name) && !to.name.equals("NULL")) {
                            // hit a block
                            break;
                        }
                        
                        if (to.x == end.x && to.y == end.y) {
                            FLAG_FOUND = true;
                        }
                        
                        updateNode(to, from, 1, Math.abs(fromX - x));
                        
                        if (to.minCross == i) {
                            // newly found
                            tmpQueue.add(to);
                        }
                    }
                    // ->
                    for (int x = fromX + 1; x < lenX; x++) {
                        Node to = matrix[x][fromY];
                        
                        if (!to.name.equals(end.name) && !to.name.equals("NULL")) {
                            // hit a block
                            break;
                        }
                        
                        if (to.x == end.x && to.y == end.y) {
                            FLAG_FOUND = true;
                        }
                        
                        updateNode(to, from, 1, Math.abs(x - fromX));
                        
                        if (to.minCross == i) {
                            // newly found
                            tmpQueue.add(to);
                        }
                    }
                    // up
                    for (int y = fromY - 1; y >= 0; y--) {
                        Node to = matrix[fromX][y];
                        
                        if (!to.name.equals(end.name) && !to.name.equals("NULL")) {
                            // hit a block
                            break;
                        }
                        
                        if (to.x == end.x && to.y == end.y) {
                            FLAG_FOUND = true;
                        }
                        
                        updateNode(to, from, 1, Math.abs(fromY - y));
                        
                        if (to.minCross == i) {
                            // newly found
                            tmpQueue.add(to);
                        }
                    }
                    // down
                    for (int y = fromY + 1; y < lenY; y++) {
                        Node to = matrix[fromX][y];
                        
                        if (!to.name.equals(end.name) && !to.name.equals("NULL")) {
                            // hit a block
                            break;
                        }
                        
                        if (to.x == end.x && to.y == end.y) {
                            FLAG_FOUND = true;
                        }
                        
                        updateNode(to, from, 1, Math.abs(y - fromY));
                        
                        if (to.minCross == i) {
                            // newly found
                            tmpQueue.add(to);
                        }
                    }
                }

                if (FLAG_FOUND) {
                    break;
                } else {
                    queue = tmpQueue;
                }
            }

            System.out.println(end.name);
            if (FLAG_FOUND) {
                StringBuilder sb = new StringBuilder();
                Node node = end;
                while (node != null) {
                    sb.append("(").append(node.x).append(", ").append(node.y).append(") ");
                    node = node.prev;
                }
                sb.delete(sb.length() - 1, sb.length()).append("\n");
                System.out.println(sb.toString());
            } else {
                System.out.println("Can not!\n");
            }
        }
    
        // from -> to, from and to must be on a straight line
        private void updateNode(Node to, Node from, int incCross, int incDist) {
            if (from.minCross + incCross < to.minCross || (from.minCross + incCross == to.minCross && from.minDist + incDist < to.minDist)) {
                to.minCross = from.minCross + incCross;
                to.minDist = from.minDist + incDist;

                if (to.x == from.x) {
                    if (to.y > from.y) {
                        to.prev = matrix[to.x][to.y - 1];
                    } else if (to.y < from.y) {
                        to.prev = matrix[to.x][to.y + 1];
                    }
                } else if (to.y == from.y) {
                    if (to.x > from.x) {
                        to.prev = matrix[to.x - 1][to.y];
                    } else {
                        to.prev = matrix[to.x + 1][to.y];
                    }
                }
            }
        }
    }
    
    private static class Node {
        public final int x;
        public final int y;
        public String name;
        public int minCross = Integer.MAX_VALUE;
        public int minDist = Integer.MAX_VALUE;
        public Node prev = null;

        public Node(String name, int x, int y) {
            this.name = name;
            this.x = x;
            this.y = y;
        }
    }
}

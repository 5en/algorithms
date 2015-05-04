package tree;

import java.util.List;
import java.util.LinkedList;

public class FindNumNodes {
    public static void main(String[] args) {
        //            3
        //       [3]        4
        //    [3]   [3]    [3]
        Node root = new Node(3);
        root.c.add(new Node(3));
        root.c.get(0).c.add(new Node(3));
        root.c.get(0).c.add(new Node(3));
        root.c.add(new Node(4));
        root.c.get(1).c.add(new Node(3));
        System.out.println(find(root));
    }

    // calculate the number of nodes whose value is the same as all of its descendants
    public static int find(Node node) {
        return findSR(node).count;
    }

    private static Result findSR(Node node) {
        if (node == null) {
            return new Result(true, 0);
        }

        int count = 0;
        boolean same = true;
        for (Node child : node.c) {
            Result r = findSR(child);
            if (!r.same || (child != null && child.x != node.x)) {
                same = false;
            }
            count += r.count;
        }

        if (same) {
            return new Result(true, count + 1);
        } else {
            return new Result(false, count);
        }
    }

    private static class Node {
        public final int x;
        public final List<Node> c = new LinkedList<Node>();

        public Node(int x) {
            this.x = x;
        }
    }

    private static class Result {
        public final boolean same; // whether all the nodes in the subtree have the same value
        public final int count; // # of nodes in the subtree whose value is the same as all of its descendants

        public Result(boolean same, int count) {
            this.same = same;
            this.count = count;
        }
    }
}

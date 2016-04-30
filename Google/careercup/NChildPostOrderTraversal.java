package com.htyleo.algorithms;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class NChildPostOrderTraversal {

    public static void main(String[] args) {
        Node root = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(5);
        Node node6 = new Node(6);
        Node node7 = new Node(7);
        Node node8 = new Node(8);
        Node node9 = new Node(9);
        Node node10 = new Node(10);

        root.childs.add(node2);
        root.childs.add(node3);
        root.childs.add(node4);

        node2.childs.add(node5);
        node2.childs.add(node6);
        node2.childs.add(node7);

        node4.childs.add(node8);
        node4.childs.add(node9);
        node4.childs.add(node10);

        System.out.println(postOrder(root));
    }

    //              1
    //       2      3     4
    //     5 6 7        8 9 10
    public static List<Integer> postOrder(Node root) {
        List<Integer> result = new LinkedList<Integer>();

        Deque<Node> stack = new ArrayDeque<Node>();
        stack.push(root);
        while (!stack.isEmpty()) {
            Node node = stack.pop();
            result.add(0, node.val);

            for (Node child : node.childs) {
                stack.push(child);
            }
        }

        return result;
    }

    private static class Node {
        int        val;
        List<Node> childs = new ArrayList<Node>();

        public Node(int val) {
            this.val = val;
        }
    }
}

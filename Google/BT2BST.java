// Given a Binary Tree, convert it to a Binary Search Tree.
// The conversion must be done in such a way that keeps the original structure of Binary Tree.

//        Example 1
//        Input:
//         10
//        /  \
//        2    7
//       / \
//      8   4
//
//        Output:
//          8
//        /  \
//       4    10
//      / \
//      2   7

package com.htyleo.algorithms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BT2BST {
    public static void main(String[] args) {
        Node root = new Node(10);
        root.left = new Node(2);
        root.left.left = new Node(8);
        root.left.right = new Node(4);
        root.right = new Node(7);

        bt2bst(root);

        System.out.println(root.val);
        System.out.println(root.left.val + " " + root.right.val);
        System.out.println(root.left.left.val + " " + root.left.right.val);
    }

    // 1. traverse the node values
    // 2. sort vals
    // 3. pre-order traversal, assign values
    public static void bt2bst(Node root) {
        List<Integer> vals = new ArrayList<Integer>();

        // 1. traverse the node values
        Queue<Node> q = new LinkedList<Node>();
        q.add(root);
        while (!q.isEmpty()) {
            Queue<Node> auxQ = new LinkedList<Node>();
            while (!q.isEmpty()) {
                Node node = q.remove();
                vals.add(node.val);
                if (node.left != null) {
                    auxQ.add(node.left);
                }
                if (node.right != null) {
                    auxQ.add(node.right);
                }
            }
            q = auxQ;
        }

        // 2. sort vals
        Collections.sort(vals);

        // 3. pre-order traversal, assign values
        assignVal(root, vals);
    }

    private static void assignVal(Node root, List<Integer> vals) {
        if (root == null) {
            return;
        }

        assignVal(root.left, vals);
        root.val = vals.remove(0);
        assignVal(root.right, vals);
    }

    private static class Node {
        int  val;
        Node left;
        Node right;

        public Node(int val) {
            this.val = val;
        }
    }
}

// Given a non-empty binary search tree and a target value, find the value in the BST that is closest to the target.

package com.htyleo.algorithms;

public class BinaryTreeCloestNode {

    public static void main(String[] args) {
        //      5
        //  3       9
        //        6     10
        Node root = new Node(5);
        root.left = new Node(3);
        root.right = new Node(9);
        root.right.left = new Node(6);
        root.right.right = new Node(10);

        System.out.println(find(root, 7).val);
        System.out.println(find(root, 9).val);
        System.out.println(find(root, 18).val);
    }

    public static Node find(Node root, int target) {
        if (root == null) {
            return null;
        }

        Node candidate = null;
        if (root.val < target) {
            candidate = find(root.right, target);
        } else if (root.val > target) {
            candidate = find(root.left, target);
        } else {
            return root;
        }

        if (candidate == null || Math.abs(root.val - target) <= Math.abs(candidate.val - target)) {
            return root;
        } else {
            return candidate;
        }
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

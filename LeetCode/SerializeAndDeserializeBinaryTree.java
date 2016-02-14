// https://leetcode.com/problems/serialize-and-deserialize-binary-tree/

package com.htyleo.algorithms;

import java.util.LinkedList;
import java.util.Queue;

public class SerializeAndDeserializeBinaryTree {
    public static void main(String[] args) {
        //          2
        //  null            3
        TreeNode root = new TreeNode(2);
        root.right = new TreeNode(3);

        String data = serialize(root);
        System.out.println(data);
        deserialize(data);
    }

    // Encodes a tree to a single string.
    public static String serialize(TreeNode root) {
        final TreeNode NULL_NODE = new TreeNode(0);

        StringBuilder sb = new StringBuilder();

        Queue<TreeNode> q = new LinkedList<TreeNode>();
        q.add(root == null ? NULL_NODE : root);
        while (!q.isEmpty()) {
            TreeNode node = q.remove();
            if (node == NULL_NODE) {
                sb.append("null,");
            } else {
                sb.append(node.val).append(',');
                q.add(node.left == null ? NULL_NODE : node.left);
                q.add(node.right == null ? NULL_NODE : node.right);
            }
        }

        sb.delete(sb.length() - 1, sb.length());
        return sb.toString();
    }

    // Decodes your encoded data to tree.
    public static TreeNode deserialize(String data) {
        String[] vals = data.split(",");
        TreeNode root = vals[0].equals("null") ? null : new TreeNode(Integer.parseInt(vals[0]));
        if (root == null) {
            return null;
        }

        Queue<TreeNode> q = new LinkedList<TreeNode>();
        q.add(root);
        int i = 1;
        while (!q.isEmpty()) {
            TreeNode node = q.remove();
            node.left = vals[i].equals("null") ? null : new TreeNode(Integer.parseInt(vals[i]));
            i++;
            if (node.left != null) {
                q.add(node.left);
            }
            node.right = vals[i].equals("null") ? null : new TreeNode(Integer.parseInt(vals[i]));
            i++;
            if (node.right != null) {
                q.add(node.right);
            }
        }

        return root;
    }

    private static class TreeNode {
        int      val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val) {
            this.val = val;
        }
    }
}

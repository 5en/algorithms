// https://leetcode.com/problems/delete-node-in-a-bst/#/description

package com.htyleo.algorithms;

/**
 * Created by htyleo on 6/23/17.
 */
public class DeleteNodeInABST {

  public TreeNode deleteNode(TreeNode root, int key) {
    if (root == null) {
      return null;
    }

    if (root.val > key) {
      root.left = deleteNode(root.left, key);
      return root;
    } else if (root.val < key) {
      root.right = deleteNode(root.right, key);
      return root;
    }

    // current node is key

    if (root.left == null) {
      return root.right;
    } else if (root.right == null) {
      return root.left;
    }

    // find largest one in left subtree
    TreeNode parent = null;
    TreeNode curr = root.left;
    while (curr.right != null) {
      parent = curr;
      curr = curr.right;
    }
    if (parent != null) {
      parent.right = curr.left;
    }
    if (curr != root.left) {
      curr.left = root.left;
    }
    curr.right = root.right;
    return curr;
  }

  public class TreeNode {

    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
      val = x;
    }
  }
}

// https://leetcode.com/problems/serialize-and-deserialize-bst/#/description

package com.htyleo.algorithms;

/**
 * Created by htyleo on 6/17/17.
 */
public class SerializeAndDeserializeBST {

  // Encodes a tree to a single string.
  public String serialize(TreeNode root) {
    StringBuilder res = new StringBuilder();
    serializeSub(res, root);
    return res.toString();
  }

  private void serializeSub(StringBuilder res, TreeNode node) {
    if (node == null) {
      return;
    }

    if (res.length() > 0) {
      res.append(' ');
    }
    res.append(node.val);
    serializeSub(res, node.left);
    serializeSub(res, node.right);
  }

  // Decodes your encoded data to tree.
  public TreeNode deserialize(String data) {
    if (data.isEmpty()) {
      return null;
    }

    String[] vals = data.split(" ");
    int[] nums = new int[vals.length];
    for (int i = 0; i < nums.length; i++) {
      nums[i] = Integer.parseInt(vals[i]);
    }
    return deserializeSub(nums, 0, vals.length - 1);
  }

  private TreeNode deserializeSub(int[] nums, int left, int right) {
    if (left > right) {
      return null;
    }

    TreeNode node = new TreeNode(nums[left]);
    for (int i = left + 1; i <= right; i++) {
      if (nums[i] > nums[left]) {
        node.left = deserializeSub(nums, left + 1, i - 1);
        node.right = deserializeSub(nums, i, right);
        break;
      }
    }

    if (nums[right] < nums[left]) {
      node.left = deserializeSub(nums, left + 1, right);
    }

    return node;
  }

  private static class TreeNode {

    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
      val = x;
    }
  }
}

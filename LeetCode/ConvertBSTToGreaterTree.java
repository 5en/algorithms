// https://leetcode.com/problems/convert-bst-to-greater-tree/#/description

package com.htyleo.algorithms;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by htyleo on 6/1/17.
 */
public class ConvertBSTToGreaterTree {

  // BST
  // O(N) time, O(1) space
  public TreeNode convertBST(TreeNode root) {
    reverseInorder(root, 0);
    return root;
  }

  public int reverseInorder(TreeNode node, int inc) {
    if (node == null) {
      return inc;
    }

    inc = reverseInorder(node.right, inc);
    node.val += inc;
    inc = node.val;
    inc = reverseInorder(node.left, inc);

    return inc;
  }

  // general tree
  // O(NlogN) time, O(N) space
  public TreeNode convert(TreeNode root) {
    List<TreeNode> nodes = new ArrayList<>();
    dfs(nodes, root);
    nodes.sort((node1, node2) -> node1.val < node2.val ? -1 : (node1.val == node2.val ? 0 : 1));

    for (int end = nodes.size() - 1; end >= 0; ) {
      // find nums equal to nodes.get(end).val
      // result index: [start + 1, end]
      int start = end - 1;
      while (start >= 0 && nodes.get(start).val == nodes.get(end).val) {
        start--;
      }

      int inc = (end + 1 < nodes.size()) ? nodes.get(end + 1).val : 0;
      for (int i = start + 1; i <= end; i++) {
        nodes.get(i).val += inc;
      }

      end = start;
    }

    return root;
  }

  public void dfs(List<TreeNode> nodes, TreeNode node) {
    if (node == null) {
      return;
    }

    nodes.add(node);
    dfs(nodes, node.left);
    dfs(nodes, node.right);
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

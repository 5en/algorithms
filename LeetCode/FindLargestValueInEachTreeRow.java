// https://leetcode.com/problems/find-largest-value-in-each-tree-row/#/description

package com.htyleo.algorithms;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * Created by htyleo on 5/30/17.
 */
public class FindLargestValueInEachTreeRow {

  public List<Integer> largestValues(TreeNode root) {

    List<Integer> res = new ArrayList<>();
    if (root == null) {
      return res;
    }

    Queue<TreeNode> q = new ArrayDeque<>();
    q.add(root);
    Queue<TreeNode> auxQ = new ArrayDeque<>();
    while (!q.isEmpty()) {

      int opt = Integer.MIN_VALUE;
      while (!q.isEmpty()) {
        TreeNode node = q.remove();
        opt = Math.max(opt, node.val);
        if (node.left != null) {
          auxQ.add(node.left);
        }
        if (node.right != null) {
          auxQ.add(node.right);
        }
      }

      res.add(opt);

      Queue<TreeNode> tmp = q;
      q = auxQ;
      auxQ = tmp;
    }

    return res;
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

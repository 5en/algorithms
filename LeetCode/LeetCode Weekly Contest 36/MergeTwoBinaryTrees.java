// https://leetcode.com/contest/leetcode-weekly-contest-36/problems/merge-two-binary-trees/

package com.htyleo.algorithms;

/**
 * Created by htyleo on 6/11/17.
 */
public class MergeTwoBinaryTrees {

  public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
    if (t1 == null) {
      return t2;
    }
    if (t2 == null) {
      return t1;
    }

    TreeNode node = new TreeNode(t1.val + t2.val);
    node.left = mergeTrees(t1.left, t2.left);
    node.right = mergeTrees(t1.right, t2.right);

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

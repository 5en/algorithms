// https://leetcode.com/problems/find-bottom-left-tree-value/#/description

package com.htyleo.algorithms;

/**
 * Created by htyleo on 5/27/17.
 */
public class FindBottomLeftTreeValue {

  public int findBottomLeftValue(TreeNode root) {
    return find(root, 0).node.val;
  }

  // find result in left and right subtree: lRes, rRes
  // case 1: no left nor right subtree: res = node
  // case 2: no left subtree: res = rRes
  // case 3: no right subtree: res = lRes
  // case 4: both left and right subtree exists:
  //      if (lRes.depth >= rRes.depth) {res = lRes;}
  //      else {res = rRes;}
  public Result find(TreeNode node, int depth) {
    if (node == null) {
      return null;
    }

    Result res = new Result(node, depth);

    Result lRes = find(node.left, depth + 1);
    if (lRes != null) {
      res = lRes;
    }

    Result rRes = find(node.right, depth + 1);
    if (rRes != null && res.depth < rRes.depth) {
      res = rRes;
    }

    return res;
  }

  private static class Result {

    public final TreeNode node;
    public final int depth;

    public Result(TreeNode node, int depth) {
      this.node = node;
      this.depth = depth;
    }
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

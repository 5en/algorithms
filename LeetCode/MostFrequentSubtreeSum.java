// https://leetcode.com/problems/most-frequent-subtree-sum/#/description

package com.htyleo.algorithms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by htyleo on 6/5/17.
 */
public class MostFrequentSubtreeSum {

  public int[] findFrequentTreeSum(TreeNode root) {
    Map<Integer, Integer> sumFreq = new HashMap<>();
    helper(root, sumFreq);

    List<Integer> sums = new ArrayList<>();
    int maxFreq = 0;
    for (int sum : sumFreq.keySet()) {
      int freq = sumFreq.get(sum);
      if (freq > maxFreq) {
        maxFreq = freq;
        sums.clear();
        sums.add(sum);
      } else if (freq == maxFreq) {
        sums.add(sum);
      }
    }

    int[] result = new int[sums.size()];
    for (int i = 0; i < sums.size(); i++) {
      result[i] = sums.get(i);
    }

    return result;
  }

  private int helper(TreeNode node, Map<Integer, Integer> sumFreq) {
    if (node == null) {
      return 0;
    }

    int lsum = helper(node.left, sumFreq);
    int rsum = helper(node.right, sumFreq);
    int sum = lsum + node.val + rsum;
    int freq = sumFreq.compute(sum, (k, v) -> (v == null) ? 1 : v + 1);

    return sum;
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

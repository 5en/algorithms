// https://leetcode.com/problems/house-robber-iii/

package com.htyleo.algorithms;

import java.util.HashMap;
import java.util.Map;

public class HouseRobberIII {

    public int rob(TreeNode root) {
        Map<TreeNode, Integer> pRobbedMemo = new HashMap<>();
        Map<TreeNode, Integer> pUnrobbedMemo = new HashMap<>();
        return Math.max(rob(root, true, pRobbedMemo, pUnrobbedMemo),
            rob(root, false, pRobbedMemo, pUnrobbedMemo));
    }

    public int rob(TreeNode node, boolean pRobbed, Map<TreeNode, Integer> pRobbedMemo,
                   Map<TreeNode, Integer> pUnrobbedMemo) {
        if (node == null) {
            return 0;
        }

        if (pRobbed) {
            // current cannot be robbed
            if (pRobbedMemo.containsKey(node)) {
                return pRobbedMemo.get(node);
            } else {
                int result = rob(node.left, false, pRobbedMemo, pUnrobbedMemo)
                             + rob(node.right, false, pRobbedMemo, pUnrobbedMemo);
                pRobbedMemo.put(node, result);
                return result;
            }
        } else {
            if (pUnrobbedMemo.containsKey(node)) {
                return pUnrobbedMemo.get(node);
            } else {
                // current can be robbed
                // case 1. current is robbed
                // case 2. current is not robbed
                int result1 = node.val + rob(node.left, true, pRobbedMemo, pUnrobbedMemo)
                              + rob(node.right, true, pRobbedMemo, pUnrobbedMemo);
                int result2 = rob(node.left, false, pRobbedMemo, pUnrobbedMemo)
                              + rob(node.right, false, pRobbedMemo, pUnrobbedMemo);
                int result = Math.max(result1, result2);
                pUnrobbedMemo.put(node, result);
                return result;
            }
        }
    }

    private static class TreeNode {
        int      val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}

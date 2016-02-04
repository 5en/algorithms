// https://leetcode.com/problems/convert-sorted-list-to-binary-search-tree/

package com.htyleo.algorithms;

public class ConvertSortedListToBinarySearchTree {
    // T(N) = O(N) + 2T(N/2) = O(NlogN) time, O(1) space
    public TreeNode sortedListToBST(ListNode head) {
        if (head == null) {
            return null;
        }

        // slow-fast traversal to find the middle one
        ListNode prevSlow = head;
        ListNode slow = head;
        ListNode fast = head.next;
        while (fast != null) {
            prevSlow = slow;
            slow = slow.next;
            fast = fast.next == null ? null : fast.next.next;
        }

        if (slow == head) {
            return new TreeNode(head.val);
        }

        prevSlow.next = null;
        TreeNode root = new TreeNode(slow.val);
        root.left = sortedListToBST(head); // head ~ prevSlow
        root.right = sortedListToBST(slow.next); // slow.next ~

        return root;
    }

    private static class ListNode {
        int      val;
        ListNode next;

        ListNode(int x) {
            val = x;
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

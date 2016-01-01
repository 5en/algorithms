// https://leetcode.com/problems/swap-nodes-in-pairs/

package com.htyleo.algorithms;

public class SwapNodesInPairs {
    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        // head -> headNext -> X
        // =>
        // headNext -> head -> swapPairs(X)
        ListNode newHead = head.next;
        head.next = swapPairs(newHead.next);
        newHead.next = head;

        return newHead;
    }

    public class ListNode {
        int      val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}

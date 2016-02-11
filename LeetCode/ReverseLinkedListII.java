// https://leetcode.com/problems/reverse-linked-list-ii/

package com.htyleo.algorithms;

public class ReverseLinkedListII {
    public ListNode reverseBetween(ListNode head, int m, int n) {
        ListNode curr = head;
        for (int i = 1; i <= m - 2; i++) {
            curr = curr.next;
        }

        ListNode mPrev = (m == 1) ? null : curr;
        ListNode mNode = (mPrev == null) ? head : mPrev.next;

        ListNode prev = mPrev;
        curr = mNode;
        for (int i = 1; i <= n - m + 1; i++) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }

        ListNode nNode = prev;
        ListNode nNext = curr;
        mNode.next = nNext;
        if (mPrev == null) {
            return nNode;
        } else {
            mPrev.next = nNode;
            return head;
        }
    }

    private static class ListNode {
        int      val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}

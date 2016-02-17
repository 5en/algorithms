// https://leetcode.com/problems/remove-duplicates-from-sorted-list-ii/

package com.htyleo.algorithms;

public class RemoveDuplicatesFromSortedListII {
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) {
            return head;
        }

        int target = head.val - 1;
        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            if (curr.val == target) {
                // curr is not the left-most duplicate element
                head = removeCurr(head, prev, curr);
            } else if (curr.next != null && curr.val == curr.next.val) {
                // curr is the left-most duplicate element
                target = curr.val;
                head = removeCurr(head, prev, curr);
            } else {
                prev = curr;
            }
            curr = curr.next;
        }

        return head;
    }

    // remove curr and return new head
    private ListNode removeCurr(ListNode head, ListNode prev, ListNode curr) {
        if (prev == null) {
            // curr is head
            return curr.next;
        }

        prev.next = curr.next;

        return head;
    }

    private static class ListNode {
        int      val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}

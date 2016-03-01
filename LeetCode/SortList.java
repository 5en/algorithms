// https://leetcode.com/problems/sort-list/

package com.htyleo.algorithms;

public class SortList {
    public ListNode sortList(ListNode head) {
        if (head == null) {
            return null;
        }
        if (head.next == null) {
            return head;
        }

        // partition in middle
        ListNode slow = head;
        ListNode fast = head.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        ListNode head2 = slow.next;
        slow.next = null;

        // sort each partition
        ListNode sorted1 = sortList(head);
        ListNode sorted2 = sortList(head2);

        // merge
        ListNode result = null;
        ListNode prev = null;
        while (sorted1 != null || sorted2 != null) {
            ListNode curr = null;

            if (sorted2 == null || (sorted1 != null && sorted1.val <= sorted2.val)) {
                curr = sorted1;
                sorted1 = sorted1.next;
            } else {
                curr = sorted2;
                sorted2 = sorted2.next;
            }

            if (prev == null) {
                result = curr;
            } else {
                prev.next = curr;
            }
            prev = curr;
        }

        return result;
    }

    private static class ListNode {
        int      val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}

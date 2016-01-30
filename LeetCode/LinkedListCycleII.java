// https://leetcode.com/problems/linked-list-cycle-ii/

package com.htyleo.algorithms;

import java.util.HashSet;
import java.util.Set;

public class LinkedListCycleII {
    // O(1) space
    //
    // Definitions:
    // C = length of the cycle, if exists.
    // B is the beginning of cycle.
    // D is the distance between B and where the slow pointer meets fast pointer.
    //
    // steps(slow) = B + D
    // steps(fast) = 2 * steps(slow) = 2 * (B + D).
    // steps(fast) - steps(slow) = K * C => B + D = K * C
    // Now the slow pointer is at (B + D). If the slow pointer runs C - D more, it will reaches B.
    // So at this time, if there's another point2 running from head => After B distance, point2 will meet slow pointer at B.
    public ListNode detectCycle(ListNode head) {
        if (head == null || head.next == null) {
            return null;
        }

        ListNode slow = head.next;
        ListNode fast = head.next.next;
        while (slow != fast) {
            slow = slow.next;
            if (fast == null || fast.next == null) {
                return null;
            }
            fast = fast.next.next;
        }

        while (head != slow) {
            head = head.next;
            slow = slow.next;
        }

        return head;
    }

    // O(N) space
    public ListNode detectCycle2(ListNode head) {
        if (head == null) {
            return null;
        }

        Set<ListNode> nodes = new HashSet<ListNode>();
        while (head != null) {
            if (nodes.contains(head)) {
                return head;
            }
            nodes.add(head);
            head = head.next;
        }

        return null;
    }

    private static class ListNode {
        int      val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }
}

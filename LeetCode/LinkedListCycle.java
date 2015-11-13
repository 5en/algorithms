// https://leetcode.com/problems/linked-list-cycle/

package com.htyleo.algorithms;

public class LinkedListCycle {
    public static boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }

        ListNode oneStep = head.next;
        ListNode twoStep = head.next.next;
        while (twoStep != null && twoStep.next != null) {
            if (oneStep == twoStep) {
                return true;
            }
            oneStep = oneStep.next;
            twoStep = twoStep.next.next;
        }

        return false;
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

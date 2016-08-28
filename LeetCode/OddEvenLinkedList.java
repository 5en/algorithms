// https://leetcode.com/problems/odd-even-linked-list/

package com.htyleo.algorithms;

public class OddEvenLinkedList {

    public ListNode oddEvenList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode oddHead = head;
        ListNode evenHead = head.next;
        ListNode oddPrev = oddHead;
        ListNode evenPrev = evenHead;
        ListNode curr = evenHead.next;
        for (boolean isOdd = true; curr != null; isOdd = !isOdd, curr = curr.next) {
            if (isOdd) {
                oddPrev.next = curr;
                oddPrev = curr;
            } else {
                evenPrev.next = curr;
                evenPrev = curr;
            }
        }

        oddPrev.next = evenHead;
        evenPrev.next = null;

        return oddHead;
    }

    private static class ListNode {
        int      val;
        ListNode next;
    }

}

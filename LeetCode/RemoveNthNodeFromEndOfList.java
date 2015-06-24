// https://leetcode.com/problems/remove-nth-node-from-end-of-list/

public class RemoveNthNodeFromEndOfList {
    public static ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode nAhead = head;
        for (int i = 0; i < n; i++) {
            nAhead = nAhead.next;
        }

        ListNode prev = null;
        ListNode curr = head;
        while (nAhead != null) {
            prev = (prev == null ? head : prev.next);
            curr = curr.next;
            nAhead = nAhead.next;
        }

        // remove curr
        if (prev == null) {
            head = head.next;
        } else {
            prev.next = curr.next;
        }

        return head;
    }

    private static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
            next = null;
        }
    }
}

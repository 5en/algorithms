// https://leetcode.com/problems/insertion-sort-list/

package com.htyleo.algorithms;

public class InsertionSortList {
    public ListNode insertionSortList(ListNode head) {
        ListNode result = new ListNode(-1); // sentinel

        ListNode node = head;
        while (node != null) {
            ListNode nextNode = node.next;
            insert(result, node);
            node = nextNode;
        }

        return result.next;
    }

    private void insert(ListNode result, ListNode node) {
        ListNode prev = result;
        ListNode curr = result.next;
        while (curr != null && curr.val <= node.val) {
            prev = curr;
            curr = curr.next;
        }

        node.next = curr;
        prev.next = node;
    }

    public class ListNode {
        int      val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}

// https://leetcode.com/problems/merge-k-sorted-lists/

import java.util.Comparator;
import java.util.PriorityQueue;

public class MergeKSortedLists {
    public static ListNode mergeKLists(ListNode[] lists) {
        PriorityQueue<ListNode> pq = new PriorityQueue<ListNode>(new Comparator<ListNode>(){
            @Override
            public int compare(ListNode n1, ListNode n2) {
                return n1.val < n2.val ? -1 : (n1.val == n2.val ? 0 : 1);
            }
        });
        for (ListNode list : lists) {
            if (list != null) {
                pq.add(list);
            }
        }
        
        if (pq.isEmpty()) {
            return null;
        }
        ListNode head = pq.remove();
        if (head.next != null) {
            pq.add(head.next);
        }
        ListNode prev = head;
        while (!pq.isEmpty()) {
            ListNode curr = pq.remove();
            if (curr.next != null) {
                pq.add(curr.next);
            }
            prev.next = curr;
            prev = curr;
        }
        
        return head;
    }

    private static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }
}

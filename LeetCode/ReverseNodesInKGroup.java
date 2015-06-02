// https://leetcode.com/problems/reverse-nodes-in-k-group/

public class ReverseNodesInKGroup {
    public static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }
    
    public static ListNode reverseKGroup(ListNode head, int k) {
        ListNode curr = head;
        for (int i = 1; i <= k; i++) {
            if (curr == null) {
                return head;
            }
            curr = curr.next;
        }
        
        ListNode resultHead = head;        
        curr = head;
        for (int i = 1; i <= k-1; i++) {
            ListNode currNext = curr.next;
            ListNode currNextNext = curr.next.next;
            curr.next = currNextNext;
            currNext.next = resultHead;
            resultHead = currNext;
        }
        curr.next = reverseKGroup(curr.next, k);
        
        return resultHead;
    }
}

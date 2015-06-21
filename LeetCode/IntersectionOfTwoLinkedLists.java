// https://leetcode.com/problems/intersection-of-two-linked-lists/

public class IntersectionOfTwoLinkedLists {
    public static ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        int lenA = getLength(headA);
        int lenB = getLength(headB);

        // ensure lenA >= lenB
        if (lenB > lenA) {
            ListNode tmpNode = headA;
            headA = headB;
            headB = tmpNode;

            int tmpLen = lenA;
            lenA = lenB;
            lenB = tmpLen;
        }

        // process headA
        for (int i = 0; i < lenA - lenB; i++) {
            headA = headA.next;
        }

        for (int i = 0; i < lenB; i++) {
            if (headA == headB) {
                return headA;
            }

            headA = headA.next;
            headB = headB.next;
        }

        return null;
    }

    private static int getLength(ListNode head) {
        int len = 0;
        while (head != null) {
            len++;
            head = head.next;
        }

        return len;
    }

    private static class ListNode {
        int val;
        ListNode next;

        public ListNode(int x) {
            val = x;
            next = null;
        }
    }
}

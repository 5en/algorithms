// https://leetcode.com/problems/partition-list/

package com.htyleo.algorithms;

public class PartitionList {
    // one-pass
    public ListNode partition(ListNode head, int x) {
        ListNode leftHead = null;
        ListNode leftTail = null;
        ListNode rightHead = null;
        ListNode rightTail = null;
        while (head != null) {
            if (head.val < x) {
                if (leftTail == null) {
                    leftHead = head;
                    leftTail = head;
                } else {
                    leftTail.next = head;
                    leftTail = head;
                }
            } else {
                if (rightTail == null) {
                    rightHead = head;
                    rightTail = head;
                } else {
                    rightTail.next = head;
                    rightTail = head;
                }
            }

            head = head.next;
        }

        if (leftTail != null) {
            leftTail.next = rightHead;
        }
        if (rightTail != null) {
            rightTail.next = null; // prevents
        }
        return leftHead != null ? leftHead : rightHead;
    }

    public ListNode partition2(ListNode head, int x) {
        return partitionSR(head, x).front;
    }

    private Result partitionSR(ListNode head, int x) {
        if (head == null) {
            return new Result(null, null);
        }

        Result subResult = partitionSR(head.next, x);
        head.next = subResult.front;
        if (subResult.front == null || subResult.partition == null) {
            return new Result(head, head.val < x ? head : null);
        }
        // subResult.front != null && subResult.partition != null
        if (head.val < x) {
            return new Result(head, subResult.partition);
        }
        // subResult.front != null && subResult.partition != null && head.val >= x
        head.next = subResult.partition.next;
        subResult.partition.next = head;
        return new Result(subResult.front, subResult.partition);
    }

    private static class Result {
        ListNode front;
        ListNode partition; // points to the rightmost node < x. null if all nodes >= x

        public Result(ListNode front, ListNode partition) {
            this.front = front;
            this.partition = partition;
        }
    }

    public class ListNode {
        int      val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}

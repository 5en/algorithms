package com.htyleo.algorithms;

/**
 * Created by htyleo on 7/7/17.
 */
public class ReorderList {

  public void reorderList(ListNode head) {
    if (head == null || head.next == null) {
      return;
    }

    // 1 -> [2] -> 3 -> 4
    // slow 1 2
    // fast 1 3
    //
    // 1 -> 2 -> [3] -> 4 -> 5
    // slow 1 2 3
    // fast 1 3 5

    ListNode slow = head;
    ListNode fast = head;
    while (fast.next != null && fast.next.next != null) {
      slow = slow.next;
      fast = fast.next.next;
    }

    // now slow is the end of the first half
    ListNode second = slow.next;
    slow.next = null;
    ListNode prev = null;
    while (true) {
      ListNode currNext = second.next;
      second.next = prev;
      prev = second;
      if (currNext == null) {
        break;
      }
      second = currNext;
    }

    // merge two lists
    ListNode first = head.next;
    ListNode curr = head;
    for (boolean isFirst = false; first != null || second != null; isFirst = !isFirst) {
      if (isFirst) {
        curr.next = first;
        first = first.next;
      } else {
        curr.next = second;
        second = second.next;
      }
      curr = curr.next;
    }
  }

  public class ListNode {

    int val;
    ListNode next;

    ListNode(int x) {
      val = x;
    }
  }
}

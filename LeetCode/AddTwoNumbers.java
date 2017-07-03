// https://leetcode.com/problems/add-two-numbers/#/description

package com.htyleo.algorithms;

/**
 * Created by htyleo on 7/4/17.
 */
public class AddTwoNumbers {

  public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
    ListNode head = null;
    ListNode prev = null;
    ListNode curr = null;
    int carry = 0;
    while (l1 != null || l2 != null) {
      if (l1 == null) {
        curr = new ListNode(l2.val);
      } else if (l2 == null) {
        curr = new ListNode(l1.val);
      } else {
        curr = new ListNode(l1.val + l2.val);
      }
      int sum = curr.val + carry;
      carry = sum / 10;
      curr.val = sum % 10;

      if (head == null) {
        head = curr;
        prev = curr;
      } else {
        prev.next = curr;
        prev = curr;
      }

      // find next
      if (l1 != null) {
        l1 = l1.next;
      }
      if (l2 != null) {
        l2 = l2.next;
      }
    }

    if (carry > 0) {
      prev.next = new ListNode(carry);
    }

    return head;
  }

  private static class ListNode {

    int val;
    ListNode next;

    ListNode(int x) {
      val = x;
    }
  }
}

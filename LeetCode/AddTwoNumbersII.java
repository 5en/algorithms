// https://leetcode.com/problems/add-two-numbers-ii/#/description

package com.htyleo.algorithms;

import java.util.Stack;

/**
 * Created by htyleo on 6/10/17.
 */
public class AddTwoNumbersII {

  public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
    Stack<Integer> s1 = parse(l1);
    Stack<Integer> s2 = parse(l2);

    ListNode head = null;
    for (int carry = 0; !s1.isEmpty() || !s2.isEmpty() || carry != 0; ) {
      int d1 = s1.isEmpty() ? 0 : s1.pop();
      int d2 = s2.isEmpty() ? 0 : s2.pop();
      int sum = d1 + d2 + carry;
      carry = sum / 10;

      ListNode curr = new ListNode(sum % 10);
      curr.next = head;
      head = curr;
    }

    return head;
  }

  private Stack<Integer> parse(ListNode node) {
    Stack<Integer> res = new Stack<Integer>();
    while (node != null) {
      res.push(node.val);
      node = node.next;
    }

    return res;
  }

  private static class ListNode {

    int val;
    ListNode next;

    ListNode(int x) {
      val = x;
    }
  }
}

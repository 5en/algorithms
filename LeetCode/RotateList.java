// https://leetcode.com/problems/rotate-list/#/description

package com.htyleo.algorithms;

/**
 * Created by htyleo on 7/9/17.
 */
public class RotateList {

  public ListNode rotateRight(ListNode head, int k) {
    int N = 0;
    ListNode prev = null;
    ListNode curr = head;
    while (curr != null) {
      prev = curr;
      curr = curr.next;
      N++;
    }
    if (N == 0) {
      return head;
    }
    k = k % N;
    if (k == 0) {
      return head;
    }

    ListNode tail = prev;
    prev = null;
    curr = head;
    for (int i = 0; i < N - k; i++) {
      prev = curr;
      curr = curr.next;
    }
    prev.next = null;
    tail.next = head;

    return curr;
  }

  private static class ListNode {

    int val;
    ListNode next;

    ListNode(int x) {
      val = x;
    }
  }

}

// https://leetcode.com/problems/linked-list-random-node/#/description
// http://blog.cloudera.com/blog/2013/04/hadoop-stratified-randosampling-algorithm/

package com.htyleo.algorithms;

import java.util.Random;

/**
 * Created by htyleo on 6/9/17.
 */
public class LinkedListRandomNode {

  private ListNode head;
  private Random rand = new Random();

  /**
   * @param head The linked list's head. Note that the head is guaranteed to be not null, so it
   * contains at least one node.
   */
  public LinkedListRandomNode(ListNode head) {
    this.head = head;
  }

  /**
   * Returns a random node's value.
   */
  public int getRandom() {
    int val = head.val;
    int n = 1;
    for (ListNode curr = head.next; curr != null; curr = curr.next) {
      n++;
      // reservoir sampling
      // we have (n - 1)/n probability to hold the old value and 1/n probability to choose the new one
      if (rand.nextInt(n) == 0) {
        val = curr.val;
      }
    }

    return val;
  }

  private static class ListNode {

    int val;
    ListNode next;

    ListNode(int x) {
      val = x;
    }
  }
}

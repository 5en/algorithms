// https://leetcode.com/problems/copy-list-with-random-pointer/#/description

package com.htyleo.algorithms;

/**
 * Created by htyleo on 7/5/17.
 */
public class CopyListWithRandomPointer {

  // O(N) time, O(1) extra space
  // hash table: O(N) extra space
  public RandomListNode copyRandomList(RandomListNode head) {
    // put the copied node between immediately after the original node
    for (RandomListNode curr = head; curr != null; curr = curr.next.next) {
      RandomListNode copy = new RandomListNode(curr.label);
      copy.next = curr.next;
      curr.next = copy;
    }

    // update copy's random
    for (RandomListNode curr = head; curr != null; curr = curr.next.next) {
      curr.next.random = curr.random == null ? null : curr.random.next;
    }

    // extract copy
    RandomListNode copyHead = head == null ? null : head.next;
    RandomListNode copyPrev = null;
    for (RandomListNode curr = head; curr != null; curr = curr.next) {
      RandomListNode copy = curr.next;
      if (copyPrev != null) {
        copyPrev.next = copy;
      }
      copyPrev = copy;
      curr.next = copy.next;
    }

    return copyHead;
  }

  private static class RandomListNode {

    int label;
    RandomListNode next, random;

    RandomListNode(int x) {
      this.label = x;
    }

  }
}

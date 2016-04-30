// A list L is too big to fit in memory. L is partially sorted.
// Partially sorted in a specific way: x-sorted L[i] < L[i+x]. Any element is at most x indices out of position.
// You can look at the condition in a different way too. L[i] >= L[i-x]
// Sort the list L.

package com.htyleo.algorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class PartiallySorted {

    public static void main(String[] args) {
        int[] nums = { 3, 2, 1, 4, 3, 2, 10, 8, 9 };
        sort(nums, 3);
        System.out.println(Arrays.toString(nums));
    }

    // O(N*logK) time, O(N) space
    //
    // construct K groups
    // min heap
    public static void sort(int[] nums, int k) {
        List<Queue<Element>> queues = new ArrayList<Queue<Element>>(k);
        for (int i = 0; i < k; i++) {
            queues.add(new LinkedList<Element>());
        }
        for (int i = 0; i < nums.length; i++) {
            int qi = i % k;
            queues.get(qi).add(new Element(nums[i], qi));
        }

        PriorityQueue<Element> minHeap = new PriorityQueue<Element>(k, new Comparator<Element>() {
            @Override
            public int compare(Element e1, Element e2) {
                return e1.val <= e2.val ? -1 : 1;
            }
        });

        for (int qi = 0; qi < k; qi++) {
            if (!queues.get(qi).isEmpty()) {
                minHeap.add(queues.get(qi).remove());
            }
        }

        int i = 0;
        while (!minHeap.isEmpty()) {
            Element element = minHeap.remove();
            int qi = element.qi;
            if (!queues.get(qi).isEmpty()) {
                minHeap.add(queues.get(qi).remove());
            }

            nums[i++] = element.val;
        }
    }

    private static class Element {
        int val;
        int qi;

        public Element(int val, int index) {
            this.val = val;
            this.qi = index;
        }
    }
}

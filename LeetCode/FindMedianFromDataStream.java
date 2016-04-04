// https://leetcode.com/problems/find-median-from-data-stream/

// Your MedianFinder object will be instantiated and called as such:
// MedianFinder mf = new MedianFinder();
// mf.addNum(1);
// mf.findMedian();

package com.htyleo.algorithms;

import java.util.Collections;
import java.util.PriorityQueue;

public class FindMedianFromDataStream {
    // keep the smaller half
    private PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>(0,
                                               Collections.reverseOrder());

    // keep the larger half
    private PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>();

    // Adds a number into the data structure.
    public void addNum(int num) {
        // invariant: Math.abs(minHeap.size() - maxHeap.size()) <= 1

        if (maxHeap.isEmpty()) {
            maxHeap.add(num);
            return;
        }

        if (num <= maxHeap.peek()) {
            maxHeap.add(num);
        } else {
            minHeap.add(num);
        }

        if (maxHeap.size() >= minHeap.size() + 2) {
            minHeap.add(maxHeap.remove());
        } else if (maxHeap.size() <= minHeap.size() - 2) {
            maxHeap.add(minHeap.remove());
        }
    }

    // Returns the median of current data stream
    public double findMedian() {
        if (maxHeap.size() == minHeap.size()) {
            return ((double) (maxHeap.peek() + minHeap.peek())) / 2;
        } else if (maxHeap.size() > minHeap.size()) {
            return maxHeap.peek();
        } else {
            return minHeap.peek();
        }
    }
}

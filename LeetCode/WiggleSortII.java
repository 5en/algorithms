// https://leetcode.com/problems/wiggle-sort-ii/#/description

package com.htyleo.algorithms;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by htyleo on 7/6/17.
 */
public class WiggleSortII {

  // O(N) (expected) time, O(N) space
  public void wiggleSort(int[] nums) {
    int N = nums.length;
    int median = findMedian(nums);

    List<Integer> s = new ArrayList<>();
    List<Integer> m = new ArrayList<>();
    List<Integer> l = new ArrayList<>();
    for (int num : nums) {
      if (num < median) {
        s.add(num);
      } else if (num > median) {
        l.add(num);
      } else {
        m.add(num);
      }
    }

    // fill large at 1, 3, 5...
    int li = 1;
    for (int num : l) {
      nums[li] = num;
      li += 2;
    }

    // fill small at N - 1 if N is odd, N - 2 if N is even, backwards
    int si = N % 2 == 0 ? N - 2 : N - 1;
    for (int num : s) {
      nums[si] = num;
      si -= 2;
    }

    // fill median at 0, and the rest
    nums[0] = m.get(0);
    for (int i = 1; i < m.size(); i++) {
      if (li < N) {
        nums[li] = m.get(i);
        li += 2;
      } else if (si >= 2) {
        nums[si] = m.get(i);
        si -= 2;
      }
    }
  }

  private int findMedian(int[] nums) {
    int N = nums.length;
    return findKthLargest(nums, N % 2 == 0 ? N / 2 : N / 2 + 1, 0, N - 1);
  }

  private int findKthLargest(int[] nums, int K, int low, int high) {
    if (high - low <= 1) {
      if (nums[low] > nums[high]) {
        swap(nums, low, high);
      }
      return K == 1 ? nums[low] : nums[high];
    }

    int N = nums.length;
    int pivot = nums[high];
    int pivotIdx = low;
    for (int i = low; i < high; i++) {
      // nums[low...pivotIdx] <= pivot
      if (nums[i] <= pivot) {
        swap(nums, i, pivotIdx);
        pivotIdx++;
      }
    }
    swap(nums, pivotIdx, high);

    if (pivotIdx - low + 1 > K) {
      return findKthLargest(nums, K, low, pivotIdx - 1);
    } else if (pivotIdx - low + 1 < K) {
      return findKthLargest(nums, K - (pivotIdx - low + 1), pivotIdx + 1, high);
    } else {
      return pivot;
    }
  }

  private void swap(int[] nums, int i, int j) {
    int tmp = nums[i];
    nums[i] = nums[j];
    nums[j] = tmp;
  }

}

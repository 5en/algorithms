// https://leetcode.com/problems/wiggle-subsequence/
// https://discuss.leetcode.com/topic/51946/very-simple-java-solution-with-detail-explanation

package com.htyleo.algorithms;

public class WiggleSubsequence {

    public int wiggleMaxLength(int[] nums) {
        if (nums.length <= 1) {
            return nums.length;
        }

        int curr = 1;
        for (; curr < nums.length; curr++) {
            if (nums[curr] != nums[0]) {
                break;
            }
        }
        if (curr == nums.length) {
            return 1;
        }

        boolean asc = nums[0] < nums[curr];
        int count = 2;
        for (int next = curr + 1; next < nums.length; next++) {
            if (asc) {
                // nums[curr] nums[next] should descend now
                if (nums[curr] > nums[next]) {
                    count++;
                    asc = !asc;
                }
                // else, nums[curr] < nums[next], ignore nums[curr]
            } else {
                // nums[curr] nums[next] should ascend now
                if (nums[curr] < nums[next]) {
                    count++;
                    asc = !asc;
                }
                // else, nums[curr] > nums[next], ignore nums[curr]
            }

            curr = next;
        }

        return count;
    }

}

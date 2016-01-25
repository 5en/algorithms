// https://leetcode.com/problems/remove-duplicates-from-sorted-array-ii/

package com.htyleo.algorithms;

public class RemoveDuplicatesFromSortedArrayII {
    public int removeDuplicates(int[] nums) {
        int N = nums.length;
        if (N <= 2) {
            return N;
        }

        int hole = -1; // index of the first hole
        int currNum = nums[0];
        int count = 1;
        for (int i = 1; i < N; i++) {
            int num = nums[i];
            if (currNum != num) {
                if (hole != -1) {
                    nums[hole++] = num;
                }
                currNum = num;
                count = 1;
            } else if (count == 2) {
                // remove duplicate
                hole = (hole == -1 ? i : hole);
            } else {
                if (hole != -1) {
                    nums[hole++] = num;
                }
                count++;
            }
        }

        return hole == -1 ? N : hole;
    }
}

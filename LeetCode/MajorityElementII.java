// https://leetcode.com/problems/majority-element-ii/
// Boyer–Moore majority vote algorithm

package com.htyleo.algorithms;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MajorityElementII {
    public List<Integer> majorityElement(int[] nums) {
        Set<Integer> result = new HashSet<Integer>();
        if (nums.length == 0) {
            return new ArrayList<Integer>(result);
        }

        int[] candidates = new int[2];
        int[] scores = new int[2];

        for (int num : nums) {
            if (candidates[0] == num) {
                scores[0]++;
            } else if (candidates[1] == num) {
                scores[1]++;
            } else if (scores[0] == 0) {
                candidates[0] = num;
                scores[0] = 1;
            } else if (scores[1] == 0) {
                candidates[1] = num;
                scores[1] = 1;
            } else {
                scores[0]--;
                scores[1]--;
            }
        }

        int[] count = new int[2];
        for (int num : nums) {
            for (int i = 0; i <= 1; i++) {
                if (candidates[i] == num) {
                    count[i]++;
                }
            }
        }

        int limit = (int) Math.floor(((double) nums.length) / 3);
        for (int i = 0; i <= 1; i++) {
            if (count[i] > limit) {
                result.add(candidates[i]);
            }
        }

        return new ArrayList<Integer>(result);
    }
}

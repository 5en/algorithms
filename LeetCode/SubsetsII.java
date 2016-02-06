// https://leetcode.com/problems/subsets-ii/

package com.htyleo.algorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SubsetsII {
    // If we want to insert an element which is a dup, we can only insert it after the newly inserted elements from last step.
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        for (int bits = 0; bits < Math.pow(2, nums.length); bits++) {
            int tmpBits = bits;
            List<Integer> curr = new ArrayList<Integer>();
            boolean ignore = false;

            // choosePrev indicates whether nums[]
            boolean choosePrev = false;
            for (int i = 0; i < nums.length; i++, tmpBits = tmpBits >> 1) {
                if ((tmpBits & 1) == 0) {
                    choosePrev = false;
                    continue;
                }

                if (i == 0 || nums[i] != nums[i - 1] || choosePrev) {
                    curr.add(nums[i]);
                    choosePrev = true;
                    continue;
                }

                // find a duplicate subset
                ignore = true;
                break;
            }

            if (!ignore) {
                result.add(curr);
            }
        }

        return result;
    }

    public List<List<Integer>> subsetsWithDup2(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        List<Integer> curr = new ArrayList<Integer>();
        helper(result, curr, nums, 0, false);

        return result;
    }

    // choosePrev indicates whether nums[index-1] is chosen
    private void helper(List<List<Integer>> result, List<Integer> curr, int[] nums, int index,
                        boolean choosePrev) {
        if (index == nums.length) {
            result.add(new ArrayList<Integer>(curr));
            return;
        }

        // do not choose nums[start]
        helper(result, curr, nums, index + 1, false);

        // choose nums[start]
        if (index == 0 || nums[index] != nums[index - 1] || choosePrev) {
            curr.add(nums[index]);
            helper(result, curr, nums, index + 1, true);
            curr.remove(curr.size() - 1);
        }
    }
}

// https://leetcode.com/problems/4sum/

package com.htyleo.algorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FourSum {
    public static void main(String[] args) {
        System.out.println(new FourSum().fourSum(new int[] { -1, -5, -5, -3, 2, 5, 0, 4 }, 0));
    }

    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();

        if (nums == null || nums.length < 4) {
            return result;
        }

        Arrays.sort(nums);

        int max = nums[nums.length - 1];
        // extreme cases
        if (4 * nums[0] > target || 4 * max < target) {
            return result;
        }

        for (int i = 0; i <= nums.length - 4; i++) {
            int num = nums[i];
            // avoid duplicates
            if (i > 0 && num == nums[i - 1]) {
                continue;
            }
            // num is too small
            if (num + 3 * max < target) {
                continue;
            }
            // num is too large
            if (num * 4 > target) {
                break;
            }

            threeSum(nums, target - num, i + 1, nums.length - 1, result, num);
        }

        return result;
    }

    /*
     * three sum in nums[low...high]
     */
    public void threeSum(int[] nums, int target, int low, int high, List<List<Integer>> result,
                         int pNum) {
        if (high - low + 1 < 3) {
            return;
        }

        int max = nums[high];
        if (3 * nums[low] > target || 3 * max < target) {
            return;
        }

        for (int i = low; i <= high - 2; i++) {
            int num = nums[i];
            // avoid duplicates
            if (i > low && num == nums[i - 1]) {
                continue;
            }
            // num is too small
            if (num + 2 * max < target) {
                continue;
            }
            // num is too large
            if (num * 3 > target) {
                break;
            }

            twoSum(nums, target - num, i + 1, high, result, pNum, num);
        }
    }

    /*
     * two sum in nums[low, high]
     */
    public void twoSum(int[] nums, int target, int low, int high, List<List<Integer>> result,
                       int ppNum, int pNum) {
        for (int lt = low, rt = high; lt < rt;) {
            // avoid duplicates
            while (lt > low && lt <= high && nums[lt] == nums[lt - 1]) {
                lt++;
            }
            while (rt < high && rt >= low && nums[rt] == nums[rt + 1]) {
                rt--;
            }
            if (lt >= rt) {
                break;
            }

            int sum = nums[lt] + nums[rt];
            if (sum > target) {
                rt--;
            } else if (sum < target) {
                lt++;
            } else {
                List<Integer> quadruplet = new ArrayList<Integer>();
                quadruplet.add(ppNum);
                quadruplet.add(pNum);
                quadruplet.add(nums[lt]);
                quadruplet.add(nums[rt]);
                result.add(quadruplet);

                lt++;
                rt--;
            }
        }
    }
}

// https://leetcode.com/problems/two-sum/

package com.htyleo.algorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TwoSum {
    // O(N) time, O(N) space
    public int[] twoSum(int[] nums, int target) {
        int N = nums.length;

        Map<Integer, List<Integer>> num2Index = new HashMap<Integer, List<Integer>>();
        for (int i = 0; i < N; i++) {
            if (!num2Index.containsKey(nums[i])) {
                num2Index.put(nums[i], new ArrayList<Integer>());
            }
            num2Index.get(nums[i]).add(i);
        }

        for (int num : nums) {
            if (!num2Index.containsKey(target - num)) {
                continue;
            }

            if (num == target - num && num2Index.get(num).size() == 2) {
                return new int[] { num2Index.get(num).get(0), num2Index.get(num).get(1) };
            }
            if (num != target - num && num2Index.containsKey(target - num)) {
                return new int[] { num2Index.get(num).get(0), num2Index.get(target - num).get(0) };
            }
        }

        return null;
    }

    // O(N*logN) time, O(N) space
    public int[] twoSum2(int[] nums, int target) {
        int N = nums.length;

        Item[] items = new Item[N];
        for (int i = 0; i < N; i++) {
            items[i] = new Item(nums[i], i);
        }

        Arrays.sort(items);
        for (int i = 0, j = N - 1; i < j;) {
            if (items[i].num + items[j].num < target) {
                i++;
            } else if (items[i].num + items[j].num > target) {
                j--;
            } else {
                return new int[] { items[i].index, items[j].index };
            }
        }

        return null;
    }

    private static class Item implements Comparable<Item> {
        public final int num;
        public final int index;

        public Item(int num, int index) {
            this.num = num;
            this.index = index;
        }

        @Override
        public int compareTo(Item item) {
            return this.num < item.num ? -1 : (this.num > item.num ? 1 : 0);
        }
    }
}

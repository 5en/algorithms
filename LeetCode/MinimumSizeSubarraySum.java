// https://leetcode.com/problems/minimum-size-subarray-sum/

import java.util.Random;

public class MinimumSizeSubarraySum {
    public static int minSubArrayLen(int s, int[] nums) {
        if (new Random().nextInt() % 2 == 0) {
            return minSubArrayLen1(s, nums);
        } else {
            return minSubArrayLen2(s, nums);
        }
    }

    // O(N) time, O(1) space
    public static int minSubArrayLen1(int s, int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int minLen = 0;
        int low = 0;
        int high = 0;
        int sum = nums[0];
        // invariant: 0 <= low <= high < num.length
        while (true) {
            if (sum >= s) {
                if (minLen == 0 || high - low + 1 < minLen) {
                    minLen = high - low + 1;
                    if (minLen == 1) {
                        return minLen;
                    }
                }

                // [low, high] -> [low+1, high]
                sum -= nums[low];
                low++;
            } else {
                // [low, high] -> [low, high+1]
                high++;
                if (high < nums.length) {
                    sum += nums[high];
                } else {
                    break;
                }
            }
        }

        return minLen;
    }

    // O(NlogN) time, O(N) space
    public static int minSubArrayLen2(int s, int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        // sums[i] = nums[0] + ... + nums[i]
        // sums[j] - sums[i] = nums[i+1] + ... + nums[j]
        int[] sums = new int[nums.length];
        sums[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            sums[i] = sums[i-1] + nums[i];
        }

        int minLen = 0;
        for (int i = 0; i < nums.length; i++) {
            if (sums[i] >= s) {
                // find max j < i such that sums[i] - sums[j] >= s (i.e. sums[j] <= sums[i] - s), len = i - (j+1) + 1 = i - j
                int low = 0;
                int high = i - 1;
                while (low <= high) {
                    int mid = (low + high) / 2;
                    if (sums[mid] <= sums[i] - s) {
                        low = mid + 1;
                    } else {
                        high = mid - 1;
                    }
                }
                int j = low - 1;

                if (minLen == 0 || i - j < minLen) {
                    minLen = i - j;
                }
            }
        }

        return minLen;
    }
}

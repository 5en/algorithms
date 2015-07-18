// https://leetcode.com/problems/find-peak-element/

public class FindPeakElement {
    // O(N) time
    public static int findPeakElement1(int[] nums) {
        if (nums.length == 1) {
            return 0;
        }

        for (int i = 0; i < nums.length; i++) {
            if (i == 0) {
                if (nums[i] > nums[i+1]) return i;
                continue;
            }

            if (i==nums.length-1) {
                if (nums[i] > nums[i-1]) return i;
                continue;
            }

            if (nums[i] > nums[i-1] && nums[i] > nums[i+1]) {
                return i;
            }
        }

        return -1;
    }

    // O(logN) time
    public static int findPeakElement2(int[] nums) {
        return findPeakElement2SR(nums, 0, nums.length-1);
    }

    private static int findPeakElement2SR(int[] nums, int low, int high) {
        if (low == high) {
            return low;
        }
        if (low == high - 1) {
            return nums[high] > nums[low] ? high : low;
        }

        int mid = (low + high) / 2;

        if (nums[low] > nums[mid]) {
            return findPeakElement2SR(nums, low, mid-1);
        }
        if (nums[high] > nums[mid]) {
            return findPeakElement2SR(nums, mid+1, high);
        }

        // nums[mid] > nums[low] && nums[mid] > nums[high]

        if (nums[mid] > nums[mid-1] && nums[mid] > nums[mid+1]) {
            return mid;
        }

        return nums[mid-1] < nums[mid] ? findPeakElement2SR(nums, mid, high) : findPeakElement2SR(nums, low, mid);
    }
}

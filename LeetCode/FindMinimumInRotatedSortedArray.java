// https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/

public class FindMinimumInRotatedSortedArray {
    public static int findMin(int[] nums) {
        return findMinSR(nums, 0, nums.length-1);
    }

    private static int findMinSR(int[] nums, int low, int high) {
        if (low == high) {
            return nums[low];
        }
        if (low == high - 1) {
            return Math.min(nums[low], nums[high]);
        }

        int mid = (low + high) / 2;
        if (nums[low] < nums[mid] && nums[mid] < nums[high]) { // no rotation
            return nums[low];
        }
        if (nums[low] < nums[mid] && nums[mid] > nums[high]) { // break point is between (mid, high]
            return findMinSR(nums, mid+1, high);
        }

        // else, nums[low] > nums[mid], break point is between (low, mid]
        return findMinSR(nums, low+1, mid);
    }
}

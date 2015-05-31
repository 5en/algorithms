// https://leetcode.com/problems/search-for-a-range/

import java.util.Arrays;

public class SearchRange {
    public static void main(String[] args) {
        int[] a = {5, 7, 7, 8, 8, 10};
        System.out.println(Arrays.toString(searchRange(a, 8)));
    }
    
    public static int[] searchRange(int[] nums, int target) {
        return searchRange(nums, 0, nums.length-1, target);
    }
    
    private static int[] searchRange(int[] nums, int low, int high, int target) {
        if (low > high) {
            return new int[]{-1, -1};
        }
        
        int mid = (low + high) / 2;
        if (nums[mid] < target) {
            return searchRange(nums, mid+1, high, target);
        } else if (nums[mid] > target) {
            return searchRange(nums, low, mid-1, target);
        } else {
            // nums[mid] == target
            int left = searchLeftMost(nums, low, mid, target);
            int right = searchRightMost(nums, mid, high, target);
            return new int[]{left, right};
        }
    }
    
    private static int searchLeftMost(int[] nums, int low, int high, int target) {
        if (low > high) {
            return -1;
        }
        
        while (low <= high) {
            int mid = (low + high) / 2;
            if (nums[mid] < target) {
                low = mid + 1;
            } else {
                // nums[mid] >= target
                high = mid - 1;
            }
        }
        
        return nums[low] == target ? low : -1;
    }
    
    private static int searchRightMost(int[] nums, int low, int high, int target) {
        if (low > high) {
            return -1;
        }
        
        while (low <= high) {
            int mid = (low + high) / 2;
            if (nums[mid] > target) {
                high = mid - 1;
            } else {
                // nums[mid] <= target
                low = mid + 1;
            }
        }
        
        return nums[high] == target ? high : -1;
    }
}

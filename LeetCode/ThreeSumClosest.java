// https://leetcode.com/problems/3sum-closest/

import java.util.Arrays;

public class ThreeSumClosest {
    public int threeSumClosest(int[] nums, int target) {
        int result = nums[0] + nums[1] + nums[2];

        Arrays.sort(nums);        
        for (int i = 0; i < nums.length; i++) {
            if (i > 0 && nums[i] == nums[i-1]) {
                continue;
            }
            
            int low = i + 1;
            int high = nums.length - 1;
            while (low < high) {
                int curr = nums[i] + nums[low] + nums[high];
                if (curr > target) {
                    high--;
                } else if (curr < target) {
                    low++;
                } else {
                    return target;
                }
                
                result = Math.abs(curr - target) < Math.abs(result - target) ? curr : result;
            }
        }
        
        return result;
    }
}

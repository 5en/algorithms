// https://leetcode.com/contest/leetcode-weekly-contest-10/problems/minimum-moves-to-equal-array-elements-ii/

import java.util.Arrays;

public class MinimumMovesToEqualArrayElementsII {

    // move to the median
    public int minMoves2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        Arrays.sort(nums);

        int result = 0;
        for (int low = 0, high = nums.length - 1; low < high; low++, high--) {
            result += nums[high] - nums[low];
        }

        return result;
    }

}

// https://leetcode.com/contest/smarking-algorithm-contest-4/problems/132-pattern/

public class OneThreeTwoPattern {

    // O(N^2) time
    // minPrev[j]: min{nums[i] | 0 <= i < j} 
    public boolean find132pattern(int[] nums) {
        int[] minPrev = new int[nums.length];
        for (int min = Integer.MAX_VALUE, k = 0; k < nums.length; k++) {
            if (find(nums, minPrev, k)) {
                return true;
            }

            minPrev[k] = min;
            min = Math.min(min, nums[k]);
        }

        return false;
    }

    private boolean find(int[] nums, int[] minPrev, int k) {
        for (int j = 1; j < k; j++) {
            int ai = minPrev[j];
            int aj = nums[j];
            int ak = nums[k];

            if (ai < ak && ak < aj) {
                return true;
            }
        }

        return false;
    }

}

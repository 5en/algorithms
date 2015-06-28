// https://leetcode.com/problems/combination-sum-iii/

import java.util.LinkedList;
import java.util.List;

public class CombinationSumIII {
    public static List<List<Integer>> combinationSum3(int k, int n) {
        int[] nums = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        List<Integer> comb = new LinkedList<Integer>();
        List<List<Integer>> combs = new LinkedList<List<Integer>>();
        combinationSum3Sub(nums, 0, k, n, comb, combs);

        return combs;
    }

    private static void combinationSum3Sub(int[] nums, int start, int k, int n, List<Integer> comb, List<List<Integer>> combs) {
        if (n < 0) {
            return;
        }

        if (comb.size() == k) {
            if (n == 0) {
                combs.add(new LinkedList<Integer>(comb));
            }
            return;
        }

        for (int i = start; i < nums.length; i++) {
            comb.add(nums[i]);
            combinationSum3Sub(nums, i+1, k, n-nums[i], comb, combs);
            comb.remove(comb.size()-1);
        }
    }
}

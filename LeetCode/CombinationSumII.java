// https://leetcode.com/problems/combination-sum-ii/

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class CombinationSumII {
    public static List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<List<Integer>> combs = new LinkedList<List<Integer>>();
        List<Integer> comb = new LinkedList<Integer>();
        combinationSum2Sub(candidates, 0, target, combs, comb);

        return combs;
    }

    // Input is candidates[start], ..., candidates[candidates.length - 1]
    private static void combinationSum2Sub(int[] candidates, int start, int target, List<List<Integer>> combs, List<Integer> comb) {
        if (target < 0) {
            return;
        }

        if (target == 0) {
            combs.add(new LinkedList<Integer>(comb));
            return;
        }

        for (int i = start; i < candidates.length; ) {
            comb.add(candidates[i]);

            combinationSum2Sub(candidates, i + 1, target - candidates[i], combs, comb);

            // backtrack
            comb.remove(comb.size() - 1);

            // remove duplicate comb
            i++;
            while (i < candidates.length && candidates[i] == candidates[i-1]) {
                i++;
            }
        }
    }
}

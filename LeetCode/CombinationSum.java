// https://leetcode.com/problems/combination-sum/

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class CombinationSum {
    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<List<Integer>> combs = new LinkedList<List<Integer>>();
        List<Integer> comb = new LinkedList<Integer>();
        combinationSumSub(candidates, 0, target, combs, comb);

        return combs;
    }

    // Input is candidates[start], ..., candidates[candidates.length - 1]
    private static void combinationSumSub(int[] candidates, int start, int target, List<List<Integer>> combs, List<Integer> comb) {
        if (target == 0) {
            combs.add(new LinkedList<Integer>(comb));
            return;
        }

        if (start >= candidates.length) {
            return;
        }

        for (int multi = 0; candidates[start] * multi <= target; multi++) {
            for (int i = 0; i < multi; i++) {
                comb.add(candidates[start]);
            }

            combinationSumSub(candidates, start + 1, target - multi * candidates[start], combs, comb);

            // backtrack
            for (int i = 0; i < multi; i++) {
                comb.remove(comb.size() - 1);
            }
        }
    }
}

// https://leetcode.com/problems/combination-sum/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class CombinationSum {
    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        Arrays.sort(candidates);
        return combinationSumSub(candidates, 0, target);
    }

    // Input is candidates[start], ..., candidates[candidates.length - 1]
    private static List<List<Integer>> combinationSumSub(int[] candidates, int start, int target) {
        List<List<Integer>> combs = new LinkedList<List<Integer>>();

        if (start == candidates.length - 1) {
            if (target % candidates[start] == 0) {
                List<Integer> comb = new ArrayList<Integer>();
                for (int i = 0; i < target / candidates[start]; i++) {
                    comb.add(candidates[start]);
                }
                combs.add(comb);
            }

            return combs;
        }

        for (int i = 0; candidates[start] * i <= target; i++) {
            if (candidates[start] * i == target) {
                List<Integer> comb = new ArrayList<Integer>();
                for (int j = 0; j < i; j++) {
                    comb.add(candidates[start]);
                }
                combs.add(comb);
                continue;
            }

            List<List<Integer>> subCombs = combinationSumSub(candidates, start + 1, target - i * candidates[start]);
            if (!subCombs.isEmpty()) {
                for(List<Integer> subComb : subCombs) {
                    for (int j = 0; j < i; j++) {
                        subComb.add(0, candidates[start]);
                    }
                }
                combs.addAll(subCombs);
            }
        }

        return combs;
    }
}

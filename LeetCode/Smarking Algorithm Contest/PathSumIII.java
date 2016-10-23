// https://leetcode.com/contest/10/problems/path-sum-iii/

import java.util.HashMap;
import java.util.Map;

public class PathSumIII {

    public int pathSum(TreeNode root, int sum) {
        return pathSumSub(root, sum).matchCount;
    }

    public Result pathSumSub(TreeNode root, int sum) {

        if (root == null) {
            return new Result();
        }

        Result leftResult = pathSumSub(root.left, sum);
        Result rightResult = pathSumSub(root.right, sum);

        int matchCount = leftResult.matchCount + rightResult.matchCount;

        Map<Integer, Integer> sumCount = new HashMap<>();
        for (Integer subSum : leftResult.sumCount.keySet()) {
            int key = subSum + root.val;
            int count = leftResult.sumCount.get(subSum);
            sumCount.put(key, sumCount.containsKey(key) ? sumCount.get(key) + count : count);
            if (key == sum) {
                matchCount += count;
            }
        }
        for (Integer subSum : rightResult.sumCount.keySet()) {
            int key = subSum + root.val;
            int count = rightResult.sumCount.get(subSum);
            sumCount.put(key, sumCount.containsKey(key) ? sumCount.get(key) + count : count);
            if (key == sum) {
                matchCount += count;
            }
        }

        sumCount.put(root.val, sumCount.containsKey(root.val) ? sumCount.get(root.val) + 1 : 1);
        if (root.val == sum) {
            matchCount++;
        }

        Result result = new Result();
        result.matchCount = matchCount;
        result.sumCount = sumCount;

        return result;
    }

    private static class Result {
        int                   matchCount;
        Map<Integer, Integer> sumCount = new HashMap<>();
    }

    public static class TreeNode {
        int      val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

}

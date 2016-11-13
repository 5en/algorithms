// https://leetcode.com/contest/smarking-algorithm-contest-4/problems/4sum-ii/

import java.util.HashMap;
import java.util.Map;

public class FourSumII {

    // each one in A+B sum set -> #
    // each one in C+D sum set -> #
    public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
        Map<Integer, Integer> abSumCnt = computeSumCnt(A, B);
        Map<Integer, Integer> cdSumCnt = computeSumCnt(C, D);

        int count = 0;

        for (int abSum : abSumCnt.keySet()) {
            if (cdSumCnt.containsKey(-abSum)) {
                count += abSumCnt.get(abSum) * cdSumCnt.get(-abSum);
            }
        }

        return count;
    }

    private Map<Integer, Integer> computeSumCnt(int[] A, int[] B) {
        Map<Integer, Integer> sumCnt = new HashMap<>();
        for (int a : A) {
            for (int b : B) {
                int sum = a + b;
                sumCnt.put(sum, sumCnt.containsKey(sum) ? sumCnt.get(sum) + 1 : 1);
            }
        }

        return sumCnt;
    }

}

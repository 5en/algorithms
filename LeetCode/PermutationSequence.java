// https://leetcode.com/problems/permutation-sequence/

package com.htyleo.algorithms;

public class PermutationSequence {
    public String getPermutation(int n, int k) {
        if (n == 1) {
            return "1";
        }

        // factorials[i] = i!
        int[] factorials = new int[n];
        factorials[1] = 1;
        for (int i = 2; i < n; i++) {
            factorials[i] = factorials[i - 1] * i;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            sb.append(i);
        }

        StringBuilder result = new StringBuilder();
        for (int i = n - 1; i >= 1; i--) {
            int group = (k - 1) / factorials[i];
            result.append(sb.charAt(group));
            sb.delete(group, group + 1);
            k -= group * factorials[i];
        }
        result.append(sb);

        return result.toString();
    }
}

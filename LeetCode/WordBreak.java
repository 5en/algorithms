// https://leetcode.com/problems/word-break/

package com.htyleo.algorithms;

import java.util.Set;

public class WordBreak {
    public boolean wordBreak(String s, Set<String> wordDict) {
        int N = s.length();
        boolean[] dp = new boolean[N + 1]; // dp[i] indicates whether s[0,i) satisfies. i = 0...N
        dp[0] = true;

        for (int i = 1; i <= N; i++) {
            for (int j = 0; j < i; j++) {
                // s[0, j) satisfies && s[j,i) in wordDict
                if (dp[j] && wordDict.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }

        return dp[N];
    }
}

package com.htyleo.algorithms.dp;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class LongestCommonSubsequence {
    public static void main(String[] args) {
        lcs("ABCDE", "ACBED");
    }

    // O(M*N) time, O(M*N) space
    // dynamic programming with memo
    public static void lcs(String s1, String s2) {
        int M = s1.length();
        int N = s2.length();

        Info[][] memo = new Info[M][N];
        for (int end1 = 0; end1 < M; end1++) {
            for (int end2 = 0; end2 < N; end2++) {
                memo[end1][end2] = new Info();
            }
        }

        lcsSR(memo, s1, M - 1, s2, N - 1);

        System.out.println(memo[M - 1][N - 1].maxLen);
        for (List<Character> seq : memo[M - 1][N - 1].seqs) {
            System.out.println(seq);
        }
    }

    // find the longest common subsequence in s1[0...end1] and s2[0...end2]
    // lcs(s1[0...end1], s2[0...end2]) = 
    // if (end1 < 0 || end2 < 0): 0
    // if (s1[end1] == s2[end2]): 1 + lcs(s1[0...end1-1], s2[0...end2-1])
    // if (s1[end1] != s2[end2]): max{lcs(s1[0...end1], s2[0...end2-1]), lcs(s1[0...end1-1], s2[0...end2])}
    private static int lcsSR(Info[][] memo, String s1, int end1, String s2, int end2) {
        if (end1 < 0 || end2 < 0) {
            return 0;
        }

        if (memo[end1][end2].maxLen != -1) {
            return memo[end1][end2].maxLen;
        }

        if (s1.charAt(end1) == s2.charAt(end2)) {
            memo[end1][end2].maxLen = 1 + lcsSR(memo, s1, end1 - 1, s2, end2 - 1);

            // (optional) update seqs
            if (end1 - 1 >= 0 && end2 - 1 >= 0) {
                for (List<Character> seq : memo[end1 - 1][end2 - 1].seqs) {
                    List<Character> newSeq = new LinkedList<Character>(seq);
                    newSeq.add(s1.charAt(end1));
                    memo[end1][end2].seqs.add(newSeq);
                }
            } else {
                List<Character> newSeq = new LinkedList<Character>();
                newSeq.add(s1.charAt(end1));
                memo[end1][end2].seqs.add(newSeq);
            }
        } else {
            int len1 = lcsSR(memo, s1, end1, s2, end2 - 1);
            int len2 = lcsSR(memo, s1, end1 - 1, s2, end2);
            memo[end1][end2].maxLen = len1 >= len2 ? len1 : len2;

            // (optional) update seqs
            if (len1 == memo[end1][end2].maxLen && end2 - 1 >= 0) {
                memo[end1][end2].seqs.addAll(memo[end1][end2 - 1].seqs);
            }
            if (len2 == memo[end1][end2].maxLen && end1 - 1 >= 0) {
                memo[end1][end2].seqs.addAll(memo[end1 - 1][end2].seqs);
            }
        }

        return memo[end1][end2].maxLen;
    }

    private static class Info {
        public int                  maxLen = -1;
        public Set<List<Character>> seqs   = new HashSet<List<Character>>(); // set of sequences
    }
}

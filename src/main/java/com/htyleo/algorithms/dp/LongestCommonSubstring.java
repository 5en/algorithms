package com.htyleo.algorithms.dp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class LongestCommonSubstring {
    public static void main(String[] args) {
        System.out.println(lcs("ABC", "DEF"));
        System.out.println(lcs2("ABC", "DEF"));
        System.out.println(lcs3("ABC", "DEF"));

        System.out.println(lcs("ABABCDEF", "ABCBADEF"));
        System.out.println(lcs2("ABABCDEF", "ABCBADEF"));
        System.out.println(lcs3("ABABCDEF", "ABCBADEF"));
    }

    // O(M*N*min(M,N)*min(M,N))
    // brute force
    public static Set<String> lcs(String s1, String s2) {
        Set<String> result = new HashSet<String>();

        int len1 = s1.length();
        int len2 = s2.length();
        int minLen = len1 < len2 ? len1 : len2;

        for (int l = minLen; l >= 1; l--) { // length of substring: min(M,N)
            for (int i1 = 0; i1 < len1 - l + 1; i1++) { // starting point in s1: M
                for (int i2 = 0; i2 < len2 - l + 1; i2++) { // starting point in s2: N
                    if (s1.substring(i1, i1 + l).equals(s2.substring(i2, i2 + l))) { // min(M,N)
                        result.add(s1.substring(i1, i1 + l));
                    }
                }
            }

            if (!result.isEmpty()) {
                break;
            }
        }

        return result;
    }

    // O(M*N) time, O(M*N) space
    // dynamic programming with memo
    // memo[i][j] indicates the max length of common substring starting at s1[i] and s2[j],
    // i = 0...M-1; j = 0...N-1
    // if (s1[i] == s2[j]), m[i][j] = 1 + m[i+1][j+1]
    // if (s1[i] != s2[j]), m[i][j] = 0
    public static Set<String> lcs2(String s1, String s2) {
        int M = s1.length();
        int N = s2.length();

        int[][] memo = new int[M][N];
        for (int i = M - 1; i >= 0; i--) {
            for (int j = N - 1; j >= 0; j--) {
                if (s1.charAt(i) != s2.charAt(j)) {
                    memo[i][j] = 0;
                } else {
                    memo[i][j] = (i + 1 < M && j + 1 < N) ? 1 + memo[i + 1][j + 1] : 1;
                }
            }
        }

        int maxLen = 0;
        List<Integer> starts1 = new LinkedList<Integer>();
        for (int i = M - 1; i >= 0; i--) {
            for (int j = N - 1; j >= 0; j--) {
                if (memo[i][j] > maxLen) {
                    maxLen = memo[i][j];
                    starts1.clear();
                    starts1.add(i);
                } else if (memo[i][j] == maxLen) {
                    starts1.add(i);
                }
            }
        }
        Set<String> result = new HashSet<String>(starts1.size());
        for (int start1 : starts1) {
            result.add(s1.substring(start1, start1 + maxLen));
        }

        return result;
    }

    // O((M+N)^2) time, O(M^2+N^2) space
    // sort suffixes and compare neighbors
    public static Set<String> lcs3(String s1, String s2) {
        List<Suffix> suffixes = new ArrayList<Suffix>(s1.length() + s2.length());
        for (int start = 0; start < s1.length(); start++) {
            suffixes.add(new Suffix(s1, start, 1));
        }
        for (int start = 0; start < s2.length(); start++) {
            suffixes.add(new Suffix(s2, start, 2));
        }
        Collections.sort(suffixes, new Comparator<Suffix>() {
            @Override
            public int compare(Suffix sfx1, Suffix sfx2) {
                return sfx1.s.substring(sfx1.start).compareTo(sfx2.s.substring(sfx2.start));
            }
        });

        int maxLen = 0;
        Set<Integer> starts = new HashSet<Integer>(); // start positions of s1
        for (int i = 1; i < suffixes.size(); i++) {
            Suffix sfx1 = suffixes.get(i - 1);
            Suffix sfx2 = suffixes.get(i);
            if (sfx1.id == sfx2.id) {
                continue;
            }
            int len = lcp(sfx1, sfx2);
            if (len > maxLen) {
                maxLen = len;
                starts.clear();
                starts.add(sfx1.id == 1 ? sfx1.start : sfx2.start);
            } else if (len == maxLen) {
                starts.add(sfx1.id == 1 ? sfx1.start : sfx2.start);
            }
        }

        Set<String> result = new HashSet<String>(starts.size());
        for (int start : starts) {
            result.add(s1.substring(start, start + maxLen));
        }

        return result;
    }

    private static int lcp(Suffix sfx1, Suffix sfx2) {
        int len = 0;

        for (int i1 = sfx1.start, i2 = sfx2.start; i1 < sfx1.s.length() && i2 < sfx2.s.length(); i1++, i2++) {
            if (sfx1.s.charAt(i1) != sfx2.s.charAt(i2)) {
                break;
            }
            len++;
        }

        return len;
    }

    private static class Suffix {
        public final String s;
        public final int    start;
        public final int    id;

        public Suffix(String s, int start, int id) {
            this.s = s;
            this.start = start;
            this.id = id;
        }
    }
}

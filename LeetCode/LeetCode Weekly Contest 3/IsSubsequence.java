// https://leetcode.com/contest/3/problems/is-subsequence/

package com.htyleo.algorithms;

public class IsSubsequence {

    public static void main(String[] args){
        System.out.println(new IsSubsequence().isSubsequence("abc", "ahbgdc"));
        System.out.println(new IsSubsequence().isSubsequence("abc", "abc"));
        System.out.println(new IsSubsequence().isSubsequence("", "abc"));
        System.out.println(new IsSubsequence().isSubsequence("abcd", "abc"));
        System.out.println(new IsSubsequence().isSubsequence("axc", "ahbgdc"));
    }

    public boolean isSubsequence(String s, String t) {
        if (s.length() == 0) {
            return true;
        }

        int si = 0;
        for (int ti = 0; ti < t.length() && si < s.length(); ti++) {
            if (s.charAt(si) == t.charAt(ti)) {
                si++;
            }
        }

        return si == s.length();
    }
}

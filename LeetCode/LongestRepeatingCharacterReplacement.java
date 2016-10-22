// https://leetcode.com/problems/longest-repeating-character-replacement/

package com.htyleo.algorithms;

public class LongestRepeatingCharacterReplacement {

    // sliding window s[low, high]
    // if # of most frequest character in the window > # of other characters + k, high++
    // else, low++ high++
    //
    // The result is the max size of sliding window
    public int characterReplacement(String s, int k) {
        if (s.length() == 0) {
            return 0;
        }

        int result = 1;

        // count[i]: # of character 'A' + i;
        int[] count = new int[26];
        for (int low = 0, high = 0; high < s.length();) {
            count[s.charAt(high) - 'A']++;
            int totalCount = high - low + 1;
            int otherCount = totalCount - getMaxCount(count, s, low, high);
            if (otherCount <= k) {
                result = Math.max(result, totalCount);
                // expand the sliding window, high++
                high++;
            } else {
                // slide, low++, high++
                count[s.charAt(low) - 'A']--;
                low++;
                high++;
            }
        }

        return result;
    }

    private int getMaxCount(int[] count, String s, int low, int high) {
        int maxCnt = count[s.charAt(low) - 'A'];
        for (int i = low + 1; i <= high; i++) {
            maxCnt = Math.max(maxCnt, count[s.charAt(i) - 'A']);
        }

        return maxCnt;
    }

}

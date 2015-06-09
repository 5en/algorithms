// https://leetcode.com/problems/longest-substring-without-repeating-characters/

import java.util.HashMap;
import java.util.Map;

public class LongestSubstringWithoutRepeatingCharacters {
    // O(N) time, O(N) space
    public int lengthOfLongestSubstringWithoutRepeatingCharacters(String s) {
        int maxLen = 0;
        int currLen = 0;
        int start = 0;
        Map<Character, Integer> ch2idx = new HashMap<Character, Integer>();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch2idx.containsKey(ch)) {
                int newStart = ch2idx.get(ch) + 1;
                for (int j = start; j < newStart; j++) {
                    ch2idx.remove(s.charAt(j));
                }
                start = newStart;
                currLen = i - newStart + 1;
            } else {
                currLen++;
                maxLen = Math.max(maxLen, currLen);
            }
            
            ch2idx.put(ch, i);
        }
        
        return maxLen;
    }
}

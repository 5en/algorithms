// https://leetcode.com/problems/valid-anagram/

import java.util.HashMap;
import java.util.Map;

public class ValidAnagram {
    public static boolean isAnagram(String s, String t) {
        Map<Character, Integer> charCount = new HashMap<Character, Integer>();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            charCount.put(ch, charCount.containsKey(ch) ? charCount.get(ch) + 1 : 1);
        }

        for (int i = 0; i < t.length(); i++) {
            char ch = t.charAt(i);
            if (!charCount.containsKey(ch)) {
                return false;
            }
            if (charCount.get(ch) == 1) {
                charCount.remove(ch);
            } else {
                charCount.put(ch, charCount.get(ch) - 1);
            }
        }

        return charCount.isEmpty();
    }
}

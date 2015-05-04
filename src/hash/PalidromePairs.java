package hash;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class PalidromePairs {
    public static void main(String[] args) {
        List<String> words = new ArrayList<String>();
        words.add("abc");
        words.add("cba");
        words.add("a");
        words.add("a");
        words.add("abcba");

        Set<String> pairs = find(words);
        for (String pair : pairs) {
            System.out.println(pair);
        }
    }

    // only consider lower case
    public static Set<String> find(List<String> words) {
        Set<String> rs = new HashSet<String>(); // reversed words
        Map<String, Integer> countMap = new HashMap<String, Integer>(); // count

        for (String word : words) {
            rs.add(new StringBuilder(word).reverse().toString());
            countMap.put(word, countMap.containsKey(word) ? countMap.get(word) + 1 : 1);
        }

        Set<String> result = new HashSet<String>();
        for (String word : words) {
            if (rs.contains(word)) {
                if (!isPalindrome(word)) {
                    result.add(word);
                } else if (countMap.get(word) >= 2) {
                    result.add(word);
                }
            }
        }

        return result;
    }

    private static boolean isPalindrome(String s) {
        return s.equalsIgnoreCase(new StringBuilder(s).reverse().toString());
    }
}

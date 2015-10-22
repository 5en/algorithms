package dfs;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class StringToWords {
    public static void main(String[] args) {
        Set<String> dict = new HashSet<String>();
        dict.add("hi");
        dict.add("ask");
        dict.add("him");
        dict.add("to");
        dict.add("do");

        System.out.println(find(dict, "askhimtodo"));
    }

    // O(2^N)
    // T(N) = T(N-1) + T(N-2) + ... T(1)
    public static List<String> find(Set<String> dict, String s) {
        if (s.length() == 0) {
            return new LinkedList<String>();
        }

        for (int i = 0; i < s.length(); i++) {
            String word = s.substring(0, i + 1); // s[0...i]
            if (!dict.contains(word)) {
                continue;
            }
            List<String> words = find(dict, s.substring(i + 1));
            if (words != null) {
                words.add(0, word);
                return words;
            }
        }

        return null;
    }
}

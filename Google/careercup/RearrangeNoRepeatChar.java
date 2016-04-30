// Rearrange characters in a string so that no character repeats twice.
//        Input: aaabc
//        Output: abaca
//
//        Input: aa
//        Output: No valid output
//
//        Input: aaaabc
//        Output: No valid output

import java.util.HashMap;
import java.util.Map;

public class RearrangeNoRepeatChar {

    public static void main(String[] args) {
        System.out.println(rearrange("aaabc"));
        System.out.println(rearrange("aaabcd"));
        System.out.println(rearrange("a"));
        System.out.println(rearrange("aa"));
        System.out.println(rearrange("aaaabc"));
    }

    // O(N)
    public static String rearrange(String s) {
        int N = s.length();

        Map<Character, Integer> chCount = new HashMap<Character, Integer>();
        for (int i = 0; i < N; i++) {
            char ch = s.charAt(i);
            chCount.put(ch, chCount.containsKey(ch) ? chCount.get(ch) + 1 : 1);
        }

        char maxCountChar = 0;
        int maxCount = 0;
        for (Map.Entry<Character, Integer> chCountEntry : chCount.entrySet()) {
            char ch = chCountEntry.getKey();
            int count = chCountEntry.getValue();
            if (count > maxCount) {
                maxCount = count;
                maxCountChar = ch;
            }
        }

        if (maxCount > Math.ceil(((double) N) / 2)) {
            return "No valid output";
        }

        StringBuilder sb = new StringBuilder(s);
        int chi = 0;
        // firstly set maxCountChar at 0, 2, 4, ...
        for (int i = 0; i < maxCount; i++, chi += 2) {
            sb.setCharAt(chi, maxCountChar);
        }
        chCount.remove(maxCountChar);
        // set rest of chars
        for (Map.Entry<Character, Integer> chCountEntry : chCount.entrySet()) {
            char ch = chCountEntry.getKey();
            int count = chCountEntry.getValue();
            for (int i = 0; i < count; i++, chi += 2) {
                if (chi >= N) {
                    chi = 1;
                }

                sb.setCharAt(chi, ch);
            }
        }

        return sb.toString();
    }
}

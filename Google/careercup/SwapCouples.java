// There are several people sitting in the cinema, some of them are couples, some are not,
// they decide to swap their seats so that the couples can seat together,
// please calculate the minimal swap numbers.
//        1. the swap can happen between any two position.
//        2. E.g. AABCCDB -> AADCCBB, ans is 1
//        3. E.g. CABBADDCEETK -> AABBCDDCEETK -> AABBDDCCEETK, ans is 2

import java.util.HashMap;
import java.util.Map;

public class SwapCouples {

    public static void main(String[] args) {
        Result result1 = swap("AABCCDB");
        System.out.println(result1.minSwap + " " + result1.swaped);

        Result result2 = swap("CABBADDCEETK");
        System.out.println(result2.minSwap + " " + result2.swaped);
    }

    public static Result swap(String s) {
        Map<Character, Integer> chCount = new HashMap<Character, Integer>();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            chCount.put(ch, chCount.containsKey(ch) ? chCount.get(ch) + 1 : 1);
        }
        int numCouples = 0;
        for (int count : chCount.values()) {
            if (count == 2) {
                numCouples++;
            }
        }

        // upperbound: # of couples
        Result result = new Result();
        result.minSwap = numCouples;

        // dfs
        swapSub(result, chCount, new StringBuilder(s), 0, 0);

        return result;
    }

    private static void swapSub(Result result, Map<Character, Integer> chCount, StringBuilder sb,
                                int start, int numSwap) {
        if (numSwap >= result.minSwap) {
            return;
        }

        if (isValid(sb.toString(), chCount)) {
            result.minSwap = numSwap;
            result.swaped = sb.toString();
            return;
        }

        if (start >= sb.length() - 1) {
            return;
        }

        swapSub(result, chCount, sb, start + 1, numSwap);

        for (int i = start + 1; i < sb.length(); i++) {
            // swap s[start] and s[i]
            char tmp = sb.charAt(start);
            sb.setCharAt(start, sb.charAt(i));
            sb.setCharAt(i, tmp);

            swapSub(result, chCount, sb, start + 1, numSwap + 1);

            // swap s[start] and s[i]
            sb.setCharAt(i, sb.charAt(start));
            sb.setCharAt(start, tmp);
        }
    }

    private static boolean isValid(String s, Map<Character, Integer> chCount) {
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (chCount.get(ch) != 2) {
                continue;
            }

            if (i > 0 && s.charAt(i - 1) == ch) {
                continue;
            }
            if (i + 1 < s.length() && s.charAt(i + 1) == ch) {
                continue;
            }

            return false;
        }

        return true;
    }

    private static class Result {
        int    minSwap;
        String swaped;
    }
}

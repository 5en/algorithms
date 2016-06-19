// Given a string consisting of a,b and c's, we can perform the following operation: 
// Take any two adjacent distinct characters and replace it with the third character. 
// For example, if 'a' and 'c' are adjacent, they can replaced with 'b'. 
// What is the smallest string which can result by applying this operation repeatedly?

// http://www.cnblogs.com/ider/archive/2012/04/13/analysis_of_string_reduction.html

package interview;

import java.util.HashMap;
import java.util.Map;

public class StringReduction {
    public static void main(String[] args) {
        System.out.println(reduce("cab"));
        System.out.println(reduce("bcab"));
        System.out.println(reduce("ccccc"));
        System.out.println(reduce("bcabbcabbcabb"));

        System.out.println();

        System.out.println(reduce2("cab"));
        System.out.println(reduce2("bcab"));
        System.out.println(reduce2("ccccc"));
        System.out.println(reduce2("bcabbcabbcabb"));
    }

    public static int reduce(String s) {
        // count
        int a = 0;
        int b = 0;
        int c = 0;

        for (char ch : s.toCharArray()) {
            switch (ch) {
                case 'a':
                    a++;
                    break;
                case 'b':
                    b++;
                    break;
                case 'c':
                    c++;
                    break;
                default:
                    throw new RuntimeException("Invalid input");
            }
        }

        // only one type of characters
        if (a == 0 && b == 0) {
            return c;
        } else if (a == 0 && c == 0) {
            return b;
        } else if (b == 0 && c == 0) {
            return a;
        }

        if ((isEven(a) && isEven(b) && isEven(c)) || (!isEven(a) && !isEven(b) && !isEven(c))) {
            return 2;
        }

        return 1;
    }

    private static boolean isEven(int n) {
        return n % 2 == 0;
    }

    // DFS
    public static int reduce2(String s) {
        Map<String, Integer> memo = new HashMap<String, Integer>();

        return reduce2SR(new StringBuilder(s), memo);
    }

    private static int reduce2SR(StringBuilder sb, Map<String, Integer> memo) {
        String s = sb.toString();
        if (memo.containsKey(s)) {
            return memo.get(s);
        }

        int minLen = s.length();

        for (int i = 0; i < s.length() - 1; i++) {
            String replacement;

            switch (s.substring(i, i + 2)) {
                case "ab":
                case "ba":
                    replacement = "c";
                    break;
                case "ac":
                case "ca":
                    replacement = "b";
                    break;
                case "bc":
                case "cb":
                    replacement = "a";
                    break;
                default:
                    continue;
            }

            String tmp = sb.substring(i, i + 2);
            sb.replace(i, i + 2, replacement);

            int len = reduce2SR(sb, memo);
            if (len < minLen) {
                minLen = len;
            }

            // back track
            sb.replace(i, i + 1, tmp);
        }

        memo.put(s, minLen);

        return minLen;
    }
}

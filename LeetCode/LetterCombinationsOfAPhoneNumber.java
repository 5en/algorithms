// https://leetcode.com/problems/letter-combinations-of-a-phone-number/

import java.util.ArrayList;
import java.util.List;

public class LetterCombinationsOfAPhoneNumber {
    public static List<String> letterCombinations(String digits) {
        if (digits == null || digits.length() == 0) {
            return new ArrayList<String>();
        }

        char[][] map = {
                {}, // 0
                {}, // 1
                {'a', 'b', 'c'}, // 2
                {'d', 'e', 'f'}, // 3
                {'g', 'h', 'i'}, // 4
                {'j', 'k', 'l'}, // 5
                {'m', 'n', 'o'}, // 6
                {'p', 'q', 'r', 's'}, // 7
                {'t', 'u', 'v'}, // 8
                {'w', 'x', 'y', 'z'}, //9
        };

        List<String> result = new ArrayList<String>();
        StringBuilder curr = new StringBuilder();
        dfs(map, digits, 0, curr, result);

        return result;
    }

    private static void dfs(char[][] map, String digits, int start, StringBuilder curr, List<String> result) {
        if (start == digits.length()) {
            result.add(curr.toString());
            return;
        }

        for (char ch : map[digits.charAt(start) - '0']) {
            curr.append(ch);
            dfs(map, digits, start+1, curr, result);
            curr.deleteCharAt(curr.length()-1);
        }
    }
}

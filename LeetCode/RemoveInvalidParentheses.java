// https://leetcode.com/problems/remove-invalid-parentheses/

package com.htyleo.algorithms;

import java.util.ArrayList;
import java.util.List;

public class RemoveInvalidParentheses {
    public static void main(String[] args) {
        RemoveInvalidParentheses m = new RemoveInvalidParentheses();
        List<String> res = m.removeInvalidParentheses(")()(e()))))))((((");
        System.out.println(res);
    }

    // search tree with K leaves (K is the # of answers)
    public List<String> removeInvalidParentheses(String s) {
        List<String> result = new ArrayList<String>();
        removeInvalidParenthesesSub(result, s, 0, 0, ')');
        return result;
    }

    // first round: CHAR_KEEP = '(', CHAR_REMOVE = ')'
    // second round: CHAR_KEEP = ')', CHAR_REMOVE = '(', reverse s
    private void removeInvalidParenthesesSub(List<String> res, String s, int start, int rmStart,
                                             char CHAR_REMOVE) {
        char CHAR_KEEP = CHAR_REMOVE == ')' ? '(' : ')';

        int stack = 0;
        for (int i = start; i < s.length(); i++) {
            if (s.charAt(i) == CHAR_KEEP) {
                stack++;
            } else if (s.charAt(i) == CHAR_REMOVE) {
                stack--;
            }

            if (stack >= 0) {
                continue;
            }

            // we find invalid prefix
            // remove each CHAR_REMOVE visited before
            // remove the first one when there is consecutive CHAR_REMOVE to avoid duplicates
            //
            // s[0...r-1][r][r+1,...] => s[0...r-1][r+1,...]
            for (int r = rmStart; r <= i; r++) {
                if (s.charAt(r) == CHAR_REMOVE && (r == rmStart || s.charAt(r - 1) != CHAR_REMOVE)) {
                    removeInvalidParenthesesSub(res,
                        s.substring(0, r) + s.substring(r + 1, s.length()), i, r, CHAR_REMOVE);
                }
            }

            return;
        }

        String reversed = new StringBuilder(s).reverse().toString();
        if (CHAR_REMOVE == ')') {
            removeInvalidParenthesesSub(res, reversed, 0, 0, '(');
            return;
        }
        res.add(reversed);
    }
}

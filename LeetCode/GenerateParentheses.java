// https://leetcode.com/problems/generate-parentheses/

package com.htyleo.algorithms;

import java.util.ArrayList;
import java.util.List;

public class GenerateParentheses {
    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<String>();
        generateParenthesisSR(result, "", 0, 0, n);
        return result;
    }

    private void generateParenthesisSR(List<String> result, String s, int lCount, int rCount, int n) {
        if (rCount == n) {
            // all the parantheses has been assigned
            result.add(s);
            return;
        }

        if (lCount < n) {
            generateParenthesisSR(result, s + "(", lCount + 1, rCount, n);
        }

        // invariant: # of "(" >= # of ")"
        if (lCount > rCount) {
            generateParenthesisSR(result, s + ")", lCount, rCount + 1, n);
        }
    }
}

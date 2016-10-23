// https://leetcode.com/contest/10/problems/ternary-expression-parser/

public class TernaryExpressionParser {

    public String parseTernary(String expression) {

        if (expression.length() == 1) {
            return expression;
        }

        char condition = expression.charAt(0);

        int colonIdx = 0;
        for (int i = 2, numQues = 0, numColons = 0; i < expression.length(); i++) {
            char ch = expression.charAt(i);
            if (ch == '?') {
                numQues++;
            } else if (ch == ':') {
                if (numQues == numColons) {
                    colonIdx = i;
                    break;
                } else {
                    numColons++;
                }
            }
        }

        String expr1 = expression.substring(2, colonIdx);
        String expr2 = expression.substring(colonIdx + 1);
        return condition == 'T' ? parseTernary(expr1) : parseTernary(expr2);
    }

}

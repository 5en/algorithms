// https://leetcode.com/problems/basic-calculator-ii/

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class BasicCalculatorII {
    public static int calculate(String s) {
        List<Token> tokens = parseTokens(s);
        List<Token> tokensPostFix = toPostFix(tokens);
        return eval(tokensPostFix);
    }

    private static List<Token> parseTokens(String s) {
        List<Token> tokens = new LinkedList<Token>();

        int num = -1;
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch >= '0' && ch <= '9') {
                // ch is a digit
                int digit = ch - '0';
                num = (num == -1 ? digit : num * 10 + digit);
                continue;
            }

            // ch is space or operator
            if (num != -1) {
                tokens.add(new Num(num));
                num = -1;
            }

            if (Op.isOp(ch)) {
                // ch is an operator
                tokens.add(new Op(ch));
            }
        }

        if (num != -1) {
            tokens.add(new Num(num));
        }

        return tokens;
    }

    private static List<Token> toPostFix(List<Token> tokens) {
        List<Token> result = new LinkedList<Token>();
        Deque<Op> stack = new LinkedList<Op>();
        for (Token token : tokens) {
            if (token.getClass() == Num.class) {
                result.add(token);
                continue;
            }

            // token is an operator
            Op op = (Op)token;
            while (!stack.isEmpty() && !op.higherPriority(stack.peek())) {
                result.add(stack.pop());
            }
            stack.push(op);
        }

        while (!stack.isEmpty()) {
            result.add(stack.pop());
        }

        return result;
    }

    private static int eval(List<Token> tokensPostFix) {
        Deque<Num> stack = new LinkedList<Num>();
        for (Token token : tokensPostFix) {
            if (token.getClass() == Num.class) {
                stack.push((Num)token);
                continue;
            }

            // token is an operator
            Op op = (Op)token;
            Num num2 = stack.pop();
            Num num1 = stack.pop();
            int result = 0;
            switch (op.symbol) {
                case '+':
                    result = num1.val + num2.val;
                    break;
                case '-':
                    result = num1.val - num2.val;
                    break;
                case '*':
                    result = num1.val * num2.val;
                    break;
                case '/':
                    result = num1.val / num2.val;
                    break;
                default:
                    throw new RuntimeException("Invalid operator");
            }
            stack.push(new Num(result));
        }

        return stack.pop().val;
    }

    private static abstract class Token {}

    private static class Num extends Token {
        public final int val;

        public Num(int val) {
            this.val = val;
        }

        @Override
        public String toString() {
            return Integer.toString(val);
        }
    }

    private static class Op extends Token {
        public final char symbol;

        public Op(char symbol) {
            if (!isOp(symbol)) {
                throw new RuntimeException("Invalid symbol");
            }

            this.symbol = symbol;
        }

        public boolean higherPriority(Op op) {
            return (symbol == '*' || symbol == '/') && (op.symbol == '+' || op.symbol == '-');
        }

        public static boolean isOp(char ch) {
            return ch == '+' || ch == '-' || ch == '*' || ch == '/';
        }

        @Override
        public String toString() {
            return Character.toString(symbol);
        }
    }
}

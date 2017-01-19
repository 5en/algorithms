// https://leetcode.com/problems/evaluate-reverse-polish-notation/

import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

/**
 * Created by htyleo on 1/18/17.
 */
public class EvaluateReversePolishNotation {

    public int evalRPN(String[] tokens) {
        Set<String> operators = new HashSet<>();
        operators.add("+");
        operators.add("-");
        operators.add("*");
        operators.add("/");

        Deque<String> stack = new LinkedList<>();
        for (String token : tokens) {
            if (operators.contains(token)) {
                int num2 = Integer.parseInt(stack.pop());
                int num1 = Integer.parseInt(stack.pop());
                int result = 0;
                if (token.equals("+")) {
                    result = num1 + num2;
                } else if (token.equals("-")) {
                    result = num1 - num2;
                } else if (token.equals("*")) {
                    result = num1 * num2;
                } else if (token.equals("/")) {
                    result = num1 / num2;
                }
                stack.push(String.valueOf(result));
            } else {
                stack.push(token);
            }
        }

        return Integer.parseInt(stack.pop());
    }

}

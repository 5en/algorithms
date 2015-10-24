/*
 * RPN (Reverse Polish notation) Conversion Method
 * By hand: "Fully parenthesize-move-erase" method
 * 1. Fully parenthesize the expression
 * 2. Replace each right parenthesis by the corresponding operator
 * 3. Erase all left parentheses
 */

package com.htyleo.algorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

@SuppressWarnings("unused")
public class Postfix {
    public static void main(String[] args) {
        String expr = "5 + 5 - 5  +  (  10 * 2)  /  ( 2 +  (6 - 4) )";
        System.out.println("Result = " + calc(expr));
    }

    public static double calc(String expr) {
        List<Token> tokens = parse(expr);
        List<Token> postFixTokens = toPostfix(tokens);
        return evaluate(postFixTokens);
    }

    private static List<Token> parse(String expr) {
        expr = expr.replaceAll("\\s+", "");
        System.out.println(expr);

        String[] operands = expr.split("[\\+\\-\\*/%\\(\\)]+"); // + - * / % +(( ...
        System.out.println("operands: " + Arrays.toString(operands));

        String[] operators = expr.split("\\d+");
        System.out.println("operators: " + Arrays.toString(operators));

        List<Token> tokens = new ArrayList<Token>();
        int i = 0; // operand index
        int j = 0; // operator index
        while (i < operands.length || j < operators.length) {
            // suppose start with an operand
            if (i < operands.length) {
                tokens.add(new Operand(Double.parseDouble(operands[i])));
                i++;
            }

            if (j < operators.length) {
                while (operators[j].length() == 0) {
                    j++;
                }
                String str = operators[j];
                for (int k = 0; k < str.length(); k++) {
                    tokens.add(new Operator(str.charAt(k)));
                }
                j++;
            }
        }
        System.out.println("tokens: " + tokens);

        return tokens;
    }

    private static List<Token> toPostfix(List<Token> tokens) {
        List<Token> postFixTokens = new LinkedList<Token>();
        Deque<Operator> stack = new LinkedList<Operator>();

        for (Token token : tokens) {
            if (token.isOperand()) {
                // append operand
                postFixTokens.add(token);
            } else {
                Operator op = (Operator) token;
                if (op.isLeftParenthesis()) {
                    // (
                    stack.push(op);
                } else if (op.isRightParenthesis()) {
                    // )
                    // pop all operators between ()
                    while (true) {
                        Operator tmpOp = stack.pop();
                        if (tmpOp.isLeftParenthesis()) {
                            break;
                        } else {
                            postFixTokens.add(tmpOp);
                        }
                    }
                } else {
                    // + - * /
                    while (true) {
                        // if the stack is empty, push
                        if (stack.isEmpty()) {
                            stack.push(op);
                            break;
                        }

                        // if the stack.peek() is "(" or has lower priority than op, push
                        Operator top = stack.peek();
                        if (top.isLeftParenthesis() || op.higherPriority(top)) {
                            stack.push(op);
                            break;
                        }

                        // else, pop operators that has higher or equal priority than op, then push
                        postFixTokens.add(stack.pop());
                    }
                }
            }
        }

        while (!stack.isEmpty()) {
            postFixTokens.add(stack.pop());
        }

        System.out.println("postfix: " + postFixTokens);

        return postFixTokens;
    }

    private static double evaluate(List<Token> postFixTokens) {
        // 1. Initialize an empty stack
        // 2. Repeat the following until the end of the expression is encountered
        //   Get the next token (const, var, operator) in the expression
        //     Operand – push onto stack
        //     Operator – do the following
        //       Pop 2 values from stack
        //       Apply operator to the two values
        //       Push resulting value back onto stack
        // 3. When end of expression encountered, value of expression is the only number left in stack;
        //    otherwise the expression is in error.

        Deque<Operand> stack = new LinkedList<Operand>();

        for (Token token : postFixTokens) {
            if (token.isOperand()) {
                stack.push((Operand) token);
            } else {
                Operator op = (Operator) token;
                Operand operand2 = stack.pop();
                Operand operand1 = stack.pop();
                stack.push(op.eval(operand1, operand2));
            }
        }

        if (stack.size() != 1) {
            throw new RuntimeException("Invalid Expression");
        }

        return stack.pop().val;
    }

    private static abstract class Token {
        public boolean isOperand() {
            return this.getClass() == Operand.class;
        }

        public boolean isOperator() {
            return this.getClass() == Operator.class;
        }
    }

    private static class Operand extends Token {
        public final double val;

        public Operand(double val) {
            this.val = val;
        }

        @Override
        public String toString() {
            return String.valueOf(val);
        }
    }

    private static class Operator extends Token {
        public final OP opVal;

        public Operator(char op) {
            switch (op) {
                case '+':
                    this.opVal = Operator.OP.ADD;
                    break;
                case '-':
                    this.opVal = Operator.OP.SUBTRACT;
                    break;
                case '*':
                    this.opVal = Operator.OP.MULTIPLY;
                    break;
                case '/':
                    this.opVal = Operator.OP.DIVIDE;
                    break;
                case '(':
                    this.opVal = Operator.OP.LEFT_PARENTHESIS;
                    break;
                case ')':
                    this.opVal = Operator.OP.RIGHT_PARENTHESIS;
                    break;
                default:
                    throw new IllegalArgumentException("Invalid Operator");
            }
        }

        public int priority() {
            int result = 0;

            switch (opVal) {
                case ADD:
                    result = 1;
                    break;
                case SUBTRACT:
                    result = 1;
                    break;
                case MULTIPLY:
                    result = 2;
                    break;
                case DIVIDE:
                    result = 2;
                    break;
                default:
                    throw new IllegalArgumentException("Invalid Operator");
            }

            return result;
        }

        public boolean higherPriority(Operator other) {
            return (this.priority() - other.priority()) > 0 ? true : false;
        }

        public boolean isLeftParenthesis() {
            return opVal == OP.LEFT_PARENTHESIS;
        }

        public boolean isRightParenthesis() {
            return opVal == OP.RIGHT_PARENTHESIS;
        }

        public Operand eval(Operand operand1, Operand operand2) {
            double result = 0;

            switch (opVal) {
                case ADD:
                    result = operand1.val + operand2.val;
                    break;
                case SUBTRACT:
                    result = operand1.val - operand2.val;
                    break;
                case MULTIPLY:
                    result = operand1.val * operand2.val;
                    break;
                case DIVIDE:
                    result = operand1.val / operand2.val;
                    break;
                default:
                    throw new IllegalArgumentException("Invalid Operator");
            }

            return new Operand(result);
        }

        @Override
        public String toString() {
            String result = null;

            switch (opVal) {
                case ADD:
                    result = "+";
                    break;
                case SUBTRACT:
                    result = "-";
                    break;
                case MULTIPLY:
                    result = "*";
                    break;
                case DIVIDE:
                    result = "/";
                    break;
                case LEFT_PARENTHESIS:
                    result = "(";
                    break;
                case RIGHT_PARENTHESIS:
                    result = ")";
                    break;
                default:
                    throw new IllegalArgumentException("Invalid Operator");
            }

            return result;
        }

        public static enum OP {
            ADD, SUBTRACT, MULTIPLY, DIVIDE, LEFT_PARENTHESIS, RIGHT_PARENTHESIS
        }
    }
}

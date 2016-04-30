// Given a string s of parentheses (ex: “(()”)
// return the minimum number of parentheses that need to be inserted to make it into a well formed string

public class BalancedParentheses {

    public static void main(String[] args) {
        System.out.println(minNumInsertionsForBalancing("(()())"));
        System.out.println(minNumInsertionsForBalancing("(()"));
        System.out.println(minNumInsertionsForBalancing(")))((("));
    }

    public static int minNumInsertionsForBalancing(String s) {
        int result = 0;
        int stack = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack++;
            } else if (s.charAt(i) == ')') {
                stack--;
            }

            if (stack < 0) {
                result++;
                stack = 0;
            }
        }

        result += stack;

        return result;
    }
}

// https://leetcode.com/contest/smarking-algorithm-contest-4/problems/repeated-substring-pattern/

public class RepeatedSubstringPattern {

    public boolean repeatedSubstringPattern(String str) {
        for (int len = 1; len <= str.length() / 2; len++) {
            if (str.length() % len != 0) {
                continue;
            }

            if (verify(str, len)) {
                return true;
            }
        }

        return false;
    }

    private boolean verify(String str, int len) {
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) != str.charAt(i % len)) {
                return false;
            }
        }

        return true;
    }

}

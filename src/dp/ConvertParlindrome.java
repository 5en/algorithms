// http://stackoverflow.com/questions/2237021/how-can-i-compute-the-number-of-characters-required-to-turn-a-string-into-a-pali

package dp;

public class ConvertParlindrome {
    public static void main(String[] args) {
        System.out.println(convert("abcd"));
        System.out.println(convert2("abcd"));

        System.out.println(convert("abcbd"));
        System.out.println(convert2("abcbd"));
    }

    // O(N^3)
    // return the minimum # of characters that must be inserted in a string to turn it into a palindrome.
    public static int convert(String s) {
        int minCount = Integer.MAX_VALUE;

        int N = s.length();
        for (int n = 0; n <= 2 * N; n++) {
            String[] parts = partition(s, n);
            String s1 = parts[0];
            String s2 = new StringBuilder(parts[1]).reverse().toString();
            int count = s1.length() + s2.length() - lcs(s1, s2) * 2;
            if (count < minCount) {
                minCount = count;
            }
        }

        return minCount;
    }

    // O(N^3)
    // return the minimum # of characters that must be inserted/deleted/modified in a string to turn it into a palindrome.
    public static int convert2(String s) {
        int minCount = Integer.MAX_VALUE;

        int N = s.length();
        for (int n = 0; n <= 2 * N; n++) {
            String[] parts = partition(s, n);
            String s1 = parts[0];
            String s2 = new StringBuilder(parts[1]).reverse().toString();
            int count = minDiff(s1, s2);
            if (count < minCount) {
                minCount = count;
            }
        }

        return minCount;
    }

    // 0   1     2   3      4     5     …   2n-3     2n-2   2n-1     2n
    //     s[0]      c[1]         c[2]  …   c[n-2]          c[n-1]
    private static String[] partition(String s, int n) {
        String s1 = null;
        String s2 = null;

        if (n % 2 == 0) {
            s1 = s.substring(0, n / 2);
            s2 = s.substring(n / 2);
        } else {
            s1 = s.substring(0, n / 2);
            s2 = s.substring(n / 2 + 1);
        }

        return new String[]{s1, s2};
    }

    // O(N^2)
    // longest common subsequence
    private static int lcs(String s1, String s2) {
        if (s1.length() == 0 || s2.length() == 0) {
            return 0;
        }

        int maxLen = 0;

        int M = s1.length();
        int N = s2.length();

        int[][] L = new int[M][N]; // length of LCS ending at s1[i] s2[j]
        for (int i = 0; i < M; i++) {
            L[i][0] = 0;
        }
        for (int j = 0; j < N; j++) {
            L[0][j] = 0;
        }
        for (int i = 1; i < M; i++) {
            for (int j = 1; j < N; j++) {
                if (s1.charAt(i) == s2.charAt(j)) {
                    L[i][j] = L[i - 1][j - 1] + 1;
                    if (L[i][j] > maxLen) {
                        maxLen = L[i][j];
                    }
                } else {
                    L[i][j] = Math.max(L[i - 1][j], L[i][j - 1]);
                }
            }
        }

        return maxLen;
    }

    // O(N^2)
    private static int minDiff(String s1, String s2) {
        if (s1.length() == 0 || s2.length() == 0) {
            return s1.length() == 0 ? s2.length() : s1.length();
        }

        int M = s1.length();
        int N = s2.length();

        int[][] diff = new int[M][N]; // min diff of s1[0...i] and s2[0...j]
        for (int i = 0; i < M; i++) {
            diff[i][0] = i + 1;
        }
        for (int j = 0; j < N; j++) {
            diff[0][j] = j + 1;
        }
        for (int i = 1; i < M; i++) {
            for (int j = 1; j < N; j++) {
                if (s1.charAt(i) == s2.charAt(j)) {
                    diff[i][j] = diff[i - 1][j - 1];
                } else {
                    int diff1 = 1 + diff[i - 1][j - 1];
                    int diff2 = 1 + diff[i][j - 1];
                    int diff3 = 1 + diff[i - 1][j];
                    diff[i][j] = min(diff1, diff2, diff3);
                }
            }
        }

        return diff[M - 1][N - 1];
    }

    private static int min(int... a) {
        int min = Integer.MAX_VALUE;

        for (int i : a) {
            if (i < min) {
                min = i;
            }
        }

        return min;
    }
}

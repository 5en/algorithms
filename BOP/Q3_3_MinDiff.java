public class Q3_3_MinDiff {
    public static void main(String[] args) {
        System.out.println(diff("abba", "abb"));
        System.out.println(diff("abba", "adbab"));
        System.out.println(diff("abba", "abcba"));
    }

    public static int diff(String s1, String s2) {
        int M = s1.length();
        int N = s2.length();

        if (M == 0 || N == 0) {
            return M == 0 ? N : M;
        }

        // diffs[i][j]: diff between s1[0...i-1] (length i) and s2[0...j-1] (length j)
        // -1 indicates unsolved
        int[][] diffs = new int[M + 1][N + 1];
        for (int i = 0; i <= M; i++) {
            for (int j = 0; j <= N; j++) {
                if (i == 0 || j == 0) {
                    diffs[i][j] = i == 0 ? j : i;
                } else {
                    diffs[i][j] = -1;
                }
            }
        }

        return diffSR(s1, M, s2, N, diffs);
    }

    // diffs[i][j] = 
    // diff[i-1][j-1], if s1[i-1] == s2[j-1]
    // 1 + min {diff[i-1][j], diff[i][j-1], diff[i-1][j-1]}, else
    private static int diffSR(String s1, int end1, String s2, int end2, int[][] diffs) {
        if (diffs[end1][end2] == -1) {
            if (s1.charAt(end1 - 1) == s2.charAt(end2 - 1)) {
                diffs[end1][end2] = diffSR(s1, end1 - 1, s2, end2 - 1, diffs);
            } else {
                int diff1 = diffSR(s1, end1 - 1, s2, end2, diffs);
                int diff2 = diffSR(s1, end1, s2, end2 - 1, diffs);
                int diff3 = diffSR(s1, end1 - 1, s2, end2 - 1, diffs);
                diffs[end1][end2] = min(diff1, diff2, diff3) + 1;
            }
        }

        return diffs[end1][end2];
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

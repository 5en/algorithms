// You are given two arrays of length M and N having elements in range 0-9.
// Your task is to create maximum number of length K from elements of these two arrays such that relative order of elements is same in the final number as in the array, they are taken from i.e.
// If two elements a,b are taken from array1 and and a comes before b in array1 so in the final number a should come before b (Relative order kept same). 
//        Example: N=4 and M =6
//        Array1 = {3, 4, 6, 5}
//        Array2 = {9, 1, 2, 5, 8, 3}
//        Suppose K = 5, then number will be {9,8,6,5,3}
//        You can see {9,8,3} are taken from array2 in the same order as they are in Array2.
//        Similarly {6,5} are taken from Array1 in the same order and number 98653 is maximum possible number.

package com.htyleo.algorithms;

public class MaxNumCombinationTwoArrays {

    public static void main(String[] args) {
        System.out.println(maxSum(new int[] { 3, 4, 6, 5 }, new int[] { 9, 1, 2, 5, 8, 3 }, 5));
    }

    public static int maxSum(int[] a, int[] b, int K) {
        int N = a.length;
        int M = b.length;

        // dp[n][m][k] is the result for (n=0...N-1, m=0...M-1, k=1...K)
        // a[n...N-1]
        // b[m...M-1]
        // k
        //
        // dp[n][m][k] = max{ dp[n][m+1][k], dp[n+1][m][k], a[n]~dp[n+1][m][k-1], b[m]~dp[n][m+1][k-1] }
        int[][][] dp = new int[N + 1][M + 1][K + 1];

        for (int k = 1; k <= K; k++) {
            for (int n = N; n >= 0; n--) {
                for (int m = M; m >= 0; m--) {
                    int opt = dp[n][m][k];
                    if (n < N) {
                        opt = Math.max(opt, dp[n + 1][m][k]);
                        opt = Math.max(opt,
                            (int) (Math.pow(10, k - 1) * a[n] + dp[n + 1][m][k - 1]));
                    }
                    if (m < M) {
                        opt = Math.max(opt, dp[n][m + 1][k]);
                        opt = Math.max(opt,
                            (int) (Math.pow(10, k - 1) * b[m] + dp[n][m + 1][k - 1]));
                    }
                    dp[n][m][k] = opt;
                }
            }
        }

        return dp[0][0][K];
    }
}

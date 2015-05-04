public class Q2_15_MaxSubarraySum2D {
    public static void main(String[] args) {
        // 2  3  5
        // 3 -4  5
        //-3  0 -2
        int[][] a = {{2, 3, 5}, {3, -4, 5}, {-3, 0, -2}}; // (0,0) ~ (1,2): 14

        Quintuple q = maxSA2D(a);
        System.out.printf("(%d %d) ~ (%d %d) : %d\n", q.rowmin, q.colmin, q.rowmax, q.colmax, q.sum);

        Quintuple q2 = maxSA2D2(a);
        System.out.printf("(%d %d) ~ (%d %d) : %d\n", q2.rowmin, q2.colmin, q2.rowmax, q2.colmax, q2.sum);
    }

    // O(M^2 * N^2)
    public static Quintuple maxSA2D(int[][] a) {
        int M = a.length;
        int N = a[0].length;

        int[][] p = partialSum(a);

        int opt_rowmin = -1;
        int opt_rowmax = -1;
        int opt_colmin = -1;
        int opt_colmax = -1;
        int max_sum = Integer.MIN_VALUE;

        for (int rowmin = 0; rowmin < M; rowmin++) {
            for (int rowmax = rowmin; rowmax < M; rowmax++) {
                for (int colmin = 0; colmin < N; colmin++) {
                    for (int colmax = colmin; colmax < N; colmax++) {
                        int sum = p[rowmax][colmax];
                        if (rowmin >= 1) {
                            sum -= p[rowmin - 1][colmax];
                        }
                        if (colmin >= 1) {
                            sum -= p[rowmax][colmin - 1];
                        }
                        if (rowmin >= 1 && colmin >= 1) {
                            sum += p[rowmin - 1][colmin - 1];
                        }

                        if (sum > max_sum) {
                            max_sum = sum;
                            opt_rowmin = rowmin;
                            opt_rowmax = rowmax;
                            opt_colmin = colmin;
                            opt_colmax = colmax;
                        }
                    }
                }
            }
        }

        return new Quintuple(opt_rowmin, opt_rowmax, opt_colmin, opt_colmax, max_sum);
    }

    // O(M * N * min(M,N))
    public static Quintuple maxSA2D2(int[][] a) {
        int M = a.length;
        int N = a[0].length;

        int[][] p = partialSum(a);

        int opt_rowmin = -1;
        int opt_rowmax = -1;
        int opt_colmin = -1;
        int opt_colmax = -1;
        int max_sum = Integer.MIN_VALUE;

        if (M <= N) {
            int[] tmp = new int[N]; // tmp[col] = sum(a[rowmin][n],...,a[rowmax][n])

            for (int rowmin = 0; rowmin < M; rowmin++) {
                for (int rowmax = rowmin; rowmax < M; rowmax++) {
                    for (int col = 0; col < N; col++) {
                        tmp[col] = p[rowmax][col];
                        if (rowmin >= 1) {
                            tmp[col] -= p[rowmin - 1][col];
                        }
                        if (col >= 1) {
                            tmp[col] -= p[rowmax][col - 1];
                        }
                        if (rowmin >= 1 && col >= 1) {
                            tmp[col] += p[rowmin - 1][col - 1];
                        }
                    }

                    Q2_14_MaxSubarraySum.Triple t = Q2_14_MaxSubarraySum.msa(tmp);
                    if (t.sum > max_sum) {
                        max_sum = t.sum;
                        opt_rowmin = rowmin;
                        opt_rowmax = rowmax;
                        opt_colmin = t.left;
                        opt_colmax = t.right;
                    }
                }
            }
        } else {
            // M > N
            int[] tmp = new int[M]; // tmp[row] = sum(a[row][colmin],...,a[row][colmax])

            for (int colmin = 0; colmin < N; colmin++) {
                for (int colmax = colmin; colmax < N; colmax++) {
                    for (int row = 0; row < M; row++) {
                        tmp[row] = p[row][colmax];
                        if (row >= 1) {
                            tmp[row] -= p[row - 1][colmax];
                        }
                        if (colmin >= 1) {
                            tmp[row] -= p[row][colmin - 1];
                        }
                        if (row >= 1 && colmin >= 1) {
                            tmp[row] += p[row - 1][colmin - 1];
                        }
                    }

                    Q2_14_MaxSubarraySum.Triple t = Q2_14_MaxSubarraySum.msa(tmp);
                    if (t.sum > max_sum) {
                        max_sum = t.sum;
                        opt_rowmin = t.left;
                        opt_rowmax = t.right;
                        opt_colmin = colmin;
                        opt_colmax = colmax;
                    }
                }
            }
        }

        return new Quintuple(opt_rowmin, opt_rowmax, opt_colmin, opt_colmax, max_sum);
    }

    // O(M*N)
    // p[row][col] = sum(a[0][0], a[row][col]), 0 <= row <= M, 0 <= col <= N
    private static int[][] partialSum(int[][] a) {
        int M = a.length;
        int N = a[0].length;

        int[][] p = new int[M][N];

        p[0][0] = a[0][0];
        // p[0][col]
        for (int col = 1; col < N; col++) {
            p[0][col] = p[0][col - 1] + a[0][col];
        }
        // p[row][0]
        for (int row = 1; row < M; row++) {
            p[row][0] = p[row - 1][0] + a[row][0];
        }

        // p[row][col] = p[row-1][col] + p[row][col-1] - p[row-1][col-1] + a[row][col]
        for (int row = 1; row < M; row++) {
            for (int col = 1; col < N; col++) {
                p[row][col] = p[row - 1][col] + p[row][col - 1] - p[row - 1][col - 1] + a[row][col];
            }
        }

        return p;
    }

    private static class Quintuple {
        public final int rowmin;
        public final int rowmax;
        public final int colmin;
        public final int colmax;
        public final int sum;

        public Quintuple(int rowmin, int rowmax, int colmin, int colmax, int sum) {
            this.rowmin = rowmin;
            this.rowmax = rowmax;
            this.colmin = colmin;
            this.colmax = colmax;
            this.sum = sum;
        }
    }
}

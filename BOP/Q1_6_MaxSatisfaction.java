import java.util.Arrays;

public class Q1_6_MaxSatisfaction {
    public static void main(String[] args) {
        int n = 5;
        int[] v = {5, 5, 5, 5, 5};
        int[] c = {100, 100, 100, 100, 100};
        double[] h = {0.1, 0.2, 0.3, 0.4, 0.5};
        int V = 50;

        int[][] x = maxSatisfaction(n, v, c, h, V);
        int[] xi = new int[n];
        double result = 0;
        int vi = V;
        for (int i = n - 1; i >= 0; i--) {
            xi[i] = x[vi][i];
            result += xi[i] * h[i];
            vi -= xi[i] * v[i];
        }
        System.out.println(Arrays.toString(xi));
        System.out.println(result);
    }

    // max
    //   sum(x[i] * h[i])
    // subject to
    //   sum(x[i] * v[i]) <= V
    //   x[i] <= c[i]
    private static int[][] maxSatisfaction(int n, int[] v, int[] c, double[] h, int V) {
        // opt[vi][i] is the max satisfaction given total volume vi and items 0,...,i
        // x[vi][i] is the number of items i we choose such that we achieve max satisfaction given total volume vi at items 0,..,i
        // vi = 0,1,...,V
        // i = 0,1,...,n-1
        double[][] opt = new double[V + 1][n];
        int[][] x = new int[V + 1][n];

        // memo, -1 indicates unsolved subproblem
        for (int vi = 0; vi <= V; vi++) {
            for (int i = 0; i < n; i++) {
                opt[vi][i] = -1;
            }
        }

        cal(n, v, c, h, V, opt, x, V, n - 1);

        return x;
    }

    private static double cal(int n, int[] v, int[] c, double[] h, int V, double[][] opt, int[][] x, int vi, int i) {
        // given total volume vi, items 0,1,...i
        // opt[vi][i] = max{ k*h[i] + opt[vi-k*v[i]][i-1] } subject to 0 <= k <= c[i] && vi-k*v[i] >= 0
        if (vi < 0 || i < 0) {
            return 0;
        }

        if (opt[vi][i] != -1) {
            return opt[vi][i];
        }
        
        if (vi == 0) {
            opt[vi][i] = 0;
            return 0;
        }

        double max = 0;
        for (int k = 0; k <= c[i]; k++) {
            if (k * v[i] > vi) {
                // exceed max volume
                break;
            }

            double tmp = cal(n, v, c, h, V, opt, x, (vi - k * v[i]), i - 1) + k * h[i];
            if (tmp > max) {
                max = tmp;
                x[vi][i] = k;
            }
        }
        opt[vi][i] = max;

        return max;
    }
}

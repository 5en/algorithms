import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Q2_18_BalancedHalfPartition {
    public static void main(String[] args) {
        int[] a = {1, 5, 7, 8, 9, 6, 3, 11, 20, 17};
        System.out.println(Arrays.toString(partition(a)));
        System.out.println(Arrays.toString(partition2(a)));
    }

    // O(2^(halfN))
    public static int[] partition(int[] a) {
        int N = a.length;
        int halfN = N / 2;

        // S.get(i) stores all the possible sums of arbitrary i (i=0...halfN) numbers
        List<Set<Integer>> S = new ArrayList<Set<Integer>>(halfN);
        for (int i = 0; i <= halfN; i++) {
            S.add(new HashSet<Integer>());
        }
        S.get(0).add(0);

        for (int k = 0; k < N; k++) {
            // for element a[k]
            // only need to update S(1), S(2),..., S(min(k+1, halfN))
            int max_i = Math.min(k + 1, halfN);
            for (int i = max_i; i >= 1; i--) { // the order is important
                for (int sum : S.get(i - 1)) {
                    S.get(i).add(sum + a[k]);
                }
            }
        }

        int totalSum = 0;
        for (int x : a) {
            totalSum += x;
        }
        int halfTotalSum = totalSum / 2;

        int maxSumLEHalfTotalSum = Integer.MIN_VALUE;
        for (int sum : S.get(halfN)) {
            if (sum <= halfTotalSum && sum > maxSumLEHalfTotalSum) {
                maxSumLEHalfTotalSum = sum;
            }
        }

        return new int[]{maxSumLEHalfTotalSum, totalSum - maxSumLEHalfTotalSum};
    }

    // O(N^2*halfTotalSum)
    public static int[] partition2(int[] a) {
        int N = a.length;
        int halfN = N / 2;

        int totalSum = 0;
        for (int x : a) {
            totalSum += x;
        }
        int halfTotalSum = totalSum / 2;

        // isOK[n][s] indicates whether there are n numbers whose sum == s
        boolean[][] isOK = new boolean[halfN + 1][halfTotalSum + 1];
        isOK[0][0] = true;

        for (int i = 0; i < N; i++) {
            // for element a[k]
            // only need to update isOK[1][], isOK[2][],..., isOK[(min(n+1, halfN))][]
            int max_n = Math.min(i + 1, halfN);
            for (int n = max_n; n >= 1; n--) { // the order is important
                for (int s = 0; s <= halfTotalSum; s++) {
                    if (s >= a[i] && isOK[n - 1][s - a[i]]) {
                        isOK[n][s] = true;
                    }
                }
            }
        }

        for (int v = halfTotalSum; v >= 0; v--) {
            if (isOK[halfN][v]) {
                return new int[]{v, totalSum - v};
            }
        }

        return new int[]{-1, -1};
    }
}

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class Q2_8_Num01 {
    public static void main(String[] args) {
        find(9);
        find(99);
    }

    // find the smallest X which contains only 0 and 1, such that X % N == 0
    public static void find(int N) {
        // buckets[n]: the smallest number x, which contains only 0 and 1, such that x % N == n
        // format of x: store the positions of 1s.
        List<List<Integer>> buckets = new ArrayList<List<Integer>>(N);
        for (int n = 0; n < N; n++) {
            buckets.add(new ArrayList<Integer>());
        }

        // round 1, analyze integers of length 1
        // init: 1 % N == 1
        buckets.get(1).add(0);

        int numRoundNoUpdate = 0;
        for (int i = 1, j = 10 % N;; i++, j = (j * 10) % N) {
            // round i+1, analyze integers of length i+1
            // integers of length < i+1 have been analyzed

            boolean FLAG_UPDATE = false;

            // analyze (10^i)
            // j = (10^i) % N
            if (buckets.get(j).isEmpty()) {
                buckets.get(j).add(i);
                FLAG_UPDATE = true;
            }

            for (int n = 0; n < N; n++) {
                if (!buckets.get(n).isEmpty() && buckets.get(n).get(buckets.get(n).size() - 1) < i /* ensure no repeated update in the same round */) {
                    int index = (j + n) % N;
                    if (buckets.get(index).isEmpty()) {
                        buckets.get(index).addAll(buckets.get(n));
                        buckets.get(index).add(i);
                        FLAG_UPDATE = true;
                    }
                }
            }

            if (FLAG_UPDATE) {
                numRoundNoUpdate = 0;
            } else {
                numRoundNoUpdate++;
            }

            if (numRoundNoUpdate == N || !buckets.get(0).isEmpty()) {
                break;
            }
        }

        // output
        if (buckets.get(0).isEmpty()) {
            System.out.println("M not exist");
            return;
        }

        StringBuilder sb = new StringBuilder();
        for (int j = buckets.get(0).size() - 1; j >= 0; j--) {
            sb.append('1');
            if (j != 0) {
                for (int k = 0; k < buckets.get(0).get(j) - buckets.get(0).get(j - 1) - 1; k++) {
                    sb.append('0');
                }
            }
        }
        BigInteger MN = new BigInteger(sb.toString());
        System.out.println(N + " * " + MN.divide(new BigInteger(String.valueOf(N))) + " = " + MN.toString());
    }
}

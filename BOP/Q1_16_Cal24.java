import java.util.HashMap;
import java.util.Map;
import java.util.HashSet;
import java.util.Set;
import java.util.Scanner;

public class Q1_16_Cal24 {
    public static void main(String[] args) {
        final double THRESHOLD = 1E-6;
        final int N = 4; // # of cards
        final int TARGET = 24;

        Scanner sc = new Scanner(System.in);
        int[] cards = new int[N];
        for (int i = 0; i < N; i++) {
            cards[i] = sc.nextInt();
        }
        sc.close();

        System.out.println(cal1(cards, TARGET, THRESHOLD));
        System.out.println(cal2(cards, TARGET, THRESHOLD));
    }

    // output one expression
    public static boolean cal1(int[] cards, int TARGET, double THRESHOLD) {
        int N = cards.length;
        double[] cardsCopy = new double[N];
        String[] exprs = new String[N];
        for (int i = 0; i < N; i++) {
            cardsCopy[i] = cards[i];
            exprs[i] = String.valueOf(cards[i]);
        }

        return cal(cardsCopy, exprs, N - 1, TARGET, THRESHOLD);
    }

    // Algorithm 1. try to get TARGET by constructing expressions using cards[0,...,n]
    private static boolean cal(double[] cards, String[] exprs, int n, int TARGET, double THRESHOLD) {
        if (n == 0) {
            // single card
            if (Math.abs(cards[0] - TARGET) < THRESHOLD) {
                System.out.println(exprs[0]);
                return true;
            } else {
                return false;
            }
        }

        // operate cards[n] with each of the others, next recursion on cards[0,...,n-1]

        for (int i1 = 0; i1 <= n; i1++) {
            for (int i2 = i1 + 1; i2 <= n; i2++) {
                // for every pair val1, val2
                // val1 <- val1 +(-, *, /) val2
                // val2 <- cards[n]
                double val1 = cards[i1];
                double val2 = cards[i2];
                String expr1 = exprs[i1];
                String expr2 = exprs[i2];

                cards[i2] = cards[n];
                exprs[i2] = exprs[n];

                // val1 + val2
                cards[i1] = val1 + val2;
                exprs[i1] = "(" + expr1 + " + " + expr2 + ")";
                if (cal(cards, exprs, n - 1, TARGET, THRESHOLD)) {
                    return true;
                }

                // val1 - val2
                cards[i1] = val1 - val2;
                exprs[i1] = "(" + expr1 + " - " + expr2 + ")";
                if (cal(cards, exprs, n - 1, TARGET, THRESHOLD)) {
                    return true;
                }

                // val2 - val1
                cards[i1] = val2 - val1;
                exprs[i1] = "(" + expr2 + " - " + expr1 + ")";
                if (cal(cards, exprs, n - 1, TARGET, THRESHOLD)) {
                    return true;
                }

                // val1 * val2
                cards[i1] = val1 * val2;
                exprs[i1] = "(" + expr1 + " * " + expr2 + ")";
                if (cal(cards, exprs, n - 1, TARGET, THRESHOLD)) {
                    return true;
                }

                // val1 / val2
                if (val2 != 0) {
                    cards[i1] = val1 / val2;
                    exprs[i1] = "(" + expr1 + " / " + expr2 + ")";
                    if (cal(cards, exprs, n - 1, TARGET, THRESHOLD)) {
                        return true;
                    }
                }

                // val2 / val1
                if (val1 != 0) {
                    cards[i1] = val2 / val1;
                    exprs[i1] = "(" + expr2 + " / " + expr1 + ")";
                    if (cal(cards, exprs, n - 1, TARGET, THRESHOLD)) {
                        return true;
                    }
                }

                // back track
                cards[i1] = val1;
                cards[i2] = val2;
                exprs[i1] = expr1;
                exprs[i2] = expr2;
            }
        }

        return false;
    }

    // output all expressions
    public static boolean cal2(int[] cards, int TARGET, double THRESHOLD) {
        int N = cards.length;
        double[] cardsCopy = new double[N];
        for (int i = 0; i < N; i++) {
            cardsCopy[i] = cards[i];
        }

        return cal(cardsCopy, N, TARGET, THRESHOLD);
    }

    // Algorithm 2
    public static boolean cal(double[] cards, int n, int TARGET, double THRESHOLD) {
        final int NUM_SUBSET = (int) Math.pow(2, n);

        Record[] S = new Record[NUM_SUBSET];
        for (int si = 1; si <= NUM_SUBSET - 1; si++) {
            // binary representation of i indicates the subset, except the empty subset
            // 1: 0001
            // 2: 0010
            // ...
            // 15: 1111
            S[si] = null;
        }

        // for subsets with single element (0001, 0010, 0100, 1000)
        for (int i = 0; i < n; i++) {
            int si = (int) Math.pow(2, i);
            S[si] = new Record();
            S[si].exprMap.put(cards[i], new HashSet<String>());
            S[si].exprMap.get(cards[i]).add(String.valueOf(cards[i]));
        }

        // calculate s[2^n-1] (the full set)
        calS(S, NUM_SUBSET - 1);

        // output
        for (Map.Entry<Double, Set<String>> entry : S[NUM_SUBSET - 1].exprMap.entrySet()) {
            if (Math.abs(entry.getKey() - TARGET) < THRESHOLD) {
                for (String expr : entry.getValue()) {
                    System.out.println(expr);
                }
                return true;
            }
        }

        return false;
    }

    // calculate all the values that can be obtained using the subset
    private static void calS(Record[] S, int si) {
        if (S[si] != null) {
            // computed
            return;
        }

        S[si] = new Record();
        for (int x = 1; x < si; x++) {
            if ((x & si) == x) {
                // x indicates a proper subset of si, (x, si-x) is a partition of si
                calS(S, x);
                calS(S, si - x);
                updateS(S, si, x, si - x);
            }
        }
    }

    private static void updateS(Record[] S, int si, int sub1, int sub2) {
        Map<Double, Set<String>> exprMap = S[si].exprMap;
        Map<Double, Set<String>> exprMapSub1 = S[sub1].exprMap;
        Map<Double, Set<String>> exprMapSub2 = S[sub2].exprMap;

        for (double d1 : exprMapSub1.keySet()) {
            for (double d2 : exprMapSub2.keySet()) {
                // d1 + d2
                double result = d1 + d2;
                if (!exprMap.containsKey(result)) {
                    exprMap.put((result), new HashSet<String>());
                }
                for (String expr1 : exprMapSub1.get(d1)) {
                    for (String expr2 : exprMapSub2.get(d2)) {
                        exprMap.get(result).add("(" + expr1 + " + " + expr2 + ")");
                    }
                }

                // d1 - d2
                result = d1 - d2;
                if (!exprMap.containsKey(result)) {
                    exprMap.put((result), new HashSet<String>());
                }
                for (String expr1 : exprMapSub1.get(d1)) {
                    for (String expr2 : exprMapSub2.get(d2)) {
                        exprMap.get(result).add("(" + expr1 + " - " + expr2 + ")");
                    }
                }

                // d2 - d1
                result = d2 - d1;
                if (!exprMap.containsKey(result)) {
                    exprMap.put((result), new HashSet<String>());
                }
                for (String expr1 : exprMapSub1.get(d1)) {
                    for (String expr2 : exprMapSub2.get(d2)) {
                        exprMap.get(result).add("(" + expr2 + " - " + expr1 + ")");
                    }
                }

                // d1 * d2
                result = d1 * d2;
                if (!exprMap.containsKey(result)) {
                    exprMap.put((result), new HashSet<String>());
                }
                for (String expr1 : exprMapSub1.get(d1)) {
                    for (String expr2 : exprMapSub2.get(d2)) {
                        exprMap.get(result).add("(" + expr1 + " * " + expr2 + ")");
                    }
                }

                // d1 / d2
                result = d1 / d2;
                if (d2 != 0) {
                    if (!exprMap.containsKey(result)) {
                        exprMap.put((result), new HashSet<String>());
                    }
                    for (String expr1 : exprMapSub1.get(d1)) {
                        for (String expr2 : exprMapSub2.get(d2)) {
                            exprMap.get(result).add("(" + expr1 + " / " + expr2 + ")");
                        }
                    }
                }

                // d2 / d1
                result = d2 / d1;
                if (d1 != 0) {
                    if (!exprMap.containsKey(result)) {
                        exprMap.put((result), new HashSet<String>());
                    }
                    for (String expr1 : exprMapSub1.get(d1)) {
                        for (String expr2 : exprMapSub2.get(d2)) {
                            exprMap.get(result).add("(" + expr2 + " / " + expr1 + ")");
                        }
                    }
                }
            }
        }
    }

    private static class Record {
        public final Map<Double, Set<String>> exprMap = new HashMap<Double, Set<String>>(); // value-expression
    }
}

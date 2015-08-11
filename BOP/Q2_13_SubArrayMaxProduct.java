// find sub-array of length N-1 that has the max product

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Q2_13_SubArrayMaxProduct {
    public static void main(String[] args) {
        List<Integer> ints = new ArrayList<Integer>(Arrays.asList(0, 0, 10, -5, 4, 5));
        maxProduct(ints);
        System.out.println(ints);
        ints = new ArrayList<Integer>(Arrays.asList(0, 0, 10, -5, 4, 5));
        maxProduct2(ints);
        System.out.println(ints);

        ints = new ArrayList<Integer>(Arrays.asList(0, 10, -5, 4, 5, -1));
        maxProduct(ints);
        System.out.println(ints);
        ints = new ArrayList<Integer>(Arrays.asList(0, 10, -5, 4, 5, -1));
        maxProduct2(ints);
        System.out.println(ints);

        ints = new ArrayList<Integer>(Arrays.asList(0, 10, -5, 4, 5, -1, -2));
        maxProduct(ints);
        System.out.println(ints);
        ints = new ArrayList<Integer>(Arrays.asList(0, 10, -5, 4, 5, -1, -2));
        maxProduct2(ints);
        System.out.println(ints);

        ints = new ArrayList<Integer>(Arrays.asList(10, -5, 4, 5, -1));
        maxProduct(ints);
        System.out.println(ints);
        ints = new ArrayList<Integer>(Arrays.asList(10, -5, 4, 5, -1));
        maxProduct2(ints);
        System.out.println(ints);

        ints = new ArrayList<Integer>(Arrays.asList(-5, -1));
        maxProduct(ints);
        System.out.println(ints);
        ints = new ArrayList<Integer>(Arrays.asList(-5, -1));
        maxProduct2(ints);
        System.out.println(ints);

        ints = new ArrayList<Integer>(Arrays.asList(10, -5, 4, 5, -1, -2));
        maxProduct(ints);
        System.out.println(ints);
        ints = new ArrayList<Integer>(Arrays.asList(10, -5, 4, 5, -1, -2));
        maxProduct2(ints);
        System.out.println(ints);
    }

    // O(N)
    public static void maxProduct(List<Integer> ints) {
        int N = ints.size();

        int[] head = new int[N+1]; // head[n] = ints[0] * ... * ints[n-1], head[0] = 1 (product of first n elements)
        head[0] = 1;
        for (int n = 1; n <= N; n++) {
            head[n] = head[n-1] * ints.get(n-1);
        }

        int[] tail = new int[N+1]; // tail[n] = ints[N-n] * ... * ints[N-1], tail[0] = 1 (product of last n elements)
        tail[0] = 1;
        for (int n = 1; n <= N; n++) {
            tail[n] = tail[n-1] * ints.get(N-n);
        }

        // if ints[i] is ignored, result = head[i] * tail[N-i-1]
        int maxProduct = head[0] * tail[N-1];
        int targetI = 0;
        for (int i = 1; i < N; i++) {
            int product = head[i] * tail[N-i-1];
            if (product > maxProduct) {
                maxProduct = product;
                targetI = i;
            }
        }

        ints.remove(targetI);
    }

    public static void maxProduct2(List<Integer> ints) {
        if (ints.size() < 2) {
            throw new RuntimeException("size must be >= 2");
        }

        int zeroCount = 0;
        int zeroIdx = -1;

        int posCount = 0;
        int posMax = 0;
        int posMaxIdx = -1;
        int posMin = Integer.MAX_VALUE;
        int posMinIdx = -1;

        int negCount = 0;
        int negMax = Integer.MIN_VALUE;
        int negMaxIdx = -1;
        int negMin = 0;
        int negMinIdx = -1;

        // statistics
        for (int i = 0; i < ints.size(); i++) {
            int v = ints.get(i);
            
            if (v == 0) {
                zeroCount++;
                zeroIdx = i;
            } else if (v > 0) {
                posCount++;
                if (v > posMax) {
                    posMax = v;
                    posMaxIdx = i;
                }
                if (v < posMin) {
                    posMin = v;
                    posMinIdx = i;
                }
            } else {
                // v < 0
                negCount++;
                if (v > negMax) {
                    negMax = v;
                    negMaxIdx = i;
                }
                if (v < negMin) {
                    negMin = v;
                    negMinIdx = i;
                }
            }
        }

        // analysis
        if (zeroCount >= 2) {
            // more than 2 zeros, the result is always 0
            ints.remove(zeroIdx);
        } else if (zeroCount == 1) {
            // one zero
            if (negCount % 2 == 0) {
                // if number of negatives is even (product without this 0 is positive), remove 0
                ints.remove(zeroIdx);
            } else {
                // if number of negatives is odd (product without this 0 is negative), remove anyone other than 0
                if (zeroIdx - 1 >= 0) {
                    ints.remove(zeroIdx - 1);
                } else {
                    ints.remove(zeroIdx + 1);
                }
            }
        } else {
            // no zero
            if (negCount % 2 == 0) {
                // if number of negatives is even

                if (posCount > 0) {
                    // if exists positive number, remove posMin
                    ints.remove(posMinIdx);
                } else {
                    // if every number if negative, remove negMin
                    ints.remove(negMinIdx);
                }
            } else {
                // if number of negatives is odd, remove negMax
                ints.remove(negMaxIdx);
            }
        }
    }
}

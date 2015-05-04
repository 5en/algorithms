// find sub-array of length N-1 that has the max product

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Q2_13_SubArrayMaxProduct {
    public static void main(String[] args) {
        List<Integer> ints = new ArrayList<Integer>(Arrays.asList(0, 0, 10, -5, 4, 5));
        maxProduct(ints);
        System.out.println(ints);

        ints = new ArrayList<Integer>(Arrays.asList(0, 10, -5, 4, 5, -1));
        maxProduct(ints);
        System.out.println(ints);

        ints = new ArrayList<Integer>(Arrays.asList(0, 10, -5, 4, 5, -1, -2));
        maxProduct(ints);
        System.out.println(ints);

        ints = new ArrayList<Integer>(Arrays.asList(10, -5, 4, 5, -1));
        maxProduct(ints);
        System.out.println(ints);

        ints = new ArrayList<Integer>(Arrays.asList(-5, -1));
        maxProduct(ints);
        System.out.println(ints);

        ints = new ArrayList<Integer>(Arrays.asList(10, -5, 4, 5, -1, -2));
        maxProduct(ints);
        System.out.println(ints);
    }

    public static void maxProduct(List<Integer> ints) {
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

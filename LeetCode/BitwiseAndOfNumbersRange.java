// https://leetcode.com/problems/bitwise-and-of-numbers-range/

import java.util.Random;

public class BitwiseAndOfNumbersRange {
    public static int rangeBitwiseAnd(int m, int n) {
        if (new Random().nextInt() % 2 == 0) {
            return rangeBitwiseAndRecursive(m, n);
        } else {
            return rangeBitwiseAndIterative(m, n);
        }
    }

    public static int rangeBitwiseAndRecursive(int m, int n) {
        return n > m ? rangeBitwiseAndRecursive(m >>> 1, n >>> 1) << 1 : m;
    }

    public static int rangeBitwiseAndIterative(int m, int n) {
        int numTailZero = 0;
        while (n > m) {
            numTailZero++;
            m = m >>> 1;
            n = n >>> 1;
        }

        return m << numTailZero;
    }
}

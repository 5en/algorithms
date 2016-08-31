// https://leetcode.com/problems/integer-break/

package com.htyleo.algorithms;

import java.util.HashMap;
import java.util.Map;

public class IntegerBreak {

    public int integerBreak(int n) {
        if (n == 2) {
            return 1;
        }
        if (n == 3) {
            return 2;
        }

        Map<Integer, Integer> num2maxProd = new HashMap<>();
        return integerBreakSub(n, num2maxProd);
    }

    public int integerBreakSub(int n, Map<Integer, Integer> num2maxProd) {
        if (num2maxProd.containsKey(n)) {
            return num2maxProd.get(n);
        }

        int result = n;
        for (int i = 1; i <= n / 2; i++) {
            int prod = integerBreakSub(i, num2maxProd) * integerBreakSub(n - i, num2maxProd);
            result = Math.max(result, prod);
        }

        num2maxProd.put(n, result);

        return result;
    }

}

// https://leetcode.com/problems/lexicographical-numbers/

package com.htyleo.algorithms;

import java.util.ArrayList;
import java.util.List;

public class LexicographicalNumbers {

    public List<Integer> lexicalOrder(int n) {
        List<Integer> result = new ArrayList<Integer>();
        result.add(1);
        for (int num = 1, i = 1; i <= n - 1; i++) {
            if (num <= n / 10) {
                num *= 10;
            } else {
                while (num % 10 == 9 || num == n) {
                    num /= 10;
                }
                num++;
            }

            result.add(num);
        }

        return result;
    }

}

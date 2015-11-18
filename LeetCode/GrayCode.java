// https://leetcode.com/problems/gray-code/

package com.htyleo.algorithms;

import java.util.ArrayList;
import java.util.List;

public class GrayCode {
    public List<Integer> grayCode(int n) {
        List<Integer> result = new ArrayList<Integer>();
        result.add(0);

        for (int i = 1; i <= n; i++) {
            int headOne = 1 << i - 1;
            for (int j = result.size() - 1; j >= 0; j--) { // flip the existing and prepend 1
                result.add(result.get(j) + headOne);
            }
        }

        return result;
    }
}

// https://leetcode.com/problems/find-all-duplicates-in-an-array/#/description

package com.htyleo.algorithms;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by htyleo on 5/31/17.
 */
public class FindAllDuplicatesInAnArray {

    public List<Integer> findDuplicates(int[] nums) {
        List<Integer> res = new ArrayList<>();

        // iteratively put num to index num-1
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] - 1 == i) {
                continue;
            }

            // set a special value at the starting point nums[i]
            int num = nums[i];
            nums[i] = 0;
            while (num != 0) {
                if (nums[num - 1] == num) {
                    res.add(num);
                    break;
                }

                int nextNum = nums[num - 1];
                nums[num - 1] = num;
                num = nextNum;
            }

        }

        return res;
    }

}

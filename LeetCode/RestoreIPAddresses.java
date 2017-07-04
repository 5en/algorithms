// https://leetcode.com/problems/restore-ip-addresses/#/description

package com.htyleo.algorithms;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by htyleo on 7/5/17.
 */
public class RestoreIPAddresses {

  public List<String> restoreIpAddresses(String s) {
    List<String> res = new ArrayList<>();
    if (s.length() == 0) {
      return res;
    }

    List<Integer> nums = new ArrayList<>();
    nums.add(s.charAt(0) - '0');
    find(res, s, 1, nums);
    return res;
  }

  private void find(List<String> res, String s, int i, List<Integer> nums) {
    if (i >= s.length()) {
      if (nums.size() != 4) {
        return;
      }

      StringBuilder sb = new StringBuilder();
      for (int num : nums) {
        if (sb.length() > 0) {
          sb.append('.');
        }
        sb.append(num);
      }
      res.add(sb.toString());
      return;
    }

    int digit = s.charAt(i) - '0';

    if (nums.size() <= 3) {
      nums.add(digit);
      find(res, s, i + 1, nums);
      nums.remove(nums.size() - 1);
    }

    int lastNum = nums.get(nums.size() - 1);
    int newLastNum = lastNum * 10 + digit;
    if (lastNum != 0 && newLastNum <= 255) {
      nums.set(nums.size() - 1, newLastNum);
      find(res, s, i + 1, nums);
      nums.set(nums.size() - 1, lastNum);
    }
  }

}

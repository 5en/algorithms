// https://leetcode.com/problems/optimal-division/

package com.htyleo.algorithms;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by htyleo on 5/29/17.
 */
public class OptimalDivision {

  // O(N!) time: # of permutations
  // O(N^2) space: memo
  public String optimalDivision(int[] nums) {
    return helper(new HashMap<>(), new HashMap<>(), nums, 0, nums.length - 1, true).expr;
  }

  private Result helper(Map<Integer, Result> min, Map<Integer, Result> max, int[] nums, int low,
      int high, boolean findMax) {
    int idx = calIdx(low, high);
    if (findMax && max.containsKey(idx)) {
      return max.get(idx);
    }
    if (!findMax && min.containsKey(idx)) {
      return min.get(idx);
    }
    if (low == high) {
      Result res = new Result(nums[low], nums[low] + "");
      min.put(idx, res);
      max.put(idx, res);
      return res;
    }

    // dp based on the last division
    // [low, mid] / [mid+1, high]
    //
    // max division: max left, min right
    // min division: min left, max right
    double optVal = findMax ? 0 : Integer.MAX_VALUE;
    String optExpr = null;
    for (int mid = low; mid < high; mid++) {
      Result leftRes = null;
      Result rightRes = null;
      if (findMax) {
        leftRes = helper(min, max, nums, low, mid, true);
        rightRes = helper(min, max, nums, mid + 1, high, false);
      } else {
        leftRes = helper(min, max, nums, low, mid, false);
        rightRes = helper(min, max, nums, mid + 1, high, true);
      }

      double quotient = leftRes.val / rightRes.val;
      if (findMax ? quotient > optVal : quotient < optVal) {
        optVal = quotient;
        String rightExpr = rightRes.expr;
        // for the right part, add parathesis if it's more than just a single number.
        // if the right part already starts with a left parathesis, remove this pair first.
        if (high - (mid + 1) >= 1) {
          if (rightExpr.charAt(0) == '(') {
            int endIdx = rightExpr.indexOf(')');
            rightExpr = "(" + rightExpr.substring(1, endIdx) + rightExpr.substring(endIdx) + ")";
          } else {
            rightExpr = "(" + rightExpr + ")";
          }
        }
        optExpr = leftRes.expr + "/" + rightExpr;
      }
    }

    Result res = new Result(optVal, optExpr);
    if (findMax) {
      max.put(idx, res);
    } else {
      min.put(idx, res);
    }
    return res;
  }

  private int calIdx(int low, int high) {
    return low * 10 + high;
  }

  private int calLow(int idx) {
    return idx / 10;
  }

  private int calHigh(int idx) {
    return idx % 10;
  }

  private static class Result {

    public final double val;
    public final String expr;

    public Result(double val, String expr) {
      this.val = val;
      this.expr = expr;
    }
  }
}

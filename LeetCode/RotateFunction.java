// https://leetcode.com/problems/rotate-function/#/description

package com.htyleo.algorithms;

/**
 * Created by htyleo on 6/27/17.
 */
public class RotateFunction {

  // O(N) time, O(1) space
  public int maxRotateFunction(int[] A) {
    // F(0) = 0 * A[0] + 1 * A[1] + ... + (n-2) * A[n-2] + (n-1) * A[n-1]
    // F(1) = 0 * A[1] + 1 * A[2] + ... + (n-2) * A[n-1] + (n-1) * A[0]
    // F(2) = 0 * A[2] + 1 * A[3] + ... + (n-2) * A[0] + (n-1) * A[1]
    //
    // F(1) - F(0) = -A[0] - ... - A[n-1] + n * A[0]
    // F(2) - F(1) = -A[0] - ... - A[n-1] + n * A[1]
    // => F(i) = F(i) - sum(A[0 ... n-1]) + n * A[i]

    int N = A.length;

    int sum = 0;
    // initially prev = F[0]
    int prev = 0;
    for (int i = 0; i < N; i++) {
      prev += i * A[i];
      sum += A[i];
    }

    int opt = prev;
    for (int i = 1; i < N; i++) {
      int curr = prev - sum + N * A[i - 1];
      opt = Math.max(opt, curr);
      prev = curr;
    }

    return opt;
  }
}

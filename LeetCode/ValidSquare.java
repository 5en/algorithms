// https://leetcode.com/problems/valid-square/#/description

package com.htyleo.algorithms;

import java.util.Arrays;

/**
 * Created by htyleo on 6/21/17.
 */
public class ValidSquare {

  public boolean validSquare(int[] p1, int[] p2, int[] p3, int[] p4) {
    int[][] points = new int[4][];
    points[0] = p1;
    points[1] = p2;
    points[2] = p3;
    points[3] = p4;
    // sort by x, y
    Arrays.sort(points, (o1, o2) -> {
      if (o1[0] < o2[0]) {
        return -1;
      } else if (o1[0] > o2[0]) {
        return 1;
      } else {
        return o1[1] <= o2[1] ? -1 : 1;
      }
    });

    //    C
    // A    D
    //   B
    //
    int[] A = points[0];
    int[] B = points[1];
    int[] C = points[2];
    int[] D = points[3];

    // AD == CB
    int lenAD = (int) (Math.pow(A[0] - D[0], 2) + Math.pow(A[1] - D[1], 2));
    int lenBC = (int) (Math.pow(B[0] - C[0], 2) + Math.pow(B[1] - C[1], 2));
    if (lenAD != lenBC || lenAD == 0) {
      return false;
    }
    // AD is orthogonal to BC
    // <=> AD * BC = 0
    // <=> (D[0] - A[0]) * (C[0] - B[0]) + (D[1] - A[1]) * (C[1] - B[1]) = 0
    if ((A[1] - D[1]) * (B[1] - C[1]) + (A[0] - D[0]) * (B[0] - C[0]) != 0) {
      return false;
    }
    // AD and BC join at their middle point
    int[] midAD = new int[]{A[0] + D[0], A[1] + D[1]};
    int[] midBC = new int[]{B[0] + C[0], B[1] + C[1]};
    return midAD[0] == midBC[0] && midAD[1] == midBC[1];
  }
}

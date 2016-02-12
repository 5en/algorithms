// https://leetcode.com/problems/gas-station/

package com.htyleo.algorithms;

public class GasStation {
    // Whenever the sum is negative, reset it and let the car start from next point.
    // In the mean time, add up all of the left gas. If it's negative finally, return -1 since it's impossible to finish.
    // If it's non-negative, return the last point saved in res; 
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int N = gas.length;

        int oldStart = -1;
        int start = 0;
        while (oldStart < start) {
            int remain = 0;
            for (int i = 0; i < N; i++) {
                remain += gas[(start + i) % N] - cost[(start + i) % N];
                if (remain < 0) {
                    oldStart = start;
                    start = (start + i + 1) % N;
                    break;
                }
            }

            if (remain >= 0) {
                return start;
            }
        }

        return -1;
    }
}

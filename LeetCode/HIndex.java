// https://leetcode.com/problems/h-index/

package com.htyleo.algorithms;

import java.util.Arrays;

public class HIndex {
    // O(N) time, O(N) space
    public int hIndex(int[] citations) {
        int N = citations.length;
        // count[i] (i < N): number of citations[n] == i
        // count[N]: number of citations[n] >= N
        int[] count = new int[N + 1];
        for (int citation : citations) {
            if (citation <= 0) {
                continue;
            } else if (citation >= N) {
                count[N]++;
            } else {
                count[citation]++;
            }
        }

        for (int i = N; i >= 0; i--) {
            if (i < N) {
                count[i] += count[i + 1];
            }

            if (count[i] >= i) {
                return i;
            }
        }

        return 0;
    }

    // O(NlogN) + time, O(1) space
    public int hIndex2(int[] citations) {
        Arrays.sort(citations);

        // example [1, 2, 5, 5, 6, 6, 7]
        int N = citations.length;

        // O(N) time
        // scan from index N-1 to 0
        // continue until ((N-1) - i + 1) > citations[i]
        //        for (int i = N - 1; i >= 0; i--) {
        //            if (N - i > citations[i]) {
        //                return N - i - 1;
        //            }
        //        }
        //        return N;

        // O(logN) time
        int low = 0;
        int high = N - 1;
        int h = 0;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (N - mid <= citations[mid]) {
                h = N - mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        return h;
    }
}

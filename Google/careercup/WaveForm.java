// Arrange array in wave form A1 > a2 < a3 > a4 ...

package com.htyleo.algorithms;

import java.util.Arrays;

public class WaveForm {

    public static void main(String[] args) {
        int[] nums1 = { 1, 2, 3, 3, 5, 5, 6, 7, 8, 9 };
        waveForm(nums1);
        System.out.println(Arrays.toString(nums1));

        int[] nums2 = { 1, 2, 3, 3, 5, 5, 5, 6, 7, 8, 9 };
        waveForm(nums2);
        System.out.println(Arrays.toString(nums2));
    }

    public static void waveForm(int[] nums) {
        Arrays.sort(nums);

        int N = nums.length;
        // [0...midIndex] [midIndex + 1...N-1]
        int midIndex = N / 2 - 1;
        for (int i = 0, j = midIndex + 1; i <= midIndex && j < N; i += 2, j += 2) {
            int tmp = nums[i];
            nums[i] = nums[j];
            nums[j] = tmp;
        }
    }

}

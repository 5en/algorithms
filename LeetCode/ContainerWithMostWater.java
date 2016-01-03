// https://leetcode.com/problems/container-with-most-water/

package com.htyleo.algorithms;

public class ContainerWithMostWater {
    // find 0 <= i < j < n, max(min{height[i], height[j]} * (j - i))
    public int maxArea(int[] height) {
        int left = 0;
        int right = height.length - 1;
        int max = 0;

        while (left < right) {
            max = Math.max(max, Math.min(height[left], height[right]) * (right - left));
            if (height[left] <= height[right]) {
                left++;
            } else {
                right--;
            }
        }

        return max;
    }
}

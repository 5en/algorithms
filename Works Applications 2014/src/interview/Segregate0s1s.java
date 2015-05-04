package interview;

import java.util.Arrays;

public class Segregate0s1s {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(segregate1(new int[]{1, 0, 0, 1, 1, 0, 1})));
        System.out.println(Arrays.toString(segregate1(new int[]{0, 0, 0, 0, 0, 0, 0})));
        System.out.println(Arrays.toString(segregate1(new int[]{1, 1, 1, 1, 1, 1, 1})));
        System.out.println(Arrays.toString(segregate1(new int[]{0})));
        System.out.println(Arrays.toString(segregate1(new int[]{1})));
        System.out.println();
        System.out.println(Arrays.toString(segregate2(new int[]{1, 0, 0, 1, 1, 0, 1})));
        System.out.println(Arrays.toString(segregate2(new int[]{0, 0, 0, 0, 0, 0, 0})));
        System.out.println(Arrays.toString(segregate2(new int[]{1, 1, 1, 1, 1, 1, 1})));
        System.out.println(Arrays.toString(segregate1(new int[]{0})));
        System.out.println(Arrays.toString(segregate1(new int[]{1})));
    }

    // O(N), traverse the array twice
    public static int[] segregate1(int[] a) {
        int count0 = 0;
        int count1 = 0;
        for (int ai : a) {
            if (ai == 0) {
                count0++;
            } else if (ai == 1) {
                count1++;
            }
        }

        int[] result = new int[a.length];
        int i = 0;
        for (int j = 0; j < count0; j++) {
            result[i++] = 0;
        }
        for (int j = 0; j < count1; j++) {
            result[i++] = 1;
        }

        return result;
    }

    // O(N), traverse the array once
    public static int[] segregate2(int[] a) {
        int left = 0;
        int right = a.length - 1;

        int[] result = new int[a.length];

        while (left <= right) {
            if (a[left] == 0) {
                result[left++] = 0;
                continue;
            }

            if (a[right] == 1) {
                result[right--] = 1;
                continue;
            }

            // a[left] == 1 && a[right] == 0
            if (left < right) {
                result[left++] = 0;
                result[right--] = 1;
            }
        }

        return result;
    }
}

// https://leetcode.com/contest/3/problems/utf-8-validation/

package com.htyleo.algorithms;

public class UTF8Validation {

    public static void main(String[] args) {
        System.out.println(new UTF8Validation().validUtf8(new int[] { 197, 130, 1 }));
        System.out.println(new UTF8Validation().validUtf8(new int[] { 235, 140, 4 }));
    }

    public boolean validUtf8(int[] data) {

        int i = 0;
        while (i < data.length) {
            int b = data[i];

            if ((b >> 7) == 0) {
                // 1 byte
                i++;

            } else if ((b >> 5) == 0x6) {
                // 2 bytes
                if (!validateSub(data, i, 1)) {
                    return false;
                }

                i += 2;

            } else if ((b >> 4) == 0xE) {
                // 3 bytes
                if (!validateSub(data, i, 2)) {
                    return false;
                }

                i += 3;

            } else if ((b >> 3) == 0x1E) {
                // 4 bytes
                if (!validateSub(data, i, 3)) {
                    return false;
                }

                i += 4;

            } else {
                return false;
            }
        }

        return true;
    }

    private boolean validateSub(int[] data, int head, int count) {
        if (head + count >= data.length) {
            return false;
        }

        for (int i = head + 1; i <= head + count; i++) {
            if ((data[i] >> 6) != 0x2) {
                return false;
            }
        }

        return true;
    }

}

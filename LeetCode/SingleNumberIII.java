package com.htyleo.algorithms;

public class SingleNumberIII {
    public static int[] singleNumber(int[] nums) {
        // allXor = nums[0] ^ nums[1] ^ ... ^ nums[nums.length-1] = p ^ q
        // Since p and q are different, allXor != 0
        // Find the bit position that p and q differ.
        int allXor = 0;
        for (int num : nums) {
            allXor ^= num;
        }

        // or
        // int ref = Integer.highestOneBit(allXor);
        int ref = 1;
        for (int shift = 0; shift < 32; shift++) {
            if ((allXor & ref) != 0) {
                break;
            }
            ref = ref << 1;
        }

        int p = 0; // xor all nums that satisfy num & ref == 0
        int q = 0; // xor all nums that satisfy num & ref == 1
        for (int num : nums) {
            if ((num & ref) == 0) {
                p ^= num;
            } else {
                q ^= num;
            }
        }

        return new int[] { p, q };
    }
}

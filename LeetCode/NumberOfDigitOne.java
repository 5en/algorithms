// https://leetcode.com/problems/number-of-digit-one/

package com.htyleo.algorithms;

public class NumberOfDigitOne {
    // https://leetcode.com/discuss/85529/very-easy-to-understand-java-solution-0ms-附中文解释
    //
    // front(curr)rear
    //
    // if curr >  1: count += (front+1) * 10^len(rear)          (example: 34(2)66, 00(1)00~34(1)99)
    // if curr == 1: count += front * 10^len(rear) + rear + 1   (example: 34(1)66, 00(1)00~34(1)66)
    // if curr == 0: count += front * 10^len(rear)              (example: 34(0)66, 00(1)00~33(1)99)
    public int countDigitOne(int n) {
        long count = 0;

        long div = 1;
        while (div <= n) {
            long front = n / (div * 10);
            long curr = (n % (div * 10)) / div;
            long rear = n % div;

            if (curr > 1) {
                count += (front + 1) * div;
            } else if (curr == 1) {
                count += front * div + rear + 1;
            } else if (curr == 0) {
                count += front * div;
            }

            div *= 10;
        }

        return (int) count;
    }
}

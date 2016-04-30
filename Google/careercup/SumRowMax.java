// Imagine you have a row of numbers like below (a traiangle).
// By starting at the top of the triangle find the maximum number in each line and sum them up example below
//        5
//        9 6
//        4 6 8
//        0 7 1 5
//
//        Answer I.e. 5+9+8+7 = 29
//        writw a code to find the maximum total from top to bottom. Assume triangle can have at most 100000 rows.
//
//        Input Output specifications
//        Input Specification
//        A string of n numbers (where 0<=n<=10^10)
//        eg.5#9#6#4#6#8#0#7#1#5
//
//        Output Specification
//        A sum of the max numbers in each line (as string ) or Output invalid in case of invalid input/triangle
//
//        Examples
//        eg1.
//        Input :5#9#6#4#6#8#0#7#1#5
//        Output:29
//
//        eg 2 .
//        Input :5#9#6#4#6#8#0#7#1
//        Output:invalid
//
//        eg 2 .
//        Input :5#9##4#6#8#0#7#1
//        Output:invalid

package com.htyleo.algorithms;

public class SumRowMax {
    public static void main(String[] args) {
        System.out.println(sumRowMax("5#9#6#4#6#8#0#7#1#5"));
        System.out.println(sumRowMax("5#9#6#4#6#8#0#7#1#5#"));
        System.out.println(sumRowMax("5#9#6#4#6#8#0#7#1"));
        System.out.println(sumRowMax("5#9##4#6#8#0#7#1"));
    }

    public static int sumRowMax(String s) {
        int expectedCount = 1;
        int sumRowMax = 0;

        int count = 0;
        int rowMax = Integer.MIN_VALUE;
        int begin = 0;
        for (int curr = 0; curr < s.length(); curr++) {
            char ch = s.charAt(curr);
            if (ch != '#') {
                continue;
            }

            // case 1: #...
            // case 2: ...##...
            if (curr == begin) {
                return -1;
            }

            // num: s[begin...curr-1], begin <- curr+1
            int num = Integer.parseInt(s.substring(begin, curr));
            rowMax = Math.max(rowMax, num);
            begin = curr + 1;

            if (++count == expectedCount) {
                // last num of current row
                sumRowMax += rowMax;
                count = 0;
                rowMax = Integer.MIN_VALUE;
                expectedCount += 1;
            }
        }

        if (s.charAt(s.length() - 1) == '#') {
            return -1;
        }

        int num = Integer.parseInt(s.substring(begin));
        rowMax = Math.max(rowMax, num);
        return ++count == expectedCount ? sumRowMax + rowMax : -1;
    }
}

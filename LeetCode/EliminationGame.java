// http://www.bilibili.com/video/av6677075/index_2.html

package com.htyleo.algorithms;

public class EliminationGame {

    // keep track of the head element, until there is only one element
    //
    // case 1: left -> right, head is updated as its right element
    // case 2: left <- right
    //      2.1: if total number is even, head is unchanged
    //      2.2: if total number is odd, head is updated as its right element
    //
    // after each iteration, the difference to the right of the head is doubled
    public int lastRemaining(int n) {
        int head = 1;
        int diff = 1;
        boolean fromLeft = true;
        for (int size = n; size > 1; size /= 2) {
            if (fromLeft || size % 2 == 1) {
                head += diff;
            }

            diff *= 2;
            fromLeft = !fromLeft;
        }

        return head;
    }

}

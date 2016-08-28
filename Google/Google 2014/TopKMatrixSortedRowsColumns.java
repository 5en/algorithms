// https://leetcode.com/problems/kth-smallest-element-in-a-sorted-matrix/

/*
Let A and B be arrays of positive integers of size N such that A[0] < A[1] < ... < A[N-1], and B[0] < B[1] < ... < B[N-1].
Find the greatest N numbers (including repetitions) among A[0] + B[0], ..., A[0] + B[N-1], ..., A[N-1] + B[0], ..., A[N-1] + B[N-1]
For example, let A = [3, 5, 90], and B = [31, 800, 801], then N = 3 and the greatest 3 sums are: 891, 890, 806.
A\B
     31   800  801
3    34   803  804
5    36   805  806
90  121   890  891
Another example, let A=[3, 5, 10, 99], and B=[25, 26, 27, 31], then N = 4 and the greatest 4 sums are: 124, 125, 126, 130.
A\B
     25   26   27   31
3    28   29   30   34
5    30   31   32   36
10   35   36   37   41
99  124  125  126  130
*/

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

public class TopKMatrixSortedRowsColumns {
    public static void main(String[] args) {
        System.out.println(find(new int[] { 3, 5, 90 }, new int[] { 31, 800, 801 }));
        System.out.println(find(new int[] { 3, 5, 10, 99 }, new int[] { 25, 26, 27, 31 }));
    }

    // O(N*logN)
    public static List<Integer> find(int[] a, int[] b) {
        int N = a.length;
        List<Integer> result = new ArrayList<Integer>(N);

        PriorityQueue<Element> maxHeap = new PriorityQueue<Element>(new Comparator<Element>() {
            @Override
            public int compare(Element i1, Element i2) {
                return i1.value > i2.value ? -1 : (i1.value == i2.value ? 0 : 1);
            }
        });

        Set<Integer> visited = new HashSet<Integer>();

        maxHeap.add(new Element((N - 1) * N + N - 1, a[N - 1] + b[N - 1]));
        visited.add((N - 1) * N + N - 1);

        while (result.size() != N) {
            Element e = maxHeap.remove();
            int row = e.index / N;
            int col = e.index % N;

            result.add(e.value);

            if (row - 1 >= 0) {
                int index = (row - 1) * N + col;
                if (!visited.contains(index)) {
                    maxHeap.add(new Element(index, a[row - 1] + b[col]));
                    visited.add(index);
                }
            }
            if (col - 1 >= 0) {
                int index = row * N + (col - 1);
                if (!visited.contains(index)) {
                    maxHeap.add(new Element(index, a[row] + b[col - 1]));
                    visited.add(index);
                }
            }
        }

        return result;
    }

    private static class Element {
        public final int index;
        public final int value;

        public Element(int index, int value) {
            this.index = index;
            this.value = value;
        }
    }
}

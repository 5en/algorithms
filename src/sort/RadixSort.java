package sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class RadixSort {
    public static void main(String[] args) {
        int[] a = { 10, 3, 567, 23, 65, 55, 28, 123, 505, 32 };
        System.out.println(Arrays.toString(a));
        RadixSort.sort(a, 3);
        System.out.println(Arrays.toString(a));
    }

    // O(d*N)
    // d: maximum # of digits
    public static void sort(int[] a, int d) {
        // 10 buckets (queues)
        List<Queue<Integer>> buckets = new ArrayList<Queue<Integer>>(10);
        for (int i = 0; i < 10; i++) {
            buckets.add(new LinkedList<Integer>());
        }

        int D = 1;
        for (int k = 0; k < d; k++) {
            // array -> buckets
            for (int value : a) {
                int bi = (value / D) % 10;
                buckets.get(bi).add(value);
            }

            // buckets -> array
            int ai = 0;
            for (int i = 0; i < 10; i++) {
                while (!buckets.get(i).isEmpty()) {
                    a[ai++] = buckets.get(i).remove();
                }
            }

            D *= 10;
        }
    }
}

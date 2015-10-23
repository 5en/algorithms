package sort;

import java.util.Arrays;
import java.util.Random;

public class QuickSort {
    public static void main(String[] args) {
        int[] a = { 10, 3, 567, 23, 65, 55, 28, 123, 505, 32 };
        System.out.println(Arrays.toString(a));
        QuickSort.sort(a);
        System.out.println(Arrays.toString(a));
    }

    // worst-case O(N^2)
    // expected O(N*logN)
    // O(1) space
    public static void sort(int[] a) {
        quickSort(a, 0, a.length - 1);
    }

    private static void quickSort(int[] a, int left, int right) {
        if (left < right) {
            int pivotIdx = partition(a, left, right);
            quickSort(a, left, pivotIdx - 1);
            quickSort(a, pivotIdx + 1, right);
        }
    }

    private static int partition(int[] a, int left, int right) {
        if (left == right) {
            return left;
        }

        int pivotIdx = choosePivot(left, right);
        int pivotValue = a[pivotIdx];
        // store a[right] at pivotIdx
        a[pivotIdx] = a[right];

        // determine the index to store the pivot value
        pivotIdx = left;

        // invariant
        // a[left...pivotIdx-1] <= pivotValue
        // a[pivotIdx...right-1] > pivotValue

        for (int i = left; i <= right - 1; i++) {
            if (a[i] < pivotValue) {
                // swap a[i] and a[pivotIdx]
                int tmp = a[pivotIdx];
                a[pivotIdx] = a[i];
                a[i] = tmp;
                pivotIdx++;
            }
        }

        // now:
        // a[left...pivotIdx-1] < pivotValue
        // a[pivotIdx...right-1] >= pivotValue

        a[right] = a[pivotIdx];
        // store pivotValue at pivotIdx
        a[pivotIdx] = pivotValue;

        return pivotIdx;
    }

    private static int choosePivot(int left, int right) {
        // new Random().nextInt(n) returns [0, n)
        return left + new Random().nextInt(right - left + 1);
    }
}

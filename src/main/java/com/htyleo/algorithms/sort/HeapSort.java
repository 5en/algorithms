package com.htyleo.algorithms.sort;

import java.util.Arrays;

@SuppressWarnings("unused")
public class HeapSort {
    public static void main(String[] args) {
        int[] a = { 10, 3, 567, 23, 65, 55, 28, 123, 505, 32 };
        System.out.println(Arrays.toString(a));
        HeapSort.sort(a);
        System.out.println(Arrays.toString(a));
    }

    // worst-case O(N*logN), O(1) space
    public static void sort(int[] a) {
        Heap heap = new Heap(a);
        heap.sort();
        int[] tmp = heap.elements;
        for (int i = 0; i < a.length; i++) {
            a[i] = tmp[i + 1];
        }
    }

    // max-heap
    private static class Heap {
        public int         size;    // number of valid elements starting from index 1
        public final int[] elements;

        public Heap(int[] a) {
            elements = new int[a.length + 1];
            for (int i = 0; i < a.length; i++) {
                elements[i + 1] = a[i];
            }
            size = a.length;
            init();
        }

        public int peek() {
            return elements[1];
        }

        // O(logN)
        public int remove() {
            int max = elements[1];
            elements[1] = elements[size];
            size--;
            percolateDown(1);

            return max;
        }

        public void increase(int i, int inc) {
            if (inc < 0) {
                System.out.println("inc must >= 0");
                return;
            }

            elements[i] += inc;
            percolateUp(i);
        }

        // O(N*logN)
        public void sort() {
            for (int i = size; i >= 2; i--) {
                int max = elements[1];

                elements[1] = elements[i];
                size--;
                percolateDown(1);

                elements[i] = max;
            }
        }

        // O(N)!
        // elements[floor(N/2)+1 ... N] are leaves
        // an N-element heap has height floor(logN)
        private void init() {
            for (int i = size / 2; i >= 1; i--) {
                percolateDown(i);
            }
        }

        // O(logN)
        // assume left and right subtrees are already valid heaps
        private void percolateDown(int i) {
            if (i > size) {
                return;
            }

            int maxIdx = i;
            int l = leftChild(i);
            if (l <= size && elements[l] > elements[maxIdx]) {
                maxIdx = l;
            }
            int r = rightChild(i);
            if (r <= size && elements[r] > elements[maxIdx]) {
                maxIdx = r;
            }

            if (maxIdx != i) {
                int tmp = elements[i];
                elements[i] = elements[maxIdx];
                elements[maxIdx] = tmp;
                percolateDown(maxIdx);
            }
        }

        // assume elements[i]>=elements[left(i)] and elements[i]>=elements[right(i)]
        private void percolateUp(int i) {
            int tmp = elements[i];

            int p = parent(i);
            while (p >= 1 && elements[p] < elements[i]) {
                elements[i] = elements[p];
                i = p;
                p = parent(p);
            }

            elements[i] = tmp;
        }

        private int parent(int i) {
            return i / 2;
        }

        private int leftChild(int i) {
            return i * 2;
        }

        private int rightChild(int i) {
            return i * 2 + 1;
        }
    }
}

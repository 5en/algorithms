package com.htyleo.algorithms.sort;

import java.util.Arrays;

public class CountingSort {
    public static void main(String[] args) {
        Element[] a = new Element[7];
        a[0] = new Element(1, 13);
        a[1] = new Element(2, 13);
        a[2] = new Element(3, 10);
        a[3] = new Element(4, 10);
        a[4] = new Element(5, 15);
        a[5] = new Element(6, 13);
        a[6] = new Element(7, 16);
        System.out.println(Arrays.toString(a));
        System.out.println(Arrays.toString(sort(a, 10, 20)));
    }

    // O(N + K), K is the # of possible value
    // stable (preserve input order for equal elements)
    // assume elements in a are integers and within [lb, ub]
    public static Element[] sort(Element[] a, int lb, int ub) {
        int N = a.length;
        int K = ub - lb + 1;

        int[] count = new int[K];

        for (Element e : a) {
            count[e.v - lb]++;
        }
        // count[i] now contains the number of elements == i.

        for (int i = 1; i < K; i++) {
            count[i] += count[i - 1];
        }
        // count[i] now contains the number of elements <= i.

        Element[] b = new Element[N];
        for (int i = N - 1; i >= 0; i--) {
            b[count[a[i].v - lb] - 1] = a[i]; // currently there are count[a[i].v - lb] available elements <= a[i].v
            count[a[i].v - lb]--;
        }

        return b;
    }

    private static class Element {
        public final int id;
        public final int v;

        public Element(int id, int v) {
            this.id = id;
            this.v = v;
        }

        @Override
        public String toString() {
            return this.id + ":" + this.v;
        }
    }
}

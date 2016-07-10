package com.htyleo.algorithms;

public class PermutationIndex {

    public static void main(String[] args) {
        System.out.println(findIdx("1234"));
        System.out.println(findIdx("1243"));
        System.out.println(findIdx("1324"));
        System.out.println(findIdx("1342"));
        System.out.println(findIdx("1423"));
        System.out.println(findIdx("1432"));
        System.out.println(findIdx("2134"));
        System.out.println(findIdx("2143"));
        System.out.println(findIdx("2314"));
        System.out.println(findIdx("2341"));
        System.out.println(findIdx("2413"));
        System.out.println(findIdx("2431"));
    }

    //    0: 1234
    //    1: 1243
    //    2: 1324
    //    3: 1342
    //    4: 1423
    //    5: 1432
    //    6: 2134
    //    7: 2143
    //    8: 2314
    //    9: 2341
    //    10: 2413
    //    11: 2431 
    public static int findIdx(String perm) {

        int N = perm.length();

        // factorials[n] = n!
        int[] factorials = new int[N];
        factorials[1] = 1;
        for (int n = 2; n < N; n++) {
            factorials[n] = factorials[n - 1] * n;
        }

        // initially:
        // freq[1]: "1", freq = 0, sum = 0
        // freq[2]: "2", freq = 1, sum = 1
        // freq[3]: "3", freq = 1, sum = 2
        // ...
        // freq[9]: "9", freq = 1, sum = 8
        FenwickTree ft = new FenwickTree(N + 1);
        ft.add(1, 0);
        for (int n = 2; n <= N; n++) {
            ft.add(n, 1);
        }

        int idx = 0;
        for (int i = 0; i < N; i++) {
            int n = perm.charAt(i) - '0';
            idx += ft.sum(n) * factorials[N - i - 1];
            ft.add(n, -1);
        }

        return idx;
    }

    private static class FenwickTree {

        public static void main(String[] args) {
            FenwickTree ft = new FenwickTree(5);
            ft.add(1, 1);
            ft.add(2, 2);
            ft.add(3, 3);
            ft.add(4, 4);
            System.out.println(ft.sum(1));
            System.out.println(ft.sum(2));
            System.out.println(ft.sum(3));
            System.out.println(ft.sum(4));

            ft.add(2, 1);
            System.out.println(ft.sum(1));
            System.out.println(ft.sum(2));
            System.out.println(ft.sum(3));
            System.out.println(ft.sum(4));
        }

        // tree[0] is a dummy node
        private int[] tree;

        public FenwickTree(int size) {
            this.tree = new int[size];
        }

        // sum of freq[1]...freq[i]
        private int sum(int i) {
            int result = 0;
            while (i > 0) {
                result += tree[i];
                i -= (i & -i); // get parent
            }

            return result;
        }

        // freq[i] += inc
        public void add(int i, int inc) {
            while (i < tree.length) {
                tree[i] += inc;
                i += (i & -i); // get next to update
            }
        }

    }

}

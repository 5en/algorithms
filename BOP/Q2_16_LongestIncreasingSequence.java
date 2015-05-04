import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class Q2_16_LongestIncreasingSequence {
    public static void main(String[] args) {
        int[] a = {16, 5, 8, 6, 1, 10, 5, 2, 15, 3, 2, 4, 1}; // 5 8 10 15, 5 6 10 15, 1 2 3 4
        System.out.println("lis()");
        System.out.println(lis(a));
        System.out.println("lis2()");
        System.out.println(lis2(a));
        System.out.println("lis3()");
        System.out.println(lis3(a));
        System.out.println("li4()");
        System.out.println(lis4(a));
        System.out.println("li5()");
        System.out.println(lis5(a));
    }

    // O(N*N)
    // L[i]: length of LIS in a[0...i] that ends at a[i]
    // L[i+1] = max{1, L[k]+1}, a[i+1]>a[k], for any 0 <= k <= i
    // result = max{L[i]}
    public static int lis(int[] a) {
        int N = a.length;

        int maxL = 0;

        int[] L = new int[N];
        for (int i = 0; i < N; i++) {
            L[i] = 1;

            for (int k = 0; k < i; k++) {
                if (a[k] < a[i] && L[k] + 1 > L[i]) {
                    L[i] = L[k] + 1;
                }
            }

            if (L[i] > maxL) {
                maxL = L[i];
            }
        }

        return maxL;
    }

    // O(N*logN), output one LIS
    // scan at a[i], i = 0,...,N-1
    // M[j]: index k of the smallest value a[k] such that there is an IS of length j ending at a[k] on the range k <= i (j <= k <= i)
    // (optional) P[i]: index of the predecessor of a[i] in the LIS ending at a[i].
    //
    // The sequence a[M[1]],..., a[M[maxL]] is nondecreasing.
    // binary search through a[M[1]],..., a[M[maxL]] with a[i], find a[M[j]] < a[i] <= a[M[j+1]]
    // update M[j+1] = i, j+1 is the new length
    // (optional) update P[i] = M[j]
    public static int lis2(int[] a) {
        int N = a.length;

        int[] M = new int[N + 1]; // M[0] is not used
        M[0] = -1;
        int[] P = new int[N]; // (optional)

        int maxL = 0;

        for (int i = 0; i < N; i++) {
            // binary search for the largest positive j <= maxL such that a[M[j]] < a[i] <= a[M[j+1]]
            int low = 1;
            int high = maxL;
            while (low <= high) {
                int mid = (low + high) >>> 1;
                if (a[M[mid]] < a[i]) {
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            }

            // low is now 1 greater than the length of the longest prefix of a[i]
            // i.e. low is the length of the LIS that ends at a[i]
            int newL = low;

            // the LIS of length newL has the updated smallest ending
            M[newL] = i;

            if (newL > maxL) {
                maxL = newL;
            }

            // (optional)
            P[i] = M[newL - 1]; // if newL == 1, P[i] = M[0] = -1
        }

        // (optional)
        // reconstruct the LIS
        if (maxL > 0) {
            int[] s = new int[maxL];
            int k = M[maxL];
            for (int i = maxL - 1; i >= 0; i--) {
                s[i] = a[k];
                k = P[k];
            }
            System.out.println(Arrays.toString(s));
        }

        return maxL;
    }

    // O(N*N), output count of LIS
    // scan at a[i], i = 0,...,N-1
    // M[j]: info of LIS of length j
    public static int lis3(int[] a) {
        int N = a.length;

        Info3[] M = new Info3[N + 1]; // M[0] is not used
        for (int j = 1; j <= N; j++) {
            M[j] = new Info3();
        }

        int maxL = 0;

        for (int i = 0; i < N; i++) {
            // binary search for the largest positive j <= maxL such that a[M[j].k] < a[i] <= a[M[j+1].k]
            int low = 1;
            int high = maxL;
            while (low <= high) {
                int mid = (low + high) >>> 1;
                if (a[M[mid].k] < a[i]) {
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            }

            // low is now 1 greater than the length of the longest prefix of a[i]
            // i.e. low is the length of the LIS that ends at a[i]
            int newL = low;

            // the LIS of length newL has the updated smallest ending
            M[newL].k = i;

            if (newL > maxL) {
                maxL = newL;
            }

            // update M[newL].ks and M[newL].cs
            M[newL].ks.add(i); // the smallest a[k] is added at the end
            if (newL == 1) {
                M[newL].cs.add(1);
            } else {
                // for LIS of length newL-1, add M[newL-1].cs[k] from right to left until a[M[newL-1].ks[k]] >= a[i] 
                int count = 0;
                for (int k = M[newL - 1].ks.size() - 1; k >= 0; k--) {
                    if (a[M[newL - 1].ks.get(k)] < a[i]) {
                        count += M[newL - 1].cs.get(k);
                    } else {
                        break;
                    }
                }
                M[newL].cs.add(count);
            }
        }

        int totalCount = 0;
        if (maxL > 0) {
            for (int count : M[maxL].cs) {
                totalCount += count;
            }
        }
        System.out.println("count of LIS = " + totalCount);

        return maxL;
    }

    private static class Info3 {
        public int k; // index k of the smallest value a[k] such that there is an IS of length j ending at a[k] on the range k <= i (j <= k <= i)
        public final List<Integer> ks = new LinkedList<Integer>(); // indices ks such that there is an IS of length j ending at a[k], a[ks] is non-increasing
        public final List<Integer> cs = new LinkedList<Integer>(); // counts of IS of length j ending at a[k]
    }

    // O(N*logN), output count of LIS
    // scan at a[i], i = 0,...,N-1
    // M[j]: info of LIS of length j
    public static int lis4(int[] a) {
        int N = a.length;

        Info4[] M = new Info4[N + 1]; // M[0] is not used
        for (int j = 1; j <= N; j++) {
            M[j] = new Info4();
        }

        int maxL = 0;

        for (int i = 0; i < N; i++) {
            // binary search for the largest positive j <= maxL such that a[M[j].k] < a[i] <= a[M[j+1].k]
            int low = 1;
            int high = maxL;
            while (low <= high) {
                int mid = (low + high) >>> 1;
                if (a[M[mid].k] < a[i]) {
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            }

            // low is now 1 greater than the length of the longest prefix of a[i]
            // i.e. low is the length of the LIS that ends at a[i]
            int newL = low;

            // the LIS of length newL has the updated smallest ending
            M[newL].k = i;

            if (newL > maxL) {
                maxL = newL;
            }

            // update M[newL].ks and M[newL].cs
            M[newL].ks.add(i);
            // A: last element of M[newL]
            // B1: last element of M[newL-1]
            // B2: right-most element of M[newL-1] such that a[M[newL-1].ks[k]] >= a[i]
            // M[newL].cs.add(X);
            // if newL == 1, X = A + 1
            // else if newL > 1, X = A + B1 - B2
            int A = M[newL].cs.isEmpty() ? 0 : M[newL].cs.get(M[newL].cs.size() - 1);
            if (newL == 1) {
                M[newL].cs.add(A + 1);
            } else {
                int B1 = M[newL - 1].cs.get(M[newL - 1].cs.size() - 1);
                int B2 = 0;
                // binary search M[newL-1].ks for a[M[newL-1].ks[k]] >= a[i] > a[M[newL-1].ks[k-1]]
                int left = 0;
                int right = M[newL - 1].ks.size() - 1;
                while (left <= right) {
                    int mid = (left + right) >>> 1;
                    if (a[M[newL - 1].ks.get(mid)] >= a[i]) {
                        left = mid + 1;
                    } else {
                        right = mid - 1;
                    }
                }
                // k = right
                if (right >= 0) {
                    B2 = M[newL - 1].cs.get(right);
                }
                M[newL].cs.add(A + B1 - B2);
            }
        }

        int totalCount = 0;
        if (maxL != 0) {
            totalCount = M[maxL].cs.get(M[maxL].cs.size() - 1);
        }
        System.out.println("count of LIS = " + totalCount);
        /*
        for (int j=1; j<=maxL; j++) {
            System.out.println("length = " + j);
            System.out.println("k = " + M[j].k + ", a[k] = " + a[M[j].k]);
            for (int i = 0; i<M[j].ks.size(); i++) {
                System.out.println("k = " + M[j].ks.get(i) + ", a[k] = " + a[M[j].ks.get(i)] + ", count sum = " + M[j].cs.get(i));
            }
        }
        */
        return maxL;
    }

    private static class Info4 {
        public int k; // index k of the smallest value a[k] such that there is an IS of length j ending at a[k] on the range k <= i (j <= k <= i)
        public final List<Integer> ks = new ArrayList<Integer>(); // indices ks such that there is an IS of length j ending at a[k], a[ks] is non-increasing
        public final List<Integer> cs = new ArrayList<Integer>(); // counts of IS of length j ending at >=a[k]
    }

    // O(N*N), output all LIS
    // scan at a[i], i = 0,...,N-1
    // M[j]: info of LIS of length j
    public static int lis5(int[] a) {
        int N = a.length;

        Info5[] M = new Info5[N + 1]; // M[0] is not used
        for (int j = 1; j <= N; j++) {
            M[j] = new Info5();
        }

        int maxL = 0;

        for (int i = 0; i < N; i++) {
            // binary search for the largest positive j <= maxL such that a[M[j].k] < a[i]
            int low = 1;
            int high = maxL;
            while (low <= high) {
                int mid = (low + high) >>> 1;
                if (a[M[mid].k] < a[i]) {
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            }

            // low is now 1 greater than the length of the longest prefix of a[i]
            // i.e. low is the length of the LIS that ends at a[i]
            int newL = low;

            // the LIS of length newL has the updated smallest ending
            M[newL].k = i;

            if (newL > maxL) {
                maxL = newL;
            }

            // update M[newL].ks and M[newL].cs
            M[newL].ks.add(i);
            // A: last element of M[newL]
            // B1: last element of M[newL-1]
            // B2: right-most element of M[newL-1] such that a[M[newL-1].ks[k]] >= a[i]
            // M[newL].cs.add(X);
            // M[newL].iss.add(S);
            // if newL == 1, X = A + 1, 
            // else if newL > 1, X = A + B1 - B2
            // S = {B2+1,...,B1} append a[i]
            int A = 0;
            if (!M[newL].cs.isEmpty()) {
                A = M[newL].cs.get(M[newL].cs.size() - 1);
            }
            if (newL == 1) {
                M[newL].cs.add(A + 1);

                // IS construction
                List<Integer> is = new LinkedList<Integer>();
                is.add(a[i]);
                Set<List<Integer>> isSet = new HashSet<List<Integer>>();
                isSet.add(is);
                M[newL].iss.add(isSet);
            } else {
                int B1 = M[newL - 1].cs.get(M[newL - 1].cs.size() - 1);
                int B2 = 0;
                // binary search M[newL-1].ks
                int left = 0;
                int right = M[newL - 1].ks.size() - 1;
                while (left <= right) {
                    int mid = (left + right) >>> 1;
                    if (a[M[newL - 1].ks.get(mid)] >= a[i]) {
                        left = mid + 1;
                    } else {
                        right = mid - 1;
                    }
                }
                // k = right
                if (right >= 0) {
                    B2 = M[newL - 1].cs.get(right);
                }
                M[newL].cs.add(A + B1 - B2);

                // IS construction
                // append to M[newL-1] left -> right_end
                Set<List<Integer>> isSet = new HashSet<List<Integer>>();
                for (int k = left; k < M[newL - 1].ks.size(); k++) {
                    for (List<Integer> is : M[newL - 1].iss.get(k)) {
                        List<Integer> newIs = new LinkedList<Integer>(is);
                        newIs.add(a[i]);
                        isSet.add(newIs);
                    }
                }
                M[newL].iss.add(isSet);
            }
        }

        int totalCount = 0;
        if (maxL != 0) {
            totalCount = M[maxL].cs.get(M[maxL].cs.size() - 1);
        }
        System.out.println("count of LIS = " + totalCount);
        for (Set<List<Integer>> isSet : M[maxL].iss) {
            for (List<Integer> is : isSet) {
                System.out.println(is);
            }
        }

        return maxL;
    }

    private static class Info5 {
        public int k; // index k of the smallest value a[k] such that there is an IS of length j ending at a[k] on the range k <= i (j <= k <= i)
        public final List<Integer> ks = new ArrayList<Integer>(); // indices ks such that there is an IS of length j ending at a[k], a[ks] is non-increasing
        public final List<Integer> cs = new ArrayList<Integer>(); // counts of IS of length j ending at >=a[k]
        public final List<Set<List<Integer>>> iss = new ArrayList<Set<List<Integer>>>(); // set if IS of length j ending at a[k]
    }
}

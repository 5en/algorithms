package dp;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class SubsetSum {
    public static void main(String[] args) {
        List<Integer> A = new LinkedList<Integer>(Arrays.asList(new Integer[]{1, 2, 2, 3, 4, 5, 6, 7}));
        System.out.println(find(A, 1, 7));
        System.out.println(find(A, 2, 7));
        System.out.println(find(A, 3, 7));
        System.out.println(find(A, 4, 7));
    }

    // O(N*k*s), N is the # of elements in A
    // find(A, k, s) = find(A-{a}, k, s) union find(A-{a}, k-1, s-a)
    public static Set<List<Integer>> find(List<Integer> A, int k, int s) {
        if (A.size() == 0 || A.size() < k) {
            return null;
        }

        if (k == 1) {
            Set<List<Integer>> result = new HashSet<List<Integer>>();
            for (int a : A) {
                if (a == s) {
                    List<Integer> subset = new LinkedList<Integer>();
                    subset.add(a);
                    result.add(subset);
                }
            }
            return result;
        }

        Set<List<Integer>> result = new HashSet<List<Integer>>();
        int a = A.get(0);
        A.remove(0);

        // does not include a:
        Set<List<Integer>> r1 = find(A, k, s);
        if (r1 != null) {
            result.addAll(r1);
        }
        // include a:
        Set<List<Integer>> r2 = find(A, k - 1, s - a);
        if (r2 != null) {
            for (List<Integer> subset : r2) {
                List<Integer> newSubset = new LinkedList<Integer>(subset);
                newSubset.add(a);
                result.add(newSubset);
            }
        }

        A.add(0, a);

        return result;
    }
}

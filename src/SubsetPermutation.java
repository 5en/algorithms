// {'a', 'b', 'c'} => aa,ab,ac,ba,bb,bc,ca,cb,cc
// follow up: do not allow same character in each combination
// follow up++: constant extra space

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SubsetPermutation {
    public static void main(String[] args) {
        Set<Character> chs = new HashSet<Character>(Arrays.asList('a', 'b', 'c'));
        System.out.println(subsetPermutation1(chs, 2));
        System.out.println(subsetPermutation1(chs, 3));
        System.out.println(subsetPermutation2(chs, 2));
        System.out.println(subsetPermutation2(chs, 3));
        System.out.println(subsetPermutation3(chs, 2));
        System.out.println(subsetPermutation3(chs, 3));
    }

    public static Set<String> subsetPermutation1(Set<Character> chs, int K) {
        Set<String> result = new HashSet<String>();
        subsetPermutation1Helper(chs, K, new StringBuilder(), result);
        return result;
    }

    // pos = 0...K-1
    private static void subsetPermutation1Helper(Set<Character> chs, int K, StringBuilder sb, Set<String> result) {
        if (sb.length() == K) {
            result.add(sb.toString());
            return;
        }

        for (char ch : chs) {
            sb.append(ch);
            subsetPermutation1Helper(chs, K, sb, result);
            sb.delete(sb.length() - 1, sb.length()); // backtrack
        }
    }

    // no duplicate characters in a string
    public static Set<String> subsetPermutation2(Set<Character> chs, int K) {
        Set<String> result = new HashSet<String>();
        subsetPermutation2Helper(new ArrayList<Character>(chs), K, new StringBuilder(), result);
        return result;
    }

    // pos = 0...K-1
    private static void subsetPermutation2Helper(List<Character> chs, int K, StringBuilder sb, Set<String> result) {
        if (sb.length() == K) {
            result.add(sb.toString());
            return;
        }

        for (int i = 0; i < chs.size(); i++) {
            Character ch = chs.remove(i);
            sb.append(ch);
            subsetPermutation2Helper(chs, K, sb, result);

            // backtrack
            sb.delete(sb.length() - 1, sb.length());
            chs.add(i, ch);
        }
    }

    public static Set<String> subsetPermutation3(Set<Character> chs, int K) {
        Set<String> result = new HashSet<String>();
        List<Character> chsList = new ArrayList<Character>(chs);
        int N = chsList.size();
        int[] ks = new int[K]; // 0, ..., N-1

        while (ks[0] < N) {
            StringBuilder sb = new StringBuilder();
            for (int k = 0; k < K; k++) {
                sb.append(chsList.get(ks[k]));
            }
            result.add(sb.toString());

            int k = K - 1;
            while (k >= 0) {
                ks[k]++;
                if (ks[k] != N) {
                    break;
                }
                // ks[k] == N
                if (k == 0) {
                    break;
                }
                ks[k] = 0;
                k--;
            }
        }

        return result;
    }
}

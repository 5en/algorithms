import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class GGames {
    private static Map<Integer, Map<Integer, Set<Integer>>> map;

    static {
        Set<Integer> set01 = new HashSet<Integer>();
        set01.add(0);
        set01.add(1);
        Set<Integer> set23 = new HashSet<Integer>();
        set23.add(2);
        set23.add(3);
        Set<Integer> set45 = new HashSet<Integer>();
        set45.add(4);
        set45.add(5);
        Set<Integer> set67 = new HashSet<Integer>();
        set67.add(6);
        set67.add(7);
        Set<Integer> set0123 = new HashSet<Integer>();
        set0123.addAll(set01);
        set0123.addAll(set23);
        Set<Integer> set4567 = new HashSet<Integer>();
        set4567.addAll(set45);
        set4567.addAll(set67);
        Set<Integer> set01234567 = new HashSet<Integer>();
        set01234567.addAll(set0123);
        set01234567.addAll(set4567);

        map = new HashMap<Integer, Map<Integer, Set<Integer>>>();
        map.put(0, new HashMap<Integer, Set<Integer>>());
        map.get(0).put(1, new HashSet<Integer>());
        map.get(0).put(2, new HashSet<Integer>());
        map.get(0).put(3, new HashSet<Integer>());
        map.get(0).get(1).addAll(set01);
        map.get(0).get(2).addAll(set0123);
        map.get(0).get(3).addAll(set01234567);

        map.put(1, new HashMap<Integer, Set<Integer>>());
        map.get(1).put(1, new HashSet<Integer>());
        map.get(1).put(2, new HashSet<Integer>());
        map.get(1).put(3, new HashSet<Integer>());
        map.get(1).get(1).addAll(set01);
        map.get(1).get(2).addAll(set0123);
        map.get(1).get(3).addAll(set01234567);

        map.put(2, new HashMap<Integer, Set<Integer>>());
        map.get(2).put(1, new HashSet<Integer>());
        map.get(2).put(2, new HashSet<Integer>());
        map.get(2).put(3, new HashSet<Integer>());
        map.get(2).get(1).addAll(set23);
        map.get(2).get(2).addAll(set0123);
        map.get(2).get(3).addAll(set01234567);

        map.put(3, new HashMap<Integer, Set<Integer>>());
        map.get(3).put(1, new HashSet<Integer>());
        map.get(3).put(2, new HashSet<Integer>());
        map.get(3).put(3, new HashSet<Integer>());
        map.get(3).get(1).addAll(set23);
        map.get(3).get(2).addAll(set0123);
        map.get(3).get(3).addAll(set01234567);

        map.put(4, new HashMap<Integer, Set<Integer>>());
        map.get(4).put(1, new HashSet<Integer>());
        map.get(4).put(2, new HashSet<Integer>());
        map.get(4).put(3, new HashSet<Integer>());
        map.get(4).get(1).addAll(set45);
        map.get(4).get(2).addAll(set4567);
        map.get(4).get(3).addAll(set01234567);

        map.put(5, new HashMap<Integer, Set<Integer>>());
        map.get(5).put(1, new HashSet<Integer>());
        map.get(5).put(2, new HashSet<Integer>());
        map.get(5).put(3, new HashSet<Integer>());
        map.get(5).get(1).addAll(set45);
        map.get(5).get(2).addAll(set4567);
        map.get(5).get(3).addAll(set01234567);

        map.put(6, new HashMap<Integer, Set<Integer>>());
        map.get(6).put(1, new HashSet<Integer>());
        map.get(6).put(2, new HashSet<Integer>());
        map.get(6).put(3, new HashSet<Integer>());
        map.get(6).get(1).addAll(set67);
        map.get(6).get(2).addAll(set4567);
        map.get(6).get(3).addAll(set01234567);

        map.put(7, new HashMap<Integer, Set<Integer>>());
        map.get(7).put(1, new HashSet<Integer>());
        map.get(7).put(2, new HashSet<Integer>());
        map.get(7).put(3, new HashSet<Integer>());
        map.get(7).get(1).addAll(set67);
        map.get(7).get(2).addAll(set4567);
        map.get(7).get(3).addAll(set01234567);
    }

    public static void main(String[] args) throws IOException {
        //        Scanner sc = new Scanner(System.in);
        Scanner sc = new Scanner(new File("C-small.in.txt"));
        //        Scanner sc = new Scanner(new File("C-large.in.txt"));
        //        PrintWriter out = new PrintWriter(System.out);
        PrintWriter out = new PrintWriter("C-output.txt");

        int T = sc.nextInt();
        sc.nextLine();
        for (int t = 1; t <= T; t++) {
            System.out.println(t);
            process(t, sc, out);
        }

        out.flush();
        out.close();
    }

    private static void process(int t, Scanner sc, PrintWriter out) {
        int N = sc.nextInt();
        int M = sc.nextInt();
        Map<Integer, Elf> elfMap = new HashMap<Integer, Elf>();
        for (int m = 0; m < M; m++) {
            int id = sc.nextInt();
            int K = sc.nextInt();
            int B = sc.nextInt();
            Set<Integer> Bs = new HashSet<Integer>();
            for (int bi = 0; bi < B; bi++) {
                Bs.add(sc.nextInt());
            }
            elfMap.put(id, new Elf(id, K, Bs));

            if (K == N) {
                out.printf("Case #%d: NO\n", t);
                return;
            }
        }

        List<Integer> orderBase = new ArrayList<Integer>((int) Math.pow(2, N));
        for (int id = 1; id <= (int) Math.pow(2, N); id++) {
            orderBase.add(id);
        }

        for (List<Integer> permutation : permMemo(orderBase)) {
            boolean success = true;

            for (int order = 0; order < permutation.size(); order++) {
                int id = permutation.get(order);
                if (!elfMap.containsKey(id)) {
                    continue;
                }

                Elf elf = elfMap.get(id);
                for (int targetOrder : map.get(order).get(elf.K)) {
                    int target = permutation.get(targetOrder);
                    if (elf.Bs.contains(target)) {
                        success = false;
                        break;
                    }
                }

                if (!success) {
                    break;
                }
            }

            if (success) {
                out.printf("Case #%d: YES\n", t);
                return;
            }
        }

        out.printf("Case #%d: NO\n", t);
    }

    // with memo
    private static Set<List<Integer>> permMemo(List<Integer> a) {
        // map items to set of its permutations
        Map<Set<Integer>, Set<List<Integer>>> memo = new HashMap<Set<Integer>, Set<List<Integer>>>();

        return permMemoSR(a, 0, memo);
    }

    // a[start, a.size()-1]
    private static Set<List<Integer>> permMemoSR(List<Integer> a, int start,
                                                 Map<Set<Integer>, Set<List<Integer>>> memo) {
        Set<Integer> key = new HashSet<Integer>(a.subList(start, a.size()));

        if (memo.containsKey(key)) {
            // found in memo
            return memo.get(key);
        }

        if (start == a.size() - 1) {
            List<Integer> perm = new ArrayList<Integer>(key);
            Set<List<Integer>> permSet = new HashSet<List<Integer>>();
            permSet.add(perm);
            memo.put(key, permSet);

            return memo.get(key);
        }

        Set<List<Integer>> permSet = new HashSet<List<Integer>>();
        for (int i = start; i < a.size(); i++) {
            // swap a[start] and a[i]
            int tmp = a.get(start);
            a.set(start, a.get(i));
            a.set(i, tmp);

            for (List<Integer> subPerm : permMemoSR(a, start + 1, memo)) {
                List<Integer> perm = new ArrayList<Integer>(a.size() - start);
                perm.add(a.get(start));
                perm.addAll(subPerm);
                permSet.add(perm);
            }

            // backtrack
            tmp = a.get(start);
            a.set(start, a.get(i));
            a.set(i, tmp);
        }
        memo.put(key, permSet);

        return permSet;
    }

    private static class Elf {
        public final int          id;
        public final int          K;
        public final Set<Integer> Bs;

        public Elf(int id, int K, Set<Integer> Bs) {
            this.id = id;
            this.K = K;
            this.Bs = Bs;
        }
    }
}

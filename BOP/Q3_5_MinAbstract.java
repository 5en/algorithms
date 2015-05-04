import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Q3_5_MinAbstract {
    public static void main(String[] args) {
        String[] W = new String[]{"aa", "bb", "cc", "dd", "ee", "ff", "gg", "cc", "aa"};
        System.out.println(Arrays.toString(run(W, new String[]{"bb"})));
        System.out.println(Arrays.toString(run(W, new String[]{"aa", "cc"})));
        System.out.println(Arrays.toString(run(W, new String[]{"bb", "aa", "cc"})));
        System.out.println(Arrays.toString(run(W, new String[]{"b", "aa"})));
    }

    // O(|W|*|Q|)
    public static int[] run(String[] W, String[] Q) {
        Set<String> setQ = new HashSet<String>(Q.length);
        for (String s : Q) {
            setQ.add(s);
        }

        int optLen = W.length + 1;
        int optBegin = -1;
        int optEnd = -1;
        List<Integer> matchIdx = new ArrayList<Integer>();
        List<String> matched = new ArrayList<String>();
        int mi = 0;
        
        for (int i = 0; i < W.length; i++) {
            if (setQ.contains(W[i])) {
                matchIdx.add(i);
                matched.add(W[i]);
            }

            while (matched.subList(mi, matched.size()).containsAll(setQ)) {
                // update min length
                int tmpLen = i - matchIdx.get(mi) + 1;
                if (tmpLen < optLen) {
                    optLen = tmpLen;
                    optBegin = matchIdx.get(mi);
                    optEnd = i;
                }

                // ignore the first matched item
                mi++;
            }
        }

        return new int[]{optBegin, optEnd};
    }
}

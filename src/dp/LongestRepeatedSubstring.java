package dp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LongestRepeatedSubstring {
    public static void main(String[] args) {
        System.out.println(lrs("BANANA"));
        System.out.println(lrs("ABCD"));
    }

    // O(N*N) time, O(N) space
    // sort suffixes and compare neighbors
    public static Set<String> lrs(String s) {
        List<Suffix> suffixes = new ArrayList<Suffix>(s.length());
        for (int start = 0; start < s.length(); start++) {
            suffixes.add(new Suffix(s, start));
        }
        Collections.sort(suffixes, new Comparator<Suffix>() {
            @Override
            public int compare(Suffix sfx1, Suffix sfx2) {
                return sfx1.s.substring(sfx1.start).compareTo(sfx2.s.substring(sfx2.start));
            }
        });

        int maxLen = 0;
        Set<Integer> starts = new HashSet<Integer>(); // start positions
        for (int i = 1; i < suffixes.size(); i++) {
            Suffix sfx1 = suffixes.get(i - 1);
            Suffix sfx2 = suffixes.get(i);
            int len = lcp(sfx1, sfx2);
            if (len > maxLen) {
                maxLen = len;
                starts.clear();
                starts.add(sfx1.start);
            } else if (len == maxLen) {
                starts.add(sfx1.start);
            }
        }

        Set<String> result = new HashSet<String>(starts.size());
        for (int start : starts) {
            result.add(s.substring(start, start + maxLen));
        }

        return result;
    }

    private static int lcp(Suffix sfx1, Suffix sfx2) {
        int len = 0;

        for (int i1 = sfx1.start, i2 = sfx2.start; i1 < sfx1.s.length() && i2 < sfx2.s.length(); i1++, i2++) {
            if (sfx1.s.charAt(i1) != sfx2.s.charAt(i2)) {
                break;
            }
            len++;
        }

        return len;
    }

    private static class Suffix {
        public final String s;
        public final int start;

        public Suffix(String s, int start) {
            this.s = s;
            this.start = start;
        }
    }
}

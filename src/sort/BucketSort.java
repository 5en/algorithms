package sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BucketSort {
    public static void main(String[] args) {
        String[] strs = { "String", "string", "save", "test", "luck", "apple" };
        System.out.println(Arrays.toString(strs));
        BucketSort.sort(strs);
        System.out.println(Arrays.toString(strs));
    }

    // O(maxLen*N)
    public static void sort(String[] strs) {
        // max length
        int maxLen = 0;
        for (String str : strs) {
            if (str.length() > maxLen) {
                maxLen = str.length();
            }
        }

        // buckets
        // 0 -> empty char
        // 1 -> A
        // 2 -> a
        // 3 -> B
        // 4 -> b
        // ...
        // 51 -> Z
        // 52 -> z
        List<Queue<String>> buckets = new ArrayList<Queue<String>>();
        for (int i = 0; i < 53; i++) {
            buckets.add(new LinkedList<String>());
        }

        // align left
        // from least significant char -> most significant char
        for (int pos = maxLen - 1; pos >= 0; pos--) {
            // array -> buckets
            for (String s : strs) {
                if (s.length() < pos + 1) {
                    // assume empty char at pos
                    buckets.get(0).add(s);
                } else {
                    buckets.get(bucketIndex(s.charAt(pos))).add(s);
                }
            }

            // buckets -> array
            int i = 0;
            for (int bi = 0; bi < buckets.size(); bi++) {
                while (!buckets.get(bi).isEmpty()) {
                    strs[i] = buckets.get(bi).remove();
                    i++;
                }
            }
        }
    }

    private static int bucketIndex(char ch) {
        int index = 0;

        if (Character.isUpperCase(ch)) {
            index = 1 + (ch - 'A') * 2;
        } else if (Character.isLowerCase(ch)) {
            index = 2 + (ch - 'a') * 2;
        }

        return index;
    }
}

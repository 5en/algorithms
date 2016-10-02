// https://www.hackerrank.com/challenges/sherlock-and-anagrams

package com.htyleo.algorithms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class SherlockAndAnagrams {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        sc.nextLine();
        for (int i = 0; i < T; i++) {
            String s = sc.nextLine();
            process(s);
        }
    }

    // O(N^4) time, O(N^2) space
    //
    // s0 s1 ... sn
    // denote c[i] (ch -> count) as the character count for s0...si
    // then c[j] - c[i] is the character count for s(i+1)...sj
    private static void process(String s) {
        int N = s.length();

        List<Map<Character, Integer>> chCount = new ArrayList<>(N);
        for (int i = 0; i < N; i++) {
            char ch = s.charAt(i);
            if (i == 0) {
                Map<Character, Integer> curr = new HashMap<>();
                curr.put(ch, 1);
                chCount.add(curr);
            } else {
                Map<Character, Integer> prev = chCount.get(i - 1);
                Map<Character, Integer> curr = new HashMap<>(prev);
                curr.put(ch, curr.containsKey(ch) ? curr.get(ch) + 1 : 1);
                chCount.add(curr);
            }
        }

        int result = 0;

        // O(N^4)
        for (int len = 1; len < N; len++) {
            // i...i+len-1
            for (int i = 0; i + len - 1 < N; i++) {
                Map<Character, Integer> c1 = mapDiff(i == 0 ? new HashMap<>() : chCount.get(i - 1),
                    chCount.get(i + len - 1));

                // j...j+len-1
                for (int j = i + 1; j + len - 1 < N; j++) {
                    Map<Character, Integer> c2 = mapDiff(chCount.get(j - 1),
                        chCount.get(j + len - 1));

                    if (c1.equals(c2)) {
                        result++;
                    }
                }
            }
        }

        System.out.println(result);
    }

    // m2 - m1
    private static Map<Character, Integer> mapDiff(Map<Character, Integer> m1,
                                                   Map<Character, Integer> m2) {
        Map<Character, Integer> result = new HashMap<>(m2);
        for (Map.Entry<Character, Integer> e1 : m1.entrySet()) {
            char ch = e1.getKey();
            int count = e1.getValue();

            result.put(ch, result.get(ch) - count);
            if (result.get(ch) == 0) {
                result.remove(ch);
            }
        }

        return result;
    }

}

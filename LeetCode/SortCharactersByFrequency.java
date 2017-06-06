// https://leetcode.com/problems/sort-characters-by-frequency/#/description

package com.htyleo.algorithms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * Created by htyleo on 6/6/17.
 */
public class SortCharactersByFrequency {

  // O(N) time, bucket sort
  public String frequencySort(String s) {
    Map<Character, Integer> chCnt = new HashMap<>();
    for (int i = 0; i < s.length(); i++) {
      chCnt.compute(s.charAt(i), (k, v) -> v == null ? 1 : v + 1);
    }

    List<Queue<Character>> buckets = new ArrayList<>();
    for (int i = 0; i <= s.length(); i++) {
      buckets.add(new LinkedList<>());
    }
    chCnt.forEach((ch, cnt) -> {
      buckets.get(cnt).add(ch);
    });

    StringBuilder res = new StringBuilder();
    for (int cnt = buckets.size() - 1; cnt >= 1; cnt--) {
      Queue<Character> q = buckets.get(cnt);
      while (!q.isEmpty()) {
        char ch = q.remove();
        for (int i = 0; i < cnt; i++) {
          res.append(ch);
        }
      }
    }

    return res.toString();
  }
}

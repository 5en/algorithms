// https://leetcode.com/contest/leetcode-weekly-contest-36/problems/add-bold-tag-in-string/

package com.htyleo.algorithms;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by htyleo on 6/11/17.
 */
public class AddBoldTagInString {

  public String addBoldTag(String s, String[] dict) {
    Node trie = constructTrie(dict);
    List<int[]> regions = new ArrayList<>();
    for (int i = 0; i < s.length(); i++) {
      int[] region = findInDict(trie, s, i);
      if (region != null) {
        regions.add(region);
      }
    }

    // merge regions
    Deque<int[]> merged = new ArrayDeque<>();
    for (int[] region : regions) {
      if (merged.isEmpty()) {
        merged.add(region);
        continue;
      }

      int[] last = merged.peekLast();
      if (last[1] >= region[0]) {
        merged.removeLast();
        merged.add(new int[]{last[0], Math.max(last[1], region[1])});
      } else {
        merged.add(region);
      }
    }

    StringBuilder res = new StringBuilder(s);
    int inc = 0;
    for (int[] region : merged) {
      res.insert(region[0] + inc, "<b>");
      inc += 3;
      res.insert(region[1] + inc, "</b>");
      inc += 4;
    }

    return res.toString();
  }

  // return largest region
  // [abc]d => {0, 3}
  private int[] findInDict(Node trie, String s, int start) {
    int end = -1;

    Node curr = trie;
    for (int i = start; i < s.length(); i++) {
      char ch = s.charAt(i);
      curr = curr.map.get(ch);
      if (curr == null) {
        break;
      }
      if (curr.isEnd) {
        end = i + 1;
      }
    }

    return end == -1 ? null : new int[]{start, end};
  }

  private Node constructTrie(String[] dict) {
    Node trie = new Node();

    for (String word : dict) {
      Node curr = trie;

      for (int i = 0; i < word.length(); i++) {
        char ch = word.charAt(i);
        curr.map.putIfAbsent(ch, new Node());
        curr = curr.map.get(ch);

        if (i == word.length() - 1) {
          // last char in this word
          curr.isEnd = true;
          break;
        }
      }
    }

    return trie;
  }

  private static class Node {

    private boolean isEnd = false;
    public final Map<Character, Node> map = new HashMap<>();
  }

}

// https://leetcode.com/problems/longest-absolute-file-path/#/description

package com.htyleo.algorithms;

import java.util.Stack;

/**
 * Created by htyleo on 6/24/17.
 */
public class LongestAbsoluteFilePath {

  public int lengthLongestPath(String input) {
    int maxLen = 0;

    // length of dir1/dir2/.../dirn/
    int prevLen = 0;
    Stack<Integer> dirs = new Stack<>();
    String[] items = input.split("\n");

    for (String item : items) {
      int level = calLevel(item);
      while (dirs.size() > level) {
        prevLen -= dirs.pop();
      }

      if (isFile(item)) {
        maxLen = Math.max(maxLen, prevLen + item.length() - level);
      } else {
        // item is directory
        dirs.push(item.length() - level + 1);
        prevLen += item.length() - level + 1;
      }
    }

    return maxLen;
  }

  private boolean isFile(String s) {
    return s.contains(".");
  }

  private int calLevel(String s) {
    int res = 0;
    for (int i = 0; i < s.length(); i++) {
      if (s.charAt(i) == '\t') {
        res++;
      } else {
        break;
      }
    }

    return res;
  }
}

// https://leetcode.com/problems/mini-parser/#/description

package com.htyleo.algorithms;

import java.util.List;

/**
 * // This is the interface that allows for creating nested lists.
 * // You should not implement it, or speculate about its implementation
 * public interface NestedInteger {
 * // Constructor initializes an empty nested list.
 * public NestedInteger();
 *
 * // Constructor initializes a single integer.
 * public NestedInteger(int value);
 *
 * // @return true if this NestedInteger holds a single integer, rather than a nested list.
 * public boolean isInteger();
 *
 * // @return the single integer that this NestedInteger holds, if it holds a single integer
 * // Return null if this NestedInteger holds a nested list
 * public Integer getInteger();
 *
 * // Set this NestedInteger to hold a single integer.
 * public void setInteger(int value);
 *
 * // Set this NestedInteger to hold a nested list and adds a nested integer to it.
 * public void add(NestedInteger ni);
 *
 * // @return the nested list that this NestedInteger holds, if it holds a nested list
 * // Return null if this NestedInteger holds a single integer
 * public List<NestedInteger> getList();
 * }
 */
public class MiniParser {

  public NestedInteger deserialize(String s) {
    NestedInteger res = new NestedInteger();

    if (s == null || s.isEmpty()) {
      return res;
    }
    if (!s.startsWith("[")) {
      res.setInteger(Integer.parseInt(s));
      return res;
    }
    if (s.length() <= 2) {
      return res;
    }

    int nestedBracketCnt = 0;
    int start = 1;
    for (int i = start; i < s.length() - 1; i++) {
      char ch = s.charAt(i);
      if (ch == '[') {
        nestedBracketCnt++;
      } else if (ch == ']') {
        nestedBracketCnt--;
      } else if (ch == ',' && nestedBracketCnt == 0) {
        res.add(deserialize(s.substring(start, i)));
        start = i + 1;
      }
    }
    res.add(deserialize(s.substring(start, s.length() - 1)));

    return res;
  }

  private static class NestedInteger {

    // Constructor initializes an empty nested list.
    NestedInteger() {
    }

    // Constructor initializes a single integer.
    NestedInteger(int value) {
    }

    // @return true if this NestedInteger holds a single integer, rather than a nested list.
    public boolean isInteger() {
      return false;
    }

    // @return the single integer that this NestedInteger holds, if it holds a single integer
    // Return null if this NestedInteger holds a nested list
    public Integer getInteger() {
      return null;
    }

    // Set this NestedInteger to hold a single integer.
    public void setInteger(int value) {
    }

    // Set this NestedInteger to hold a nested list and adds a nested integer to it.
    public void add(NestedInteger ni) {
    }

    // @return the nested list that this NestedInteger holds, if it holds a nested list
    // Return null if this NestedInteger holds a single integer
    public List<NestedInteger> getList() {
      return null;
    }
  }

}

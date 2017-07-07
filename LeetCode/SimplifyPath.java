// https://leetcode.com/problems/simplify-path/#/description

package com.htyleo.algorithms;

import java.util.Stack;

/**
 * Created by htyleo on 7/7/17.
 */
public class SimplifyPath {

  public String simplifyPath(String path) {
    Stack<String> stack = new Stack<>();
    for (String s : path.split("/")) {
      if (s.isEmpty() || s.equals(".")) {
        continue;
      } else if (s.equals("..")) {
        if (!stack.isEmpty()) {
          stack.pop();
        }
      } else {
        stack.push(s);
      }
    }

    StringBuilder sb = new StringBuilder();
    while (!stack.isEmpty()) {
      sb.insert(0, "/" + stack.pop());
    }

    return sb.length() == 0 ? "/" : sb.toString();
  }
}

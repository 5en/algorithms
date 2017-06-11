// https://leetcode.com/contest/leetcode-weekly-contest-36/problems/design-compressed-string-iterator/

package com.htyleo.algorithms;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by htyleo on 6/11/17.
 */
public class DesignCompressedStringIterator {

  private Queue<Character> chs = new LinkedList<>();
  private Queue<Integer> cnts = new LinkedList<>();
  private int currCnt = 0;

  public DesignCompressedStringIterator(String compressedString) {
    String[] chars = compressedString.split("\\d+");
    String[] counts = compressedString.split("\\D");
    for (String ch : chars) {
      if (!ch.isEmpty()) {
        chs.add(ch.charAt(0));
      }
    }
    for (String cnt : counts) {
      if (!cnt.isEmpty()) {
        cnts.add(Integer.valueOf(cnt));
      }
    }

    System.out.println(chs);
    System.out.println(cnts);
  }

  public char next() {
    if (!hasNext()) {
      return ' ';
    }

    char res = chs.peek();

    if (++currCnt == cnts.peek()) {
      chs.remove();
      cnts.remove();
      currCnt = 0;
    }

    System.out.println(chs);
    System.out.println(cnts);

    return res;
  }

  public boolean hasNext() {
    return !chs.isEmpty();
  }

}

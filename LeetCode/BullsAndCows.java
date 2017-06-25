// https://leetcode.com/problems/bulls-and-cows/#/description

package com.htyleo.algorithms;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by htyleo on 6/25/17.
 */
public class BullsAndCows {

  public String getHint(String secret, String guess) {
    Map<Character, Integer> scnt = new HashMap<>();
    for (int i = 0; i < secret.length(); i++) {
      scnt.compute(secret.charAt(i), (k, v) -> v == null ? 1 : v + 1);
    }

    int A = 0;
    int B = 0;
    Map<Character, Integer> gcnt = new HashMap<>();
    for (int i = 0; i < secret.length(); i++) {
      char ch = guess.charAt(i);
      if (ch == secret.charAt(i)) {
        // bull
        A++;
        scnt.compute(ch, (k, v) -> v - 1);
      } else {
        gcnt.compute(ch, (k, v) -> v == null ? 1 : v + 1);
      }
    }

    for (char ch : gcnt.keySet()) {
      B += Math.min(gcnt.get(ch), scnt.getOrDefault(ch, 0));
    }

    return A + "A" + B + "B";
  }
}

// https://leetcode.com/problems/kill-process/#/description

package com.htyleo.algorithms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * Created by htyleo on 6/9/17.
 */
public class KillProcess {

  public List<Integer> killProcess(List<Integer> pid, List<Integer> ppid, int kill) {
    Map<Integer, List<Integer>> childs = new HashMap<>();
    for (int i = 0; i < pid.size(); i++) {
      int p = pid.get(i);
      int pp = ppid.get(i);
      childs.putIfAbsent(pp, new ArrayList<>());
      childs.get(pp).add(p);
    }

    List<Integer> res = new ArrayList<>();
    Queue<Integer> ps = new LinkedList<>(Collections.singletonList(kill));
    while (!ps.isEmpty()) {
      int p = ps.remove();
      if (childs.containsKey(p)) {
        ps.addAll(childs.get(p));
      }
      res.add(p);
    }

    return res;
  }
}

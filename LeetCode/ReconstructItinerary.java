// https://leetcode.com/problems/reconstruct-itinerary/#/description

package com.htyleo.algorithms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by htyleo on 7/1/17.
 */
public class ReconstructItinerary {

  public List<String> findItinerary(String[][] tickets) {
    Map<String, List<String>> m = new HashMap<>();
    for (String[] ticket : tickets) {
      m.putIfAbsent(ticket[0], new ArrayList<>());
      m.get(ticket[0]).add(ticket[1]);
    }
    for (List<String> to : m.values()) {
      Collections.sort(to);
    }

    List<String> res = new ArrayList<>();
    res.add("JFK");
    dfs(tickets.length + 1, m, res);

    return res;
  }

  private boolean dfs(int totalCount, Map<String, List<String>> m, List<String> res) {
    if (totalCount == res.size()) {
      return true;
    }

    List<String> to = m.get(res.get(res.size() - 1));
    if (to == null) {
      return false;
    }
    for (int i = 0; i < to.size(); i++) {
      String next = to.remove(i);
      res.add(next);
      if (dfs(totalCount, m, res)) {
        return true;
      }

      // backtrack
      res.remove(res.size() - 1);
      to.add(i, next);
    }

    return false;
  }
}

// https://leetcode.com/problems/evaluate-division/

package com.htyleo.algorithms;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class EvaluateDivision {

    // equivalent to: given a graph with weighted directed edges, find the path
    public double[] calcEquation(String[][] equations, double[] values, String[][] queries) {

        // numerator -> {denominator -> value}
        Map<String, Map<String, Double>> db = new HashMap<>();

        for (int i = 0; i < values.length; i++) {
            String[] equation = equations[i];
            String num = equation[0];
            String denom = equation[1];
            double value = values[i];

            put(db, num, denom, value);
            if (value != 0) {
                put(db, denom, num, 1 / value);
            }
        }

        double[] result = new double[queries.length];
        for (int i = 0; i < queries.length; i++) {
            String num = queries[i][0];
            String denom = queries[i][1];
            result[i] = query(db, num, denom);
        }

        return result;
    }

    private double query(Map<String, Map<String, Double>> db, String num, String denom) {
        Set<String> used = new HashSet<>();
        used.add(num);
        return querySub(db, num, denom, used, 1.0);
    }

    private double querySub(Map<String, Map<String, Double>> db, String curr, String denom,
                            Set<String> used, double value) {
        if (!db.containsKey(curr)) {
            return -1;
        }

        for (String next : db.get(curr).keySet()) {
            if (next.equals(denom)) {
                return value * db.get(curr).get(next);
            }

            if (used.contains(next)) {
                continue;
            }

            used.add(next);

            double result = querySub(db, next, denom, used, value * db.get(curr).get(next));
            if (result != -1) {
                return result;
            }

            used.remove(next);
        }

        return -1;
    }

    private void put(Map<String, Map<String, Double>> map, String key1, String key2, double value) {
        if (!map.containsKey(key1)) {
            map.put(key1, new HashMap<>());
        }
        map.get(key1).put(key2, value);
    }

}
